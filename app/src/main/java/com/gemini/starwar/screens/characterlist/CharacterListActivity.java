 package com.gemini.starwar.screens.characterlist;

 import android.app.SearchManager;
 import android.content.Context;
 import android.content.Intent;
 import android.os.Bundle;
 import android.util.Log;
 import android.view.Menu;
 import android.view.MenuInflater;
 import android.widget.SearchView;

 import com.gemini.starwar.Characteristics.Character;
 import com.gemini.starwar.Characteristics.FetchCharacterDetailsUseCase;
 import com.gemini.starwar.Characteristics.FetchCharacterListUseCase;
 import com.gemini.starwar.R;
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

     @Inject
     FetchCharacterDetailsUseCase mFetchCharacterDetailsUseCase;

     private CharacterListViewMvc mViewMvc;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         getPresentationComponent().inject(this);

         mViewMvc = mViewMvcFactory.newInstance(CharacterListViewMvc.class, null);

         setContentView(mViewMvc.getRootView());
         Intent intent = getIntent();
         if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
             String query = intent.getStringExtra(SearchManager.QUERY);
             finish();
             CharacterDetailsActivity.start(CharacterListActivity.this,query);
         }

     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.options_menu, menu);

         // Associate searchable configuration with the SearchView
         SearchManager searchManager =
                 (SearchManager) getSystemService(Context.SEARCH_SERVICE);
         SearchView searchView =
                 (SearchView) menu.findItem(R.id.search).getActionView();
         searchView.setSearchableInfo(
                 searchManager.getSearchableInfo(getComponentName()));

         return true;
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
