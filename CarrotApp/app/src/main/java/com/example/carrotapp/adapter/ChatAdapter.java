package com.example.carrotapp.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrotapp.R;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.UserModelViewHoler>{

    class UserModelViewHoler extends RecyclerView.ViewHolder{
        TextView nickname,location,time,lastChat;
        ImageView profilePic;

        public UserModelViewHoler(@NonNull View itemView) {
            super(itemView);
            nickname = itemView.findViewById(R.id.chat_nickname);
            location = itemView.findViewById(R.id.chat_location);
            time = itemView.findViewById(R.id.chat_time);
            lastChat = itemView.findViewById(R.id.last_chat);
            profilePic = itemView.findViewById(R.id.profile_pic);
        }
    }

    @NonNull
    @Override
    public UserModelViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserModelViewHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }







}
