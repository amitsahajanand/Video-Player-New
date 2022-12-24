package com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.BaseActivity;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.MarginDecoration;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.PicHolder;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.imageFolder;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.itemClickListener;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.pictureFacer;
import com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils.pictureFolderAdapter;
import com.videoplayer.fastplayer.gdvideoplayer.ExtraClass.Utils;
import com.videoplayer.fastplayer.gdvideoplayer.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Author CodeBoy722
 * <p>
 * The main Activity start and loads all folders containing images in a RecyclerView
 * this folders are gotten from the MediaStore by the Method getPicturePaths()
 */
public class ImageGallery_Screen extends BaseActivity implements itemClickListener {

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    RecyclerView folderRecycler, folderRecycler_list;
    TextView empty, txt_header, txt_total;
    ImageView img_back;
    ImageView img_camera;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    TextView folder_list;
    ImageView ic_search,close;
    EditText search_folder;
    LinearLayout ll_nodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.image_folder_new_view );


        empty = findViewById( R.id.empty );
        txt_header = findViewById( R.id.txt_header );
        txt_total = findViewById( R.id.txt_total );
        img_back = findViewById( R.id.img_back );
        img_camera = findViewById( R.id.img_camera );
        folderRecycler_list = findViewById( R.id.folderRecycler_list );
        folder_list = findViewById( R.id.folder_list );
        ic_search = findViewById( R.id.ic_search );
        search_folder = findViewById( R.id.search_folder );
        ll_nodata = findViewById( R.id.ll_nodata );
        close = findViewById( R.id.close );

        img_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        } );


        folderRecycler = findViewById( R.id.folderRecycler );
        folderRecycler.addItemDecoration( new MarginDecoration( this ) );
        folderRecycler.hasFixedSize();
        GridLayoutManager layoutManager = new GridLayoutManager( this, 2, LinearLayoutManager.VERTICAL, false );
        folderRecycler.setLayoutManager( layoutManager );
        layoutManager.setSpanSizeLookup( new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 5 == 4 ? 2 : 1;
            }
        } );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false );
        folderRecycler_list.setLayoutManager( linearLayoutManager );

        DataSetImage();

    }

    ArrayList<imageFolder> finalvideoFolderList = new ArrayList<>();

    private void DataSetImage() {

        ArrayList<imageFolder> folds;
        folds = getPicturePaths();

        ic_search.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                OpenSearchDialog( folds );
                search_folder.setVisibility( View.VISIBLE );
                ic_search.setVisibility( View.GONE );
                close.setVisibility( View.VISIBLE );

                search_folder.setFocusable(true);
                search_folder.setFocusableInTouchMode(true);
                search_folder.setEnabled(true);
                search_folder.requestFocus();

                InputMethodManager imm = (InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(search_folder, InputMethodManager.SHOW_FORCED);

            }
        } );

        close.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_folder.setVisibility( View.GONE );
                ic_search.setVisibility( View.VISIBLE );
                close.setVisibility( View.GONE );

                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(search_folder.getWindowToken(), 0);
            }
        } );


        folderRecycler.setVisibility( View.VISIBLE );
        ll_nodata.setVisibility( View.GONE );

        String size = String.valueOf( folds.size() );
        folder_list.setText( size + " Folder" );
        finalvideoFolderList = getImageListForAds( folds );

//            if (folds.isEmpty()) {
//                empty.setVisibility( View.VISIBLE );
//            } else {
        pictureFolderAdapter folderAdapter = new pictureFolderAdapter( finalvideoFolderList, ImageGallery_Screen.this, this, true );
        folderRecycler.setAdapter( folderAdapter );

//        ImageFolderAdapter imageFolderAdapter = new ImageFolderAdapter( finalvideoFolderList, ImageGallery_Screen.this, this, true );
//        folderRecycler_list.setAdapter( imageFolderAdapter );
//            }

        search_folder.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                folderAdapter.getFilter().filter( charSequence );
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() > 0) {
                } else {
                    ll_nodata.setVisibility( View.GONE );
                }

                if (folderAdapter.getItemCount() > 0) {
                    folderRecycler.setVisibility( View.VISIBLE );
                    ll_nodata.setVisibility( View.GONE );
                    folderAdapter.getFilter().filter( editable );

                } else {
                    folderRecycler.setVisibility( View.GONE );
                    ll_nodata.setVisibility( View.VISIBLE );
                }

                if (editable.toString().isEmpty()) {
                    folderRecycler.setVisibility( View.VISIBLE );
                    ll_nodata.setVisibility( View.GONE );
                }

            }
        } );

    }


    private ArrayList<imageFolder> getImageListForAds(ArrayList<imageFolder> videoList) {
        Utils.nativehashmap_list.clear();

        ArrayList<imageFolder> finalvideolist = new ArrayList<>();

        int itemcount = 0;
        for (int i = 0; i < videoList.size(); i++) {
            if (itemcount == 4) {
                itemcount = 1;
                finalvideolist.add( null );
                finalvideolist.add( videoList.get( i ) );

            } else {
                itemcount++;
                finalvideolist.add( videoList.get( i ) );
            }

        }

        return finalvideolist;
    }


