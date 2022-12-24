package com.videoplayer.fastplayer.gdvideoplayer.Activity;

import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Facebook.NewFaceBookActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Instagram.InstaActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.ShareChat.ShareChatDownLoadActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.TikTok.TikTokActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Twitter.TwitterActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Whatsapp.WAstatusActivity;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Preference;
import com.videoplayer.fastplayer.gdvideoplayer.R;

public class SocialDownloaderActivity extends BaseActivity {

    //View

    ImageView iv_insta, iv_fb, iv_whatsapp, iv_twitter, iv_tiktok, iv_sharechat;
    LinearLayout ln_pastelink, ln_download;

    EditText et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_social_downloader );

        Declaration();
        Intialization();
    }

    private void Declaration() {

        iv_fb = findViewById( R.id.iv_fb );
        iv_insta = findViewById( R.id.iv_insta );
        iv_whatsapp = findViewById( R.id.iv_whatsapp );
        iv_twitter = findViewById( R.id.iv_twitter );
        iv_tiktok = findViewById( R.id.iv_tiktok );
        iv_sharechat = findViewById( R.id.iv_sharechat );

        et_search = findViewById( R.id.et_search );
        et_search.setImeOptions( EditorInfo.IME_ACTION_DONE );

        ln_pastelink = findViewById( R.id.ln_pastelink );
        ln_download = findViewById( R.id.ln_download );

    }

    private void Intialization() {

        ln_pastelink.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startpastelink();
            }
        } );

        et_search.setOnEditorActionListener( new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                CheckWhichlink( et_search.getText().toString().trim() );
                et_search.setText( "" );
                return false;
            }
        } );

        ln_download.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );


        iv_whatsapp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 29) {
                    if (Preference.getWhatsapp_URI().equals( "" )) {
                        startActivity( new Intent( SocialDownloaderActivity.this, WAstatusActivity.class ) );
                    } else {

                        Ads_Interstitial.showAds_full( SocialDownloaderActivity.this, new Ads_Interstitial.OnFinishAds() {
                            @Override
                            public void onFinishAds(boolean b) {
                                startActivity( new Intent( SocialDownloaderActivity.this, WAstatusActivity.class ) );
                            }
                        } );

                    }
                } else {
                    Ads_Interstitial.showAds_full( SocialDownloaderActivity.this, new Ads_Interstitial.OnFinishAds() {
                        @Override
                        public void onFinishAds(boolean b) {
                            startActivity( new Intent( SocialDownloaderActivity.this, WAstatusActivity.class ) );
                        }
                    } );
                }
            }
        } );
        iv_insta.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ads_Interstitial.showAds_full( SocialDownloaderActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity( new Intent( SocialDownloaderActivity.this, InstaActivity.class ) );
                    }
                } );

            }
        } );
        iv_twitter.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ads_Interstitial.showAds_full( SocialDownloaderActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity( new Intent( SocialDownloaderActivity.this, TwitterActivity.class ) );
                    }
                } );

            }
        } );
        iv_tiktok.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ads_Interstitial.showAds_full( SocialDownloaderActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity( new Intent( SocialDownloaderActivity.this, TikTokActivity.class ) );
                    }
                } );

            }
        } );
        iv_sharechat.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ads_Interstitial.showAds_full( SocialDownloaderActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity( new Intent( SocialDownloaderActivity.this, ShareChatDownLoadActivity.class ) );
                    }
                } );

            }
        } );

        iv_fb.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ads_Interstitial.showAds_full( SocialDownloaderActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity( new Intent( SocialDownloaderActivity.this, NewFaceBookActivity.class ) );
//                        startActivity(new Intent(SocialDownloaderActivity.this, FacebookDownloadActivity.class));
                    }
                } );

            }
        } );

    }


    private void startpastelink() {
        try {
            et_search.setText( "" );
            ClipboardManager manager = (ClipboardManager) getSystemService( CLIPBOARD_SERVICE );
            if (manager != null && manager.getPrimaryClip() != null && manager.getPrimaryClip().getItemCount() > 0) {
                if (manager.getPrimaryClip().getItemAt( 0 ).getText().toString().contains( "www.instagram.com" )) {
                    et_search.setText( manager.getPrimaryClip().getItemAt( 0 ).getText().toString() );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CheckWhichlink(String link) {

        if (link.contains( "fb" ) || link.contains( "facebook" )) {
            iv_fb.performClick();
        } else if (link.contains( "www.instagram.com" )) {
            iv_insta.performClick();
        } else if (link.contains( "twitter.com" )) {
            iv_twitter.performClick();
        } else if (link.contains( "tiktok.com" )) {
            iv_tiktok.performClick();
        } else if (link.contains( "sharechat" )) {
            iv_sharechat.performClick();
        }

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