package com.videoplayer.fastplayer.gdvideoplayer.Activity.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Adapter_List;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.util.ArrayList;

import com.videoplayer.fastplayer.gdvideoplayer.Activity.Download.ImageAndVideoListingActivity;

import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Preference;
import com.videoplayer.fastplayer.gdvideoplayer.Interface.ItemClickInterface;
import com.videoplayer.fastplayer.gdvideoplayer.R;


import static android.view.View.GONE;
import static com.ads.adsdemosp.AdsClass.Ads_Adapter_List.admob_nativehashmap;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.ViewHolder> {
    private ImageAndVideoListingActivity imageAndVideoListingActivity;
    private ArrayList<File> fileArrayList;

    ItemClickInterface itemClickInterface;
    private LayoutInflater layoutInflater;

    public FileListAdapter(ImageAndVideoListingActivity context2, ArrayList<File> arrayList) {
        this.imageAndVideoListingActivity = context2;
        this.fileArrayList = arrayList;
        itemClickInterface = (ItemClickInterface) imageAndVideoListingActivity;

        if (Ads_Adapter_List.admob_nativehashmap != null) {
            Ads_Adapter_List.admob_nativehashmap.clear();
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder((LayoutInflater.from(imageAndVideoListingActivity).inflate(R.layout.items_file_view, viewGroup, false)));
    }

    @SuppressLint("WrongConstant")
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {


        final File file = fileArrayList.get(i);

        if (file != null) {

            viewHolder.ll_detail.setVisibility(View.VISIBLE);
            viewHolder.ll_downloading.setVisibility(View.GONE);
            viewHolder.lnr_ads_show.setVisibility(GONE);

            try {

                Glide.with(imageAndVideoListingActivity).load(file.getPath()).into(viewHolder.iv_image2);
                viewHolder.tv_name.setText(file.getName());
                viewHolder.tv_path.setText(file.getPath());


            } catch (Exception unused) {
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    itemClickInterface.getItempositon(file);

                }
            });
        } else {

            if (i == 0) {
                viewHolder.ll_detail.setVisibility(View.GONE);
                viewHolder.ll_downloading.setVisibility(View.VISIBLE);
                viewHolder.lnr_ads_show.setVisibility(GONE);

                viewHolder.rl_view.setVisibility(View.GONE);
                viewHolder.ivCameraIcon.setVisibility(View.GONE);
                viewHolder.ll_downloading.setVisibility(View.VISIBLE);
                viewHolder.tv_update.setText(Preference.getpercentage() + "%");
            } else {

                viewHolder.ll_detail.setVisibility(View.GONE);
                viewHolder.ll_downloading.setVisibility(View.GONE);
                viewHolder.lnr_ads_show.setVisibility(View.VISIBLE);

                if (admob_nativehashmap != null && admob_nativehashmap.get(i) != null) {
                    try {
                        if (admob_nativehashmap.get(i).getParent() != null)
                            ((ViewGroup) admob_nativehashmap.get(i).getParent()).removeView(admob_nativehashmap.get(i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        if (admob_nativehashmap.get(i) != null) {
                            viewHolder.llnative.removeAllViews();
                            viewHolder.llline.setVisibility(View.GONE);
                            viewHolder.llnative.setVisibility(View.VISIBLE);
                            Log.e("NativeFull_Show", "admob Native ad show : ");
                            viewHolder.llnative.addView(admob_nativehashmap.get(i));

                        } else {
                            viewHolder.llline.setVisibility(View.GONE);
                            viewHolder.llnative.setVisibility(View.GONE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d("onBindViewHolder12", "one");
                    return;
                }
                Ads_Adapter_List.NativeFull_Show(imageAndVideoListingActivity, viewHolder.llnative, viewHolder.llline, i);
                admob_nativehashmap = admob_nativehashmap;

//                viewHolder.ll_detail.setVisibility(View.GONE);
//                viewHolder.ll_downloading.setVisibility(View.GONE);
//                viewHolder.lnr_ads_show.setVisibility(View.VISIBLE);
//
//                Ads_Adapter_List.NativeFull_Show(imageAndVideoListingActivity, viewHolder.llnative, viewHolder.llline, i);

            }

        }

    }

    public int getItemCount() {
        ArrayList<File> arrayList = fileArrayList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView iv_image;
        ImageView ivCameraIcon;
        RelativeLayout rl_view;
        LinearLayout ll_downloading;
        TextView tv_update,folder_name;

        LinearLayout lnr_ads_show, llline, llnative;

        LinearLayout ll_detail;
        ImageView iv_image2;
        TextView tv_name, tv_path;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCameraIcon = itemView.findViewById(R.id.ivCameraIcon);
            iv_image = itemView.findViewById(R.id.iv_image);
            rl_view = itemView.findViewById(R.id.rl_view);
            ll_downloading = itemView.findViewById(R.id.ll_downloading);
            tv_update = itemView.findViewById(R.id.tv_update);
            folder_name = itemView.findViewById(R.id.folder_name);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_path = itemView.findViewById(R.id.tv_path);
            iv_image2 = itemView.findViewById(R.id.iv_image2);
            ll_detail = itemView.findViewById(R.id.ll_detail);

            llline = itemView.findViewById(R.id.llline);
            llnative = itemView.findViewById(R.id.llnative);
            lnr_ads_show = itemView.findViewById(R.id.lnr_ads_show);
        }
    }
}
