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

import static androidx.recyclerview.widget.ItemTouchHelper.Callback.makeMovementFlags;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.fashionboomer.R;
import com.example.fashionboomer.dto.DataModel;
import com.example.fashionboomer.dto.RetrofitClient;
import com.example.fashionboomer.dto.RetrofitInterface;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClosetAdapter extends RecyclerView.Adapter<ClosetAdapter.ViewHolder> {
    private static final String BASE_URL = "http://fashionboomer.tk:8080";
    private DataModel.Closet closet;
    private List<DataModel.Closet> closetList;
    private DataModel.PageData pageData;
    Context context;
    private ClosetAdapter closetAdapter = this;

    private RetrofitClient retrofitClient;
    private RetrofitInterface retrofitInterface;

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

        holder.closet_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("정말 삭제하시겠습니까?");
                builder.setMessage("삭제하면 옷장에서 제거됩니다.");
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        retrofitClient = RetrofitClient.getInstance();
                        retrofitInterface = RetrofitClient.getRetrofitInterface();

                        retrofitInterface.deleteCloset(closetList.get(selectPosition).getId()).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(context, "이미 삭제된 사진입니다.", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "옷을 삭제하지 않습니다.", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public void setClothList(List<DataModel.Closet> closetList) {
        this.closetList = closetList;
        closetAdapter.notifyDataSetChanged();
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


