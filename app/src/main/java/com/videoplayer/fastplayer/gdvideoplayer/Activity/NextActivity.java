package com.videoplayer.fastplayer.gdvideoplayer.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.documentfile.provider.DocumentFile;

import com.ads.adsdemosp.AdsClass.Ads_ExitNativeFull;
import com.ads.adsdemosp.AdsClass.Ads_Interstitial;

import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Preference;
import com.videoplayer.fastplayer.gdvideoplayer.R;

import static com.videoplayer.fastplayer.gdvideoplayer.Activity.LetsgoActivity.Exit_Dialog;

public class NextActivity extends BaseActivity {

    Button iv_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Declaration();
        Intialization();
    }


    private void Declaration() {
        iv_next = findViewById(R.id.iv_next);
    }

    private void Intialization() {

        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ads_Interstitial.showAds_full(NextActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity(new Intent(NextActivity.this, StartActivity.class));
                    }
                });

            }
        });
    }

    boolean exit_flag = false;

    @Override
    public void onBackPressed() {
        if (Preference.getScreen_show() == 2) {
            if (Ads_ExitNativeFull.checkExitAdsLoaded()) {
                Exit_Dialog(NextActivity.this);
            } else {
                if (exit_flag) {
                    finishAffinity();
                } else {
                    exit_flag = true;
                    Toast.makeText(this, "Please tap again!", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            exit_flag = false;
                        }
                    }, 3000);
                }
            }
        } else {
            Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
                @Override
                public void onFinishAds(boolean b) {
                    finish();
                }
            });
        }

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("NextActivity12", "onActivityResult");
        if (resultCode == RESULT_OK) {
            Log.d("NextActivity12", "RESULT_OK");
            if (requestCode == 42) {
                Log.d("NextActivity12", "42");
                if (data != null) {
                    Log.d("NextActivity12", "data");
                    Uri uri = data.getData();
                    Log.d("NextActivity12", "uri = " + uri);
                    if (uri.getPath().endsWith(".Statuses")) {
                        Log.d("TAG", "onActivityResult: " + uri.getPath());
                      /*  final int takeFlags = data.getFlags()
                                & (Intent.FLAG_GRANT_READ_URI_PERMISSION);*/
                        final int takeFlags = data.getFlags()
                                & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            Log.d("NextActivity12", "KITKAT = ");
                            getContentResolver().takePersistableUriPermission(uri, takeFlags);
                        }

                        DocumentFile fromTreeUri = DocumentFile.fromTreeUri(NextActivity.this, uri);
                        if (Build.VERSION.SDK_INT >= 29) {
                            DocumentFile[] documentFiles = fromTreeUri.listFiles();
                            Log.d("NextActivity12", "allow " + documentFiles.length);
                        }

                        Toast.makeText(this, "Permission allow", Toast.LENGTH_SHORT).show();

                    } else {
                        // dialog when user gave wrong path
                        Toast.makeText(this, "Permission not allow", Toast.LENGTH_SHORT).show();
                        //showWrongPathDialog();
                    }

                }
            }
        }
    }


}