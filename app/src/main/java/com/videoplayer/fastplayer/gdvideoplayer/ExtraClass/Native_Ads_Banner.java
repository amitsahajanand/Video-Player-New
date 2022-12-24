package com.videoplayer.fastplayer.gdvideoplayer.ExtraClass;/*
package com.picturemanage.privateiimage.gallery20.ExtraClass;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.picturemanage.privateiimage.gallery20.R;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

import static com.picturemanage.privateiimage.gallery20.Activity.SplashActivity.admob_adaptive_banner_list;
import static com.picturemanage.privateiimage.gallery20.Activity.SplashActivity.admob_native_banner_list;


public class Native_Ads_Banner {

   */
/* public static void Native_adtype(final Context context, final LinearLayout llnative, String native_type) {
        Show_AdmobNative(context, llnative, native_type);
    }*//*


    public static Integer admob_banner_int = 0;

    public static void Native_adtype(Context context, LinearLayout llnative, String native_type) {

        if (Preference.getisBottom_native()) {
            Banner_Native_adtype(context, llnative);
        } else {

            if (!Preference.getAdmob_page()) {
                AdmobBanner(context, llnative);
            } else if (Preference.getAdmob_page() && admob_banner_int != Preference.getbanner_admob()) {
                admob_banner_int++;
                AdmobBanner(context, llnative);
            } else {
                admob_banner_int = 0;
                if (Preference.getqureka_show()) {
                    Qureka_banner_Ads(context, llnative);
                } else {
                    AdmobBanner(context, llnative);
                }
            }
        }
    }


    public static void AdmobBanner(final Context context, final LinearLayout llnative) {

        AdaptiveBanneradsRandomId();
        AdView adView = new AdView(context);
        adView.setAdUnitId(Preference.getadmob_banner_id());

        //  llnative.removeAllViews();
        //  llnative.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.setAdSize(getAdSize(context));
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                llnative.removeAllViews();
                llnative.addView(adView);
                if (admob_adaptive_banner_list.size() != Preference.getAdaptiveBannerArrayINT() && admob_adaptive_banner_list.size() > 1) {
                    Log.d("AdmobAdaptiveBanner", "failed to re-load");
                    admob_adaptive_banner_array_int = admob_adaptive_banner_array_int + 1;
                    Preference.setAdaptiveBannerArrayINT(admob_adaptive_banner_array_int);
                    AdmobBanner(context, llnative);
                } else {
                    Log.d("AdmobAdaptiveBanner", "failed to complete");
                    if (Preference.getqureka_show()) {
                        Qureka_banner_Ads(context, llnative);
                    }
                }

                Log.d("Admob_ads_check", "Banner---onAdFailedToLoad: " + loadAdError);
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
//                admob_hashmap.put(position, (AdView) adView);

                if (Preference.getadmob_load_type()) {
                    if (admob_adaptive_banner_list.size() != Preference.getAdaptiveBannerArrayINT() && admob_adaptive_banner_list.size() > 1) {
                        Log.d("AdmobAdaptiveBanner", "BOOL TRUE VALUE PLUS");
                        admob_adaptive_banner_array_int = admob_adaptive_banner_array_int + 1;
                        Preference.setAdaptiveBannerArrayINT(admob_adaptive_banner_array_int);
                        AdmobBanner(context, llnative);
                    } else {
                        Log.d("AdmobAdaptiveBanner", "ELSE CONDITION");
                    }
                }

                llnative.removeAllViews();
                llnative.addView(adView);
                //llline.setVisibility(View.GONE);
                llnative.setVisibility(View.VISIBLE);
                //  ll_ads.setVisibility(View.VISIBLE);
                Log.d("Admob_ads_check", "Banner---onAdLoad: ");
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
//                Appcontroller.fast_start = true;
                Log.d("Admob_ads_check", "Banner---onAdFailedToLoad: clicks");
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }
        });

    }


    public static AdSize getAdSize(Context context) {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
    }


    public static void Qureka_banner_Ads(final Context context, final LinearLayout llnative) {
        if (Preference.getqureka_show()) {

            llnative.setVisibility(View.VISIBLE);

            View layout2 = LayoutInflater.from(context).inflate(R.layout.ads_banner, llnative, false);
            TextView tv_text_ad_name = layout2.findViewById(R.id.tv_text_ad_name);
            TextView tv_text_ad_desc = layout2.findViewById(R.id.tv_text_ad_desc);
            GifImageView iv_round_gif = layout2.findViewById(R.id.iv_round_gif);
            int random = new Random().nextInt(5);
            if (random == 1) {
                tv_text_ad_name.setText("Play & Win Coins");
                tv_text_ad_desc.setText("Win 5,00,000 Coins & More");

                iv_round_gif.setBackgroundResource(R.drawable.qureka_round1);
            } else if (random == 2) {
                tv_text_ad_name.setText("Play Bubble Shooter Game");
                tv_text_ad_desc.setText("Win 50,000 Coins With Mobile Games");

                iv_round_gif.setBackgroundResource(R.drawable.qureka_round2);
            } else if (random == 3) {
                tv_text_ad_name.setText("Play Fruit Chop Game");
                tv_text_ad_desc.setText("Win 50,000 Coins No Install Required");

                iv_round_gif.setBackgroundResource(R.drawable.qureka_round3);
            } else if (random == 4) {
                tv_text_ad_name.setText("Play Don't Crash Game");
                tv_text_ad_desc.setText("Collect 50,000 Coins Now");

                iv_round_gif.setBackgroundResource(R.drawable.qureka_round_4);
            } else {
                tv_text_ad_name.setText("Play Car Racing Game");
                tv_text_ad_desc.setText("Win Coin & No Installation Required");

                iv_round_gif.setBackgroundResource(R.drawable.qureka_round1);
            }

            layout2.findViewById(R.id.rlBanner).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.url_passing(context);

                }
            });
            llnative.addView(layout2);
        } else {
            llnative.setVisibility(View.GONE);
        }
    }

    public static int admob_native_banner_array_int = Preference.getNativeBannerArrayINT();
    public static String admob_native_banner_id = "";

    public static void NativeBannerFullIdsRandomId() {
        try {
            Log.d("AdmobNativeBanner", "NativeBannerFullIdsRandomId");
            if (admob_native_banner_list != null && admob_native_banner_list.size() != 0 && admob_native_banner_list.size() != admob_native_banner_array_int && admob_native_banner_list.size() >= admob_native_banner_array_int) {
                Log.d("AdmobNativeBanner", "NativeBannerFullIdsRandomId  if " + Preference.getNativeBannerArrayINT());
                admob_native_banner_id = admob_native_banner_list.get(Preference.getNativeBannerArrayINT());
                Preference.setadmob_native_two(admob_native_banner_id);
                */
