package com.example.applicationandroid;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class ManakoryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
