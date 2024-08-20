package com.example.carrotapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.carrotapp.model.PostDetail;
import com.google.android.material.snackbar.Snackbar;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PostActivity extends AppCompatActivity {

    ImageButton imgBtn;
    TextView postPrice, postTitle, priceTag, description;
    ImageView productImg;
    Button btn;
    int id;
    PostDetail postDetail;

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
        btn = findViewById(R.id.chat_button);


        Post post = (Post) getIntent().getSerializableExtra("post");
        id = post.getId();




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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostActivity.this,ChatRoomActivity.class);
                intent.putExtra("post", post);
                startActivity(intent);
                finish();
            }
        });

        getNetworkData();

    }

    private void getNetworkData() {

        Retrofit retrofit = NetworkClient.getRetrofitClient(PostActivity.this);

        PostApi api = retrofit.create(PostApi.class);

        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME,MODE_PRIVATE);
        String token = sp.getString("token", "");

        Call<PostDetail> call = api.getPostDetail(id,"Bearer " + token);

        call.enqueue(new Callback<PostDetail>() {
            @Override
            public void onResponse(Call<PostDetail> call, Response<PostDetail> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PostDetail postDetail = response.body();

                    // items 배열의 첫 번째 아이템을 가져옴
                    if (postDetail.items != null && !postDetail.items.isEmpty()) {
                        PostDetail.Item firstItem = postDetail.items.get(0);
                        int price = firstItem.getPrice();
                        NumberFormat formatter = NumberFormat.getInstance(Locale.KOREA);
                        String formattedPrice = formatter.format(price) + "원";
                        postTitle.setText(firstItem.getTitle());
                        postPrice.setText(formattedPrice);
                        priceTag.setText(formattedPrice);
                        description.setText(firstItem.getDescription());
                        // 이미지 설정
                        // 이미지 설정
                        String imageUrl = firstItem.getProductImageUrl(); // 이미지 URL 가져오기
                        Glide.with(PostActivity.this)
                                .load(imageUrl)
                                .placeholder(R.drawable.cuteboy) // 로딩 중 표시할 이미지
                                .error(R.drawable.miku) // 에러 발생 시 표시할 이미지
                                .into(productImg); // productImg는 ImageView의 ID
                        productImg.setScaleType(ImageView.ScaleType.CENTER_CROP);


                    } else {
                        Log.d("PostActivity", "items가 비어 있습니다.");
                    }
                } else {
                    Log.e("PostActivity", "응답 실패: " + response.code() + " - " + response.message());
                    Toast.makeText(PostActivity.this, "응답 실패: " + response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PostDetail> call, Throwable t) {
                Log.e("PostActivity", "API 호출 실패: " + t.getMessage());
                Toast.makeText(PostActivity.this, "실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}