package com.gemini.starwar.Characteristics;

import java.util.List;

public class FilmsDetails {

    private final String mTitle;


    private final String mEpisode_id;

    private final String mCrawl;


    private final List<String> mPlanets;

    public FilmsDetails(String title, String episode_id, String crawl, List<String> planets) {
        mTitle = title;
        mEpisode_id = episode_id;
        mCrawl = crawl;
        mPlanets = planets;
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

    public List<String> getmPlanets() {
        return mPlanets;
    }
}
