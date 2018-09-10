package com.gemini.starwar.questions;

public class Character {

    private final String mName;

    private final String mGender;

    public Character(String name, String gender) {
        mName = name;
        mGender = gender;
    }

    public String getmName() {
        return mName;
    }

    public String getmGender() {
        return mGender;
    }
}
