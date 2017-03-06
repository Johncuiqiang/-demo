package cn.ling.android.volley;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.lang.ref.WeakReference;

import cn.ling.android.entities.BaseEntity;
import cn.ling.android.entities.ThirdPluginBaseDataEntity;
import cn.ling.android.volley.basehelper.BasePostBodyHelper;

/**
 * Created by David小硕 on 2016/9/29.
 */

public class VolleyRequestUtil {

    /**
     * 添加一个get请求到volley中
     *
     * @param url
     * @param clazz
     * @param listener
     */
    public static <T extends BaseEntity> void addGetGsonRequestForSerialBaseData(
            String url, Class<T> clazz, final Listener<T> listener) {
        addGsonRequestForSerialBaseData(Request.Method.GET, url, clazz, listener);
    }


    public static <T extends  ThirdPluginBaseDataEntity> void add3dPluginGetGsonRequestForSeialBaseData(
            String url, Class<T> clazz, final Listener<T> listener){

        add3dGsonRequestForSerialBaseData(Request.Method.GET, url, clazz, listener);
    }

    /**
     * 添加一个post请求道volley中
     *
     * @param url
     * @param clazz
     * @param listener
     */
    public static <T extends BaseEntity> void addPostGsonRequestForSerialBaseData(
            PostJsonBodyHelper bodyHelper, String url, Class<T> clazz,
            Listener<T> listener) {
        final ListenerWrapper<T> wrapper = new ListenerWrapper<T>(listener,
                false);
        StartVolleyManager.getRequestQueue().add(
                new DefaultPostGsonRequestForSerialBaseData<T>(url, clazz,
                        new Response.Listener<T>() {

                            @Override
                            public void onResponse(T paramT) {
                                final Listener<T> listener = wrapper
                                        .getListener();
                                if (null != listener) {
                                    listener.onResponse(paramT);
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(
                            VolleyError paramVolleyError) {
                        final Listener<T> listener = wrapper
                                .getListener();
                        if (null != listener) {
                            listener.onErrorResponse(paramVolleyError);
                        }
                    }
                }).setBodyHelper(bodyHelper));
    }

    public static <T extends BaseEntity> void addPostStringRequestForSerialBaseData(
            PostStringBodyHelper bodyHelper,
            String url, Class<T> clazz,
            Listener<T> listener){
        final ListenerWrapper<T> wrapper = new ListenerWrapper<T>(listener,
                false);
        StartVolleyManager.getRequestQueue().add(
                new DefaultPostStringRequestForSerialBaseData<T>(url, clazz,
                        new Response.Listener<T>() {

                            @Override
                            public void onResponse(T paramT) {
                                final Listener<T> listener = wrapper
                                        .getListener();
                                if (null != listener) {
                                    listener.onResponse(paramT);
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(
                            VolleyError paramVolleyError) {
                        final Listener<T> listener = wrapper
                                .getListener();
                        if (null != listener) {
                            listener.onErrorResponse(paramVolleyError);
                        }
                    }
                }).setBodyHelper(bodyHelper));

    }


    public static <T extends ThirdPluginBaseDataEntity> void add3dPluginPostGsonRequestForSerialBaseData(
            PostJsonBodyHelper bodyHelper, String url, Class<T> clazz,
            Listener<T> listener){
        final ListenerWrapper<T> wrapper = new ListenerWrapper<T>(listener,
                false);
        StartVolleyManager.getRequestQueue().add(new Default3dPluginPostGsonReqForSerailBaseData<T>(url, clazz
                , new Response.Listener<T>() {
            @Override
            public void onResponse(T paramT) {
                final Listener<T> listener = wrapper
                        .getListener();
                if (null != listener) {
                    listener.onResponse(paramT);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                final Listener<T> listener = wrapper
                        .getListener();
                if (null != listener) {
                    listener.onErrorResponse(volleyError);
                }
            }
        }).setBodyHelper(bodyHelper));
    }

    public static <T extends ThirdPluginBaseDataEntity> void add3dPluginPostGsonRequestForSerialBaseData(
            PostStringBodyHelper bodyHelper, String url, Class<T> clazz,
            Listener<T> listener){
        final ListenerWrapper<T> wrapper = new ListenerWrapper<T>(listener,
                false);
        StartVolleyManager.getRequestQueue().add(new Default3dPluginStringPostReqForSerialBaseData<T>(url, clazz, new Response.Listener<T>() {
            @Override
            public void onResponse(T paramT) {
                final Listener<T> listener = wrapper
                        .getListener();
                if (null != listener) {
                    listener.onResponse(paramT);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                final Listener<T> listener = wrapper
                        .getListener();
                if (null != listener) {
                    listener.onErrorResponse(volleyError);
                }
            }
        }).setBodyHelper(bodyHelper));
    }


    /**
     * 添加一个post请求道volley中
     *
     * @param url
     * @param clazz
     * @param listener
     */
    public static <T extends BaseEntity> void addPostGsonRequestForSerialBaseData(
            BasePostBodyHelper bodyHelper, String url, Class<T> clazz,
            final Listener<T> listener) {
        addGsonRequestForSerialBaseData(bodyHelper, Request.Method.POST, url, clazz,
                listener);
    }

    private static <T extends BaseEntity> void addGsonRequestForSerialBaseData(
            int method, String url, Class<T> clazz, Listener<T> listener) {
        final ListenerWrapper<T> wrapper = new ListenerWrapper<T>(listener,
                false);
        StartVolleyManager.getRequestQueue().add(
                new GsonRequestForSerialBaseData<T>(method, url, clazz,
                        new Response.Listener<T>() {

                            @Override
                            public void onResponse(T paramT) {
                                final Listener<T> listener = wrapper
                                        .getListener();
                                if (null != listener) {
                                    listener.onResponse(paramT);
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(
                            VolleyError paramVolleyError) {
                        final Listener<T> listener = wrapper
                                .getListener();
                        if (null != listener) {
                            listener.onErrorResponse(paramVolleyError);
                        }
                    }
                }));
        // 目前服务器还没支持
        // .setGetBodyHelper((BaseGetBodyHelper) new DefaultGetBodyHelper()
        // .addHeader(ConstantHttpProtocal.ACCEPT_ENCODING ,
        // ConstantHttpProtocal.GZIP)));

    }

    private static <T extends BaseEntity> void addGsonRequestForSerialBaseData(
            BasePostBodyHelper bodyHelper, int method, String url,
            Class<T> clazz, Listener<T> listener) {
        final ListenerWrapper<T> wrapper = new ListenerWrapper<T>(listener,
                false);
        StartVolleyManager.getRequestQueue().add(
                new GsonRequestForSerialBaseData<T>(method, url, clazz,
                        new Response.Listener<T>() {

                            @Override
                            public void onResponse(T paramT) {
                                final Listener<T> listener = wrapper
                                        .getListener();
                                if (null != listener) {
                                    listener.onResponse(paramT);
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(
                            VolleyError paramVolleyError) {
                        final Listener<T> listener = wrapper
                                .getListener();
                        if (null != listener) {
                            listener.onErrorResponse(paramVolleyError);
                        }
                    }
                }).setPostBodyHelper(bodyHelper));
    }

    private static <T extends ThirdPluginBaseDataEntity> void add3dGsonRequestForSerialBaseData(
            int method, String url, Class<T> clazz, Listener<T> listener){
        final ListenerWrapper<T> wrapper = new ListenerWrapper<T>(listener,
                false);
        StartVolleyManager.getRequestQueue().add(new ThirdPluginGsonRequestForSerialBaseData<T>(method, url, clazz, new Response.Listener<T>() {
            @Override
            public void onResponse(T paramT) {
                final Listener<T> listener = wrapper
                        .getListener();
                if (null != listener) {
                    listener.onResponse(paramT);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                final Listener<T> listener = wrapper
                        .getListener();
                if (null != listener) {
                    listener.onErrorResponse(volleyError);
                }
            }
        }));
    }

    public static interface Listener<T> {

        void onResponse(T paramT);

        void onErrorResponse(VolleyError paramVolleyError);
    }

    private static class ListenerWrapper<T> {

        private WeakReference<Listener<T>> mWeakReference;
        private Listener<T> mListener;
        private boolean mIsWeak;

        public ListenerWrapper(Listener<T> listener, boolean isWeak) {
            mIsWeak = isWeak;
            if (mIsWeak) {
                mWeakReference = new WeakReference<Listener<T>>(listener);
            } else {
                mListener = listener;
            }
        }

        public Listener<T> getListener() {
            if (mIsWeak) {
                return mWeakReference.get();
            } else {
                return mListener;
            }
        }
    }

}
