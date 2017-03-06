package cn.ling.voice.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cn.ling.voice.VoiceApplication;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "startwork.db";

    public DatabaseOpenHelper() {
        super(VoiceApplication.getAppContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        UserInfoDao.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        UserInfoDao.onUpgrade(db,oldVersion,newVersion);
    }


}
