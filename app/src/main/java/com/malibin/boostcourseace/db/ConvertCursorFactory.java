package com.malibin.boostcourseace.db;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.malibin.boostcourseace.ui.entity.Movie;
import com.malibin.boostcourseace.ui.entity.MovieReview;
import com.malibin.boostcourseace.ui.entity.MovieShortInfo;
import com.malibin.boostcourseace.util.MovieRate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 25, 2019
 */

public class ConvertCursorFactory {

    public List<MovieShortInfo> toMovieList(Cursor cursor) {
        ArrayList<MovieShortInfo> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            MovieShortInfo item =
                    new MovieShortInfo(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getInt(4),
                            cursor.getFloat(5),
                            MovieRate.findByRate(cursor.getInt(6)),
                            cursor.getString(7));
            result.add(item);
        }
        return result;
    }

    @Nullable
    public Movie toMovie(Cursor cursor) {
        cursor.moveToNext();
        Movie movie = null;
        try {
            movie = new Movie(
                    cursor.getInt(0),                           // id
                    cursor.getString(1),                        // imageUrl
                    cursor.getString(2),                        // title
                    MovieRate.findByRate(cursor.getInt(3)),     // movieRate
                    cursor.getString(4),                        // openingDay
                    cursor.getString(5),                        // genre
                    cursor.getInt(6),                           // showTime
                    cursor.getInt(7),                           // likeCount
                    cursor.getInt(8),                           // dislikeCount
                    cursor.getInt(9),                           // reservationRank
                    cursor.getFloat(10),                        // reservationRate
                    cursor.getFloat(11),                        // starRate
                    cursor.getFloat(12),                        // accumulatedAttendance
                    cursor.getString(13),                       // plot
                    cursor.getString(14),                       // director
                    cursor.getString(15),                       // actress
                    Arrays.asList(TextUtils.split(cursor.getString(16), "##")), // photoLinks
                    Arrays.asList(TextUtils.split(cursor.getString(17), "##")), // videoLinks
                    Arrays.asList(TextUtils.split(cursor.getString(18), "##"))  // outLinks
            );
            //Log.d("Malibin Debug", Arrays.asList(TextUtils.split(cursor.getString(16), "##")));
        } catch (Exception e) {
            return null;
        }
        return movie;
    }

    public List<MovieReview> toMovieReviews(Cursor cursor) {
        ArrayList<MovieReview> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            MovieReview item =
                    new MovieReview(
                            cursor.getInt(1),    // review_id
                            cursor.getString(2), // profile
                            cursor.getString(3), // nickname
                            cursor.getString(4), // time
                            cursor.getFloat(5),  // star_rate
                            cursor.getString(6), // content
                            cursor.getInt(7));   // recommendCount
            result.add(item);
        }
        return result;
    }
}
