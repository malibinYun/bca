package com.malibin.boostcourseace.ui.movie.detail;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.VolleyError;
import com.malibin.boostcourseace.db.LocalRepository;
import com.malibin.boostcourseace.network.CallBack;
import com.malibin.boostcourseace.network.RemoteRepository;
import com.malibin.boostcourseace.network.request.MovieReviewListRequestDTO;
import com.malibin.boostcourseace.network.request.ReviewRecommendRequestDTO;
import com.malibin.boostcourseace.ui.dto.ReviewListDTO;
import com.malibin.boostcourseace.ui.movie.Movie;
import com.malibin.boostcourseace.ui.review.MovieReview;

import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 17, 2019
 */

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private MovieDetailContract.View view;
    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    public MovieDetailPresenter(
            @NonNull MovieDetailContract.View view,
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
    public void requestRemoteMovieDetail(int movieId) {
        view.setLoadingIndicator(true);
        remoteRepository.sendMovieDetailRequest(movieId, new CallBack<Movie>() {
            @Override
            public void onResponse(Movie response) {
                view.initMovieDetailInfo(response);
                view.setLoadingIndicator(false);

                localRepository.saveMovieDetail(response);
            }

            @Override
            public void onFailure(VolleyError error) {
                view.showServerFailToast();
                view.setLoadingIndicator(false);
            }
        });
    }

    @Override
    public void requestRemoteRecentReview(int movieId) {
        MovieReviewListRequestDTO dto = new MovieReviewListRequestDTO(movieId, "3", null, null);
        remoteRepository.sendMovieReviewListRequest(dto, new CallBack<ReviewListDTO>() {
            @Override
            public void onResponse(ReviewListDTO response) {
                view.initRecentReviews(response);

                localRepository.saveReviews(movieId, response.getReviews());
            }

            @Override
            public void onFailure(VolleyError error) {
                view.showServerFailToast();
            }
        });
    }

    @Override
    public void requestLocalMovieDetail(int movieId) {
        view.setLoadingIndicator(true);
        Movie movie = localRepository.getMovieDetail(movieId);
        if (movie == null) {
            view.showMissingMovie();
            return;
        }
        view.initMovieDetailInfo(movie);
        view.showDatabaseLoaded();
        view.setLoadingIndicator(false);
    }

    @Override
    public void requestLocalRecentReview(int movieId) {
        List<MovieReview> reviews = localRepository.getReviews(movieId);
        if (reviews.isEmpty()) {
            view.showMissingReviews();
            return;
        }
        ReviewListDTO dto = new ReviewListDTO(3, reviews.subList(0, 3));
        view.initRecentReviews(dto);
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
                view.showServerFailToast();
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
                view.showServerFailToast();
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
                view.showServerFailToast();
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
                view.showServerFailToast();
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
                view.showServerFailToast();
            }
        });
    }
}
