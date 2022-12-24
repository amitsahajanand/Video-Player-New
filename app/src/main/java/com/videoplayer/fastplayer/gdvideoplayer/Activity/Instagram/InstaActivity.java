package com.videoplayer.fastplayer.gdvideoplayer.Activity.Instagram;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.observers.DisposableObserver;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Download.ImageAndVideoListingActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Instagram.ModelInsta.Data;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.AppConstants;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Preference;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.Model.DataInsta;
import com.videoplayer.fastplayer.gdvideoplayer.Model.Edge;
import com.videoplayer.fastplayer.gdvideoplayer.Model.EdgeSidecarToChildren;
import com.videoplayer.fastplayer.gdvideoplayer.Model.ResponseModel;
import com.videoplayer.fastplayer.gdvideoplayer.Retrofit.APIInterface;
import com.videoplayer.fastplayer.gdvideoplayer.Retrofit.api.CommonClassForAPI;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Instagram.ModelInsta.InstaData;

import com.videoplayer.fastplayer.gdvideoplayer.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.jetbrains.annotations.NotNull;

public class InstaActivity extends BaseActivity {

    //View
    ImageView btn_download, btn_paste;
    EditText et_text;


    //variable
    CommonClassForAPI commonClassForAPI;
    private String PhotoUrl;
    private String VideoUrl;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_insta);
        commonClassForAPI = CommonClassForAPI.getInstance(InstaActivity.this);
        Utils.createFileFolder();
        Declaration();
        Intialization();
    }

    private void Declaration() {

        btn_download = findViewById(R.id.btn_browser);
        btn_paste = findViewById(R.id.btn_paste);
        et_text = findViewById(R.id.et_text);

    }

    private void Intialization() {
        webView = (WebView) findViewById(R.id.webview);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startpastelink();
            }
        }, 100);

        btn_paste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasteText();
            }
        });
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String LINK = et_text.getText().toString().trim();
                if (LINK.equals("")) {
                    Utils.setToast(InstaActivity.this, getResources().getString(R.string.enter_url));
                } else if (!Patterns.WEB_URL.matcher(LINK).matches()) {
                    Utils.setToast(InstaActivity.this, getResources().getString(R.string.enter_valid_url));
                } else {
                    getInstaData();
                }
            }
        });

    }

    private void getInstaData() {

        try {
            Utils.createFileFolder();
            URL url = new URL(et_text.getText().toString());
            String host = url.getHost();
            Log.e("initViews: ", host);
            if (host.equals("www.instagram.com")) {
                callDownload(et_text.getText().toString());
            } else {
                Utils.setToast(InstaActivity.this, getResources().getString(R.string.enter_valid_url));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callDownload(String Url) {
//        Utils.showProgressDialog(this);
//        UserDataGet(Url);
        //   NewInstaApi(Url);
        if (Preference.getInsta_login()) {
            NewInstaApi(Url);
        } else {
            Insta_Login(Url);
        }

    }

    private void NewInstaApi(String url) {

        String[] urlparth = url.split("/");
        String key = urlparth[4];
      //  Toast.makeText(this, "" + key, Toast.LENGTH_SHORT).show();

        String instaurl = "?query_hash=9f8827793ef34641b2fb195d4d41151c&variables=%7B%22shortcode%22%3A%22" + key + "%22%2C%22first%22%3A12%7D";

        //  HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(7000, TimeUnit.SECONDS)
                .connectTimeout(7000, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                 .header("cookie", Preference.getcookie())
//                                .header("cookie", "ig_nrcb=1; mid=YyVyvwALAAE3B5NYqf__FkJ7gnxo; ig_did=C03E241A-1031-420F-BB85-21B5F9AA323C; csrftoken=dDGezmhTZ3Y0G3gXtSYtnu0bRaapZnqX; ds_user_id=51186722227; sessionid=51186722227%3AZk7nVRu1Us6WJy%3A17%3AAYed13IRING1fBKhVZaAM49kYWD4b10jap5imgcG6Q; rur=EAG054511867222270541694943996:01f75a881eaa3393c4244ae16e8bcc215ae69afe270601a11c1c79855dbc171b7a3bf378")
                                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36")
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    }
                }).build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.instagram.com/graphql/query/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        APIInterface apiInterface = retrofit.create(APIInterface.class);

        Call<InstaData> call = apiInterface.getInstaData(instaurl);

        Utils.showProgressDialog(this);
        call.enqueue(new Callback<InstaData>() {
            @Override
            public void onResponse(Call<InstaData> call, Response<InstaData> response) {
                Utils.hideProgressDialog();
                if (response.isSuccessful()) {
//                    Toast.makeText(InstaActivity.this, "data get", Toast.LENGTH_SHORT).show();


                    Data data = response.body().getData();

                    if (data.getShortcodeMedia().getIsVideo()) {
                        VideoUrl = data.getShortcodeMedia().getVideoUrl();
                        Utils.startDownload(VideoUrl, Utils.RootDirectoryInsta, InstaActivity.this, getVideoFilenameFromURL(VideoUrl), AppConstants.DOWNLOAD);
                    } else {
                        PhotoUrl = data.getShortcodeMedia().getDisplayUrl();
                        Utils.startDownload(PhotoUrl, Utils.RootDirectoryInsta, InstaActivity.this, getImageFilenameFromURL(PhotoUrl), AppConstants.DOWNLOAD);
                    }

                    et_text.setText("");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Toast toast = Toast.makeText(InstaActivity.this, "Download start", Toast.LENGTH_SHORT);
                            Log.d("InstaActivity12", "9");
                            Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Download start", Snackbar.LENGTH_LONG);
                            View view = snack.getView();
                            TextView tv = (TextView) view.findViewById(R.id.snackbar_text);
                            tv.setTextColor(getResources().getColor(R.color.white));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            } else {
                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            snack.show();

                            Ads_Interstitial.showAds_full( InstaActivity.this, new Ads_Interstitial.OnFinishAds() {
                                @Override
                                public void onFinishAds(boolean b) {
                                    Intent intent = new Intent(InstaActivity.this, ImageAndVideoListingActivity.class);
                                    intent.putExtra("Name", AppConstants.INSTAGRAM);
                                    startActivity(intent);
                                }
                            });
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<InstaData> call, Throwable t) {
                Utils.hideProgressDialog();
//                Toast.makeText(InstaActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });


    }

    boolean isRedirected = true;
    String cookie;

    private void Insta_Login(String url) {
        CookieManager.getInstance().removeAllCookie();
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setUserAgentString(Utils.USER_AGENT);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new webClient());
        isRedirected = true;
        webView.setVisibility(View.VISIBLE);
        webView.loadUrl("https://www.instagram.com/accounts/login/");
     /*   String[] urlparth = url.split("/");
        String key = urlparth[4];
        String instaurl = "https://www.instagram.com/graphql/query/?query_hash=9f8827793ef34641b2fb195d4d41151c&variables=%7B%22shortcode%22%3A%22" + key + "%22%2C%22first%22%3A12%7D";
        webView.loadUrl(instaurl);*/
    }

    private class webClient extends WebViewClient {
        private webClient() {
        }

        @SuppressLint("WrongConstant")
        public void onPageFinished(WebView webView, String str) {
            Log.d("Instaactivtit12", "onPageFinished = " + str);
            cookie = CookieManager.getInstance().getCookie(str);
            Log.d("cookiess", "socile " + cookie);

            Preference.setcookie(cookie);
            if (cookie != null && cookie.contains("sessionid") && isRedirected) {
                isRedirected = false;
                webView.setVisibility(View.GONE);
                Preference.setInsta_login(true);
            }
        }
    }


    String follower_list = "";

    private void UserDataGet(String urlWithoutQP) {


        class MyJavaScriptInterface {
            @JavascriptInterface
            @SuppressWarnings("unused")
            public void processHTML(String html) {

                Log.d("Webview_data__", "processHTML: " + html);

                follower_list = html.split("<body>")[1].split(">")[1].split("<")[0];

                Log.d("Webview_data__", "follower_list: " + follower_list);

                new AsyncCaller().execute();
            }
        }

        //webView.setVerticalScrollBarEnabled(false);
        // webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setUserAgentString(Utils.USER_AGENT);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                webView.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            }
        });


        String[] urlparth = urlWithoutQP.split("/");
        String key = urlparth[4];
        String instaurl = "https://www.instagram.com/graphql/query/?query_hash=9f8827793ef34641b2fb195d4d41151c&variables=%7B%22shortcode%22%3A%22" + key + "%22%2C%22first%22%3A12%7D";

        webView.loadUrl(instaurl);

        Log.d("Webview_data__", "onCreate: " + webView.getUrl());
    }


    boolean loader = true;

    private class AsyncCaller extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

//            SetUp_Data(follower_list);
            setupnewurl(follower_list);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Utils.hideProgressDialog();

            if (product_type.equals("video")) {
                VideoUrl = local;
                Utils.startDownload(VideoUrl, Utils.RootDirectoryInsta, InstaActivity.this, getVideoFilenameFromURL(VideoUrl), AppConstants.DOWNLOAD);
            } else {
                PhotoUrl = local;
                Utils.startDownload(PhotoUrl, Utils.RootDirectoryInsta, InstaActivity.this, getImageFilenameFromURL(PhotoUrl), AppConstants.DOWNLOAD);
            }

           /* if (flag) {

            }else{
                Toast.makeText(InstaActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                return;
            }*/
            et_text.setText("");


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                            Toast toast = Toast.makeText(InstaActivity.this, "Download start", Toast.LENGTH_SHORT);
                    Log.d("InstaActivity12", "9");
                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Download start", Snackbar.LENGTH_LONG);
                    View view = snack.getView();
                    TextView tv = (TextView) view.findViewById(R.id.snackbar_text);
                    tv.setTextColor(getResources().getColor(R.color.white));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    } else {
                        tv.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    snack.show();

                    Ads_Interstitial.showAds_full(InstaActivity.this, new Ads_Interstitial.OnFinishAds() {
                        @Override
                        public void onFinishAds(boolean b) {
                            Intent intent = new Intent(InstaActivity.this, ImageAndVideoListingActivity.class);
                            intent.putExtra("Name", AppConstants.INSTAGRAM);
                            startActivity(intent);
                        }
                    });
                }
            });
        }

    }

    String local;

    private void setupnewurl(String follower_list) {

        Gson gson = new Gson();

        InstaData data = gson.fromJson(follower_list, InstaData.class);

        if (data.getData().getShortcodeMedia().getIsVideo()) {
            product_type = "video";
            local = data.getData().getShortcodeMedia().getVideoUrl();
        } else {
            product_type = "image";
            local = data.getData().getShortcodeMedia().getDisplayUrl();
        }




      /*  flag = true;
        Gson gson = new Gson();
        NewDataInsta newDataInsta = gson.fromJson(follower_list, NewDataInsta.class);

        if (newDataInsta != null) {
            if (newDataInsta.graphql.getShortcode_media().isIs_video()) {
                local = newDataInsta.graphql.getShortcode_media().getVideo_url().replace("amp;", "");
                Log.d("image_video_insta", "setupnewurl video: " + local);
            } else {
                local = newDataInsta.graphql.getShortcode_media().getDisplay_url().replace("amp;", "");
                Log.d("image_video_insta", "setupnewurl photo: " + local);
            }



        }*/


    }

    boolean flag = false;
    String product_type = "";

    private void SetUp_Data(String follower_list) {

        try {
            flag = true;
            Gson gson = new Gson();
            DataInsta dataInsta = gson.fromJson(follower_list, DataInsta.class);
            Log.d("Webview_data__", "data get");
            if (dataInsta != null) {

                product_type = dataInsta.items.get(0).product_type;
                Log.d("Webview_data__", "product_type = " + dataInsta.items.get(0).product_type);
                if (dataInsta.items.get(0).product_type.equals("clips")) {


                    Log.d("Webview_data__", "dataInsta: " + dataInsta.items.get(0).videoVersions.get(0).url);
                    VideoUrl = dataInsta.items.get(0).videoVersions.get(0).url.replace("amp;", "");
                    Log.d("Webview_data__", "Video URL = " + VideoUrl);
                } else if (product_type.equals("carousel_container")) {
                    Log.d("Webview_data__", "carousel_container = " + dataInsta.items.get(0).image_versions2);
                    if (dataInsta.items.get(0).carousel_media.get(0) != null) {
                        String imageURL = dataInsta.items.get(0).carousel_media.get(0).image_versions2.candidatesList.get(0).url;
                        PhotoUrl = imageURL.replace("amp;", "");
                        Log.d("Webview_data__", "Image Url = " + PhotoUrl);
                    }
                } else {
                    Log.d("Webview_data__", "image_versions2 = " + dataInsta.items.get(0).image_versions2);
                    if (dataInsta.items.get(0).image_versions2 != null) {
                        String imageURL = dataInsta.items.get(0).image_versions2.candidatesList.get(0).url;
                        PhotoUrl = imageURL.replace("amp;", "");
                        Log.d("Webview_data__", "Image Url = " + PhotoUrl);
                    }
                }

            } else {
                flag = false;
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }

        } catch (NullPointerException e) {
            flag = false;
            e.printStackTrace();
        }

    }


    private DisposableObserver<JsonObject> instaObserver = new DisposableObserver<JsonObject>() {
        @Override
        public void onNext(JsonObject versionList) {
            Utils.hideProgressDialog();
            try {
                Log.d("InstaActivity12", "1 = " + versionList.toString());
                Type listType = new TypeToken<ResponseModel>() {
                }.getType();
                Log.d("InstaActivity12", "2");
                ResponseModel responseModel = new Gson().fromJson(versionList.toString(), listType);
                EdgeSidecarToChildren edgeSidecarToChildren = responseModel.getGraphql().getShortcode_media().getEdge_sidecar_to_children();
                Log.d("InstaActivity12", "3");
                if (edgeSidecarToChildren != null) {
                    Log.d("InstaActivity12", "4");
                    List<Edge> edgeArrayList = edgeSidecarToChildren.getEdges();
                    for (int i = 0; i < edgeArrayList.size(); i++) {
                        Log.d("InstaActivity12", "5");
//                        if (i == 0) {
                        if (edgeArrayList.get(i).getNode().isIs_video()) {
                            Log.d("InstaActivity12", "6");
                            VideoUrl = edgeArrayList.get(i).getNode().getVideo_url();
                            Utils.startDownload(VideoUrl, Utils.RootDirectoryInsta, InstaActivity.this, getVideoFilenameFromURL(VideoUrl), AppConstants.DOWNLOAD);
                            et_text.setText("");
                            VideoUrl = "";

                        } else {
                            Log.d("InstaActivity12", "7");
                            PhotoUrl = edgeArrayList.get(i).getNode().getDisplay_resources().get(edgeArrayList.get(i).getNode().getDisplay_resources().size() - 1).getSrc();
                            Utils.startDownload(PhotoUrl, Utils.RootDirectoryInsta, InstaActivity.this, getImageFilenameFromURL(PhotoUrl), AppConstants.DOWNLOAD);
                            PhotoUrl = "";
                            et_text.setText("");
                        }
//                        }
                    }
                    Log.d("InstaActivity12", "8");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Toast toast = Toast.makeText(InstaActivity.this, "Download start", Toast.LENGTH_SHORT);
                            Log.d("InstaActivity12", "9");
                            Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Download start", Snackbar.LENGTH_LONG);
                            View view = snack.getView();
                            TextView tv = (TextView) view.findViewById(R.id.snackbar_text);
                            tv.setTextColor(getResources().getColor(R.color.white));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            } else {
                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            snack.show();

                            Ads_Interstitial.showAds_full(InstaActivity.this, new Ads_Interstitial.OnFinishAds() {
                                @Override
                                public void onFinishAds(boolean b) {
                                    Intent intent = new Intent(InstaActivity.this, ImageAndVideoListingActivity.class);
                                    intent.putExtra("Name", AppConstants.INSTAGRAM);
                                    startActivity(intent);
                                }
                            });
                        }
                    });

                } else {
                    Log.d("InstaActivity12", "10");
                    boolean isVideo = responseModel.getGraphql().getShortcode_media().isIs_video();
                    Log.d("InstaActivity12", "11");
                    if (isVideo) {
                        Log.d("InstaActivity12", "12");
                        VideoUrl = responseModel.getGraphql().getShortcode_media().getVideo_url();
                        //new DownloadFileFromURL().execute(VideoUrl,getFilenameFromURL(VideoUrl));
                        Utils.startDownload(VideoUrl, Utils.RootDirectoryInsta, InstaActivity.this, getVideoFilenameFromURL(VideoUrl), AppConstants.DOWNLOAD);
                        VideoUrl = "";
                        et_text.setText("");
                    } else {
                        Log.d("InstaActivity12", "13");
                        PhotoUrl = responseModel.getGraphql().getShortcode_media().getDisplay_resources()
                                .get(responseModel.getGraphql().getShortcode_media().getDisplay_resources().size() - 1).getSrc();

                        Utils.startDownload(PhotoUrl, Utils.RootDirectoryInsta, InstaActivity.this, getImageFilenameFromURL(PhotoUrl), AppConstants.DOWNLOAD);
                        PhotoUrl = "";
                        et_text.setText("");
                        // new DownloadFileFromURL().execute(PhotoUrl,getFilenameFromURL(PhotoUrl));
                    }
                    Log.d("InstaActivity12", "14");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("InstaActivity12", "15");
                            Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Download start", Snackbar.LENGTH_LONG);
                            View view = snack.getView();
                            TextView tv = (TextView) view.findViewById(R.id.snackbar_text);
                            tv.setTextColor(getResources().getColor(R.color.white));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            } else {
                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                            snack.show();

                            Ads_Interstitial.showAds_full(InstaActivity.this, new Ads_Interstitial.OnFinishAds() {
                                @Override
                                public void onFinishAds(boolean b) {
                                    Intent intent = new Intent(InstaActivity.this, ImageAndVideoListingActivity.class);
                                    intent.putExtra("Name", AppConstants.INSTAGRAM);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }

            } catch (Exception e) {
                Log.d("InstaActivity12", "17");
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.d("InstaActivity12", "18");
            Utils.hideProgressDialog();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("InstaActivity12", "19");
                    Toast.makeText(InstaActivity.this, "Something went wrong! or The account is private ", Toast.LENGTH_SHORT).show();
                }
            });
            Log.d("InstaActivity12", "20");
            e.printStackTrace();
        }

        @Override
        public void onComplete() {
            Utils.hideProgressDialog();
        }
    };


    public String getImageFilenameFromURL(String url) {
        try {
            return new File(new URL(url).getPath().toString()).getName();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return System.currentTimeMillis() + ".png";
        }
    }

    public String getVideoFilenameFromURL(String url) {
        try {
            return new File(new URL(url).getPath().toString()).getName();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return System.currentTimeMillis() + ".mp4";
        }
    }


    private String getUrlWithoutParameters(String url) {
        try {
            URI uri = new URI(url);
            return new URI(uri.getScheme(),
                    uri.getAuthority(),
                    uri.getPath(),
                    null, // Ignore the query part of the input url
                    uri.getFragment()).toString();
        } catch (Exception e) {
            e.printStackTrace();
            Utils.setToast(InstaActivity.this, getResources().getString(R.string.enter_valid_url));
            return "";
        }
    }


    private void PasteText() {
        try {
            et_text.setText("");
            ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            if (manager != null && manager.getPrimaryClip() != null && manager.getPrimaryClip().getItemCount() > 0) {
                et_text.setText(manager.getPrimaryClip().getItemAt(0).getText().toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startpastelink() {
        try {
            et_text.setText("");
            ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            if (manager != null && manager.getPrimaryClip() != null && manager.getPrimaryClip().getItemCount() > 0) {
                if (manager.getPrimaryClip().getItemAt(0).getText().toString().contains("www.instagram.com")) {
                    et_text.setText(manager.getPrimaryClip().getItemAt(0).getText().toString());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {

        Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                finish();
            }
        });

    }
}