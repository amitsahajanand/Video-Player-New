package com.videoplayer.fastplayer.gdvideoplayer.ExtraClass;

import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

public class CountDownAnimation {
    private Animation mAnimation;
    private final Runnable mCountDown = new Runnable() {
        /* class CountDownAnimation.AnonymousClass1 */

        public void run() {
            if (CountDownAnimation.this.mCurrentCount > 0) {
                TextView textView = CountDownAnimation.this.mTextView;
                textView.setText(CountDownAnimation.this.mCurrentCount + "");
                CountDownAnimation.this.mTextView.startAnimation(CountDownAnimation.this.mAnimation);
                CountDownAnimation.access$010(CountDownAnimation.this);
                return;
            }
            CountDownAnimation.this.mTextView.setVisibility(View.GONE);
            if (CountDownAnimation.this.mListener != null) {
                CountDownAnimation.this.mListener.onCountDownEnd(CountDownAnimation.this);
            }
        }
    };
    private int mCurrentCount;
    private Handler mHandler = new Handler();
    private CountDownListener mListener;
    private int mStartCount;
    private TextView mTextView;

    public interface CountDownListener {
        void onCountDownEnd(CountDownAnimation countDownAnimation);
    }

    static /* synthetic */ int access$010(CountDownAnimation countDownAnimation) {
        int i = countDownAnimation.mCurrentCount;
        countDownAnimation.mCurrentCount = i - 1;
        return i;
    }

    public CountDownAnimation(TextView textView, int i) {
        this.mTextView = textView;
        this.mStartCount = i;
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        this.mAnimation = alphaAnimation;
        alphaAnimation.setDuration(1000);
    }

    public void start() {
        this.mHandler.removeCallbacks(this.mCountDown);
        TextView textView = this.mTextView;
        textView.setText(this.mStartCount + "");
        this.mTextView.setVisibility(View.VISIBLE);
        this.mCurrentCount = this.mStartCount;
        this.mHandler.post(this.mCountDown);
        for (int i = 1; i <= this.mStartCount; i++) {
            this.mHandler.postDelayed(this.mCountDown, (long) (i * 1000));
        }
    }

    public void cancel() {
        this.mHandler.removeCallbacks(this.mCountDown);
        this.mTextView.setText("0");
//        this.mTextView.setVisibility(View.GONE);
    }

    public void setAnimation(Animation animation) {
        this.mAnimation = animation;
        if (animation.getDuration() == 0) {
            this.mAnimation.setDuration(1000);
        }
    }

    public Animation getAnimation() {
        return this.mAnimation;
    }

    public void setStartCount(int i) {
        this.mStartCount = i;
    }

    public int getStartCount() {
        return this.mStartCount;
    }

    public void setCountDownListener(CountDownListener countDownListener) {
        this.mListener = countDownListener;
    }
}
