package com.malibin.boostcourseace.ui.movie.select;

import android.support.annotation.NonNull;

import com.android.volley.VolleyError;
import com.malibin.boostcourseace.ui.movie.MovieShortInfo;
import com.malibin.boostcourseace.network.RemoteRepository;
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
    private RemoteRepository remoteRepository;

    public MovieSelectPresenter(
            @NonNull MovieSelectContract.View view,
            @NonNull RemoteRepository remoteRepository
    ) {
        this.view = view;
        this.remoteRepository = remoteRepository;
    }

    @Override
    public void start() {

    }

    @Override
    public void sendMovieListRequest() {
        view.setLoadingIndicator(true);
        remoteRepository.sendMovieListRequest(movieListParam(),
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



























