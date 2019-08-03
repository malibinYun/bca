package com.malibin.boostcourseace.review;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.review.MovieReview;

public class MovieReviewView extends ConstraintLayout {

    private ImageView ivProfile;
    private TextView tvNickname;
    private TextView tvTime;
    private RatingBar ratingBar;
    private TextView tvContent;
    private TextView tvRecommendCount;

    public MovieReviewView(Context context) {
        super(context);

        init(context);
    }

    public MovieReviewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.rv_item_movie_review, this, true);
        viewBinding();
    }

    private void viewBinding() {
        ivProfile = findViewById(R.id.iv_rv_movie_review_profile);
        tvNickname = findViewById(R.id.tv_rv_movie_review_nickname);
        tvTime = findViewById(R.id.tv_rv_movie_review_time_text);
        ratingBar = findViewById(R.id.rating_rv_movie_review);
        tvContent = findViewById(R.id.iv_rv_movie_review_contents);
        tvRecommendCount = findViewById(R.id.tv_rv_movie_review_recommend_count);
    }

    public void bindContentsWith(MovieReview review){
        setNickname(review.getNickname());
        setProfileImage(R.drawable.user1);
        setTimeText(review.getTime());
        setRatingBarScore(review.getStarRate());
        setContent(review.getContent());
        setRecommendCount(review.getRecommendCount());
    }

    public void setNickname(String nickname) {
        tvNickname.setText(nickname);
    }

    public void setProfileImage(String imgUrl) {
        Glide
                .with(getContext())
                .load(imgUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfile);
    }

    public void setProfileImage(int resourceId) {
        Glide
                .with(getContext())
                .load(resourceId)
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfile);
    }

    public void setTimeText(String timeText) {
        tvTime.setText(timeText);
    }

    public void setRatingBarScore(float score) {
        ratingBar.setRating(score);
    }

    public void setContent(String content) {
        tvContent.setText(content);
    }

    public void setRecommendCount(int count) {
        tvRecommendCount.setText(String.valueOf(count));
    }
}