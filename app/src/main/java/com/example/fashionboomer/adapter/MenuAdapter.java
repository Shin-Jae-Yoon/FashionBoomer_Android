package com.example.fashionboomer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.fashionboomer.R;

import java.util.List;


/**
 * 왼쪽 메뉴 ListView용 어댑터
 *
 * @author Administrator
 */
public class MenuAdapter extends BaseAdapter {

    private Context context;
    private int selectItem = 0;
    private List<String> list;

    public MenuAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }

    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ViewHolder holder = null;
        if (arg1 == null) {
            holder = new ViewHolder();
            arg1 = View.inflate(context, R.layout.item_menu, null);
            holder.tv_name =  arg1.findViewById(R.id.item_name);
            holder.v_line = arg1.findViewById(R.id.v_line);
            holder.lin_item = arg1.findViewById(R.id.lin_item);
            arg1.setTag(holder);
        } else {
            holder = (ViewHolder) arg1.getTag();
        }
        if (arg0 == selectItem) {
            holder.tv_name.setTextColor(context.getResources().getColor(R.color.color_2B3440));
            holder.lin_item.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.v_line.setVisibility(View.VISIBLE);
        } else {
            holder.tv_name.setTextColor(context.getResources().getColor(R.color.color_999999));
            holder.lin_item.setBackgroundColor(context.getResources().getColor(R.color.color_F6F6f6));
            holder.v_line.setVisibility(View.INVISIBLE);
        }
        holder.tv_name.setText(list.get(arg0));
        return arg1;
    }

    static class ViewHolder {
        private TextView tv_name;
        private View v_line;
        private LinearLayout lin_item;
    }
}
