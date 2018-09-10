package com.gemini.starwar.questions;

import android.support.annotation.Nullable;

import com.gemini.starwar.common.BaseObservable;
import com.gemini.starwar.networking.CharacteristicsSchema;
import com.gemini.starwar.networking.SingleCharacterResponseSchema;
import com.gemini.starwar.networking.StarWarApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchCharacterDetailsUseCase extends BaseObservable<FetchCharacterDetailsUseCase.Listener> {

    public interface Listener {
        void onFetchOfCharacterDetailsSucceeded(CharacteristicsDetails question);
        void onFetchOfCharacterDetailsFailed();
    }

    private final StarWarApi mStarWar;

    @Nullable Call<SingleCharacterResponseSchema> mCall;

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

    private CharacteristicsDetails questionDetailsFromQuestionSchema(CharacteristicsSchema question) {
        return new CharacteristicsDetails(
                question.getmName(),
                question.getmHeight(),
                question.getmHairColor(),
                question.getMskin_color(),
                question.getmEye_color()
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
}
