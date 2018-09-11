package com.gemini.starwar.screens.characterdetails;

import com.gemini.starwar.Characteristics.CharacteristicsDetails;
import com.gemini.starwar.Characteristics.FilmsDetails;
import com.gemini.starwar.screens.common.mvcviews.ObservableViewMvc;

public interface CharacterDetailsViewMvc extends ObservableViewMvc<CharacterDetailsViewMvc.Listener> {

    interface Listener {
        // currently no user actions
    }

    void bindCharacter(CharacteristicsDetails question);
    void bindMovie(FilmsDetails question);
}
