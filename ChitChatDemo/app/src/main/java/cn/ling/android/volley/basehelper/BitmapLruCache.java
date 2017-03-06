package cn.ling.android.volley.basehelper;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class BitmapLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

    public BitmapLruCache(int maxSize){
        super(maxSize);
    }

    protected  int sizeOf(String key,Bitmap value){
        return  value.getRowBytes()*value.getHeight();
    }

    @Override
    public Bitmap getBitmap(String url) {

        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url,bitmap);
    }
}
