package com.videoplayer.fastplayer.gdvideoplayer.Model;

import com.google.gson.annotations.SerializedName;

public class TikTokResponce {

    @SerializedName("status")
    String status;
    @SerializedName("download_url")
    String download_url;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
