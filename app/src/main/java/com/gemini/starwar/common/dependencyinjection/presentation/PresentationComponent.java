package com.gemini.starwar.common.dependencyinjection.presentation;

import com.gemini.starwar.screens.characterdetails.CharacterDetailsActivity;
import com.gemini.starwar.screens.characterlist.CharacterListActivity;


import dagger.Subcomponent;

@Subcomponent(modules = PresentationModule.class)
public interface PresentationComponent {


    void inject(CharacterListActivity characterListActivity);
    void inject(CharacterDetailsActivity characterDetailsActivity);
}
