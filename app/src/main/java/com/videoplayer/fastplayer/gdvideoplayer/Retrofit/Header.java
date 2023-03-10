package com.videoplayer.fastplayer.gdvideoplayer.Retrofit;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.videoplayer.fastplayer.gdvideoplayer.R;

public class Header extends LinearLayout {
    public ImageView img_back;
    public TextView txt_name;
    String titleText;
    Context contexts;
//    View view;
//    GifImageView iv_round_two;

    public Header(Context context) {
        super(context);
        contexts = context;
    }

    public Header(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Header, 0, 0);
        titleText = a.getString(R.styleable.Header_titleText);
        LayoutInflater li = LayoutInflater.from(context);
        LinearLayout ll = (LinearLayout) li.inflate(R.layout.activity_header, this);

        txt_name = (TextView) ll.findViewById(R.id.txt_header);
        img_back = (ImageView) ll.findViewById(R.id.iv_back_button);
        txt_name.setText(titleText);

        img_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).onBackPressed();
            }
        });
        
        a.recycle();
    }

    public Header(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Header(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}