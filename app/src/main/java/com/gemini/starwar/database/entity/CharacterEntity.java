package com.gemini.starwar.database.entity;

/**
 * Created by geminih on 9/11/2018.
 */
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Character")
public class CharacterEntity {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "height")
    private String mHeight;

    @ColumnInfo(name = "hair_color")
    private int mHairColor;

    @ColumnInfo(name = "skin_color")
    private int mSkinColor;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmHeight() {
        return mHeight;
    }

    public void setmHeight(String mHeight) {
        this.mHeight = mHeight;
    }

    public int getmHairColor() {
        return mHairColor;
    }

    public void setmHairColor(int mHairColor) {
        this.mHairColor = mHairColor;
    }

    public int getmSkinColor() {
        return mSkinColor;
    }

    public void setmSkinColor(int mSkinColor) {
        this.mSkinColor = mSkinColor;
    }
}
