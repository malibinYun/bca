package com.malibin.boostcourseace.ui.review.more;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.network.MovieRepository;
import com.malibin.boostcourseace.ui.dto.ReviewMoreDTO;
import com.malibin.boostcourseace.ui.dto.ReviewWriteDTO;
import com.malibin.boostcourseace.ui.review.MovieReview;
import com.malibin.boostcourseace.ui.review.adapter.ReviewListAdapter;
import com.malibin.boostcourseace.ui.review.write.ReviewWriteActivity;
import com.malibin.boostcourseace.util.MovieRate;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ReviewMoreActivity extends AppCompatActivity implements ReviewMoreContract.View {

    private ReviewMoreContract.Presenter presenter;
    private ReviewListAdapter adapter;

    private ReviewMoreDTO reviewMoreDTO;
    private int reviewStartIdx = 0;

    private final int REQUEST_LENGTH = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_more);

        getIntentData();
        initPresenter();
        initView();

        sendReviewListRequest();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        ProgressBar progressBar = findViewById(R.id.progressBar_review_more_act);
        int visibility = active ? View.VISIBLE : View.INVISIBLE;
        progressBar.setVisibility(visibility);
    }

    @Override
    public void addReviews(List<MovieReview> reviews) {
        adapter.addReviews(reviews);
        reviewStartIdx += REQUEST_LENGTH;
    }

    private void getIntentData() {
        reviewMoreDTO = getIntent().getParcelableExtra("dto");
    }

    private void initPresenter() {
        MovieRepository repository = MovieRepository.getInstance(this);
        presenter = new ReviewMorePresenter(this, repository);
    }

    private void initView() {
        initToolbar();

        initMovieShortInfo();

        initReviewWriteBtn();
        initReviewList();
    }

    private void sendReviewListRequest() {
        int movieId = reviewMoreDTO.getMovieId();
        presenter.sendReviewListRequest(movieId, reviewStartIdx, REQUEST_LENGTH);
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
        String count = NumberFormat.getNumberInstance(Locale.US).format(reviewMoreDTO.getParticipants());
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
        int movieId = reviewMoreDTO.getMovieId();
        MovieRate rate = reviewMoreDTO.getMovieRate();
        String movieTitle = reviewMoreDTO.getTitle();
        return new ReviewWriteDTO(movieId, movieTitle, rate);
    }

    private void initReviewList() {
        ListView reviewList = findViewById(R.id.rv_review_more_review_list);
        adapter = new ReviewListAdapter(this);
        reviewList.setAdapter(adapter);
    }

}
