package com.videoplayer.fastplayer.gdvideoplayer.Activity.Twitter;

import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.reactivex.observers.DisposableObserver;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Download.ImageAndVideoListingActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.AppConstants;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.Model.TwitterResponse;
import com.videoplayer.fastplayer.gdvideoplayer.R;
import com.videoplayer.fastplayer.gdvideoplayer.Retrofit.api.CommonClassForAPI;

public class TwitterActivity extends BaseActivity {

    //View
    EditText et_text;
    TextView btn_download ,btn_paste;


    CommonClassForAPI commonClassForAPI;
    private String VideoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);
        commonClassForAPI = CommonClassForAPI.getInstance(TwitterActivity.this);
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
                String LINK = et_text.getText().toString().trim();
                if (LINK.equals("")) {
                    Utils.setToast(TwitterActivity.this, getResources().getString(R.string.enter_url));
                } else if (!Patterns.WEB_URL.matcher(LINK).matches()) {
                    Utils.setToast(TwitterActivity.this, getResources().getString(R.string.enter_valid_url));
                } else {
                    GetTwitterData();
                }
            }
        });

    }

    private void GetTwitterData() {
        try {
            Utils.createFileFolder();
            URL url = new URL(et_text.getText().toString());
            String host = url.getHost();
            if (host.contains("twitter.com")) {
                Long id = getTweetId(et_text.getText().toString());
                if (id != null) {
                    callGetTwitterData(String.valueOf(id));
                }
            } else {
                Utils.setToast(TwitterActivity.this, getResources().getString(R.string.enter_valid_url));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callGetTwitterData(String id) {
        String URL_LINK = "https://twittervideodownloaderpro.com/twittervideodownloadv2/index.php";
        try {
            Utils utils = new Utils(TwitterActivity.this);
            if (utils.isNetworkAvailable()) {
                if (commonClassForAPI != null) {
                    Utils.showProgressDialog(TwitterActivity.this);
                    commonClassForAPI.callTwitterApi(observer, URL_LINK, id);
                }
            } else {
//                Utils.setToast(TwitterActivity.this, getResources().getString(R.string.no_net_conn));
                Toast.makeText(this, "" + getResources().getString(R.string.no_net_conn), Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private DisposableObserver<TwitterResponse> observer = new DisposableObserver<TwitterResponse>() {
        @Override
        public void onNext(TwitterResponse twitterResponse) {
            Utils.hideProgressDialog();
            try {
                VideoUrl = twitterResponse.getVideos().get(0).getUrl();
                if (twitterResponse.getVideos().get(0).getType().equals("image")) {
                    Utils.startDownload(VideoUrl, Utils.RootTwitter, TwitterActivity.this, getFilenameFromURL(VideoUrl, "image"), AppConstants.DOWNLOAD);
                    et_text.setText("");
                } else {
                    VideoUrl = twitterResponse.getVideos().get(twitterResponse.getVideos().size() - 1).getUrl();
                    Utils.startDownload(VideoUrl, Utils.RootTwitter, TwitterActivity.this, getFilenameFromURL(VideoUrl, "mp4"), AppConstants.DOWNLOAD);
                    et_text.setText("");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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
                        Ads_Interstitial.showAds_full(TwitterActivity.this, new Ads_Interstitial.OnFinishAds() {
                            @Override
                            public void onFinishAds(boolean b) {
                                Intent intent = new Intent(TwitterActivity.this, ImageAndVideoListingActivity.class);
                                intent.putExtra("Name", AppConstants.TWITTER);
                                startActivity(intent);
                            }
                        });
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utils.setToast(TwitterActivity.this, getResources().getString(R.string.no_media_on_tweet));
                    }
                });

            }
        }

        @Override
        public void onError(Throwable e) {
            Utils.hideProgressDialog();
            e.printStackTrace();

        }

        @Override
        public void onComplete() {
            Utils.hideProgressDialog();
        }
    };

    public String getFilenameFromURL(String url, String type) {
        if (type.equals("image")) {
            try {
                return new File(new URL(url).getPath()).getName() + "";
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return System.currentTimeMillis() + ".jpg";
            }
        } else {
            try {
                return new File(new URL(url).getPath()).getName() + "";
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return System.currentTimeMillis() + ".mp4";
            }
        }
    }

    private Long getTweetId(String s) {
        try {
            String[] split = s.split("\\/");
            String id = split[5].split("\\?")[0];
            return Long.parseLong(id);
        } catch (Exception e) {
            Log.d("TAG", "getTweetId: " + e.getLocalizedMessage());
            return null;
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
                if (manager.getPrimaryClip().getItemAt(0).getText().toString().contains("twitter.com")) {
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