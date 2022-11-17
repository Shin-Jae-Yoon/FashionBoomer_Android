package com.example.fashionboomer.bottom;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.fashionboomer.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

public class FragmentCategory extends Fragment {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    public FragmentCategory() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);

        // ListView 아이템에 표시될 사용자 테이터 정의
        String[] categoryMenu = {"NEW", "모자", "상의", "바지", "아우터", "원피스", "치마", "신발"};

        ListView listView = (ListView) view.findViewById(R.id.clothCategory);

        // 데이터 입력받을 Adapter 생성
        // fragment에서는 'this' 사용이 불가하므로, Activity의 참조 획득이 가능한 getActivity()함수 사용
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, categoryMenu){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view1 = super.getView(position, convertView, parent);
                TextView tv = (TextView) view1.findViewById(android.R.id.text1);
                tv.setTextColor(Color.parseColor("#0099CC"));
                tv.setTextSize(15);
                return view1;
            }
        };

        listView.setAdapter(listViewAdapter);


        return view;
    }
}
