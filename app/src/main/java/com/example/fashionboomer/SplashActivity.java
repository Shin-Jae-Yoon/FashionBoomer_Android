package com.example.fashionboomer;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class SplashActivity extends AppCompatActivity {

    public static final int REQUEST_PERMISSION = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (checkPermission() == true) {
            Handler hd = new Handler();
            hd.postDelayed(new SplashHandler(), 2000);

            ImageView iv = findViewById(R.id.splash_item);
            CircleImageView cv = findViewById(R.id.splash_bg);
            TextView tv = findViewById(R.id.splash_logo);

            Animation anim_icon = AnimationUtils.loadAnimation(this, R.anim.anim_splash_imageview);
            Animation anim_logo = AnimationUtils.loadAnimation(this, R.anim.anim_splash_textview);

            cv.setAnimation(anim_icon);
            iv.setAnimation(anim_icon);
            tv.setAnimation(anim_logo);
        } else if (checkPermission() == false) {
            Toast.makeText(this, "권한이 없습니다. 앱을 다시 켜주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    private class SplashHandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), LoginActivity.class));
            finish();
        }
    }

    //권한 확인
    public boolean checkPermission() {
        int permissionWrite = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        System.out.println(permissionWrite);

        //권한이 없으면 권한 요청
        if (permissionWrite != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "이 앱을 실행하기 위해 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }

            ActivityCompat.requestPermissions(this, new String[]{
                   Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION: {
                // 권한이 취소되면 result 배열은 비어있다.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "권한 확인", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();
                    finish(); //권한이 없으면 앱 종료
                }
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        checkPermission(); //권한체크
    }

}