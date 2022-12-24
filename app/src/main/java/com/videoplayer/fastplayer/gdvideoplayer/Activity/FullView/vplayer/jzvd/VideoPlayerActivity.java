package com.videoplayer.fastplayer.gdvideoplayer.Activity.FullView.vplayer.jzvd;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.R;

public class VideoPlayerActivity extends BaseActivity {

    String path;
  //  private VideoModel simplevideoshow;
    ImageView iv_back_icon;
    MyJzvdStd videoplayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vplayer);
       // simplevideoshow = Utils.videoModel1;

        path = getIntent().getStringExtra("share");
        videoplayer = findViewById(R.id.videoplayer);
        iv_back_icon = findViewById(R.id.iv_back_icon);

        videoplayer.setUp("file://" + path, "");
        videoplayer.startVideo();

        iv_back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                videoplayer.onStatePause();
                videoplayer.reset();
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        videoplayer.onStatePause();
        videoplayer.reset();
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
                @Override
                public void onFinishAds(boolean b) {
                    finish();
                }
            });

        }
    }
    
}