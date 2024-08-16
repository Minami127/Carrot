package com.example.carrotapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carrotapp.adapter.PostAdapter;
import com.example.carrotapp.api.NetworkClient;
import com.example.carrotapp.api.PostApi;
import com.example.carrotapp.config.Config;
import com.example.carrotapp.model.Post;
import com.example.carrotapp.model.PostList;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Post> postingArrayList = new ArrayList<>();
    PostAdapter adapter;
    int offset = 0;
    int limit = 25;
    int count = 0;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//      Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new PostAdapter(getContext(), postingArrayList);
        recyclerView.setAdapter(adapter);

        getNetworkData();

        return view;

    }

    private void getNetworkData() {

        Retrofit retrofit = NetworkClient.getRetrofitClient(getActivity());

        SharedPreferences sp = getActivity().getSharedPreferences(Config.PREFERENCE_NAME, Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        PostApi api = retrofit.create(PostApi.class);

        Call<PostList> call = api.getPost("Bearer " + token, offset, limit);

        call.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {


                if (response.isSuccessful()) {
                    PostList postingList = response.body();

                    if (postingList != null) {
                        postingArrayList.clear(); // 기존 리스트를 비움
                        postingArrayList.addAll(postingList.items); // 새로운 아이템 추가
                        count = postingList.count;

                        // ID로 내림차순 정렬
                        Collections.sort(postingArrayList, new Comparator<Post>() {
                            @Override
                            public int compare(Post post1, Post post2) {
                                return Integer.compare(post2.getId(), post1.getId()); // ID 기준 내림차순
                            }
                        });

                        // 어댑터에 데이터 변경 알림
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(getActivity(), "데이터를 불러오는데 실패했습니다. 네트워크 상태를 확인해주세요.", Toast.LENGTH_SHORT).show();
            }

        });

    }
}