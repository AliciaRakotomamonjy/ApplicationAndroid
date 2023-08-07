package com.example.applicationandroid.ui.favorite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.applicationandroid.R;
import com.example.applicationandroid.databinding.FragmentArticleListBinding;
import com.example.applicationandroid.databinding.FragmentFavoriteBinding;
import com.example.applicationandroid.helper.CallBack;
import com.example.applicationandroid.helper.webservice.ArticleHelper;
import com.example.applicationandroid.modele.Article;
import com.example.applicationandroid.ui.adapter.ArticleListAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;
    private String idUser = "64c9e5652d561f1d28aea1ac";

    private ProgressBar progressBar;
    private View containerFavori;
    private RecyclerView recyclerView;

    private ArticleListAdapter adapter;



    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
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
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressBar = root.findViewById(R.id.progressBarFavori);
        containerFavori = root.findViewById(R.id.containerFavori);
        recyclerView = root.findViewById(R.id.favoriRecyclerView);
        load();
        adapter = new ArticleListAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadData();
        return root;
    }

    public void load(){
        containerFavori.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void unload(){
        containerFavori.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    public void loadData(){
        ArticleHelper articleHelper = new ArticleHelper(getContext());
        articleHelper.getListArticleFavori(idUser,new CallBack() {
            @Override
            public void onSuccess(JSONObject response) {
                try{
                    JSONArray data = response.getJSONArray("data");
                    Gson gson = new Gson();
                    Article[] articles = gson.fromJson(data.toString(),Article[].class);
                    List<Article> listeArticles = Arrays.asList(articles);
                    adapter.setArticles(listeArticles);
                }catch (Exception e){
                    e.printStackTrace();

                }
                unload();
            }

            @Override
            public void onError(VolleyError error) {
                System.out.println("----------- error api aritcle ----------- "+error.getMessage());

                if (error instanceof NetworkError) {
                    showSnackbar("Erreur de réseau");
                } else if (error instanceof ServerError) {
                    showSnackbar("Erreur du serveur");
                } else if (error instanceof AuthFailureError) {
                    //
                    showSnackbar("Erreur d'authentification");
                } else if (error instanceof ParseError) {
                    //
                    showSnackbar("Erreur d'analyse des données");
                } else if (error instanceof NoConnectionError) {
                    //
                    showSnackbar("Pas de connexion disponible");
                } else if (error instanceof TimeoutError) {
                    //
                    showSnackbar("Délai dépassé");
                }
                unload();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showSnackbar(String message) {
        View rootView = getView(); // The root view of your layout

        Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}