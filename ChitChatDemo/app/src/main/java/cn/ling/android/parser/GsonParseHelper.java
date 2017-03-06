package cn.ling.android.parser;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class GsonParseHelper {

    private static Gson INSTANCE = new Gson();


    public static <T> T safeParse(String strDataJson, Class<T> classOfT){
        T data = null;
        if (strDataJson != null&&!strDataJson.equals("")) {
            try {
                data = INSTANCE.fromJson(strDataJson, classOfT);
            }catch (JsonSyntaxException e){
                System.out.println(e.toString());
            }catch (Exception localException) {
                localException.printStackTrace();
            }
            if(null == data){
                try {
                    data = classOfT.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }else{
            try {
                data = classOfT.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static <T> T safeParse(byte[] dataJson, Class<T> classOfT) {
        T data = null;

        String strDataJson = null;
        try {
            strDataJson = new String(dataJson, "utf-8");
            data = parse(strDataJson, classOfT);
        }catch (UnsupportedEncodingException unsupportedEncodingException){
            unsupportedEncodingException.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return data;
    }



    public static <T> T safeParse(byte[] dataJson, Class<T> classOfT, String charsetName){
        T data = null;
        String strDataJson = null;
        try {
            strDataJson = new String(dataJson, charsetName);
            data = parse(strDataJson, classOfT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static <T> T parse(String strDataJson, Class<T> classOfT)
            throws JsonSyntaxException, IllegalAccessException, InstantiationException {
        if(strDataJson.isEmpty()) {
            return classOfT.newInstance();
        }
        return (T)INSTANCE.fromJson(strDataJson, classOfT);
    }
    public static <T> T parse(byte[] dataJson, Class<T> classOfT)
            throws UnsupportedEncodingException, JsonSyntaxException, InstantiationException, IllegalAccessException {
        return (T)parse(new String(dataJson, "utf-8"), classOfT);
    }

    public static <T> T parse(byte[] dataJson, Class<T> classOfT, String charsetName)
            throws UnsupportedEncodingException, JsonSyntaxException, InstantiationException, IllegalAccessException {
        return (T)parse(new String(dataJson, charsetName), classOfT);
    }
}
