package com.gemini.starwar;

import android.app.Application;

import com.gemini.starwar.common.dependencyinjection.application.ApplicationComponent;
import com.gemini.starwar.common.dependencyinjection.application.ApplicationModule;
import com.gemini.starwar.common.dependencyinjection.application.DaggerApplicationComponent;

public class MyApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
