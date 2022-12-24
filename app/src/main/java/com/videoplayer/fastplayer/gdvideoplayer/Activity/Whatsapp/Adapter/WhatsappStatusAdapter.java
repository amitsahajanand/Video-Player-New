package com.videoplayer.fastplayer.gdvideoplayer.Activity.Whatsapp.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Adapter_List;
import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

import com.videoplayer.fastplayer.gdvideoplayer.Activity.Download.ImageAndVideoListingActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Whatsapp.FulllWhatsappScreen;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.AppConstants;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.Model.WhatsappStatusModel;
import com.videoplayer.fastplayer.gdvideoplayer.R;

import static android.view.View.GONE;


public class WhatsappStatusAdapter extends RecyclerView.Adapter<WhatsappStatusAdapter.ViewHolder> {
    public String SaveFilePath = (Utils.RootDirectoryWhatsappShow + "/");
    public Activity context;
    private ArrayList<WhatsappStatusModel> fileArrayList;
    private ArrayList<WhatsappStatusModel> statuswithout_ads;
    private LayoutInflater layoutInflater;
    boolean isVideo;

    public WhatsappStatusAdapter(Activity context, ArrayList<WhatsappStatusModel> arrayList, ArrayList<WhatsappStatusModel> statuswithout_ads) {
        this.context = context;
        this.fileArrayList = arrayList;
        this.statuswithout_ads = statuswithout_ads;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.items_whatsapp_view, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        final WhatsappStatusModel whatsappStatusModel = this.fileArrayList.get(position);

        if (whatsappStatusModel != null) {

            holder.ll_detail.setVisibility(View.VISIBLE);
            holder.lnr_ads_show.setVisibility(GONE);

            Glide.with(context).load(whatsappStatusModel.getPath()).into(holder.iv_image);
            holder.tv_name.setText(whatsappStatusModel.getName());
            if (whatsappStatusModel.getUri().toString().endsWith(".mp4")) {
                holder.iv_play.setVisibility(View.VISIBLE);
            } else {
                holder.iv_play.setVisibility(GONE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Ads_Interstitial.showAds_full(context, new Ads_Interstitial.OnFinishAds() {
                        @Override
                        public void onFinishAds(boolean b) {
                            Intent inNext = new Intent(context, FulllWhatsappScreen.class);
                            Utils.whatsappStatusModelArrayList = statuswithout_ads;
                            Utils.position = 0;
                            if (statuswithout_ads.contains(whatsappStatusModel)) {
                                int pos = statuswithout_ads.indexOf(whatsappStatusModel);
                                Utils.position = pos;
                            }

//                            inNext.putExtra("ImageDataFile", fileArrayList);
//                            inNext.putExtra("Position", i);
                            context.startActivity(inNext);
                        }
                    });


                }
            });


            holder.btn_download.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    Ads_Interstitial.showAds_full(context, new Ads_Interstitial.OnFinishAds() {
                        @Override
                        public void onFinishAds(boolean b) {
//                            Utils.createFileFolder();
//                            if (Build.VERSION.SDK_INT >= 29) {
//                                Downloadnew(whatsappStatusModel);
//                            } else {
//                                Download(whatsappStatusModel);
//                            }
//
//                            context.startActivity(new Intent(context, ImageAndVideoListingActivity.class).putExtra("Name", AppConstants.WHATSAPP));
                            Utils.createFileFolder();
                            if (Build.VERSION.SDK_INT >= 29) {
                                if(Utils.Check_WhatsApp_path) {
                                    Downloadnew(whatsappStatusModel);
                                }else{
                                    Download(whatsappStatusModel);
                                }
                            } else {
                                Download(whatsappStatusModel);
                            }

                            context.startActivity(new Intent(context, ImageAndVideoListingActivity.class).putExtra("Name", AppConstants.WHATSAPP));

                        }
                    });

                }

            });

        } else {
            holder.ll_detail.setVisibility(GONE);
            holder.lnr_ads_show.setVisibility(View.VISIBLE);

            Ads_Adapter_List.NativeFull_Show(context, holder.llnative, holder.llline, position);
        }

      /*  DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        double width = displayMetrics.widthPixels / 2.7;
        double height = displayMetrics.widthPixels / 1.4;

        viewHolder.itemView.getLayoutParams().height = (int) height;
        viewHolder.iv_img.getLayoutParams().height = (int) height;


        final WhatsappStatusModel whatsappStatusModel = this.fileArrayList.get(i);
        if (whatsappStatusModel.getUri().toString().endsWith(".mp4")) {
            viewHolder.iv_play.setVisibility(View.VISIBLE);
        } else {
            viewHolder.iv_play.setVisibility(GONE);
        }
        Glide.with(context).load(whatsappStatusModel.getPath()).into(viewHolder.iv_img);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Ads.showAds(context, new Ads.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        Intent inNext = new Intent(context, FulllWhatsappScreen.class);
                        Utils.whatsappStatusModelArrayList = fileArrayList;
                        Utils.position = i;

//                            inNext.putExtra("ImageDataFile", fileArrayList);
//                            inNext.putExtra("Position", i);
                        context.startActivity(inNext);
                    }
                });


            }
        });

        viewHolder.ll_download.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Ads.showAds(context, new Ads.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        Utils.createFileFolder();
                        if (Build.VERSION.SDK_INT >= 29) {
                            Downloadnew(whatsappStatusModel);
                        } else {
                            Download(whatsappStatusModel);
                        }

                        context.startActivity(new Intent(context, ImageAndVideoListingActivity.class).putExtra("Name", AppConstants.WHATSAPP));
                        *//*Download(whatsappStatusModel);
                        context.startActivity(new Intent(context, ImageAndVideoListingActivity.class).putExtra("Name", AppConstants.WHATSAPP));*//*
                    }
                });

            }

        });*/
    }

    private void Downloadnew(WhatsappStatusModel whatsappStatusModel) {
        byte[] videoBytes;
        if (whatsappStatusModel.getUri().getScheme().equals("content")) {
            //videoBytes = getBytes(iStream);
            try {
                InputStream iStream = context.getContentResolver().openInputStream(whatsappStatusModel.getUri());
                videoBytes = IOUtils.toByteArray(iStream);
                Log.d("FullWhatsApp12", "videoBytes = " + videoBytes);
                String extension = whatsappStatusModel.getPath().substring(whatsappStatusModel.getPath().lastIndexOf("."));
                SavePhotoTask(videoBytes, extension);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            File file = new File(whatsappStatusModel.getUri().getPath());
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                videoBytes = IOUtils.toByteArray(fileInputStream);
                Log.d("FullWhatsApp12", "videoBytes 1 = " + videoBytes);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void SavePhotoTask(byte[] jpeg, String extension) {
        Log.d("FullWhatsApp12", "extension = " + extension);
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + extension;

        Log.d("FullWhatsApp12", "SavePhotoTask = " + fileName);
        File directory = new File(SaveFilePath);
        if (!directory.exists()) {
            Log.d("VideoDownload_Screen12", "makeing = ");
            directory.mkdir();
        }
        Log.d("FullWhatsApp12", "SavePhotoTask = ");
        final File photo = new File(directory, fileName);
        Log.d("FullWhatsApp12", "photo = " + photo.getPath());
        try {
            Log.d("FullWhatsApp12", "FileOutputStream = ");
            FileOutputStream fos = new FileOutputStream(photo.getPath());
            fos.write(jpeg);
            fos.close();
        } catch (Exception e) {
            Log.d("FullWhatsApp12", "Exception = " + e.getMessage());
        }
    }


    private void Download(WhatsappStatusModel whatsappStatusModel) {
        Utils.createFileFolder();
        String path = whatsappStatusModel.getPath();
        String substring = path.substring(path.lastIndexOf("/") + 1);
        try {
            FileUtils.copyFileToDirectory(new File(path), new File(SaveFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String substring2 = substring.substring(12);
        MediaScannerConnection.scanFile(context, new String[]{new File(SaveFilePath + substring2).getAbsolutePath()}, new String[]{whatsappStatusModel.getUri().toString().endsWith(".mp4") ? "simplevideoshow/*" : "image/*"}, new MediaScannerConnection.MediaScannerConnectionClient() {
            public void onMediaScannerConnected() {
            }

            public void onScanCompleted(String str, Uri uri) {
            }
        });
        new File(SaveFilePath, substring).renameTo(new File(SaveFilePath, substring2));
        Toast.makeText(context, "Saved to:" + SaveFilePath + substring2, Toast.LENGTH_LONG).show();
    }

    public int getItemCount() {
        ArrayList<WhatsappStatusModel> arrayList = this.fileArrayList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        RoundedImageView iv_img;
        ImageView iv_play, iv_image;
        LinearLayout ll_download;
        TextView tv_name;
        Button btn_download;
        LinearLayout ll_detail;

        LinearLayout lnr_ads_show, llline, llnative;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
            iv_play = itemView.findViewById(R.id.iv_play);
            ll_download = itemView.findViewById(R.id.ll_download);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_name = itemView.findViewById(R.id.tv_name);
            btn_download = itemView.findViewById(R.id.btn_download);

            ll_detail = itemView.findViewById(R.id.ll_detail);

            llline = itemView.findViewById(R.id.llline);
            llnative = itemView.findViewById(R.id.llnative);
            lnr_ads_show = itemView.findViewById(R.id.lnr_ads_show);
        }
    }
}
