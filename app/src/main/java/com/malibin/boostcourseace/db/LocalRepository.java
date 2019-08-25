package com.malibin.boostcourseace.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.malibin.boostcourseace.ui.movie.Movie;
import com.malibin.boostcourseace.ui.movie.MovieShortInfo;
import com.malibin.boostcourseace.ui.review.MovieReview;

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

    public void deleteMovieList() {
        String sql = "DELETE FROM movieList";
        database.execSQL(sql);
    }

    public List<MovieShortInfo> getMovieList() {
        String sql = "SELECT * FROM movieList ORDER BY id";
        Cursor cursor = database.rawQuery(sql, null);
        List<MovieShortInfo> result = convertCursorFactory.toMovieList(cursor);
        cursor.close();
        return result;
    }

    public void saveMovieDetail(Movie movie) {
        String sql = "INSERT INTO movie VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            database.execSQL(sql, movie.toSqlArgs());
        } catch (Exception e) {
            Log.d("Malibin Debug", "중복된 movie insert 안됨. movieId : " + movie.getId());
        }
    }

    public Movie getMovieDetail(int movieId) {
        String sql = "SELECT * FROM movie WHERE id = " + movieId;
        Cursor cursor = database.rawQuery(sql, null);
        Movie movie = convertCursorFactory.toMovie(cursor);
        cursor.close();
        return movie;
    }

    public void saveReviews(int movieId, List<MovieReview> reviews) {
        String sql = "INSERT INTO review VALUES(" + movieId + ",?,?,?,?,?,?,?)";
        for (MovieReview review : reviews) {
            try {
                database.execSQL(sql, review.toSqlArgs());
            } catch (Exception e) {
                Log.d("Malibin Debug", "중복된 review insert 안됨. reviewId : " + review.getReviewId());
            }
        }
    }

    public List<MovieReview> getReviews(int movieId) {
        String sql = "SELECT * FROM review WHERE movie_id = " + movieId + " ORDER BY review_id DESC";
        Cursor cursor = database.rawQuery(sql, null);
        List<MovieReview> reviews = convertCursorFactory.toMovieReviews(cursor);
        cursor.close();
        return reviews;
    }
}
