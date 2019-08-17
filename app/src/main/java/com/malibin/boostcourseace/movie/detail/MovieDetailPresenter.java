package com.malibin.boostcourseace.movie.detail;

import android.support.annotation.NonNull;

import com.android.volley.VolleyError;
import com.malibin.boostcourseace.movie.Movie;
import com.malibin.boostcourseace.network.CallBack;
import com.malibin.boostcourseace.network.MovieRepository;

/**
 * Created By Yun Hyeok
 * on 8월 17, 2019
 */
public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private MovieDetailContract.View view;
    private MovieRepository repository;

    public MovieDetailPresenter(
            @NonNull MovieDetailContract.View view,
            @NonNull MovieRepository repository
    ) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void start() {

    }

    @Override
    public void sendMovieDetailRequest(int movieId) {
        view.setLoadingIndicator(true);
        repository.sendMovieDetailRequest(movieId, new CallBack<Movie>() {
            @Override
            public void onResponse(Movie response) {
                view.initMovieDetailInfo(response);
                view.setLoadingIndicator(false);
            }

            @Override
            public void onFailure(VolleyError error) {
                view.setLoadingIndicator(false);
            }
        });
    }
}
