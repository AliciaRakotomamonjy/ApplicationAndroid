package com.example.applicationandroid.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.applicationandroid.R;
import com.example.applicationandroid.databinding.FragmentArticleListBinding;
import com.example.applicationandroid.databinding.FragmentHomeBinding;
import com.example.applicationandroid.helper.CallBack;
import com.example.applicationandroid.helper.webservice.ArticleHelper;
import com.example.applicationandroid.helper.webservice.CategorieHelper;
import com.example.applicationandroid.modele.Article;
import com.example.applicationandroid.modele.Categorie;
import com.example.applicationandroid.ui.adapter.ArticleListAdapter;
import com.example.applicationandroid.ui.adapter.HomeAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleListFragment extends Fragment {

    private FragmentArticleListBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String CATEGORIE_ID = "categorie_id";
    public static final String CATEGORIE_LIBELLE = "categorie_libelle";

    // TODO: Rename and change types of parameters
    private String categorieId;

    private String categorieLibelle;

    private ProgressBar progressBar;
    private View containerArticleList;
    private RecyclerView recyclerView;

    private SearchView searchView;

    private TextView categorieLabel;

    private ArticleListAdapter adapter;

    public ArticleListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ArticleListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleListFragment newInstance(String param1,String param2) {
        ArticleListFragment fragment = new ArticleListFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORIE_ID, param1);
        args.putString(CATEGORIE_LIBELLE, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categorieId = getArguments().getString(CATEGORIE_ID);
            categorieLibelle = getArguments().getString(CATEGORIE_LIBELLE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentArticleListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressBar = root.findViewById(R.id.progressBarArticle);
        containerArticleList = root.findViewById(R.id.containerArticleList);
        recyclerView = root.findViewById(R.id.articleRecyclerView);
        searchView = root.findViewById(R.id.articleRecherche);
        initSearchView();
        categorieLabel = root.findViewById(R.id.categorieLabel);
        categorieLabel.setText(categorieLibelle);

        load();
        adapter = new ArticleListAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadData();
        return root;
    }

    public void initSearchView(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                load();
                searchArticle(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Gérez les modifications de texte de recherche ici
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                load();
                loadData();
                return false;
            }
        });
    }

    public void searchArticle(String titre){
        ArticleHelper articleHelper = new ArticleHelper(getContext());
        articleHelper.rechercheArticle(titre,new CallBack() {
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

    public void load(){
        containerArticleList.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void unload(){
        containerArticleList.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    public void loadData(){
        ArticleHelper articleHelper = new ArticleHelper(getContext());
        articleHelper.getListArticle(categorieId,new CallBack() {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println("------------------retour vers home --------------");
        if (item.getItemId() == android.R.id.home) {
            NavController navController = Navigation.findNavController(requireView());
            navController.navigateUp();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSnackbar(String message) {
        View rootView = getView(); // The root view of your layout

        Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}