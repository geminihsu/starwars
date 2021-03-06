package com.gemini.starwar.common.dependencyinjection.presentation;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.gemini.starwar.networking.StarWarApi;
import com.gemini.starwar.Characteristics.FetchCharacterDetailsUseCase;
import com.gemini.starwar.screens.common.ImageLoader;
import com.gemini.starwar.screens.common.dialogs.DialogsManager;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    private final FragmentActivity mActivity;

    public PresentationModule(FragmentActivity fragmentActivity) {
        mActivity = fragmentActivity;
    }

    @Provides
    FragmentManager getFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

    @Provides
    LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    @Provides
    Activity getActivity() {
        return mActivity;
    }

    @Provides
    Context context(Activity activity) {
        return activity;
    }

    @Provides
    FetchCharacterDetailsUseCase getFetchCharacterDetailsUseCase(StarWarApi starWarApi) {
        return new FetchCharacterDetailsUseCase(starWarApi);
    }

    @Provides
    DialogsManager getDialogsManager(FragmentManager fragmentManager) {
        return new DialogsManager(fragmentManager);
    }

    @Provides
    ImageLoader getImageLoader(Activity activity) {
        return new ImageLoader(activity);
    }
}
