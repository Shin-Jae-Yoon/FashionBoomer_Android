package com.example.fashionboomer;

import static com.example.fashionboomer.BuildConfig.KAKAO_API_KEY;

import android.app.Application;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.model.AgeRange;
import com.kakao.sdk.user.model.Gender;

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 카카오 초기화
        KakaoSdk.init(this, KAKAO_API_KEY);
    }

    private long memberId;

    private String memberName;

    private Gender memberGender;

    private AgeRange memberAgerange;

    private String memberBirthday;

    public Gender getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(Gender memberGender) {
        this.memberGender = memberGender;
    }

    public AgeRange getMemberAgerange() {
        return memberAgerange;
    }

    public void setMemberAgerange(AgeRange memberAgerange) {
        this.memberAgerange = memberAgerange;
    }

    public String getMemberBirthday() {
        return memberBirthday;
    }

    public void setMemberBirthday(String memberBirthday) {
        this.memberBirthday = memberBirthday;
    }

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
