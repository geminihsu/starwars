package com.gemini.starwar.networking;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class SingleCharacterResponseSchema {

    @SerializedName("results")
    private final List<CharacteristicsSchema> mQuestions;

    public SingleCharacterResponseSchema(CharacteristicsSchema question) {
        mQuestions = Collections.singletonList(question);
    }

    public CharacteristicsSchema getQuestion() {
        return mQuestions.get(0);
    }
}
