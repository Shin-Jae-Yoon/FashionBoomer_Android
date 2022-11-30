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

public class ClothAdapter extends RecyclerView.Adapter<ClothAdapter.ViewHolder> {
    private static final String BASE_URL = "http://fashionboomer.tk:8080";
    private ClothBean.Cloth cloth;
    private List<ClothBean.Cloth> cList;
    private DataModel.Member member;
    Context context;

    public ClothAdapter(List<ClothBean.Cloth> cList) {
        this.cList = cList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClothAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.adapter_cloth, parent, false) ;
        ClothAdapter.ViewHolder viewHolder = new ClothAdapter.ViewHolder(view) ;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClothAdapter.ViewHolder holder, int position) {
        String url = BASE_URL + "/v11/clothes/images/" + cList.get(position).getCategory() + "/" + cList.get(position).getDetails() + "/" + String.valueOf(position+1);
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions()
                        .transform(new CenterCrop(), new RoundedCorners(2)
                        )).load(url)
                .into(holder.cloth_iv);
        holder.cloth_brand.setText(cList.get(position).getBrand());
        holder.cloth_name.setText(cList.get(position).getName());
        holder.cloth_price.setText(cList.get(position).getPrice());
        if(cList.get(position).getGender().length() == 6) {
            if (cList.get(position).getGender().substring(2, 4).equals("남성")) {
                holder.cloth_gender.setText("M");
                holder.cloth_gender.setTextColor(Color.parseColor("blue"));
            } else {
                holder.cloth_gender.setText("W");
                holder.cloth_gender.setTextColor(Color.parseColor("red"));
            }
        } else if (cList.get(position).getGender().length() == 12) {
            holder.cloth_gender.setText("M W");
            holder.cloth_gender.setTextColor(Color.parseColor("black"));
        }

        int selectPosition = position;

        holder.cloth_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InfoActivity.class);
                intent.putExtra("id", cList.get(selectPosition).getId());
                intent.putExtra("category", cList.get(selectPosition).getCategory());
                intent.putExtra("details", cList.get(selectPosition).getDetails());
                intent.putExtra("brand", cList.get(selectPosition).getBrand());
                intent.putExtra("name", cList.get(selectPosition).getName());
                intent.putExtra("price", cList.get(selectPosition).getPrice());
                intent.putExtra("gender", cList.get(selectPosition).getGender());
                intent.putExtra("link", cList.get(selectPosition).getLink());
                intent.putExtra("position", selectPosition);

                v.getContext().startActivity(intent);
            }
        });
    }

    public void setClothList(List<ClothBean.Cloth> cList) {
        this.cList = cList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cloth_iv;
        TextView cloth_brand;
        TextView cloth_name;
        TextView cloth_price;
        TextView cloth_gender;

        ViewHolder(View itemView) {
            super(itemView);

            cloth_iv = itemView.findViewById(R.id.cloth_iv);
            cloth_brand = itemView.findViewById(R.id.cloth_brand);
            cloth_name = itemView.findViewById(R.id.cloth_name);
            cloth_price = itemView.findViewById(R.id.cloth_price);
            cloth_gender = itemView.findViewById(R.id.cloth_gender);
        }
    }
}

