package com.videoplayer.fastplayer.gdvideoplayer.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientTikTok {


    public static final String BASE_URL_TIKTOK = "https://www.tiktokdownloader.org/";


    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        String url = "";
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_TIKTOK)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
