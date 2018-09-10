package com.gemini.starwar.screens.characterlist;

import com.gemini.starwar.questions.Character;
import com.gemini.starwar.screens.common.mvcviews.ObservableViewMvc;

import java.util.List;

public interface CharacterListViewMvc extends ObservableViewMvc<CharacterListViewMvc.Listener> {

    interface Listener {
        void onCharacterClicked(Character question);
    }

    void bindCharacter(List<Character> questions);
}
