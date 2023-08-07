package com.example.applicationandroid.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.applicationandroid.R;
import com.example.applicationandroid.databinding.FragmentHomeBinding;
import com.example.applicationandroid.helper.CallBack;
import com.example.applicationandroid.helper.webservice.CategorieHelper;
import com.example.applicationandroid.modele.Categorie;
import com.example.applicationandroid.ui.adapter.HomeAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ProgressBar progressBar;
    private View containerHome;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressBar = root.findViewById(R.id.progressBarHome);
        containerHome = root.findViewById(R.id.homeContainer);
        recyclerView = root.findViewById(R.id.homeRecyclerView);
        load();
        homeAdapter = new HomeAdapter(new ArrayList<>());
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        loadData();
        return root;
    }

    public void load(){
        containerHome.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void unload(){
        containerHome.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    public void loadData(){
        CategorieHelper categorieHelper = new CategorieHelper(getContext());
        categorieHelper.getListCategorie(new CallBack() {
            @Override
            public void onSuccess(JSONObject response) {
                try{
                    JSONArray data = response.getJSONArray("data");
                    Gson gson = new Gson();
                    Categorie[] categories = gson.fromJson(data.toString(),Categorie[].class);
                    List<Categorie> listeCategorie = Arrays.asList(categories);
                    homeAdapter.setCategories(listeCategorie);

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

    private void showSnackbar(String message) {
        View rootView = getView(); // The root view of your layout

        Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}