package com.example.carrotapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.example.carrotapp.model.PostList;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChatRoomActivity extends AppCompatActivity {

    ImageButton backBtn,sendBtn;
    TextView txtView;
    EditText chatSend;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        backBtn = findViewById(R.id.chat_back_btn);
        txtView = findViewById(R.id.chat_user);
        chatSend = findViewById(R.id.chat_send);
        sendBtn = findViewById(R.id.message_send_btn);

        Post post = (Post) getIntent().getSerializableExtra("post");
        id = post.getId();




        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatRoomActivity.this, PostActivity.class);
                intent.putExtra("post", post);
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
        Log.i("ChatRoomActivity", "API 호출 시작: id=" + id + ", token=" + token);

        call.enqueue(new Callback<PostDetail>() {
            @Override
            public void onResponse(Call<PostDetail> call, Response<PostDetail> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i("ChatRoomActivity", "응답 수신: " + response.code());
                    PostDetail postDetail = response.body();
                    // items 배열의 첫 번째 아이템을 가져옴
                    if (postDetail.items != null && !postDetail.items.isEmpty()) {
                        PostDetail.Item firstItem = postDetail.items.get(0);
                        txtView.setText(firstItem.getNickname());
                        Log.i("ChatRoomActivity", "첫 번째 아이템 닉네임: " + firstItem.getNickname()); // 닉네임 로그
                    } else {
                        Log.i("CCCCC", "items가 비어 있습니다.");
                    }

                }

            }

            @Override
            public void onFailure(Call<PostDetail> call, Throwable t) {
                Log.e("CCCC", "API 호출 실패: " + t.getMessage());
                Toast.makeText(ChatRoomActivity.this, "실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }

}