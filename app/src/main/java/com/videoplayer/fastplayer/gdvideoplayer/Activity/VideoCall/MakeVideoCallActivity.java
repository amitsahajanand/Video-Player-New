package com.videoplayer.fastplayer.gdvideoplayer.Activity.VideoCall;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.CountDownAnimation;
import com.videoplayer.fastplayer.gdvideoplayer.R;

public class MakeVideoCallActivity extends BaseActivity implements CountDownAnimation.CountDownListener {

    //View
    TextView tv_count;
    LinearLayout iv_call_now;
    RelativeLayout rl_start;
    ProgressBar progressbar;
    CountDownTimer countDownTimer;
    int i_pos=0;
    LinearLayout ln_timer;
    ImageView iv_back_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_video_call);

        Declaration();
        Initialization();

    }

    private void Declaration() {
        rl_start = findViewById(R.id.rl_start);
        iv_call_now = findViewById(R.id.iv_call_now);
        tv_count = findViewById(R.id.tv_count);
        progressbar = findViewById(R.id.progressbar);
        iv_back_button = findViewById(R.id.iv_back_button);
        ln_timer = findViewById(R.id.ln_timer);
        progressbar.setProgress(i_pos);
        progressbar.getProgressDrawable().setColorFilter( Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

    }

    boolean isclick = true;

    private void Initialization() {
        initCountDownAnimation();

        iv_back_button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );

        rl_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer(5);
            }
        });
        iv_call_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isclick){
                    isclick=false;
                    timer(5);
                }

            }
        });

    }

    private CountDownAnimation countDownAnimation;

    private void initCountDownAnimation() {
        this.countDownAnimation = new CountDownAnimation(this.tv_count, 5);
        this.countDownAnimation.setCountDownListener(this);
    }

    private void timer(int i) {
        ln_timer.setVisibility(View.VISIBLE);
        startCountDownAnimation();
        countDownTimer = new CountDownTimer((long) 5000, 1000) {


            public void onTick(long j) {

                i_pos++;
                progressbar.setProgress((int)i_pos*100/(5000/1000));

            }

            public void onFinish() {
                cancelCountDownAnimation();
                isclick=true;
                i_pos++;
                progressbar.setProgress(100);

                Ads_Interstitial.showAds_full(MakeVideoCallActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity(new Intent(MakeVideoCallActivity.this, VideoChatViewActivity.class));
                        finish();
                    }
                });


            }
        }.start();
    }

    private void startCountDownAnimation() {
        isclick=false;
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        this.countDownAnimation.setAnimation(animationSet);
        this.countDownAnimation.setStartCount(5);
        this.countDownAnimation.start();
    }


    private void cancelCountDownAnimation() {
        this.countDownAnimation.cancel();
    }

    @Override
    public void onCountDownEnd(CountDownAnimation countDownAnimation) {
//        tv_count.setText("0");
//        tv_count.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }


    }

    @Override
    public void onBackPressed() {

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                finish();
            }
        });

    }


}