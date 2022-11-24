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

    private long memberId;

    private String memberName;

    private boolean likeStatus;

    public long getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public boolean getLikeStatus() {
        return likeStatus;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setLikeStatus(boolean likeStatus) {
        this.likeStatus = likeStatus;
    }
}
