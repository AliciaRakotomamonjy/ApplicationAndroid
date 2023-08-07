package com.example.applicationandroid;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.example.applicationandroid.databinding.FragmentArticleDetailBinding;
import com.example.applicationandroid.databinding.FragmentArticleListBinding;
import com.example.applicationandroid.helper.Utils;
import com.example.applicationandroid.modele.Article;
import com.example.applicationandroid.modele.MediaItem;
import com.example.applicationandroid.ui.adapter.ArticleListAdapter;
import com.example.applicationandroid.ui.adapter.MediaAdapter;
import com.example.applicationandroid.ui.home.ArticleListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ArticleDetailFragment extends Fragment {

    public static final String ARTICLE = "article";

    public Article article;
    FragmentArticleDetailBinding binding;
    HorizontalScrollView gallerie;
    RecyclerView recyclerView;
    TextView titreTextView;
    TextView descriptionTextView;
    TextView dateTextView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment ArticleDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleDetailFragment newInstance(Article article) {
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARTICLE,article);
        fragment.setArguments(args);
        return fragment;
    }

    public ArticleDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            article = getArguments().getParcelable(ARTICLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentArticleDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        gallerie = root.findViewById(R.id.gallerieView);
        recyclerView = root.findViewById(R.id.articleDetailRecyclerView);
        titreTextView = root.findViewById(R.id.titreTextView);
        titreTextView.setText(article.getTitre());
        dateTextView = root.findViewById(R.id.dateTextView);
        dateTextView.setText(Utils.formatDate(article.getDatecreation(),"dd MMMM YYYY", Locale.FRANCE));
        descriptionTextView = root.findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(article.getDescription());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        loadData();
        return root;
    }

    public void loadData(){
        List<MediaItem> media = new ArrayList<MediaItem>();

        if(article!=null){
            String[] images = article.getImages();
            if(images!=null){
                for(int i=0; i<images.length; i++){
                    MediaItem item = new MediaItem();
                    item.setMediaPath(images[i]);
                    item.setMediaType("photo");
                    media.add(item);
                }
            }

            String[] videos = article.getVideos();
            if(videos!=null){
                for(int i=0; i<videos.length; i++){
                    MediaItem item = new MediaItem();
                    item.setMediaPath(videos[i]);
                    item.setMediaType("video");
                    media.add(item);
                }
            }
        }
        MediaAdapter adapter = new MediaAdapter(media);
        recyclerView.setAdapter(adapter);
    }

}