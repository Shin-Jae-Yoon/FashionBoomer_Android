package com.example.fashionboomer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.fashionboomer.ClothActivity;
import com.example.fashionboomer.R;
import com.example.fashionboomer.dto.ClothBean;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ClothAdapter extends RecyclerView.Adapter<ClothAdapter.ViewHolder> {
    private static final String BASE_URL = "http://fashionboomer.tk:8080";
    private ClothBean.Cloth cloth;
    private List<ClothBean.Cloth> cList;
    Context context;
    BaseViewHolder helper;

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
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions()
                        .transform(new CenterCrop(), new RoundedCorners(2)
                        )).load(BASE_URL + "/v11/clothes/images/" + cList.get(position).getCategory() + "/" + cList.get(position).getDetails() + "/" + String.valueOf(position+1))
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
//            holder.cloth_gender.setText(cList.get(position).getGender().substring(2, 4) + " " + cList.get(position).getGender().substring(8, 10));
            holder.cloth_gender.setText("M W");
            holder.cloth_gender.setTextColor(Color.parseColor("black"));
        }
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

