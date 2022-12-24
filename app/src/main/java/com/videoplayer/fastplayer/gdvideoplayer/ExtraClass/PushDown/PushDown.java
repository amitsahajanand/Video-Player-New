package com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.PushDown;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public interface PushDown {
    PushDown setDurationPush(long j);

    PushDown setDurationRelease(long j);

    PushDown setInterpolatorPush(AccelerateDecelerateInterpolator accelerateDecelerateInterpolator);

    PushDown setInterpolatorRelease(AccelerateDecelerateInterpolator accelerateDecelerateInterpolator);

    PushDown setOnClickListener(View.OnClickListener onClickListener);

    PushDown setOnLongClickListener(View.OnLongClickListener onLongClickListener);

    PushDown setOnTouchEvent(View.OnTouchListener onTouchListener);

    PushDown setScale(float f);

    PushDown setScale(int i, float f);
}
