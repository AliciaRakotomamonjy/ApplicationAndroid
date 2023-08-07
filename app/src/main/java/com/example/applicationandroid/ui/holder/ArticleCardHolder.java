package com.example.applicationandroid.ui.holder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationandroid.ArticleDetailFragment;
import com.example.applicationandroid.R;
import com.example.applicationandroid.modele.Article;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ArticleCardHolder  extends RecyclerView.ViewHolder{

    ImageView articleImage;
    TextView articleTitre;
    TextView articleDate;
    Button articleButton;
    String id;

    Article article;

    FloatingActionButton favoriButton;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public FloatingActionButton getFavoriButton() {
        return favoriButton;
    }

    public void setFavoriButton(FloatingActionButton favoriButton) {
        this.favoriButton = favoriButton;
    }

    public ImageView getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(ImageView articleImage) {
        this.articleImage = articleImage;
    }

    public TextView getArticleTitre() {
        return articleTitre;
    }

    public void setArticleTitre(TextView articleTitre) {
        this.articleTitre = articleTitre;
    }

    public TextView getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(TextView articleDate) {
        this.articleDate = articleDate;
    }

    public Button getArticleButton() {
        return articleButton;
    }

    public void setArticleButton(Button articleButton) {
        this.articleButton = articleButton;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArticleCardHolder(@NonNull View view) {
        super(view);
        articleButton = view.findViewById(R.id.articleCardBouton);
        articleDate = view.findViewById(R.id.articleCardDate);
        articleTitre = view.findViewById(R.id.articleCardTitre);
        articleImage = view.findViewById(R.id.articleCardImage);
        initCard();
    }

    public void initCard(){
        this.articleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putParcelable(ArticleDetailFragment.ARTICLE,article);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_cardArticleFragment_to_detailArticleFragment,args);
            }
        });
    }

}
