package com.example.applicationandroid.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationandroid.R;
import com.example.applicationandroid.helper.Constante;
import com.example.applicationandroid.helper.Utils;
import com.example.applicationandroid.modele.Article;
import com.example.applicationandroid.ui.holder.ArticleCardHolder;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleCardHolder> {
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public ArticleListAdapter(List<Article> articles) {
        this.articles = articles;
    }

    public ArticleListAdapter() {
    }



    @NonNull
    @Override
    public ArticleCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_article_card, parent, false);
        return new ArticleCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleCardHolder holder, int position) {
        holder.setId(articles.get(position).get_id());
        String[] images = articles.get(position).getImages();
        if (images!= null && images.length>0 && images[0]!=null && !images[0].isEmpty()){
            String couverture = images[0];
            Picasso.get().load(couverture).error(R.drawable.image_par_defaut).into(holder.getArticleImage());
        }else{
            int ressource = Utils.getDrawableResourceId(holder.itemView.getContext(),Constante.DEFAULT_IMAGE);
            Picasso.get().load(ressource).error(R.drawable.image_par_defaut).into(holder.getArticleImage());
        }


        holder.getArticleDate().setText(Utils.formatDate(articles.get(position).getDatecreation(),"dd MMMM yyyy", Locale.FRANCE));
        holder.getArticleTitre().setText(articles.get(position).getTitre());
        holder.setArticle(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return getArticles().size();
    }
}
