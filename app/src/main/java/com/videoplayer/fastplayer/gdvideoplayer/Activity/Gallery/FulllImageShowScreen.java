package com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.FullImageGalleryAdapter;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.pictureFacer;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;

import java.util.ArrayList;

public class FulllImageShowScreen extends BaseActivity {


    //View
    ImageView iv_back, iv_download;
    ViewPager viewpager_imgvid;
    Button btn_download;

    private ArrayList<pictureFacer> fileArrayList;
    private int Position = 0;
    int lastPosition = 0;
    TextView ic_path;

    FullImageGalleryAdapter fullImageGalleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fulll_image_show_screen );
        Declaration();
        Initiazlization();
    }

    private void Declaration() {

        btn_download = findViewById( R.id.btn_download );
        iv_download = findViewById( R.id.iv_download );
        iv_back = findViewById( R.id.iv_back );
        viewpager_imgvid = findViewById( R.id.viewpager_imgvid );
        ic_path = findViewById( R.id.ic_path );


//        LinearLayout lnr_ads_banner = (LinearLayout) findViewById(R.id.lnr_ads_banner);
//        LinearLayout ll_nativeadfullview_banner = (LinearLayout) findViewById(R.id.ll_nativeadfullview_banner);
//        LinearLayout ll_ads_banner = (LinearLayout) findViewById(R.id.ll_ads_banner);
//        Ads.Banner_Native_adtype(FulllImageShowScreen.this, ll_nativeadfullview_banner, lnr_ads_banner, ll_ads_banner, 0, false);
        fileArrayList = Utils.pictureFacerlist;
        Position = Utils.position;


    }

    private void Initiazlization() {


        iv_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );

        fullImageGalleryAdapter = new FullImageGalleryAdapter( this, fileArrayList );
        viewpager_imgvid.setAdapter( fullImageGalleryAdapter );
        viewpager_imgvid.setCurrentItem( Position );

        ic_path.setText( fileArrayList.get( Position ).getPicturePath() );

        viewpager_imgvid.setOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int pos) {

                if (fileArrayList.get( pos ) == null) {
                    if (lastPosition >= pos) {
                        viewpager_imgvid.setCurrentItem( pos - 1 );
                        lastPosition = pos - 1;
                        Log.d( "onPageScroll12345", "Left onPageSelected  = " + pos );
                    } else if (lastPosition <= pos) {
                        viewpager_imgvid.setCurrentItem( pos + 1 );
                        lastPosition = pos + 1;
                        Log.d( "onPageScroll12345", "Right onPageSelected  = " + pos );
                    }
                    Log.d( "onPageScroll12345", "onPageSelected  = " + pos );
                    Log.d( "onPageScroll12345", "onPageSelected lastPosition   = " + pos );
                    return;
                }
                Position = pos;
                System.out.println( "Current position==" + Position );
                ic_path.setText( fileArrayList.get( Position ).getPicturePath() );
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int num) {
            }
        } );

    }


    @Override
    public void onBackPressed() {

        Ads_Interstitial.BackshowAds_full( this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                finish();
            }
        } );

    }
}