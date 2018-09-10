package com.gemini.starwar.networking;

import com.google.gson.annotations.SerializedName;

public class CharacteristicsSchema {

    @SerializedName("name")
    private final String mName;

    @SerializedName("height")
    private final String mHeight;

    @SerializedName("hair_color")
    private final String mHairColor;

    @SerializedName("skin_color")
    private final String mskin_color;

    @SerializedName("eye_color")
    private final String mEye_color;

    @SerializedName("birth_year")
    private final String mBirth_year;

    @SerializedName("gender")
    private final String mGender;


    public CharacteristicsSchema(String name, String height, String hair_color, String skin_color,String eye_color,String birth_year,String gender) {
        mName = name;
        mHeight = height;
        mHairColor = hair_color;
        mskin_color = skin_color;
        mEye_color = eye_color;
        mBirth_year = birth_year;
        mGender = gender;

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

    public String getMskin_color() {
        return mskin_color;
    }

    public String getmEye_color() {
        return mEye_color;
    }

    public String getmBirth_year() {
        return mBirth_year;
    }

    public String getmGender() {
        return mGender;
    }

}
