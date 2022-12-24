package com.videoplayer.fastplayer.gdvideoplayer.ExtraClass;/*
package com.picturemanage.privateiimage.gallery20.ExtraClass;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.picturemanage.privateiimage.gallery20.R;

import java.util.HashMap;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

import static com.picturemanage.privateiimage.gallery20.Activity.SplashActivity.admob_native_list;


public class NativeAds {

    public static UnifiedNativeAd admobnativeAd;
    public static HashMap<Integer, UnifiedNativeAdView> admob_nativehashmap = new HashMap<>();
    public static Boolean is_AdmobNativeload = false;
    public static Integer installnumber = 0;



    public static void Native_adtype(final Context context, final LinearLayout llnative, String native_type) {

//        PackageManager pm = context.getPackageManager();
//        boolean isInstalled = isPackageInstalled("com.rg.royal111", pm);
//        if (Preference.getisInstallapk() && !isInstalled) {
//            if (installnumber == Preference.getNativeByPage()) {
//                installnumber = 0;
//                getNativeDownloadApkAds(context, llnative);
//                return;
//            }
//            installnumber++;
//        }

        if (!Preference.getAdmob_page()) {
            if (admobnativeAd != null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                UnifiedNativeAdView adView = (UnifiedNativeAdView) inflater.inflate(R.layout.admob_nativefull, null);
               // ll_ads.setVisibility(View.VISIBLE);
              //  llline.setVisibility(View.GONE);
                llnative.setVisibility(View.VISIBLE);
                populateUnifiedNativeAdView(admobnativeAd, adView, "");
                Log.e("New_Ads", "admob Native ad show : ");
                llnative.removeAllViews();
                llnative.addView(adView);
               // admob_nativehashmap.put(position, adView);
            } else {
                Big_qureka_native(context, llnative);
            }
        } else if (Preference.getAdmob_page() && admob_native_int != Preference.getnative_admob()) {
            admob_native_int++;

            if (admobnativeAd != null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                UnifiedNativeAdView adView = (UnifiedNativeAdView) inflater.inflate(R.layout.admob_nativefull, null);
                llnative.setVisibility(View.VISIBLE);
                populateUnifiedNativeAdView(admobnativeAd, adView, "");
                Log.e("New_Ads", "admob Native ad show : ");
                llnative.removeAllViews();
                llnative.addView(adView);
               // admob_nativehashmap.put(position, adView);
            } else {

                Big_qureka_native(context, llnative);
            }
        } else {
            admob_native_int = 0;
            if (Preference.getqureka_show()) {
                Big_qureka_native(context, llnative);
            } else {
                if (admobnativeAd != null) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    UnifiedNativeAdView adView = (UnifiedNativeAdView) inflater.inflate(R.layout.admob_nativefull, null);
                  //  ll_ads.setVisibility(View.VISIBLE);
                //    llline.setVisibility(View.GONE);
                    llnative.setVisibility(View.VISIBLE);
                    populateUnifiedNativeAdView(admobnativeAd, adView, "");
                    Log.e("New_Ads", "admob Native ad show : ");
                    llnative.removeAllViews();
                    llnative.addView(adView);
                 //   admob_nativehashmap.put(position, adView);
                } else {
                 //   llline.setVisibility(View.GONE);
                    llnative.setVisibility(View.GONE);
                  //  ll_ads.setVisibility(View.GONE);
                }
            }

        }
        AdmobNativeFull(context);
    }


    public static void Big_qureka_native(final Context context, final LinearLayout llnative){
        if (Preference.getqureka_show()) {
            llnative.setVisibility(View.VISIBLE);
            View layout2 = LayoutInflater.from(context).inflate(R.layout.native_qurekha, llnative, false);

            TextView tv_text_ad_name = layout2.findViewById(R.id.tv_text_ad_name);
            TextView tv_text_ad_desc = layout2.findViewById(R.id.tv_text_ad_desc);
            GifImageView iv_round_gif = layout2.findViewById(R.id.iv_round_gif);
            int random = new Random().nextInt(5);
            if (random == 1) {
                tv_text_ad_name.setText("Play Cricket Win Coins");
                tv_text_ad_desc.setText("Win 5,00,000 Coins & More");
                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native1);
                iv_round_gif.setBackgroundResource(R.drawable.qureka_round1);
            } else if (random == 2) {
                tv_text_ad_name.setText("Play Bubble Shooter Game");
                tv_text_ad_desc.setText("Win 50,000 Coins With Mobile Games");
                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native2);
                iv_round_gif.setBackgroundResource(R.drawable.qureka_round2);
            } else if (random == 3) {
                tv_text_ad_name.setText("Play Fruit Chop Game");
                tv_text_ad_desc.setText("Win 50,000 Coins No Install Required");
                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native3);
                iv_round_gif.setBackgroundResource(R.drawable.qureka_round3);
            } else if (random == 4) {
                tv_text_ad_name.setText("Play Don't Crash Game");
                tv_text_ad_desc.setText("Collect 50,000 Coins Now");
                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native4);
                iv_round_gif.setBackgroundResource(R.drawable.qureka_round_4);
            } else {
                tv_text_ad_name.setText("Play Car Racing Game");
                tv_text_ad_desc.setText("Win Coin & No Installation Required");
                layout2.findViewById(R.id.rl_qurekha).setBackgroundResource(R.drawable.qureka_native5);
                iv_round_gif.setBackgroundResource(R.drawable.qureka_round1);
            }

            layout2.findViewById(R.id.playNowLL).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Utils.url_passing(context);
                }
            });
            llnative.removeAllViews();
            llnative.addView(layout2);
        } else {
            llnative.setVisibility(View.GONE);
        }
    }



    public static int admob_native_array_int = Preference.getNativeArrayINT();
    public static String admob_native_id = "";

    public static void NativeFullIdsRandomId() {
        Log.d("AdmobNativeFull", "NativeFullIdsRandomId");
        try {
            if (admob_native_list != null && admob_native_list.size() != 0 && admob_native_list.size() != admob_native_array_int && admob_native_list.size() >= admob_native_array_int) {
                Log.d("AdmobNativeFull", "NativeFullIdsRandomId  if " + Preference.getNativeArrayINT());
                admob_native_id = admob_native_list.get(Preference.getNativeArrayINT());
                Preference.setadmob_native_id(admob_native_id);
               */
