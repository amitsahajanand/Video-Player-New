package com.videoplayer.fastplayer.gdvideoplayer.Activity.Adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Adapter_List;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.GetData;
import com.videoplayer.fastplayer.gdvideoplayer.Interface.VideoCountInterface;
import com.videoplayer.fastplayer.gdvideoplayer.Interface.VideoInerface;
import com.videoplayer.fastplayer.gdvideoplayer.Model.VideoModel;
import com.videoplayer.fastplayer.gdvideoplayer.R;

import static com.ads.adsdemosp.AdsClass.Ads_Adapter_List.admob_nativehashmap;


public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> implements Filterable {

    Context context;
    List<VideoModel> mData, filterdata;

    VideoInerface videoInerface;
    VideoCountInterface videoCountInterface;
    private boolean isshow;

    public VideoListAdapter(Context context, List<VideoModel> mData, boolean isshow) {
        this.context = context;
        this.mData = mData;
        this.filterdata = mData;
        this.isshow = isshow;
        videoInerface = (VideoInerface) context;
        videoCountInterface = (VideoCountInterface) context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View cell;
        if (isshow) {
            cell = inflater.inflate( R.layout.adapter_videolist, parent, false );
        } else {
            cell = inflater.inflate( R.layout.grid_list_item, parent, false );
        }
        return new ViewHolder( cell );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (mData.get(position) != null) {

            holder.ll_detail.setVisibility(View.VISIBLE);
            holder.lnr_ads_show.setVisibility(View.GONE);

            holder.tv_name.setText(mData.get(position).getVideoTitle());
            holder.tv_duration.setText(GetData.timeConversion(mData.get(position).getVideoDuration()));
            holder.tv_size.setText("Size: " + mData.get(position).getFilesize() + " (" + mData.get(position).getDateadded() + ")");
            holder.folder_name.setText( mData.get( position ).getFoldername() );

            Glide.with(context).load(mData.get(position).getStr_thumb())
                    .apply(new RequestOptions().error(R.color.graayyy)).placeholder(R.color.graayyy)
                    .into(holder.iv_video);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    videoInerface.videoclick(mData, position);
                }
            });

        } else {

            holder.ll_detail.setVisibility(View.GONE);
            holder.lnr_ads_show.setVisibility(View.VISIBLE);
            if (admob_nativehashmap != null && admob_nativehashmap.get(position) != null) {
                try {
                    if (admob_nativehashmap.get(position).getParent() != null)
                        ((ViewGroup) admob_nativehashmap.get(position).getParent()).removeView(admob_nativehashmap.get(position));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (admob_nativehashmap.get(position) != null) {
                        holder.llnative.removeAllViews();
                        holder.llline.setVisibility(View.GONE);
                        holder.llnative.setVisibility(View.VISIBLE);
                        Log.e("NativeFull_Show", "admob Native ad show : ");
                        holder.llnative.addView(admob_nativehashmap.get(position));

                    } else {
                        holder.llline.setVisibility(View.GONE);
                        holder.llnative.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("onBindViewHolder12", "one");
                return;
            }
            Ads_Adapter_List.NativeFull_Show(context, holder.llnative, holder.llline, position);
            admob_nativehashmap = admob_nativehashmap;

//            holder.ll_detail.setVisibility(View.GONE);
//            holder.lnr_ads_show.setVisibility(View.VISIBLE);
//            Ads_Adapter_List.NativeFull_Show(context, holder.llnative, holder.llline, position);
        }
    }

    private void setPopmenu(ViewHolder holder, int position) {
      /*  holder.ll_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(context, v);

                Menu itemofmenu = popupMenu.getMenu();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.action_share:
                                GetData.shareVideo(context, mData.get(position).getFilepath());
                                break;
                            case R.id.action_delete:
                                ShowConfirmDilaog(position);
                                break;
                        }
                        return false;
                    }
                });

                popupMenu.inflate(R.menu.menu_video);
                popupMenu.show();
            }
        });*/

    }



    public static boolean delete(final Context context, final File file) {
        final String where = MediaStore.MediaColumns.DATA + "=?";
        final String[] selectionArgs = new String[]{
                file.getAbsolutePath()
        };
        final ContentResolver contentResolver = context.getContentResolver();
        final Uri filesUri = MediaStore.Files.getContentUri("external");

        contentResolver.delete(filesUri, where, selectionArgs);

        if (file.exists()) {

            contentResolver.delete(filesUri, where, selectionArgs);
        }
        return !file.exists();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_video;
        TextView tv_duration, tv_name, tv_size;

        LinearLayout ll_detail;
        LinearLayout lnr_ads_show, llline, llnative;
        TextView folder_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_video = itemView.findViewById(R.id.iv_video);
            tv_duration = itemView.findViewById(R.id.tv_duration);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_size = itemView.findViewById(R.id.tv_size);
            folder_name = itemView.findViewById(R.id.folder_name);

            ll_detail = itemView.findViewById(R.id.ll_detail);

            llline = itemView.findViewById(R.id.llline);
            llnative = itemView.findViewById(R.id.llnative);
            lnr_ads_show = itemView.findViewById(R.id.lnr_ads_show);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mData = filterdata;
                } else {
                    List<VideoModel> filteredList = new ArrayList<>();
                    for (VideoModel row : filterdata) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getVideoTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    mData = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                try {
                    mData = (ArrayList<VideoModel>) filterResults.values;
                    if (mData.size() <= 0) {
//                        Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
                    }
                    notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

}
