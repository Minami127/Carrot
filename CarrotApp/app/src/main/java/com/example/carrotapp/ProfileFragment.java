package com.example.carrotapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.carrotapp.api.NetworkClient;
import com.example.carrotapp.api.UserApi;
import com.example.carrotapp.config.Config;
import com.example.carrotapp.model.Res;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ProfileFragment extends Fragment {

    private ImageButton imgBtn;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        imgBtn = view.findViewById(R.id.miku);

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        return view;
    }

    // 로그아웃 기능
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // 이 다이얼로그의 외곽부분을 눌렀을 때, 사라지지 않도록 하는 코드.
        builder.setCancelable(false);
        builder.setTitle("로그아웃");
        builder.setMessage("정말 로그아웃 하시겠습니까?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                onDestroyLogout();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                // 아무 작업도 하지 않음
            }
        });
        builder.show(); // 다이얼로그 출력
    }

    private void onDestroyLogout() {
        SharedPreferences sp = getContext().getSharedPreferences(Config.PREFERENCE_NAME, Context.MODE_PRIVATE);

        String token = sp.getString("token", "");
        token = "Bearer " + token;

        Retrofit retrofit = NetworkClient.getRetrofitClient(getContext());
        UserApi api = retrofit.create(UserApi.class);
        Call<Res> call = api.LogOut(token);
        call.enqueue(new Callback<Res>() {
            @Override
            public void onResponse(Call<Res> call, Response<Res> response) {
                if (response.isSuccessful()) {
                    // 쉐어드프리퍼런스의 token을 없애야 한다.
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("token", "");
                    editor.putInt("type", 0);
                    editor.apply();

                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    // 실패 처리
                }
            }

            @Override
            public void onFailure(Call<Res> call, Throwable t) {
                Log.i("AAA", "통신 오류");
            }
        });
    }
}