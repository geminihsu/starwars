 package com.gemini.starwar.screens.characterlist;

 import android.os.Bundle;

 import com.gemini.starwar.questions.Character;
 import com.gemini.starwar.questions.FetchCharacterListUseCase;
 import com.gemini.starwar.screens.characterdetails.CharacterDetailsActivity;
 import com.gemini.starwar.screens.common.activities.BaseActivity;
 import com.gemini.starwar.screens.common.dialogs.DialogsManager;
 import com.gemini.starwar.screens.common.dialogs.ServerErrorDialogFragment;
 import com.gemini.starwar.screens.common.mvcviews.ViewMvcFactory;


 import java.util.List;

 import javax.inject.Inject;

 public class CharacterListActivity extends BaseActivity implements
         CharacterListViewMvc.Listener, FetchCharacterListUseCase.Listener {

     private static final int NUM_OF_QUESTIONS_TO_FETCH = 20;

     @Inject FetchCharacterListUseCase mFetchCharacterListUseCase;
     @Inject DialogsManager mDialogsManager;
     @Inject ViewMvcFactory mViewMvcFactory;

     private CharacterListViewMvc mViewMvc;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         getPresentationComponent().inject(this);

         mViewMvc = mViewMvcFactory.newInstance(CharacterListViewMvc.class, null);

         setContentView(mViewMvc.getRootView());

     }

     @Override
     protected void onStart() {
         super.onStart();
         mViewMvc.registerListener(this);
         mFetchCharacterListUseCase.registerListener(this);

         mFetchCharacterListUseCase.fetchLastActiveQuestionsAndNotify();
     }

     @Override
     protected void onStop() {
         super.onStop();
         mViewMvc.unregisterListener(this);
         mFetchCharacterListUseCase.unregisterListener(this);
     }


     @Override
     public void onFetchOfCharacterSucceeded(List<Character> questions) {
         mViewMvc.bindCharacter(questions);
     }

     @Override
     public void onFetchOfCharacterFailed() {
         mDialogsManager.showDialogWithId(ServerErrorDialogFragment.newInstance(), "");
     }

     @Override
     public void onCharacterClicked(Character question) {
         CharacterDetailsActivity.start(CharacterListActivity.this,question.getmName());
     }
 }
