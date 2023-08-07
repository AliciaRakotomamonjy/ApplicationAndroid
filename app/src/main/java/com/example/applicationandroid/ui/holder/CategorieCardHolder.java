package com.example.applicationandroid.ui.holder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationandroid.R;
import com.example.applicationandroid.modele.Categorie;
import com.example.applicationandroid.ui.home.ArticleListFragment;
import com.example.applicationandroid.ui.home.CategorieCardFragment;

public class CategorieCardHolder extends RecyclerView.ViewHolder{
    ImageView categorieImage;
    TextView categorieLabel;
    String id;
    String label;
    CardView categorieView;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ImageView getCategorieImage() {
        return categorieImage;
    }

    public void setCategorieImage(ImageView categorieImage) {
        this.categorieImage = categorieImage;
    }

    public TextView getCategorieLabel() {
        return categorieLabel;
    }

    public void setCategorieLabel(TextView categorieLabel) {
        this.categorieLabel = categorieLabel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CardView getCategorieView() {
        return categorieView;
    }

    public void setCategorieView(CardView categorieView) {
        this.categorieView = categorieView;
    }

    public CategorieCardHolder(@NonNull View view) {
        super(view);
        categorieView = view.findViewById(R.id.categorieCardView);
        categorieImage = view.findViewById(R.id.categorieImageView);
        categorieLabel = view.findViewById(R.id.categorieTextView);
        initCard();
    }

    public void initCard(){
        this.categorieView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();

                args.putString(ArticleListFragment.CATEGORIE_ID,id);
                args.putString(ArticleListFragment.CATEGORIE_LIBELLE,label);

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_cardFragment_to_detailFragment,args);
            }
        });
    }

}
