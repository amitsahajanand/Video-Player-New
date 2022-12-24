package com.videoplayer.fastplayer.gdvideoplayer.Activity.Facebook;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Download.ImageAndVideoListingActivity;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.AppConstants;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils.RootDirectoryFacebook;
import static com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils.startDownload;


public class NewFaceBookActivity extends BaseActivity {
    //VIew
    ImageView  iv_open_fb, iv_download;
    EditText et_text;
    TextView btn_paste, btn_browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_face_book);
        Declaration();
        Intialization();
    }

    private void Declaration() {

        et_text = findViewById(R.id.et_text);
        btn_paste = findViewById(R.id.btn_paste);
        btn_browser = findViewById(R.id.btn_browser);
        iv_open_fb = findViewById(R.id.iv_open_fb);
        iv_download = findViewById(R.id.iv_download);


    }

    private void Intialization() {

        btn_browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloaderVideo();

            }
        });

        btn_paste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasteText();
            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startpastelink();
            }
        }, 100);


        iv_open_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.OpenApp(NewFaceBookActivity.this, "com.facebook.katana");
            }
        });

        iv_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ads_Interstitial.showAds_full(NewFaceBookActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        Intent intent = new Intent(NewFaceBookActivity.this, ImageAndVideoListingActivity.class);
                        intent.putExtra("Name", AppConstants.FACEBOOK);
                        startActivity(intent);
                    }
                });


            }
        });

    }


    private void DownloaderVideo() {
        String LL = et_text.getText().toString().trim();
        //Log.d("fb_url", LL);
        if (LL.equals("")) {
            Toast.makeText(this, "Enter Url", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.WEB_URL.matcher(LL).matches()) {
            Toast.makeText(this, "Enter Valid Url", Toast.LENGTH_SHORT).show();
        } else {
            if (LL.contains("facebook") || LL.contains("fb")) {
//                        getDataFromUrl(et_text.getText().toString().trim());

                new Data().execute(et_text.getText().toString().trim());
                et_text.setText("");


            } else {
                Toast.makeText(this, "Enter Valid Url", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String VideoTitle;

    @SuppressLint("StaticFieldLeak")
    private class Data extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection;
            BufferedReader reader;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                String buffer = "No URL";
                String Line;
                while ((Line = reader.readLine()) != null) {
                    Log.d("doInBackground23", "Line =1  " + Line);
                    Log.d("doInBackground23", "reader.readLine() " + reader.readLine());
                    if (Line.contains("https:\\/\\/video.")) {
                        Log.d("doInBackground23", "Line =2 =  " + Line);
                        Line = Line.substring(Line.indexOf("https:\\/\\/video."));
                        Line = Line.split("&quot;")[0];
                        if (Line.contains("\\")) {
                            Log.d("doInBackground23", "Line =3 =  " + Line);
                            Line = Line.replace("\\", "");
                        }
                        if (Line.contains("amp;")) {
                            Log.d("doInBackground23", "Line =4 =  " + Line);
                            Line = Line.replace("amp;", "");
                        }
                        if (!Line.contains("https")) {
                            Log.d("doInBackground23", "Line =5 =  " + Line);
                            Line = Line.replace("http", "https");
                        }
                        buffer = Line;
                        break;
                    } else {
                        buffer = "No URL";
                    }
                }
                return buffer;
            } catch (IOException e) {
                return "No URL";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Utils.hideProgressDialog();
            Log.d("InstaActivity12", "9tostrinmg" + s);
            if (!s.contains("No URL")) {
                String guessFileName = URLUtil.guessFileName(s, null, MimeTypeMap.getFileExtensionFromUrl(s));
                startDownload(s, RootDirectoryFacebook, NewFaceBookActivity.this, guessFileName, AppConstants.DOWNLOAD);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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
                    }
                });
                Ads_Interstitial.showAds_full(NewFaceBookActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity(new Intent(NewFaceBookActivity.this, ImageAndVideoListingActivity.class).putExtra("Name", AppConstants.FACEBOOK));

                    }
                });

//                startActivity(new Intent(NewFaceBookActivity.this, ImageAndVideoListingActivity.class).putExtra("Name", AppConstants.FACEBOOK));


            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                Looper.prepare();
                        Toast.makeText(NewFaceBookActivity.this, "Wrong Video URL or Check Internet Connection", Toast.LENGTH_SHORT).show();
//                Looper.loop();
                    }
                });
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Utils.showProgressDialog(NewFaceBookActivity.this);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Utils.hideProgressDialog();
            Looper.prepare();
            Toast.makeText(NewFaceBookActivity.this, "Video Can't be downloaded! Try Again", Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    private static int ordinalIndexOf(String str, String substr, int n) {
        int pos = -1;
        do {
            pos = str.indexOf(substr, pos + 1);
        } while (n-- > 0 && pos != -1);
        return pos;
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
                if (manager.getPrimaryClip().getItemAt(0).getText().toString().contains("fb") || manager.getPrimaryClip().getItemAt(0).getText().toString().contains("facebook")) {
                    et_text.setText(manager.getPrimaryClip().getItemAt(0).getText().toString());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {

        Ads_Interstitial.BackshowAds_full(NewFaceBookActivity.this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                finish();
            }
        });

    }
}