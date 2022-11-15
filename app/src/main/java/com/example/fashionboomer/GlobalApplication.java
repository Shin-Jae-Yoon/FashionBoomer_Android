package com.example.fashionboomer;

import static com.example.fashionboomer.BuildConfig.KAKAO_API_KEY;

import android.app.Application;
import com.kakao.sdk.common.KakaoSdk;

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 카카오 초기화
        KakaoSdk.init(this, KAKAO_API_KEY);
    }

}
