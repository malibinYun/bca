package com.malibin.boostcourseace.review.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.malibin.boostcourseace.review.MovieReview;
import com.malibin.boostcourseace.review.MovieReviewView;

import java.util.List;

public class ReviewListAdapter extends BaseAdapter {

    private Context context;
    private List<MovieReview> items;

    public ReviewListAdapter(Context context, List<MovieReview> items) {
        this.context = context;
        this.items = items;
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
        return view;
    }

    public void addReview(MovieReview review) {
        items.add(review);
        notifyDataSetChanged();
    }
}
