package com.videoplayer.fastplayer.gdvideoplayer.Activity.ShareChat;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.google.android.material.snackbar.Snackbar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Download.ImageAndVideoListingActivity;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.AppConstants;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;
import com.videoplayer.fastplayer.gdvideoplayer.Retrofit.api.CommonClassForAPI;

import static android.content.ContentValues.TAG;

public class ShareChatDownLoadActivity extends BaseActivity {

    //View
    TextView btn_download ,btn_paste;
    EditText et_text;


    CommonClassForAPI commonClassForAPI;

    private String VideoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_chat_down_load);

        commonClassForAPI = CommonClassForAPI.getInstance(ShareChatDownLoadActivity.this);
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
                    Utils.setToast(ShareChatDownLoadActivity.this, getResources().getString(R.string.enter_url));
                } else if (!Patterns.WEB_URL.matcher(LL).matches()) {
                    Utils.setToast(ShareChatDownLoadActivity.this, getResources().getString(R.string.enter_valid_url));
                } else {
//            Utils.showProgressDialog(ShareChatDownLoadActivity.this);
                    GetSharechatData();
                }

            }
        });

    }

    private void GetSharechatData() {
        try {
            Utils.createFileFolder();
            URL url = new URL(et_text.getText().toString());
            String host = url.getHost();
            if (host.contains("sharechat")) {
                Utils.showProgressDialog(ShareChatDownLoadActivity.this);
                new callGetShareChatData().execute(et_text.getText().toString());

            } else {
                Utils.setToast(ShareChatDownLoadActivity.this, getResources().getString(R.string.enter_valid_url));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class callGetShareChatData extends AsyncTask<String, Void, Document> {

        Document ShareChatDoc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Document doInBackground(String... urls) {
            try {
                ShareChatDoc = Jsoup.connect(urls[0]).get();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "doInBackground: Error");
            }
            return ShareChatDoc;
        }

        protected void onPostExecute(Document result) {
            Utils.hideProgressDialog();
            try {
//                VideoUrl = result.select("meta[property=\"og:simplevideoshow:secure_url\"]").last().attr("content");

                VideoUrl = result.getAllElements().get(0).data().split("contentUrl")[1].split(",")[0].split("\":",4)[1].replace("\"","");
                Log.e("onPostExecute: ", VideoUrl);
                if (!VideoUrl.equals("")) {
                    try {
                        Utils.startDownload(VideoUrl, Utils.RootSharechat, ShareChatDownLoadActivity.this, "sharechat_" + System.currentTimeMillis() + ".mp4", AppConstants.DOWNLOAD);
                        VideoUrl = "";
                        et_text.setText("");
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
                        Ads_Interstitial.showAds_full(ShareChatDownLoadActivity.this, new Ads_Interstitial.OnFinishAds() {
                            @Override
                            public void onFinishAds(boolean b) {
                                Intent intent = new Intent(ShareChatDownLoadActivity.this, ImageAndVideoListingActivity.class);
                                intent.putExtra("Name", AppConstants.SHARECHAT);
                                startActivity(intent);
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(ShareChatDownLoadActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
                Toast.makeText(ShareChatDownLoadActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
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
                if (manager.getPrimaryClip().getItemAt(0).getText().toString().contains("sharechat")) {
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