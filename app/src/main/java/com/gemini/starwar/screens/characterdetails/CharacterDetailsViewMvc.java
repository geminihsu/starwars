package com.gemini.starwar.screens.characterdetails;

import com.gemini.starwar.questions.CharacteristicsDetails;
import com.gemini.starwar.screens.common.mvcviews.ObservableViewMvc;

public interface CharacterDetailsViewMvc extends ObservableViewMvc<CharacterDetailsViewMvc.Listener> {

    interface Listener {
        // currently no user actions
    }

    void bindCharacter(CharacteristicsDetails question);
}
