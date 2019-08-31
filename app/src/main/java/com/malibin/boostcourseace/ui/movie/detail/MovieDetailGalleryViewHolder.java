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
import com.malibin.boostcourseace.util.GalleryClickListener;

/**
 * Created By Yun Hyeok
 * on 8월 30, 2019
 */

public class MovieDetailGalleryViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivImage;
    private ImageView ivIsVideo;

    private GalleryClickListener galleryClickListener;

    public MovieDetailGalleryViewHolder(@NonNull View itemView) {
        super(itemView);

        ivImage = itemView.findViewById(R.id.iv_rv_movie_gallery_image);
        ivIsVideo = itemView.findViewById(R.id.iv_rv_movie_gallery_video);
    }

    public void setGalleryClickListener(GalleryClickListener l) {
        galleryClickListener = l;
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
        ivImage.setOnClickListener(view -> galleryClickListener.onImageClick(imageUrl));
    }

    private void setVideoGallery(MovieVideoGallery gallery) {
        String imageUrl = gallery.getImageUrl();
        String videoUrl = gallery.getVideoUrl();
        ivIsVideo.setVisibility(View.VISIBLE);
        Glide.with(ivImage)
                .load(imageUrl)
                .into(ivImage);
        ivImage.setOnClickListener(view -> galleryClickListener.onVideoClick(videoUrl));
    }
}
