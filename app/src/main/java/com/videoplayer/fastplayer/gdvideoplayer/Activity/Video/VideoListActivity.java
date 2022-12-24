package com.videoplayer.fastplayer.gdvideoplayer.Activity.Video;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Adapter_List;
import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Adapter.VideoListAdapter;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.GetData;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.ItemOffsetDecoration;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.Interface.VideoCountInterface;
import com.videoplayer.fastplayer.gdvideoplayer.Interface.VideoInerface;
import com.videoplayer.fastplayer.gdvideoplayer.Model.VideoModel;
import com.videoplayer.fastplayer.gdvideoplayer.Player.GlobalVar;
import com.videoplayer.fastplayer.gdvideoplayer.Player.PlayVideoActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Player.videoService.VideoService;
import com.videoplayer.fastplayer.gdvideoplayer.R;

import java.util.ArrayList;
import java.util.List;

public class VideoListActivity extends BaseActivity implements VideoInerface, VideoCountInterface {

    TextView tv_nodata;
    RecyclerView rv_videolist;
    List<VideoModel> videoList = new ArrayList<>();
    List<VideoModel> video_Last_List = new ArrayList<>();
    VideoInerface videoInerface;
    VideoCountInterface videoCountInterface;

    ImageView grid_list;
    int grid_first = 0;
    VideoListAdapter videoListAdapter;
    GridLayoutManager gridLayoutManager_Two;
    LinearLayoutManager linearLayoutManager2;
    List<VideoModel> videoModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_video_list );
        videoCountInterface = (VideoCountInterface) this;
        videoInerface = (VideoInerface) this;
        Declaration();
        Intialization();

    }


    private void Declaration() {

        tv_nodata = findViewById( R.id.tv_nodata );
        rv_videolist = findViewById( R.id.rv_videolist );
        grid_list = findViewById( R.id.grid_list );

    }

    private void Intialization() {


        GridLayoutManager gridLayoutManager = new GridLayoutManager( this, 1, RecyclerView.VERTICAL, false );
        rv_videolist.setLayoutManager( gridLayoutManager );
        rv_videolist.addItemDecoration( new ItemOffsetDecoration( 10 ) );


        grid_list.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (grid_first == 0) {
                    grid_list.setBackground( getResources().getDrawable( R.drawable.ic_category_list ) );
                    gridLayoutManager_Two = new GridLayoutManager( VideoListActivity.this, 2, RecyclerView.VERTICAL, false );
                    rv_videolist.setLayoutManager( gridLayoutManager_Two );
                    gridLayoutManager_Two.setSpanSizeLookup( new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            return position % 5 == 4 ? 2 : 1;
                        }
                    } );
                    videoListAdapter = new VideoListAdapter( VideoListActivity.this, videoModelList, false );
                    rv_videolist.setAdapter( videoListAdapter );
                    grid_first++;

                } else {

                    grid_list.setBackground( getResources().getDrawable( R.drawable.ic_category ) );

                    linearLayoutManager2 = new LinearLayoutManager( VideoListActivity.this );
                    videoListAdapter = new VideoListAdapter( VideoListActivity.this, videoModelList, true );
                    rv_videolist.setLayoutManager( gridLayoutManager );
                    rv_videolist.setAdapter( videoListAdapter );
                    grid_first = 0;
                }

            }
        } );

        inItService();


        if (getIntent() != null) {

            String foldername = "";
            foldername = getIntent().getStringExtra( GetData.foldername );
            String video = getIntent().getStringExtra( GetData.videolist );

            if (!video.isEmpty()) {
                if (foldername.isEmpty()) {
                    videoList = GetData.getVideoList( VideoListActivity.this );
                } else {
                    videoList = new Gson().fromJson( video, new TypeToken<List<VideoModel>>() {
                    }.getType() );
                }

                if (videoList.size() > 0) {
                    tv_nodata.setVisibility( View.GONE );
                    rv_videolist.setVisibility( View.VISIBLE );

                    videoModelList = new ArrayList<>();

                    videoModelList = getVideoListForAds( videoList );
                    videoListAdapter = new VideoListAdapter( VideoListActivity.this, videoModelList, true );
                    rv_videolist.setAdapter( videoListAdapter );


                } else {
                    tv_nodata.setVisibility( View.VISIBLE );
                    rv_videolist.setVisibility( View.GONE );
                }

            } else {
                tv_nodata.setVisibility( View.VISIBLE );
                rv_videolist.setVisibility( View.GONE );
            }
        }


        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mGlobalVar.videoService == null) {
                    handler.postDelayed( this, 10 );
                } else {
                    Log.d( "stopnoti", "run: " );
                    mGlobalVar.pause();
                    handler.removeCallbacks( this );
                }

            }
        };
        runnable.run();
    }

    protected static Intent _playIntent;
    GlobalVar mGlobalVar = GlobalVar.getInstance();

    public void inItService() {
        _playIntent = new Intent( VideoListActivity.this, VideoService.class );
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService( _playIntent );
            } else {
                startService( _playIntent );
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return;
        }

        bindService( _playIntent, videoServiceConnection, Context.BIND_AUTO_CREATE );
    }

    protected ServiceConnection videoServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            VideoService.VideoBinder binder = (VideoService.VideoBinder) service;
            GlobalVar.getInstance().videoService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (GlobalVar.getInstance().videoService != null) {
            stopService( new Intent( this, VideoService.class ) );
            mGlobalVar.videoService = null;
            unbindService( videoServiceConnection );
        }
    }

    @Override
    public void onBackPressed() {

        Ads_Interstitial.BackshowAds_full( this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                Utils.ad_sample = true;
                finish();
            }
        } );


    }

    @Override
    public void videoclick(List<VideoModel> mData, int position) {

        Ads_Interstitial.showAds_full( this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {

                if (searchdialog != null) {
                    searchdialog.dismiss();
                    searchdialog = null;
                }
                int pos = 0;
                if (videoList.contains( mData.get( position ) )) {
                    pos = videoList.indexOf( mData.get( position ) );
                }

                GlobalVar.getInstance().videoItemsPlaylist = videoList;
                GlobalVar.getInstance().playingVideo = videoList.get( pos );
                GlobalVar.getInstance().seekPosition = 0;

                video_Last_List.add( mData.get( position ) );

                if (GlobalVar.getInstance().getPlayer() == null) {
                    return;
                } else if (!GlobalVar.getInstance().isMutilSelectEnalble) {
                    if (!GlobalVar.getInstance().isPlayingAsPopup()) {
                        GlobalVar.getInstance().videoService.playVideo( GlobalVar.getInstance().seekPosition, false );
                        Intent intent = new Intent( VideoListActivity.this, PlayVideoActivity.class );
                        startActivity( intent );
                        if (GlobalVar.getInstance().videoService != null)
                            GlobalVar.getInstance().videoService.releasePlayerView();
                    } else {

                    }
                }


            }
        } );


    }

    Dialog searchdialog;

    //View
    EditText et_search;
    ImageView iv_close_search;
    RecyclerView rv_search_folder;
    LinearLayout ll_nodata;


    private void OpenSearchDialog(List<VideoModel> videolist_search) {

        if (searchdialog == null) {
            searchdialog = new Dialog( VideoListActivity.this );
            searchdialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
            searchdialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );
            searchdialog.setContentView( R.layout.dialog_search );
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            final int height = ViewGroup.LayoutParams.MATCH_PARENT;
            searchdialog.getWindow().setLayout( width, height );
            searchdialog.setCancelable( false );
            searchdialog.show();
        }

        et_search = searchdialog.findViewById( R.id.et_search );
        iv_close_search = searchdialog.findViewById( R.id.iv_close_search );
        rv_search_folder = searchdialog.findViewById( R.id.rv_search_folder );
        ll_nodata = searchdialog.findViewById( R.id.ll_nodata );


        rv_search_folder.setLayoutManager( new GridLayoutManager( VideoListActivity.this, 1 ) );
        rv_search_folder.addItemDecoration( new ItemOffsetDecoration( 10 ) );
