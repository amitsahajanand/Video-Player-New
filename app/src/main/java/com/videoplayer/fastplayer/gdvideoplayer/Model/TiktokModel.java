package com.videoplayer.fastplayer.gdvideoplayer.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TiktokModel implements Serializable {
    @SerializedName("data")
    TiktokDataModel data;
    @SerializedName("description")
    String description;
    @SerializedName("message")
    String message;
    @SerializedName("responsecode")
    String responsecode;
    @SerializedName("status")
    String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getResponsecode() {
        return this.responsecode;
    }

    public void setResponsecode(String str) {
        this.responsecode = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public TiktokDataModel getData() {
        return this.data;
    }

    public void setData(TiktokDataModel tiktokDataModel) {
        this.data = tiktokDataModel;
    }
}
