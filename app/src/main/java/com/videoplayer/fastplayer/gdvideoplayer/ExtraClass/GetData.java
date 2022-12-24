package com.videoplayer.fastplayer.gdvideoplayer.ExtraClass;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.widget.Toast;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.videoplayer.fastplayer.gdvideoplayer.Model.VideoFolder;
import com.videoplayer.fastplayer.gdvideoplayer.Model.VideoModel;
import com.videoplayer.fastplayer.gdvideoplayer.R;

public class GetData {


      public static String foldername = "foldername";
    public static String videolist = "videolist";
    public static String songlist = "songlist";
    public static String position = "position";
    public static String data = "data";
//    public static File RootDirectoryWhatsappShow = new File(Environment.getExternalStorageDirectory() + "/Download/HDVideoplayer/WAstatus");
    public static boolean isDelete = false;

    public static List<VideoFolder> getVideoFolderList(Activity activity) {
        List<VideoFolder> videoFolderList = new ArrayList<>();

        List<VideoModel> videoList = new ArrayList<>();
        videoList = getVideoList(activity);

        for (int i = 0; i < videoList.size(); i++) {

            VideoFolder videoFolder = new VideoFolder(videoList.get(i).getFolderid(), "", -1, "", "", new ArrayList<>());

            if (videoFolderList.contains(videoFolder)) {
                videoFolderList.get(videoFolderList.indexOf(videoFolder)).getVideoList().add(videoList.get(i));
                videoFolderList.get(videoFolderList.indexOf(videoFolder)).setTotalvideo(videoFolderList.get(videoFolderList.indexOf(videoFolder)).getVideoList().size());

            } else {
                List<VideoModel> videoList1 = new ArrayList<>();
                videoList1.add(videoList.get(i));
                if (videoList.get(i).getFoldername() == null) {
                    videoList.get(i).setFoldername("Unknow Folder");
                }
                VideoFolder videoFolder1 = new VideoFolder(videoList.get(i).getFolderid(), videoList.get(i).getFoldername(), 1, videoList.get(i).getStr_path(), videoList.get(i).getStr_thumb(), videoList1);
                videoFolderList.add(videoFolder1);
            }
        }

        return videoFolderList;
    }

