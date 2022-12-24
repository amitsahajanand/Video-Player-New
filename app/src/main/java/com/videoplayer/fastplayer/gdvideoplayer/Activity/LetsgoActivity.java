package com.videoplayer.fastplayer.gdvideoplayer.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.adsdemosp.AdsClass.Ads_ExitNativeFull;
import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Preference;
import com.videoplayer.fastplayer.gdvideoplayer.R;

public class LetsgoActivity extends BaseActivity {

    //View
    Button iv_letsgo;

    LinearLayout language_1, language_2, language_3, language_4, language_5, language_6, language_7, language_8, language_9, language_10;
    TextView language_text_1, language_text_2, language_text_3, language_text_4, language_text_5, language_text_6, language_text_7, language_text_8, language_text_9, language_text_10;

    boolean select_1 = false, select_2 = false, select_3 = false, select_4 = false, select_5 = false, select_6 = false, select_7 = false, select_8 = false, select_9 = false, select_10 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_letsgo );

        Declaration();
        Intialization();
    }

    private void Declaration() {
        iv_letsgo = findViewById( R.id.iv_letsgo );
        language_1 = findViewById( R.id.language_1 );
        language_2 = findViewById( R.id.language_2 );
        language_3 = findViewById( R.id.language_3 );
        language_4 = findViewById( R.id.language_4 );
        language_5 = findViewById( R.id.language_5 );
        language_6 = findViewById( R.id.language_6 );
        language_7 = findViewById( R.id.language_7 );
        language_8 = findViewById( R.id.language_8 );
        language_9 = findViewById( R.id.language_9 );
        language_10 = findViewById( R.id.language_10 );
        language_text_1 = findViewById( R.id.language_text_1 );
        language_text_2 = findViewById( R.id.language_text_2 );
        language_text_3 = findViewById( R.id.language_text_3 );
        language_text_4 = findViewById( R.id.language_text_4 );
        language_text_5 = findViewById( R.id.language_text_5 );
        language_text_6 = findViewById( R.id.language_text_6 );
        language_text_7 = findViewById( R.id.language_text_7 );
        language_text_8 = findViewById( R.id.language_text_8 );
        language_text_9 = findViewById( R.id.language_text_9 );
        language_text_10 = findViewById( R.id.language_text_10 );
    }

    private void Intialization() {

        iv_letsgo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ads_Interstitial.showAds_full( LetsgoActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity( new Intent( LetsgoActivity.this, NextActivity.class ) );
                    }
                } );

            }
        } );


        language_1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select_1) {
                    language_1.setBackground( getResources().getDrawable( R.drawable.third_bg ) );
                    language_text_1.setTextColor( getResources().getColor( R.color.button_color_new ) );
                    select_1 = false;
                } else {
                    language_1.setBackground( getResources().getDrawable( R.drawable.third_bg_select ) );
                    language_text_1.setTextColor( getResources().getColor( R.color.button_color ) );
                    select_1 = true;
                }
            }
        } );

        language_2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select_2) {
                    language_2.setBackground( getResources().getDrawable( R.drawable.third_bg ) );
                    language_text_2.setTextColor( getResources().getColor( R.color.button_color_new ) );
                    select_2 = false;
                } else {
                    language_2.setBackground( getResources().getDrawable( R.drawable.third_bg_select ) );
                    language_text_2.setTextColor( getResources().getColor( R.color.button_color ) );
                    select_2 = true;
                }
            }
        } );

        language_3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select_3) {
                    language_3.setBackground( getResources().getDrawable( R.drawable.third_bg ) );
                    language_text_3.setTextColor( getResources().getColor( R.color.button_color_new ) );
                    select_3 = false;
                } else {
                    language_3.setBackground( getResources().getDrawable( R.drawable.third_bg_select ) );
                    language_text_3.setTextColor( getResources().getColor( R.color.button_color ) );
                    select_3 = true;
                }
            }
        } );

        language_4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select_4) {
                    language_4.setBackground( getResources().getDrawable( R.drawable.third_bg ) );
                    language_text_4.setTextColor( getResources().getColor( R.color.button_color_new ) );
                    select_4 = false;
                } else {
                    language_4.setBackground( getResources().getDrawable( R.drawable.third_bg_select ) );
                    language_text_4.setTextColor( getResources().getColor( R.color.button_color ) );
                    select_4 = true;
                }
            }
        } );

        language_5.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select_5) {
                    language_5.setBackground( getResources().getDrawable( R.drawable.third_bg ) );
                    language_text_5.setTextColor( getResources().getColor( R.color.button_color_new ) );
                    select_5 = false;
                } else {
                    language_5.setBackground( getResources().getDrawable( R.drawable.third_bg_select ) );
                    language_text_5.setTextColor( getResources().getColor( R.color.button_color ) );
                    select_5 = true;
                }
            }
        } );

        language_6.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select_6) {
                    language_6.setBackground( getResources().getDrawable( R.drawable.third_bg ) );
                    language_text_6.setTextColor( getResources().getColor( R.color.button_color_new ) );
                    select_6 = false;
                } else {
                    language_6.setBackground( getResources().getDrawable( R.drawable.third_bg_select ) );
                    language_text_6.setTextColor( getResources().getColor( R.color.button_color ) );
                    select_6 = true;
                }
            }
        } );

        language_7.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select_7) {
                    language_7.setBackground( getResources().getDrawable( R.drawable.third_bg ) );
                    language_text_7.setTextColor( getResources().getColor( R.color.button_color_new ) );
                    select_7 = false;
                } else {
                    language_7.setBackground( getResources().getDrawable( R.drawable.third_bg_select ) );
                    language_text_7.setTextColor( getResources().getColor( R.color.button_color ) );
                    select_7 = true;
                }
            }
        } );

        language_8.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select_8) {
                    language_8.setBackground( getResources().getDrawable( R.drawable.third_bg ) );
                    language_text_8.setTextColor( getResources().getColor( R.color.button_color_new ) );
                    select_8 = false;
                } else {
                    language_8.setBackground( getResources().getDrawable( R.drawable.third_bg_select ) );
                    language_text_8.setTextColor( getResources().getColor( R.color.button_color ) );
                    select_8 = true;
                }
            }
        } );

        language_9.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select_9) {
                    language_9.setBackground( getResources().getDrawable( R.drawable.third_bg ) );
                    language_text_9.setTextColor( getResources().getColor( R.color.button_color_new ) );
                    select_9 = false;
                } else {
                    language_9.setBackground( getResources().getDrawable( R.drawable.third_bg_select ) );
                    language_text_9.setTextColor( getResources().getColor( R.color.button_color ) );
                    select_9 = true;
                }
            }
        } );

        language_10.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select_10) {
                    language_10.setBackground( getResources().getDrawable( R.drawable.third_bg ) );
                    language_text_10.setTextColor( getResources().getColor( R.color.button_color_new ) );
                    select_10 = false;
                } else {
                    language_10.setBackground( getResources().getDrawable( R.drawable.third_bg_select ) );
                    language_text_10.setTextColor( getResources().getColor( R.color.button_color ) );
                    select_10 = true;
                }
            }
        } );


    }

    boolean exit_flag = false;


    @Override
    public void onBackPressed() {
        if (Preference.getScreen_show() == 3) {
            if (Ads_ExitNativeFull.checkExitAdsLoaded()) {
                Exit_Dialog( LetsgoActivity.this );
            } else {
                if (exit_flag) {
                    finishAffinity();
                } else {
                    exit_flag = true;
                    Toast.makeText( this, "Please tap again!", Toast.LENGTH_SHORT ).show();
                    new Handler().postDelayed( new Runnable() {
                        @Override
                        public void run() {
                            exit_flag = false;
                        }
                    }, 3000 );
                }
            }
        } else {
            Ads_Interstitial.BackshowAds_full( this, new Ads_Interstitial.OnFinishAds() {
                @Override
                public void onFinishAds(boolean b) {
                    finish();
                }
            } );
        }

    }


    public static Dialog exit_dialog;

    public static void Exit_Dialog(Activity activity) {
        exit_dialog = new Dialog( activity );
        exit_dialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );


        Window window = exit_dialog.getWindow();
        window.setLayout( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT );
        window.setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        window.getDecorView().setSystemUiVisibility( uiOptions );

        LayoutInflater inflater = (LayoutInflater) exit_dialog.getLayoutInflater();
        View customView = inflater.inflate( R.layout.custom_exit_dialog, null );
        exit_dialog.setContentView( customView );
        exit_dialog.getWindow().setGravity( Gravity.BOTTOM );
        exit_dialog.getWindow().setLayout( RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        exit_dialog.getWindow().getAttributes().windowAnimations = R.style.ExitDialogAnimation;
        exit_dialog.setCancelable( true );
        exit_dialog.setCanceledOnTouchOutside( true );
        TextView txt_done = (TextView) exit_dialog.findViewById( R.id.txt_done );

        LinearLayout llline = (LinearLayout) exit_dialog.findViewById( R.id.llline );
        LinearLayout llnative = (LinearLayout) exit_dialog.findViewById( R.id.llnative );
        TextView ad_call_to_action = (TextView) exit_dialog.findViewById( R.id.ad_call_to_action );

        Ads_ExitNativeFull.Exit_NativeFull_Show( activity, llnative, llline, ad_call_to_action );

        txt_done.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finishAffinity();
            }
        } );

        exit_dialog.show();
    }

}