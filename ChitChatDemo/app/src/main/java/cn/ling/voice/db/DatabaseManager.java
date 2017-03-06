package cn.ling.voice.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class DatabaseManager {

    private static DatabaseManager INSTANCE;

    private DatabaseOpenHelper mHelper;
    private SQLiteDatabase mDatabase;
    private UserInfoDao mUserInfoDao;

    private DatabaseManager() {
        mHelper = new DatabaseOpenHelper();
        mDatabase = mHelper.getWritableDatabase();
        mUserInfoDao = new UserInfoDao(mDatabase);
    }


    public static DatabaseManager getInstance() {
        if (null == INSTANCE) {
            synchronized (DatabaseManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new DatabaseManager();
                }
            }
        }
        return INSTANCE;
    }

    public UserInfoDao getUserInfoDao() {
        return this.mUserInfoDao;
    }


}
