package com.example.carrotapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.carrotapp.PostActivity;
import com.example.carrotapp.R;
import com.example.carrotapp.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{
    private List<Post> itemList;
    private Context context;

    public PostAdapter(Context context,List<Post> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView thumbNail;
        public TextView title;
        public TextView price;
        public TextView time;
        public TextView location;

        public ViewHolder(View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.postItem);
            thumbNail = itemView.findViewById(R.id.thumbNail);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            time = itemView.findViewById(R.id.time);
            location = itemView.findViewById(R.id.location);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = itemList.get(position);

        holder.title.setText(post.getTitle());
        holder.price.setText(String.valueOf(post.getPrice()));

        holder.location.setText(post.getLocation());
        // 이미지 설정
        Glide.with(context)
                .load(post.getProductImageUrl())
                .into(holder.thumbNail);

        holder.cardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, PostActivity.class);
            intent.putExtra("postContent", post.getId()); // 원하는 데이터 전송
            context.startActivity(intent);
        });





    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}
