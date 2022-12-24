package com.videoplayer.fastplayer.gdvideoplayer.Activity.Instagram.ModelInsta;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("shortcode_media")
@Expose
private ShortcodeMedia shortcodeMedia;

public ShortcodeMedia getShortcodeMedia() {
return shortcodeMedia;
}

public void setShortcodeMedia(ShortcodeMedia shortcodeMedia) {
this.shortcodeMedia = shortcodeMedia;
}

}