package com.gemini.starwar.networking;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterListResponseSchema {

    @SerializedName("results")
    private final List<CharacteristicsSchema> mCharacter;

    public CharacterListResponseSchema(List<CharacteristicsSchema> questions) {
        mCharacter = questions;
    }

    public List<CharacteristicsSchema> getQuestions() {
        return mCharacter;
    }
}
