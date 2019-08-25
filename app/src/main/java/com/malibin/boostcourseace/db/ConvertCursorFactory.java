package com.malibin.boostcourseace.db;

import android.database.Cursor;

import com.malibin.boostcourseace.ui.movie.MovieShortInfo;
import com.malibin.boostcourseace.util.MovieRate;

import java.util.ArrayList;
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
}
