package com.gemini.starwar.networking;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class SingleFilmsResponseSchema {

    @SerializedName("results")
    private final List<FilmsSchema> mQuestions;

    public SingleFilmsResponseSchema(FilmsSchema question) {
        mQuestions = Collections.singletonList(question);
    }

    public FilmsSchema getQuestion() {
        return mQuestions.get(0);
    }
}
