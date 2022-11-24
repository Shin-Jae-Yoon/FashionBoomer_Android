package com.example.fashionboomer.bottom;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fashionboomer.R;
import com.example.fashionboomer.adapter.HomeAdapter;
import com.example.fashionboomer.adapter.MenuAdapter;
import com.example.fashionboomer.dto.CategoryBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentCategory extends Fragment {
    private Activity mActivity;
    private Context mContext;
    @BindView(R.id.lv_menu) ListView lv_menu;
    @BindView(R.id.lv_home) ListView lv_home;
    private List<String> menuList;
    private List<CategoryBean.DataBean> homeList;
    private List<Integer> showTitle;
    private MenuAdapter menuAdapter;
    private HomeAdapter homeAdapter;
    private int currentItem;
    private String receiveHomeFrag;

    public FragmentCategory() {

    }

    // Initialise it from onAttach()
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        menuList = new ArrayList<>();
        homeList = new ArrayList<>();
        menuAdapter = new MenuAdapter(mContext, menuList);

        lv_menu.setAdapter(menuAdapter);

        homeAdapter = new HomeAdapter(mContext, homeList);
        lv_home.setAdapter(homeAdapter);


        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuAdapter.setSelectItem(position);
                menuAdapter.notifyDataSetInvalidated();
                lv_home.setSelection(showTitle.get(position));
            }
        });

        lv_home.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int scrollState;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.scrollState = scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    return;
                }
                int current = showTitle.indexOf(firstVisibleItem);
                if (currentItem != current && current >= 0) {
                    currentItem = current;
                    menuAdapter.setSelectItem(currentItem);
                    menuAdapter.notifyDataSetInvalidated();
                }
            }
        });

        loadData();

        if (getArguments() != null) {
            receiveHomeFrag = getArguments().getString("homeFrag");
            if (receiveHomeFrag.equals("top")) {
                changeMenu(0);
            }
            else if (receiveHomeFrag.equals("outer")) {
                changeMenu(1);
            }
            else if (receiveHomeFrag.equals("pants")) {
                changeMenu(2);
            }
            else if (receiveHomeFrag.equals("onepiece")) {
                changeMenu(3);
            }
            else if (receiveHomeFrag.equals("skirt")) {
                changeMenu(4);
            }
            else if (receiveHomeFrag.equals("shoes")) {
                changeMenu(5);
            }
            else if (receiveHomeFrag.equals("hat")) {
                changeMenu(6);
            }
        }
        return view;
    }

    private void changeMenu(int position) {
        menuAdapter.setSelectItem(position);
        menuAdapter.notifyDataSetInvalidated();
        lv_home.setSelection(showTitle.get(position));
    }

    private void loadData() {

        String json = getJson(mContext, "category.json");
        CategoryBean categoryBean = new Gson().fromJson(json, CategoryBean.class);
        showTitle = new ArrayList<>();
        for (int i = 0; i < categoryBean.getData().size(); i++) {
            CategoryBean.DataBean dataBean = categoryBean.getData().get(i);
            menuList.add(dataBean.getModuleTitle());
            showTitle.add(i);
            homeList.add(dataBean);
        }

        menuAdapter.notifyDataSetChanged();
        homeAdapter.notifyDataSetChanged();
    }
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = context.getAssets();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
