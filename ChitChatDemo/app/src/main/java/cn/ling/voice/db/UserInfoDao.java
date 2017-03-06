package cn.ling.voice.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import cn.ling.voice.entities.UserInfoEntity;


/**
 * Created by David小硕 on 2016/9/28.
 */

public class UserInfoDao implements IDao<UserInfoEntity> {

    private static final String TABLE_NAME = "UserInfo";

    private static final String[] RETURN_COLUMNS_NEWS = new String[] {
            UserInfoColumns.USER_ID, UserInfoColumns.PWD, UserInfoColumns.USER_TYPE, UserInfoColumns.PERSON_NAME
            , UserInfoColumns.PHONE_NUMBER, UserInfoColumns.AGE, UserInfoColumns.NOW_CITY, UserInfoColumns.NOW_AREA
            , UserInfoColumns.NOW_ADDRESS, UserInfoColumns.ID_CARD, UserInfoColumns.OLD_PROVINCE,
            UserInfoColumns.OLD_CITY
            , UserInfoColumns.WORK_TYPE_ID, UserInfoColumns.WORK_TYPE, UserInfoColumns.WORK_TIME,
            UserInfoColumns.WORK_PRICE
            , UserInfoColumns.WORK_NUMBER, UserInfoColumns.USER_LEVEL, UserInfoColumns.LATITUDE,
            UserInfoColumns.LONGITUDE,
            UserInfoColumns.IMAGE, UserInfoColumns.QUOTA
    };

    private SQLiteDatabase mDatabase;

    public UserInfoDao(SQLiteDatabase database) {
        this.mDatabase = database;
    }

    private static class UserInfoColumns implements BaseColumns {

        public static final String USER_ID = "userId";

        public static final String PWD = "pwd";

        public static final String USER_TYPE = "userTYPE";

        public static final String PERSON_NAME = "personName";

        public static final String PHONE_NUMBER = "phoneNumber";

        public static final String AGE = "age";

        public static final String NOW_CITY = "nowCity";

        public static final String NOW_AREA = "nowArea";

        public static final String NOW_ADDRESS = "nowAddress";

        public static final String ID_CARD = "idCard";

        public static final String OLD_PROVINCE = "oldProvince";

        public static final String OLD_CITY = "oldCity";

        public static final String WORK_TYPE_ID = "workTypeId";

        public static final String WORK_TYPE = "workType";

        public static final String WORK_TIME = "workTime";

        public static final String WORK_PRICE = "workPrice";

        public static final String WORK_NUMBER = "workNumber";

        public static final String USER_LEVEL = "userLevel";

        public static final String LATITUDE = "latitude";

        public static final String LONGITUDE = "longitude";

        public static final String IMAGE = "image";

        public static final String QUOTA = "quota";

    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("Create TABLE ").append(TABLE_NAME).append("(")
                .append(UserInfoColumns.USER_ID).append(" TEXT, ")
                .append(UserInfoColumns.PWD).append(" TEXT, ")
                .append(UserInfoColumns.USER_TYPE).append(" INTEGER, ")
                .append(UserInfoColumns.PERSON_NAME).append(" TEXT, ")
                .append(UserInfoColumns.PHONE_NUMBER).append(" TEXT, ")
                .append(UserInfoColumns.AGE).append(" INTEGER, ")
                .append(UserInfoColumns.NOW_CITY).append(" TEXT, ")
                .append(UserInfoColumns.NOW_AREA).append(" TEXT, ")
                .append(UserInfoColumns.NOW_ADDRESS).append(" TEXT, ")
                .append(UserInfoColumns.ID_CARD).append(" TEXT, ")
                .append(UserInfoColumns.OLD_PROVINCE).append(" TEXT, ")
                .append(UserInfoColumns.OLD_CITY).append(" TEXT, ")
                .append(UserInfoColumns.WORK_TYPE_ID).append(" TEXT, ")
                .append(UserInfoColumns.WORK_TYPE).append(" TEXT, ")
                .append(UserInfoColumns.WORK_TIME).append(" LONG, ")
                .append(UserInfoColumns.WORK_PRICE).append(" FLOAT, ")
                .append(UserInfoColumns.WORK_NUMBER).append(" TEXT, ")
                .append(UserInfoColumns.USER_LEVEL).append(" INTEGER, ")
                .append(UserInfoColumns.LATITUDE).append(" TEXT, ")
                .append(UserInfoColumns.LONGITUDE).append(" TEXT, ")
                .append(UserInfoColumns.QUOTA).append(" INTEGER,")
                .append(UserInfoColumns.IMAGE).append(" TEXT ").append(")");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion,
                                 int newVersion) {

    }

