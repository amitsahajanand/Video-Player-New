package com.videoplayer.fastplayer.gdvideoplayer.Activity.Whatsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Whatsapp.Fragment.WhatsappImgFragment;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Whatsapp.Fragment.WhatsappVidFragment;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;

public class WAstatusActivity extends BaseActivity {

    //View
    TabLayout tabs;
    ViewPager viewpager;
    ImageView  iv_open_whatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w_astatus);

        Declaration();
        Intialization();


    }


    private void Declaration() {


        tabs = findViewById(R.id.tabs);
        viewpager = findViewById(R.id.viewpager);
        iv_open_whatsapp = findViewById(R.id.iv_open_whatsapp);


        iv_open_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.OpenApp(WAstatusActivity.this, "com.whatsapp");
            }
        });

    }

    private void Intialization() {

        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager(), 1);
        viewPagerAdapter.addFragment(new WhatsappImgFragment(), "Images");
        viewPagerAdapter.addFragment(new WhatsappVidFragment(), "Videos");
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(1);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList();
        private final List<String> mFragmentTitleList = new ArrayList();

        ViewPagerAdapter(FragmentManager fragmentManager, int i) {
            super(fragmentManager, i);
        }

        public Fragment getItem(int i) {
            return this.mFragmentList.get(i);
        }

        public int getCount() {
            return this.mFragmentList.size();
        }

        /* access modifiers changed from: package-private */
        public void addFragment(Fragment fragment, String str) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(str);
        }

        public CharSequence getPageTitle(int i) {
            return this.mFragmentTitleList.get(i);
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