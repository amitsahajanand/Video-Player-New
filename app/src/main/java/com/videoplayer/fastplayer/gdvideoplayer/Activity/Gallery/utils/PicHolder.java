package com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.videoplayer.fastplayer.gdvideoplayer.R;


/**
 * Author CodeBoy722
 * <p>
 * picture_Adapter's ViewHolder
 */

public class PicHolder extends RecyclerView.ViewHolder {

    public ImageView picture;
    public TextView tv_name, tv_path;
    public LinearLayout ll_detail;

    LinearLayout llline, llnative,ll_ads;

    PicHolder(@NonNull View itemView) {
        super(itemView);

        picture = itemView.findViewById(R.id.image);
        tv_name = itemView.findViewById(R.id.tv_name);
        tv_path = itemView.findViewById(R.id.tv_path);

        ll_detail = itemView.findViewById(R.id.ll_detail);

        ll_ads = (LinearLayout) itemView.findViewById(R.id.ll_ads);
        llline = (LinearLayout) itemView.findViewById(R.id.llline);
        llnative = (LinearLayout) itemView.findViewById(R.id.llnative);
    }
}
