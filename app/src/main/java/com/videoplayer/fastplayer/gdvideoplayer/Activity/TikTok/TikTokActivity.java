package com.videoplayer.fastplayer.gdvideoplayer.Activity.TikTok;

import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Download.ImageAndVideoListingActivity;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.AppConstants;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;
import com.videoplayer.fastplayer.gdvideoplayer.Retrofit.api.CommonClassForAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class TikTokActivity extends BaseActivity {

    EditText et_text;
    TextView btn_download ,btn_paste;


    CommonClassForAPI commonClassForAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_tik_tok);

        commonClassForAPI = CommonClassForAPI.getInstance(TikTokActivity.this);
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
                String LL = et_text.getText().toString();
                if (LL.equals("")) {
                    Utils.setToast(TikTokActivity.this, getResources().getString(R.string.enter_url));
                } else {

                    if (LL.contains("tiktok")) {
                        String url = et_text.getText().toString().trim();
                        Download_url_tiktok(url);

                    } else {
                        Utils.setToast(TikTokActivity.this, getResources().getString(R.string.enter_valid_url));
                    }

                }
            }
        });

    }


    private void Download_url_tiktok(String url) {

        Utils.showProgressDialog(TikTokActivity.this);


        PageService pageService = ApiClientTikTok.getClient().create(PageService.class);

        Call<TikTokResponce> responceCall = pageService.Call_TikTokAPi("check.php?v=" + url);

        responceCall.enqueue(new Callback<TikTokResponce>() {
            @Override
            public void onResponse(Call<TikTokResponce> call, Response<TikTokResponce> response) {
                Log.d("Tik_responce", new Gson().toJson(response.body()));
                Utils.hideProgressDialog();

                et_text.setText("");
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equals("2")) {

                        Utils.startDownload(response.body().getDownload_url(), Utils.RootTikTOk, TikTokActivity.this, "tiktok_" + System.currentTimeMillis() + ".mp4", AppConstants.DOWNLOAD);

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
                        Ads_Interstitial.showAds_full( TikTokActivity.this, new Ads_Interstitial.OnFinishAds() {
                            @Override
                            public void onFinishAds(boolean b) {
                                Intent intent = new Intent(TikTokActivity.this, ImageAndVideoListingActivity.class);
                                intent.putExtra("Name", AppConstants.TIKTOK);
                                startActivity(intent);
                            }
                        });

                    } else {
                        Toast.makeText(TikTokActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TikTokActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TikTokResponce> call, Throwable t) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utils.hideProgressDialog();
                        et_text.setText("");
                        Toast.makeText(TikTokActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }

    interface PageService {
        //  VideoUrl = result.getAllElements().get(0).data().split("contentUrl")[1].split(",")[0].split("\":",4)[1].replace("\"","");
        @GET()
        Call<TikTokResponce> Call_TikTokAPi(@Url String url);
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
                if (manager.getPrimaryClip().getItemAt(0).getText().toString().contains("tiktok.com")) {
                    et_text.setText(manager.getPrimaryClip().getItemAt(0).getText().toString());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {

        Ads_Interstitial.BackshowAds_full( this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                finish();
            }
        });

    }

}