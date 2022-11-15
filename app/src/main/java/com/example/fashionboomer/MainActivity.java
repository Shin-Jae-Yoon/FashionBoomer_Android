package com.example.fashionboomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;

import com.kakao.sdk.user.UserApiClient;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {
    private View logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv1 = findViewById(R.id.tx_id);
//        TextView tv2 = findViewById(R.id.tx_name);


        String userId = getIntent().getStringExtra("userId");
//        String userName = getIntent().getStringExtra("userName");

        tv1.setText(userId);
//        tv2.setText(userName);

        logoutButton = findViewById(R.id.logoutbtn);

        // 로그 아웃 버튼
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        MainActivity.this.finish();
                        Toast.makeText(MainActivity.this, "로그아웃에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        return null;
                    }
                });
            }
        });
    }
}