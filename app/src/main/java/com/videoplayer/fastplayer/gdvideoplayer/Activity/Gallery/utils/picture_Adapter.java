package com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Adapter_List;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;

import static androidx.core.view.ViewCompat.setTransitionName;

public class picture_Adapter extends RecyclerView.Adapter<PicHolder> {

    private ArrayList<pictureFacer> pictureList;
    private Context pictureContx;
    private final itemClickListener picListerner;

    public picture_Adapter(ArrayList<pictureFacer> pictureList, Context pictureContx, itemClickListener picListerner) {
        this.pictureList = pictureList;
        this.pictureContx = pictureContx;
        this.picListerner = picListerner;
    }

    @NonNull
    @Override
    public PicHolder onCreateViewHolder(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View cell = inflater.inflate(R.layout.pic_holder_item, container, false);
        return new PicHolder(cell);
    }

    @Override
    public void onBindViewHolder(@NonNull final PicHolder holder, final int position) {


        final pictureFacer image = pictureList.get(position);

        if (image == null) {

            holder.llline.setVisibility(View.VISIBLE);
            holder.ll_ads.setVisibility(View.VISIBLE);
            holder.ll_detail.setVisibility(View.GONE);

            if (Utils.nativehashmap_list != null && Utils.nativehashmap_list.get(position) != null) {
                try {
                    if (Utils.nativehashmap_list.get(position).getParent() != null)
                        ((ViewGroup) Utils.nativehashmap_list.get(position).getParent()).removeView(Utils.nativehashmap_list.get(position));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (Utils.nativehashmap_list.get(position) != null) {
                        holder.llnative.removeAllViews();
                        holder.llline.setVisibility(View.GONE);
                        holder.llnative.setVisibility(View.VISIBLE);
                        Log.e("NativeFull_Show", "admob Native ad show : ");
                        holder.llnative.addView(Utils.nativehashmap_list.get(position));

                    } else {
                        holder.llline.setVisibility(View.GONE);
                        holder.llnative.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Log.d("onBindViewHolder12", "one");
                return;
            }
            Ads_Adapter_List.NativeFull_Show(pictureContx, holder.llnative, holder.llline, position);
            Utils.nativehashmap_list  = Ads_Adapter_List.admob_nativehashmap;
            return;
        }

           /* if (admob_nativehashmap.get(position) == null) {
                if (position == 0) {
                    Ads.Native_adtype(pictureContx, holder.llnative, holder.llline, holder.ll_ads, position, false);
                } else {
                    Ads.Native_adtype(pictureContx, holder.llnative, holder.llline, holder.ll_ads, position, false);
                }

            } else {
                if (admob_nativehashmap.get(position) != null) {
                    if (admob_nativehashmap.get(position).getParent() != null)
                        ((ViewGroup) admob_nativehashmap.get(position).getParent()).removeView(admob_nativehashmap.get(position));
                    holder.llnative.removeAllViews();
                    holder.llline.setVisibility(View.GONE);
                    holder.llnative.setVisibility(View.VISIBLE);
                    holder.llnative.addView(admob_nativehashmap.get(position));
                }
            }*/



        holder.llnative.setVisibility(View.GONE);
        holder.llline.setVisibility(View.GONE);
        holder.ll_ads.setVisibility(View.GONE);
        holder.ll_detail.setVisibility(View.VISIBLE);

        holder.tv_name.setText(image.getPicturName());
            holder.tv_path.setText(image.getPicturePath());
        Glide.with(pictureContx).load(image.getPicturePath()).apply(new RequestOptions().centerCrop()).into(holder.picture);

        setTransitionName(holder.picture, String.valueOf(position) + "_image");

        holder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picListerner.onPicClicked(holder, position, pictureList);
            }
        });

    }



//            holder.ll_detail.setVisibility(View.VISIBLE);
//            holder.lnr_ads_show.setVisibility(View.GONE);
//
//            holder.tv_name.setText(image.getPicturName());
//            holder.tv_path.setText(image.getPicturePath());
//            Glide.with(pictureContx)
//                    .load(image.getPicturePath())
//                    .apply(new RequestOptions().centerCrop())
//                    .into(holder.picture);
//
//            setTransitionName(holder.picture, String.valueOf(position) + "_image");
//
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    picListerner.onPicClicked(holder, position, pictureList);
//                }
//            });
//
////        PushDownAnim.setPushDownAnimTo(holder.picture).setScale(MODE_SCALE, 0.95f);
//
//        } else {
//            holder.ll_detail.setVisibility(View.GONE);
//            holder.lnr_ads_show.setVisibility(View.VISIBLE);
//
//            Ads_Adapter_List.NativeFull_Show(pictureContx, holder.llnative, holder.llline, position);
//        }
//    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("ImageDisplay12", "pos = " + position);
        if (position == 12) {
            return 1;
        } else {
            return 0;
        }
        //return super.getItemViewType(position);
    }

}
