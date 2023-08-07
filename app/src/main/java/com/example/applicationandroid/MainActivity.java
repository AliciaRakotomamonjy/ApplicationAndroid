package com.example.applicationandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.example.applicationandroid.ui.LoginActivity;
import com.example.applicationandroid.ui.favorite.FavoriteFragment;
import com.example.applicationandroid.ui.home.HomeFragment;
import com.example.applicationandroid.ui.profile.ProfileFragment;
import com.example.applicationandroid.ui.setting.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.applicationandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        //setSupportActionBar(binding.toolbar);
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_setting, R.id.navigation_favorite,R.id.navigation_profil)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        // Supprimer cette ligne pour ne pas configurer l'action bar avec le NavController
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        navView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("TAG","COUCOUUUUUUUUUU");
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Log.d("TAG","HOME");
                        // Afficher le fragment HomeFragment
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment_activity_main, new HomeFragment())
                                .commit();
                        return true;
                    case R.id.navigation_favorite:
                        Log.d("TAG","FAVORITE");
                        // Afficher le fragment FavoriteFragment
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment_activity_main, new FavoriteFragment())
                                .commit();
                        return true;
                    case R.id.navigation_setting:
                        Log.d("TAG","SETTING");
                        // Afficher le fragment SettingFragment
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment_activity_main, new SettingFragment())
                                .commit();
                        return true;
                    case R.id.navigation_profil:
                        Log.d("TAG","PROFILE");
                        if (isConnected) {
                            // Utilisateur connecté, afficher le fragment ProfileFragment
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.nav_host_fragment_activity_main, new ProfileFragment())
                                    .commit();
                        } else {
                            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                            finish();
                        }
                        return true;
                }
                return false;
            }
        });

    }

    // Implémenter la méthode onOptionsItemSelected() pour gérer les actions de la barre d'action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Renvoie true si la navigation vers la destination précédente a réussi
        return NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(this, R.id.nav_host_fragment_activity_main))
                || super.onOptionsItemSelected(item);
    }

    // Implémenter la méthode onSupportNavigateUp() pour permettre la navigation vers la destination précédente
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment_activity_main), (AppBarConfiguration) null);
    }

    public SharedPreferences getAppSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }
}