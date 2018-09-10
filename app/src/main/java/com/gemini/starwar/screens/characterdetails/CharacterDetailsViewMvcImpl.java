package com.gemini.starwar.screens.characterdetails;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gemini.starwar.R;
import com.gemini.starwar.questions.CharacteristicsDetails;
import com.gemini.starwar.screens.common.ImageLoader;
import com.gemini.starwar.screens.common.mvcviews.BaseViewMvc;

public class CharacterDetailsViewMvcImpl extends BaseViewMvc<CharacterDetailsViewMvc.Listener>
        implements CharacterDetailsViewMvc {

    private final ImageLoader mImageLoader;


    private final TextView mTxtHeight;
    private final TextView mTxtMass;
    private final TextView mTxtHair_color;
    private final TextView mTxtUserDisplayName;
    private final ImageView mImgUserAvatar;

    public CharacterDetailsViewMvcImpl(LayoutInflater inflater, ViewGroup container, ImageLoader imageLoader) {
        mImageLoader = imageLoader;
        setRootView(inflater.inflate(R.layout.layout_question_details, container, false));

        mTxtHeight = findViewById(R.id.txt_height);
        mTxtUserDisplayName = findViewById(R.id.txt_user_display_name);
        mTxtHair_color = findViewById(R.id.txt_hair_color);
        mTxtMass = findViewById(R.id.txt_mass);
        mImgUserAvatar = findViewById(R.id.img_user_avatar);
    }

    @Override
    public void bindCharacter(CharacteristicsDetails question) {
        //String questionBody = question.getmName();

        //if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        //    mTxtQuestionBody.setText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
        //} else {
        //    mTxtQuestionBody.setText(Html.fromHtml(questionBody));
        //}

        mTxtUserDisplayName.setText(question.getmName());
        mTxtHeight.setText(question.getmHeight());
        mTxtMass.setText(question.getmHairColor());
        mTxtHair_color.setText(question.getmEyeColor());
        //mImageLoader.loadImage(question.getUserAvatarUrl(), mImgUserAvatar);
    }
}
