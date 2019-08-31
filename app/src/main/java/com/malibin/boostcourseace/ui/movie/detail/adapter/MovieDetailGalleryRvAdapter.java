package com.malibin.boostcourseace.ui.movie.detail.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.ui.entity.MovieGallery;
import com.malibin.boostcourseace.ui.movie.detail.MovieDetailGalleryViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Yun Hyeok
 * on 8월 30, 2019
 */
public class MovieDetailGalleryRvAdapter extends RecyclerView.Adapter<MovieDetailGalleryViewHolder> {

    private Context context;
    private List<MovieGallery> galleryList = new ArrayList<>();

    public MovieDetailGalleryRvAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MovieDetailGalleryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_item_movie_gallery, viewGroup, false);
        return new MovieDetailGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieDetailGalleryViewHolder holder, int position) {
        MovieGallery gallery = galleryList.get(position);
        holder.bindContent(gallery);
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public void addGallery(MovieGallery gallery) {
        galleryList.add(gallery);
    }

    public void addGalleries(List<MovieGallery> galleryList) {
        this.galleryList.addAll(galleryList);
    }
}
