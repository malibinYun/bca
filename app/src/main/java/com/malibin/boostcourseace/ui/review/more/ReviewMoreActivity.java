package com.malibin.boostcourseace.ui.review.more;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.db.DatabaseOpenHelper;
import com.malibin.boostcourseace.db.LocalRepository;
import com.malibin.boostcourseace.network.RemoteRepository;
import com.malibin.boostcourseace.ui.dto.ReviewMoreDTO;
import com.malibin.boostcourseace.ui.dto.ReviewWriteDTO;
import com.malibin.boostcourseace.ui.entity.MovieReview;
import com.malibin.boostcourseace.ui.review.MovieReviewView;
import com.malibin.boostcourseace.ui.review.adapter.ReviewListAdapter;
import com.malibin.boostcourseace.ui.review.write.ReviewWriteActivity;
import com.malibin.boostcourseace.util.MovieRate;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ReviewMoreActivity extends AppCompatActivity implements ReviewMoreContract.View {

    private final int REQUEST_CODE_REVIEW_WRITE = 10003;
    private final int REQUEST_LENGTH = 20;

    private ReviewMoreContract.Presenter presenter;
    private ReviewListAdapter adapter;

    private ReviewMoreDTO reviewMoreDTO;
    private int reviewStartIdx = 0;

    private View.OnClickListener recommendBtnClickListener = v -> {
        v.setEnabled(false);
        View parent = (View) v.getParentForAccessibility();
        int reviewId = ((MovieReviewView) parent).getReviewId();
        presenter.sendReviewRecommendRequest(reviewId);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_more);

        getIntentData();
        initPresenter();
        initView();

        int movieId = reviewMoreDTO.getMovieId();
        if (isInternetConnected()) {
            presenter.requestRemoteReviewList(movieId, reviewStartIdx, REQUEST_LENGTH);
            return;
        }
        presenter.requestLocalReviewList(movieId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_REVIEW_WRITE) {
            if (resultCode == Activity.RESULT_OK) {
                MovieReview receivedData = data.getParcelableExtra("review");
                appendReceivedReview(receivedData);
                showWriteReviewSaved();
                return;
            }
            showWriteReviewCanceled();
        }
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

    @Override
    public void showRecommendCompleteToast(int reviewId) {
        Toast.makeText(this, "리뷰를 추천하였습니다 !", Toast.LENGTH_SHORT).show();
        adapter.disableBtnById(reviewId);
    }

    @Override
    public void showServerFailToast() {
        Toast.makeText(this, R.string.server_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMissingReviews() {
        Toast.makeText(this, R.string.missing_reviews, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDatabaseLoaded() {
        Toast.makeText(this, R.string.database_loaded, Toast.LENGTH_SHORT).show();
    }

    private void getIntentData() {
        reviewMoreDTO = getIntent().getParcelableExtra("dto");
    }

    private void initPresenter() {
        DatabaseOpenHelper helper = DatabaseOpenHelper.getInstance(this, "cinemaHeaven.db", null, 1);
        LocalRepository localRepository = LocalRepository.getInstance(helper);
        RemoteRepository remoteRepository = RemoteRepository.getInstance(this);
        presenter = new ReviewMorePresenter(this, remoteRepository, localRepository);
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
        startActivityForResult(intent, REQUEST_CODE_REVIEW_WRITE);
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
        adapter.setRecommendBtnClickListener(recommendBtnClickListener);
        reviewList.setAdapter(adapter);
    }

    private void appendReceivedReview(MovieReview review) {
        adapter.addReview(review);
    }

    private void showWriteReviewSaved() {
        View view = findViewById(R.id.progressBar_review_more_act);
        Snackbar.make(view, R.string.snack_bar_review_saved, Snackbar.LENGTH_SHORT).show();
    }

    private void showWriteReviewCanceled() {
        View view = findViewById(R.id.progressBar_review_more_act);
        Snackbar.make(view, R.string.snack_bar_review_canceled, Snackbar.LENGTH_SHORT).show();
    }

    private boolean isInternetConnected() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null;
    }
}
