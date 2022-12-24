package com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.PushDown;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

public class PushDownAnim implements PushDown {
    public static final AccelerateDecelerateInterpolator DEFAULT_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    public static final long DEFAULT_PUSH_DURATION = 50;
    public static final float DEFAULT_PUSH_SCALE = 0.97f;
    public static final float DEFAULT_PUSH_STATIC = 2.0f;
    public static final long DEFAULT_RELEASE_DURATION = 125;
    public static final int MODE_SCALE = 0;
    public static final int MODE_STATIC_DP = 1;
    /* access modifiers changed from: private */
    public final float defaultScale;
    /* access modifiers changed from: private */
    public long durationPush = 50;
    /* access modifiers changed from: private */
    public long durationRelease = 125;
    /* access modifiers changed from: private */
    public AccelerateDecelerateInterpolator interpolatorPush;
    /* access modifiers changed from: private */
    public AccelerateDecelerateInterpolator interpolatorRelease;
    /* access modifiers changed from: private */
    public int mode = 0;
    /* access modifiers changed from: private */
    public float pushScale = 0.97f;
    /* access modifiers changed from: private */
    public float pushStatic = 2.0f;
    private AnimatorSet scaleAnimSet;
    /* access modifiers changed from: private */
    public WeakReference<View> weakView;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    private PushDownAnim(View view) {
        AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = DEFAULT_INTERPOLATOR;
        this.interpolatorPush = accelerateDecelerateInterpolator;
        this.interpolatorRelease = accelerateDecelerateInterpolator;
        WeakReference<View> weakReference = new WeakReference<>(view);
        this.weakView = weakReference;
        ((View) weakReference.get()).setClickable(true);
        this.defaultScale = view.getScaleX();
    }

    public static PushDownAnim setPushDownAnimTo(View view) {
        PushDownAnim pushDownAnim = new PushDownAnim(view);
        pushDownAnim.setOnTouchEvent((View.OnTouchListener) null);
        return pushDownAnim;
    }

    public static PushDownAnimList setPushDownAnimTo(View... viewArr) {
        return new PushDownAnimList(viewArr);
    }

    public PushDown setScale(float f) {
        int i = this.mode;
        if (i == 0) {
            this.pushScale = f;
        } else if (i == 1) {
            this.pushStatic = f;
        }
        return this;
    }

    public PushDown setScale(int i, float f) {
        this.mode = i;
        setScale(f);
        return this;
    }

    public PushDown setDurationPush(long j) {
        this.durationPush = j;
        return this;
    }

    public PushDown setDurationRelease(long j) {
        this.durationRelease = j;
        return this;
    }

    public PushDown setInterpolatorPush(AccelerateDecelerateInterpolator accelerateDecelerateInterpolator) {
        this.interpolatorPush = accelerateDecelerateInterpolator;
        return this;
    }

    public PushDown setInterpolatorRelease(AccelerateDecelerateInterpolator accelerateDecelerateInterpolator) {
        this.interpolatorRelease = accelerateDecelerateInterpolator;
        return this;
    }

    public PushDown setOnClickListener(View.OnClickListener onClickListener) {
        if (this.weakView.get() != null) {
            ((View) this.weakView.get()).setOnClickListener(onClickListener);
        }
        return this;
    }

    public PushDown setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        if (this.weakView.get() != null) {
            ((View) this.weakView.get()).setOnLongClickListener(onLongClickListener);
        }
        return this;
    }

    public PushDown setOnTouchEvent(final View.OnTouchListener onTouchListener) {
        if (this.weakView.get() != null) {
            if (onTouchListener == null) {
                ((View) this.weakView.get()).setOnTouchListener(new View.OnTouchListener() {
                    boolean isOutSide;
                    Rect rect;

                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (view.isClickable()) {
                            int action = motionEvent.getAction();
                            if (action == 0) {
                                this.isOutSide = false;
                                this.rect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                                PushDownAnim pushDownAnim = PushDownAnim.this;
                                pushDownAnim.makeDecisionAnimScale(view, pushDownAnim.mode, PushDownAnim.this.pushScale, PushDownAnim.this.pushStatic, PushDownAnim.this.durationPush, PushDownAnim.this.interpolatorPush, action);
                            } else if (action == 2) {
                                Rect rect2 = this.rect;
                                if (rect2 != null && !this.isOutSide && !rect2.contains(view.getLeft() + ((int) motionEvent.getX()), view.getTop() + ((int) motionEvent.getY()))) {
                                    this.isOutSide = true;
                                    PushDownAnim pushDownAnim2 = PushDownAnim.this;
                                    pushDownAnim2.makeDecisionAnimScale(view, pushDownAnim2.mode, PushDownAnim.this.defaultScale, 0.0f, PushDownAnim.this.durationRelease, PushDownAnim.this.interpolatorRelease, action);
                                }
                            } else if (action == 3 || action == 1) {
                                PushDownAnim pushDownAnim3 = PushDownAnim.this;
                                pushDownAnim3.makeDecisionAnimScale(view, pushDownAnim3.mode, PushDownAnim.this.defaultScale, 0.0f, PushDownAnim.this.durationRelease, PushDownAnim.this.interpolatorRelease, action);
                            }
                        }
                        return false;
                    }
                });
            } else {
                ((View) this.weakView.get()).setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return onTouchListener.onTouch((View) PushDownAnim.this.weakView.get(), motionEvent);
                    }
                });
            }
        }
        return this;
    }

    /* access modifiers changed from: private */
    public void makeDecisionAnimScale(View view, int i, float f, float f2, long j, TimeInterpolator timeInterpolator, int i2) {
        if (i == 1) {
            f = getScaleFromStaticSize(f2);
        }
        animScale(view, f, j, timeInterpolator);
    }

    private void animScale(final View view, float f, long j, TimeInterpolator timeInterpolator) {
        view.animate().cancel();
        AnimatorSet animatorSet = this.scaleAnimSet;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "scaleX", new float[]{f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleY", new float[]{f});
        ofFloat.setInterpolator(timeInterpolator);
        ofFloat.setDuration(j);
        ofFloat2.setInterpolator(timeInterpolator);
        ofFloat2.setDuration(j);
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.scaleAnimSet = animatorSet2;
        animatorSet2.play(ofFloat).with(ofFloat2);
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
            }

            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
            }
        });
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                View p = (View) view.getParent();
                if (p != null) {
                    p.invalidate();
                }
            }
        });
        this.scaleAnimSet.start();
    }

    private float getScaleFromStaticSize(float f) {
        float viewHeight;
        int viewHeight2;
        if (f <= 0.0f) {
            return this.defaultScale;
        }
        float dpToPx = dpToPx(f);
        if (getViewWidth() > getViewHeight()) {
            if (dpToPx > ((float) getViewWidth())) {
                return 1.0f;
            }
            viewHeight = ((float) getViewWidth()) - (dpToPx * 2.0f);
            viewHeight2 = getViewWidth();
        } else if (dpToPx > ((float) getViewHeight())) {
            return 1.0f;
        } else {
            viewHeight = ((float) getViewHeight()) - (dpToPx * 2.0f);
            viewHeight2 = getViewHeight();
        }
        return viewHeight / ((float) viewHeight2);
    }

    private int getViewHeight() {
        return ((View) this.weakView.get()).getMeasuredHeight();
    }

    private int getViewWidth() {
        return ((View) this.weakView.get()).getMeasuredWidth();
    }

    private float dpToPx(float f) {
        return TypedValue.applyDimension(1, f, ((View) this.weakView.get()).getResources().getDisplayMetrics());
    }
}
