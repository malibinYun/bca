package com.malibin.boostcourseace.ui.movie.detail;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.VolleyError;
import com.malibin.boostcourseace.network.request.ReviewRecommendRequestDTO;
import com.malibin.boostcourseace.ui.dto.ReviewListDTO;
import com.malibin.boostcourseace.ui.movie.Movie;
import com.malibin.boostcourseace.network.CallBack;
import com.malibin.boostcourseace.network.RemoteRepository;
import com.malibin.boostcourseace.network.request.MovieReviewListRequestDTO;

/**
 * Created By Yun Hyeok
 * on 8월 17, 2019
 */
public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private MovieDetailContract.View view;
    private RemoteRepository remoteRepository;

    public MovieDetailPresenter(
            @NonNull MovieDetailContract.View view,
            @NonNull RemoteRepository remoteRepository
    ) {
        this.view = view;
        this.remoteRepository = remoteRepository;
    }

    @Override
    public void start() {

    }

    @Override
    public void sendMovieDetailRequest(int movieId) {
        view.setLoadingIndicator(true);
        remoteRepository.sendMovieDetailRequest(movieId, new CallBack<Movie>() {
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
    public void sendRecentReviewRequest(int movieId) {
        MovieReviewListRequestDTO dto = new MovieReviewListRequestDTO(movieId, "3", null, null);
        remoteRepository.sendMovieReviewListRequest(dto, new CallBack<ReviewListDTO>() {
            @Override
            public void onResponse(ReviewListDTO response) {
                view.initRecentReviews(response);
            }

            @Override
            public void onFailure(VolleyError error) {

            }
        });
    }

    @Override
    public void sendReviewRecommendRequest(int reviewId) {
        ReviewRecommendRequestDTO dto = new ReviewRecommendRequestDTO(reviewId);
        remoteRepository.sendRecommendRequest(dto, new CallBack<String>() {
            @Override
            public void onResponse(String response) {
                view.showRecommendCompleteToast(reviewId);
            }

            @Override
            public void onFailure(VolleyError error) {

            }
        });
    }

    @Override
    public void sendLikeRequest(int movieId) {
        remoteRepository.sendLikeDisLikeRequest(movieId, "likeyn=Y", new CallBack<String>() {
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
        remoteRepository.sendLikeDisLikeRequest(movieId, "likeyn=N", new CallBack<String>() {
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
        remoteRepository.sendLikeDisLikeRequest(movieId, "dislikeyn=Y", new CallBack<String>() {
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
        remoteRepository.sendLikeDisLikeRequest(movieId, "dislikeyn=N", new CallBack<String>() {
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
