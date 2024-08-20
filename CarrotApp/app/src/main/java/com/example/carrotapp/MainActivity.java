package com.example.carrotapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.carrotapp.config.Config;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btm;
    HomeFragment homeFragment;
    FavoriteListFragment favoriteListFragment;
    ChatFragment chatFragment;
    ProfileFragment profileFragment;
    Toolbar mainToolbar;
    TextView toolbarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btm = findViewById(R.id.bottom_navigation);
        mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        homeFragment = new HomeFragment();
        favoriteListFragment = new FavoriteListFragment();
        chatFragment = new ChatFragment();
        profileFragment = new ProfileFragment();
        toolbarTitle = findViewById(R.id.toolbar_title);


        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);
        int type = sp.getInt("type", 0);
        Log.i("AAAAAAAAAAAAAA", "type : " + type);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

        btm.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                String title = ""; // 툴바 제목 초기화

                // if 문으로 메뉴 아이템 선택 처리
                if (item.getItemId() == R.id.menu_main) {
                    selectedFragment = homeFragment;
                    title = "홈";
                } else if (item.getItemId() == R.id.favorite_list) {
                    selectedFragment = favoriteListFragment;
                    title = "즐겨찾기";
                } else if (item.getItemId() == R.id.menu_chat) {
                    selectedFragment = chatFragment;
                    title = "채팅";
                } else if (item.getItemId() == R.id.menu_profile) {
                    selectedFragment = profileFragment;
                    title = "프로필";
                }

                // 선택된 프래그먼트가 null이 아닐 경우 변경
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    setToolbarTitle(title); // 툴바 제목 설정
                }
                return true;
            }
        });
    }

    public void setToolbarTitle(String title) {
        toolbarTitle.setText(title);
    }

}