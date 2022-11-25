package com.example.fashionboomer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


public class GridScrollCloset extends GridView {

    public GridScrollCloset(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridScrollCloset(Context context) {
        super(context);
    }

    public GridScrollCloset(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, expandSpec+30);

    }


}
