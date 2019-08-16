package com.malibin.boostcourseace.movie.select;

import com.malibin.boostcourseace.util.BasePresenter;
import com.malibin.boostcourseace.util.BaseView;

/**
 * Created By Yun Hyeok
 * on 8월 16, 2019
 */

public interface MovieSelectContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void show

    }

    interface Presenter extends BasePresenter {



    }

}