//    Dialog searchdialog;
//    //View
//    EditText et_search;
//    ImageView iv_close_search;
//    RecyclerView rv_search_folder;
//    LinearLayout ll_nodata;


    /*private void OpenSearchDialog(ArrayList<imageFolder> videofolderlist_search) {

        if (searchdialog == null) {
            searchdialog = new Dialog( ImageGallery_Screen.this );
            searchdialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
            searchdialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );
            searchdialog.setContentView( R.layout.dialog_search );
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            final int height = ViewGroup.LayoutParams.MATCH_PARENT;
            searchdialog.getWindow().setLayout( width, height );
            searchdialog.setCancelable( false );
            searchdialog.show();
        }

        et_search = searchdialog.findViewById( R.id.et_search );
        iv_close_search = searchdialog.findViewById( R.id.iv_close_search );
        rv_search_folder = searchdialog.findViewById( R.id.rv_search_folder );
        ll_nodata = searchdialog.findViewById( R.id.ll_nodata );


//        rv_search_folder.setLayoutManager(new LinearLayoutManager(VideoFolderListActivity.this));
        rv_search_folder.setLayoutManager( new GridLayoutManager( ImageGallery_Screen.this, 1 ) );
        rv_search_folder.addItemDecoration( new ItemOffsetDecoration( 10 ) );

        if (videofolderlist_search.size() > 0) {
            rv_search_folder.setVisibility( View.VISIBLE );
            ll_nodata.setVisibility( View.GONE );


            pictureFolderAdapter folderAdapter = new pictureFolderAdapter( videofolderlist_search, ImageGallery_Screen.this, ImageGallery_Screen.this, false );
            rv_search_folder.setAdapter( folderAdapter );


            et_search.addTextChangedListener( new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    folderAdapter.getFilter().filter( charSequence );

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.toString().trim().length() > 0) {

                    } else {
                        ll_nodata.setVisibility( View.GONE );

                    }

                    if (folderAdapter.getItemCount() > 0) {


                        rv_search_folder.setVisibility( View.VISIBLE );
                        ll_nodata.setVisibility( View.GONE );
                        folderAdapter.getFilter().filter( editable );

                    } else {

                        rv_search_folder.setVisibility( View.GONE );
                        ll_nodata.setVisibility( View.VISIBLE );
                    }

                    if (editable.toString().isEmpty()) {
                        rv_search_folder.setVisibility( View.VISIBLE );
                        ll_nodata.setVisibility( View.GONE );
                    }

                }
            } );

        } else {
            rv_search_folder.setVisibility( View.GONE );
            ll_nodata.setVisibility( View.VISIBLE );
        }


        iv_close_search.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchdialog.dismiss();
                searchdialog = null;
            }
        } );


        searchdialog.setOnKeyListener( new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    searchdialog.dismiss();
                    searchdialog = null;
                }
                return true;
            }
        } );

    }*/


    /**
     * @return gets all folders with privateiimage on the device and loads each of them in a custom object imageFolder
     * the returns an ArrayList of these custom objects
     */
    private ArrayList<imageFolder> getPicturePaths() {
        ArrayList<imageFolder> picFolders = new ArrayList<>();
        ArrayList<String> picPaths = new ArrayList<>();
        Uri allImagesuri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.ImageColumns.DATA, MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.BUCKET_ID};
        Cursor cursor = getContentResolver().query( allImagesuri, projection, null, null, null );
        try {
            if (cursor != null) {
                cursor.moveToFirst();
            }
            do {
                imageFolder folds = new imageFolder();
                String name = cursor.getString( cursor.getColumnIndexOrThrow( MediaStore.Images.Media.DISPLAY_NAME ) );
                String folder = cursor.getString( cursor.getColumnIndexOrThrow( MediaStore.Images.Media.BUCKET_DISPLAY_NAME ) );
                String datapath = cursor.getString( cursor.getColumnIndexOrThrow( MediaStore.Images.Media.DATA ) );

                //String folderpaths =  datapath.replace(name,"");
                String folderpaths = datapath.substring( 0, datapath.lastIndexOf( folder + "/" ) );
                folderpaths = folderpaths + folder + "/";
                if (!picPaths.contains( folderpaths )) {
                    picPaths.add( folderpaths );

                    folds.setPath( folderpaths );
                    folds.setFolderName( folder );
                    folds.setFirstPic( datapath );
                    folds.setSecPic( "" );
                    folds.setThirdPic( "" );
                    folds.setForthPic( "" );
                    folds.addpics();
                    picFolders.add( folds );
                } else {
                    for (int i = 0; i < picFolders.size(); i++) {
                        if (picFolders.get( i ).getPath().equals( folderpaths )) {

                            picFolders.get( i ).setForthPic( picFolders.get( i ).getThirdPic() );
                            picFolders.get( i ).setThirdPic( picFolders.get( i ).getSecPic() );
                            picFolders.get( i ).setSecPic( picFolders.get( i ).getFirstPic() );
                            picFolders.get( i ).setFirstPic( datapath );

                            picFolders.get( i ).addpics();
                        }
                    }
                }
            } while (cursor.moveToNext());
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < picFolders.size(); i++) {
            Log.d( "picture folders", picFolders.get( i ).getFolderName() + " and path = " + picFolders.get( i ).getPath() + " " + picFolders.get( i ).getNumberOfPics() );
        }


        return picFolders;
    }


    @Override
    public void onPicClicked(PicHolder holder, int position, ArrayList<pictureFacer> pics) {

    }

    /**
     * Each time an item in the RecyclerView is clicked this method from the implementation of the transitListerner
     * in this activity is executed, this is possible because this class is passed as a parameter in the creation
     * of the RecyclerView's Adapter, see the adapter class to understand better what is happening here
     *
     * @param pictureFolderPath a String corresponding to a folder path on the device external storage
     */
    @Override
    public void onPicClicked(String pictureFolderPath, String folderName) {


        Ads_Interstitial.showAds_full( this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                Intent move = new Intent( ImageGallery_Screen.this, ImageDisplay.class );
                move.putExtra( "folderPath", pictureFolderPath );
                move.putExtra( "folderName", folderName );

                //move.putExtra("recyclerItemSize",getCardsOptimalWidth(4));
                startActivity( move );
            }
        } );


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText( this, "camera permission granted", Toast.LENGTH_LONG ).show();
                Intent cameraIntent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
                startActivityForResult( cameraIntent, CAMERA_REQUEST );
            } else {
                Toast.makeText( this, "camera permission denied", Toast.LENGTH_LONG ).show();
            }
        }
    }

    private String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat( "yyyyMMdd_HHmmss" ).format( new Date() );
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM );
        String path = storageDir.getPath() + "/Camera";
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                new File( path )      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get( "data" );
            Toast.makeText( this, "Success", Toast.LENGTH_SHORT ).show();
            //imageView.setImageBitmap(photo);

            // do not forget to close the stream

            try {
             /*   String path = Environment.getExternalStorageDirectory().toString();
                OutputStream fOut = null;
                Integer counter = 0;
                File file = new File(path, "FitnessGirl"+counter+".jpg"); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
                fOut = new FileOutputStream(file);


                photo.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
                fOut.flush(); // Not really required
                fOut.close();*/
                //MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
                //MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
                savebitmap( photo );

                DataSetImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void savebitmap(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES ).getAbsolutePath();

        OutputStream outStream = null;
        // String temp = null;
        //File file = new File(extStorageDirectory, "temp.jpg");
        File file = null;
        try {
            file = createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file.exists()) {
            file.delete();
            //   file = new File(extStorageDirectory, "temp.jpg");
            try {
                file = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {
            outStream = new FileOutputStream( file );
            bmp.compress( Bitmap.CompressFormat.JPEG, 100, outStream );
            outStream.flush();
            outStream.close();
            Toast.makeText( this, "Success", Toast.LENGTH_SHORT ).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Ads_Interstitial.BackshowAds_full( this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                finish();
            }
        } );
    }
}
