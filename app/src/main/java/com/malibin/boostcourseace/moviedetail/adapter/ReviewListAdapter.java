package com.malibin.boostcourseace.moviedetail.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.malibin.boostcourseace.moviedetail.MovieReview;
import com.malibin.boostcourseace.moviedetail.MovieReviewView;

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
        MovieReviewView view = (MovieReviewView) convertView;
        if (view == null) {
            view = new MovieReviewView(context);
        }
        MovieReview item = items.get(position);
        view.bindContentsWith(item);
        return view;
    }
}
