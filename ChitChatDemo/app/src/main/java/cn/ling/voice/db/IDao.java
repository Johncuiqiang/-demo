package cn.ling.voice.db;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

/**
 * Created by David小硕 on 2016/9/28.
 */

public interface IDao<T> {

    void insert(T t);

    void update(T t);

    void delete(T t);

    void delete(String id);

    T get(String id);

    List<T> getAll();

    boolean exists(T t);

    boolean exists(String id);

    ContentValues createContentValues(T t);

    T buildFromCursor(Cursor cursor);

}
