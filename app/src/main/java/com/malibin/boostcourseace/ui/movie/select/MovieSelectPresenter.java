package com.malibin.boostcourseace.ui.movie.select;

import android.support.annotation.NonNull;

import com.android.volley.VolleyError;
import com.malibin.boostcourseace.db.LocalRepository;
import com.malibin.boostcourseace.network.CallBack;
import com.malibin.boostcourseace.network.RemoteRepository;
import com.malibin.boostcourseace.ui.entity.MovieShortInfo;

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
    private LocalRepository localRepository;

    public MovieSelectPresenter(
            @NonNull MovieSelectContract.View view,
            @NonNull RemoteRepository remoteRepository,
            @NonNull LocalRepository localRepository
    ) {
        this.view = view;
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    @Override
    public void start() {

    }

    @Override
    public void requestRemoteMovieList() {
        view.setLoadingIndicator(true);
        remoteRepository.sendMovieListRequest(movieListParam(),
                new CallBack<List<MovieShortInfo>>() {
                    @Override
                    public void onResponse(List<MovieShortInfo> response) {
                        view.initMovieSelectPages(response);
                        view.setLoadingIndicator(false);

                        localRepository.saveMovieList(response);
                    }

                    @Override
                    public void onFailure(VolleyError error) {
                        view.showServerFailToast();
                        view.setLoadingIndicator(false);
                    }
                }
        );
    }

    @Override
    public void requestLocalMovieList() {
        view.setLoadingIndicator(true);

        List<MovieShortInfo> result = localRepository.getMovieList();
        if (result.isEmpty()) {
            view.showMissingMovieList();
            view.setLoadingIndicator(false);
            return;
        }
        view.initMovieSelectPages(result);
        view.showDatabaseLoaded();
        view.setLoadingIndicator(false);
    }

    @Override
    public void deleteLocalMovieList() {
        localRepository.deleteMovieList();
    }

    private Map<String, String> movieListParam() {
        HashMap<String, String> result = new HashMap<>();
        result.put("type", "1");
        return result;
    }
}



























