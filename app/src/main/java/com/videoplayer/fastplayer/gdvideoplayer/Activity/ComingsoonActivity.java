package com.videoplayer.fastplayer.gdvideoplayer.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.videoplayer.fastplayer.gdvideoplayer.R;


public class ComingsoonActivity extends BaseActivity {

    ImageView iv_close_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comingsoon);

        iv_close_bg = findViewById(R.id.iv_close_bg);

        iv_close_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}