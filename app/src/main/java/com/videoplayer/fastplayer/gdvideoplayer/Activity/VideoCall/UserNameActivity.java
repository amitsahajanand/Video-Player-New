package com.videoplayer.fastplayer.gdvideoplayer.Activity.VideoCall;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.bumptech.glide.Glide;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Preference;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;

public class UserNameActivity extends BaseActivity {
    //View
    EditText et_name;
    ImageView iv_next;
    FrameLayout fm_profile;
    private static final int SELECT_PICTURE = 100;
    public static Uri selectedImageUri;
    ImageView iv_profile;
    ImageView iv_back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_name );


        Declaration();
        Intialziation();
    }

    private void Declaration() {

        et_name = findViewById( R.id.et_name );
        iv_next = findViewById( R.id.iv_next );
        fm_profile = findViewById( R.id.fm_profile );
        iv_profile = findViewById( R.id.iv_profile );
        iv_back_button = findViewById( R.id.iv_back_button );

        iv_back_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );

        Preference.setselectedImageUri( "" );
    }

    private void Intialziation() {

        iv_next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!et_name.getText().toString().isEmpty()) {

                    Utils.USER_NAME = et_name.getText().toString().trim();
                    Ads_Interstitial.showAds_full( UserNameActivity.this, new Ads_Interstitial.OnFinishAds() {
                        @Override
                        public void onFinishAds(boolean b) {
                            startActivity( new Intent( UserNameActivity.this, GenderActivity.class ) );
                            finish();
                        }
                    } );


                } else {
                    et_name.requestFocus();
                    et_name.setError( "Please Enter Name " );
                }
            }
        } );


        fm_profile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType( "image/*" );
                intent.setAction( Intent.ACTION_GET_CONTENT );
                startActivityForResult( Intent.createChooser( intent, "Select Picture" ), SELECT_PICTURE );
            }
        } );


        findViewById( R.id.btn_next ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!et_name.getText().toString().isEmpty()) {
                    Ads_Interstitial.showAds_full( UserNameActivity.this, new Ads_Interstitial.OnFinishAds() {
                        @Override
                        public void onFinishAds(boolean b) {
                            startActivity( new Intent( UserNameActivity.this, GenderActivity.class ) );
                            finish();
                        }
                    } );
                } else {
                    et_name.requestFocus();
                    et_name.setError( "Please Enter Name " );
                }
            }
        } );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {

                    Log.d( "uriiiii", "new   " + selectedImageUri );
                    Preference.setselectedImageUri( String.valueOf( selectedImageUri ) );

                    Glide.with( UserNameActivity.this ).load( selectedImageUri ).placeholder( R.drawable.ic_profile_slct ).error( R.drawable.ic_profile_slct ).into( iv_profile );

                }
            }
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