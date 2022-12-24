package com.videoplayer.fastplayer.gdvideoplayer.Activity.VideoCall;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.VideoCall.ExtraPiicker.Age_number_Adapter;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.VideoCall.ExtraPiicker.PickerLayoutManager;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.VideoCall.ExtraPiicker.numberModel;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Preference;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;

import okhttp3.internal.Util;

import static com.videoplayer.fastplayer.gdvideoplayer.Activity.VideoCall.GenderActivity.select;

public class VideoCall_Age_Activity extends BaseActivity {
    PickerLayoutManager pickerLayoutManager;
    RecyclerView rcy_age;
    ImageView  iv_profile, iv_gender,iv_profile_new;
    TextView tv_username;
    Button btn_next;

    ArrayList<numberModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_video_call__age_ );


        findViewById( R.id.btn_next ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ads_Interstitial.showAds_full( VideoCall_Age_Activity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity( new Intent( VideoCall_Age_Activity.this, MakeVideoCallActivity.class ) );
                        finish();
                    }
                } );

            }
        } );

        btn_next = findViewById(R.id.btn_next);
        iv_profile = findViewById(R.id.iv_profile);
        iv_gender = findViewById(R.id.iv_gender);
        rcy_age = findViewById(R.id.rcy_age);
        tv_username = findViewById(R.id.tv_username);
        iv_profile_new = findViewById(R.id.iv_profile_new);

        tv_username.setText(Utils.USER_NAME);

        if (Preference.getselectedImageUri().equals( "" )) {
            iv_profile_new.setImageDrawable( getResources().getDrawable( R.drawable.ic_profile_slct ) );
        } else {
            Uri uri = Uri.parse( Preference.getselectedImageUri() );
            Glide.with( VideoCall_Age_Activity.this ).load( uri ).placeholder( R.drawable.ic_profile_slct ).error( R.drawable.ic_profile_slct ).into( iv_profile_new );
        }

        if( Utils.USER_GENDER == 1){
            iv_gender.setImageResource(R.drawable.ic_select_male);
        }else {
            iv_gender.setImageResource(R.drawable.ic_select_female);
        }

        if (select == 1){
            iv_profile.setBackground( getResources().getDrawable( R.drawable.ic_male_unselc ) );
        }else if (select == 2){
            iv_profile.setBackground( getResources().getDrawable( R.drawable.ic_female_unselec ) );
        }


        pickerLayoutManager = new PickerLayoutManager(this, PickerLayoutManager.HORIZONTAL, false);
        pickerLayoutManager.setChangeAlpha(true);
        pickerLayoutManager.setScaleDownBy(0.99f);
        pickerLayoutManager.setScaleDownDistance(0.8f);
        pickerLayoutManager.setManager(pickerLayoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rcy_age);
        rcy_age.setLayoutManager(pickerLayoutManager);
        //rcy_age.setHasFixedSize(true);

        for (int i = 16; i < 104; i++) {
            String number = String.valueOf(i);
            if (i <= 100) {
                data.add(new numberModel(number, false));

            } else {
                data.add(new numberModel("", false));
            }
        }

        final Age_number_Adapter age_number_adapter = new Age_number_Adapter(this, data);
        rcy_age.setAdapter(age_number_adapter);


        rcy_age.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("NumberSelect", "selected state +===> " + dx);
                int firstPos = pickerLayoutManager.findFirstVisibleItemPosition();
                int lastPos = pickerLayoutManager.findLastVisibleItemPosition();
                int middle = Math.abs(lastPos - firstPos) / 2 + firstPos;


                Utils.age_selected_pos = middle;
                Log.d("NumberSelect", "selected middle +===> " + middle);
                age_number_adapter.notifyDataSetChanged();
            }
        });

        rcy_age.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //touch_choose = true;
                return false;
            }
        });

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