package com.gemini.starwar.Characteristics;

import android.support.annotation.Nullable;

import com.gemini.starwar.common.BaseObservable;
import com.gemini.starwar.networking.CharacteristicsSchema;
import com.gemini.starwar.networking.FilmsSchema;
import com.gemini.starwar.networking.SingleCharacterResponseSchema;
import com.gemini.starwar.networking.SingleFilmsResponseSchema;
import com.gemini.starwar.networking.StarWarApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchCharacterDetailsUseCase extends BaseObservable<FetchCharacterDetailsUseCase.Listener> {

    public interface Listener {
        void onFetchOfCharacterDetailsSucceeded(CharacteristicsDetails question);
        void onFetchOfCharacterDetailsFailed();
        void onFetchOfFilmDetailsSucceeded(FilmsDetails question);
        void onFetchOfFilmDetailsFailed();
    }

    private final StarWarApi mStarWar;

    @Nullable Call<SingleCharacterResponseSchema> mCall;

    @Nullable Call<FilmsSchema> mFilmCall;

    public FetchCharacterDetailsUseCase(StarWarApi mStarWarApi) {
        mStarWar = mStarWarApi;
    }

    public void fetchCharacterDetailsAndNotify(String questionId) {

        cancelCurrentFetchIfActive();

        mCall = mStarWar.characterDetails(questionId);
        mCall.enqueue(new Callback<SingleCharacterResponseSchema>() {
            @Override
            public void onResponse(Call<SingleCharacterResponseSchema> call, Response<SingleCharacterResponseSchema> response) {
                if (response.isSuccessful()) {
                    notifySucceeded(questionDetailsFromQuestionSchema(response.body().getQuestion()));
                } else {
                    notifyFailed();
                }
            }

            @Override
            public void onFailure(Call<SingleCharacterResponseSchema> call, Throwable t) {
                notifyFailed();
            }
        });
    }

    public void fetchFilmsDetailsAndNotify(String questionId) {

        cancelCurrentFetchIfActive();

        mFilmCall = mStarWar.filmDetails(Integer.valueOf(questionId));
        mFilmCall.enqueue(new Callback<FilmsSchema>() {
            @Override
            public void onResponse(Call<FilmsSchema> call, Response<FilmsSchema> response) {
                if (response.isSuccessful()) {
                    notifySucceeded(filmDetailsFromFilmsSchema(response.body()));
                } else {
                    notifyFailed();
                }
            }

            @Override
            public void onFailure(Call<FilmsSchema> call, Throwable t) {
                notifyFailed();
            }
        });
    }

    private CharacteristicsDetails questionDetailsFromQuestionSchema(CharacteristicsSchema question) {
        return new CharacteristicsDetails(
                question.getmName(),
                question.getmHeight(),
                question.getmHairColor(),
                question.getMskin_color(),
                question.getmEye_color(),
                question.getmFilms()
        );
    }

    private FilmsDetails filmDetailsFromFilmsSchema(FilmsSchema question) {
        return new FilmsDetails(
                question.getmTitle(),
                question.getmEpisode_id(),
                question.getmCrawl(),
                question.getMplanets()
        );
    }

    private void cancelCurrentFetchIfActive() {
        if (mCall != null && !mCall.isCanceled() && !mCall.isExecuted()) {
            mCall.cancel();
        }
    }

    private void notifySucceeded(CharacteristicsDetails question) {
        for (Listener listener : getListeners()) {
            listener.onFetchOfCharacterDetailsSucceeded(question);
        }
    }

    private void notifyFailed() {
        for (Listener listener : getListeners()) {
            listener.onFetchOfCharacterDetailsFailed();
        }
    }

    private void notifySucceeded(FilmsDetails question) {
        for (Listener listener : getListeners()) {
            listener.onFetchOfFilmDetailsSucceeded(question);
        }
    }


}
