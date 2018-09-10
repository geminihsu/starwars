package com.gemini.starwar.questions;

public class CharacteristicsDetails {

    private final String mName;

    private final String mHeight;

    private final String mHairColor;

    private final String mSkinColor;

    private final String mEyeColor;

    public CharacteristicsDetails(String name, String height, String hair, String skin, String eye) {
        mName = name;
        mHeight = height;
        mHairColor = hair;
        mSkinColor = skin;
        mEyeColor = eye;
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
}
