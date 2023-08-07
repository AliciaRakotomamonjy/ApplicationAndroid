package com.example.applicationandroid.modele;

public class MediaItem {
    private String mediaType; // "photo" ou "video"
    private String mediaPath; // Chemin/URL du média

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }
}
