package com.example.applicationandroid.ui.holder;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationandroid.R;
import com.example.applicationandroid.modele.MediaItem;
import com.squareup.picasso.Picasso;

public class MediaViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private VideoView videoView;

    public MediaViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.articleDetailImageView);
        videoView = itemView.findViewById(R.id.articleDetailVideoView);
    }

    public void bind(MediaItem mediaItem) {
        // Affichez la photo ou la vidéo en fonction du type de média
        if ("photo".equals(mediaItem.getMediaType())) {
            imageView.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.GONE);
            Picasso.get().load(mediaItem.getMediaPath()).into(imageView);
            // Chargez et affichez l'image
            // Utilisez une bibliothèque comme Picasso ou Glide pour le chargement d'image
        } else if ("video".equals(mediaItem.getMediaType())) {
            imageView.setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);
            videoView.setVideoURI(Uri.parse(mediaItem.getMediaPath()));

            // Chargez et affichez la vidéo
            // Configurez le lecteur vidéo et définissez le chemin/URL de la vidéo
        }
    }
}
