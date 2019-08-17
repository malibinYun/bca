package com.malibin.boostcourseace.movie.select;

import android.content.Context;

import com.android.volley.VolleyError;
import com.malibin.boostcourseace.network.MovieRepository;
import com.malibin.boostcourseace.network.response.MovieShortInfoResponseDTO;
import com.malibin.boostcourseace.network.response.ResponseTemplate;
import com.malibin.boostcourseace.util.CallBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By Yun Hyeok
 * on 8월 16, 2019
 */
public class MovieSelectPresenter implements MovieSelectContract.Presenter {

    public MovieSelectContract.View view;
    public MovieRepository movieRepository;
    public Context context;

    @Override
    public void start() {
        movieRepository = MovieRepository.getInstance(context);
    }

    @Override
    public void getMovieShortInfoList() {
        movieRepository.sendRequest(tempParams(),
                new CallBack<List<MovieShortInfoResponseDTO>>() {
                    @Override
                    public void onResponse(List<MovieShortInfoResponseDTO> response) {
                        view.showMovieSelectPages(response);
                    }

                    @Override
                    public void onFailure(VolleyError error) {

                    }
                }
        );
    }

    Map<String, String> tempParams() {
        HashMap<String, String> result = new HashMap<>();
        result.put("type", "1");
        return result;
    }
}



