/* admob_native_array_int = admob_native_array_int + 1;
                Preference.setNativeArrayINT(admob_native_array_int);*//*

            } else {
                Log.d("AdmobNativeFull", "NativeFullIdsRandomId  else");
                admob_native_array_int = 0;
                Preference.setNativeArrayINT(admob_native_array_int);
                Log.d("AdmobNativeFull", "NativeFullIdsRandomId NativeFullIdsRandomId else   " + Preference.getNativeArrayINT());
                admob_native_id = admob_native_list.get(Preference.getNativeArrayINT());
                Preference.setadmob_native_id(admob_native_id);
              */
/*  admob_native_array_int = admob_native_array_int + 1;
                Preference.setNativeArrayINT(admob_native_array_int);*//*

            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void Show_AdmobNative(Context context, LinearLayout llnative, String native_type) {
        if (admobnativeAd != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            UnifiedNativeAdView adView = (UnifiedNativeAdView) inflater.inflate(R.layout.admob_nativefull, null);
            llnative.setVisibility(View.VISIBLE);
            populateUnifiedNativeAdView(admobnativeAd, adView, native_type);
            Log.e("Ads", "admob Native ad show : ");
            llnative.removeAllViews();
            llnative.addView(adView);
        } else {
        }
        AdmobNativeFull(context);
    }
    public static void Native_exit(final Context context, final LinearLayout llnative, final LinearLayout llline, TextView ad_call_to_action) {
        if (admobnativeAd != null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            UnifiedNativeAdView adView = null;

            adView = (UnifiedNativeAdView) inflater.inflate(R.layout.ads_admob_native_exit, null);

            llline.setVisibility(View.GONE);
            llnative.setVisibility(View.VISIBLE);
            populateUnifiedNativeAdView_exit(admobnativeAd, adView, false, ad_call_to_action);
            Log.e("Ads", "admob Native ad show : ");
            llnative.removeAllViews();
            llnative.addView(adView);
        } else {
            llline.setVisibility(View.GONE);
            llnative.setVisibility(View.GONE);
        }
        AdmobNativeFull(context);
    }


    public static void AdmobNativeFull(final Context context) {
        NativeFullIdsRandomId();
        AdLoader.Builder builder = new AdLoader.Builder(context, Preference.getadmob_native_id());

        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                if (admobnativeAd != null) {
                    admobnativeAd = null;
                }
                admobnativeAd = unifiedNativeAd;
                if (Preference.getadmob_load_type()) {
                    if (admob_native_list.size() != Preference.getNativeArrayINT() && admob_native_list.size() > 1) {
                        Log.d("AdmobNativeFull", "BOOL TRUE VALUE PLUS");
                        admob_native_array_int = admob_native_array_int + 1;
                        Preference.setNativeArrayINT(admob_native_array_int);
                    } else {
                        Log.d("AdmobNativeFull", "ELSE CONDITION");
                    }
                }
                is_AdmobNativeload = true;
                Log.e("AdmobAds", "admob Native ad show : ");
            }
        });

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                //  FFF_App.fast_start = true;
                super.onAdClicked();
            }

            @Override
            public void onAdLoaded() {
                is_AdmobNativeload = true;
                Log.d("ADMOB_NATIVE_ADS", "Load Ads");
                super.onAdLoaded();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                if (admobnativeAd != null) {
                    admobnativeAd = null;
                }
                Log.d("AdmobAds", "on failed");

                if (admob_native_list.size() != Preference.getNativeArrayINT() && admob_native_list.size() > 1) {
                    Log.d("AdmobNativeFull", "failed to re-load");
                    admob_native_array_int = admob_native_array_int + 1;
                    Preference.setNativeArrayINT(admob_native_array_int);
                    AdmobNativeFull(context);
                } else {
                    Log.d("AdmobNativeFull", "failed to complete");
                    if (Preference.getqureka_show()) {
                        is_AdmobNativeload = true;
                    } else {
                        is_AdmobNativeload = false;
                    }
                }
                Log.d("ADMOB_NATIVE_ADS", "fail to load");
            }
        }).build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    public static void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView, String native_type) {
        com.google.android.gms.ads.formats.MediaView mediaView = adView.findViewById(R.id.ad_media);
        adView.setMediaView(mediaView);
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());

        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }


        ((Button) adView.getCallToActionView()).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Preference.getnative_button_color())));
        ((Button) adView.getCallToActionView()).setTextColor(Color.parseColor(Preference.getnative_btn_text_color()));


        if (native_type.equals("custom")) {
            if (nativeAd.getIcon() == null) {
                adView.getIconView().setVisibility(View.GONE);
            } else {
                ((ImageView) adView.getIconView()).setImageDrawable(
                        nativeAd.getIcon().getDrawable());
                adView.getIconView().setVisibility(View.GONE);
            }
        } else {
            if (nativeAd.getIcon() == null) {
                adView.getIconView().setVisibility(View.GONE);
            } else {
                ((ImageView) adView.getIconView()).setImageDrawable(
                        nativeAd.getIcon().getDrawable());
                adView.getIconView().setVisibility(View.VISIBLE);
            }
        }


        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.GONE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        adView.setNativeAd(nativeAd);

        VideoController vc = nativeAd.getVideoController();

        // Updates the UI to say whether or not this ad has a simplevideoshow asset.
        if (vc.hasVideoContent()) {
            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    super.onVideoEnd();
                }
            });
        } else {

        }
    }


    public static void populateUnifiedNativeAdView_exit(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView, boolean flag, TextView ad_call_to_action) {
        com.google.android.gms.ads.formats.MediaView mediaView = adView.findViewById(R.id.ad_media);
        adView.setMediaView(mediaView);
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
//        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setCallToActionView(ad_call_to_action);
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        */
/*((Button) adView.getCallToActionView()).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Preference.getNative_button_color())));
        ((Button) adView.getCallToActionView()).setTextColor(Color.parseColor(Preference.getNative_btn_text_color()));*//*


        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            if (flag) {
                adView.getStarRatingView().setVisibility(View.GONE);
            } else {
                adView.getStarRatingView().setVisibility(View.INVISIBLE);
            }
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            if (flag) {
                adView.getStarRatingView().setVisibility(View.GONE);
            } else {
                adView.getStarRatingView().setVisibility(View.VISIBLE);
            }
        }


        if (nativeAd.getAdvertiser() == null) {
            if (flag) {
                adView.getAdvertiserView().setVisibility(View.GONE);
            } else {
                adView.getAdvertiserView().setVisibility(View.INVISIBLE);
            }
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            if (flag) {
                adView.getAdvertiserView().setVisibility(View.GONE);
            } else {
                adView.getAdvertiserView().setVisibility(View.VISIBLE);
            }
        }

        adView.setNativeAd(nativeAd);

        VideoController vc = nativeAd.getMediaContent().getVideoController();

        // Updates the UI to say whether or not this ad has a simplevideoshow asset.
        if (vc.hasVideoContent()) {
            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    super.onVideoEnd();
                }
            });
        } else {

        }
    }
}
*/
