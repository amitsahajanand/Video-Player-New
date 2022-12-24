package com.videoplayer.fastplayer.gdvideoplayer.Retrofit.api;


import com.google.gson.JsonObject;

import java.util.HashMap;

import io.reactivex.Observable;
import com.videoplayer.fastplayer.gdvideoplayer.Model.TiktokModel;
import com.videoplayer.fastplayer.gdvideoplayer.Model.TwitterResponse;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIServices {
    @GET
    Observable<JsonObject> callResult(@Url String str, @Header("Cookie") String str2, @Header("User-Agent") String str3);

    @FormUrlEncoded
    @POST
    Observable<JsonObject> callSnackVideo(@Url String str, @Field("shortKey") String str2, @Field("os") String str3, @Field("sig") String str4, @Field("client_key") String str5);

    @FormUrlEncoded
    @POST
    Observable<TwitterResponse> callTwitter(@Url String str, @Field("id") String str2);

    /*@GET
    Observable<FullDetailModel> getFullDetailInfoApi(@Url String str, @Header("Cookie") String str2, @Header("User-Agent") String str3);*/

    /*@GET
    Observable<StoryModel> getStoriesApi(@Url String str, @Header("Cookie") String str2, @Header("User-Agent") String str3);*/

    @POST
    Observable<TiktokModel> getTiktokData(@Url String str, @Body HashMap<String, String> hashMap);

    @POST
    Observable<JsonObject> getFaceBookdata(@Url String str, @Field("url") String url, @Field("dowload") String dowload);


}
