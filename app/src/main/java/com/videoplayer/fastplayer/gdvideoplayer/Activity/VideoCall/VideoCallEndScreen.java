package com.videoplayer.fastplayer.gdvideoplayer.Activity.VideoCall;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.BuildConfig;
import com.videoplayer.fastplayer.gdvideoplayer.R;

public class VideoCallEndScreen extends BaseActivity {

    //View
    LinearLayout ll_rateus;
    ImageView iv_reconnect, iv_goback;
    Button btn_connect, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call_end_screen);

        Declaration();
        Intialization();
    }

    private void Declaration() {

        ll_rateus = findViewById(R.id.ll_rateus);
        iv_reconnect = findViewById(R.id.iv_reconnect);
        btn_connect = findViewById(R.id.btn_connect);
        iv_goback = findViewById(R.id.iv_goback);
        btn_back = findViewById(R.id.btn_back);


//        findViewById( R.id.btn_connect ).setBackgroundTintList( ColorStateList.valueOf( Color.parseColor( Preference.getgender_color() ) ) );
//        findViewById( R.id.btn_back ).setBackgroundTintList( ColorStateList.valueOf( Color.parseColor( Preference.getgender_color() ) ) );
    }

    private void Intialization() {


        ll_rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID));
                startActivity(intent);
            }
        });

        iv_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        iv_reconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ads_Interstitial.showAds_full(VideoCallEndScreen.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity(new Intent(VideoCallEndScreen.this, VideoChatViewActivity.class));
                        finish();
                    }
                });
            }
        });
        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ads_Interstitial.showAds_full(VideoCallEndScreen.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity(new Intent(VideoCallEndScreen.this, VideoChatViewActivity.class));
                        finish();
                    }
                });
            }
        });

    }


    @Override
    public void onBackPressed() {

        Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                finish();
            }
        });

    }
}