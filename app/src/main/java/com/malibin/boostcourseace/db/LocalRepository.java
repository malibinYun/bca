package com.malibin.boostcourseace.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.malibin.boostcourseace.ui.movie.MovieShortInfo;

import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 25, 2019
 */

public class LocalRepository {

    private static LocalRepository INSTANCE = null;
    private static SQLiteDatabase database = null;

    private ConvertCursorFactory convertCursorFactory;

    private LocalRepository() {
        convertCursorFactory = new ConvertCursorFactory();
    }

    public static LocalRepository getInstance(DatabaseOpenHelper helper) {
        if (INSTANCE == null) {
            database = helper.getWritableDatabase();
            INSTANCE = new LocalRepository();
        }
        return INSTANCE;
    }

    public void saveMovieList(List<MovieShortInfo> movieShortInfoList) {
        String sql = "INSERT INTO movieList VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        for (MovieShortInfo movie : movieShortInfoList) {
            database.execSQL(sql, movie.toSqlArgs());
        }
    }

    public List<MovieShortInfo> getMovieList() {
        String sql = "SELECT * FROM movieList";
        Cursor cursor = database.rawQuery(sql, null);
        List<MovieShortInfo> result = convertCursorFactory.toMovieList(cursor);
        cursor.close();
        return result;
    }
}
