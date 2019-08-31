package com.malibin.boostcourseace.ui.review.more;

import android.support.annotation.NonNull;

import com.android.volley.VolleyError;
import com.malibin.boostcourseace.db.LocalRepository;
import com.malibin.boostcourseace.network.CallBack;
import com.malibin.boostcourseace.network.RemoteRepository;
import com.malibin.boostcourseace.network.request.MovieReviewListRequestDTO;
import com.malibin.boostcourseace.network.request.ReviewRecommendRequestDTO;
import com.malibin.boostcourseace.ui.dto.ReviewListDTO;
import com.malibin.boostcourseace.ui.entity.MovieReview;

import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 18, 2019
 */

public class ReviewMorePresenter implements ReviewMoreContract.Presenter {

    private ReviewMoreContract.View view;
    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    public ReviewMorePresenter(
            @NonNull ReviewMoreContract.View view,
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
    public void requestRemoteReviewList(int movieId, int startIdx, int length) {
        view.setLoadingIndicator(true);
        MovieReviewListRequestDTO dto = new MovieReviewListRequestDTO(movieId, null, startIdx, length);
        remoteRepository.sendMovieReviewListRequest(dto, new CallBack<ReviewListDTO>() {
            @Override
            public void onResponse(ReviewListDTO response) {
                view.addReviews(response.getReviews());
                view.setLoadingIndicator(false);

                localRepository.saveReviews(movieId, response.getReviews());
            }

            @Override
            public void onFailure(VolleyError error) {
                view.showServerFailToast();
                view.setLoadingIndicator(false);
            }
        });

    }

    @Override
    public void requestLocalReviewList(int movieId) {
        view.setLoadingIndicator(true);
        List<MovieReview> reviews = localRepository.getReviews(movieId);

        if (reviews.isEmpty()) {
            view.showMissingReviews();
            return;
        }
        view.addReviews(reviews);
        view.showDatabaseLoaded();
        view.setLoadingIndicator(false);
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
}
