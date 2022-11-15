package com.example.fashionboomer.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fashionboomer.LoginActivity;
import com.example.fashionboomer.MainActivity;
import com.example.fashionboomer.dto.DataModel;
import com.example.fashionboomer.dto.RetrofitClient;
import com.example.fashionboomer.dto.RetrofitInterface;
import com.google.gson.Gson;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KakaoLogin extends Activity {
    private RetrofitClient retrofitClient;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 카카오가 설치되어 있는지 확인 하는 메서드또한 카카오에서 제공 콜백 객체를 이용함
        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                // 이때 토큰이 전달이 되면 로그인이 성공한 것이고 토큰이 전달되지 않았다면 로그인 실패
                // 토큰이 전달되면 카카오 ui 뜨게
                if (oAuthToken != null) {
                    updateKakaoLoginUi();
                }
                if (throwable != null) {
                    // 약관 동의나 가입에 관련하여 취소하였을 때
                    Toast.makeText(KakaoLogin.this, "작업이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(KakaoLogin.this, LoginActivity.class);
                    startActivity(intent);
                    KakaoLogin.this.finish();
                }
                return null;
            }
        };

        // 휴대폰에 카카오톡이 깔려있는 상태이면 카카오톡으로 이용
        if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(KakaoLogin.this)) {
            UserApiClient.getInstance().loginWithKakaoTalk(KakaoLogin.this, callback);
        }
        // 휴대폰에 카카오톡 없으면 웹으로 이용
        else {
            UserApiClient.getInstance().loginWithKakaoAccount(KakaoLogin.this, callback);
        }
        // 카카오 관련 ui 열기
        updateKakaoLoginUi();
    }

    private void updateKakaoLoginUi() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                // 로그인이 되어있으면
                if (user != null) {
                    String userName = user.getKakaoAccount().getProfile().getNickname();
                    String userEmail = user.getKakaoAccount().getEmail();
                    String userPlatform = "kakao";

                    // 레트로핏 생성
                    retrofitClient = RetrofitClient.getInstance();
                    retrofitInterface = RetrofitClient.getRetrofitInterface();

                    DataModel.Member member = new DataModel.Member(userEmail, userName, userPlatform);

                    retrofitInterface.getResponseMember(member).enqueue(new Callback<DataModel.ResponseMember>() {
                        @Override
                        public void onResponse(Call<DataModel.ResponseMember> call, Response<DataModel.ResponseMember> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                DataModel.ResponseMember responseMember = response.body();
                                Gson gson = new Gson();
                                System.out.println(gson.toJson(responseMember).toString());
                                System.out.println();
                                Toast.makeText(KakaoLogin.this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(KakaoLogin.this, MainActivity.class);
                                startActivity(intent);
                                KakaoLogin.this.finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<DataModel.ResponseMember> call, Throwable t) {
                            Toast.makeText(KakaoLogin.this, "작업이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });
                }
                return null;
            }
        });
    }
}
