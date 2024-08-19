package com.example.carrotapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.carrotapp.api.NetworkClient;
import com.example.carrotapp.api.PostApi;
import com.example.carrotapp.config.Config;
import com.example.carrotapp.model.PostDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChatRoomActivity extends AppCompatActivity {

    ImageButton backBtn;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        backBtn = findViewById(R.id.chat_back_btn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatRoomActivity.this, PostActivity.class);
                startActivity(intent);
                finish();
            }
        });

        getNetworkData();
    }


    private void getNetworkData() {

        Retrofit retrofit = NetworkClient.getRetrofitClient(ChatRoomActivity.this);
        PostApi api = retrofit.create(PostApi.class);
        SharedPreferences sp = getSharedPreferences(Config.PREFERENCE_NAME,MODE_PRIVATE);
        String token = sp.getString("token", "");

        Call<PostDetail> call = api.getPostDetail(id,"Bearer " + token);

        call.enqueue(new Callback<PostDetail>() {
            @Override
            public void onResponse(Call<PostDetail> call, Response<PostDetail> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PostDetail postDetail = response.body();

                    if(postDetail.items != null && !postDetail.items.isEmpty()){


                    } else {
                        Log.d("ChatRoomActivity", "items가 비어 있습니다.");
                    }
                }else {
                    Log.e("ChatRoomActivity", "응답 실패: " + response.code() + " - " + response.message());
                    Toast.makeText(ChatRoomActivity.this, "응답 실패: " + response.code(), Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<PostDetail> call, Throwable t) {
                Log.e("ChatRoomActivity", "API 호출 실패: " + t.getMessage());
                Toast.makeText(ChatRoomActivity.this, "실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}