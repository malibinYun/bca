package com.malibin.boostcourseace.movie.select;

import android.support.annotation.NonNull;

import com.android.volley.VolleyError;
import com.malibin.boostcourseace.movie.MovieShortInfo;
import com.malibin.boostcourseace.network.MovieRepository;
import com.malibin.boostcourseace.network.CallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By Yun Hyeok
 * on 8월 16, 2019
 */

public class MovieSelectPresenter implements MovieSelectContract.Presenter {

    private MovieSelectContract.View view;
    private MovieRepository movieRepository;

    public MovieSelectPresenter(
            @NonNull MovieSelectContract.View view,
            @NonNull MovieRepository movieRepository
    ) {
        this.view = view;
        this.movieRepository = movieRepository;
    }

    @Override
    public void start() {

    }

    @Override
    public void sendMovieListRequest() {
        view.setLoadingIndicator(true);
        movieRepository.sendMovieListRequest(movieListParam(),
                new CallBack<List<MovieShortInfo>>() {
                    @Override
                    public void onResponse(List<MovieShortInfo> response) {
                        view.initMovieSelectPages(response);
                        view.setLoadingIndicator(false);
                    }

                    @Override
                    public void onFailure(VolleyError error) {
                        view.setLoadingIndicator(false);
                    }
                }
        );
    }

    private Map<String, String> movieListParam() {
        HashMap<String, String> result = new HashMap<>();
        result.put("type", "1");
        return result;
    }
}



























