package cn.ling.voice;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iflytek.cloud.SpeechError;

import cn.ling.voice.chitchat.ChitChatManager;
import cn.ling.voice.chitchat.asr.ASRConstants;
import cn.ling.voice.chitchat.asr.ASRManager;
import cn.ling.voice.chitchat.asr.ASRTool;
import cn.ling.voice.chitchat.nlu.INLUNetRequest;
import cn.ling.voice.chitchat.nlu.NLUNetRequest;
import cn.ling.voice.chitchat.nlu.NLUResultEntity;
import cn.ling.voice.chitchat.tts.TTSConstants;
import cn.ling.voice.chitchat.tts.TTSManager;
import cn.ling.voice.chitchat.tts.TTSTool;

/**
 * Created by cuiqiang on 2016/12/20.
 * MainActivity使用示例类
 * <p>
 * initView() 初始化view
 * initData() 初始化逻辑
 * onDestroy() 释放资源
 * inputListener 语音输入点击事件
 * outputListener 语音输出点击事件
 * nluNetRequestListener nlu网络请求点击事件
 * chitChatListener 闲聊点击事件
 */
public class MainActivity extends AppCompatActivity {

    private Button mInput;
    private Button mOutput;
    private Button mBtnNLU;

    private TextView mText;
    private Context mContext;
    private Button mBtnChat;
    private NLUNetRequest mNLUNetRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mInput = (Button) findViewById(R.id.voice_input);
        mOutput = (Button) findViewById(R.id.voice_output);
        mBtnNLU = (Button) findViewById(R.id.btn_nlu);
        mBtnChat = (Button) findViewById(R.id.btn_chat);
        mText = (TextView) findViewById(R.id.tv_output);
    }

    private void initData() {
        mContext = this;
        mNLUNetRequest = new NLUNetRequest(mContext);
        ASRManager.getInstance().init(mContext, ASRConstants.ASR_TYPE_CLOUD);
        TTSManager.getInstance().init(mContext, TTSConstants.TTS_TYPE_LOCAL);
        ChitChatManager.getInstance().init(mContext);
        mInput.setOnClickListener(new inputListener());
        mOutput.setOnClickListener(new outputListener());
        mBtnNLU.setOnClickListener(new nluNetRequestListener());
        mBtnChat.setOnClickListener(new chitChatListener());
    }

    //语音听写
    private class inputListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ASRManager.getInstance().startRecognizer(new ASRTool.ASRCallBackListener(){
                @Override
                public void onResult(String text) {
                    mText.setText(text);
                }
            });
        }
    }

    //语音输出
    private class outputListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String text = mText.getText().toString().trim();
            if (text != null) {
                TTSManager.getInstance().startSpeeh(text, new TTSTool.TTSCallBackListener(){
                    @Override
                    public void onCompleted(SpeechError error) {

                    }

                });
            }
        }
    }

    //语音听写
    private class nluNetRequestListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String text = mText.getText().toString().trim();
            mNLUNetRequest.startNLUNetRequest(text, new INLUNetRequest.NLUNetRequestCallBack() {
                @Override
                public void success(NLUResultEntity resultEntity,String result) {
                    String answer = resultEntity.getAnswer();
                    mText.setText(answer);
                }

                @Override
                public void error(Exception exception) {

                }
            });

        }
    }

    //闲聊
    private class chitChatListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ChitChatManager.getInstance().startChat();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ASRManager.getInstance().onDestroy();
        TTSManager.getInstance().onDestroy();
        ChitChatManager.getInstance().stopChat();
    }
}
