package com.videoplayer.fastplayer.gdvideoplayer.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Download.DownloadActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.ImageGallery_Screen;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Video.VideoFolderListActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.VideoCall.UserNameActivity;
import com.videoplayer.fastplayer.gdvideoplayer.BuildConfig;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Preference;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;
import com.videoplayer.fastplayer.gdvideoplayer.connection.N_Screen;

public class MainVideoScreen extends BaseActivity {

    public static boolean isMainActivity = false;

    ImageView img_vpn;
    ImageView ll_social_downloader, ll_videocall, ll_player, ll_mydownload, ll_gallery , ll_player_one;
    RecyclerView rv_folderlist;
    TextView tv_nodata;
    ProgressBar progress_video;
    ImageView rate, share, privacy;
    HorizontalScrollView horizontal_scroll;
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_video_screen );
        isMainActivity = true;

        Declaration();
        Intialization();
    }


    private void Declaration() {

        img_vpn = findViewById( R.id.img_vpn );
        ll_mydownload = findViewById( R.id.ll_mydownload );
        ll_player = findViewById( R.id.ll_player );
        ll_videocall = findViewById( R.id.ll_videocall );
        ll_social_downloader = findViewById( R.id.ll_social_downloader );
        ll_gallery = findViewById( R.id.ll_gallery );
        rate = findViewById( R.id.rate );
        share = findViewById( R.id.share );
        privacy = findViewById( R.id.privacy );
        horizontal_scroll = findViewById( R.id.horizontal_scroll );
        ll_player_one = findViewById( R.id.ll_player_one );
        linear = findViewById( R.id.linear );

        rv_folderlist = findViewById( R.id.rv_folderlist );
        tv_nodata = findViewById( R.id.tv_nodata );
        progress_video = findViewById( R.id.progress_video );
    }

    private void Intialization() {

        if (Preference.getvideocall_show() || Preference.getImg_gallery() || Preference.getSocial_downloader()){
            horizontal_scroll.setVisibility( View.VISIBLE );
            linear.setVisibility( View.GONE );
        }else {
            horizontal_scroll.setVisibility( View.GONE );
            linear.setVisibility( View.VISIBLE );
        }

        if (Preference.getvideocall_show()) {
            ll_videocall.setVisibility( View.VISIBLE );
        } else {
            ll_videocall.setVisibility( View.GONE );
        }

        if (Preference.getImg_gallery()) {
            ll_gallery.setVisibility( View.VISIBLE );
        } else {
            ll_gallery.setVisibility( View.GONE );
        }

        if (Preference.getSocial_downloader()) {
            ll_social_downloader.setVisibility( View.VISIBLE );
            ll_mydownload.setVisibility( View.VISIBLE );
        } else {
            ll_social_downloader.setVisibility( View.GONE );
            ll_mydownload.setVisibility( View.GONE );
        }

        if (Preference.getVn_header_show() && Preference.getVPN_Show()) {
            img_vpn.setVisibility( View.VISIBLE );
        } else {
            img_vpn.setVisibility( View.GONE );
        }


        rate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID ) );
                startActivity( intent );
            }
        } );

        share.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent( Intent.ACTION_SEND );
                    shareIntent.setType( "text/plain" );
                    shareIntent.putExtra( Intent.EXTRA_SUBJECT, getResources().getString( R.string.app_name ) );
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra( Intent.EXTRA_TEXT, shareMessage );
                    startActivity( Intent.createChooser( shareIntent, "choose one" ) );
                } catch (Exception e) {
                    //e.toString();
                }
            }
        } );

        privacy.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Preference.getprivacy_policy().isEmpty()) {
                    String urlString = Preference.getprivacy_policy();
                    Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( urlString ) );
                    intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                    intent.setPackage( "com.android.chrome" );
                    try {
                        startActivity( intent );
                    } catch (ActivityNotFoundException ex) {
                        intent.setPackage( null );
                        startActivity( intent );
                    }
                }
            }
        } );

        img_vpn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ads_Interstitial.showAds_full( MainVideoScreen.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity( new Intent( MainVideoScreen.this, N_Screen.class ).putExtra( "type_connection", "disconnect" ) );
                    }
                } );
            }
        } );

        ll_player.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ads_Interstitial.showAds_full( MainVideoScreen.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        Utils.ad_sample = false;
                        startActivity( new Intent( MainVideoScreen.this, VideoFolderListActivity.class ) );
                    }
                } );
            }
        } );

        ll_player_one.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ads_Interstitial.showAds_full( MainVideoScreen.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        Utils.ad_sample = false;
                        startActivity( new Intent( MainVideoScreen.this, VideoFolderListActivity.class ) );
                    }
                } );
            }
        } );

        ll_social_downloader.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ads_Interstitial.showAds_full( MainVideoScreen.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity( new Intent( MainVideoScreen.this, SocialDownloaderActivity.class ) );
                    }
                } );

            }
        } );

        ll_videocall.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ads_Interstitial.showAds_full( MainVideoScreen.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity( new Intent( MainVideoScreen.this, UserNameActivity.class ) );
                    }
                } );
            }
        } );

        ll_gallery.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ads_Interstitial.showAds_full( MainVideoScreen.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity( new Intent( MainVideoScreen.this, ImageGallery_Screen.class ) );
                    }
                } );
            }
        } );


        ll_mydownload.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ads_Interstitial.showAds_full( MainVideoScreen.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity( new Intent( MainVideoScreen.this, DownloadActivity.class ) );
                    }
                } );
            }
        } );


    }

    @Override
    public void onBackPressed() {
        isMainActivity = false;

        Ads_Interstitial.BackshowAds_full( this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                finish();
            }
        } );
    }

}