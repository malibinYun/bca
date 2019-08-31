package com.malibin.boostcourseace.ui.review.more;

import com.malibin.boostcourseace.ui.entity.MovieReview;
import com.malibin.boostcourseace.util.BasePresenter;
import com.malibin.boostcourseace.util.BaseView;

import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 18, 2019
 */
public interface ReviewMoreContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void addReviews(List<MovieReview> reviews);

        void showRecommendCompleteToast(int reviewId);

        void showServerFailToast();

        void showMissingReviews();

        void showDatabaseLoaded();
    }

    interface Presenter extends BasePresenter {

        void requestRemoteReviewList(int movieId, int startIdx, int length);

        void requestLocalReviewList(int movieId);

        void sendReviewRecommendRequest(int reviewId);

    }

}