    @Override
    public void insert(UserInfoEntity userInfo) {
        if (userInfo == null) {
            return;
        }
        ContentValues values = createContentValues(userInfo);
        mDatabase.insert(TABLE_NAME, null, values);
    }

    @Override
    public void update(UserInfoEntity userInfo) {
        if (userInfo == null) {
            return;
        }
        if (exists(userInfo)) {
            ContentValues values = createContentValues(userInfo);
            mDatabase.update(TABLE_NAME, values, UserInfoColumns.USER_ID + " = ?", new String[] {userInfo.getUserId()});
        } else {
            insert(userInfo);
        }
    }

    @Override
    public void delete(UserInfoEntity userInfo) {
        if (userInfo == null) {
            return;
        }
        delete(userInfo.getUserId());
    }

    @Override
    public void delete(String userId) {
        mDatabase.delete(TABLE_NAME, UserInfoColumns.USER_ID + " = ?", new String[] {userId});
    }

    @Override
    public UserInfoEntity get(String userId) {
        UserInfoEntity userInfo = null;
        Cursor cursor = mDatabase.query(TABLE_NAME, RETURN_COLUMNS_NEWS, UserInfoColumns.USER_ID + " = ?",
                new String[] {userId}, null, null, null);
        if (null != cursor) {
            while (cursor.moveToNext()) {
                userInfo = buildFromCursor(cursor);
            }
            cursor.close();
        }
        return userInfo;
    }

    @Override
    public List<UserInfoEntity> getAll() {
        List<UserInfoEntity> userInfos = new ArrayList<UserInfoEntity>();
        Cursor cursor = mDatabase.query(TABLE_NAME, RETURN_COLUMNS_NEWS, null, null, null, null, null);
        if (null != cursor) {
            while (cursor.moveToNext()) {
                userInfos.add(buildFromCursor(cursor));
            }
            cursor.close();
        }
        return userInfos;
    }

    @Override
    public boolean exists(UserInfoEntity userInfo) {
        return exists(userInfo.getUserId());
    }

    @Override
    public boolean exists(String userId) {
        boolean isExists = false;
        Cursor cursor = mDatabase.query(TABLE_NAME, RETURN_COLUMNS_NEWS, UserInfoColumns.USER_ID + " = ?",
                new String[] {userId}, null, null, null);
        if (null != cursor) {
            if (cursor.moveToNext()) {
                isExists = true;
            }
            cursor.close();
        }
        return isExists;
    }

