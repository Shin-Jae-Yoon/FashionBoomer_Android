package com.example.fashionboomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.fashionboomer.adapter.ClothAdapter;
import com.example.fashionboomer.dto.ClothBean;
import com.example.fashionboomer.dto.RetrofitClient;
import com.example.fashionboomer.dto.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClothActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://fashionboomer.tk:8080";
    private ClothBean.ResponseCloth responseCloth;
    private ClothBean.Cloth cloth;

    private Context context;
    private RetrofitClient retrofitClient;
    private RetrofitInterface retrofitInterface;

    private final ClothActivity clothActivity = this;

    private ClothAdapter clothAdapter;
    private List<ClothBean.Cloth> cList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_cloth);

        // 인텐트 전달받은 title, category, details
        Intent intent = getIntent();
        String title = intent.getExtras().getString("title");
        String category = intent.getExtras().getString("category");
        String details = intent.getExtras().getString("details");

        // 레트로핏 생성
        retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = RetrofitClient.getRetrofitInterface();

        retrofitInterface.getClothDetails(category, details, 1, 90).enqueue(new Callback<ClothBean.ResponseCloth>() {
            @Override
            public void onResponse(Call<ClothBean.ResponseCloth> call, Response<ClothBean.ResponseCloth> response) {
                if(response.isSuccessful() && response.body() != null) {
                    responseCloth = new ClothBean.ResponseCloth(response.body());
                    cList = responseCloth.getData();

                    clothAdapter = new ClothAdapter(cList);
                    recyclerView.setAdapter(clothAdapter);
                    GridLayoutManager lm = new GridLayoutManager(context,2);
                    recyclerView.setLayoutManager(lm);
                }
            }

            @Override
            public void onFailure(Call<ClothBean.ResponseCloth> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}