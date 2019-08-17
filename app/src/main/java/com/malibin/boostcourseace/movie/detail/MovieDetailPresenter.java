package com.malibin.boostcourseace.movie.detail;

import android.support.annotation.NonNull;
import android.util.Log;

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

    @Override
    public void sendLikeRequest(int movieId) {
        repository.sendLikeDisLikeRequest(movieId, "likeyn=Y", new CallBack<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Malibin Debug", "response : " + response);
            }

            @Override
            public void onFailure(VolleyError error) {

            }
        });
    }

    @Override
    public void sendLikeCancelRequest(int movieId) {
        repository.sendLikeDisLikeRequest(movieId, "likeyn=N", new CallBack<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Malibin Debug", "response : " + response);
            }

            @Override
            public void onFailure(VolleyError error) {

            }
        });
    }

    @Override
    public void sendDisLikeRequest(int movieId) {
        repository.sendLikeDisLikeRequest(movieId, "dislikeyn=Y", new CallBack<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Malibin Debug", "response : " + response);
            }

            @Override
            public void onFailure(VolleyError error) {

            }
        });
    }

    @Override
    public void sendDisLikeCancelRequest(int movieId) {
        repository.sendLikeDisLikeRequest(movieId, "dislikeyn=N", new CallBack<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Malibin Debug", "response : " + response);
            }

            @Override
            public void onFailure(VolleyError error) {

            }
        });
    }
}
