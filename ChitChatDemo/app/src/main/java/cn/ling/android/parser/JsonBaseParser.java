package cn.ling.android.parser;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by David小硕 on 2016/9/28.
 */

public abstract class JsonBaseParser implements IParser {

    @Override
    public Object parser(InputStream paramInputStream) {

        try {
            if (paramInputStream == null) {
                return null;
            }
            String json = inputStream2String(paramInputStream);

            return parseData(json);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (paramInputStream != null) {
                    paramInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


    public static String inputStream2String(InputStream is) throws IOException {

        if (is == null) {
            return "";
        }

        int size = 128;
        byte[] buffer = new byte[2048];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int cnt = 0;
        while ((cnt = is.read(buffer)) > -1) {
            baos.write(buffer, 0, cnt);
        }
        baos.flush();
        is.close();
        baos.close();
        return baos.toString();
    }

    public abstract Object parseData(String paramString)
            throws JSONException, InstantiationException, IllegalAccessException;
}
