package com.malibin.boostcourseace.ui.review.write;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.ui.dto.ReviewWriteDTO;
import com.malibin.boostcourseace.ui.review.MovieReview;
import com.malibin.boostcourseace.util.MovieRate;

public class ReviewWriteActivity extends AppCompatActivity {

    private ReviewWriteDTO reviewWriteDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);

        getIntentData();
        initView();
    }

    private void getIntentData() {
        reviewWriteDTO = getIntent().getParcelableExtra("dto");
    }

    private void initView() {
        initToolbar();
        setMovieTitle();
        setMovieRate();
        initSaveBtn();
        initCancelBtn();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_review_write_act);
        toolbar.setTitle("한줄평 작성");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }

    private void setMovieTitle() {
        String movieTitle = reviewWriteDTO.getTitle();
        TextView textView = findViewById(R.id.tv_review_write_act_title);
        textView.setText(movieTitle);
    }

    private void setMovieRate() {
        MovieRate movieRate = reviewWriteDTO.getMovieRate();
        int rateImageResource = movieRate.getImageResource();
        ImageView imageView = findViewById(R.id.iv_review_write_act_rating);
        imageView.setImageResource(rateImageResource);
    }

    private void initSaveBtn() {
        ConstraintLayout saveBtn = findViewById(R.id.btn_review_write_act_save);
        saveBtn.setOnClickListener(view -> clickSaveBtn());
    }

    private void clickSaveBtn() {
        saveReview();
        finish();
    }

    private void initCancelBtn() {
        ConstraintLayout cancelBtn = findViewById(R.id.btn_review_write_act_cancel);
        cancelBtn.setOnClickListener(view -> clickCancelBtn());
    }

    private void clickCancelBtn() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    private void saveReview() {
        String content = getEditTextContent();
        float starRate = getRatingBarScore();
        Log.d("Malibin Debug", "content : " + content + ", starRate : " + starRate);
        sendReviewToPreviousActivity();
        // 리뷰를 저장하는 로직 작성
    }

    private String getEditTextContent() {
        EditText editText = findViewById(R.id.et_review_write_act_content);
        return editText.getText().toString();
    }

    private float getRatingBarScore() {
        RatingBar ratingBar = findViewById(R.id.ratingBar_review_write_act);
        return ratingBar.getRating();
    }

    private MovieReview getMovieReview() {
        float starRate = getRatingBarScore();
        String content = getEditTextContent();
        return new MovieReview(0, "", "userNickname", "방금", starRate, content, 0);
    }

    private void sendReviewToPreviousActivity() {
        MovieReview review = getMovieReview();
        Intent intent = new Intent();
        intent.putExtra("review", review);
        setResult(Activity.RESULT_OK, intent);
    }
}
