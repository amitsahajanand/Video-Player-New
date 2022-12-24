//package com.picturemanage.privateiimage.gallery20.ExtraClass;
//
//import android.animation.ObjectAnimator;
//import android.animation.PropertyValuesHolder;
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.res.ColorStateList;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.net.ConnectivityManager;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.animation.Animation;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RatingBar;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//
//import com.facebook.ads.Ad;
//import com.facebook.ads.AdOptionsView;
//import com.facebook.ads.InterstitialAdListener;
//import com.facebook.ads.MediaView;
//import com.facebook.ads.NativeAdLayout;
//import com.facebook.ads.NativeAdListener;
//import com.google.android.gms.ads.AdError;
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdLoader;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.FullScreenContentCallback;
//import com.google.android.gms.ads.LoadAdError;
//import com.google.android.gms.ads.VideoController;
//import com.google.android.gms.ads.appopen.AppOpenAd;
//import com.google.android.gms.ads.interstitial.InterstitialAd;
//import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
//import com.google.android.gms.ads.nativead.NativeAd;
//import com.google.android.gms.ads.nativead.NativeAdView;
//import com.picturemanage.privateiimage.gallery20.R;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Random;
//
//import pl.droidsonroids.gif.GifImageView;
//
//import static com.picturemanage.privateiimage.gallery20.Activity.SplashActivity.admob_app_open_list;
//import static com.picturemanage.privateiimage.gallery20.Activity.SplashActivity.admob_interstitial_array_int;
//import static com.picturemanage.privateiimage.gallery20.Activity.SplashActivity.admob_interstitial_list;
//import static com.picturemanage.privateiimage.gallery20.Activity.SplashActivity.admob_native_banner_list;
//import static com.picturemanage.privateiimage.gallery20.Activity.SplashActivity.admob_native_list;
//
//
//public class Ads {
//
//    public static InterstitialAd fbinterstitialAd;
//
//    public static Context context;
//    public static OnFinishAds onFinishAds;
//    public static int adsclick = 0;
//    public static int AppOpen_click = 0;
//    public static int backclick = 0;
//    public static boolean is_AdmobNativeload = true;
//    public static boolean is_AdmobNativeload_banner = true;
//    public static boolean extra_AdmobNativeload = true;
//    public static boolean isOpenShow = false;
//
//
//    public interface OnFinishAds {
//        void onFinishAds(boolean b);
//    }
//
//    //<editor-fold desc="Full ads">
//
//    private static InterstitialAd Admob_mInterstitialAd;
//    public static String adview_full = "";
//
//    public static void InterstitialIdsRandomId() {
//        Log.d("ADMOB_FULL", "InterstitialIdsRandomId");
//        try {
//            if (admob_interstitial_list != null && admob_interstitial_list.size() != 0 && admob_interstitial_list.size() != admob_interstitial_array_int && admob_interstitial_list.size() >= admob_interstitial_array_int) {
//                Log.d("ADMOB_FULL", "InterstitialIdsRandomId  if " + Preference.getFulladsArrayINT());
//                adview_full = admob_interstitial_list.get(Preference.getFulladsArrayINT());
//                Preference.setadmob_interstitial_id(adview_full);
//                /*admob_interstitial_array_int = admob_interstitial_array_int + 1;
//                Preference.setFulladsArrayINT(admob_interstitial_array_int);*/
//            } else {
//                Log.d("ADMOB_FULL", "InterstitialIdsRandomId  else");
//                admob_interstitial_array_int = 0;
//                Preference.setFulladsArrayINT(admob_interstitial_array_int);
//                Log.d("ADMOB_FULL", "InterstitialIdsRandomId getFulladsArrayINT else   " + Preference.getFulladsArrayINT());
//                adview_full = admob_interstitial_list.get(Preference.getFulladsArrayINT());
//                Preference.setadmob_interstitial_id(adview_full);
//                /*admob_interstitial_array_int = admob_interstitial_array_int + 1;
//                Preference.setFulladsArrayINT(admob_interstitial_array_int);*/
//            }
//        } catch (NullPointerException N) {
//            N.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void AdmobFullAds(final Activity context) {
////        Utill.pdialog_ads(context);
//        //    ProgressDialog_ads((Activity) context);
//        InterstitialIdsRandomId();
//        AdRequest adRequest = new AdRequest.Builder().build();
//
//        InterstitialAd.load(context, Preference.getadmob_interstitial_id(), adRequest, new InterstitialAdLoadCallback() {
//            @Override
//            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
//                // The mInterstitialAd reference will be null until
//                // an ad is loaded.
//                Admob_mInterstitialAd = interstitialAd;
//                Log.i("Admob_full", "onAdLoaded");
//
//                if (Preference.getadmob_load_type()) {
//                    if (admob_interstitial_list.size() != Preference.getFulladsArrayINT() && admob_interstitial_list.size() > 1) {
//                        Log.d("ADMOB_FULL", "BOOL TRUE VALUE PLUS");
//                        admob_interstitial_array_int = admob_interstitial_array_int + 1;
//                        Preference.setFulladsArrayINT(admob_interstitial_array_int);
//                    } else {
//                        Log.d("ADMOB_FULL", "ELSE CONDITION");
//                    }
//                }
//
//                Admob_mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//                    @Override
//                    public void onAdDismissedFullScreenContent() {
//                        // Called when fullscreen content is dismissed.
//                        Log.d("TAG", "The ad was dismissed.");
//                        onFinishAds.onFinishAds(true);
//                    }
//
//                    @Override
//                    public void onAdFailedToShowFullScreenContent(AdError adError) {
//                        // Called when fullscreen content failed to show.
//                        Log.d("TAG", "The ad failed to show.");
//
//                    }
//
//                    @Override
//                    public void onAdShowedFullScreenContent() {
//                        // Called when fullscreen content is shown.
//                        // Make sure to set your reference to null so you don't
//                        // show it a second time.
//                        Admob_mInterstitialAd = null;
//                        Log.d("TAG", "The ad was shown.");
//                    }
//                });
//            }
//
//            @Override
//            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                // Handle the error
//                Log.i("Admob_full", loadAdError.getMessage());
//                Admob_mInterstitialAd = null;
//                if (admob_interstitial_list.size() != Preference.getFulladsArrayINT() && admob_interstitial_list.size() > 1) {
//                    Log.d("ADMOB_FULL", "failed to re-load");
//                    admob_interstitial_array_int = admob_interstitial_array_int + 1;
//                    Preference.setFulladsArrayINT(admob_interstitial_array_int);
//                    AdmobFullAds(context);
//                } else {
//                    Log.d("ADMOB_FULL", "failed to complete");
//                    if (Preference.getShow_ads_type().equals("admob")) {
//                        Fb_InterstitialAd(context);
//                    }
//                }
//            }
//        });
//    }
//
//    public static int interstitial_admob_back = 0;
//
//    public static void showAds_back_old(Context context, OnFinishAds onFinishAd, boolean... doShowAds) {
//        onFinishAds = onFinishAd;
//
////        PackageManager pm = context.getPackageManager();
////        boolean isInstalled = isPackageInstalled("com.rg.royal111", pm);
////        if (Preference.getisInstallapk() && !isInstalled) {
////            if (interstitial_11_back == Preference.getinterstitialPageBack()) {
////                interstitial_11_back = 0;
////                getDownloadApkAds(context);
////                return;
////            }
////            interstitial_11_back++;
////        }
//
//        if (Ads.backclick == Preference.getback_click()) {
//            Ads.backclick = 0;
//
//            if (!Preference.getAdmob_page()) {
//                if (Admob_mInterstitialAd != null) {
//                    Admob_mInterstitialAd.show((Activity) context);
//                } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                    fb_interstitialAd.show();
//                } else {
//                    if (Preference.getqureka_show()) {
//                        getQurekaAds(context);
//                    } else {
//                        onFinishAds.onFinishAds(true);
//                    }
//                }
//            } else if (Preference.getAdmob_page() && interstitial_admob_back != Preference.getInter_back_admob()) {
//                interstitial_admob_back++;
//                if (Admob_mInterstitialAd != null) {
//                    Admob_mInterstitialAd.show((Activity) context);
//                } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                    fb_interstitialAd.show();
//                } else {
//                    if (Preference.getqureka_show()) {
//                        getQurekaAds(context);
//                    } else {
//                        onFinishAds.onFinishAds(true);
//                    }
//                }
//            } else {
//                interstitial_admob_back = 0;
//                if (Preference.getqureka_show()) {
//                    getQurekaAds(context);
//                } else {
//                    if (Admob_mInterstitialAd != null) {
//                        Admob_mInterstitialAd.show((Activity) context);
//                    } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                        fb_interstitialAd.show();
//                    } else {
//                        onFinishAds.onFinishAds(true);
//                    }
//                }
//            }
//
//            AdmobFullAds((Activity) context);
//        } else {
//            Ads.backclick++;
//            onFinishAds.onFinishAds(true);
//        }
//    }
//
//    public static void showAds_back(Context context, OnFinishAds onFinishAd, boolean... doShowAds) {
//        onFinishAds = onFinishAd;
//
////        PackageManager pm = context.getPackageManager();
////        boolean isInstalled = isPackageInstalled("com.rg.royal111", pm);
////        if (Preference.getisInstallapk() && !isInstalled) {
////            if (interstitial_11_back == Preference.getinterstitialPageBack()) {
////                interstitial_11_back = 0;
////                getDownloadApkAds(context);
////                return;
////            }
////            interstitial_11_back++;
////        }
//        if (Ads.backclick == Preference.getback_click()) {
//            Ads.backclick = 0;
//            if (Preference.getShow_ads_type().equals("admob")) {
//                if (Admob_mInterstitialAd != null) {
//                    Admob_mInterstitialAd.show((Activity) context);
//                } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                    fb_interstitialAd.show();
//                } else {
//                    if (Preference.getqureka_show()) {
//                        getQurekaAds(context);
//                    } else {
//                        onFinishAds.onFinishAds(true);
//                    }
//                }
//                AdmobFullAds((Activity) context);
//            } else {
//                if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                    fb_interstitialAd.show();
//                } else if (Admob_mInterstitialAd != null) {
//                    Admob_mInterstitialAd.show((Activity) context);
//                } else {
//                    if (Preference.getqureka_show()) {
//                        getQurekaAds(context);
//                    } else {
//                        onFinishAds.onFinishAds(true);
//                    }
//                }
//                Fb_InterstitialAd((Activity) context);
//            }
//        } else {
//            Ads.backclick++;
//            onFinishAds.onFinishAds(true);
//        }
//    }
//
//    public static int interstitial_admob = 0;
//    public static int appopen_admob = 0;
//
//    public static void showAds_old(Context context, OnFinishAds onFinishAd, boolean... doShowAds) {
//        Log.d("Utillss", "showads one");
//
//        onFinishAds = onFinishAd;
//
//        if (!Preference.getShow_ads_type().equals("admob")) {
//            FbadsShow_setup(context);
//        } else {
//            if (Preference.getAppOpen_inter_show()) {
//                if (Ads.AppOpen_click == Preference.getAppOpen_click()) {
//                    Ads.AppOpen_click = 0;
//
//                    if (!Preference.getAdmob_page()) {
//                        OpenAppAds_Show((Activity) context);
//                    } else if (Preference.getAdmob_page() && appopen_admob != Preference.getAds_open_admob()) {
//                        appopen_admob++;
//                        OpenAppAds_Show((Activity) context);
//                    } else {
//                        appopen_admob = 0;
//                        if (Preference.getqureka_show()) {
//                            getAddOpenQurekha((Activity) context);
//                        } else {
//                            OpenAppAds_Show((Activity) context);
//                        }
//                    }
//
//                } else {
//                    Ads.AppOpen_click++;
////                PackageManager pm = context.getPackageManager();
////                boolean isInstalled = isPackageInstalled("com.rg.royal111", pm);
////                if (Preference.getisInstallapk() && !isInstalled) {
////                    if (interstitial_11 == Preference.getinterstitialPageAds()) {
////                        interstitial_11 = 0;
////                        getDownloadApkAds(context);
////                        return;
////                    }
////                    interstitial_11++;
////                }
//                    if (Ads.adsclick == Preference.getads_click()) {
//                        Ads.adsclick = 0;
//                        if (!Preference.getAdmob_page()) {
//                            if (Admob_mInterstitialAd != null) {
//                                Admob_mInterstitialAd.show((Activity) context);
//                            } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                                fb_interstitialAd.show();
//                            } else {
//                                if (Preference.getqureka_show()) {
//                                    getQurekaAds(context);
//                                } else {
//                                    onFinishAds.onFinishAds(true);
//                                }
//                            }
//                        } else if (Preference.getAdmob_page() && interstitial_admob != Preference.getinter_admob()) {
//                            interstitial_admob++;
//                            if (Admob_mInterstitialAd != null) {
//                                Admob_mInterstitialAd.show((Activity) context);
//                            } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                                fb_interstitialAd.show();
//                            } else {
//                                if (Preference.getqureka_show()) {
//                                    getQurekaAds(context);
//                                } else {
//                                    onFinishAds.onFinishAds(true);
//                                }
//                            }
//                        } else {
//                            interstitial_admob = 0;
//                            if (Preference.getqureka_show()) {
//                                getQurekaAds(context);
//                            } else {
//                                if (Admob_mInterstitialAd != null) {
//                                    Admob_mInterstitialAd.show((Activity) context);
//                                } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                                    fb_interstitialAd.show();
//                                } else {
//                                    onFinishAds.onFinishAds(true);
//                                }
//                                // onFinishAds.onFinishAds(true);
//                            }
//                        }
//                        AdmobFullAds((Activity) context);
//
//                    } else {
//                        Ads.adsclick++;
//                        onFinishAds.onFinishAds(true);
//                    }
//                }
//            } else {
////            PackageManager pm = context.getPackageManager();
////            boolean isInstalled = isPackageInstalled("com.rg.royal111", pm);
////            if (Preference.getisInstallapk() && !isInstalled) {
////                if (interstitial_11 == Preference.getinterstitialPageAds()) {
////                    interstitial_11 = 0;
////                    getDownloadApkAds(context);
////                    return;
////                }
////                interstitial_11++;
////            }
//                if (Ads.adsclick == Preference.getads_click()) {
//                    Ads.adsclick = 0;
//                    if (!Preference.getAdmob_page()) {
//                        interstitial_admob++;
//                        if (Admob_mInterstitialAd != null) {
//                            Admob_mInterstitialAd.show((Activity) context);
//                        } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                            fb_interstitialAd.show();
//                        } else {
//                            if (Preference.getqureka_show()) {
//                                getQurekaAds(context);
//                            } else {
//                                onFinishAds.onFinishAds(true);
//                            }
//                        }
//                    } else if (Preference.getAdmob_page() && interstitial_admob != Preference.getinter_admob()) {
//                        interstitial_admob++;
//                        if (Admob_mInterstitialAd != null) {
//                            Admob_mInterstitialAd.show((Activity) context);
//                        } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                            fb_interstitialAd.show();
//                        } else {
//                            if (Preference.getqureka_show()) {
//                                getQurekaAds(context);
//                            } else {
//                                onFinishAds.onFinishAds(true);
//                            }
//                        }
//                    } else {
//                        interstitial_admob = 0;
//                        if (Preference.getqureka_show()) {
//                            getQurekaAds(context);
//                        } else {
//                            if (Admob_mInterstitialAd != null) {
//                                Admob_mInterstitialAd.show((Activity) context);
//                            } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                                fb_interstitialAd.show();
//                            } else {
//                                onFinishAds.onFinishAds(true);
//                            }
//                            //  onFinishAds.onFinishAds(true);
//                        }
//                    }
//
//                    AdmobFullAds((Activity) context);
//                } else {
//                    Ads.adsclick++;
//                    onFinishAds.onFinishAds(true);
//                }
//            }
//        }
////        onFinishAds = onFinishAd;
////        if (Preference.getAppOpen_inter_show()) {
////
////
////            if (Ads.AppOpen_click == Preference.getAppOpen_click()) {
////                Ads.AppOpen_click = 0;
////                isOpenShow = true;
////
////                if (Admob_mInterstitialAd != null) {
////                    Admob_mInterstitialAd.show((Activity) context);
////                } else {
////                    if (Preference.getqureka_show()) {
////                        getQurekaAds(context);
////                    } else {
////                        isOpenShow = false;
////                        onFinishAds.onFinishAds(true);
////                    }
////                }
////                AdmobFullAds((Activity) context);
////            } else {
////                Ads.AppOpen_click++;
////                isOpenShow = false;
////                if (Ads.adsclick == Preference.getads_click()) {
////                    Ads.adsclick = 0;
////                    if (Admob_mInterstitialAd != null) {
////                        Admob_mInterstitialAd.show((Activity) context);
////                    } else {
////                        if (Preference.getqureka_show()) {
////                            getQurekaAds(context);
////                        } else {
////                            onFinishAds.onFinishAds(true);
////                        }
////                    }
////                    AdmobFullAds((Activity) context);
////                } else {
////                    Ads.adsclick++;
////                    onFinishAds.onFinishAds(true);
////
////                }
////
////
////            }
////        } else {
////
////            isOpenShow = false;
////            if (Ads.adsclick == Preference.getads_click()) {
////                Ads.adsclick = 0;
////                if (Admob_mInterstitialAd != null) {
////                    Admob_mInterstitialAd.show((Activity) context);
////                } else {
////                    if (Preference.getqureka_show()) {
////                        getQurekaAds(context);
////                    } else {
////                        onFinishAds.onFinishAds(true);
////                    }
////                }
////                AdmobFullAds((Activity) context);
////            } else {
////                Ads.adsclick++;
////                onFinishAds.onFinishAds(true);
////
////            }
////
////        }
//    }
//
//    public static void showAds(Context context, OnFinishAds onFinishAd, boolean... doShowAds) {
//        onFinishAds = onFinishAd;
//        if (Ads.adsclick == Preference.getads_click()) {
//            Ads.adsclick = 0;
//            if (Preference.getShow_ads_type().equals("admob")) {
//                if (Admob_mInterstitialAd != null) {
//                    Admob_mInterstitialAd.show((Activity) context);
//                } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                    fb_interstitialAd.show();
//                } else {
//                    if (Preference.getqureka_show()) {
//                        getQurekaAds(context);
//                    } else {
//                        onFinishAds.onFinishAds(true);
//                    }
//                }
//                AdmobFullAds((Activity) context);
//            } else {
//                if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                    fb_interstitialAd.show();
//                } else if (Admob_mInterstitialAd != null) {
//                    Admob_mInterstitialAd.show((Activity) context);
//                } else {
//                    if (Preference.getqureka_show()) {
//                        getQurekaAds(context);
//                    } else {
//                        onFinishAds.onFinishAds(true);
//                    }
//                }
//
//                Fb_InterstitialAd((Activity) context);
//
//            }
//
//        } else {
//            Ads.adsclick++;
//            onFinishAds.onFinishAds(true);
//        }
//
//    }
//
//    private static void FbadsShow_setup(Context context) {
//        if (Ads.adsclick == Preference.getads_click()) {
//            Ads.adsclick = 0;
//            if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                fb_interstitialAd.show();
//            } else if (Preference.getAppOpen_inter_show()) {
//                if (Ads.AppOpen_click == Preference.getAppOpen_click()) {
//                    Ads.AppOpen_click = 0;
//
//                    if (!Preference.getAdmob_page()) {
//                        OpenAppAds_Show((Activity) Ads.context);
//                    } else if (Preference.getAdmob_page() && appopen_admob != Preference.getAds_open_admob()) {
//                        appopen_admob++;
//                        OpenAppAds_Show((Activity) Ads.context);
//                    } else {
//                        appopen_admob = 0;
//                        if (Preference.getqureka_show()) {
//                            getAddOpenQurekha((Activity) Ads.context);
//                        } else {
//                            OpenAppAds_Show((Activity) Ads.context);
//                        }
//                    }
//
//                } else {
//                    Ads.AppOpen_click++;
////                PackageManager pm = context.getPackageManager();
////                boolean isInstalled = isPackageInstalled("com.rg.royal111", pm);
////                if (Preference.getisInstallapk() && !isInstalled) {
////                    if (interstitial_11 == Preference.getinterstitialPageAds()) {
////                        interstitial_11 = 0;
////                        getDownloadApkAds(context);
////                        return;
////                    }
////                    interstitial_11++;
////                }
//                    if (!Preference.getAdmob_page()) {
//                        if (Admob_mInterstitialAd != null) {
//                            Admob_mInterstitialAd.show((Activity) context);
//                        } else {
//                            if (Preference.getqureka_show()) {
//                                getQurekaAds(context);
//                            } else {
//                                onFinishAds.onFinishAds(true);
//                            }
//                        }
//                    } else if (Preference.getAdmob_page() && interstitial_admob != Preference.getinter_admob()) {
//                        interstitial_admob++;
//                        if (Admob_mInterstitialAd != null) {
//                            Admob_mInterstitialAd.show((Activity) context);
//                        } else {
//                            if (Preference.getqureka_show()) {
//                                getQurekaAds(context);
//                            } else {
//                                onFinishAds.onFinishAds(true);
//                            }
//                        }
//                    } else {
//                        interstitial_admob = 0;
//                        if (Preference.getqureka_show()) {
//                            getQurekaAds(context);
//                        } else {
//                            if (Admob_mInterstitialAd != null) {
//                                Admob_mInterstitialAd.show((Activity) context);
//                            } else {
//                                onFinishAds.onFinishAds(true);
//                            }
//                            // onFinishAds.onFinishAds(true);
//                        }
//                    }
//                }
//            } else {
////            PackageManager pm = context.getPackageManager();
////            boolean isInstalled = isPackageInstalled("com.rg.royal111", pm);
////            if (Preference.getisInstallapk() && !isInstalled) {
////                if (interstitial_11 == Preference.getinterstitialPageAds()) {
////                    interstitial_11 = 0;
////                    getDownloadApkAds(context);
////                    return;
////                }
////                interstitial_11++;
////            }
//                if (!Preference.getAdmob_page()) {
//                    interstitial_admob++;
//                    if (Admob_mInterstitialAd != null) {
//                        Admob_mInterstitialAd.show((Activity) context);
//                    } else {
//                        if (Preference.getqureka_show()) {
//                            getQurekaAds(context);
//                        } else {
//                            onFinishAds.onFinishAds(true);
//                        }
//                    }
//                } else if (Preference.getAdmob_page() && interstitial_admob != Preference.getinter_admob()) {
//                    interstitial_admob++;
//                    if (Admob_mInterstitialAd != null) {
//                        Admob_mInterstitialAd.show((Activity) context);
//                    } else {
//                        if (Preference.getqureka_show()) {
//                            getQurekaAds(context);
//                        } else {
//                            onFinishAds.onFinishAds(true);
//                        }
//                    }
//                } else {
//                    interstitial_admob = 0;
//                    if (Preference.getqureka_show()) {
//                        getQurekaAds(context);
//                    } else {
//                        if (Admob_mInterstitialAd != null) {
//                            Admob_mInterstitialAd.show((Activity) context);
//                        } else {
//                            onFinishAds.onFinishAds(true);
//                        }
//                        //  onFinishAds.onFinishAds(true);
//                    }
//                }
//
//            }
//            Fb_InterstitialAd((Activity) context);
//
//        } else {
//            Ads.adsclick++;
//            onFinishAds.onFinishAds(true);
//        }
//
//
//    }
//
//    public static void showAds_new_old(Context context, OnFinishAds onFinishAd, boolean... doShowAds) {
//        Log.d("Utillss", "showads one");
//
//        onFinishAds = onFinishAd;
//        if (Preference.getAppOpen_inter_show()) {
//            OpenAppAds_Show((Activity) context);
//        } else {
//            if (Admob_mInterstitialAd != null) {
//                Admob_mInterstitialAd.show((Activity) context);
//            } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                fb_interstitialAd.show();
//            } else {
//                if (Preference.getqureka_show()) {
//                    getQurekaAds(context);
//                } else {
//                    onFinishAds.onFinishAds(true);
//                }
//            }
//            AdmobFullAds((Activity) context);
//
//        }
////        onFinishAds = onFinishAd;
////        if (Preference.getAppOpen_inter_show()) {
////            isOpenShow = true;
////
////            if (Admob_mInterstitialAd != null) {
////                Admob_mInterstitialAd.show((Activity) context);
////            } else {
////                if (Preference.getqureka_show()) {
////                    getQurekaAds(context);
////                } else {
////                    isOpenShow = false;
////                    onFinishAds.onFinishAds(true);
////                }
////            }
////            AdmobFullAds((Activity) context);
////        } else {
////            isOpenShow = false;
////            if (Admob_mInterstitialAd != null) {
////                Admob_mInterstitialAd.show((Activity) context);
////            } else {
////                if (Preference.getqureka_show()) {
////                    getQurekaAds(context);
////                } else {
////                    isOpenShow = false;
////                    onFinishAds.onFinishAds(true);
////                }
////            }
////            AdmobFullAds((Activity) context);
////        }
//
//    }
//
//    public static void showAds_new(Context context, OnFinishAds onFinishAd, boolean... doShowAds) {
//        onFinishAds = onFinishAd;
//        if (Preference.getShow_ads_type().equals("admob")) {
//            if (Admob_mInterstitialAd != null) {
//                Admob_mInterstitialAd.show((Activity) context);
//            } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                fb_interstitialAd.show();
//            } else {
//                if (Preference.getqureka_show()) {
//                    getQurekaAds(context);
//                } else {
//                    onFinishAds.onFinishAds(true);
//                }
//            }
//            AdmobFullAds((Activity) context);
//        } else {
//            if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                fb_interstitialAd.show();
//            } else if (Admob_mInterstitialAd != null) {
//                Admob_mInterstitialAd.show((Activity) context);
//            } else {
//                if (Preference.getqureka_show()) {
//                    getQurekaAds(context);
//                } else {
//                    onFinishAds.onFinishAds(true);
//                }
//            }
//
//            Fb_InterstitialAd((Activity) context);
//
//        }
//
//    }
//
//    public static void OpenAppAds_Show(Activity context) {
//        Log.d("OpenAppAds12", "OpenAppAds_Show ");
//        try {
//            if (!Appcontroller.isShowingAd) {
//                if (appOpenAd != null) {
//                    Log.d("OpenAppAds12", "appOpenAd 1 ");
//                    FullScreenContentCallback fullScreenContentCallback =
//                            new FullScreenContentCallback() {
//                                @Override
//                                public void onAdDismissedFullScreenContent() {
//                                    Appcontroller.isShowingAd = false;
//                                    Show_ads_Full(context);
//                                }
//
//                                @Override
//                                public void onAdFailedToShowFullScreenContent(AdError adError) {
//                                    Log.d("OpenAppAds12", "onAdFailedToShowFullScreenContent = " + adError.getMessage());
//                                    Show_ads_Full(context);
//                                }
//
//                                @Override
//                                public void onAdShowedFullScreenContent() {
//                                    Appcontroller.isShowingAd = true;
//                                }
//                            };
//
//                    appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
//                    appOpenAd.show(context);
//                    Log.d("OpenAppAds12", "appOpenAd show ");
//                } else {
//                    Show_ads_Full(context);
//                }
//            } else {
//                Show_ads_Full(context);
//            }
//        } catch (NullPointerException n) {
//            n.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        OpenAppAds(context);
//    }
//
//    private static void Show_ads_Full(Activity context) {
//
//        if (!Preference.getAdmob_page()) {
//            interstitial_admob++;
//            if (Admob_mInterstitialAd != null) {
//                Admob_mInterstitialAd.show((Activity) context);
//            } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                fb_interstitialAd.show();
//            } else {
//                if (Preference.getqureka_show()) {
//                    getQurekaAds(context);
//                } else {
//                    onFinishAds.onFinishAds(true);
//                }
//            }
//        } else if (Preference.getAdmob_page() && interstitial_admob != Preference.getinter_admob()) {
//            interstitial_admob++;
//            if (Admob_mInterstitialAd != null) {
//                Admob_mInterstitialAd.show((Activity) context);
//            } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                fb_interstitialAd.show();
//            } else {
////            onFinishAds.onFinishAds(true);
//                if (Preference.getqureka_show()) {
//                    getQurekaAds(context);
//                } else {
//                    onFinishAds.onFinishAds(true);
//                }
//            }
//        } else {
//            interstitial_admob = 0;
//            if (Preference.getqureka_show()) {
//                getQurekaAds(context);
//            } else {
//                if (Admob_mInterstitialAd != null) {
//                    Admob_mInterstitialAd.show((Activity) context);
//                } else if (fb_interstitialAd != null && fb_interstitialAd.isAdLoaded()) {
//                    fb_interstitialAd.show();
//                } else {
//                    onFinishAds.onFinishAds(true);
//                }
//            }
//        }
//        AdmobFullAds((Activity) context);
//    }
//
//    public static void getAddOpenQurekha(Activity activity) {
//
//        Dialog appopendialog = new Dialog(activity);
//        appopendialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        appopendialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        appopendialog.setContentView(R.layout.dialog_appopen_qureka);
//        int width = ViewGroup.LayoutParams.MATCH_PARENT;
//        final int height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        appopendialog.getWindow().setLayout(width, height);
//        appopendialog.setCancelable(false);
//        appopendialog.show();
//        Appcontroller.isShowingAd = true;
//        LinearLayout ll_continue = appopendialog.findViewById(R.id.ll_continue);
//        RelativeLayout rl_qureka = appopendialog.findViewById(R.id.rl_qureka);
//        Button btn_play1 = appopendialog.findViewById(R.id.btn_play1);
//        Button btn_play2 = appopendialog.findViewById(R.id.btn_play2);
//
//
//        GifImageView gif_app_open = appopendialog.findViewById(R.id.gif_qureka1);
//        TextView tv_header_text = appopendialog.findViewById(R.id.tv_header_text);
//        ImageView iv_qureka_img = appopendialog.findViewById(R.id.iv_qureka_img);
//        int random = new Random().nextInt(3);
//        if (random == 1) {
//            tv_header_text.setText("Play Bubble Shooter Game And Win Cash");
//            iv_qureka_img.setBackgroundResource(R.drawable.qureka_open2);
//            gif_app_open.setBackgroundResource(R.drawable.qureka_round2);
//        } else if (random == 2) {
//            tv_header_text.setText("Play Fruit Chop Game And Win Cash");
//            iv_qureka_img.setBackgroundResource(R.drawable.qureka_open3);
//            gif_app_open.setBackgroundResource(R.drawable.qureka_round3);
//        } else {
//            tv_header_text.setText("Play Car Racing Game And Win Cash");
//            iv_qureka_img.setBackgroundResource(R.drawable.qureka_open5);
//            gif_app_open.setBackgroundResource(R.drawable.qureka_round1);
//        }
//
//        ll_continue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Appcontroller.isShowingAd = false;
//                appopendialog.dismiss();
//                Show_ads_Full(activity);
//            }
//        });
//
//        btn_play1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                GetData.OpenQureka(activity);
//            }
//        });
//
//        btn_play2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                GetData.OpenQureka(activity);
//            }
//        });
//
//        rl_qureka.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                GetData.OpenQureka(activity);
//            }
//        });
//
//        appopendialog.setOnKeyListener(new Dialog.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    Appcontroller.isShowingAd = false;
//                    appopendialog.dismiss();
//                    Show_ads_Full(activity);
//                }
//                return true;
//            }
//        });
////        appopendialog = new Dialog(activity);
////        appopendialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
////        appopendialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
////        appopendialog.setContentView(R.layout.dialog_appopen_qureka);
////        int width = ViewGroup.LayoutParams.MATCH_PARENT;
////        final int height = ViewGroup.LayoutParams.WRAP_CONTENT;
////        appopendialog.getWindow().setLayout(width, height);
////        appopendialog.setCancelable(false);
////        Appcontroller.isShowingAd = true;
////        appopendialog.show();
////
////        ll_continue = appopendialog.findViewById(R.id.ll_continue);
////        rl_qureka = appopendialog.findViewById(R.id.rl_qureka);
////        btn_play1 = appopendialog.findViewById(R.id.btn_play1);
////        btn_play2 = appopendialog.findViewById(R.id.btn_play2);
////
////        ll_continue.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Appcontroller.isShowingAd = false;
////                appopendialog.dismiss();
//////                IntentActivy();
////
////                isOpenShow = false;
////                onFinishAds.onFinishAds(true);
////            }
////        });
////
////        btn_play1.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                GetData.OpenQureka(activity);
////            }
////        });
////
////        btn_play2.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                GetData.OpenQureka(activity);
////            }
////        });
////
////        rl_qureka.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                GetData.OpenQureka(activity);
////            }
////        });
////
////        appopendialog.setOnKeyListener(new Dialog.OnKeyListener() {
////
////            @Override
////            public boolean onKey(DialogInterface arg0, int keyCode,
////                                 KeyEvent event) {
////                // TODO Auto-generated method stub
////                if (keyCode == KeyEvent.KEYCODE_BACK) {
////                    Appcontroller.isShowingAd = false;
////                    appopendialog.dismiss();
////                    isOpenShow = false;
////                    onFinishAds.onFinishAds(true);
////                }
////                return true;
////            }
////        });
//
//    }
//
//
//    public static com.facebook.ads.InterstitialAd fb_interstitialAd;
//
//    public static void Fb_InterstitialAd(Activity context) {
//
//        String fb_interstitialAd_id = Preference.getFb_interstitialAd_id();
//        Log.d("Interstitialad12", "fb Interstitial ad ids." + fb_interstitialAd_id);
//
//        fb_interstitialAd = new com.facebook.ads.InterstitialAd(context, fb_interstitialAd_id);
//
//
//        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
//            @Override
//            public void onInterstitialDisplayed(Ad ad) {
//                // Interstitial ad displayed callback
//                Log.d("Interstitialad12", "fb Interstitial ad displayed.");
//            }
//
//            @Override
//            public void onInterstitialDismissed(Ad ad) {
//                // Interstitial dismissed callback
//                onFinishAds.onFinishAds(true);
//                Log.d("Interstitialad12", "fb Interstitial ad dismissed.");
//            }
//
//
//            @Override
//            public void onError(Ad ad, com.facebook.ads.AdError adError) {
//                Log.d("Interstitialad12", "fb Interstitial ad is onError = " + adError.getErrorMessage());
//                if (!Preference.getShow_ads_type().equals("admob")) {
//                    AdmobFullAds(context);
//                }
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                // Interstitial ad is loaded and ready to be displayed
//                Log.d("Interstitialad12", "fb Interstitial ad is loaded and ready to be displayed!");
//                // Show the ad
//
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//                // Ad clicked callback
//                Log.d("Interstitialad12", "fb Interstitial ad clicked!");
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//                // Ad impression logged callback
//                Log.d("Interstitialad12", "fb Interstitial ad impression logged!");
//            }
//        };
//
//        fb_interstitialAd.loadAd(
//                fb_interstitialAd.buildLoadAdConfig()
//                        .withAdListener(interstitialAdListener)
//                        .build());
//
//    }
//
//
//  /*  public static void showAds_back(Context context, OnFinishAds onFinishAd, boolean... doShowAds) {
//        onFinishAds = onFinishAd;
//        if (Ads.backclick == Preference.getback_click()) {
//            Ads.backclick = 0;
//            if (Admob_mInterstitialAd != null) {
//                Admob_mInterstitialAd.show((Activity) context);
//            } else {
//                if (Preference.getqureka_show()) {
//                    getQurekaAds(context);
//                } else {
//                    onFinishAds.onFinishAds(true);
//                }
//            }
//            AdmobFullAds((Activity) context);
//        } else {
//            Ads.backclick++;
//            onFinishAds.onFinishAds(true);
//
//        }
//    }
//
//
//    public static void showAds(Context context, OnFinishAds onFinishAd, boolean... doShowAds) {
//        Log.d("Utillss", "showads one");
//        onFinishAds = onFinishAd;
//        if (Preference.getAppOpen_inter_show()) {
//            if (Ads.AppOpen_click == Preference.getAppOpen_click()) {
//                Ads.AppOpen_click = 0;
//                OpenAppAds_Show((Activity) context);
//            } else {
//                Ads.AppOpen_click++;
//
//                if (Ads.adsclick == Preference.getads_click()) {
//                    Ads.adsclick = 0;
//                    if (Admob_mInterstitialAd != null) {
//                        Admob_mInterstitialAd.show((Activity) context);
//                    } else {
//                        if (Preference.getqureka_show()) {
//                            getQurekaAds(context);
//                        } else {
//                            onFinishAds.onFinishAds(true);
//                        }
//                    }
//                    AdmobFullAds((Activity) context);
//                } else {
//                    Ads.adsclick++;
//                    onFinishAds.onFinishAds(true);
//
//                }
//
//
//            }
//        } else {
//            if (Ads.adsclick == Preference.getads_click()) {
//                Ads.adsclick = 0;
//                if (Admob_mInterstitialAd != null) {
//                    Admob_mInterstitialAd.show((Activity) context);
//                } else {
//                    if (Preference.getqureka_show()) {
//                        getQurekaAds(context);
//                    } else {
//                        onFinishAds.onFinishAds(true);
//                    }
//                }
//                AdmobFullAds((Activity) context);
//            } else {
//                Ads.adsclick++;
//                onFinishAds.onFinishAds(true);
//
//            }
//
//        }
//    }
//
//
//    public static void showAds_new(Context context, OnFinishAds onFinishAd, boolean... doShowAds) {
//        Log.d("Utillss", "showads one");
//        onFinishAds = onFinishAd;
//        if (Preference.getAppOpen_inter_show()) {
//            OpenAppAds_Show((Activity) context);
//        } else {
//            if (Admob_mInterstitialAd != null) {
//                Admob_mInterstitialAd.show((Activity) context);
//            } else {
//                if (Preference.getqureka_show()) {
//                    getQurekaAds(context);
//                } else {
//                    onFinishAds.onFinishAds(true);
//                }
//            }
//            AdmobFullAds((Activity) context);
//
//        }
//
//    }
//
//    public static void OpenAppAds_Show(Activity context) {
//        Log.d("OpenAppAds12", "OpenAppAds_Show ");
//        try {
//            if (!Appcontroller.isShowingAd) {
//                if (appOpenAd != null) {
//                    Log.d("OpenAppAds12", "appOpenAd 1 ");
//                    FullScreenContentCallback fullScreenContentCallback =
//                            new FullScreenContentCallback() {
//                                @Override
//                                public void onAdDismissedFullScreenContent() {
//                                    Appcontroller.isShowingAd = false;
//                                    Show_ads_Full(context);
//                                }
//
//                                @Override
//                                public void onAdFailedToShowFullScreenContent(AdError adError) {
//                                    Log.d("OpenAppAds12", "onAdFailedToShowFullScreenContent = " + adError.getMessage());
//                                    Show_ads_Full(context);
//                                }
//
//                                @Override
//                                public void onAdShowedFullScreenContent() {
//                                    Appcontroller.isShowingAd = true;
//                                }
//                            };
//
//                    appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
//                    appOpenAd.show(context);
//                    Log.d("OpenAppAds12", "appOpenAd show ");
//                } else {
//                    Show_ads_Full(context);
//                }
//            } else {
//                Show_ads_Full(context);
//            }
//        } catch (NullPointerException n) {
//            n.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        OpenAppAds(context);
//    }*/
//
//
//    public static int admob_appopen_array_int = Preference.getOpenAdsArrayINT();
//    public static String admob_appopen_id = "";
//
//    public static void OpenAppAdsRandomId() {
//        Log.d("OpenAppAds123", "OpenAppAdsRandomId");
//        try {
//            if (admob_app_open_list != null && admob_app_open_list.size() != 0 && admob_app_open_list.size() != admob_appopen_array_int && admob_app_open_list.size() >= admob_appopen_array_int) {
//                Log.d("OpenAppAds123", "OpenAppAdsRandomId  if " + Preference.getOpenAdsArrayINT());
//                admob_appopen_id = admob_app_open_list.get(Preference.getOpenAdsArrayINT());
//                Preference.setapp_open_id(admob_appopen_id);
//               /* admob_appopen_array_int = admob_appopen_array_int + 1;
//                Preference.setOpenAdsArrayINT(admob_appopen_array_int);*/
//            } else {
//                Log.d("OpenAppAds123", "OpenAppAdsRandomId  else");
//                admob_appopen_array_int = 0;
//                Preference.setOpenAdsArrayINT(admob_appopen_array_int);
//                Log.d("OpenAppAds123", "OpenAppAdsRandomId  else   " + Preference.getOpenAdsArrayINT());
//                admob_appopen_id = admob_app_open_list.get(Preference.getOpenAdsArrayINT());
//                Preference.setapp_open_id(admob_appopen_id);
//                /*admob_appopen_array_int = admob_appopen_array_int + 1;
//                Preference.setOpenAdsArrayINT(admob_appopen_array_int);*/
//            }
//        } catch (NullPointerException n) {
//            n.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static AppOpenAd appOpenAd;
//    public static AppOpenAd.AppOpenAdLoadCallback loadCallback;
//
//    public static void OpenAppAds(Activity context) {
//
//        OpenAppAdsRandomId();
//        try {
//            String app_open_id = Preference.getapp_open_id();
//            Log.d("OpenAppAds12", "OpenAppAds ids = " + app_open_id);
//            loadCallback =
//                    new AppOpenAd.AppOpenAdLoadCallback() {
//                        @Override
//                        public void onAdLoaded(AppOpenAd ad) {
//                            appOpenAd = ad;
//                            Log.d("OpenAppAds12", "onAdLoaded");
//
//                            if (Preference.getadmob_load_type()) {
//                                if (admob_app_open_list.size() != Preference.getOpenAdsArrayINT() && admob_app_open_list.size() > 1) {
//                                    Log.d("OpenAppAds12", "BOOL TRUE VALUE PLUS");
//                                    admob_appopen_array_int = admob_appopen_array_int + 1;
//                                    Preference.setOpenAdsArrayINT(admob_appopen_array_int);
//                                } else {
//                                    Log.d("OpenAppAds12", "ELSE CONDITION");
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onAdFailedToLoad(LoadAdError loadAdError) {
//                            Log.d("OpenAppAds12", "onAdFailedToLoad = " + loadAdError.getMessage());
//                            // appOpenAd = null;
//                            // Appcontroller.isShowingAd = false;
//                            if (admob_app_open_list.size() != Preference.getOpenAdsArrayINT() && admob_app_open_list.size() > 1) {
//                                Log.d("OpenAppAds12", "failed to re-load");
//                                admob_appopen_array_int = admob_appopen_array_int + 1;
//                                Preference.setOpenAdsArrayINT(admob_appopen_array_int);
//                                OpenAppAds(context);
//                            } else {
//                                Log.d("OpenAppAds12", "failed to complete");
//                            }
//                        }
//                    };
//            AdRequest request = new AdRequest.Builder().build();
//            AppOpenAd.load(context, app_open_id, request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void getQurekaAds(Context context) {
//        Dialog dialog = new Dialog(context, android.R.style.Theme_Light);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.getWindow().getAttributes().windowAnimations = R.style.QuerekaInter;
//        dialog.setContentView(R.layout.qureka_interstitial_layout);
////        dialog.setCancelable(false);
////        dialog.setCanceledOnTouchOutside(false);
//        //  int drawable = getQurekaImage();
//        //   dialog.findViewById(R.id.qurekaAds).setBackgroundResource(drawable);
//        //  dialog.findViewById(R.id.qurekaAds1).setBackgroundResource(drawable);
//        GifImageView gif_inter_round = dialog.findViewById(R.id.gif_inter_round);
//        TextView tv_text_ad_name = dialog.findViewById(R.id.tv_text_ad_name);
//        TextView tv_text_ad_desc = dialog.findViewById(R.id.tv_text_ad_desc);
//        int random = new Random().nextInt(5);
//        if (random == 1) {
//            tv_text_ad_name.setText("Play Fruit Chop Game");
//            tv_text_ad_desc.setText("Win 50,000 Coins No Install Required");
//            gif_inter_round.setBackgroundResource(R.drawable.qureka_round3);
//            dialog.findViewById(R.id.qurekaAds).setBackgroundResource(R.drawable.qureka_inter1);
//            dialog.findViewById(R.id.qurekaAds1).setBackgroundResource(R.drawable.qureka_inter1);
//        } else if (random == 2) {
//            tv_text_ad_name.setText("Play Fruit Chop Game");
//            tv_text_ad_desc.setText("Win 50,000 Coins No Install Required");
//            gif_inter_round.setBackgroundResource(R.drawable.qureka_round3);
//            dialog.findViewById(R.id.qurekaAds).setBackgroundResource(R.drawable.qureka_inter2);
//            dialog.findViewById(R.id.qurekaAds1).setBackgroundResource(R.drawable.qureka_inter2);
//        } else if (random == 3) {
//            tv_text_ad_name.setText("Play Bubble Shooter Game");
//            tv_text_ad_desc.setText("Win 50,000 Coins With Mobile Games");
//            gif_inter_round.setBackgroundResource(R.drawable.qureka_round2);
//            dialog.findViewById(R.id.qurekaAds).setBackgroundResource(R.drawable.qureka_inter3);
//            dialog.findViewById(R.id.qurekaAds1).setBackgroundResource(R.drawable.qureka_inter3);
//        } else if (random == 4) {
//            tv_text_ad_name.setText("Play Cricket Win Coins");
//            tv_text_ad_desc.setText("Win 5,00,000 Coins & More");
//            gif_inter_round.setBackgroundResource(R.drawable.qureka_round_4);
//            dialog.findViewById(R.id.qurekaAds).setBackgroundResource(R.drawable.qureka_inter4);
//            dialog.findViewById(R.id.qurekaAds1).setBackgroundResource(R.drawable.qureka_inter4);
//        } else {
//            tv_text_ad_name.setText("Play Car Racing Game");
//            tv_text_ad_desc.setText("Win Coin & No Installation Required");
//            gif_inter_round.setBackgroundResource(R.drawable.qureka_round1);
//            dialog.findViewById(R.id.qurekaAds).setBackgroundResource(R.drawable.qureka_inter5);
//            dialog.findViewById(R.id.qurekaAds1).setBackgroundResource(R.drawable.qureka_inter5);
//        }
//
//        (dialog.findViewById(R.id.qurekaAds)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                GetData.OpenQureka((Activity) context);
//
//            }
//        });
//
//        (dialog.findViewById(R.id.ll_qureka)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                GetData.OpenQureka((Activity) context);
//            }
//        });
//
//        (dialog.findViewById(R.id.qurekaAdsClose)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                onFinishAds.onFinishAds(true);
//            }
//        });
//
//        dialog.setOnKeyListener(new Dialog.OnKeyListener() {
//
//            @Override
//            public boolean onKey(DialogInterface arg0, int keyCode,
//                                 KeyEvent event) {
//                // TODO Auto-generated method stub
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    dialog.dismiss();
//                    onFinishAds.onFinishAds(true);
//                }
//                return true;
//            }
//        });
//
//        dialog.show();
//    }
//
//
//    //</editor-fold>
//
//
//    //<editor-fold desc="banner ads">
//
//
////    public static HashMap<Integer, AdView> admob_hashmap = new HashMap<>();
////
////    public static void banner_show(final Activity context, final LinearLayout linearLayout, final TextView txt_ads_load, int position) {
////        AdmobBanner(context, linearLayout, txt_ads_load, position);
////    }
////
////    public static LinearLayout adView;
////    public static String adview_id = "";
////
////    public static void AdmobBanner(final Activity context, final LinearLayout linearLayout, final TextView txt_ads_load, int position) {
////
////        adview_id = Preference.getadmob_banner_id();
////
////        AdView adView = new AdView(context);
////        adView.setAdUnitId(adview_id);
////        Log.d("native_id", "" + adview_id);
////
////        linearLayout.removeAllViews();
////        linearLayout.addView(adView);
////        AdRequest adRequest = new AdRequest.Builder().build();
////        adView.setAdSize(getAdSize(context));
////        adView.loadAd(adRequest);
////
////        adView.setAdListener(new AdListener() {
////            @Override
////            public void onAdClosed() {
////                super.onAdClosed();
////                // Code to be executed when the user is about to return
////            }
////
////            @Override
////            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
////                super.onAdFailedToLoad(loadAdError);
////
////                txt_ads_load.setVisibility(View.GONE);
////                linearLayout.setVisibility(View.GONE);
////
////                Log.d("Admob_ads_check", "Banner---onAdFailedToLoad: " + loadAdError);
////            }
////
////            @Override
////            public void onAdOpened() {
////                super.onAdOpened();
////            }
////
////            @Override
////            public void onAdLoaded() {
////                super.onAdLoaded();
////                admob_hashmap.put(position, (AdView) adView);
////                txt_ads_load.setVisibility(View.GONE);
////                linearLayout.setVisibility(View.VISIBLE);
////// Code to be executed when an ad finishes loading.
////                Log.d("Admob_ads_check", "Banner---onAdLoad: ");
////            }
////
////            @Override
////            public void onAdClicked() {
////                super.onAdClicked();
//////                Appcontroller.fast_start = true;
////                Log.d("Admob_ads_check", "Banner---onAdFailedToLoad: clicks");
////            }
////
////            @Override
////            public void onAdImpression() {
////                super.onAdImpression();
////            }
////        });
////    }
////
////    public static AdSize getAdSize(Context context) {
////        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
////        DisplayMetrics outMetrics = new DisplayMetrics();
////        display.getMetrics(outMetrics);
////        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, (int) (((float) outMetrics.widthPixels) / outMetrics.density));
////    }
//
//    //</editor-fold>
//
//
//    //<editor-fold desc="Native ads">
//
//    public static LinearLayout native_adView;
//    public static NativeAd admobnativeAd;
//    public static NativeAd admobnativeAd_banner;
//    public static HashMap<Integer, NativeAdView> admob_nativehashmap = new HashMap<>();
//
//
//    public static void Native_adtype_old(final Context context, final LinearLayout llnative, final LinearLayout llline, final LinearLayout ll_ads, int position, boolean isInvisble, boolean isMainScreen) {
//
//
//        if (!Preference.getAdmob_page()) {
//            if (admobnativeAd != null) {
//                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//
//                if (isMainScreen) {
////                adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefull, null);
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefull1, null);
//                } else {
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefull2, null);
//                }
//
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView(admobnativeAd, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
//                admob_nativehashmap.put(position, adView);
//            } else {
//                Big_qureka_native(context, llnative, llline, ll_ads, 0, isInvisble, isMainScreen);
//            }
//
//        } else if (Preference.getAdmob_page() && admob_native_int != Preference.getnative_admob()) {
//            admob_native_int++;
//
//            if (admobnativeAd != null) {
//                LayoutInflater inflater = (LayoutInflater) context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//
//                if (isMainScreen) {
////                adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefull, null);
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefull1, null);
//                } else {
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefull2, null);
//                }
//
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView(admobnativeAd, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
//                admob_nativehashmap.put(position, adView);
//            } else {
//                Big_qureka_native(context, llnative, llline, ll_ads, 0, isInvisble, isMainScreen);
//            }
//        } else {
//            admob_native_int = 0;
//            if (Preference.getis_big_native_qureka()) {
//                Big_qureka_native(context, llnative, llline, ll_ads, 0, isInvisble, isMainScreen);
//            } else {
//                if (admobnativeAd != null) {
//                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    NativeAdView adView = null;
//                    if (isMainScreen) {
//                        adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefull1, null);
//                    } else {
//                        adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefull2, null);
//                    }
//
//                    llline.setVisibility(View.GONE);
//                    llnative.setVisibility(View.VISIBLE);
//                    populateUnifiedNativeAdView(admobnativeAd, adView, false);
//                    Log.e("Ads", "admob Native ad show : ");
//                    llnative.removeAllViews();
//                    llnative.addView(adView);
//                    admob_nativehashmap.put(position, adView);
//                } else {
//                    Big_qureka_native(context, llnative, llline, ll_ads, 0, isInvisble, isMainScreen);
//                }
//            }
//        }
//        Admob_native(context);
//
//    }
//
//    public static void Native_adtype(final Context context, final LinearLayout llnative, final LinearLayout llline, final LinearLayout ll_ads, int position, boolean isInvisble, boolean isMainScreen) {
//        if (Preference.getShow_ads_type().equals("admob")) {
//            if (admobnativeAd != null) {
//                LayoutInflater inflater = (LayoutInflater) context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//                if (isMainScreen) {
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefull1, null);
//                } else {
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefull2, null);
//                }
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView(admobnativeAd, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
//                admob_nativehashmap.put(position, adView);
//            } else if (fb_native_adpater != null && fb_native_adpater.isAdLoaded()) {
//                Log.d("NativeFull_Show", "fb loaded ");
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                llnative.removeAllViews();
//                LayoutInflater inflater = LayoutInflater.from(context);
//                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ads_fb_native_full, nativeAdLayout, false);
//                llnative.addView(adView);
//                fb_inflateAd_full(context, fb_native_adpater, llnative, llline, adView);
//            } else {
//                Big_qureka_native(context, llnative, llline, ll_ads, 0, isInvisble, isMainScreen);
//            }
//            Admob_native(context);
//        } else {
//            if (fb_native_adpater != null && fb_native_adpater.isAdLoaded()) {
//                Log.d("NativeFull_Show", "fb loaded ");
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                llnative.removeAllViews();
//                LayoutInflater inflater = LayoutInflater.from(context);
//                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ads_fb_native_full, nativeAdLayout, false);
//                llnative.addView(adView);
//                fb_inflateAd_full(context, fb_native_adpater, llnative, llline, adView);
//            } else if (admobnativeAd != null) {
//                LayoutInflater inflater = (LayoutInflater) context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//                if (isMainScreen) {
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefull1, null);
//                } else {
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefull2, null);
//                }
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView(admobnativeAd, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
//                admob_nativehashmap.put(position, adView);
//            } else {
//                Big_qureka_native(context, llnative, llline, ll_ads, 0, isInvisble, isMainScreen);
//            }
//
//            FB_NativeAd(context);
//        }
//    }
//
//    public static void Big_qureka_native(final Context context, final LinearLayout llnative, final LinearLayout llline, final LinearLayout ll_ads, int position, boolean isInvisble, boolean isMainScreen) {
//        if (Preference.getis_big_native_qureka()) {
//
//            View layout2 = null;
//
//            llline.setVisibility(View.GONE);
////            llnative.setVisibility(View.GONE);
//            llnative.setVisibility(View.VISIBLE);
//            if (isMainScreen) {
//                layout2 = LayoutInflater.from(context).inflate(R.layout.native_qurekha, llnative, false);
//            } else {
//                layout2 = LayoutInflater.from(context).inflate(R.layout.native_qurekha2, llnative, false);
//            }
//            layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(Utils.getQurekaNativeAds());
//            layout2.findViewById(R.id.rl_qurekha).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    try {
//                        Intent intent = new Intent("android.intent.action.VIEW");
//                        Bundle bundle = new Bundle();
//                        bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
//                        intent.putExtras(bundle);
//                        intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", context.getResources().getColor(R.color.colorPrimary));
//                        intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
//                        intent.setPackage("com.android.chrome");
//                        intent.setData(Uri.parse(Preference.getlink()));
//                        context.startActivity(intent, (Bundle) null);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//
//            ((LinearLayout) layout2.findViewById(R.id.playNowLL)).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Preference.getnative_button_color())));
//            ((TextView) layout2.findViewById(R.id.tv_playnow)).setTextColor(Color.parseColor(Preference.getnative_btn_text_color()));
//
//            makeMeBlink((LinearLayout) layout2.findViewById(R.id.playNowLL));
//
//            layout2.findViewById(R.id.playNowLL).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    try {
//                        Intent intent = new Intent("android.intent.action.VIEW");
//                        Bundle bundle = new Bundle();
//                        bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
//                        intent.putExtras(bundle);
//                        intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", context.getResources().getColor(R.color.colorPrimary));
//                        intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
//                        intent.setPackage("com.android.chrome");
//                        intent.setData(Uri.parse(Preference.getlink()));
//                        context.startActivity(intent, (Bundle) null);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//            layout2.findViewById(R.id.qurekaLL1).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    try {
//                        Intent intent = new Intent("android.intent.action.VIEW");
//                        Bundle bundle = new Bundle();
//                        bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
//                        intent.putExtras(bundle);
//                        intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", context.getResources().getColor(R.color.colorPrimary));
//                        intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
//                        intent.setPackage("com.android.chrome");
//                        intent.setData(Uri.parse(Preference.getlink()));
//                        context.startActivity(intent, (Bundle) null);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//            layout2.findViewById(R.id.ll_header).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    try {
//                        Intent intent = new Intent("android.intent.action.VIEW");
//                        Bundle bundle = new Bundle();
//                        bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
//                        intent.putExtras(bundle);
//                        intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", context.getResources().getColor(R.color.colorPrimary));
//                        intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
//                        intent.setPackage("com.android.chrome");
//                        intent.setData(Uri.parse(Preference.getlink()));
//                        context.startActivity(intent, (Bundle) null);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//            llnative.removeAllViews();
//            llnative.addView(layout2);
//        } else {
//
//            llline.setVisibility(View.GONE);
//            llnative.setVisibility(View.GONE);
//            ll_ads.setVisibility(View.GONE);
//
//        }
//    }
//
//
//    public static Integer admob_native_banner_int = 0;
//    public static Integer admob_native_int = 0;
//
//    public static void Native_Adslist_vertical(final Context context, final LinearLayout llnative, final LinearLayout llline, final LinearLayout ll_ads, int position) {
//
//        if (!Preference.getAdmob_page()) {
//            if (admobnativeAd != null) {
//                LayoutInflater inflater = (LayoutInflater) context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//                adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist, null);
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView(admobnativeAd, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
//                admob_nativehashmap.put(position, adView);
//            } else {
//                Native_Adslist_qureka(context, llnative, llline, ll_ads, position);
//            }
//        } else if (Preference.getAdmob_page() && admob_native_int != Preference.getnative_admob()) {
//            admob_native_int++;
//            if (admobnativeAd != null) {
//                LayoutInflater inflater = (LayoutInflater) context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//                adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist, null);
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView(admobnativeAd, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
//                admob_nativehashmap.put(position, adView);
//            } else {
//                Native_Adslist_qureka(context, llnative, llline, ll_ads, position);
//            }
//        } else {
//            admob_native_int = 0;
//            if (Preference.getis_big_native_qureka()) {
//                Native_Adslist_qureka(context, llnative, llline, ll_ads, position);
//            } else {
//                if (admobnativeAd != null) {
//                    LayoutInflater inflater = (LayoutInflater) context
//                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    NativeAdView adView = null;
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist, null);
//                    llline.setVisibility(View.GONE);
//                    llnative.setVisibility(View.VISIBLE);
//                    populateUnifiedNativeAdView(admobnativeAd, adView, false);
//                    Log.e("Ads", "admob Native ad show : ");
//                    llnative.removeAllViews();
//                    llnative.addView(adView);
//                    admob_nativehashmap.put(position, adView);
//                } else {
//                    llline.setVisibility(View.GONE);
//                    llnative.setVisibility(View.GONE);
//                    ll_ads.setVisibility(View.GONE);
//                }
//            }
//        }
//
//        Admob_native(context);
//    }
//
//
//    public static void Native_Adslist_old(final Context context, final LinearLayout llnative, final LinearLayout llline, final LinearLayout ll_ads, int position) {
//
//        if (!Preference.getAdmob_page()) {
//            if (admobnativeAd != null) {
//                LayoutInflater inflater = (LayoutInflater) context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//                adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist_vertical, null);
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView(admobnativeAd, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
//                admob_nativehashmap.put(position, adView);
//            } else {
//                Native_Adslist_qureka_vertical(context, llnative, llline, ll_ads, position);
//            }
//        } else if (Preference.getAdmob_page() && admob_native_int != Preference.getnative_admob()) {
//            admob_native_int++;
//            if (admobnativeAd != null) {
//                LayoutInflater inflater = (LayoutInflater) context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//                adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist_vertical, null);
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView(admobnativeAd, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
//                admob_nativehashmap.put(position, adView);
//            } else {
//                Native_Adslist_qureka_vertical(context, llnative, llline, ll_ads, position);
//            }
//        } else {
//            admob_native_int = 0;
//            if (Preference.getis_big_native_qureka()) {
//                Native_Adslist_qureka_vertical(context, llnative, llline, ll_ads, position);
//            } else {
//                if (admobnativeAd != null) {
//                    LayoutInflater inflater = (LayoutInflater) context
//                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    NativeAdView adView = null;
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist_vertical, null);
//                    llline.setVisibility(View.GONE);
//                    llnative.setVisibility(View.VISIBLE);
//                    populateUnifiedNativeAdView(admobnativeAd, adView, false);
//                    Log.e("Ads", "admob Native ad show : ");
//                    llnative.removeAllViews();
//                    llnative.addView(adView);
//                    admob_nativehashmap.put(position, adView);
//                } else {
//                    llline.setVisibility(View.GONE);
//                    llnative.setVisibility(View.GONE);
//                    ll_ads.setVisibility(View.GONE);
//                }
//            }
//        }
//
//        Admob_native(context);
//    }
//
//    public static void Native_Adslist(final Context context, final LinearLayout llnative, final LinearLayout llline, final LinearLayout ll_ads, int position) {
//        if (Preference.getShow_ads_type().equals("admob")) {
//            if (admobnativeAd != null) {
//                LayoutInflater inflater = (LayoutInflater) context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//                adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist_vertical, null);
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView(admobnativeAd, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
//                admob_nativehashmap.put(position, adView);
//            } else if (fb_native_adpater != null && fb_native_adpater.isAdLoaded()) {
//                Log.d("NativeFull_Show", "fb loaded ");
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                llnative.removeAllViews();
//                LayoutInflater inflater = LayoutInflater.from(context);
//                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ads_fb_nativefulllist_vertical, nativeAdLayout, false);
//                llnative.addView(adView);
//                fb_inflateAd_full(context, fb_native_adpater, llnative, llline, adView);
//            } else {
//                Native_Adslist_qureka_vertical(context, llnative, llline, ll_ads, position);
//            }
//            Admob_native(context);
//        } else {
//            if (fb_native_adpater != null && fb_native_adpater.isAdLoaded()) {
//                Log.d("NativeFull_Show", "fb loaded ");
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                llnative.removeAllViews();
//                LayoutInflater inflater = LayoutInflater.from(context);
//                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ads_fb_nativefulllist_vertical, nativeAdLayout, false);
//                llnative.addView(adView);
//                fb_inflateAd_full(context, fb_native_adpater, llnative, llline, adView);
//            } else if (admobnativeAd != null) {
//                LayoutInflater inflater = (LayoutInflater) context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//                adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist_vertical, null);
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView(admobnativeAd, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
//                admob_nativehashmap.put(position, adView);
//            } else {
//                Native_Adslist_qureka_vertical(context, llnative, llline, ll_ads, position);
//            }
//            FB_NativeAd(context);
//        }
//    }
//
//    public static void Native_Adslist_qureka(final Context context, final LinearLayout llnative, final LinearLayout llline, final LinearLayout ll_ads, int position) {
//        if (Preference.getis_big_native_qureka()) {
//
//            llline.setVisibility(View.GONE);
//            llnative.setVisibility(View.VISIBLE);
//
//            View layout2 = LayoutInflater.from(context).inflate(R.layout.native_qurekhalist, llnative, false);
//            TextView tv_text_ad_name = layout2.findViewById(R.id.tv_text_ad_name);
//            TextView tv_text_ad_desc = layout2.findViewById(R.id.tv_text_ad_desc);
//            GifImageView iv_round_gif = layout2.findViewById(R.id.iv_round_gif);
//            int random = new Random().nextInt(5);
//            if (random == 1) {
//                tv_text_ad_name.setText("Play Cricket Win Coins");
//                tv_text_ad_desc.setText("Win 5,00,000 Coins & More");
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native1);
//                iv_round_gif.setBackgroundResource(R.drawable.qureka_round1);
//            } else if (random == 2) {
//                tv_text_ad_name.setText("Play Bubble Shooter Game");
//                tv_text_ad_desc.setText("Win 50,000 Coins With Mobile Games");
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native2);
//                iv_round_gif.setBackgroundResource(R.drawable.qureka_round2);
//            } else if (random == 3) {
//                tv_text_ad_name.setText("Play Fruit Chop Game");
//                tv_text_ad_desc.setText("Win 50,000 Coins No Install Required");
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native3);
//                iv_round_gif.setBackgroundResource(R.drawable.qureka_round3);
//            } else if (random == 4) {
//                tv_text_ad_name.setText("Play Don't Crash Game");
//                tv_text_ad_desc.setText("Collect 50,000 Coins Now");
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native4);
//                iv_round_gif.setBackgroundResource(R.drawable.qureka_round_4);
//            } else {
//                tv_text_ad_name.setText("Play Car Racing Game");
//                tv_text_ad_desc.setText("Win Coin & No Installation Required");
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native5);
//                iv_round_gif.setBackgroundResource(R.drawable.qureka_round1);
//            }
//            layout2.findViewById(R.id.rl_qurekha).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    GetData.OpenQureka((Activity) context);
//
//                }
//            });
//
//            ((Button) layout2.findViewById(R.id.btn_play)).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Preference.getnative_button_color())));
//            ((Button) layout2.findViewById(R.id.btn_play)).setTextColor(Color.parseColor(Preference.getnative_btn_text_color()));
//
//            makeMeBlink((Button) layout2.findViewById(R.id.btn_play));
//            layout2.findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    GetData.OpenQureka((Activity) context);
//                }
//            });
//
//            llnative.removeAllViews();
//            llnative.addView(layout2);
//        } else {
//            llline.setVisibility(View.GONE);
//            llnative.setVisibility(View.GONE);
//            ll_ads.setVisibility(View.GONE);
//        }
//    }
//
//
//    public static void Native_Adslist_qureka_vertical(final Context context, final LinearLayout llnative, final LinearLayout llline, final LinearLayout ll_ads, int position) {
//        if (Preference.getis_big_native_qureka()) {
//
//            llline.setVisibility(View.GONE);
//            llnative.setVisibility(View.VISIBLE);
//
//            View layout2 = LayoutInflater.from(context).inflate(R.layout.native_qurekhalist_vertical, llnative, false);
//            TextView tv_text_ad_name = layout2.findViewById(R.id.tv_text_ad_name);
//            TextView tv_text_ad_desc = layout2.findViewById(R.id.tv_text_ad_desc);
//
//            GifImageView iv_round_gif = layout2.findViewById(R.id.iv_round_gif);
//            int random = new Random().nextInt(5);
//            if (random == 1) {
//                tv_text_ad_name.setText("Play Cricket Win Coins");
//                tv_text_ad_desc.setText("Win 5,00,000 Coins & More");
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native1);
//                iv_round_gif.setBackgroundResource(R.drawable.qureka_round1);
//            } else if (random == 2) {
//                tv_text_ad_name.setText("Play Bubble Shooter Game");
//                tv_text_ad_desc.setText("Win 50,000 Coins With Mobile Games");
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native2);
//                iv_round_gif.setBackgroundResource(R.drawable.qureka_round2);
//            } else if (random == 3) {
//                tv_text_ad_name.setText("Play Fruit Chop Game");
//                tv_text_ad_desc.setText("Win 50,000 Coins No Install Required");
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native3);
//                iv_round_gif.setBackgroundResource(R.drawable.qureka_round3);
//            } else if (random == 4) {
//                tv_text_ad_name.setText("Play Don't Crash Game");
//                tv_text_ad_desc.setText("Collect 50,000 Coins Now");
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native4);
//                iv_round_gif.setBackgroundResource(R.drawable.qureka_round_4);
//            } else {
//                tv_text_ad_name.setText("Play Car Racing Game");
//                tv_text_ad_desc.setText("Win Coin & No Installation Required");
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native5);
//                iv_round_gif.setBackgroundResource(R.drawable.qureka_round1);
//            }
//            layout2.findViewById(R.id.rl_qurekha).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    GetData.OpenQureka((Activity) context);
//
//                }
//            });
//
//            ((LinearLayout) layout2.findViewById(R.id.playNowLL)).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Preference.getnative_button_color())));
//            ((TextView) layout2.findViewById(R.id.tv_playnow)).setTextColor(Color.parseColor(Preference.getnative_btn_text_color()));
//
//            makeMeBlink((LinearLayout) layout2.findViewById(R.id.playNowLL));
//
//            layout2.findViewById(R.id.tv_playnow).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    GetData.OpenQureka((Activity) context);
//                }
//            });
//
//            llnative.removeAllViews();
//            llnative.addView(layout2);
//        } else {
//            llline.setVisibility(View.GONE);
//            llnative.setVisibility(View.GONE);
//            ll_ads.setVisibility(View.GONE);
//        }
//    }
//
//
//    public static void Native_Adslist_banner(final Context context, final LinearLayout llnative, final LinearLayout llline, final LinearLayout ll_ads, int position) {
//
//        if (!Preference.getAdmob_page()) {
//            if (admobnativeAd_banner != null) {
//                LayoutInflater inflater = (LayoutInflater) context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//
//                adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist, null);
//
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView(admobnativeAd_banner, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
//            } else {
//                Native_Adslist_qureka(context, llnative, llline, ll_ads, position);
//            }
//        } else if (Preference.getAdmob_page() && admob_native_banner_int != Preference.getbanner_admob()) {
//            admob_native_banner_int++;
//            if (admobnativeAd_banner != null) {
//                LayoutInflater inflater = (LayoutInflater) context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//
//                adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist, null);
//
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView(admobnativeAd_banner, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
//            } else {
//                Native_Adslist_qureka(context, llnative, llline, ll_ads, position);
//            }
//        } else {
//            admob_native_banner_int = 0;
//
//            if (Preference.getis_small_native_qureka()) {
//                Native_Adslist_qureka(context, llnative, llline, ll_ads, position);
//            } else {
//                if (admobnativeAd_banner != null) {
//                    LayoutInflater inflater = (LayoutInflater) context
//                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    NativeAdView adView = null;
//
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist, null);
//
//                    llline.setVisibility(View.GONE);
//                    llnative.setVisibility(View.VISIBLE);
//                    populateUnifiedNativeAdView(admobnativeAd_banner, adView, false);
//                    Log.e("Ads", "admob Native ad show : ");
//                    llnative.removeAllViews();
//                    llnative.addView(adView);
//                } else {
//                    llline.setVisibility(View.GONE);
//                    llnative.setVisibility(View.GONE);
//                    ll_ads.setVisibility(View.GONE);
//                }
//            }
//
//        }
//
//        Admob_native_banner(context);
//    }
//
//    public static void Banner_Native_adtype_old(final Context context, final LinearLayout llnative, final LinearLayout llline, final LinearLayout ll_ads, int position, boolean isList) {
//
//        if (!Preference.getAdmob_page()) {
//            if (admobnativeAd_banner != null) {
//                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//
//                if (!isList) {
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_native_banner, null);
//                } else {
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist_vertical, null);
//                }
//
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView_banner(admobnativeAd_banner, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
////              admob_nativehashmap.put(position, adView);
//            } else {
//                Banner_Native_qureka(context, llnative, llline, ll_ads, 0, isList);
//            }
//        } else if (Preference.getAdmob_page() && admob_native_banner_int != Preference.getbanner_admob()) {
//            admob_native_banner_int++;
//            if (admobnativeAd_banner != null) {
//                LayoutInflater inflater = (LayoutInflater) context
//                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//
//                if (!isList) {
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_native_banner, null);
//                } else {
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist_vertical, null);
//                }
//
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView_banner(admobnativeAd_banner, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
////            admob_nativehashmap.put(position, adView);
//            } else {
//
//                Banner_Native_qureka(context, llnative, llline, ll_ads, 0, isList);
//
//            }
//        } else {
//            admob_native_banner_int = 0;
//            if (Preference.getis_small_native_qureka()) {
//
//                Banner_Native_qureka(context, llnative, llline, ll_ads, 0, isList);
//
//            } else {
//                if (admobnativeAd_banner != null) {
//                    LayoutInflater inflater = (LayoutInflater) context
//                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    NativeAdView adView = null;
//
//                    if (!isList) {
//                        adView = (NativeAdView) inflater.inflate(R.layout.admob_native_banner, null);
//                    } else {
//                        adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist_vertical, null);
//                    }
//
//                    llline.setVisibility(View.GONE);
//                    llnative.setVisibility(View.VISIBLE);
//                    populateUnifiedNativeAdView_banner(admobnativeAd_banner, adView, false);
//                    Log.e("Ads", "admob Native ad show : ");
//                    llnative.removeAllViews();
//                    llnative.addView(adView);
////            admob_nativehashmap.put(position, adView);
//                } else {
//                    llline.setVisibility(View.GONE);
//                    llnative.setVisibility(View.GONE);
//                    ll_ads.setVisibility(View.GONE);
//                }
//            }
//        }
//
//
//        Admob_native_banner(context);
//    }
//
//    public static void Banner_Native_adtype(final Context context, final LinearLayout llnative, final LinearLayout llline, final LinearLayout ll_ads, int position, boolean isList) {
//        if (Preference.getShow_ads_type().equals("admob")) {
//            if (admobnativeAd_banner != null) {
//                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//                if (!isList) {
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_native_banner, null);
//                } else {
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist_vertical, null);
//                }
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView_banner(admobnativeAd_banner, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
//            } else if (fb_native_adpater != null && fb_native_adpater.isAdLoaded()) {
//                Log.d("NativeFull_Show", "fb loaded ");
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                llnative.removeAllViews();
//                LayoutInflater inflater = LayoutInflater.from(context);
//                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ads_fb_nativefulllist_vertical, nativeAdLayout, false);
//                llnative.addView(adView);
//                fb_inflateAd_full(context, fb_native_adpater, llnative, llline, adView);
//
//            } else {
//                Banner_Native_qureka(context, llnative, llline, ll_ads, 0, isList);
//            }
//            Admob_native_banner(context);
//        } else {
//            if (fb_native_adpater != null && fb_native_adpater.isAdLoaded()) {
//                Log.d("NativeFull_Show", "fb loaded ");
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                llnative.removeAllViews();
//                LayoutInflater inflater = LayoutInflater.from(context);
//                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.ads_fb_nativefulllist_vertical, nativeAdLayout, false);
//                llnative.addView(adView);
//                fb_inflateAd_full(context, fb_native_adpater, llnative, llline, adView);
//            } else if (admobnativeAd_banner != null) {
//                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                NativeAdView adView = null;
//                if (!isList) {
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_native_banner, null);
//                } else {
//                    adView = (NativeAdView) inflater.inflate(R.layout.admob_nativefulllist_vertical, null);
//                }
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                populateUnifiedNativeAdView_banner(admobnativeAd_banner, adView, false);
//                Log.e("Ads", "admob Native ad show : ");
//                llnative.removeAllViews();
//                llnative.addView(adView);
//            } else {
//                Banner_Native_qureka(context, llnative, llline, ll_ads, 0, isList);
//            }
//            FB_NativeAd(context);
//        }
//
//    }
//
//
//    public static void Banner_Native_qureka(final Context context, final LinearLayout llnative, final LinearLayout llline, final LinearLayout ll_ads, int position, boolean isList) {
//        if (Preference.getis_small_native_qureka()) {
//            llline.setVisibility(View.GONE);
//            llnative.setVisibility(View.VISIBLE);
//            View layout2;
//            if (isList) {
//                layout2 = LayoutInflater.from(context).inflate(R.layout.native_qurekhalist_vertical, llnative, false);
//            } else {
//                layout2 = LayoutInflater.from(context).inflate(R.layout.ads_banner, llnative, false);
//            }
//            //  layout2.findViewById(R.id.rlBanner).setBackgroundResource(Utils.getQurekaBannerAds());
//
//            TextView tv_text_ad_name = layout2.findViewById(R.id.tv_text_ad_name);
//            TextView tv_text_ad_desc = layout2.findViewById(R.id.tv_text_ad_desc);
//            GifImageView iv_round_gif = layout2.findViewById(R.id.iv_round_gif);
//            int random = new Random().nextInt(5);
//            if (random == 1) {
//                tv_text_ad_name.setText("Play & Win Coins");
//                tv_text_ad_desc.setText("Win 5,00,000 Coins & More");
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native1);
//                iv_round_gif.setImageResource(R.drawable.qureka_round1);
//            } else if (random == 2) {
//                tv_text_ad_name.setText("Play Bubble Shooter Game");
//                tv_text_ad_desc.setText("Win 50,000 Coins With Mobile Games");
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native2);
//                iv_round_gif.setImageResource(R.drawable.qureka_round2);
//            } else if (random == 3) {
//                tv_text_ad_name.setText("Play Fruit Chop Game");
//                tv_text_ad_desc.setText("Win 50,000 Coins No Install Required");
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native3);
//                iv_round_gif.setImageResource(R.drawable.qureka_round3);
//            } else if (random == 4) {
//                tv_text_ad_name.setText("Play Don't Crash Game");
//                tv_text_ad_desc.setText("Collect 50,000 Coins Now");
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native4);
//                iv_round_gif.setImageResource(R.drawable.qureka_round_4);
//            } else {
//                tv_text_ad_name.setText("Play Car Racing Game");
//                tv_text_ad_desc.setText("Win Coin & No Installation Required");
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native5);
//                iv_round_gif.setImageResource(R.drawable.qureka_round1);
//            }
//
//            ((LinearLayout) layout2.findViewById(R.id.playNowLL)).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Preference.getnative_button_color())));
//            ((TextView) layout2.findViewById(R.id.tv_playnow)).setTextColor(Color.parseColor(Preference.getnative_btn_text_color()));
//
//            makeMeBlink(layout2.findViewById(R.id.playNowLL));
//            layout2.findViewById(R.id.rlBanner).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    try {
//                        Intent intent = new Intent("android.intent.action.VIEW");
//                        Bundle bundle = new Bundle();
//                        bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
//                        intent.putExtras(bundle);
//                        intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", context.getResources().getColor(R.color.colorPrimary));
//                        intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
//                        intent.setPackage("com.android.chrome");
//                        intent.setData(Uri.parse(Preference.getlink()));
//                        context.startActivity(intent, (Bundle) null);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//            llnative.addView(layout2);
//        } else {
//            llline.setVisibility(View.GONE);
//            llnative.setVisibility(View.GONE);
//            ll_ads.setVisibility(View.INVISIBLE);
//        }
//    }
//
//    public static void Native_exit(final Context context, final LinearLayout llnative, final LinearLayout llline, TextView ad_call_to_action) {
//
//
//        if (admobnativeAd != null) {
//            LayoutInflater inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            NativeAdView adView = null;
//
//
//            adView = (NativeAdView) inflater.inflate(R.layout.ads_admob_native_exit, null);
//
//
//            llline.setVisibility(View.GONE);
//            llnative.setVisibility(View.VISIBLE);
//            populateUnifiedNativeAdView_exit(admobnativeAd, adView, false, ad_call_to_action);
//            Log.e("Ads", "admob Native ad show : ");
//            llnative.removeAllViews();
//            llnative.addView(adView);
////            admob_nativehashmap.put(position, adView);
//        } else {
//            if (Preference.getis_big_native_qureka()) {
//
//                llline.setVisibility(View.GONE);
////            llnative.setVisibility(View.GONE);
//                llnative.setVisibility(View.VISIBLE);
//                View layout2 = LayoutInflater.from(context).inflate(R.layout.native_qurekha, llnative, false);
//                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(Utils.getQurekaNativeAds());
//                ((LinearLayout) layout2.findViewById(R.id.playNowLL)).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Preference.getnative_button_color())));
//                ad_call_to_action.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Preference.getnative_button_color())));
//                ((TextView) layout2.findViewById(R.id.tv_playnow)).setTextColor(Color.parseColor(Preference.getnative_btn_text_color()));
//                ad_call_to_action.setTextColor(Color.parseColor(Preference.getnative_btn_text_color()));
//                layout2.findViewById(R.id.rl_qurekha).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try {
//                            Intent intent = new Intent("android.intent.action.VIEW");
//                            Bundle bundle = new Bundle();
//                            bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
//                            intent.putExtras(bundle);
//                            intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", context.getResources().getColor(R.color.colorPrimary));
//                            intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
//                            intent.setPackage("com.android.chrome");
//                            intent.setData(Uri.parse(Preference.getlink()));
//                            context.startActivity(intent, (Bundle) null);
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
//
//                layout2.findViewById(R.id.playNowLL).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try {
//                            Intent intent = new Intent("android.intent.action.VIEW");
//                            Bundle bundle = new Bundle();
//                            bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
//                            intent.putExtras(bundle);
//                            intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", context.getResources().getColor(R.color.colorPrimary));
//                            intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
//                            intent.setPackage("com.android.chrome");
//                            intent.setData(Uri.parse(Preference.getlink()));
//                            context.startActivity(intent, (Bundle) null);
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
//
//
//                layout2.findViewById(R.id.qurekaLL1).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try {
//                            Intent intent = new Intent("android.intent.action.VIEW");
//                            Bundle bundle = new Bundle();
//                            bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
//                            intent.putExtras(bundle);
//                            intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", context.getResources().getColor(R.color.colorPrimary));
//                            intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
//                            intent.setPackage("com.android.chrome");
//                            intent.setData(Uri.parse(Preference.getlink()));
//                            context.startActivity(intent, (Bundle) null);
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
//                layout2.findViewById(R.id.ll_header).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try {
//                            Intent intent = new Intent("android.intent.action.VIEW");
//                            Bundle bundle = new Bundle();
//                            bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
//                            intent.putExtras(bundle);
//                            intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", context.getResources().getColor(R.color.colorPrimary));
//                            intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
//                            intent.setPackage("com.android.chrome");
//                            intent.setData(Uri.parse(Preference.getlink()));
//                            context.startActivity(intent, (Bundle) null);
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                });
//
//                ad_call_to_action.setText("Click Here");
//
//                ad_call_to_action.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try {
//                            Intent intent = new Intent("android.intent.action.VIEW");
//                            Bundle bundle = new Bundle();
//                            bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
//                            intent.putExtras(bundle);
//                            intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", context.getResources().getColor(R.color.colorPrimary));
//                            intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
//                            intent.setPackage("com.android.chrome");
//                            intent.setData(Uri.parse(Preference.getlink()));
//                            context.startActivity(intent, (Bundle) null);
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//                llnative.removeAllViews();
//                llnative.addView(layout2);
//            } else {
//                llline.setVisibility(View.GONE);
//                llnative.setVisibility(View.GONE);
//
//            }
//        }
//        Admob_native(context);
//
//
//    }
//
//
//    public static int admob_native_array_int = Preference.getNativeArrayINT();
//    public static String admob_native_id = "";
//
//    public static void NativeFullIdsRandomId() {
//        Log.d("AdmobNativeFull", "NativeFullIdsRandomId");
//        try {
//            if (admob_native_list != null && admob_native_list.size() != 0 && admob_native_list.size() != admob_native_array_int && admob_native_list.size() >= admob_native_array_int) {
//                Log.d("AdmobNativeFull", "NativeFullIdsRandomId  if " + Preference.getNativeArrayINT());
//                admob_native_id = admob_native_list.get(Preference.getNativeArrayINT());
//                Preference.setadmob_native_id(admob_native_id);
//               /* admob_native_array_int = admob_native_array_int + 1;
//                Preference.setNativeArrayINT(admob_native_array_int);*/
//            } else {
//                Log.d("AdmobNativeFull", "NativeFullIdsRandomId  else");
//                admob_native_array_int = 0;
//                Preference.setNativeArrayINT(admob_native_array_int);
//                Log.d("AdmobNativeFull", "NativeFullIdsRandomId NativeFullIdsRandomId else   " + Preference.getNativeArrayINT());
//                admob_native_id = admob_native_list.get(Preference.getNativeArrayINT());
//                Preference.setadmob_native_id(admob_native_id);
//              /*  admob_native_array_int = admob_native_array_int + 1;
//                Preference.setNativeArrayINT(admob_native_array_int);*/
//            }
//        } catch (NullPointerException n) {
//            n.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void Admob_native(final Context context/*, LinearLayout linearLayout, LinearLayout lnr_view*//*, int position, LinearLayout ll_ads , boolean isFromalbum*/) {
//
//        NativeFullIdsRandomId();
//        Log.e("AdmobAds", "admob adviewnative : ");
//        AdLoader.Builder builder = new AdLoader.Builder(context, Preference.getadmob_native_id())
//                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
//                    @Override
//                    public void onNativeAdLoaded(NativeAd nativeAd) {
//                        // Assumes you have a placeholder FrameLayout in your View layout
//                        // (with id fl_adplaceholder) where the ad is to be placed.
//                        Log.d("AdmobAds", "on loaded");
//                        if (admobnativeAd != null) {
//                            admobnativeAd = null;
//                        }
//
//                        admobnativeAd = nativeAd;
//                        if (Preference.getadmob_load_type()) {
//                            if (admob_native_list.size() != Preference.getNativeArrayINT() && admob_native_list.size() > 1) {
//                                Log.d("AdmobNativeFull", "BOOL TRUE VALUE PLUS");
//                                admob_native_array_int = admob_native_array_int + 1;
//                                Preference.setNativeArrayINT(admob_native_array_int);
//                            } else {
//                                Log.d("AdmobNativeFull", "ELSE CONDITION");
//                            }
//                        }
//                        is_AdmobNativeload = true;
//                        Log.e("AdmobAds", "admob Native ad show : ");
//                    }
//                }).withAdListener(new AdListener() {
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        super.onAdFailedToLoad(loadAdError);
//                        if (admobnativeAd != null) {
//                            admobnativeAd = null;
//                        }
//                        Log.d("AdmobAds", "on failed");
//
//                        if (admob_native_list.size() != Preference.getNativeArrayINT() && admob_native_list.size() > 1) {
//                            Log.d("AdmobNativeFull", "failed to re-load");
//                            admob_native_array_int = admob_native_array_int + 1;
//                            Preference.setNativeArrayINT(admob_native_array_int);
//                            Admob_native(context);
//                        } else {
//                            Log.d("AdmobNativeFull", "failed to complete");
//                            if (Preference.getis_big_native_qureka()) {
//                                is_AdmobNativeload = true;
//                            } else {
//                                is_AdmobNativeload = false;
//                            }
//                            if (Preference.getShow_ads_type().equals("admob")) {
//                                FB_NativeAd(context);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onAdClicked() {
//                        super.onAdClicked();
//                        //                        Appcontroller.fast_start = true;
//                    }
//                });
//
//        Log.d("AdmobAds", "on loadeding...");
//        Log.e("Utill_native_ads", "admob Native ad loading...: ");
//        builder.build().loadAd(new AdRequest.Builder().build());
//
//    }
//
//    public static int admob_native_banner_array_int = Preference.getNativeBannerArrayINT();
//    public static String admob_native_banner_id = "";
//
//    public static void NativeBannerFullIdsRandomId() {
//        try {
//            Log.d("AdmobNativeBanner", "NativeBannerFullIdsRandomId");
//            if (admob_native_banner_list != null && admob_native_banner_list.size() != 0 && admob_native_banner_list.size() != admob_native_banner_array_int && admob_native_banner_list.size() >= admob_native_banner_array_int) {
//                Log.d("AdmobNativeBanner", "NativeBannerFullIdsRandomId  if " + Preference.getNativeBannerArrayINT());
//                admob_native_banner_id = admob_native_banner_list.get(Preference.getNativeBannerArrayINT());
//                Preference.setadmob_native_two(admob_native_banner_id);
//                    /*admob_native_banner_array_int = admob_native_banner_array_int + 1;
//                    Preference.setNativeBannerArrayINT(admob_native_banner_array_int);*/
//            } else {
//                Log.d("AdmobNativeBanner", "NativeBannerFullIdsRandomId  else");
//                admob_native_banner_array_int = 0;
//                Preference.setNativeBannerArrayINT(admob_native_banner_array_int);
//                Log.d("AdmobNativeBanner", "NativeBannerFullIdsRandomId  else   " + Preference.getNativeBannerArrayINT());
//                admob_native_banner_id = admob_native_banner_list.get(Preference.getNativeBannerArrayINT());
//                Preference.setadmob_native_two(admob_native_banner_id);
//                   /* admob_native_banner_array_int = admob_native_banner_array_int + 1;
//                    Preference.setNativeBannerArrayINT(admob_native_banner_array_int);*/
//            }
//
//        } catch (NullPointerException n) {
//            n.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void Admob_native_banner(final Context context/*, LinearLayout linearLayout, LinearLayout lnr_view*//*, int position, LinearLayout ll_ads , boolean isFromalbum*/) {
//
////        (Ads.extra_AdmobNativeload && !Ads.is_AdmobNativeload)
//        NativeBannerFullIdsRandomId();
//        AdLoader.Builder builder = new AdLoader.Builder(context, Preference.getadmob_native_two())
//                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
//                    @Override
//                    public void onNativeAdLoaded(NativeAd nativeAd) {
//                        // Assumes you have a placeholder FrameLayout in your View layout
//                        // (with id fl_adplaceholder) where the ad is to be placed.
//                        Log.d("AdmobAds", "on loaded");
//                        if (admobnativeAd_banner != null) {
//                            admobnativeAd_banner = null;
//                        }
//                        if (Preference.getadmob_load_type()) {
//                            if (admob_native_banner_list.size() != Preference.getNativeBannerArrayINT() && admob_native_banner_list.size() > 1) {
//                                Log.d("AdmobNativeBanner", "failed to re-load");
//                                admob_native_banner_array_int = admob_native_banner_array_int + 1;
//                                Preference.setNativeBannerArrayINT(admob_native_banner_array_int);
//                            } else {
//                                Log.d("AdmobNativeBanner", "failed to complete");
//                            }
//                        }
//
//                        admobnativeAd_banner = nativeAd;
//                        Log.e("AdmobAds", "admob Native ad show : ");
//                    }
//                }).withAdListener(new AdListener() {
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                        super.onAdFailedToLoad(loadAdError);
//                        if (admobnativeAd_banner != null) {
//                            admobnativeAd_banner = null;
//                        }
//                        Log.d("AdmobAds", "on failed");
//
//
//                        if (admob_native_banner_list.size() != Preference.getNativeBannerArrayINT() && admob_native_banner_list.size() > 1) {
//                            Log.d("AdmobNativeBanner", "failed to re-load");
//                            admob_native_banner_array_int = admob_native_banner_array_int + 1;
//                            Preference.setNativeBannerArrayINT(admob_native_banner_array_int);
//                            Admob_native_banner(context);
//                        } else {
//                            Log.d("AdmobNativeBanner", "failed to complete");
//                            if (Preference.getShow_ads_type().equals("admob")) {
//                                FB_NativeAd(context);
//                            }
//                        }
//
//                    }
//
//                    @Override
//                    public void onAdClicked() {
//                        super.onAdClicked();
////                        Appcontroller.fast_start = true;
//                    }
//                });
//
//        Log.d("AdmobAds", "on loadeding...");
//
//
//        Log.e("Utill_native_ads", "admob Native ad loading...: ");
//        builder.build().loadAd(new AdRequest.Builder().build());
//
////        Log.e("AdmobAds", "admob adviewnative : ");
////        AdLoader.Builder builder = new AdLoader.Builder(context, Preference.getadmob_native_two())
////                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
////                    @Override
////                    public void onNativeAdLoaded(NativeAd nativeAd) {
////                        // Assumes you have a placeholder FrameLayout in your View layout
////                        // (with id fl_adplaceholder) where the ad is to be placed.
////                        Log.d("AdmobAds", "on loaded");
////                        if (admobnativeAd_banner != null) {
////                            admobnativeAd_banner = null;
////                        }
////                        admobnativeAd_banner = nativeAd;
////
////                        is_AdmobNativeload_banner = true;
////
////                        Log.e("AdmobAds", "admob Native ad show : ");
////
////
////                    }
////                }).withAdListener(new AdListener() {
////                    @Override
////                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
////                        super.onAdFailedToLoad(loadAdError);
////                        if (admobnativeAd_banner != null) {
////                            admobnativeAd_banner = null;
////                        }
////                        Log.d("AdmobAds", "on failed");
////                        is_AdmobNativeload_banner = true;
////                        extra_AdmobNativeload = false;
////
////                    }
////
////                    @Override
////                    public void onAdClicked() {
////                        super.onAdClicked();
//////                        Appcontroller.fast_start = true;
////                    }
////                });
////
////        Log.d("AdmobAds", "on loadeding...");
////
////
////        Log.e("Utill_native_ads", "admob Native ad loading...: ");
////        builder.build().loadAd(new AdRequest.Builder().build());
//
//    }
//
//
//    public static void populateUnifiedNativeAdView(NativeAd nativeAd, NativeAdView adView, boolean flag) {
//        com.google.android.gms.ads.nativead.MediaView mediaView = adView.findViewById(R.id.ad_media);
//        adView.setMediaView(mediaView);
//        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
//        adView.setBodyView(adView.findViewById(R.id.ad_body));
//        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
//        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
//        adView.setPriceView(adView.findViewById(R.id.ad_price));
//        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
//        adView.setStoreView(adView.findViewById(R.id.ad_store));
//        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
//        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
//        if (nativeAd.getBody() == null) {
//            adView.getBodyView().setVisibility(View.INVISIBLE);
//        } else {
//            adView.getBodyView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
//        }
//
//        if (nativeAd.getCallToAction() == null) {
//            adView.getCallToActionView().setVisibility(View.INVISIBLE);
//        } else {
//            ((Button) adView.getCallToActionView()).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Preference.getnative_button_color())));
//            ((Button) adView.getCallToActionView()).setTextColor(Color.parseColor(Preference.getnative_btn_text_color()));
//            adView.getCallToActionView().setVisibility(View.VISIBLE);
//            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
//            makeMeBlink((Button) adView.getCallToActionView());
//        }
//
//        if (nativeAd.getIcon() == null) {
//            adView.getIconView().setVisibility(View.GONE);
//        } else {
//            ((ImageView) adView.getIconView()).setImageDrawable(
//                    nativeAd.getIcon().getDrawable());
//            adView.getIconView().setVisibility(View.VISIBLE);
//        }
//
//        if (nativeAd.getPrice() == null) {
//            adView.getPriceView().setVisibility(View.INVISIBLE);
//        } else {
//            adView.getPriceView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
//        }
//
//        if (nativeAd.getStore() == null) {
//            adView.getStoreView().setVisibility(View.INVISIBLE);
//        } else {
//            adView.getStoreView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
//        }
//
//        if (nativeAd.getStarRating() == null) {
//            if (flag) {
//                adView.getStarRatingView().setVisibility(View.GONE);
//            } else {
//                adView.getStarRatingView().setVisibility(View.INVISIBLE);
//            }
//        } else {
//            ((RatingBar) adView.getStarRatingView())
//                    .setRating(nativeAd.getStarRating().floatValue());
//            if (flag) {
//                adView.getStarRatingView().setVisibility(View.GONE);
//            } else {
//                adView.getStarRatingView().setVisibility(View.VISIBLE);
//            }
//        }
//
//
//        if (nativeAd.getAdvertiser() == null) {
//            if (flag) {
//                adView.getAdvertiserView().setVisibility(View.GONE);
//            } else {
//                adView.getAdvertiserView().setVisibility(View.INVISIBLE);
//            }
//        } else {
//            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
//            if (flag) {
//                adView.getAdvertiserView().setVisibility(View.GONE);
//            } else {
//                adView.getAdvertiserView().setVisibility(View.VISIBLE);
//            }
//        }
//
//        adView.setNativeAd(nativeAd);
//
//        VideoController vc = nativeAd.getMediaContent().getVideoController();
//
//        // Updates the UI to say whether or not this ad has a simplevideoshow asset.
//        if (vc.hasVideoContent()) {
//            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
//                @Override
//                public void onVideoEnd() {
//                    super.onVideoEnd();
//                }
//            });
//        } else {
//
//        }
//    }
//
//    public static void populateUnifiedNativeAdView_banner(NativeAd nativeAd, NativeAdView adView, boolean flag) {
//        com.google.android.gms.ads.nativead.MediaView mediaView = adView.findViewById(R.id.ad_media);
//        adView.setMediaView(mediaView);
//        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
//        adView.setBodyView(adView.findViewById(R.id.ad_body));
//        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
//        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
//        adView.setPriceView(adView.findViewById(R.id.ad_price));
//        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
//        adView.setStoreView(adView.findViewById(R.id.ad_store));
//        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
//        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
//        if (nativeAd.getBody() == null) {
//            adView.getBodyView().setVisibility(View.GONE);
//        } else {
//            adView.getBodyView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
//        }
//
//        if (nativeAd.getCallToAction() == null) {
//            adView.getCallToActionView().setVisibility(View.INVISIBLE);
//        } else {
//            ((Button) adView.getCallToActionView()).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Preference.getnative_button_color())));
//            ((Button) adView.getCallToActionView()).setTextColor(Color.parseColor(Preference.getnative_btn_text_color()));
//            adView.getCallToActionView().setVisibility(View.VISIBLE);
//            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
//            makeMeBlink((Button) adView.getCallToActionView());
//        }
//
//        if (nativeAd.getIcon() == null) {
//            adView.getIconView().setVisibility(View.GONE);
//        } else {
//            ((ImageView) adView.getIconView()).setImageDrawable(
//                    nativeAd.getIcon().getDrawable());
//            adView.getIconView().setVisibility(View.VISIBLE);
//        }
//
//        if (nativeAd.getPrice() == null) {
//            adView.getPriceView().setVisibility(View.INVISIBLE);
//        } else {
//            adView.getPriceView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
//        }
//
//        if (nativeAd.getStore() == null) {
//            adView.getStoreView().setVisibility(View.INVISIBLE);
//        } else {
//            adView.getStoreView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
//        }
//
//        if (nativeAd.getStarRating() == null) {
//            if (flag) {
//                adView.getStarRatingView().setVisibility(View.GONE);
//            } else {
//                adView.getStarRatingView().setVisibility(View.INVISIBLE);
//            }
//        } else {
//            ((RatingBar) adView.getStarRatingView())
//                    .setRating(nativeAd.getStarRating().floatValue());
//            if (flag) {
//                adView.getStarRatingView().setVisibility(View.GONE);
//            } else {
//                adView.getStarRatingView().setVisibility(View.VISIBLE);
//            }
//        }
//
//
//        if (nativeAd.getAdvertiser() == null) {
//            if (flag) {
//                adView.getAdvertiserView().setVisibility(View.GONE);
//            } else {
//                adView.getAdvertiserView().setVisibility(View.INVISIBLE);
//            }
//        } else {
//            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
//            if (flag) {
//                adView.getAdvertiserView().setVisibility(View.GONE);
//            } else {
//                adView.getAdvertiserView().setVisibility(View.VISIBLE);
//            }
//        }
//
//        adView.setNativeAd(nativeAd);
//
//        VideoController vc = nativeAd.getMediaContent().getVideoController();
//
//        // Updates the UI to say whether or not this ad has a simplevideoshow asset.
//        if (vc.hasVideoContent()) {
//            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
//                @Override
//                public void onVideoEnd() {
//                    super.onVideoEnd();
//                }
//            });
//        } else {
//
//        }
//    }
//
//
//    public static void populateUnifiedNativeAdView_exit(NativeAd nativeAd, NativeAdView adView, boolean flag, TextView ad_call_to_action) {
//        com.google.android.gms.ads.nativead.MediaView mediaView = adView.findViewById(R.id.ad_media);
//        adView.setMediaView(mediaView);
//        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
//        adView.setBodyView(adView.findViewById(R.id.ad_body));
////        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
//        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
//        adView.setPriceView(adView.findViewById(R.id.ad_price));
//        adView.setCallToActionView(ad_call_to_action);
//        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
//        adView.setStoreView(adView.findViewById(R.id.ad_store));
//        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
//        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
//        if (nativeAd.getBody() == null) {
//            adView.getBodyView().setVisibility(View.INVISIBLE);
//        } else {
//            adView.getBodyView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
//        }
//
//        if (nativeAd.getCallToAction() == null) {
//            adView.getCallToActionView().setVisibility(View.INVISIBLE);
//        } else {
//            ((TextView) adView.getCallToActionView()).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Preference.getnative_button_color())));
//            ((TextView) adView.getCallToActionView()).setTextColor(Color.parseColor(Preference.getnative_btn_text_color()));
//            adView.getCallToActionView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
//        }
//
//        if (nativeAd.getIcon() == null) {
//            adView.getIconView().setVisibility(View.GONE);
//        } else {
//            ((ImageView) adView.getIconView()).setImageDrawable(
//                    nativeAd.getIcon().getDrawable());
//            adView.getIconView().setVisibility(View.VISIBLE);
//        }
//
//        if (nativeAd.getPrice() == null) {
//            adView.getPriceView().setVisibility(View.INVISIBLE);
//        } else {
//            adView.getPriceView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
//        }
//
//        if (nativeAd.getStore() == null) {
//            adView.getStoreView().setVisibility(View.INVISIBLE);
//        } else {
//            adView.getStoreView().setVisibility(View.VISIBLE);
//            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
//        }
//
//        if (nativeAd.getStarRating() == null) {
//            if (flag) {
//                adView.getStarRatingView().setVisibility(View.GONE);
//            } else {
//                adView.getStarRatingView().setVisibility(View.INVISIBLE);
//            }
//        } else {
//            ((RatingBar) adView.getStarRatingView())
//                    .setRating(nativeAd.getStarRating().floatValue());
//            if (flag) {
//                adView.getStarRatingView().setVisibility(View.GONE);
//            } else {
//                adView.getStarRatingView().setVisibility(View.VISIBLE);
//            }
//        }
//
//
//        if (nativeAd.getAdvertiser() == null) {
//            if (flag) {
//                adView.getAdvertiserView().setVisibility(View.GONE);
//            } else {
//                adView.getAdvertiserView().setVisibility(View.INVISIBLE);
//            }
//        } else {
//            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
//            if (flag) {
//                adView.getAdvertiserView().setVisibility(View.GONE);
//            } else {
//                adView.getAdvertiserView().setVisibility(View.VISIBLE);
//            }
//        }
//
//        adView.setNativeAd(nativeAd);
//
//        VideoController vc = nativeAd.getMediaContent().getVideoController();
//
//        // Updates the UI to say whether or not this ad has a simplevideoshow asset.
//        if (vc.hasVideoContent()) {
//            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
//                @Override
//                public void onVideoEnd() {
//                    super.onVideoEnd();
//                }
//            });
//        } else {
//
//        }
//    }
//
//
//    //Fb Native Ads
//    public static String fb_native_id12 = "";
//    public static com.facebook.ads.NativeAd fb_native_adpater;
//    public static NativeAdListener nativeAdListener;
//
//    public static void FB_NativeAd(final Context context) {
//
//        fb_native_id12 = Preference.getFb_native_id();
//        Log.d("NativeFull_Show", "fb online_id " + fb_native_id12);
//
//        fb_native_adpater = new com.facebook.ads.NativeAd(context, fb_native_id12);
//
//        nativeAdListener = new NativeAdListener() {
//            @Override
//            public void onMediaDownloaded(Ad ad) {
//
//            }
//
//            @Override
//            public void onError(Ad ad, com.facebook.ads.AdError adError) {
//                Log.d("NativeFull_Show", "fb error = " + adError.getErrorMessage());
//                if (!Preference.getShow_ads_type().equals("admob")) {
//                    Admob_native(context);
//                }
//            }
//
//            @Override
//            public void onAdLoaded(Ad ad) {
//                // Race condition, load() called again before last ad was displayed
//                Log.d("NativeFull_Show", "fb load ad= ");
//                if (fb_native_adpater != ad) {
//                    return;
//                }
//
//
//            }
//
//            @Override
//            public void onAdClicked(Ad ad) {
//            }
//
//            @Override
//            public void onLoggingImpression(Ad ad) {
//
//            }
//        };
//        // Request an ad
//
//        if (!fb_native_adpater.isAdLoaded()) {
//            Log.d("NativeFull_Show", "fb  Loadding..");
//            fb_native_adpater.loadAd(
//                    fb_native_adpater.buildLoadAdConfig()
//                            .withAdListener(nativeAdListener)
//                            .build());
//        }
//    }
//
//    public static NativeAdLayout nativeAdLayout;
//
//    public static void fb_inflateAd_full(Context context, com.facebook.ads.NativeAd nativeAd, LinearLayout adViewContainer, LinearLayout load, LinearLayout adView) {
//        load.setVisibility(View.GONE);
//        adViewContainer.setVisibility(View.VISIBLE);
//        nativeAd.unregisterView();
//        // Add the Ad view into the ad container.
//
//
//        // Add the AdChoices icon
//
//      /*  AdChoicesView adChoicesView = new AdChoicesView(context, nativeAd, true);
//        adView.addView(adChoicesView, 0);*/
//        LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
//        AdOptionsView adOptionsView = new AdOptionsView(context, nativeAd, nativeAdLayout);
//        adChoicesContainer.removeAllViews();
//        adChoicesContainer.addView(adOptionsView, 0);
//
//        // Create native UI using the ad metadata.
//        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
//        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
//        com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
//        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
//        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
//        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
//        TextView nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);
//
//        // Set the Text.
////        nativeAdMedia.setVisibility(View.GONE);
//        nativeAdTitle.setText(nativeAd.getAdvertiserName());
//        nativeAdBody.setText(nativeAd.getAdBodyText());
//        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
//        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
//        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
//        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());
//
//        // Create a list of clickable views
//        List<View> clickableViews = new ArrayList<>();
//        clickableViews.add(nativeAdTitle);
//        clickableViews.add(nativeAdCallToAction);
//
//        // Register the Title and CTA button to listen for clicks.
//        nativeAd.registerViewForInteraction(
//                adView,
//                nativeAdMedia,
//                nativeAdIcon,
//                clickableViews);
//    }
//
//
//    //</editor-fold>
//
//
//    public static boolean isNetworkConnected(Activity context) {
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
//    }
//
//
//    @SuppressLint("WrongConstant")
//    public static View makeMeBlink(View view) {
//
//        if (Preference.getbuttonanimate()) {
//            ObjectAnimator AnimateGlass1;
//            if (Preference.getanimationtype().equals("zoominout")) {
//                AnimateGlass1 = ObjectAnimator.ofPropertyValuesHolder(view,
//                        PropertyValuesHolder.ofFloat("scaleX", 0.9f, 1f),
//                        PropertyValuesHolder.ofFloat("scaleY", 0.9f, 1f));
//            } else {
//                AnimateGlass1 = ObjectAnimator.ofFloat(view, "alpha", 0.3f, 1f);
//
//            }
//            AnimateGlass1.setDuration(500);
//            AnimateGlass1.setRepeatMode(Animation.REVERSE);
//            AnimateGlass1.setRepeatCount(Animation.INFINITE);
//            AnimateGlass1.start();
//
//        }
//        return view;
//
//    }
//
//
//    public static void getDownloadApkAds(Context context) {
////        Dialog dialog = new Dialog(context, android.R.style.Theme_Light);
////        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
////        dialog.getWindow().getAttributes().windowAnimations = R.style.QuerekaInter;
////        dialog.setContentView(R.layout.qureka_download_interstitial_layout);
////
////
////        //  int random = new Random().nextInt(5);
////        ImageView qurekaAds = dialog.findViewById(R.id.qurekaAds);
////        //   ImageView qurekaAds1 = dialog.findViewById(R.id.qurekaAds1);
////        try {
////            if (Utils.mutli_image != null) {
////                int random = new Random().nextInt(Utils.mutli_image.size());
////                Log.d("mutli_image12", "mutli_image = " + random);
////                Glide.with(context).load(Utils.mutli_image.get(random)).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.app_open_qureka).error(R.drawable.img_ads_place)
////                        .into(qurekaAds);
////                //  Glide.with(context).load(Utill.mutli_image.get(random)).into(qurekaAds1);
////            }
////        } catch (NullPointerException n) {
////            n.printStackTrace();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        (dialog.findViewById(R.id.qurekaAds)).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////
////                if (Preference.getplaystoreredirect()) {
////                    String packageName = context.getPackageName();
////                    try {
////                        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Preference.getapplink())));
////                    } catch (ActivityNotFoundException unused) {
////                        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Preference.getapplink())));
////                    }
////
////                } else {
////                    PackageManager pm = context.getPackageManager();
////                    boolean isInstalled = isPackageInstalled("com.rg.royal111", pm);
////                    Log.d("url_passing12", "isInstalled = " + isInstalled);
////                    if (isInstalled) {
////                        dialog.dismiss();
////                        onFinishAds.onFinishAds(true);
////                        Toast.makeText(context, "Already installed!", Toast.LENGTH_SHORT).show();
////                    } else {
////                        Utils.DownloadApk(context);
////                    }
////                }
////            }
////        });
////
////
////        (dialog.findViewById(R.id.qurekaAdsClose)).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                dialog.dismiss();
////                onFinishAds.onFinishAds(true);
////            }
////        });
////
////        dialog.setOnKeyListener(new Dialog.OnKeyListener() {
////            @Override
////            public boolean onKey(DialogInterface arg0, int keyCode,
////                                 KeyEvent event) {
////                // TODO Auto-generated method stub
////                if (keyCode == KeyEvent.KEYCODE_BACK) {
////                    dialog.dismiss();
////                    onFinishAds.onFinishAds(true);
////                }
////                return true;
////            }
////        });
////
////        dialog.show();
//    }
//
//}
