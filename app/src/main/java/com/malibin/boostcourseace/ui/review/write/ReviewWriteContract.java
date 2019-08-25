package com.malibin.boostcourseace.ui.review.write;

import com.malibin.boostcourseace.network.request.MovieReviewSaveRequestDTO;
import com.malibin.boostcourseace.util.BasePresenter;
import com.malibin.boostcourseace.util.BaseView;

/**
 * Created By Yun Hyeok
 * on 8월 18, 2019
 */
public interface ReviewWriteContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void notifyReviewSaved();

        void showServerFailToast();

        void setSaveBtnEnable(boolean state);

    }

    interface Presenter extends BasePresenter {

        void saveReview(MovieReviewSaveRequestDTO dto);

    }

}
