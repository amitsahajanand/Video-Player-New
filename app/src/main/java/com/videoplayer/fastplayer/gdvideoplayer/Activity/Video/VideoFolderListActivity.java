package com.videoplayer.fastplayer.gdvideoplayer.Activity.Video;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Adapter_List;
import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Adapter.LastWatchVideoListAdapter;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Adapter.VideoFolderLlistAdapter;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.ImageDisplay;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.PicHolder;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.imageFolder;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.itemClickListener;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.pictureFacer;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.pictureFolderAdapter;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.GetData;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.ItemOffsetDecoration;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.Interface.FolderItemInterface;
import com.videoplayer.fastplayer.gdvideoplayer.Model.VideoFolder;
import com.videoplayer.fastplayer.gdvideoplayer.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class VideoFolderListActivity extends BaseActivity implements FolderItemInterface {

    //View
    TextView tv_foldername, tv_nodata;
    RecyclerView rv_folderlist;
    LinearLayout lnr_search;
    ProgressBar progress_video;
    List<VideoFolder> videoFolderList = new ArrayList<>();
    FolderItemInterface folderItemInterface;
    Dialog searchdialog;
    VideoFolderLlistAdapter videoFolderLlistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_video_folder_list );
        folderItemInterface = (FolderItemInterface) this;
        Declaration();
        Initialization();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void Declaration() {

        tv_foldername = findViewById( R.id.tv_foldername );
        progress_video = findViewById( R.id.progress_video );

        tv_nodata = findViewById( R.id.tv_nodata );
        rv_folderlist = findViewById( R.id.rv_folderlist );
        lnr_search = findViewById( R.id.lnr_search );

    }

    private void Initialization() {

        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        rv_folderlist.setLayoutManager(gridLayoutManager);
//        rv_folderlist.addItemDecoration(new ItemOffsetDecoration(10));
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return position % 5 == 4 ? 2 : 1;
//            }
//        });

        videoFolderLlistAdapter = new VideoFolderLlistAdapter(this, videoFolderList);
        rv_folderlist.setAdapter(videoFolderLlistAdapter);


        LoadData loaddata = new LoadData();
        loaddata.execute();
    }



    public class LoadData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress_video.setVisibility(View.VISIBLE);
            videoFolderList = new ArrayList<>();

        }

        @Override
        protected Void doInBackground(Void... voids) {
//            videoFolderArrayList = GetData.getVideoFolderList(getActivity());
            videoFolderList = GetData.getVideoFolderList(VideoFolderListActivity.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (videoFolderList.size() > 0) {

                videoFolderLlistAdapter.UpdateData(getVideoListForAds(videoFolderList));
                rv_folderlist.setVisibility(View.VISIBLE);
                tv_nodata.setVisibility(View.GONE);
            } else {
                rv_folderlist.setVisibility(View.GONE);
                tv_nodata.setVisibility(View.VISIBLE);
            }

            progress_video.setVisibility(View.GONE);
        }
    }

    private List<VideoFolder> getVideoListForAds(List<VideoFolder> videoList) {

        List<VideoFolder> finalvideolist = new ArrayList<>();
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
    public void onBackPressed() {

        Ads_Interstitial.BackshowAds_full( this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                finish();
            }
        } );

    }

    @Override
    public void folderclick(List<VideoFolder> mData, int position) {
        Ads_Interstitial.showAds_full( VideoFolderListActivity.this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                startActivity( new Intent( VideoFolderListActivity.this, VideoListActivity.class )
                        .putExtra( GetData.foldername, mData.get( position ).getFoldername() )
                        .putExtra( GetData.videolist, new Gson().toJson( mData.get( position ).getVideoList() ) ) );

                if (searchdialog != null) {
                    searchdialog.dismiss();
                    searchdialog = null;
                }
            }
        } );
    }
}