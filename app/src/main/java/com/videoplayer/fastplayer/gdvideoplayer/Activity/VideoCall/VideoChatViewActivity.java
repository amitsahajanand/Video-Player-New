package com.videoplayer.fastplayer.gdvideoplayer.Activity.VideoCall;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.Random;

import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.CameraView;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Preference;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;

public class VideoChatViewActivity extends BaseActivity implements View.OnClickListener, SurfaceHolder.Callback {

    private boolean mCallEnd;


    private FrameLayout mLocalContainer;
    FrameLayout main_frame_layout;
    //  public SurfaceView surfaceView;
    public Camera camera_preview;
    MKLoader mk_loader;
    public VideoView videoView;
    ImageView iv_video_mute;

    // Customized logger view
    //private LoggerRecyclerView mLogView;

    boolean view_show = true;
    boolean voicecheck = true;


    //forcamera
    private Camera mCamera;
    CameraView cameraView;
    private boolean cameraFront = false;


    final Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_chat_view);
        initUI();
        Room_id();

    }


    private void initUI() {
        main_frame_layout = findViewById(R.id.main_frame_layout);
        mLocalContainer = findViewById(R.id.local_video_view_container);

        iv_video_mute = findViewById(R.id.iv_video_mute);

//        showSampleLogs();

    }


    public int random;
    boolean camera = false;
    public ImageView sound_control;
    public MediaPlayer mediaPlayer;
    CountDownTimer countDownTimer;

    public void Room_id() {


        //FOR fake Video Call


        int cameraId = findFrontFacingCamera();
        mCamera = Camera.open(cameraId);
        mCamera.setDisplayOrientation(90);
        cameraView = new CameraView(this, mCamera);
        cameraView.setZOrderMediaOverlay(true);
        mLocalContainer.addView(cameraView);

        main_frame_layout.setVisibility(View.VISIBLE);


        random = new Random().nextInt(255);
        //http://134.209.103.120/VideoCall/public/uploads/video/1619164700_60827e1cdcf54.mp4
        Uri s = Uri.parse("http://139.59.84.76:9158/vids/" + random + ".mp4");
        String s1 = "http://139.59.84.76:9158/vids/" + random + ".mp4";
        Log.d("data_succesfully", "" + s);
        Log.d("data_succesfully", "" + s1);


        mk_loader = findViewById(R.id.mk_loader);


        if (Preference.getisLive()) {

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    videoView = (VideoView) findViewById(R.id.videocall);
                    sound_control = (ImageView) findViewById(R.id.iv_video_mute);
                    sound_control.setOnClickListener(new C0621e());
                    LinearLayout imageView = (LinearLayout) findViewById(R.id.BackCamera);


                    ((ImageView) findViewById(R.id.endCall)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            VideoChatViewActivity.this.onBackPressed();
                        }
                    });
                    getWindow().setFormat(0);
                    videoView.setVideoURI(Uri.parse(s1));
                    videoView.requestFocus();
                    videoView.start();
                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            VideoChatViewActivity.this.mediaPlayer = mp;
                            if (voicecheck) {
                                VideoChatViewActivity.this.mediaPlayer.setVolume(1.0f, 1.0f);
                            } else {
                                VideoChatViewActivity.this.mediaPlayer.setVolume(0.0f, 0.0f);
                            }
                            mp.setLooping(true);
                            mk_loader.setVisibility(View.GONE);
                        }
                    });

                }
            }, 40);
        } else {


            countDownTimer = new CountDownTimer((long) 5000, 1000) {


                public void onTick(long j) {
//                Log.d("TAG", "inside timer" + getStartCount());
                }

                public void onFinish() {
                    getmsgDialog();
                }
            };
            countDownTimer.start();


        }


    }

    //View
    TextView tv_maintenance_msg;
    Button btn_ok, btn_cancel;


    Dialog undermaintenancedialog;

    private void getmsgDialog() {

        undermaintenancedialog = new Dialog(VideoChatViewActivity.this);
        undermaintenancedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        undermaintenancedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        undermaintenancedialog.setContentView(R.layout.dialog_undermaintenanace);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        final int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        undermaintenancedialog.getWindow().setLayout(width, height);
        undermaintenancedialog.setCancelable(false);
        undermaintenancedialog.show();

        tv_maintenance_msg = undermaintenancedialog.findViewById(R.id.tv_maintenance_msg);
        btn_ok = undermaintenancedialog.findViewById(R.id.btn_ok);
        btn_cancel = undermaintenancedialog.findViewById(R.id.btn_cancel);

        tv_maintenance_msg.setText("No one is available for simplevideoshow call");
        btn_cancel.setVisibility(View.GONE);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                undermaintenancedialog.dismiss();
                Ads_Interstitial.BackshowAds_full(VideoChatViewActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity(new Intent(VideoChatViewActivity.this, VideoCallEndScreen.class));
                        finish();
                    }
                });
            }
        });

    }

    public class C0621e implements View.OnClickListener {
        public C0621e() {
        }

        public void onClick(View view) {

            if (voicecheck) {
                voicecheck = false;
                sound_control.setBackgroundResource(0);
                sound_control.setBackgroundResource(R.drawable.voice_stop);
                if (mediaPlayer != null) {
                    VideoChatViewActivity.this.mediaPlayer.setVolume(0.0f, 0.0f);
                }

            } else {
                voicecheck = true;
                sound_control.setBackgroundResource(0);
                sound_control.setBackgroundResource(R.drawable.voice_on);
                if (mediaPlayer != null) {
                    VideoChatViewActivity.this.mediaPlayer.setVolume(1.0f, 1.0f);
                }
            }
        }
    }

    private int findFrontFacingCamera() {

        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                cameraFront = true;
                break;
            }
        }
        return cameraId;

    }

    private int findBackFacingCamera() {
        int cameraId = -1;
        //Search for the back facing camera
        //get the number of cameras
        int numberOfCameras = Camera.getNumberOfCameras();
        //for every camera check
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                cameraFront = false;
                break;

            }

        }
        return cameraId;
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        Camera open = Camera.open(1);
        this.camera_preview = open;
        /*try {
            open.setPreviewDisplay(this.surfaceView.getHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        this.camera_preview.setDisplayOrientation(90);
        this.camera_preview.startPreview();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int i4 = 0;
        Camera.getCameraInfo(0, cameraInfo);
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        if (rotation != 0) {
            if (rotation == 1) {
                i4 = 90;
            } else if (rotation == 2) {
                i4 = 180;
            } else if (rotation == 3) {
                i4 = 270;
            }
        }
        Camera.Parameters parameters = this.camera_preview.getParameters();
        parameters.setRotation(((cameraInfo.orientation - i4) + 360) % 360);
        this.camera_preview.setParameters(parameters);
        this.camera_preview.setDisplayOrientation(90);
        this.camera_preview.startPreview();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onClick(View view) {

    }

    public void onVideoMuteClick(View view) {
        if (mediaPlayer != null) {
            if (voicecheck) {
                VideoChatViewActivity.this.mediaPlayer.setVolume(1.0f, 1.0f);

            } else {
                VideoChatViewActivity.this.mediaPlayer.setVolume(0.0f, 0.0f);
            }
        }

    }

    public void onCallClicked(View view) {
        if (mCallEnd) {
//            startCall();
            mCallEnd = false;
//            mCallBtn.setImageResource(R.drawable.call_end);
        } else {
            checkInternetAvailable();
            //mCallBtn.setImageResource(R.drawable.big_plush);
        }

        showButtons(!mCallEnd);

    }

    private void showButtons(boolean show) {
        int visibility = show ? View.VISIBLE : View.GONE;


    }


    private void checkInternetAvailable() {

        if (Utils.isNetworkConnected(this)) {
            Log.d("test_check", "start check");
            Go_BackApiCall();
        } else {
            try {
                showAlertDialog(getResources().getString(R.string.app_name),
                        getResources().getString(R.string.disconnected),
                        "Retry");
            } catch (NumberFormatException ex) { // handle your exception
            }
        }
    }


    private void showAlertDialog(String title, String msg, String positiveText) {
        androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        checkInternetAvailable();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // finishAffinity();
                        finish();
                    }
                })
                .show();
    }


    private void Go_BackApiCall() {

//        Toast.makeText(this, "Go_BackApiCall", Toast.LENGTH_SHORT).show();
//        mRemoteVideo = null;
//        endCall();
        mCallEnd = true;
        onBackPressed();


    }


    public void onVideoSwitchCamera(View view) {

        int camerasNumber = Camera.getNumberOfCameras();
        if (camerasNumber > 1) {
            //release the old camera instance
            //switch camera, from the front and the back and vice versa

            releaseCamera();
            chooseCamera();
        }
    }

    private void releaseCamera() {
        // stop and release camera
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    public void chooseCamera() {
        //if the camera preview is the front
        if (cameraFront) {
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview

                mCamera = Camera.open(cameraId);
                mCamera.setDisplayOrientation(90);
                cameraView.refreshCamera(mCamera);
            }
        } else {
            int cameraId = findFrontFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview
                mCamera = Camera.open(cameraId);
                mCamera.setDisplayOrientation(90);
                cameraView.refreshCamera(mCamera);
            }
        }
    }

    public void onResume() {

        super.onResume();

        if (cameraView != null) {
            if (mCamera == null) {
                mCamera = Camera.open();
                mCamera.setDisplayOrientation(90);
                cameraView.refreshCamera(mCamera);
                Log.d("nu", "null");
            } else {
                Log.d("nu", "no null");
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
        }

        if (Preference.getisLive()) {

            Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
                @Override
                public void onFinishAds(boolean b) {
                    startActivity(new Intent(VideoChatViewActivity.this, VideoCallEndScreen.class));
                    finish();
                }
            });

        } else {

            if (countDownTimer != null) {
                countDownTimer.cancel();
            }

            Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
                @Override
                public void onFinishAds(boolean b) {
//                    startActivity(new Intent(VideoChatViewActivity.this, VideoCallEndScreen.class));
                    finish();
                }
            });

        }


    }
}