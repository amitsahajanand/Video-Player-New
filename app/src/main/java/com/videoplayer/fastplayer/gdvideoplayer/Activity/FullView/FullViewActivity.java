package com.videoplayer.fastplayer.gdvideoplayer.Activity.FullView;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import java.io.File;
import java.util.ArrayList;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Adapter.ShowImagesAdapter;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.AppConstants;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.GetData;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;

import static android.content.ContentValues.TAG;

public class FullViewActivity extends BaseActivity {

    ViewPager viewpager_imgvid;
    RelativeLayout rl_whatsapp, rl_share, rl_delete;


    private ArrayList<File> fileArrayList;
    private int Position = 0;
    ShowImagesAdapter showImagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);


        Declaration();
        Initiazlization();

    }

    private void Declaration() {


        viewpager_imgvid = findViewById(R.id.viewpager_imgvid);
        rl_whatsapp = findViewById(R.id.rl_whatsapp);
        rl_share = findViewById(R.id.rl_share);
        rl_delete = findViewById(R.id.rl_delete);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fileArrayList = (ArrayList<File>) getIntent().getSerializableExtra("ImageDataFile");
            Position = getIntent().getIntExtra("Position", 0);
        }


    }

    private void Initiazlization() {

        showImagesAdapter = new ShowImagesAdapter(this, fileArrayList, FullViewActivity.this);
        viewpager_imgvid.setAdapter(showImagesAdapter);
        viewpager_imgvid.setCurrentItem(Position);

        viewpager_imgvid.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int pos) {
                Position = pos;
                System.out.println("Current position==" + Position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int num) {
            }
        });


        rl_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileArrayList.get(Position).getName().contains(".mp4")) {
                    GetData.shareImageAndVideo(FullViewActivity.this, fileArrayList.get(Position).getPath(), true);
                } else {

                    GetData.shareImageAndVideo(FullViewActivity.this, fileArrayList.get(Position).getPath(), false);
                }
            }
        });


        rl_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileArrayList.get(Position).getName().contains(".mp4")) {
                    Log.d("SSSSS", "onClick: " + fileArrayList.get(Position) + "");
                    GetData.shareVideo(FullViewActivity.this, fileArrayList.get(Position).getPath());
                } else {
                    GetData.shareImage(FullViewActivity.this, fileArrayList.get(Position).getPath());
                }
            }
        });


        rl_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowConfirmDilaog(Position);
            }
        });

    }


    private void ShowConfirmDilaog(int position) {
        try {
            final Dialog dialog2 = new Dialog(FullViewActivity.this);
            dialog2.requestWindowFeature(1);
            if (dialog2.getWindow() != null) {
                dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog2.getWindow().getAttributes().windowAnimations = R.style.DefaultDialogAnimation;
            }

            dialog2.setContentView(R.layout.new_dialog_junk_cancel);
            dialog2.setCancelable(false);
            dialog2.setCanceledOnTouchOutside(false);
            dialog2.getWindow().setLayout(-1, -1);
            dialog2.getWindow().setGravity(17);
            dialog2.findViewById(R.id.dialog_img).setVisibility(View.GONE);
            ((TextView) dialog2.findViewById(R.id.dialog_title)).setText("Delete");
            ((TextView) dialog2.findViewById(R.id.dialog_msg)).setText("Are you sure you want to delete ?");

            dialog2.findViewById(R.id.ll_no).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    dialog2.dismiss();
                    Log.d(TAG, "onClick: dismiss");
                }
            });

            dialog2.findViewById(R.id.ll_yes).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    File file = fileArrayList.get(Position);
                    boolean b = fileArrayList.get(Position).delete();
                    Log.d(TAG, "onClick: delate");
                    if (b) {
                        delete(Position);
                    }

                    dialog2.dismiss();

                    MediaScannerConnection.scanFile(FullViewActivity.this, new String[]{file.getPath()}, new String[]{getMimeType(file.getPath())}, new MediaScannerConnection.MediaScannerConnectionClient() {
                        public void onMediaScannerConnected() {
                        }

                        public void onScanCompleted(String str, Uri uri) {
                        }
                    });

                }

            });
            dialog2.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }


    public void delete(int position) {
        fileArrayList.remove(position);
        showImagesAdapter.notifyDataSetChanged();
        Toast.makeText(this, "File Deleted.", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                Utils.ad_sample = true;
                AppConstants.BackFromFullView = true;
                finish();
            }
        });

    }

}