package com.gemini.starwar.screens.common.mvcviews;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gemini.starwar.screens.characterdetails.CharacterDetailsViewMvc;
import com.gemini.starwar.screens.characterdetails.CharacterDetailsViewMvcImpl;
import com.gemini.starwar.screens.characterlist.CharacterListViewMvc;
import com.gemini.starwar.screens.characterlist.CharacterListViewMvcImpl;
import com.gemini.starwar.screens.common.ImageLoader;

import javax.inject.Inject;

public class ViewMvcFactory {

    private final LayoutInflater mLayoutInflater;
    private ImageLoader mImageLoader;

    @Inject
    public ViewMvcFactory(LayoutInflater layoutInflater, ImageLoader imageLoader) {
        mLayoutInflater = layoutInflater;
        mImageLoader = imageLoader;
    }

    /**
     * Instantiate a new implementation of MVC view. The returned instance will be casted to MVC view
     * type inferred by java's automatic type inference.
     * @param mvcViewClass the class of the required MVC view
     * @param container this container will be used as MVC view's parent. See {@link LayoutInflater#inflate(int, ViewGroup)}
     * @param <T> the type of the required MVC view
     * @return new instance of MVC view
     */
    public <T extends ViewMvc> T newInstance(Class<T> mvcViewClass, @Nullable ViewGroup container) {

        ViewMvc viewMvc;

        if (mvcViewClass == CharacterListViewMvc.class) {
            viewMvc = new CharacterListViewMvcImpl(mLayoutInflater, container);
        }
        else if (mvcViewClass == CharacterDetailsViewMvc.class) {
            viewMvc = new CharacterDetailsViewMvcImpl(mLayoutInflater, container, mImageLoader);
        }
        else {
            throw new IllegalArgumentException("unsupported MVC view class " + mvcViewClass);
        }

        //noinspection unchecked
        return (T) viewMvc;
    }

}
