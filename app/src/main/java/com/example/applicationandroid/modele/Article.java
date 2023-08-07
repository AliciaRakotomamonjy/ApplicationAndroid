package com.example.applicationandroid.modele;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Article implements Parcelable {

    String _id;
    String[] images;
    String[] videos;
    String titre;
    String description;
    Date datecreation;

//    Avis[] avis;

    boolean favori;

    public boolean isFavori() {
        return favori;
    }

    public void setFavori(boolean favori) {
        this.favori = favori;
    }

    public Article() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String[] getVideos() {
        return videos;
    }

    public void setVideos(String[] videos) {
        this.videos = videos;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeStringArray(images);
        parcel.writeStringArray(videos);
        parcel.writeString(titre);
        parcel.writeString(description);
        parcel.writeLong(datecreation.getTime());
//        parcel.writeTypedArray(avis, i);
    }

    protected Article(Parcel in) {
        _id = in.readString();
        images = in.createStringArray();
        videos = in.createStringArray();
        titre = in.readString();
        description = in.readString();
        long dateMillis = in.readLong();
        datecreation = new Date(dateMillis);
//        avis = in.createTypedArray(Avis.CREATOR);
    }
}
