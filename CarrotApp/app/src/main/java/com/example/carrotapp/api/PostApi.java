package com.example.carrotapp.api;

import com.example.carrotapp.model.Post;
import com.example.carrotapp.model.PostList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostApi {

    // 게시글 카드뷰 조회 API
    @GET("/post/list")
    Call<PostList> getPost(@Header("Authorization") String token,
                                    @Query("offset") int offset,
                                    @Query("limit") int limit);

    // 게시글 상세조회 API
    @GET("/post/detail/{id}")
    Call<Post> getPostDetail(@Path("id") int id, @Header("Authorization") String token);

}
