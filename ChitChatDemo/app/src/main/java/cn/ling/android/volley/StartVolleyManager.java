package cn.ling.android.volley;

import android.app.ActivityManager;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import cn.ling.android.ApplicationInfo;
import cn.ling.android.volley.basehelper.BitmapLruCache;
import cn.ling.android.volley.basehelper.PatientVolleyBuilder;
import cn.ling.android.utils.Util;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class StartVolleyManager {

    private static RequestQueue sRequestQueue;
    private static ImageLoader sImageLoader;

    private StartVolleyManager() {
        //no instance
    }


    public static void init() {
        PatientVolleyBuilder builder = new PatientVolleyBuilder();
        builder.setAppContext(ApplicationInfo.getApplicatonContext()).setUserAgent(
                Util.getUserAgent());
        sRequestQueue = builder.build();

        final int memClass = ((ActivityManager) ApplicationInfo.getApplicatonContext().
                getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = 1024 * 1024 * memClass / 8;
        sImageLoader = new ImageLoader(sRequestQueue, new BitmapLruCache(
                cacheSize));
    }

    public static RequestQueue getRequestQueue() {
        if (sRequestQueue != null) {
            return sRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    /**
     * Returns instance of ImageLoader initialized with {@see FakeImageCache}
     * which effectively means that no memory caching is used. This is useful
     * for images that you know that will be show only once.
     *
     * @return
     */
    public static ImageLoader getImageLoader() {
        if (sImageLoader != null) {
            return sImageLoader;
        } else {
            throw new IllegalStateException("ImageLoader not initialized");
        }
    }
}
