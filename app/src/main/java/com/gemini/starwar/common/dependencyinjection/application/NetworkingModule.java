package com.gemini.starwar.common.dependencyinjection.application;

import com.gemini.starwar.Constants;
import com.gemini.starwar.networking.StarWarApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkingModule {

    @Singleton
    @Provides
    Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.STAR_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    @Singleton
    @Provides
    StarWarApi getStarWarApi(Retrofit retrofit) {
        return retrofit.create(StarWarApi.class);
    }

}
