package com.malibin.boostcourseace.util;

/**
 * Created By Yun Hyeok
 * on 8월 16, 2019
 */
public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

}
