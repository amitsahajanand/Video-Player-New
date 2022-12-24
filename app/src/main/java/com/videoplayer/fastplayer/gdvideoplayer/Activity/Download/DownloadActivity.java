package com.videoplayer.fastplayer.gdvideoplayer.Activity.Download;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.AppConstants;
import com.videoplayer.fastplayer.gdvideoplayer.R;

import static com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils.ad_sample;

public class DownloadActivity extends BaseActivity {
    //View

    ImageView iv_insta,iv_fb,iv_whatsapp,iv_twitter,iv_tiktok,iv_sharechat;
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Declaration();
        Intialization();
    }


    private void Declaration() {

        iv_fb = findViewById(R.id.iv_fb);
        iv_insta = findViewById(R.id.iv_insta);
        iv_whatsapp = findViewById(R.id.iv_whatsapp);
        iv_twitter = findViewById(R.id.iv_twitter);
        iv_tiktok = findViewById(R.id.iv_tiktok);
        iv_sharechat = findViewById(R.id.iv_sharechat);

    }

    private void Intialization() {

        iv_insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentActivity(AppConstants.INSTAGRAM);
            }
        });
        iv_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentActivity(AppConstants.FACEBOOK);
            }
        });
        iv_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentActivity(AppConstants.WHATSAPP);
            }
        });
        iv_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentActivity(AppConstants.TWITTER);
            }
        });
        iv_tiktok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentActivity(AppConstants.TIKTOK);
            }
        });
        iv_sharechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentActivity(AppConstants.SHARECHAT);
            }
        });

    }

    private void IntentActivity(String flag) {

        Ads_Interstitial.showAds_full(DownloadActivity.this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                ad_sample = false;
                startActivity(new Intent(DownloadActivity.this, ImageAndVideoListingActivity.class).putExtra("Name", flag));
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