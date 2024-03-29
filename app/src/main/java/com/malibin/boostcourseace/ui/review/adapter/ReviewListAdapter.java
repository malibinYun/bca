package com.malibin.boostcourseace.ui.review.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.malibin.boostcourseace.ui.dto.ReviewListDTO;
import com.malibin.boostcourseace.ui.entity.MovieReview;
import com.malibin.boostcourseace.ui.review.MovieReviewView;

import java.util.ArrayList;
import java.util.List;

public class ReviewListAdapter extends BaseAdapter {

    private Context context;
    private List<MovieReview> items = new ArrayList<>();
    private View.OnClickListener recommendBtnClickListener;

    public ReviewListAdapter(Context context) {
        this.context = context;
    }

    public ReviewListAdapter(Context context, ReviewListDTO reviewListDTO) {
        this.context = context;
        this.items = reviewListDTO.getReviews();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieReviewView view = new MovieReviewView(context);
        if (convertView != null) {
            view = (MovieReviewView) convertView;
        }
        MovieReview item = items.get(position);
        view.bindContentsWith(item);
        view.setRecommendBtnClickListener(recommendBtnClickListener);
        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public void setRecommendBtnClickListener(View.OnClickListener onClickListener) {
        recommendBtnClickListener = onClickListener;
    }

    public void disableBtnById(int reviewId) {
        MovieReview review = items.stream()
                .filter(item -> item.getReviewId() == reviewId)
                .findFirst()
                .get();
        int position = items.indexOf(review);
        isEnabled(position);
        MovieReviewView view = (MovieReviewView) getView(position, null, null);
        //view.setEnabled(false);
        view.setRecommendBtnDisabled();

        review.setRecommendCount(review.getRecommendCount() + 1);
        notifyDataSetChanged();
    }

    public void addReview(MovieReview review) {
        items.add(0, review);
        notifyDataSetChanged();
    }

    public void addReviews(List<MovieReview> reviews) {
        items.addAll(reviews);
        notifyDataSetChanged();
    }

    public void deleteAllReviews() {
        items.clear();
        notifyDataSetChanged();
    }
}
