package com.example.carrotapp.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatAdapter {

    class UserModelViewHoler extends RecyclerView.ViewHolder{
        TextView nickname,location,time,lastChat;
        ImageView profilePic;

        public UserModelViewHoler(@NonNull View itemView) {
            super(itemView);
        }
    }





}
