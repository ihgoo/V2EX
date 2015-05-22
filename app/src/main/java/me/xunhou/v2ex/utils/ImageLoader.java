package me.xunhou.v2ex.utils;

import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by ihgoo on 2015/5/19.
 */
public class ImageLoader {
    private volatile static ImageLoader instance;

    public static ImageLoader getInstance() {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null) {
                    instance = new ImageLoader();
                }
            }
        }
        return instance;
    }

    protected ImageLoader() {
    }

    public void displayImage(String string, SimpleDraweeView simpleDraweeView) {

        if (null==string){
            return;
        }
        if (!string.startsWith("http:")) {
            string = "http:" + string;
        }
        Uri uri = Uri.parse(string);
        simpleDraweeView.setImageURI(uri);
    }

}
