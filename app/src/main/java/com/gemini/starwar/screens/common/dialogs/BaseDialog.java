package com.gemini.starwar.screens.common.dialogs;

import android.support.annotation.UiThread;
import android.support.v4.app.DialogFragment;

import com.gemini.starwar.MyApplication;
import com.gemini.starwar.common.dependencyinjection.application.ApplicationComponent;
import com.gemini.starwar.common.dependencyinjection.presentation.PresentationComponent;
import com.gemini.starwar.common.dependencyinjection.presentation.PresentationModule;

public abstract class BaseDialog extends DialogFragment {

    private boolean mIsInjectorUsed;

    @UiThread
    protected PresentationComponent getPresentationComponent() {
        if (mIsInjectorUsed) {
            throw new RuntimeException("there is no need to use injector more than once");
        }
        mIsInjectorUsed = true;
        return getApplicationComponent()
                .newPresentationComponent(new PresentationModule(getActivity()));

    }

    private ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getActivity().getApplication()).getApplicationComponent();
    }
}
