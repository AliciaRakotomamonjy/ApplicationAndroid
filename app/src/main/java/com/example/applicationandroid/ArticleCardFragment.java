package com.example.applicationandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleCardFragment extends Fragment {

    HorizontalScrollView horizontalScrollView;
    public ArticleCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ArticleCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleCardFragment newInstance() {
        ArticleCardFragment fragment = new ArticleCardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_article_card, container, false);
    }
}