package com.videoplayer.fastplayer.gdvideoplayer.Retrofit;


import com.videoplayer.fastplayer.gdvideoplayer.Activity.Instagram.ModelInsta.InstaData;
import com.videoplayer.fastplayer.gdvideoplayer.Model.Example;
import com.videoplayer.fastplayer.gdvideoplayer.Model.Pro_IPModel;
import com.videoplayer.fastplayer.gdvideoplayer.Model.TikTokResponce;
import com.videoplayer.fastplayer.gdvideoplayer.Model.TraficLimitResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

import java.util.Map;

public interface APIInterface {

    @POST("api/setting")
    Call<Example> setting_api(@Body Map<String, String> body);

    @DELETE()
    Call<TraficLimitResponse> Call_Delete_Trafic(@Url String url);

    @POST()
    Call<TraficLimitResponse> Call_Add_Trafic(@Url String url);


    @GET("?fields=61439")
    Call<Pro_IPModel> getipdata();

    @GET()
    Call<InstaData> getInstaData(@Url String sp);

    @GET()
    Call<TikTokResponce> Call_TikTokAPi(@Url String url);
}