    @SuppressLint("Range")
    public static List<VideoModel> getVideoList(Activity activity) {
        List<VideoModel> videoList = new ArrayList<>();

        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name, column_id, thum, duration, folderid, title, filepath;

        String absolutePathOfImage = null;
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Video.Media.BUCKET_ID,
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.RESOLUTION,
                MediaStore.Video.Media.DATE_TAKEN,
                MediaStore.Video.Thumbnails.DATA};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = activity.getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
        folderid = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID);
        column_id = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
        thum = cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA);
        title = cursor.getColumnIndex(MediaStore.Video.Media.TITLE);
        duration = cursor.getColumnIndex(MediaStore.Video.Media.DURATION);
        filepath = cursor.getColumnIndex(MediaStore.Video.Media.DATA);


        if (cursor != null && cursor.moveToFirst()) {

            do {

                int videoid = cursor.getInt(column_id);
                String videotitle = cursor.getString(title);
                int videiduration = cursor.getInt(duration);
                String data = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                String foldername = cursor.getString(column_index_folder_name);
                int folder_id = cursor.getInt(folderid);
                String thumnail = cursor.getString(thum);
                absolutePathOfImage = cursor.getString(column_index_data);
                String filepath_ = cursor.getString(filepath);
                String Resolution = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.RESOLUTION));
                String date = null;
                try {
                    date = convertDate(cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATE_TAKEN)), "dd/MM/yyyy");
                } catch (Exception e) {
                    e.printStackTrace();
                    date = "0 Mb";
                }

                File file = new File(filepath_);
                long fileSize = 0;
                if (file.exists()) {
                    fileSize = file.length();
                }

                VideoModel video = new VideoModel(videoid, videotitle, videiduration, data, foldername,
                        folder_id, absolutePathOfImage, thumnail, filepath_, Resolution, date, getStringSizeLengthFile(fileSize));

                videoList.add(video);

            } while (cursor.moveToNext());

        }
        return videoList;
    }
    @SuppressLint("Range")
    public static List<VideoModel> getwhatsappdownload(Activity activity) {
        List<VideoModel> videoList = new ArrayList<>();

        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name, column_id, thum, duration, folderid, title, filepath;

        String absolutePathOfImage = null;
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Video.Media.BUCKET_ID,
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.RESOLUTION,
                MediaStore.Video.Media.DATE_TAKEN,
                MediaStore.Video.Thumbnails.DATA};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        String selection = MediaStore.Video.Media.DATA + " like?";
        cursor = activity.getContentResolver().query(uri, projection, selection, new String[]{"%WAstatus%"}, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
        folderid = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID);
        column_id = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
        thum = cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA);
        title = cursor.getColumnIndex(MediaStore.Video.Media.TITLE);
        duration = cursor.getColumnIndex(MediaStore.Video.Media.DURATION);
        filepath = cursor.getColumnIndex(MediaStore.Video.Media.DATA);


        if (cursor != null && cursor.moveToFirst()) {

            do {

                int videoid = cursor.getInt(column_id);
                String videotitle = cursor.getString(title);
                int videiduration = cursor.getInt(duration);
                 String data = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                String foldername = cursor.getString(column_index_folder_name);
                int folder_id = cursor.getInt(folderid);
                String thumnail = cursor.getString(thum);
                absolutePathOfImage = cursor.getString(column_index_data);
                String filepath_ = cursor.getString(filepath);
                String Resolution = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.RESOLUTION));
                String date = null;
                try {
                    date = convertDate(cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATE_TAKEN)), "dd/MM/yyyy");
                } catch (Exception e) {
                    e.printStackTrace();
                    date = "0 Mb";
                }

                File file = new File(filepath_);
                long fileSize = 0;
                if (file.exists()) {
                    fileSize = file.length();
                }

                VideoModel video = new VideoModel(videoid, videotitle, videiduration, data, foldername,
                        folder_id, absolutePathOfImage, thumnail, filepath_, Resolution, date, getStringSizeLengthFile(fileSize));

                videoList.add(video);

            } while (cursor.moveToNext());

        }
        return videoList;
    }

    public static String convertDate(String dateInMilliseconds, String dateFormat) {
        return DateFormat.format(dateFormat, Long.parseLong(dateInMilliseconds)).toString();
    }

    public static String timeConversion(int value) {
        String videoTime;
        int dur = (int) value;
        int hrs = (dur / 3600000);
        int mns = (dur / 60000) % 60000;
        int scs = dur % 60000 / 1000;

        if (hrs > 0) {
            videoTime = String.format("%02d:%02d:%02d", hrs, mns, scs);
        } else {
            videoTime = String.format("%02d:%02d", mns, scs);
        }
        return videoTime;
    }

    public static String getFileExtension(String fileName) {
        String fileNameArray[] = fileName.split("\\.");
        return fileNameArray[fileNameArray.length - 1];


    }

    public static String getStringSizeLengthFile(long size) {

        DecimalFormat df = new DecimalFormat("0.00");

        float sizeKb = 1024.0f;
        float sizeMb = sizeKb * sizeKb;
        float sizeGb = sizeMb * sizeKb;
        float sizeTerra = sizeGb * sizeKb;


        if (size < sizeMb)
            return df.format(size / sizeKb) + " Kb";
        else if (size < sizeGb)
            return df.format(size / sizeMb) + " Mb";
        else if (size < sizeTerra)
            return df.format(size / sizeGb) + " Gb";

        return "";
    }

    public static void shareVideo(Context context2, String str) {
        Uri parse = Uri.parse(str);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("simplevideoshow/mp4");
        intent.putExtra("android.intent.extra.SUBJECT", context2.getString(R.string.app_name));
        intent.putExtra("android.intent.extra.TEXT", context2.getString(R.string.share_app_message) + ("\nhttps://play.google.com/store/apps/details?id=" + context2.getPackageName()));
        intent.putExtra("android.intent.extra.STREAM", parse);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            context2.startActivity(Intent.createChooser(intent, "Share Video using"));
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(context2, R.string.no_app_installed, Toast.LENGTH_LONG).show();
        }
    }




   /* public static void createFileFolder() {

        if (!RootDirectoryWhatsappShow.exists()) {
            RootDirectoryWhatsappShow.mkdirs();
        }

    }*/

    public static void shareImageAndVideo(Context context2, String str, boolean z) {
        Uri parse = Uri.parse(str);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setPackage("com.whatsapp");
        intent.putExtra("android.intent.extra.TEXT", context2.getString(R.string.share_app_message) + ("\nhttps://play.google.com/store/apps/details?id=" + context2.getPackageName()));
        intent.putExtra("android.intent.extra.STREAM", parse);
        if (z) {
            intent.setType("simplevideoshow/*");
        } else {
            intent.setType("image/*");
        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            context2.startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(context2, "Whatsapp Not Installed", Toast.LENGTH_SHORT).show();
//            setToast(context2, context2.getResources().getString(R.string.whatsapp_not_installed));
        }
    }

    public static void shareImage(Context context2, String str) {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.SUBJECT", context2.getString(R.string.app_name));
            intent.putExtra("android.intent.extra.TEXT", context2.getString(R.string.share_app_message) + ("\nhttps://play.google.com/store/apps/details?id=" + context2.getPackageName()));
            intent.putExtra("android.intent.extra.STREAM", Uri.parse(MediaStore.Images.Media.insertImage(context2.getContentResolver(), str, "", (String) null)));
            intent.setType("image/*");
            context2.startActivity(Intent.createChooser(intent, context2.getResources().getString(R.string.share_image_via)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    public static void OpenQureka(Activity activity) {

        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            Bundle bundle = new Bundle();
            bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
            intent.putExtras(bundle);
            intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", activity.getResources().getColor(R.color.colorPrimary));
            intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
            intent.setPackage("com.android.chrome");
            intent.setData(Uri.parse(Preference.getlink()));
            activity.startActivity(intent, (Bundle) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
