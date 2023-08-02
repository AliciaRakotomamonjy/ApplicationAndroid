package com.example.applicationandroid;

import android.os.Bundle;
import android.util.Log;

import com.example.applicationandroid.helper.webservice.DeviceTokenAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.applicationandroid.databinding.ActivityMainBinding;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendToken();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initNavigation();
    }

    /**
     * initialisation du tab bar en bas
     */
    public void initNavigation(){
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_setting, R.id.navigation_favorite,R.id.navigation_profil)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    /**
     * fonction pour regarder le device token et l'envoyer vers le serveur
     */
    public void sendToken(){
        FirebaseMessaging.getInstance().getToken()
        .addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                String token = task.getResult();
                Log.d("FCM Token", token);
                DeviceTokenAPI deviceTokenAPI = new DeviceTokenAPI(this);
                try {
                    deviceTokenAPI.sendDeviceToken(token);
                } catch (JSONException e) {
                }
            } else {
                Log.e("FCM Token", "Erreur lors de la récupération du token");
            }
        });
    }




}