//        rv_search_folder.setLayoutManager(new LinearLayoutManager(VideoListActivity.this));


        if (videolist_search.size() > 0) {
            rv_search_folder.setVisibility( View.VISIBLE );
            ll_nodata.setVisibility( View.GONE );


            VideoListAdapter videoListAdapter = new VideoListAdapter( VideoListActivity.this, videolist_search, true );
            rv_search_folder.setAdapter( videoListAdapter );


            et_search.addTextChangedListener( new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                    videoListAdapter.getFilter().filter( charSequence );


                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.toString().trim().length() > 0) {

                    } else {
                        ll_nodata.setVisibility( View.GONE );

                    }

                    if (videoListAdapter.getItemCount() > 0) {


                        rv_search_folder.setVisibility( View.VISIBLE );
                        ll_nodata.setVisibility( View.GONE );
                        videoListAdapter.getFilter().filter( editable );

                    } else {

                        rv_search_folder.setVisibility( View.GONE );
                        ll_nodata.setVisibility( View.VISIBLE );
//                  Toast.makeText(mainActivity, "No data found", Toast.LENGTH_SHORT).show();
                    }

                    if (editable.toString().isEmpty()) {
                        rv_search_folder.setVisibility( View.VISIBLE );
                        ll_nodata.setVisibility( View.GONE );
                    }

                }
            } );

        } else {
            rv_search_folder.setVisibility( View.GONE );
            ll_nodata.setVisibility( View.VISIBLE );
        }


        iv_close_search.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchdialog.dismiss();
                searchdialog = null;
            }
        } );


        searchdialog.setOnKeyListener( new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    searchdialog.dismiss();
                    searchdialog = null;
                }
                return true;
            }
        } );

//        searchdialog.setOnKeyListener(new Dialog.OnKeyListener() {
//
//            @Override
//            public boolean onKey(DialogInterface arg0, int keyCode,
//                                 KeyEvent event) {
//                // TODO Auto-generated method stub
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    searchdialog.dismiss();
//                }
//                return true;
//            }
//        });


    }

    @Override
    public void CallCount(VideoModel videoModel) {

        if (videoList.size() > 0) {
            if (videoList.contains( videoModel )) {
                videoList.remove( videoModel );

                if (videoList.size() > 0) {
//                    tv_no_video.setText("" + videoList.size() + " videos");
                } else {
//                    tv_no_video.setText("0 videos");
                    tv_nodata.setVisibility( View.VISIBLE );
                    rv_videolist.setVisibility( View.GONE );
                }
            }
        }

    }

    private List<VideoModel> getVideoListForAds(List<VideoModel> videoList) {

        if (Ads_Adapter_List.admob_nativehashmap != null) {
            Ads_Adapter_List.admob_nativehashmap.clear();
        }

        List<VideoModel> finalvideolist = new ArrayList<>();
//        finalvideolist.add(null);
//        finalvideolist.addAll(videoList);
        int itemcount = 0;
        for (int i = 0; i < videoList.size(); i++) {
            if (itemcount == 4) {
                itemcount = 1;
                finalvideolist.add( null );
                finalvideolist.add( videoList.get( i ) );

            } else {
                itemcount++;
                finalvideolist.add( videoList.get( i ) );
            }

        }

        return finalvideolist;
    }

}