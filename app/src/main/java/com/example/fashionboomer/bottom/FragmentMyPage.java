package com.example.fashionboomer.bottom;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dd.CircularProgressButton;
import com.example.fashionboomer.GlobalApplication;
import com.example.fashionboomer.LoginActivity;
import com.example.fashionboomer.MainActivity;
import com.example.fashionboomer.R;
import com.kakao.sdk.user.UserApiClient;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class FragmentMyPage extends Fragment {
    private MainActivity mainActivity;
    private Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
        mContext = context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        TextView userName = view.findViewById(R.id.userName);
        TextView userGender = view.findViewById(R.id.userGender);
        TextView userAgerange = view.findViewById(R.id.userAgerange);
        TextView userBirthday = view.findViewById(R.id.userBirthday);

        GlobalApplication globalApplication = (GlobalApplication)getActivity().getApplicationContext();

        userName.setText("회원이름 : " + globalApplication.getMemberName());

        if (globalApplication.getMemberGender().toString().equals("MALE")) {
            userGender.setText("회원성별 : 남성");
            userGender.setTextColor(Color.parseColor("blue"));
        }

        if (globalApplication.getMemberAgerange().toString().equals("AGE_20_29")) {
            userAgerange.setText("연령대 : 20세 ~ 29세 사이");
        }

        if (globalApplication.getMemberBirthday().toString().equals("0918")) {
            userBirthday.setText("생년월일 : 1996년 09월 18일");
        }

        CircularProgressButton logoutButton = view.findViewById(R.id.logoutbtn);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutButton.setIndeterminateProgressMode(true);
                logoutButton.setProgress(50);

                Handler btnhandler = new Handler();
                btnhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        logoutButton.setProgress(100);
                    }
                }, 1000); // 1초 이후 실행


                Handler logoutHandler = new Handler();
                logoutHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // logout을 unlink로 하면 연결 끊는거라 회원탈퇴같은 개념
                        UserApiClient.getInstance().unlink(new Function1<Throwable, Unit>() {
                            @Override
                            public Unit invoke(Throwable throwable) {
                                Intent intent = new Intent(mContext, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(mContext, "회원 탈퇴가 성공적으로 되었습니다.", Toast.LENGTH_SHORT).show();
                                return null;
                            }
                        });
                    }
                }, 2000);// 1초 정도 딜레이를 준 후 시작
            }
        });

        return view;
    }
}