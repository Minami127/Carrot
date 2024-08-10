package com.example.carrotapp.model;

import java.io.Serializable;

public class User implements Serializable {

    public String nickname;
    public String email;
    public String location;
    public String password;
    // public String profileImg;
    // 구글 로그인 구분 위한 변수
    // public int type;

    public User(String nickname, String email, String location, String password) {
        this.nickname = nickname;
        this.email = email;
        this.location = location;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

//    // 구글 로그인을 이용한 회원가입을 위한 생성자
//    public User(String name, String email, int type) {
//        this.name = name;
//        this.email = email;
//        this.type = type;
//    }
}