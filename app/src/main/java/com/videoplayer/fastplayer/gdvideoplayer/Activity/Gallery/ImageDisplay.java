package com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.PicHolder;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.itemClickListener;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.pictureFacer;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.picture_Adapter;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;

import static com.ads.adsdemosp.AdsClass.Ads_Adapter_List.admob_nativehashmap;

/**
 * Author CodeBoy722
 * <p>
 * This Activity get a path to a folder that contains images from the MainActivity Intent and displays
 * all the images in the folder inside a RecyclerView
 */


public class ImageDisplay extends BaseActivity implements itemClickListener {

    RecyclerView imageRecycler;
    ArrayList<pictureFacer> allpictures;
    ArrayList<pictureFacer> fullallpictures = new ArrayList<>();
    ProgressBar load;
    String foldePath;
    TextView folderName;
    ImageView img_back;
    //    LinearLayout lnr_back;
    picture_Adapter picture_adapter;
    String currentPhotoPath = "";

    LayoutAnimationController animation;
    TextView txt_total;
    ImageView ic_more;

    public static int spanCount = 2;
    public static GridLayoutManager gridLayoutManager;


    Integer[] seriesOfNumbers = {6, 12, 18, 24,};
    int poss = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_display_2);
        img_back = findViewById(R.id.img_back);
        folderName = findViewById(R.id.foldername);
        txt_total = findViewById(R.id.txt_total);
        ic_more = findViewById(R.id.ic_more);
        int resId = R.anim.layout_animation_from_bottom;
        animation = AnimationUtils.loadLayoutAnimation(ImageDisplay.this, resId);


        folderName.setText(getIntent().getStringExtra("folderName"));
        foldePath = getIntent().getStringExtra("folderPath");

        allpictures = new ArrayList<>();
        imageRecycler = findViewById(R.id.recycler);

        gridLayoutManager = new GridLayoutManager(ImageDisplay.this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 7 == 6 ? 3 : 1;
            }
        });

        load = findViewById(R.id.loader);


        ic_more.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (allpictures.isEmpty()) {
            load.setVisibility(View.VISIBLE);
            if (admob_nativehashmap != null) {
                admob_nativehashmap.clear();
            }
            allpictures = getAllImagesByFolder(foldePath);
            pics_image = allpictures;
            ArrayList<pictureFacer> finalvideoFolderList = getFolderlistForAds(allpictures);
            Utils.select_image_pos = allpictures.size();
            picture_adapter = new picture_Adapter(finalvideoFolderList, ImageDisplay.this, this);

            imageRecycler.setLayoutManager(gridLayoutManager);
            imageRecycler.hasFixedSize();

            imageRecycler.setAdapter(picture_adapter);
            imageRecycler.setLayoutAnimation(animation);
            load.setVisibility(View.GONE);

        } else {

        }
    }

    private ArrayList<pictureFacer> getFolderlistForAds(List<pictureFacer> videoFolderList) {
        admob_nativehashmap.clear();


        ArrayList<pictureFacer> finalfoldervideolist = new ArrayList<>();
//        finalfoldervideolist.add(null);
        int itemcount = 0;
        for (int i = 0; i < videoFolderList.size(); i++) {
            if (itemcount == 6) {
                itemcount = 1;
                finalfoldervideolist.add(null);
                finalfoldervideolist.add(videoFolderList.get(i));

            } else {
                itemcount++;
                finalfoldervideolist.add(videoFolderList.get(i));
            }

        }

        return finalfoldervideolist;
    }


    public static ArrayList<pictureFacer> pics_image = new ArrayList<>();


    @Override
    public void onPicClicked(PicHolder holder, int position, ArrayList<pictureFacer> pics) {
        pics_image = pics;
        int pos = 0;

        if (fullallpictures.contains(pics.get(position))) {
            pos = fullallpictures.indexOf(pics.get(position));
        }


        int finalPos = pos;
        Ads_Interstitial.showAds_full(ImageDisplay.this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {

                Intent inNext = new Intent(ImageDisplay.this, FulllImageShowScreen.class);
                Utils.pictureFacerlist = pics;
                Utils.position = position;
                startActivity(inNext);

            }
        });

    }

    @Override
    public void onPicClicked(String pictureFolderPath, String folderName) {



    }


    /**
     * This Method gets all the images in the folder paths passed as a String to the method and returns
     * and ArrayList of pictureFacer a custom object that holds data of a given image
     *
     * @param path a String corresponding to a folder path on the device external storage
     */

    int ads_show = 0;
    ArrayList<pictureFacer> getAllpictures = new ArrayList<>();

    public ArrayList<pictureFacer> getAllImagesByFolder(String path) {
        if (getAllpictures != null) {
            getAllpictures.clear();
        }
        ads_show = 0;
        ArrayList<pictureFacer> images = new ArrayList<>();
        Uri allVideosuri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE};
        Cursor cursor = ImageDisplay.this.getContentResolver().query(allVideosuri, projection, MediaStore.Images.Media.DATA + " like ? ", new String[]{"%" + path + "%"}, null);
        try {
            cursor.moveToFirst();
            do {
                pictureFacer pic = new pictureFacer();

                pic.setPicturName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)));

                pic.setPicturePath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));

                pic.setPictureSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)));

                images.add(pic);
            } while (cursor.moveToNext());
            cursor.close();
            ArrayList<pictureFacer> reSelection = new ArrayList<>();
            for (int i = images.size() - 1; i > -1; i--) {
                reSelection.add(images.get(i));
            }
            images = reSelection;
        } catch (Exception e) {
            e.printStackTrace();
        }
      /*  for (int i = 0; i < images.size(); i++) {
            if (ads_show == 6) {
                ads_show = 1;
                 getAllpictures.add(null);

            }
            ads_show++;
            getAllpictures.add(images.get(i));
        }*/

        return images;
    }

    public ArrayList<pictureFacer> getAllVideosByFolder(String path) {
        ArrayList<pictureFacer> images = new ArrayList<>();
        Uri allVideosuri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Video.VideoColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Video.Media.SIZE};
        Cursor cursor = ImageDisplay.this.getContentResolver().query(allVideosuri, projection, MediaStore.Video.Media.DATA + " like ? ", new String[]{"%" + path + "%"}, null);
        try {
            cursor.moveToFirst();
            do {
                pictureFacer pic = new pictureFacer();

                pic.setPicturName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)));

                pic.setPicturePath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));

                pic.setPictureSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)));

                images.add(pic);
            } while (cursor.moveToNext());
            cursor.close();
            ArrayList<pictureFacer> reSelection = new ArrayList<>();
            for (int i = images.size() - 1; i > -1; i--) {
                reSelection.add(images.get(i));
            }
            images = reSelection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }

    @Override
    public void onBackPressed() {

        Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                Utils.select_image_pos = 0;
                currentPhotoPath = "";
                finish();
            }
        });

    }
}
