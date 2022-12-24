package com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

import com.videoplayer.fastplayer.gdvideoplayer.Activity.Whatsapp.FulllWhatsappScreen;
import com.videoplayer.fastplayer.gdvideoplayer.R;

public class FullImageGalleryAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<pictureFacer> imageList;
    private LayoutInflater inflater;

    FulllWhatsappScreen fulllWhatsappScreen;

    public FullImageGalleryAdapter(Context context, ArrayList<pictureFacer> imageList) {
        this.context = context;
        this.imageList = imageList;

        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NotNull Object object) {
        container.removeView((View) object);
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup view, int position) {

        if (imageList.get(position) == null) {
            return null;
        }
        View imageLayout;

        imageLayout = inflater.inflate(R.layout.slidingimages_layout_wt, view, false);


        assert imageLayout != null;
        final ImageView imageView = imageLayout.findViewById(R.id.im_fullViewImage_wt);



        Glide.with(context).load(imageList.get(position).getPicturePath()).into(imageView);
        view.addView(imageLayout, 0);









        return imageLayout;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, @NotNull Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}

