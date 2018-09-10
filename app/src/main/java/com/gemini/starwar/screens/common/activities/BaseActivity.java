package com.gemini.starwar.screens.common.activities;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

import com.gemini.starwar.MyApplication;
import com.gemini.starwar.common.dependencyinjection.application.ApplicationComponent;
import com.gemini.starwar.common.dependencyinjection.presentation.PresentationComponent;
import com.gemini.starwar.common.dependencyinjection.presentation.PresentationModule;

public class BaseActivity extends AppCompatActivity {

    private boolean mIsInjectorUsed;

    @UiThread
    protected PresentationComponent getPresentationComponent() {
        if (mIsInjectorUsed) {
            throw new RuntimeException("there is no need to use injector more than once");
        }
        mIsInjectorUsed = true;
        return getApplicationComponent()
                .newPresentationComponent(new PresentationModule(this));

    }

    private ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }
}
