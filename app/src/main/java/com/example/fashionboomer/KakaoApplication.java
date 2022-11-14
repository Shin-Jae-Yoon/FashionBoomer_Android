package com.example.fashionboomer;

import static com.example.fashionboomer.BuildConfig.KAKAO_API_KEY;

import android.app.Application;
import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this, KAKAO_API_KEY);
    }

}
