package com.videoplayer.fastplayer.gdvideoplayer.Activity.VideoCall;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.bumptech.glide.Glide;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Preference;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;

public class GenderActivity extends BaseActivity {

    ImageView iv_profile, iv_female, iv_male, ic_profile, iv_profile_new, notes;
    TextView tv_username;
    RelativeLayout rl_male, rl_female;
    View view_female, view_male;
    Button btn_next;
    TextView tv_female, tv_male;
    int GENDERFLAG = 1;
    LinearLayout female, male;
    private static final int SELECT_PICTURE = 100;
    public static int select = 1;
    public static Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gender );

        Declaration();
        Intialization();

    }

    private void Declaration() {
        iv_profile = findViewById( R.id.iv_profile );
        ic_profile = findViewById( R.id.ic_profile );
        tv_username = findViewById( R.id.tv_username );
        rl_male = findViewById( R.id.rl_male );
        rl_female = findViewById( R.id.rl_female );
        view_female = findViewById( R.id.view_female );
        view_male = findViewById( R.id.view_male );
        iv_female = findViewById( R.id.iv_female );
        iv_male = findViewById( R.id.iv_male );
        btn_next = findViewById( R.id.btn_next );
        female = findViewById( R.id.female );
        male = findViewById( R.id.male );
        iv_profile_new = findViewById( R.id.iv_profile_new );
        notes = findViewById( R.id.notes );

        tv_male = findViewById( R.id.tv_male );
        tv_female = findViewById( R.id.tv_female );
    }

    private void Intialization() {

//        try {
//            Glide.with(GenderActivity.this).load(UserNameActivity.selectedImageUri).placeholder(R.drawable.ic_profile_slct).error(R.drawable.ic_profile_slct).into(iv_profile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        if (Preference.getselectedImageUri().equals( "" )) {
            iv_profile_new.setImageDrawable( getResources().getDrawable( R.drawable.ic_profile_slct ) );
            notes.setVisibility( View.VISIBLE );
        } else {
            Uri uri = Uri.parse( Preference.getselectedImageUri() );
            Glide.with( GenderActivity.this ).load( uri ).placeholder( R.drawable.ic_profile_slct ).error( R.drawable.ic_profile_slct ).into( iv_profile_new );
            notes.setVisibility( View.GONE );
        }

        iv_profile_new.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType( "image/*" );
                intent.setAction( Intent.ACTION_GET_CONTENT );
                startActivityForResult( Intent.createChooser( intent, "Select Picture" ), SELECT_PICTURE );
            }
        } );

        tv_username.setText( Utils.USER_NAME );

        setGenderView();

        rl_female.setOnClickListener( v -> {
            GENDERFLAG = 2;
            setGenderView();
        } );

        rl_male.setOnClickListener( v -> {
            GENDERFLAG = 1;
            setGenderView();
        } );


        male.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = 1;
                iv_profile.setBackground( getResources().getDrawable( R.drawable.ic_male_seclec ) );
                ic_profile.setBackground( getResources().getDrawable( R.drawable.ic_female_unselec ) );
            }
        } );

        female.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = 2;
                iv_profile.setBackground( getResources().getDrawable( R.drawable.ic_male_unselc ) );
                ic_profile.setBackground( getResources().getDrawable( R.drawable.ic_female_selec ) );
            }
        } );

        btn_next.setOnClickListener( v -> {
            Ads_Interstitial.showAds_full( GenderActivity.this, new Ads_Interstitial.OnFinishAds() {
                @Override
                public void onFinishAds(boolean b) {
                    Utils.USER_GENDER = GENDERFLAG;
                    startActivity( new Intent( GenderActivity.this, VideoCall_Age_Activity.class ) );
                    finish();
                }
            } );
        } );
    }

    private void setGenderView() {

        if (GENDERFLAG == 1) {
            MaleSelect();
        } else {
            FemaleSelect();
        }
    }

    private void MaleSelect() {
        iv_female.setImageResource( R.drawable.ic_unselect_female );
        iv_male.setImageResource( R.drawable.ic_select_male );
        tv_female.setTextColor( getResources().getColor( R.color.txt_discription ) );
        tv_male.setTextColor( getResources().getColor( R.color.black ) );
        view_female.setVisibility( View.GONE );
        view_male.setVisibility( View.VISIBLE );
    }

    private void FemaleSelect() {
        iv_female.setImageResource( R.drawable.ic_select_female );
        iv_male.setImageResource( R.drawable.ic_unselect_male );
        tv_male.setTextColor( getResources().getColor( R.color.txt_discription ) );
        tv_female.setTextColor( getResources().getColor( R.color.black ) );
        view_female.setVisibility( View.VISIBLE );
        view_male.setVisibility( View.GONE );
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

                    Glide.with( GenderActivity.this ).load( selectedImageUri ).placeholder( R.drawable.ic_profile_slct ).error( R.drawable.ic_profile_slct ).into( iv_profile_new );

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