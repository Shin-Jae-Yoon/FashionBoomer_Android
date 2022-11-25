//package com.example.fashionboomer.adapter;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//
//import com.example.fashionboomer.R;
//import com.example.fashionboomer.dto.CategoryBean;
//import com.example.fashionboomer.dto.DataModel;
//import com.example.fashionboomer.widget.GridScrollCloset;
//import com.example.fashionboomer.widget.GridViewForScrollView;
//
//import java.util.List;
//
//public class ClosetAdapter extends BaseAdapter {
//
//    private Context context;
//    private List<DataModel.Closet> closetList;
//    private DataModel.PageData pageData;
//
//    public ClosetAdapter(Context context, List<DataModel.Closet> closetList) {
//        this.context = context;
//        this.closetList = closetList;
//    }
//
//    @Override
//    public int getCount() {
//        if (closetList != null) {
//            return closetList.size();
//        }
//        else {
//            return 10;
//        }
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return closetList.size();
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ViewHold viewHold = null;
//        if (convertView == null) {
//            convertView = View.inflate(context, R.layout.item_closet_home, null);
//            viewHold = new ViewHold();
//            viewHold.gridView = (GridViewForScrollView) convertView.findViewById(R.id.closet_gridView);
//            convertView.setTag(viewHold);
//        } else {
//            viewHold = (ViewHold) convertView.getTag();
//        }
//        ClosetItemAdapter adapter = new ClosetItemAdapter(context, closetList);
//        viewHold.gridView.setAdapter(adapter);
//        return convertView;
//    }
//
//    private static class ViewHold {
//        private GridViewForScrollView gridView;
//    }
//
//}

package com.example.fashionboomer.adapter;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.fashionboomer.ClothActivity;
import com.example.fashionboomer.InfoActivity;
import com.example.fashionboomer.R;
import com.example.fashionboomer.dto.CategoryBean;
import com.example.fashionboomer.dto.ClothBean;
import com.example.fashionboomer.dto.DataModel;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ClosetAdapter extends RecyclerView.Adapter<ClosetAdapter.ViewHolder> {
    private static final String BASE_URL = "http://fashionboomer.tk:8080";
    private DataModel.Closet closet;
    private List<DataModel.Closet> closetList;
    private DataModel.PageData pageData;
    Context context;

    public ClosetAdapter(List<DataModel.Closet> closetList) {
        this.closetList = closetList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClosetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.adapter_closet, parent, false) ;
        ClosetAdapter.ViewHolder viewHolder = new ClosetAdapter.ViewHolder(view) ;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClosetAdapter.ViewHolder holder, int position) {
        int selectPosition = position;

        String url = BASE_URL + "/v11/closets/images/" + closetList.get(selectPosition).getId();
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions()
                        .transform(new CenterCrop(), new RoundedCorners(2)
                        )).load(url)
                .into(holder.closet_iv);
    }

    public void setClothList(List<DataModel.Closet> closetList) {
        this.closetList = closetList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return closetList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView closet_iv;

        ViewHolder(View itemView) {
            super(itemView);

            closet_iv = itemView.findViewById(R.id.closet_iv);
        }
    }
}


