 package com.gemini.starwar.screens.characterdetails;

 import android.content.Context;
 import android.content.Intent;
 import android.os.Bundle;

 import com.gemini.starwar.questions.CharacteristicsDetails;
 import com.gemini.starwar.questions.FetchCharacterDetailsUseCase;
 import com.gemini.starwar.screens.common.activities.BaseActivity;
 import com.gemini.starwar.screens.common.dialogs.DialogsManager;
 import com.gemini.starwar.screens.common.dialogs.ServerErrorDialogFragment;
 import com.gemini.starwar.screens.common.mvcviews.ViewMvcFactory;

 import javax.inject.Inject;

 public class CharacterDetailsActivity extends BaseActivity implements
         CharacterDetailsViewMvc.Listener, FetchCharacterDetailsUseCase.Listener {

     public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

     public static void start(Context context, String questionId) {
         Intent intent = new Intent(context, CharacterDetailsActivity.class);
         intent.putExtra(EXTRA_QUESTION_ID, questionId);
         context.startActivity(intent);
     }

     @Inject
     FetchCharacterDetailsUseCase mFetchCharacterDetailsUseCase;
     @Inject DialogsManager mDialogsManager;
     @Inject ViewMvcFactory mViewMvcFactory;

     private String mQuestionId;

     private CharacterDetailsViewMvc mViewMvc;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         getPresentationComponent().inject(this);

         mViewMvc = mViewMvcFactory.newInstance(CharacterDetailsViewMvc.class, null);

         setContentView(mViewMvc.getRootView());

         //noinspection ConstantConditions
         mQuestionId = getIntent().getExtras().getString(EXTRA_QUESTION_ID);
     }

     @Override
     protected void onStart() {
         super.onStart();
         mViewMvc.registerListener(this);
         mFetchCharacterDetailsUseCase.registerListener(this);

         mFetchCharacterDetailsUseCase.fetchCharacterDetailsAndNotify(mQuestionId);
     }

     @Override
     protected void onStop() {
         super.onStop();
         mViewMvc.unregisterListener(this);
         mFetchCharacterDetailsUseCase.unregisterListener(this);
     }


     @Override
     public void onFetchOfCharacterDetailsSucceeded(CharacteristicsDetails question) {
         mViewMvc.bindCharacter(question);
     }

     @Override
     public void onFetchOfCharacterDetailsFailed() {
         mDialogsManager.showDialogWithId(ServerErrorDialogFragment.newInstance(), "");
     }
 }
