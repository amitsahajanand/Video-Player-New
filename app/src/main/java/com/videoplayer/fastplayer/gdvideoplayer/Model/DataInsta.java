package com.videoplayer.fastplayer.gdvideoplayer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataInsta {

    @SerializedName("items")
    @Expose
    public List<Item> items = null;

    public class Item {


        @SerializedName("video_versions")
        @Expose
        public List<VideoVersion> videoVersions = null;


        @SerializedName("carousel_media")
        @Expose
        public List<Carousel_media> carousel_media = null;


        @SerializedName("image_versions2")
        @Expose
        public Image_Versions2 image_versions2 = null;

        @SerializedName("product_type")
        public String product_type;


    }

    public class Image_Versions2 {
        @SerializedName("candidates")
        @Expose
        public List<Candidates> candidatesList = null;
    }

    public class Candidates {

        @SerializedName("url")
        public String url;

    }

    public class Carousel_media {

        @SerializedName("image_versions2")
        @Expose
        public Image_Versions2 image_versions2 = null;
    }

    public class VideoVersion {

        @SerializedName("type")
        @Expose
        public Integer type;
        @SerializedName("width")
        @Expose
        public Integer width;
        @SerializedName("height")
        @Expose
        public Integer height;
        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("id")
        @Expose
        public String id;
    }
}
