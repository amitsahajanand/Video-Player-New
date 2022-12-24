package com.videoplayer.fastplayer.gdvideoplayer.Activity.Gallery.utils;

/**
 * author CodeBoy722
 *
 * Custom Class that holds information of a folder containing images
 * on the device external storage, used to populate our RecyclerView of
 * picture folders
 */
public class imageFolder {

    private String path;
    private String FolderName;
    private int numberOfPics = 0;
    private String firstPic = "";
    private String secPic = "";
    private String thirdPic = "";
    private String forthPic = "";

    public imageFolder() {

    }

    public imageFolder(String path, String folderName) {
        this.path = path;
        FolderName = folderName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String folderName) {
        FolderName = folderName;
    }

    public int getNumberOfPics() {
        return numberOfPics;
    }

    public void setNumberOfPics(int numberOfPics) {
        this.numberOfPics = numberOfPics;
    }

    public void addpics() {
        this.numberOfPics++;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }

    public String getSecPic() {
        return secPic;
    }

    public void setSecPic(String secPic) {
        this.secPic = secPic;
    }

    public String getThirdPic() {
        return thirdPic;
    }

    public void setThirdPic(String thirdPic) {
        this.thirdPic = thirdPic;
    }

    public String getForthPic() {
        return forthPic;
    }

    public void setForthPic(String forthPic) {
        this.forthPic = forthPic;
    }
}
