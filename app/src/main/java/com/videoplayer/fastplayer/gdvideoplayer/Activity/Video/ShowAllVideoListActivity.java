package com.videoplayer.fastplayer.gdvideoplayer.Activity.Video;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Adapter.VideoAllFolderAdapter;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.GetData;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.ItemOffsetDecoration;
import com.videoplayer.fastplayer.gdvideoplayer.Interface.FolderItemInterface;
import com.videoplayer.fastplayer.gdvideoplayer.Model.VideoFolder;
import com.videoplayer.fastplayer.gdvideoplayer.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ShowAllVideoListActivity extends BaseActivity implements FolderItemInterface {

    FolderItemInterface folderItemInterface;
    TextView tv_foldername, tv_nodata;
    RecyclerView rv_folderlist;
    ProgressBar progress_video;
    VideoAllFolderAdapter videoAllFolderAdapter;
    List<VideoFolder> videoFolderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_show_all_video_list );

        folderItemInterface = (FolderItemInterface) this;
        Declaration();
        Initialization();

    }

    private void Declaration() {

        tv_foldername = findViewById(R.id.tv_foldername);
        progress_video = findViewById(R.id.progress_video);
        tv_nodata = findViewById(R.id.tv_nodata);
        rv_folderlist = findViewById(R.id.rv_folderlist);

    }

    private void Initialization() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rv_folderlist.addItemDecoration(new ItemOffsetDecoration(10));
        rv_folderlist.setLayoutManager( gridLayoutManager );
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 5 == 4 ? 2 : 1;
            }
        });

        videoAllFolderAdapter = new VideoAllFolderAdapter(this, videoFolderList);
        rv_folderlist.setAdapter(videoAllFolderAdapter);

        LoadData loaddata = new LoadData();
        loaddata.execute();

    }

    private List<VideoFolder> getVideoListForAds(List<VideoFolder> videoList) {

        List<VideoFolder> finalvideolist = new ArrayList<>();
        int itemcount = 0;
        for (int i = 0; i < videoList.size(); i++) {
            if (itemcount == 4) {
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
    public void folderclick(List<VideoFolder> mData, int position) {
        Ads_Interstitial.showAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                startActivity(new Intent(ShowAllVideoListActivity.this, VideoListActivity.class)
                        .putExtra(GetData.foldername, mData.get(position).getFoldername())
                        .putExtra(GetData.videolist, new Gson().toJson(mData.get(position).getVideoList())));
            }
        });
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

    public class LoadData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress_video.setVisibility( View.VISIBLE);
            videoFolderList = new ArrayList<>();

        }

        @Override
        protected Void doInBackground(Void... voids) {
//            videoFolderArrayList = GetData.getVideoFolderList(getActivity());
            videoFolderList = GetData.getVideoFolderList(ShowAllVideoListActivity.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (videoFolderList.size() > 0) {

                videoAllFolderAdapter.UpdateData(getVideoListForAds(videoFolderList));
                rv_folderlist.setVisibility(View.VISIBLE);
                tv_nodata.setVisibility(View.GONE);
            } else {
                rv_folderlist.setVisibility(View.GONE);
                tv_nodata.setVisibility(View.VISIBLE);
            }


            progress_video.setVisibility(View.GONE);
        }
    }

}