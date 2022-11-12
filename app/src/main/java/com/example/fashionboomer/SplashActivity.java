package com.example.fashionboomer;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new SplashHandler(), 2000);

        ImageView iv = findViewById(R.id.ic_bp);
        CircleImageView cv = findViewById(R.id.bg_bp);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_splash_imageview);
        cv.setAnimation(anim);
        iv.setAnimation(anim);
    }

    private class SplashHandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), LoginActivity.class));
        }
    }

}