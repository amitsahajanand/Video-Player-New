package com.videoplayer.fastplayer.gdvideoplayer.Activity.Instagram.ModelInsta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShortcodeMedia {

    @SerializedName("display_url")
    @Expose
    private String displayUrl;
    @SerializedName("video_url")
    @Expose
    private String videoUrl;
    @SerializedName("is_video")
    @Expose
    private Boolean isVideo;

    public String getDisplayUrl() {
        return displayUrl;
    }

    public void setDisplayUrl(String displayUrl) {
        this.displayUrl = displayUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Boolean getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(Boolean isVideo) {
        this.isVideo = isVideo;
    }

}