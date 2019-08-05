package com.example01.myapplicationtest01.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

public class SquareLinearLayout extends LinearLayout {
    public SquareLinearLayout(Context context) { super(context);}

    public SquareLinearLayout(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    public SquareLinearLayout(Context context, AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SquareLinearLayout(Context context,AttributeSet attrs,int defStyleAttr,int defStyleRes) {
        super(context,attrs,defStyleAttr,defStyleRes);
    }

    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0,widthMeasureSpec),getDefaultSize(0,heightMeasureSpec));

        //获取测量后的宽度
        int width = getMeasuredWidth();
        //创建一个测量规格，设置高度和宽度相等
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthMeasureSpec,heightMeasureSpec);
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }
}
