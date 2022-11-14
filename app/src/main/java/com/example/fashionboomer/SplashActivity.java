package com.example.fashionboomer;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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
    }

    private class SplashHandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), LoginActivity.class));
            finish();
        }
    }

}