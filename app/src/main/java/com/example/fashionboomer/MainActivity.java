package com.example.fashionboomer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.example.fashionboomer.bottom.FragmentBoard;
import com.example.fashionboomer.bottom.FragmentCategory;
import com.example.fashionboomer.bottom.FragmentCloset;
import com.example.fashionboomer.bottom.FragmentHome;
import com.example.fashionboomer.bottom.FragmentMyPage;
import com.example.fashionboomer.adapter.ViewPagerAdapter;
import com.google.android.material.navigation.NavigationBarView;
import com.kakao.sdk.user.UserApiClient;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import me.relex.circleindicator.CircleIndicator3;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // 프래그먼트 ----------------------------------
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentCategory fragmentCategory = new FragmentCategory();
    private FragmentCloset fragmentCloset = new FragmentCloset();
    private FragmentHome fragmentHome = new FragmentHome();
    private FragmentBoard fragmentBoard = new FragmentBoard();
    private FragmentMyPage fragmentMyPage = new FragmentMyPage();
    //------------------------------------------------

    private CircularProgressButton logoutButton;
    FrameLayout home_frame;

    private Handler sliderHandler = new Handler();

    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 4;
    private CircleIndicator3 mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GlobalApplication globalApplication = (GlobalApplication)getApplicationContext();

        // textview에 이름 연결
        TextView idTextView = findViewById(R.id.tx_id);
        idTextView.setText(globalApplication.getMemberName() + "님");

        viewPager2 = findViewById(R.id.viewPager);
        pagerAdapter = new ViewPagerAdapter(this, num_page);
        viewPager2.setAdapter(pagerAdapter);

        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(viewPager2);
        mIndicator.createIndicators(num_page, 0);

        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setCurrentItem(1000);
        viewPager2.setOffscreenPageLimit(3);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffsetPixels == 0) {
                    viewPager2.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position%num_page);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 2000);
            }
        });


        final float pageMargin= getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        final float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

        viewPager2.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float myOffset = position * -(2 * pageOffset + pageMargin);
                if (viewPager2.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(viewPager2) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.setTranslationX(-myOffset);
                    } else {
                        page.setTranslationX(myOffset);
                    }
                } else {
                    page.setTranslationY(myOffset);
                }
            }
        });


        // 로그 아웃 버튼
        logoutButton = findViewById(R.id.logoutbtn);
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
                }, 2000);// 1초 정도 딜레이를 준 후 시작
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame_layout, fragmentHome).commit();
        BottomNavigationView bottom_menu = findViewById(R.id.menu_bottom_navigation);
        bottom_menu.setSelectedItemId(R.id.home);
        bottom_menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.category:
                        onPause();
                        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame_layout, fragmentCategory).commit();
                        return true;
                    case R.id.closet:
                        onPause();
                        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame_layout, fragmentCloset).commit();
                        return true;
                    case R.id.home:
                        onResume();
                        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame_layout, fragmentHome).commit();
                        return true;
                    case R.id.board:
                        onPause();
                        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame_layout, fragmentBoard).commit();
                        return true;
                    case R.id.mypage:
                        onPause();
                        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame_layout, fragmentMyPage).commit();
                        return true;
                }
                return false;
            }
        });
    }
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 2000);
    }

    public void pauseForHome() {
        onPause();
    }
}