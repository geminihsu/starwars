package com.gemini.starwar.Characteristics;

import java.util.List;

public class CharacteristicsDetails {

    private final String mName;


    private final String mHeight;

    private final String mHairColor;

    private final String mSkinColor;

    private final String mEyeColor;

    private final List<String> mfilms;

    public CharacteristicsDetails(String name, String height, String hair, String skin, String eye, List<String> films) {
        mName = name;
        mHeight = height;
        mHairColor = hair;
        mSkinColor = skin;
        mEyeColor = eye;
        mfilms = films;
    }

    public String getmName() {
        return mName;
    }

    public String getmHeight() {
        return mHeight;
    }

    public String getmHairColor() {
        return mHairColor;
    }

    public String getmSkinColor() {
        return mSkinColor;
    }

    public String getmEyeColor() {
        return mEyeColor;
    }

    public List<String> getMfilms() {
        return mfilms;
    }
}
