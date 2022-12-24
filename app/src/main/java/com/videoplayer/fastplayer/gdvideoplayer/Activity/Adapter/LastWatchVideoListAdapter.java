package com.videoplayer.fastplayer.gdvideoplayer.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.videoplayer.fastplayer.gdvideoplayer.Interface.FolderLastItemInterface;
import com.videoplayer.fastplayer.gdvideoplayer.Model.VideoModel;
import com.videoplayer.fastplayer.gdvideoplayer.R;

import java.util.List;


public class LastWatchVideoListAdapter extends RecyclerView.Adapter<LastWatchVideoListAdapter.ViewHolder> {

    Context context;
    List<VideoModel> mData;

    FolderLastItemInterface folderLastItemInterface;

    public LastWatchVideoListAdapter(Context context, List<VideoModel> mData) {
        this.context = context;
        this.mData = mData;
        folderLastItemInterface = (FolderLastItemInterface) context;

    }

    public void UpdateData(List<VideoModel> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from( context ).inflate( R.layout.adapter_videofolder_last, parent, false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Glide.with( context ).load( mData.get( position ).getStr_thumb() ).apply( new RequestOptions().error( R.color.graayyy ) ).placeholder( R.color.graayyy ).into( holder.videoImage );

        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                folderLastItemInterface.lastclick(mData, position);
            }
        } );

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView videoImage;
//        LinearLayout lnr_ads_show, llline, llnative;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );


            videoImage = itemView.findViewById( R.id.videoImage );


//            llline = itemView.findViewById(R.id.llline);
//            llnative = itemView.findViewById(R.id.llnative);
//            lnr_ads_show = itemView.findViewById(R.id.lnr_ads_show);

        }
    }

}
