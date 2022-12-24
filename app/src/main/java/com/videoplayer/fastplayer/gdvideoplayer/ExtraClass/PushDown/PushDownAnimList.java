package com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.PushDown;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

public class PushDownAnimList implements PushDown {
    private final List<PushDownAnim> pushDownList = new ArrayList();

    PushDownAnimList(View... viewArr) {
        for (View pushDownAnimTo : viewArr) {
            PushDownAnim pushDownAnimTo2 = PushDownAnim.setPushDownAnimTo(pushDownAnimTo);
            pushDownAnimTo2.setOnTouchEvent((View.OnTouchListener) null);
            this.pushDownList.add(pushDownAnimTo2);
        }
    }

    public PushDownAnimList setScale(float f) {
        for (PushDownAnim scale : this.pushDownList) {
            scale.setScale(f);
        }
        return this;
    }

    public PushDown setScale(int i, float f) {
        for (PushDownAnim scale : this.pushDownList) {
            scale.setScale(i, f);
        }
        return this;
    }

    public PushDownAnimList setDurationPush(long j) {
        for (PushDownAnim durationPush : this.pushDownList) {
            durationPush.setDurationPush(j);
        }
        return this;
    }

    public PushDownAnimList setDurationRelease(long j) {
        for (PushDownAnim durationRelease : this.pushDownList) {
            durationRelease.setDurationRelease(j);
        }
        return this;
    }

    public PushDownAnimList setInterpolatorPush(AccelerateDecelerateInterpolator accelerateDecelerateInterpolator) {
        for (PushDownAnim interpolatorPush : this.pushDownList) {
            interpolatorPush.setInterpolatorPush(accelerateDecelerateInterpolator);
        }
        return this;
    }

    public PushDownAnimList setInterpolatorRelease(AccelerateDecelerateInterpolator accelerateDecelerateInterpolator) {
        for (PushDownAnim interpolatorRelease : this.pushDownList) {
            interpolatorRelease.setInterpolatorRelease(accelerateDecelerateInterpolator);
        }
        return this;
    }

    public PushDownAnimList setOnClickListener(View.OnClickListener onClickListener) {
        for (PushDownAnim next : this.pushDownList) {
            if (onClickListener != null) {
                next.setOnClickListener(onClickListener);
            }
        }
        return this;
    }

    public PushDown setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        for (PushDownAnim next : this.pushDownList) {
            if (onLongClickListener != null) {
                next.setOnLongClickListener(onLongClickListener);
            }
        }
        return this;
    }

    public PushDownAnimList setOnTouchEvent(View.OnTouchListener onTouchListener) {
        for (PushDownAnim next : this.pushDownList) {
            if (onTouchListener != null) {
                next.setOnTouchEvent(onTouchListener);
            }
        }
        return this;
    }
}
