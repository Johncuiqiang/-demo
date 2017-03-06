package cn.ling.android.network;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class PostParamter implements Serializable, Comparator<PostParamter> {

    private static final String JPEG = "image/jpeg";
    private static final String GIF = "image/gif";
    private static final String PNG = "image/png";
    private static final String OCTET = "application/octet-stream";

    private String name;
    private String value;
    private InputStream input;

    public PostParamter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public PostParamter(String name, double value) {
        this.name = name;
        this.value = String.valueOf(value);
    }

    public PostParamter(String name, int value) {
        this.name = name;
        this.value = String.valueOf(value);
    }

    public PostParamter(String name, InputStream input) {
        this.name = name;
        if (input instanceof BufferedInputStream) {
            this.input = input;
        } else {
            this.input = new BufferedInputStream(input);
        }
    }

    public PostParamter(String name, File file) throws FileNotFoundException {
        this(name, new BufferedInputStream(new FileInputStream(file)));
    }


    public PostParamter setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public PostParamter setValue(String value) {
        this.value = value;
        return this;
    }

    public String getValue() {
        return this.value;
    }


    public InputStream getInput() {
        return this.input;
    }

    public boolean isFile() {
        return this.input != null;
    }

    public static String getFileContentType(String fileName) {
        String fileType = "";
        if (fileName.isEmpty()) {
            return fileType;
        }
        String extensions = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if ("gif".equals(extensions)) {
            fileType = GIF;
        } else if ("png".equals(extensions)) {
            fileType = PNG;
        } else if ("jpg".equals(extensions) || "jpeg".equals(extensions)) {
            fileType = JPEG;
        } else {
            fileType = OCTET;
        }
        return fileType;
    }

    public static boolean containsFile(PostParamter[] params) {
        boolean isContains = false;
        if (params == null) {
            return isContains;
        }
        for (int i = 0; i < params.length; i++) {
            PostParamter param = params[i];
            if (param.isFile()) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }

    public static boolean containsFile(Collection<PostParamter> params) {
        boolean isContians = false;
        for (PostParamter param : params) {
            if (param.isFile()) {
                isContians = true;
                break;
            }
        }
        return isContians;
    }


    public static PostParamter[] getParameterArray(String name, String value) {
        return new PostParamter[]{new PostParamter(name, value)};
    }

    public static PostParamter[] getParameterArray(String name, int value) {
        return getParameterArray(name, String.valueOf(value));
    }


    public static PostParamter[] getParameterArray(String name1, String value1, String name2, String value2) {
        return new PostParamter[]{new PostParamter(name1, value1),
                new PostParamter(name2, value2)};
    }

    public static PostParamter[] getParameterArray(String name1, int value1, String name2, int value2) {
        return getParameterArray(name1, String.valueOf(value1), name2,
                String.valueOf(value2));
    }


    @Override
    public int compare(PostParamter lhs, PostParamter rhs) {
        return 0;
    }

    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + (input != null ? input.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "PostParamter{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", input=" + input +
                '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if ((obj instanceof PostParamter)) {
            PostParamter that = (PostParamter) obj;

            if (input != null ? !input.equals(input) : input != null) {
            }
            return (name.equals(name)) && (value.equals(value));
        }
        return false;
    }

    public int compareTo(PostParamter that) {
        int compared = name.compareTo(name);
        if (compared == 0) {
            compared = value.compareTo(value);
        }
        return compared;
    }


    public static String encodeParameters(List<PostParamter> httpParams) {
        if (null == httpParams) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < httpParams.size(); i++) {
            PostParamter postParamter = httpParams.get(i);
            if (postParamter.isFile()) {
                throw new IllegalArgumentException("parameter [" + postParamter.name + "] should be text");
            }
            if (i != 0) {
                sb.append("&");
            }
            try {
                sb.append(URLEncoder.encode(postParamter.name, "UTF-8")).append("=").append(URLEncoder.encode(postParamter.value, "UTF-8"));
            } catch (UnsupportedEncodingException localUnsupportedEncodingException) {

            }
        }
        return sb.toString();
    }


}
