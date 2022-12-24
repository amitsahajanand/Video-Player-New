package com.videoplayer.fastplayer.gdvideoplayer.Activity.FullView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.R;

public class ViewVideoScreen extends BaseActivity {
    private static final String TAG = "Share_Video";
    String path;
    String From = "";
    PlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    LinearLayout lnr_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video_screen);
        lnr_back = findViewById(R.id.lnr_back);

        path = getIntent().getStringExtra("share");

        Log.d(TAG, "onCreate:=================>  " + path);

        exoPlayerView = findViewById(R.id.idExoPlayerView1);

        lnr_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        LinearLayout lnr_ads_banner = (LinearLayout) findViewById(R.id.lnr_ads_banner);
//        LinearLayout ll_nativeadfullview_banner = (LinearLayout) findViewById(R.id.ll_nativeadfullview_banner);
//        LinearLayout ll_ads_banner = (LinearLayout) findViewById(R.id.ll_ads_banner);
//        Ads.Banner_Native_adtype(ViewVideoScreen.this, ll_nativeadfullview_banner, lnr_ads_banner, ll_ads_banner, 0, false);

    }


    private void createVideoPlayer(String m) {
        LoadControl loadControl = new DefaultLoadControl();


        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);

        Uri videoUri = Uri.parse(m);

//        DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource mediaSource = new ExtractorMediaSource(videoUri, new DefaultDataSourceFactory(this, "Exoplayer-local"),
                extractorsFactory, null, null);


        exoPlayerView.setPlayer(exoPlayer);
        exoPlayerView.setKeepScreenOn(true);
        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);

        exoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });
    }

    @Override
    protected void onResume() {
        createVideoPlayer(path);
        super.onResume();
    }


    @Override
    public void onBackPressed() {

        exoPlayer.setPlayWhenReady(false);
        exoPlayer.stop();
        exoPlayer.release();

        Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                finish();
            }
        });

    }
}