/*admob_native_banner_array_int = admob_native_banner_array_int + 1;
                Preference.setNativeBannerArrayINT(admob_native_banner_array_int);*//*

            } else {
                Log.d("AdmobNativeBanner", "NativeBannerFullIdsRandomId  else");
                admob_native_banner_array_int = 0;
                Preference.setNativeBannerArrayINT(admob_native_banner_array_int);
                Log.d("AdmobNativeBanner", "NativeBannerFullIdsRandomId  else   " + Preference.getNativeBannerArrayINT());
                admob_native_banner_id = admob_native_banner_list.get(Preference.getNativeBannerArrayINT());
                Preference.setadmob_native_two(admob_native_banner_id);
               */
/* admob_native_banner_array_int = admob_native_banner_array_int + 1;
                Preference.setNativeBannerArrayINT(admob_native_banner_array_int);*//*

            }

        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void AdmobNativeFull(final Context context) {

        NativeBannerFullIdsRandomId();
        AdLoader.Builder builder = new AdLoader.Builder(context, Preference.getadmob_native_two())
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        // Assumes you have a placeholder FrameLayout in your View layout
                        // (with id fl_adplaceholder) where the ad is to be placed.
                        Log.d("AdmobAds", "on loaded");
                        if (admobnativeAd_banner != null) {
                            admobnativeAd_banner = null;
                        }
                        if (Preference.getadmob_load_type()) {
                            if (admob_native_banner_list.size() != Preference.getNativeBannerArrayINT() && admob_native_banner_list.size() > 1) {
                                Log.d("AdmobNativeBanner", "failed to re-load");
                                admob_native_banner_array_int = admob_native_banner_array_int + 1;
                                Preference.setNativeBannerArrayINT(admob_native_banner_array_int);
                            } else {
                                Log.d("AdmobNativeBanner", "failed to complete");
                            }
                        }

                        admobnativeAd_banner = nativeAd;
                        Log.e("AdmobAds", "admob Native ad show : ");
                    }
                }).withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        if (admobnativeAd_banner != null) {
                            admobnativeAd_banner = null;
                        }
                        Log.d("AdmobAds", "on failed");


                        if (admob_native_banner_list.size() != Preference.getNativeBannerArrayINT() && admob_native_banner_list.size() > 1) {
                            Log.d("AdmobNativeBanner", "failed to re-load");
                            admob_native_banner_array_int = admob_native_banner_array_int + 1;
                            Preference.setNativeBannerArrayINT(admob_native_banner_array_int);
                            AdmobNativeFull(context);
                        } else {
                            Log.d("AdmobNativeBanner", "failed to complete");
                        }

                    }

                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
//                        Appcontroller.fast_start = true;
                    }
                });

        Log.d("AdmobAds", "on loadeding...");


        Log.e("Utill_native_ads", "admob Native ad loading...: ");
        builder.build().loadAd(new AdRequest.Builder().build());

    }

    public static Integer admob_native_banner_int = 0;
    public static NativeAd admobnativeAd_banner;

    public static void Banner_Native_adtype(final Context context, final LinearLayout llnative) {

        if (!Preference.getAdmob_page()) {
            if (admobnativeAd_banner != null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                NativeAdView adView = null;
                adView = (NativeAdView) inflater.inflate(R.layout.admob_native_banner, null);

                llnative.setVisibility(View.VISIBLE);
                populateUnifiedNativeAdView_banner(admobnativeAd_banner, adView, false);
                Log.e("New_Ads", "admob Native ad show : ");
                llnative.removeAllViews();
                llnative.addView(adView);
                // admob_nativehashmap.put(position, adView);
            } else {
                Qureka_banner_Ads(context, llnative);
            }
        } else if (Preference.getAdmob_page() && admob_native_banner_int != Preference.getbanner_admob()) {
            admob_native_banner_int++;
            if (admobnativeAd_banner != null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                NativeAdView adView = null;
                adView = (NativeAdView) inflater.inflate(R.layout.admob_native_banner, null);

                llnative.setVisibility(View.VISIBLE);

                populateUnifiedNativeAdView_banner(admobnativeAd_banner, adView, false);
                Log.e("New_Ads", "admob Native ad show : ");
                llnative.removeAllViews();
                llnative.addView(adView);
                //  admob_nativehashmap.put(position, adView);
            } else {
                Qureka_banner_Ads(context, llnative);
            }
        } else {
            admob_native_banner_int = 0;
            if (Preference.getqureka_show()) {
                Qureka_banner_Ads(context, llnative);
            } else {
                if (admobnativeAd_banner != null) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    NativeAdView adView = null;
                    adView = (NativeAdView) inflater.inflate(R.layout.admob_native_banner, null);

                    llnative.setVisibility(View.VISIBLE);

                    populateUnifiedNativeAdView_banner(admobnativeAd_banner, adView, false);
                    Log.e("New_Ads", "admob Native ad show : ");
                    llnative.removeAllViews();
                    llnative.addView(adView);
                    // admob_nativehashmap.put(position, adView);
                } else {
                    llnative.setVisibility(View.GONE);
                }
            }
        }


        AdmobNativeFull(context);

    }


    public static void populateUnifiedNativeAdView_banner(NativeAd nativeAd, NativeAdView adView, boolean flag) {
        com.google.android.gms.ads.nativead.MediaView mediaView = adView.findViewById(R.id.ad_media);
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
            adView.getBodyView().setVisibility(View.GONE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            ((Button) adView.getCallToActionView()).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(Preference.getnative_button_color())));
            ((Button) adView.getCallToActionView()).setTextColor(Color.parseColor(Preference.getnative_btn_text_color()));
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

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


    public static int admob_adaptive_banner_array_int = Preference.getAdaptiveBannerArrayINT();

    public static void AdaptiveBanneradsRandomId() {
        try {
            Log.d("AdmobAdaptiveBanner", "AdaptiveBanneradsRandomId");
            if (admob_adaptive_banner_list != null && admob_adaptive_banner_list.size() != 0 && admob_adaptive_banner_list.size() != admob_adaptive_banner_array_int && admob_adaptive_banner_list.size() >= admob_adaptive_banner_array_int) {
                Log.d("AdmobAdaptiveBanner", "NativeBannerFullIdsRandomId  if " + Preference.getAdaptiveBannerArrayINT());
                admob_native_banner_id = admob_adaptive_banner_list.get(Preference.getAdaptiveBannerArrayINT());
                Preference.setadmob_banner_id(admob_native_banner_id);
                */
/*admob_adaptive_banner_array_int = admob_adaptive_banner_array_int + 1;
                Preference.setAdaptiveBannerArrayINT(admob_adaptive_banner_array_int);*//*

            } else {
                Log.d("AdmobAdaptiveBanner", "AdaptiveBanneradsRandomId  else");
                admob_adaptive_banner_array_int = 0;
                Preference.setAdaptiveBannerArrayINT(admob_adaptive_banner_array_int);
                Log.d("AdmobAdaptiveBanner", "AdaptiveBanneradsRandomId  else   " + Preference.getAdaptiveBannerArrayINT());
                admob_native_banner_id = admob_adaptive_banner_list.get(Preference.getAdaptiveBannerArrayINT());
                Preference.setadmob_banner_id(admob_native_banner_id);
               */
/* admob_adaptive_banner_array_int = admob_adaptive_banner_array_int + 1;
                Preference.setAdaptiveBannerArrayINT(admob_adaptive_banner_array_int);*//*

            }

        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
