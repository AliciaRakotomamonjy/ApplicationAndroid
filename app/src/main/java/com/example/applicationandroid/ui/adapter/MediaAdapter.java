package com.example.applicationandroid.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationandroid.R;
import com.example.applicationandroid.modele.MediaItem;
import com.example.applicationandroid.ui.holder.MediaViewHolder;

import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaViewHolder> {
    private List<MediaItem> mediaItems;

    public MediaAdapter(List<MediaItem> mediaItems) {
        this.mediaItems = mediaItems;
    }

    public MediaAdapter() {
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_media, parent, false);
        return new MediaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        MediaItem mediaItem = mediaItems.get(position);
        holder.bind(mediaItem);
    }

    @Override
    public int getItemCount() {
        return mediaItems.size();
    }
}
