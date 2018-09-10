package com.gemini.starwar.common;

import android.app.Service;
import android.support.annotation.UiThread;

import com.gemini.starwar.MyApplication;
import com.gemini.starwar.common.dependencyinjection.application.ApplicationComponent;
import com.gemini.starwar.common.dependencyinjection.service.ServiceComponent;
import com.gemini.starwar.common.dependencyinjection.service.ServiceModule;

public abstract class BaseService extends Service {

    private boolean mIsServiceComponentUsed;

    @UiThread
    protected ServiceComponent getServiceComponent() {
        if (mIsServiceComponentUsed) {
            throw new RuntimeException("there is no reason to perform injection more than once");
        }

        mIsServiceComponentUsed = true;

        return getApplicationComponent().newServiceComponent(new ServiceModule(this));
    }

    private ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }
}
