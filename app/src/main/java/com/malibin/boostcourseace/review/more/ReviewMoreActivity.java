package com.malibin.boostcourseace.review.more;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.dto.ReviewMoreDTO;
import com.malibin.boostcourseace.dto.ReviewWriteDTO;
import com.malibin.boostcourseace.review.MovieReview;
import com.malibin.boostcourseace.review.adapter.ReviewListAdapter;
import com.malibin.boostcourseace.review.write.ReviewWriteActivity;
import com.malibin.boostcourseace.util.MovieRate;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReviewMoreActivity extends AppCompatActivity {

    private ReviewMoreDTO reviewMoreDTO;
    private int participantsCount = 1142;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_more);

        getIntentData();
        initView();
    }

    private void getIntentData() {
        reviewMoreDTO = getIntent().getParcelableExtra("dto");
    }

    private void initView() {
        initToolbar();

        initMovieShortInfo();

        initReviewWriteBtn();
        initReviewList();
    }

    private void initMovieShortInfo() {
        setMovieTitle();
        setMovieRate();
        setStarRatingBar();
        setStarRatingCount();
        setParticipantsCount();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_review_more_act);
        toolbar.setTitle("한줄평 목록");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }

    private void setMovieTitle() {
        String movieTitle = reviewMoreDTO.getTitle();
        TextView textView = findViewById(R.id.tv_review_more_act_title);
        textView.setText(movieTitle);
    }

    private void setMovieRate() {
        MovieRate movieRate = reviewMoreDTO.getMovieRate();
        int rateImageResource = movieRate.getImageResource();
        ImageView imageView = findViewById(R.id.iv_review_more_act_rating);
        imageView.setImageResource(rateImageResource);
    }

    private void setStarRatingBar() {
        float starRate = reviewMoreDTO.getStarRate() / 2;
        RatingBar ratingBar = findViewById(R.id.ratingBar_review_more_act);
        ratingBar.setRating(starRate);
    }

    private void setStarRatingCount() {
        String starRate = String.valueOf(reviewMoreDTO.getStarRate());
        TextView textView = findViewById(R.id.tv_review_more_act_star_count);
        textView.setText(starRate);
    }

    private void setParticipantsCount() {
        String count = NumberFormat.getNumberInstance(Locale.US).format(participantsCount);
        String text = "(" + count + "명 참여)";
        TextView textView = findViewById(R.id.tv_review_more_act_people_count);
        textView.setText(text);
    }

    private void initReviewWriteBtn() {
        LinearLayout btn = findViewById(R.id.btn_review_more_act_review_write);
        btn.setOnClickListener(view -> startReviewWriteActivity());
    }

    private void startReviewWriteActivity() {
        ReviewWriteDTO dto = getReviewWriteDTO();
        Intent intent = new Intent(this, ReviewWriteActivity.class);
        intent.putExtra("dto", dto);
        startActivity(intent);
    }

    private ReviewWriteDTO getReviewWriteDTO() {
        MovieRate rate = reviewMoreDTO.getMovieRate();
        String movieTitle = reviewMoreDTO.getTitle();
        return new ReviewWriteDTO(movieTitle, rate);
    }

    private void initReviewList() {
        ListView reviewList = findViewById(R.id.rv_review_more_review_list);
        ReviewListAdapter adapter = new ReviewListAdapter(this, tempData());
        reviewList.setAdapter(adapter);
    }

    private List<MovieReview> tempData() {
        ArrayList<MovieReview> result = new ArrayList<>();
        result.add(new MovieReview("", "모메", "10분전", 10f, "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", 0));
        result.add(new MovieReview("", "모메", "10분전", 3.7f, "리뷰 내용용요용ㅇ용", 2));
        result.add(new MovieReview("", "모메", "10분전", 4.2f, "리뷰 내용용요용ㅇ용", 2));
        result.add(new MovieReview("", "모메", "10분전", 2f, "리뷰 내용용요용ㅇ용", 2));
        result.add(new MovieReview("", "모메", "10분전", 4.2f, "리뷰 내용용요용ㅇ용", 2));
        result.add(new MovieReview("", "모메", "10분전", 2f, "리뷰 내용용요용ㅇ용", 2));
        result.add(new MovieReview("", "모메", "10분전", 4.2f, "리뷰 내용용요용ㅇ용dㅁㄴㅇㄹㄴㄷㄹㄴㄷㄹㄴㄷㄹㄴㄷㄹㄴㄷㄹㄴㄷㄹㄷㄹㄴㄹㄴㄷㄹㄴㄷㄹ", 2));
        result.add(new MovieReview("", "모메", "10분전", 2f, "리뷰 내용용요용ㅇ용", 2));
        result.add(new MovieReview("", "모메", "10분전", 4.2f, "리뷰 내용용요용ㅇ용", 2));
        result.add(new MovieReview("", "모메", "10분전", 2f, "리뷰 내용용요용ㅇ용", 2));
        result.add(new MovieReview("", "모메", "10분전", 4.2f, "리뷰 내용용요용ㅇ용", 2));
        result.add(new MovieReview("", "모메", "10분전", 2f, "리뷰 내용용요용ㅇ용", 2));
        return result;
    }
}
