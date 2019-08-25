package com.malibin.boostcourseace.ui.movie.select;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.VolleyError;
import com.malibin.boostcourseace.db.LocalRepository;
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

                        Log.d("Malibin Debug", "localRepository.saveMovieList(response); 호출 직전");
                        localRepository.saveMovieList(response);
                        Log.d("Malibin Debug", "localRepository.saveMovieList(response); 호출 직후");
                        requestLocalMovieList();
                        Log.d("Malibin Debug", "서버통신 끝");
                    }

                    @Override
                    public void onFailure(VolleyError error) {
                        view.setLoadingIndicator(false);
                    }
                }
        );
    }

    @Override
    public void requestLocalMovieList() {
        Log.d("Malibin Debug", "requestLocalMovieList 호출됨");

        List<MovieShortInfo> result = localRepository.getMovieList();
        for (MovieShortInfo item : result) {
            Log.d("Malibin Debug", "gma : " + item.toString());
        }
        Log.d("Malibin Debug", "nothing?");

        Log.d("Malibin Debug", "requestLocalMovieList 끝");
    }

    private Map<String, String> movieListParam() {
        HashMap<String, String> result = new HashMap<>();
        result.put("type", "1");
        return result;
    }
}



























