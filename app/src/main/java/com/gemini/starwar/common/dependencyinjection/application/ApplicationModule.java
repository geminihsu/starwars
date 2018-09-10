package com.gemini.starwar.common.dependencyinjection.application;

import android.app.Application;

import com.gemini.starwar.networking.StarWarApi;
import com.gemini.starwar.questions.FetchCharacterListUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    FetchCharacterListUseCase getFetchCharacterListUseCase(StarWarApi starwarApi) {
        return new FetchCharacterListUseCase(starwarApi);
    }

}
