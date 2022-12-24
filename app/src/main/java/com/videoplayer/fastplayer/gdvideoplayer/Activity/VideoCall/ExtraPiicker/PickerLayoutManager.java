package com.videoplayer.fastplayer.gdvideoplayer.Activity.VideoCall.ExtraPiicker;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by adityagohad on 06/06/17.
 */

public class PickerLayoutManager extends LinearLayoutManager {
    private float scaleDownBy = 0.66f;
    private float scaleDownDistance = 0.9f;
    private boolean changeAlpha = true;
    private int selectedNoPos = -1;

    private onScrollStopListener onScrollStopListener;
    private onScrollEnd onScrollEnd;
    Context context;
    PickerLayoutManager pickerLayoutManager;

    public PickerLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.context = context;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        scaleDownView();
    }

    public void setManager(PickerLayoutManager manager) {
        this.pickerLayoutManager = manager;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int orientation = getOrientation();
        if (orientation == HORIZONTAL) {
            int scrolled = super.scrollHorizontallyBy(dx, recycler, state);
            scaleDownView();
            return scrolled;
        } else return 0;
    }

    private void scaleDownView() {
        float mid = getWidth() / 2.0f;
        float unitScaleDownDist = scaleDownDistance * mid;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            float childMid = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2.0f;
            float scale = 1.8f + (-1 * scaleDownBy) * (Math.min(unitScaleDownDist, Math.abs(mid - childMid))) / unitScaleDownDist;
            child.setScaleX(scale);
            child.setScaleY(scale);
            if (changeAlpha) {
                child.setAlpha((float) (scale / 2));
            }
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        try {
            if (state == 0) {
                int selected = 0;
                float lastHeight = 0f;
                for (int i = 0; i < getChildCount(); i++) {
                    if (lastHeight < getChildAt(i).getScaleY()) {
                        lastHeight = getChildAt(i).getScaleY();
                        selected = i;
                    }
                }
                this.selectedNoPos = (int) getChildAt(selected).getTag();

                if (onScrollStopListener != null) {
                    onScrollStopListener.selectedView(getChildAt(selected));
                }
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getScaleDownBy() {
        return scaleDownBy;
    }

    public void setScaleDownBy(float scaleDownBy) {
        this.scaleDownBy = scaleDownBy;
    }

    public float getScaleDownDistance() {
        return scaleDownDistance;
    }

    public void setScaleDownDistance(float scaleDownDistance) {
        this.scaleDownDistance = scaleDownDistance;
    }

    public boolean isChangeAlpha() {
        return changeAlpha;
    }

    public void setChangeAlpha(boolean changeAlpha) {
        this.changeAlpha = changeAlpha;
    }

    public void setOnScrollStopListener(onScrollStopListener onScrollStopListener) {
        this.onScrollStopListener = onScrollStopListener;
    }

    public void setOnScrollEnd(onScrollEnd onScrollStopListener) {
        this.onScrollEnd = onScrollStopListener;
    }

    public interface onScrollStopListener {
        public void selectedView(View view);
    }

    public interface onScrollEnd {
        public void selectedView(View view);
    }

    public int getSelectedNoPos() {
        return selectedNoPos;
    }

    public void reSetSelectedNoPos() {
        this.selectedNoPos = -1;
    }
}
