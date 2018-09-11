package com.gemini.starwar.screens.characterlist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gemini.starwar.R;
import com.gemini.starwar.Characteristics.Character;
import com.gemini.starwar.screens.common.mvcviews.BaseViewMvc;

import java.util.ArrayList;
import java.util.List;

public class CharacterListViewMvcImpl extends BaseViewMvc<CharacterListViewMvc.Listener>
        implements CharacterListViewMvc {

    private RecyclerView mRecyclerView;
    private CharacterAdapter mQuestionsAdapter;

    public CharacterListViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        setRootView(inflater.inflate(R.layout.layout_questions_list, container, false));

        // init recycler view
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mQuestionsAdapter = new CharacterAdapter(new OnCharacterClickListener() {
            @Override
            public void onCharacterClicked(Character question) {
                for (Listener listener : getListeners()) {
                    listener.onCharacterClicked(question);
                }
            }
        });

        mRecyclerView.setAdapter(mQuestionsAdapter);
    }

    @Override
    public void bindCharacter(List<Character> questions) {
        mQuestionsAdapter.bindData(questions);
    }

    // --------------------------------------------------------------------------------------------
    // RecyclerView adapter
    // --------------------------------------------------------------------------------------------

    public interface OnCharacterClickListener {
        void onCharacterClicked(Character question);
    }

    public static class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.QuestionViewHolder> {

        private final OnCharacterClickListener mOnQuestionClickListener;

        private List<Character> mQuestionsList = new ArrayList<>(0);

        public class QuestionViewHolder extends RecyclerView.ViewHolder {
            public TextView mTitle;

            public QuestionViewHolder(View view) {
                super(view);
                mTitle = view.findViewById(R.id.txt_title);
            }
        }

        public CharacterAdapter(OnCharacterClickListener onCharacterClickListener) {
            mOnQuestionClickListener = onCharacterClickListener;
        }

        public void bindData(List<Character> questions) {
            mQuestionsList = new ArrayList<>(questions);
            notifyDataSetChanged();
        }

        @Override
        public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_question_list_item, parent, false);

            return new QuestionViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(QuestionViewHolder holder, final int position) {
            holder.mTitle.setText(mQuestionsList.get(position).getmName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnQuestionClickListener.onCharacterClicked(mQuestionsList.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mQuestionsList.size();
        }
    }
}
