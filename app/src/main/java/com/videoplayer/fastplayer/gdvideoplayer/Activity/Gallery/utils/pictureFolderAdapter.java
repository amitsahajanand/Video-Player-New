package com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.videoplayer.fastplayer.gdvideoplayer.R;

import java.util.ArrayList;

import static com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils.nativehashmap_list;

public class pictureFolderAdapter extends RecyclerView.Adapter<pictureFolderAdapter.FolderHolder> implements Filterable {

    private ArrayList<imageFolder> folders = new ArrayList<>();
    private Context folderContx;
    private itemClickListener listenToClick;
    private boolean isshow;
    ArrayList<imageFolder> filterdata;

    public pictureFolderAdapter(ArrayList<imageFolder> folders, Context folderContx, itemClickListener listen, boolean isshow) {
        this.folders = folders;
        this.folderContx = folderContx;
        this.listenToClick = listen;
        this.isshow = isshow;
        // this.mData = folders;
        this.filterdata = folders;
        if (isshow) {
            if (Ads_Adapter_List.admob_nativehashmap != null) {
                Ads_Adapter_List.admob_nativehashmap.clear();
            }
        }

    }

    @NonNull
    @Override
    public FolderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from( parent.getContext() );
        View cell;
        if (isshow) {
            cell = inflater.inflate( R.layout.picture_folder_item_new, parent, false );
        } else {
            cell = inflater.inflate( R.layout.adapter_videofolder, parent, false );
        }
        return new FolderHolder( cell );

    }


    @Override
    public void onBindViewHolder(@NonNull FolderHolder holder, int position) {

        if (isshow) {
            if (folders.get( position ) == null) {

                holder.llline.setVisibility( View.VISIBLE );
                holder.ll_ads.setVisibility( View.VISIBLE );
                holder.lnr_main.setVisibility( View.GONE );

                if (nativehashmap_list != null && nativehashmap_list.get( position ) != null) {
                    try {
                        if (nativehashmap_list.get( position ).getParent() != null)
                            ((ViewGroup) nativehashmap_list.get( position ).getParent()).removeView( nativehashmap_list.get( position ) );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        if (nativehashmap_list.get( position ) != null) {
                            holder.llnative.removeAllViews();
                            holder.llline.setVisibility( View.GONE );
                            holder.llnative.setVisibility( View.VISIBLE );
                            Log.e( "NativeFull_Show", "admob Native ad show : " );
                            holder.llnative.addView( nativehashmap_list.get( position ) );

                        } else {
                            holder.llline.setVisibility( View.GONE );
                            holder.llnative.setVisibility( View.GONE );
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // Log.d("onBindViewHolder12", "one");
                    return;
                }
                Ads_Adapter_List.NativeFull_Show( folderContx, holder.llnative, holder.llline, position );
                nativehashmap_list = Ads_Adapter_List.admob_nativehashmap;
                return;
            }
        }

        holder.llnative.setVisibility( View.GONE );
        holder.llline.setVisibility( View.GONE );
        holder.ll_ads.setVisibility( View.GONE );
        holder.lnr_main.setVisibility( View.VISIBLE );

        final imageFolder folder = folders.get( position );

        if (isshow) {
            Glide.with( folderContx ).load( folder.getFirstPic() ).apply( new RequestOptions().centerCrop() ).into( holder.image_1 );
            Glide.with( folderContx ).load( folder.getSecPic() ).apply( new RequestOptions().centerCrop() ).into( holder.image_2 );
            Glide.with( folderContx ).load( folder.getThirdPic() ).apply( new RequestOptions().centerCrop() ).into( holder.image_3 );
            Glide.with( folderContx ).load( folder.getForthPic() ).apply( new RequestOptions().centerCrop() ).into( holder.image_4 );
        } else {
            Glide.with( folderContx ).load( folder.getFirstPic() ).apply( new RequestOptions().centerCrop() ).into( holder.iv_photo2 );
        }

        String text = folder.getFolderName();
        holder.folderName.setText( text );
        holder.txt_total.setText( String.valueOf( folder.getNumberOfPics() ) + " images" );


        holder.lnr_main.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenToClick.onPicClicked( folder.getPath(), folder.getFolderName() );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return folders.size();
    }


    public class FolderHolder extends RecyclerView.ViewHolder {
        ImageView iv_photo2;
        TextView folderName, txt_total;
        ImageView image_1, image_2, image_3, image_4;
        LinearLayout lnr_main;
        LinearLayout llline, llnative, ll_ads;

        public FolderHolder(@NonNull View itemView) {
            super( itemView );
            iv_photo2 = itemView.findViewById( R.id.iv_photo2 );
            folderName = itemView.findViewById( R.id.tv_foldername2 );
            txt_total = itemView.findViewById( R.id.tv_count2 );
            image_1 = itemView.findViewById( R.id.image_1 );
            image_2 = itemView.findViewById( R.id.image_2 );
            image_3 = itemView.findViewById( R.id.image_3 );
            image_4 = itemView.findViewById( R.id.image_4 );
            lnr_main = itemView.findViewById( R.id.ll_detail );

            ll_ads = (LinearLayout) itemView.findViewById( R.id.lnr_ads_show );
            llline = (LinearLayout) itemView.findViewById( R.id.llline );
            llnative = (LinearLayout) itemView.findViewById( R.id.llnative );
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    folders = filterdata;
                } else {
                    ArrayList<imageFolder> filteredList = new ArrayList<>();
                    for (imageFolder row : filterdata) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row != null) {
                            if (row.getFolderName().toLowerCase().contains( charString.toLowerCase() )) {
                                filteredList.add( row );
                            }
                        }
                    }
                    folders = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = folders;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                try {
                    folders = (ArrayList<imageFolder>) filterResults.values;
                    if (folders.size() <= 0) {
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
