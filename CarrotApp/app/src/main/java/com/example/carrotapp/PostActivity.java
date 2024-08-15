package com.example.carrotapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.carrotapp.api.NetworkClient;
import com.example.carrotapp.api.PostApi;
import com.example.carrotapp.config.Config;
import com.example.carrotapp.model.Post;
import com.example.carrotapp.model.PostList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PostActivity extends AppCompatActivity {

    private int id;
    ImageButton imgBtn;
    TextView postPrice,postTitle,priceTag,description;
    ImageView productImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        imgBtn = findViewById(R.id.img_back_btn_post);
        productImg = findViewById(R.id.product_image);
        postPrice = findViewById(R.id.post_price);
        postTitle = findViewById(R.id.post_title);
        priceTag = findViewById(R.id.price_tag);
        description = findViewById(R.id.description);


        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        id = getIntent().getIntExtra("id", -1);

        // 네트워크 데이터 요청
        getNetworkData(); // 데이터 요청 추가

    }

    private void getNetworkData() {

        Retrofit retrofit = NetworkClient.getRetrofitClient(PostActivity.this);

        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME, Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        PostApi api = retrofit.create(PostApi.class);

        Call<Post> call = api.getPost(id,"Bearer " + token);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // 성공적으로 데이터를 가져온 경우
                    Post post = response.body();
                    updateUI(post); // UI 업데이트 메서드 호출
                } else {
                    // 오류 처리
                    Toast.makeText(PostActivity.this, "게시글을 불러오는 데 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }





            @Override
            public void onFailure(Call<Post> call, Throwable t) {

                Toast.makeText(PostActivity.this,"데이터를 불러오는데 실패했습니다. 네트워크 상태를 확인해주세요.", Toast.LENGTH_SHORT).show();
            }


        });


    }

    private void updateUI(Post post) {
        // UI 요소에 데이터 설정
        postTitle.setText(post.getTitle());
        description.setText(post.getDescription());
        postPrice.setText(String.valueOf(post.getPrice()));
        Glide.with(this).load(post.getProductImageUrl()).into(productImg);
    }
}