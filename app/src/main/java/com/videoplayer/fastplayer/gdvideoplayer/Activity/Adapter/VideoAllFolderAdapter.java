package com.videoplayer.fastplayer.gdvideoplayer.Activity.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Adapter_List;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.videoplayer.fastplayer.gdvideoplayer.Interface.FolderItemInterface;
import com.videoplayer.fastplayer.gdvideoplayer.Model.VideoFolder;
import com.videoplayer.fastplayer.gdvideoplayer.R;

import java.util.List;

import static com.ads.adsdemosp.AdsClass.Ads_Adapter_List.admob_nativehashmap;


public class VideoAllFolderAdapter extends RecyclerView.Adapter<VideoAllFolderAdapter.ViewHolder> {

    Context context;
    List<VideoFolder> mData;

    FolderItemInterface folderItemInterface;

    public VideoAllFolderAdapter(Context context, List<VideoFolder> mData) {
        this.context = context;
        this.mData = mData;
        folderItemInterface = (FolderItemInterface) context;

        if (Ads_Adapter_List.admob_nativehashmap != null) {
            Ads_Adapter_List.admob_nativehashmap.clear();
        }

    }

    public void UpdateData(List<VideoFolder> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from( context ).inflate( R.layout.adapter_allvideofolder, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mData.get( position ) != null) {


            holder.ll_detail.setVisibility( View.VISIBLE );
            holder.lnr_ads_show.setVisibility( View.GONE );
            Glide.with( context ).load( mData.get( position ).getVideoList().get( 0 ).getFilepath() ).transition( DrawableTransitionOptions.withCrossFade() ).into( holder.iv_photo2 );

            holder.tv_foldername2.setText( mData.get( position ).getFoldername() );
            holder.tv_count2.setText( mData.get( position ).getVideoList().size() + " Videos" );

            holder.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    folderItemInterface.folderclick( mData, position );
                }
            } );

        } else {

            holder.ll_detail.setVisibility( View.GONE );
            holder.lnr_ads_show.setVisibility( View.VISIBLE );
            if (admob_nativehashmap != null && admob_nativehashmap.get( position ) != null) {
                try {
                    if (admob_nativehashmap.get( position ).getParent() != null)
                        ((ViewGroup) admob_nativehashmap.get( position ).getParent()).removeView( admob_nativehashmap.get( position ) );
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (admob_nativehashmap.get( position ) != null) {
                        holder.llnative.removeAllViews();
                        holder.llline.setVisibility( View.GONE );
                        holder.llnative.setVisibility( View.VISIBLE );
                        Log.e( "NativeFull_Show", "admob Native ad show : " );
                        holder.llnative.addView( admob_nativehashmap.get( position ) );

                    } else {
                        holder.llline.setVisibility( View.GONE );
                        holder.llnative.setVisibility( View.GONE );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d( "onBindViewHolder12", "one" );
                return;
            }
            Ads_Adapter_List.NativeFull_Show( context, holder.llnative, holder.llline, position );
            admob_nativehashmap = admob_nativehashmap;

        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_foldername, tv_no_video;
        LinearLayout ll_detail;
        ImageView iv_photo2;
        TextView tv_foldername2, tv_count2;
        Button btn_open;

        LinearLayout lnr_ads_show, llline, llnative;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            tv_foldername = itemView.findViewById( R.id.tv_foldername );
            tv_no_video = itemView.findViewById( R.id.tv_no_video );
            iv_photo2 = itemView.findViewById( R.id.iv_photo2 );
            tv_foldername2 = itemView.findViewById( R.id.tv_foldername2 );
            tv_count2 = itemView.findViewById( R.id.tv_count2 );
            btn_open = itemView.findViewById( R.id.btn_open );

            ll_detail = itemView.findViewById( R.id.ll_detail );

            llline = itemView.findViewById( R.id.llline );
            llnative = itemView.findViewById( R.id.llnative );
            lnr_ads_show = itemView.findViewById( R.id.lnr_ads_show );

        }
    }

}
