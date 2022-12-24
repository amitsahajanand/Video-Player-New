package com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils;

import androidx.annotation.Nullable;

/**
 * Author CodeBoy722
 *
 * Custom class for holding data of images on the device external storage
 */
public class pictureFacer {

    private String picturName;
    private String picturePath;
    private String pictureSize;
    private String imageUri;
    private Boolean selected = false;

    public pictureFacer(){

    }

    public pictureFacer(String picturName, String picturePath, String pictureSize, String imageUri) {
        this.picturName = picturName;
        this.picturePath = picturePath;
        this.pictureSize = pictureSize;
        this.imageUri = imageUri;
    }


    public String getPicturName() {
        return picturName;
    }

    public void setPicturName(String picturName) {
        this.picturName = picturName;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getPictureSize() {
        return pictureSize;
    }

    public void setPictureSize(String pictureSize) {
        this.pictureSize = pictureSize;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        pictureFacer pictureFacer = (pictureFacer)obj;

        if(this.picturePath.equals(pictureFacer.getPicturePath())){
            return true;
        }else {
            return false;
        }

    }
}
