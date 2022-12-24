package com.videoplayer.fastplayer.gdvideoplayer.Activity.VideoCall.ExtraPiicker;

import java.io.Serializable;

public class numberModel implements Serializable {
    public String number;
    public boolean isSelected;
    public numberModel(String number, boolean isSelected) {
        this.number = number;
        this.isSelected = isSelected;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}