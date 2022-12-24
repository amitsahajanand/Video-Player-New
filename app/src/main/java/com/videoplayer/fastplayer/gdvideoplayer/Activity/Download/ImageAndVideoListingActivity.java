package com.videoplayer.fastplayer.gdvideoplayer.Activity.Download;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.videoplayer.fastplayer.gdvideoplayer.Activity.Adapter.FileListAdapter;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.FullView.FullViewActivity;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.AppConstants;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.ItemOffsetDecoration;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Preference;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.Interface.ItemClickInterface;
import com.videoplayer.fastplayer.gdvideoplayer.R;

import static com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils.lattestfile_path;

public class ImageAndVideoListingActivity extends BaseActivity implements ItemClickInterface {


    TextView /*tv_name, */tv_nodata;
    SwipeRefreshLayout swiperefresh;
    RecyclerView rv_fileList;

    //variabler
    private FileListAdapter fileListAdapter;
    private ArrayList<File> fileArrayList = new ArrayList<>();
    private ArrayList<File> fileArrayList_withoutads = new ArrayList<>();
    String header;

    GridLayoutManager gridLayoutManager;
    ItemClickInterface itemClickInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_and_video_listing);
        itemClickInterface = (ItemClickInterface) this;

        Declaration();
        Initialization();


    }

    private void Declaration() {

       // tv_name = findViewById(R.id.tv_name);
        tv_nodata = findViewById(R.id.tv_nodata);
        swiperefresh = findViewById(R.id.swiperefresh);
        rv_fileList = findViewById(R.id.rv_fileList);
        gridLayoutManager = new GridLayoutManager(ImageAndVideoListingActivity.this, 1);
        rv_fileList.setLayoutManager(gridLayoutManager);
        rv_fileList.addItemDecoration(new ItemOffsetDecoration(5));


    }

    private void Initialization() {

        if (getIntent() != null) {

            Utils.downloadDoneinterface = new Utils.DownloadDoneinterface() {
                @Override
                public void refreshPage() {
                    if (fileListAdapter != null) {
//                            fileListAdapter.notifyItemChanged(0);
//                        SessionManagement.setIntValue(ImageAndVideoListingActivity.this, AppConstant.PERCENTAGE, 0);
                        Preference.setpercentage(0);
                        getAllFiles();
                    }
                }
            };

            Utils.updateDownPer = new Utils.UpdateDownPer() {
                @Override
                public void percentage(int percentage) {
                    if (fileListAdapter != null) {
                        Preference.setpercentage(percentage);
//                        SessionManagement.setIntValue(ImageAndVideoListingActivity.this, AppConstant.PERCENTAGE, percentage);
                        rv_fileList.post(new Runnable() {
                            @Override
                            public void run() {
                                fileListAdapter.notifyItemChanged(0);
                            }
                        });
                    }
                }
            };

            header = getIntent().getStringExtra("Name");

          //  tv_name.setText(header);

            getAllFiles();

            swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    getAllFiles();
                    swiperefresh.setRefreshing(false);
                }
            });
        }

    }


    private void getAllFiles() {
        fileArrayList = new ArrayList<>();
        File[] files = new File[0];
        if (header.equals(AppConstants.INSTAGRAM)) {
            files = Utils.RootDirectoryInstaShow.listFiles();
        } else if (header.equals(AppConstants.FACEBOOK)) {
            files = Utils.RootDirectoryFacebookShow.listFiles();
        } else if (header.equals(AppConstants.WHATSAPP)) {
            files = Utils.RootDirectoryWhatsappShow.listFiles();
        } else if (header.equals(AppConstants.TWITTER)) {
            files = Utils.RootDirectoryTwitter.listFiles();
        } else if (header.equals(AppConstants.TIKTOK)) {
            files = Utils.RootDirectoryTikTok.listFiles();
        } else if (header.equals(AppConstants.SHARECHAT)) {
            files = Utils.RootDirectoryShareChat.listFiles();
        }


        if (files != null) {

            Arrays.sort(files, new Comparator<File>() {
                public int compare(File f1, File f2) {
                    return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
                }
            });

            if (files.length > 0 /*|| Utils.isDownloading*/) {

                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (File file : files) {
                    fileArrayList.add(file);

                }

//                fileArrayList_withoutads.addAll(fileArrayList);
//                lattestfile_path = fileArrayList.get(2).getPath();

                Collections.reverse(fileArrayList);
                fileArrayList_withoutads.addAll(fileArrayList);
                if (Utils.isDownloading) {


                    if (isFileDownload()) {

                        if (!fileArrayList.get(0).getAbsolutePath().split("/")[fileArrayList.get(0).getAbsolutePath().split("/").length - 1].equals(lattestfile_path.split("/")[lattestfile_path.split("/").length - 1]))
                        {

                            fileArrayList.remove(0);

                        }

                        fileArrayList.add(0, null);
                    }
                }

                if (fileArrayList.size() > 0) {
                    Log.d("listofdile", new Gson().toJson(fileArrayList));
//                    if (Ads.admobnativeAd != null) {
//                        fileArrayList = getVideoListForAds(fileArrayList);
//                    } else {
//                        if (Preference.getis_big_native_qureka()) {
//                            fileArrayList = getVideoListForAds(fileArrayList);
//                        }
//                    }
                    fileArrayList = getVideoListForAds(fileArrayList);


                    fileListAdapter = new FileListAdapter(ImageAndVideoListingActivity.this, fileArrayList);
                    rv_fileList.setAdapter(fileListAdapter);

                    rv_fileList.setVisibility(View.VISIBLE);
                    tv_nodata.setVisibility(View.GONE);
                } else {

                    rv_fileList.setVisibility(View.GONE);
                    tv_nodata.setVisibility(View.VISIBLE);
                }
            } else {

                if (Utils.isDownloading) {

                    if (isFileDownload()) {

                        fileArrayList.add(0, null);

                        fileListAdapter = new FileListAdapter(ImageAndVideoListingActivity.this, fileArrayList);
                        rv_fileList.setAdapter(fileListAdapter);


                        rv_fileList.setVisibility(View.VISIBLE);
                        tv_nodata.setVisibility(View.GONE);


                    } else {
                        rv_fileList.setVisibility(View.GONE);
                        tv_nodata.setVisibility(View.VISIBLE);
                    }

                } else {

                    rv_fileList.setVisibility(View.GONE);
                    tv_nodata.setVisibility(View.VISIBLE);
                }
            }
        } else {
            rv_fileList.setVisibility(View.GONE);
            tv_nodata.setVisibility(View.VISIBLE);
        }
    }


    private boolean isFileDownload() {
        boolean isdownload = false;
        if (Utils.fromintent == 1 && header.equals(AppConstants.INSTAGRAM)) {
            isdownload = true;
        } else if (Utils.fromintent == 2 && header.equals(AppConstants.FACEBOOK)) {
            isdownload = true;
        } else if (Utils.fromintent == 3 && header.equals(AppConstants.TWITTER)) {
            isdownload = true;
        } else if (Utils.fromintent == 4 && header.equals(AppConstants.TIKTOK)) {
            isdownload = true;
        } else if (Utils.fromintent == 5 && header.equals(AppConstants.SHARECHAT)) {
            isdownload = true;
        }
        return isdownload;
    }


    @Override
    public void onResume() {
        super.onResume();

//        if (ad_sample) {
//            ad_sample = false;
//            if (Ads_Adapter_List.admob_nativehashmap != null) {
//                Ads_Adapter_List.admob_nativehashmap.clear();
//            }
//            if (fileListAdapter != null) {
//                fileListAdapter.notifyDataSetChanged();
//            }
//        }

        if (AppConstants.BackFromFullView) {
            AppConstants.BackFromFullView = false;
            if (fileListAdapter != null) {
                getAllFiles();
            }
        }
    }

    @Override
    public void onBackPressed() {

        Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
//                ad_sample = true;
                finish();
            }
        });
    }


    private ArrayList<File> getVideoListForAds(ArrayList<File> videoList) {

//        Ads.admob_nativehashmap.clear();

        ArrayList<File> finalvideolist = new ArrayList<>();
//        finalvideolist.add(null);
//        finalvideolist.addAll(videoList);
        int itemcount = 0;
        for (int i = 0; i < videoList.size(); i++) {
            if (itemcount == 3) {
                itemcount = 1;
                finalvideolist.add(null);
                finalvideolist.add(videoList.get(i));

            } else {
                itemcount++;
                finalvideolist.add(videoList.get(i));
            }
        }

        return finalvideolist;
    }

    @Override
    public void getItempositon(File file) {
        int pos = 0;

        if (fileArrayList_withoutads.contains(file)) {
            pos = fileArrayList_withoutads.indexOf(file);
        }


        int finalPos = pos;

        Ads_Interstitial.showAds_full(ImageAndVideoListingActivity.this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {

                Intent inNext = new Intent(ImageAndVideoListingActivity.this, FullViewActivity.class);
                inNext.putExtra("ImageDataFile", fileArrayList_withoutads);
                inNext.putExtra("Position", finalPos);
                startActivity(inNext);

            }
        });

    }

}