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

import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;


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


    private String getRelativeTime(String inputDateTime) {
        // Define the formatter based on the input format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Parse the input date-time string with seconds
        LocalDateTime inputTime = LocalDateTime.parse(inputDateTime, formatter);

        // Get the current date-time
        LocalDateTime now = LocalDateTime.now();

        // Calculate the duration between now and the input date-time
        Duration duration = Duration.between(inputTime, now);

        // Calculate the difference in various units
        long seconds = duration.getSeconds();
        long minutes = duration.toMinutes();
        long hours = duration.toHours();
        long days = duration.toDays();
        long months = ChronoUnit.MONTHS.between(inputTime.toLocalDate(), now.toLocalDate());
        long years = ChronoUnit.YEARS.between(inputTime.toLocalDate(), now.toLocalDate());

        // Format the result based on the duration
        if (seconds < 60) {
            return seconds + "초 전";
        } else if (minutes < 60) {
            return minutes + "분 전";
        } else if (hours < 24) {
            return hours + "시간 전";
        } else if (days < 30) {
            return days + "일 전";
        } else if (months < 12) {
            return months + "개월 전";
        } else {
            return years + "년 전";
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = itemList.get(position);

        holder.title.setText(post.getTitle());

        // NumberFormat을 사용하여 가격 포맷팅
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
        String formattedPrice = numberFormat.format(post.getPrice());
        holder.price.setText(formattedPrice + "원");

        holder.location.setText(post.getLocation());

        // createdAt을 사용하여 시간 정보 설정
        String createdAt = post.getCreatedAt();
        Log.d("PostAdapter", "Created At: " + createdAt);

        if (createdAt != null && createdAt.contains("T")) {
            // Convert date-time string from "yyyy-MM-ddTHH:mm:ss" to "yyyy-MM-dd HH:mm:ss"
            String formattedDate = createdAt.replace("T", " ");
            // Convert formattedDate to relative time
            String relativeTime = getRelativeTime(formattedDate);
            holder.time.setText(relativeTime);
        } else {
            holder.time.setText("시간 정보 없음");
        }

        // 로그 추가
        Log.d("PostAdapter", "Binding item at position " + position);
        Log.d("PostAdapter", "Image URL: " + post.getProductImageUrl());
        Log.d("PostAdapter", "Clicked Post ID: " + post.getId()); // 클릭한 ID 로그



        // 이미지 설정
        Glide.with(context)
                .load(post.getProductImageUrl())
                .placeholder(R.drawable.cuteboy) // 로딩 중에 표시할 이미지
                .error(R.drawable.miku) // 에러 발생 시 표시할 이미지
                .into(holder.thumbNail);

        holder.cardView.setOnClickListener(view -> {
            Log.d("PostAdapter", "Clicked Post ID: " + post.getId()); // 클릭한 ID 로그
            Intent intent = new Intent(context, PostActivity.class);
            intent.putExtra("post", post); // Post 객체 전달
//            intent.putExtra("id", post.getId()); // 원하는 데이터 전송
            context.startActivity(intent);
        });



    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }


}
