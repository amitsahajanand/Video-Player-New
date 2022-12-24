/*
package com.picturemanage.privateiimage.gallery20.ExtraClass;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;
import com.picturemanage.privateiimage.gallery20.R;

public class QurekaView extends RelativeLayout {

    RelativeLayout rl_qureka_view;
    Context context;
    GifImageView qureka_gifImageView;
    int drawable;


    public QurekaView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public QurekaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public QurekaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public QurekaView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init();
    }


    private int Generatenum(){
        int min = 1;
        int max = 3;
        return  new Random().nextInt((max - min) +1) + min;
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.qureka_view, this , true);
        rl_qureka_view = findViewById(R.id.rl_qureka_view);
        qureka_gifImageView = findViewById(R.id.qureka_gifImageView);

        int number = Generatenum();
        if(number == 1){
            qureka_gifImageView.setImageResource(R.drawable.qureka1);
        }else if(number == 2){
            qureka_gifImageView.setImageResource(R.drawable.qureka2);
        }else if(number == 3){
            qureka_gifImageView.setImageResource(R.drawable.qureka3);
        }else {
            qureka_gifImageView.setImageResource(R.drawable.qureka3);
        }


        rl_qureka_view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Relative View", Toast.LENGTH_SHORT).show();
                GetData.OpenQureka((Activity) context);
            }
        });


    }
}
*/
