package com.videoplayer.fastplayer.gdvideoplayer.connection;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.LetsgoActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.NextActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.StartActivity;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Preference;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;

public class Privacy_Policy_Screen extends BaseActivity {

    //    WebView webView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy__policy__screen);

        //webView = (WebView) findViewById(R.id.pp);
        textView = (TextView) findViewById(R.id.txt_privacy);
        TextView next = (TextView) findViewById(R.id.next);
        //UserDataGet();
        Utils.isConnectingToInternet(Privacy_Policy_Screen.this, new Utils.OnCheckNet() {
            @Override
            public void OnCheckNet(boolean b) {
                if (b) {
                  /*  webView.setWebViewClient(new MyWebViewClient());
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.getSettings().setDisplayZoomControls(true);*/
                    try {
                        if (Preference.getPrivacy_policy_html() != null) {
                            textView.setMovementMethod(LinkMovementMethod.getInstance());
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                textView.setText(Html.fromHtml(Preference.getPrivacy_policy_html(), Html.FROM_HTML_MODE_COMPACT));
                            } else {
                                textView.setText(Html.fromHtml(Preference.getPrivacy_policy_html()));
                            }

                        } else {
                            Toast.makeText(Privacy_Policy_Screen.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NullPointerException n) {
                        n.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //webView.loadUrl("file:///android_asset/pp.html");

                } else {
                    finishAffinity();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ads_Interstitial.showAds_full(Privacy_Policy_Screen.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        if (Preference.getScreen_show() > 3) {
                            //fromstart = 3;
                            startActivity(new Intent(Privacy_Policy_Screen.this, LetsgoActivity.class));
                            finish();
                        } else if (Preference.getScreen_show() == 3) {
                            //   fromstart = 3;
                            startActivity(new Intent(Privacy_Policy_Screen.this, LetsgoActivity.class));
                            finish();
                        } else  if (Preference.getScreen_show() == 2) {
                            //   fromstart = 3;
                            startActivity(new Intent(Privacy_Policy_Screen.this, NextActivity.class));
                            finish();
                        } else if (Preference.getScreen_show() == 1) {
                            //  fromstart = 4;
                            startActivity(new Intent(Privacy_Policy_Screen.this, StartActivity.class));
                            finish();
                        } else {
                            //fromstart = 5;
                            startActivity(new Intent(Privacy_Policy_Screen.this, StartActivity.class));
                            finish();
                        }
                    }
                });

            }
        });
    }

}