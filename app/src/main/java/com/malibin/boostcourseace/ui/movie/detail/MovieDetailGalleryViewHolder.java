package com.malibin.boostcourseace.ui.movie.detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.ui.entity.MovieGallery;
import com.malibin.boostcourseace.ui.entity.MoviePictureGallery;
import com.malibin.boostcourseace.ui.entity.MovieVideoGallery;

/**
 * Created By Yun Hyeok
 * on 8월 30, 2019
 */

public class MovieDetailGalleryViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivImage;
    private ImageView ivIsVideo;

    public MovieDetailGalleryViewHolder(@NonNull View itemView) {
        super(itemView);

        ivImage = itemView.findViewById(R.id.iv_rv_movie_gallery_image);
        ivIsVideo = itemView.findViewById(R.id.iv_rv_movie_gallery_video);
    }

    public void bindContent(MovieGallery gallery) {
        if (gallery instanceof MoviePictureGallery) {
            String imageUrl = ((MoviePictureGallery) gallery).getImageUrl();
            setPictureGallery(imageUrl);
            return;
        }
        setVideoGallery((MovieVideoGallery) gallery);
    }

    private void setPictureGallery(String imageUrl) {
        Glide.with(ivImage)
                .load(imageUrl)
                .into(ivImage);
        ivImage.setOnClickListener(view -> {
        });
    }

    private void setVideoGallery(MovieVideoGallery gallery) {
        ivIsVideo.setVisibility(View.VISIBLE);
        Glide.with(ivImage)
                .load(gallery.getImageUrl())
                .into(ivImage);
        ivImage.setOnClickListener(view -> {
        });
    }
}
