package com.gemini.starwar.networking;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmsSchema {

    @SerializedName("title")
    private final String mTitle;

    @SerializedName("episode_id")
    private final String mEpisode_id;

    @SerializedName("opening_crawl")
    private final String mCrawl;

    @SerializedName("planets")
    private final List<String> mplanets;

    public FilmsSchema(String title, String episode_id, String crawl,List<String> films) {
        mTitle = title;
        mEpisode_id = episode_id;
        mCrawl = crawl;
        mplanets = films;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmEpisode_id() {
        return mEpisode_id;
    }

    public String getmCrawl() {
        return mCrawl;
    }

    public List<String> getMplanets() {
        return mplanets;
    }
}
