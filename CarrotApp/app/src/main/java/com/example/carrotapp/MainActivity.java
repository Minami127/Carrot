package com.example.carrotapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btm;
    HomeFragment homeFragment;
    FavoriteListFragment favoriteListFragment;
    ChatFragment chatFragment;
    ProfileFragment profileFragment;
    ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btm = findViewById(R.id.bottom_navigation);
        homeFragment = new HomeFragment();
        favoriteListFragment = new FavoriteListFragment();
        chatFragment = new ChatFragment();
        profileFragment = new ProfileFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

        btm.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_main){
                    Log.i("AAA","a");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,homeFragment).commit();
                }if(item.getItemId() == R.id.favorite_list){
                    Log.i("AAA","a");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,favoriteListFragment).commit();
                }if(item.getItemId() == R.id.menu_chat){
                    Log.i("AAA","a");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,chatFragment).commit();
                }if(item.getItemId() == R.id.menu_profile) {
                    Log.i("AAA", "a");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, profileFragment).commit();
                }
                return true;
            }

        });

    }
}