package com.videoplayer.fastplayer.gdvideoplayer.Activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.ads.adsdemosp.AdsClass.Ads_ExitNativeFull;
import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.ForwardToSettingsCallback;
import com.permissionx.guolindev.callback.RequestCallback;
import com.permissionx.guolindev.request.ForwardScope;

import com.videoplayer.fastplayer.gdvideoplayer.BuildConfig;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Preference;
import com.videoplayer.fastplayer.gdvideoplayer.R;

import java.util.List;

import static com.videoplayer.fastplayer.gdvideoplayer.Activity.LetsgoActivity.Exit_Dialog;

public class StartActivity extends BaseActivity {

    //View
    Button iv_start;
    LinearLayout iv_rate, iv_share, iv_privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Declaration();
        Intialization();

    }

    private void Declaration() {
        iv_start = findViewById(R.id.iv_start);


        iv_rate = findViewById(R.id.iv_rate);
        iv_share = findViewById(R.id.iv_share);
        iv_privacy = findViewById(R.id.iv_privacy);

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void Intialization() {

        iv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (hasPermissions(StartActivity.this, permissions)) {
                    onPermissionGranted();
                } else {
                    requestStoragePermission();
                }


            }
        });

        iv_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Preference.getprivacy_policy().isEmpty()) {
//                    Appcontroller.fast_start = true;
                    String urlString = Preference.getprivacy_policy();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setPackage("com.android.chrome");
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        // Chrome browser presumably not installed so allow user to choose instead
                        intent.setPackage(null);
                        startActivity(intent);
                    }
                }
            }
        });

        iv_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Appcontroller.fast_start = true;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID));
                startActivity(intent);
            }
        });

        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Appcontroller.fast_start = true;
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }

            }
        });

    }


    String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA"};
    private static final int requestSettingResponseCode = 2123;

    private void requestStoragePermission() {

        PermissionX.init(StartActivity.this)
                .permissions(permissions)
                .onForwardToSettings(new ForwardToSettingsCallback() {
                    @Override
                    public void onForwardToSettings(@NonNull ForwardScope scope, @NonNull List<String> deniedList) {
                        scope.showForwardToSettingsDialog(deniedList, "Core fundamental are based on these permissions",
                                "OK", "Cancel");
                    }
                }).request(new RequestCallback() {
            @Override
            public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                if (allGranted) {
//                    onPermissionGranted();
                }
            }
        });

    }

    private void onPermissionGranted() {


        Ads_Interstitial.showAds_full(StartActivity.this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                startActivity(new Intent(StartActivity.this, MainVideoScreen.class));
            }
        });

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                if (Ads.adsclick == Preference.getads_click()) {
                    Ads.adsclick = 0;
                    Ads.showAds(StartActivity.this, new Ads.OnFinishAds() {
                        @Override
                        public void onFinishAds(boolean b) {
                            startActivity(new Intent(StartActivity.this, MainActivity.class));
                        }
                    });
                } else {
                    Ads.adsclick++;
                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                }
            } else {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, requestSettingResponseCode);
            }
        } else {

            if (Ads.adsclick == Preference.getads_click()) {
                Ads.adsclick = 0;
                Ads.showAds(StartActivity.this, new Ads.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity(new Intent(StartActivity.this, MainActivity.class));
                    }
                });
            } else {
                Ads.adsclick++;
                startActivity(new Intent(StartActivity.this, MainActivity.class));
            }
        }*/
    }

    private void permissionrequiredDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Required");
        builder.setMessage("App won't run without Manage Stoarge Permission ");
        builder.setPositiveButton("Grant", (dialog, which) -> {
            dialog.cancel();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            Uri uri = Uri.fromParts("package", this.getPackageName(), null);
            intent.setData(uri);
            startActivityForResult(intent, requestSettingResponseCode);
        });

        builder.setCancelable(true);
        try {
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestSettingResponseCode) {
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
//                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText((Context) this, "Permission Granted", Toast.LENGTH_SHORT).show();
//                onPermissionGranted();
//            } else {
//                Toast.makeText((Context) this, "Permission NOT Granted", Toast.LENGTH_SHORT).show();
//                permissionrequiredDialog();
//            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    onPermissionGranted();
                } else {
                    permissionrequiredDialog();
                }
            }


        } else {
            Toast.makeText((Context) this, "Invalid Response Code", Toast.LENGTH_SHORT).show();
            permissionrequiredDialog();
        }
    }


    /*@Override
    public void onBackPressed() {
        if (Ads.backclick == Preference.getback_click()) {
            Ads.backclick = 0;
            Ads.showAds(StartActivity.this, new Ads.OnFinishAds() {
                @Override
                public void onFinishAds(boolean b) {
                    finish();
                }
            });
        } else {
            Ads.backclick++;
            finish();
        }
    }*/

    boolean exit_flag = false;

    @Override
    public void onBackPressed() {
        if (Preference.getScreen_show() == 1) {
            if (Ads_ExitNativeFull.checkExitAdsLoaded()) {
                Exit_Dialog(StartActivity.this);
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

      /*  if (Ads_ExitNativeFull.checkExitAdsLoaded()) {
            Exit_Dialog(StartActivity.this);
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
        }*/
      /*  Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                finish();
            }
        });
*/
    }


}