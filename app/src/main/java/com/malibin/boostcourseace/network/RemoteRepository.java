package com.malibin.boostcourseace.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.malibin.boostcourseace.network.request.MovieReviewSaveRequestDTO;
import com.malibin.boostcourseace.network.request.ReviewRecommendRequestDTO;
import com.malibin.boostcourseace.ui.dto.ReviewListDTO;
import com.malibin.boostcourseace.ui.movie.Movie;
import com.malibin.boostcourseace.ui.movie.MovieShortInfo;
import com.malibin.boostcourseace.network.request.MovieReviewListRequestDTO;
import com.malibin.boostcourseace.network.response.MovieDetailResponseDTO;
import com.malibin.boostcourseace.network.response.MovieReviewResponseDTO;
import com.malibin.boostcourseace.network.response.MovieShortInfoResponseDTO;
import com.malibin.boostcourseace.network.response.ResponseTemplate;
import com.malibin.boostcourseace.ui.review.MovieReview;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created By Yun Hyeok
 * on 8월 16, 2019
 */

public class RemoteRepository {

    private static RemoteRepository INSTANCE;
    private static RequestQueue requestQueue;
    private static Gson gson;

    private final String baseUrl = "http://boostcourse-appapi.connect.or.kr:10000";

    public static RemoteRepository getInstance(Context context) {
        if (INSTANCE != null)
            return INSTANCE;
        requestQueue = Volley.newRequestQueue(context);
        gson = new Gson();
        return INSTANCE = new RemoteRepository();
    }

    private RemoteRepository() {

    }

    public void sendMovieListRequest(
            Map<String, String> params,
            CallBack<List<MovieShortInfo>> callBack
    ) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                baseUrl + "/movie/readMovieList",
                response -> {
                    List<MovieShortInfo> movieShortInfoList = toMovieShortInfoList(response);
                    callBack.onResponse(movieShortInfoList);
                },
                callBack::onFailure
        ) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    private List<MovieShortInfo> toMovieShortInfoList(String response) {
        Type type = new TypeToken<ResponseTemplate<List<MovieShortInfoResponseDTO>>>() {
        }.getType();
        ResponseTemplate<List<MovieShortInfoResponseDTO>> responseDTO = gson.fromJson(response, type);
        return responseDTO.getResult()
                .stream()
                .map(MovieShortInfoResponseDTO::toMovieShortInfo)
                .collect(Collectors.toList());
    }

    public void sendMovieDetailRequest(
            int movieId,
            CallBack<Movie> callBack
    ) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                baseUrl + "/movie/readMovie?id=" + movieId,
                response -> {
                    Movie movie = toMovie(response);
                    callBack.onResponse(movie);
                },
                callBack::onFailure
        ) {
        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }
    // Volley 라이브러리는 GET 방식일 때 getParams 를 호출 하지 않는다. POST 와 PUT 일 때만 호출한다고 한다.

    private Movie toMovie(String response) {
        Type type = new TypeToken<ResponseTemplate<List<MovieDetailResponseDTO>>>() {
        }.getType();
        ResponseTemplate<List<MovieDetailResponseDTO>> responseDTO = gson.fromJson(response, type);
        return responseDTO.getResult().get(0).toMovie();
    }

    public void sendLikeDisLikeRequest(int movieId, String query, CallBack<String> callBack) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                baseUrl + "/movie/increaseLikeDisLike?id=" + movieId + "&" + query,
                response -> {
                    String result = getStringResult(response);
                    callBack.onResponse(result);
                },
                callBack::onFailure
        ) {

        };
        Log.d("Malibin Debug", "url : " + request.getUrl());
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    private String getStringResult(String response) {
        Type type = new TypeToken<ResponseTemplate<String>>() {
        }.getType();
        ResponseTemplate<String> responseDTO = gson.fromJson(response, type);
        return responseDTO.getResult();
    }

    public void sendMovieReviewListRequest(
            MovieReviewListRequestDTO dto,
            CallBack<ReviewListDTO> callBack
    ) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                baseUrl + "/movie/readCommentList" + dto.toParams(),
                response -> {
                    ReviewListDTO result = toMovieReviewList(response);
                    callBack.onResponse(result);
                },
                callBack::onFailure
        ) {

        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    private ReviewListDTO toMovieReviewList(String response) {
        Type type = new TypeToken<ResponseTemplate<List<MovieReviewResponseDTO>>>() {
        }.getType();
        ResponseTemplate<List<MovieReviewResponseDTO>> responseDTO = gson.fromJson(response, type);
        List<MovieReview> reviews = responseDTO.getResult()
                .stream()
                .map(MovieReviewResponseDTO::toMovieReview)
                .collect(Collectors.toList());

        return new ReviewListDTO(responseDTO.getTotalCount(), reviews);
    }

    public void sendReviewSaveRequest(
            MovieReviewSaveRequestDTO dto,
            CallBack<String> callBack
    ) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                baseUrl + "/movie/createComment" + dto.toParams(),
                response -> {
                    String result = getStringResult(response);
                    callBack.onResponse(result);
                },
                callBack::onFailure
        ) {

        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    public void sendRecommendRequest(
            ReviewRecommendRequestDTO dto,
            CallBack<String> callBack
    ) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                baseUrl + "/movie/increaseRecommend" + dto.toParams(),
                response -> {
                    String result = getStringResult(response);
                    callBack.onResponse(result);
                },
                callBack::onFailure
        ) {

        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }
}
