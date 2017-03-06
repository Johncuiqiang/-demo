package cn.ling.voice.customui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.ling.android.utils.ToastHelper;
import cn.ling.voice.R;


/**
 * Created by David小硕 on 2016/9/28.
 */

public class CommonToast {

    public static Toast makeText(Context context,String text,int duration){
        Toast  toast = ToastHelper.getInstance().obtainToast();
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflate.inflate(R.layout.vw_common_toast, null);
        TextView content = (TextView) view.findViewById(R.id.tvToastContent);
        content.setText(text);
        toast.setView(view);
        toast.setDuration(duration);
        return toast;
    }

}
