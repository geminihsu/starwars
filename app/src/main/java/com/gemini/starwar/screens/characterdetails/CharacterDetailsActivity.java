 package com.gemini.starwar.screens.characterdetails;

 import android.content.Context;
 import android.content.Intent;
 import android.databinding.DataBindingUtil;
 import android.os.Bundle;

 import com.gemini.starwar.Characteristics.CharacteristicsDetails;
 import com.gemini.starwar.Characteristics.FetchCharacterDetailsUseCase;
 import com.gemini.starwar.Characteristics.FilmsDetails;
 import com.gemini.starwar.R;
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

         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         getSupportActionBar().setDisplayShowHomeEnabled(true);

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
         String filmId = question.getMfilms().get(0).substring(question.getMfilms().get(0).length()-2,question.getMfilms().get(0).length() -1);
         mFetchCharacterDetailsUseCase.fetchFilmsDetailsAndNotify(filmId);
         mViewMvc.bindCharacter(question);
     }

     @Override
     public void onFetchOfCharacterDetailsFailed() {
         mDialogsManager.showDialogWithId(ServerErrorDialogFragment.newInstance(), "");
     }

     @Override
     public void onFetchOfFilmDetailsSucceeded(FilmsDetails question) {
         mViewMvc.bindMovie(question);
     }

     @Override
     public void onFetchOfFilmDetailsFailed() {

     }
 }
