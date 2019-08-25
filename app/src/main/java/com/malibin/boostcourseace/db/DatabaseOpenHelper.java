package com.malibin.boostcourseace.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created By Yun Hyeok
 * on 8월 24, 2019
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String CREATE_MOVIE_LIST =
            "CREATE TABLE IF NOT EXISTS movieList" +
                    " (id INTEGER PRIMARY KEY, image_url TEXT, title TEXT, title_eng TEXT," +
                    " reservation_rank INTEGER, reservation_rate FLOAT, movie_rate INTEGER, opening_day TEXT )";

    private static final String CREATE_MOVIE =
            "CREATE TABLE IF NOT EXISTS movie (id INTEGER PRIMARY KEY, image_url TEXT, title TEXT, movie_rate INTEGER, opening_day TEXT, genre TEXT, show_time INTEGER," +
                    " like_count INTEGER, dislike_count INTEGER, reservation_rank INTEGER, reservation_rate FLOAT, star_rate FLOAT, accumulated_attendance FLOAT, plot TEXT" +
                    ", director TEXT, actress TEXT, photo_links TEXT, video_links TEXT, out_links TEXT)";

    private static final String CREATE_REVIEW =
            "CREATE TABLE IF NOT EXISTS review (review_id INTEGER PRIMARY KEY, movie_id INTEGER, profile TEXT, nickname TEXT, time TEXT, " +
                    "star_rate FLOAT, content TEXT, recommend_count INTEGER)";

    private static DatabaseOpenHelper INSTANCE = null;

    private DatabaseOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d("Malibin Debug", "DatabaseOpenHelper 생성자호출됨 ");
    }

    public static DatabaseOpenHelper getInstance(Context context, String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseOpenHelper(context, name, factory, version);
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Malibin Debug", "DatabaseOpenHelper onCreate 호출됨 ");
        db.execSQL(CREATE_MOVIE_LIST);
        db.execSQL(CREATE_MOVIE);
        db.execSQL(CREATE_REVIEW);
        Log.d("Malibin Debug", "DatabaseOpenHelper onCreate 끝 ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DELETE FROM movieList");
        db.execSQL("DELETE FROM movie");
        db.execSQL("DELETE FROM review");
    }
}
