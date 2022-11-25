package com.example.fashionboomer.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fashionboomer.ClothActivity;
import com.example.fashionboomer.R;
import com.example.fashionboomer.dto.CategoryBean;
import com.example.fashionboomer.dto.DataModel;

import java.util.List;

public class ClosetItemAdapter extends BaseAdapter {

    private Context context;
    private List<DataModel.Closet> foodDatas;
    private static final String BASE_URL = "http://fashionboomer.tk:8080";
    private Long memberId;


    public ClosetItemAdapter(Context context, List<DataModel.Closet> foodDatas) {
        this.context = context;
        this.foodDatas = foodDatas;
    }


    @Override
    public int getCount() {
        if (foodDatas != null) {
            return foodDatas.size();
        } else {
            return 10;
        }
    }

    @Override
    public Object getItem(int position) {
        return foodDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataModel.Closet subcategory = foodDatas.get(position);
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_closet, null);
            viewHold = new ViewHold();
            viewHold.iv_icon =  convertView.findViewById(R.id.item_closet);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }

        String url = BASE_URL + "/v11/closets/images/" + subcategory.getId();
        Uri uri = Uri.parse(url);
        Glide.with(context)
                .load(uri)
                .into(viewHold.iv_icon);
        viewHold.iv_icon.setBackgroundResource(R.drawable.square_line_theme);
        return convertView;
    }

    private static class ViewHold {
        private ImageView iv_icon;
    }

}
