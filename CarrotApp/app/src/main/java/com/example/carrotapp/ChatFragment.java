package com.example.carrotapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ChatFragment extends Fragment {

    RecyclerView recyclerView;



    public ChatFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        recyclerView = view.findViewById(R.id.chat_recyclerView);

        // 툴바 제목 설정
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setToolbarTitle("채팅");
        }

        return view;
    }
}