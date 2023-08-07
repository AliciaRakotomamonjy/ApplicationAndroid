package com.example.applicationandroid.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.applicationandroid.R;
import com.example.applicationandroid.helper.Constante;
import com.example.applicationandroid.helper.Utils;
import com.example.applicationandroid.modele.Categorie;
import com.example.applicationandroid.ui.holder.CategorieCardHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<CategorieCardHolder> {

    private List<Categorie> categories;

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public HomeAdapter(List<Categorie> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategorieCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_categorie_card, parent, false);
        return new CategorieCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategorieCardHolder holder, int position) {
        holder.setId(categories.get(position).get_id());
        holder.setLabel(categories.get(position).getLibelle());
        String couverture = Utils.parsePhoto(categories.get(position).getIcone());
        Picasso.get().load(Utils.getDrawableResourceId(holder.itemView.getContext(), couverture)).into(holder.getCategorieImage());
        holder.getCategorieLabel().setText(categories.get(position).getLibelle());
    }



    @Override
    public int getItemCount() {
        return getCategories().size();
    }
}
