package com.example.fashionboomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fashionboomer.dto.CategoryBean;
import com.example.fashionboomer.dto.DataModel;
import com.example.fashionboomer.dto.RetrofitClient;
import com.example.fashionboomer.dto.RetrofitInterface;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity {
    private InfoActivity infoActivity = this;
    private static final String BASE_URL = "http://fashionboomer.tk:8080";
    private LinearLayout back;
    private boolean status;
    private Long memberId;
    private DataModel.PageData pageData;
    private List<DataModel.Closet> closet;
    private DataModel.Closet postCloset;
    private DataModel.ResponseCloset responseCloset;
    private int closetId;
    private Long closetUserId;
    private int closetClothId;

    private RetrofitClient retrofitClient;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();

        int clickPosition = intent.getExtras().getInt("position");
        int infoId = intent.getExtras().getInt("id");
        String infoCategory = intent.getExtras().getString("category");
        String infoDetails = intent.getExtras().getString("details");
        String infoBrand = intent.getExtras().getString("brand");
        String infoName = intent.getExtras().getString("name");
        String infoPrice = intent.getExtras().getString("price");
        String infoGender = intent.getExtras().getString("gender");
        String infoLink = intent.getExtras().getString("link");

        ImageView cloth_iv = findViewById(R.id.cloth_iv);
        TextView cloth_categoryDetails = findViewById(R.id.cloth_categoryDetails);
        TextView cloth_brand = findViewById(R.id.cloth_brand);
        TextView cloth_name = findViewById(R.id.cloth_name);
        TextView cloth_price = findViewById(R.id.cloth_price);
        TextView cloth_gender = findViewById(R.id.cloth_gender);
        Button cloth_link_btn = findViewById(R.id.cloth_link);
        Button cloth_insta_btn = findViewById(R.id.cloth_insta);

        Glide.with(infoActivity).load(BASE_URL + "/v11/clothes/images/" + infoCategory + "/" + infoDetails + "/" + String.valueOf(clickPosition+1)).into(cloth_iv);
        cloth_categoryDetails.setText(infoCategory + " > " + infoDetails);
        cloth_brand.setText("brand - " + infoBrand);
        cloth_name.setText(infoName);
        cloth_price.setText(infoPrice);

        cloth_link_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(infoLink));
                startActivity(linkIntent);
            }
        });

        cloth_insta_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=" + infoBrand;
                Intent linkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(linkIntent);
            }
        });

        if(infoGender.length() == 6) {
            if(infoGender.equals("['남성']")) {
                cloth_gender.setText("M");
                cloth_gender.setTextColor(Color.parseColor("blue"));
            } else {
                cloth_gender.setText("W");
                cloth_gender.setTextColor(Color.parseColor("red"));
            }
        } else if(infoGender.length() == 12) {
            cloth_gender.setText("M W");
            cloth_gender.setTextColor(Color.parseColor("black"));
        }

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 초기화면 하트 표시
        ImageView likeImage = findViewById(R.id.likeImage);

        GlobalApplication globalApplication = (GlobalApplication)getApplicationContext();
        memberId = globalApplication.getMemberId();

        retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = RetrofitClient.getRetrofitInterface();

        globalApplication.setLikeStatus(false);
        retrofitInterface.getMemberClosets(memberId.intValue(), 1, 30).enqueue(new Callback<DataModel.PageData>() {
            @Override
            public void onResponse(Call<DataModel.PageData> call, Response<DataModel.PageData> response) {
                if (response.isSuccessful()) {
                    pageData = new DataModel.PageData(response.body());
                    closet = pageData.getData();

                    if (closet.size() == 0) {
                        likeImage.setBackgroundResource(R.drawable.heart_none);
                        globalApplication.setLikeStatus(false);
                    }

                    for (int i = 0; i < closet.size(); i++) {
                        if (closet.get(i).getCloth_id() == infoId) {
                            likeImage.setBackgroundResource(R.drawable.heart_color);
                            globalApplication.setLikeStatus(true);
                            break;
                        } else {
                            likeImage.setBackgroundResource(R.drawable.heart_none);
                            globalApplication.setLikeStatus(false);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DataModel.PageData> call, Throwable t) {
                t.printStackTrace();
            }
        });

        likeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 좋아요 없는 것은 누르면 옷장에 post 요청과 하트 색칠
                if (globalApplication.getLikeStatus() == false) {
                    postCloset = new DataModel.Closet(memberId, infoId);
                    retrofitInterface.getResponseCloset(postCloset).enqueue(new Callback<DataModel.ResponseCloset>() {
                        @Override
                        public void onResponse(Call<DataModel.ResponseCloset> call, Response<DataModel.ResponseCloset> response) {
                            if (response.isSuccessful()) {
                                responseCloset = response.body();
                                likeImage.setBackgroundResource(R.drawable.heart_color);
                                globalApplication.setLikeStatus(true);
                            }
                        }

                        @Override
                        public void onFailure(Call<DataModel.ResponseCloset> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }

                // 좋아요 된 것은 다시 누르면 옷장에 delete 요청과 하트 검정으로
                else {
                    for (int i = 0; i < closet.size(); i++) {
                        if (closet.get(i).getCloth_id() == infoId) {
                            retrofitInterface.deleteCloset(closet.get(i).getId()).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (response.isSuccessful()) {
                                        likeImage.setBackgroundResource(R.drawable.heart_none);
                                        globalApplication.setLikeStatus(false);
                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });
                            break;
                        }
                    }
                }
            }
        });
    }
}