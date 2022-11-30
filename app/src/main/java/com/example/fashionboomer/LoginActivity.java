package com.example.fashionboomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fashionboomer.dto.RetrofitClient;
import com.example.fashionboomer.dto.RetrofitInterface;
import com.example.fashionboomer.login.KakaoLogin;


public class LoginActivity extends AppCompatActivity {
    private RetrofitClient retrofitClient;
    private RetrofitInterface retrofitInterface;

    private static final String TAG = "LoginActivity";
    private View kakaoLoginButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        kakaoLoginButton = findViewById(R.id.iv_kakao);

        kakaoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, KakaoLogin.class);
                startActivity(intent);
            }
        });
    }

}