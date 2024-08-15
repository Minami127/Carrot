package com.example.carrotapp.model;

import java.io.Serializable;

public class User implements Serializable {


    public String nickname;
    public String email;
    public String location;
    public String password;
    public int id;


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

}