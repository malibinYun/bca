package com.malibin.boostcourseace.util;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.movie.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 13, 2019
 */
public class TempData {

    public static List<Movie> movie() {
        ArrayList<Movie> result = new ArrayList<>();

        result.add(new Movie(R.drawable.image1, "군도", MovieRate.FIFTEEN, "2014.07.23", "액션", "137",
                15, 1, 1.8f, 8.2f, 839399
                , "줄거리", "감독쓰", "배우쓰"));
        result.add(new Movie(R.drawable.image2, "호롤리", MovieRate.TWELVE, "2014.07.23", "액션", "137",
                15, 1, 1.8f, 8.2f, 839399
                , "줄거리", "감독쓰", "배우쓰"));
        result.add(new Movie(R.drawable.image3, "ㅇㅇ", MovieRate.ALL, "2014.07.23", "액션", "137",
                15, 1, 1.8f, 8.2f, 839399
                , "줄거리", "감독쓰", "배우쓰"));
        result.add(new Movie(R.drawable.image4, "ㄹㄹ", MovieRate.NINETEEN, "2014.07.23", "액션", "137",
                15, 1, 1.8f, 8.2f, 839399
                , "줄거리", "감독쓰", "배우쓰"));

        return result;
    }
}