    @Override
    public ContentValues createContentValues(UserInfoEntity userInfo) {
        ContentValues values = new ContentValues();
        values.put(UserInfoColumns.USER_ID, userInfo.getUserId());
        values.put(UserInfoColumns.PWD, userInfo.getPwd());
        if(!TextUtils.isEmpty(userInfo.getUserType())){
            values.put(UserInfoColumns.USER_TYPE, Integer.valueOf(userInfo.getUserType()));
        }
        values.put(UserInfoColumns.PERSON_NAME, userInfo.getPersonName());
        values.put(UserInfoColumns.PHONE_NUMBER, userInfo.getPhoneNumber());
        values.put(UserInfoColumns.AGE, userInfo.getAge());
        values.put(UserInfoColumns.NOW_CITY, userInfo.getNowCity());
        values.put(UserInfoColumns.NOW_AREA, userInfo.getNowArea());
        values.put(UserInfoColumns.NOW_ADDRESS, userInfo.getNowAddress());
        values.put(UserInfoColumns.ID_CARD, userInfo.getIdCard());
        values.put(UserInfoColumns.OLD_PROVINCE, userInfo.getOldProvince());
        values.put(UserInfoColumns.OLD_CITY, userInfo.getOldCity());
        values.put(UserInfoColumns.WORK_TYPE_ID, userInfo.getWorkTypeId());
        values.put(UserInfoColumns.WORK_TYPE, userInfo.getWorkType());
        values.put(UserInfoColumns.WORK_TIME, userInfo.getWorkTime());
        values.put(UserInfoColumns.WORK_PRICE, (float)userInfo.getWorkPrice());
        values.put(UserInfoColumns.WORK_NUMBER, userInfo.getWorkNumber());
        values.put(UserInfoColumns.USER_LEVEL, userInfo.getUserLevel());
        values.put(UserInfoColumns.LATITUDE, userInfo.getLat());
        values.put(UserInfoColumns.LONGITUDE, userInfo.getLng());
        values.put(UserInfoColumns.IMAGE, userInfo.getImage());
        values.put(UserInfoColumns.QUOTA,userInfo.getQuota());
        return values;
    }

    @Override
    public UserInfoEntity buildFromCursor(Cursor cursor) {
        UserInfoEntity userInfo = new UserInfoEntity();

        int index = -1;
        index = cursor.getColumnIndex(UserInfoColumns.USER_ID);
        if (-1 != index) {
            userInfo.setUserId(cursor.getString(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.PWD);
        if (-1 != index) {
            userInfo.setPwd(cursor.getString(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.USER_TYPE);
        if (-1 != index) {
            userInfo.setUserType(String.valueOf(cursor.getInt(index)));
        }
        index = cursor.getColumnIndex(UserInfoColumns.PERSON_NAME);
        if (-1 != index) {
            userInfo.setPersonName(cursor.getString(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.PHONE_NUMBER);
        if (-1 != index) {
            userInfo.setPhoneNumber(cursor.getString(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.AGE);
        if (-1 != index) {
            userInfo.setAge(cursor.getInt(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.NOW_CITY);
        if (-1 != index) {
            userInfo.setNowCity(cursor.getString(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.NOW_AREA);
        if (-1 != index) {
            userInfo.setNowArea(cursor.getString(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.NOW_ADDRESS);
        if (-1 != index) {
            userInfo.setNowAddress(cursor.getString(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.ID_CARD);
        if (-1 != index) {
            userInfo.setIdCard(cursor.getString(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.OLD_PROVINCE);
        if (-1 != index) {
            userInfo.setOldProvince(cursor.getString(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.OLD_CITY);
        if (-1 != index) {
            userInfo.setOldCity(cursor.getString(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.WORK_TYPE_ID);
        if (-1 != index) {
            userInfo.setWorkTypeId(cursor.getString(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.WORK_TYPE);
        if (-1 != index) {
            userInfo.setWorkType(cursor.getString(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.WORK_TIME);
        if (-1 != index) {
            userInfo.setWorkTime(cursor.getLong(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.WORK_PRICE);
        if (-1 != index) {
            userInfo.setWorkPrice(cursor.getFloat(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.USER_LEVEL);
        if (-1 != index) {
            userInfo.setUserLevel(cursor.getInt(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.LATITUDE);
        if (-1 != index) {
            userInfo.setLat(cursor.getString(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.LONGITUDE);
        if (-1 != index) {
            userInfo.setLng(cursor.getString(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.IMAGE);
        if (-1 != index) {
            userInfo.setImage(cursor.getString(index));
        }
        index = cursor.getColumnIndex(UserInfoColumns.QUOTA);
        if(-1!=index){
            userInfo.setQuota(cursor.getInt(index));
        }
        return userInfo;
    }

}
