package com.gemini.starwar.questions;

import android.support.annotation.Nullable;

import com.gemini.starwar.common.BaseObservable;
import com.gemini.starwar.networking.CharacterListResponseSchema;
import com.gemini.starwar.networking.CharacteristicsSchema;
import com.gemini.starwar.networking.StarWarApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchCharacterListUseCase extends BaseObservable<FetchCharacterListUseCase.Listener> {

    public interface Listener {
        void onFetchOfCharacterSucceeded(List<Character> questions);
        void onFetchOfCharacterFailed();
    }

    private final StarWarApi mStarWarApi;

    @Nullable Call<CharacterListResponseSchema> mCall;

    public FetchCharacterListUseCase(StarWarApi stackoverflowApi) {
        mStarWarApi = stackoverflowApi;
    }

    public void fetchLastActiveQuestionsAndNotify() {

        cancelCurrentFetchIfActive();

        mCall = mStarWarApi.allCharacterList();
        mCall.enqueue(new Callback<CharacterListResponseSchema>() {
            @Override
            public void onResponse(Call<CharacterListResponseSchema> call, Response<CharacterListResponseSchema> response) {
                if (response.isSuccessful()) {
                    notifySucceeded(questionsFromQuestionsSchemas(response.body().getQuestions()));
                } else {
                    notifyFailed();
                }
            }

            @Override
            public void onFailure(Call<CharacterListResponseSchema> call, Throwable t) {
                notifyFailed();
            }
        });
    }

    private List<Character> questionsFromQuestionsSchemas(List<CharacteristicsSchema> questionSchemas) {
        List<Character> questions = new ArrayList<>(questionSchemas.size());
        for (CharacteristicsSchema questionSchema : questionSchemas) {
            questions.add(new Character(questionSchema.getmName(), questionSchema.getmGender()));
        }
        return questions;
    }

    private void cancelCurrentFetchIfActive() {
        if (mCall != null && !mCall.isCanceled() && !mCall.isExecuted()) {
            mCall.cancel();
        }
    }

    private void notifySucceeded(List<Character> questions) {
        List<Character> unmodifiableQuestions = Collections.unmodifiableList(questions);
        for (Listener listener : getListeners()) {
            listener.onFetchOfCharacterSucceeded(unmodifiableQuestions);
        }
    }

    private void notifyFailed() {
        for (Listener listener : getListeners()) {
            listener.onFetchOfCharacterFailed();
        }
    }
}
