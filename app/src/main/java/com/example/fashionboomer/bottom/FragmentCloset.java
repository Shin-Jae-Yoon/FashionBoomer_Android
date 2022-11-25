package com.example.fashionboomer.bottom;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fashionboomer.GlobalApplication;
import com.example.fashionboomer.MainActivity;
import com.example.fashionboomer.R;
import com.example.fashionboomer.adapter.ClosetAdapter;
import com.example.fashionboomer.adapter.ClothAdapter;
import com.example.fashionboomer.dto.DataModel;
import com.example.fashionboomer.dto.RetrofitClient;
import com.example.fashionboomer.dto.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCloset extends Fragment {

    private Activity mActivity;
    private Context mContext;
//    @BindView(R.id.gridCloset) ListView gridCloset;

    private DataModel.PageData pageData;
    private List<DataModel.Closet> closetList;
    private ClosetAdapter closetAdapter;

    private RetrofitClient retrofitClient;
    private RetrofitInterface retrofitInterface;

    private Long memberId;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }
        mContext = context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_closet, container, false);
        ButterKnife.bind(this, view);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_cloth);

        GlobalApplication globalApplication = (GlobalApplication)getActivity().getApplicationContext();
        memberId = globalApplication.getMemberId();
        closetList = new ArrayList<>();

        retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = RetrofitClient.getRetrofitInterface();

        retrofitInterface.getMemberClosets(memberId.intValue(), 1, 30).enqueue(new Callback<DataModel.PageData>() {
            @Override
            public void onResponse(Call<DataModel.PageData> call, Response<DataModel.PageData> response) {
                if (response.isSuccessful()) {
                    pageData = new DataModel.PageData(response.body());
                    closetList = pageData.getData();
//                    gridCloset.setAdapter(closetAdapter);

                    closetAdapter = new ClosetAdapter(closetList);
                    recyclerView.setAdapter(closetAdapter);
                    GridLayoutManager lm = new GridLayoutManager(mContext,2);
                    recyclerView.setLayoutManager(lm);
                }
            }

            @Override
            public void onFailure(Call<DataModel.PageData> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return view;
    }
}