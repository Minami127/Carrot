package com.example.carrotapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    ImageButton postAddBtn;

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
        postAddBtn = findViewById(R.id.post_add_btn);

        postAddBtn.setVisibility(View.VISIBLE);


        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME, MODE_PRIVATE);
        String token = sp.getString("token", null);
        String userId = sp.getString("userId", null);
        String profileImg = sp.getString("profileImg", null);
        int type = sp.getInt("type", 0);

        Log.i("UserRes", "Access Token: " + token);
        Log.i("UserRes", "User ID: " + userId);
        Log.i("UserRes", "Profile Image: " + profileImg);
        Log.i("UserRes", "Type: " + type);

        postAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PostAddActivity.class);
                startActivity(intent);
                finish();
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

        btm.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                String title = ""; // 툴바 제목 초기화

                if (item.getItemId() == R.id.menu_main) {
                    selectedFragment = homeFragment;
                    title = "홈";
                    postAddBtn.setVisibility(View.VISIBLE); // 홈 프래그먼트에서 버튼 보이기
                } else {
                    postAddBtn.setVisibility(View.GONE); // 다른 프래그먼트에서는 버튼 숨기기
                    if (item.getItemId() == R.id.favorite_list) {
                        selectedFragment = favoriteListFragment;
                        title = "즐겨찾기";
                    } else if (item.getItemId() == R.id.menu_chat) {
                        selectedFragment = chatFragment;
                        title = "채팅";
                    } else if (item.getItemId() == R.id.menu_profile) {
                        selectedFragment = profileFragment;
                        title = "프로필";
                    }
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