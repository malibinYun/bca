package com.malibin.boostcourseace.moviedetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.dto.ReviewMoreDTO;
import com.malibin.boostcourseace.dto.ReviewWriteDTO;
import com.malibin.boostcourseace.review.adapter.ReviewListAdapter;
import com.malibin.boostcourseace.review.MovieReview;
import com.malibin.boostcourseace.review.more.ReviewMoreActivity;
import com.malibin.boostcourseace.review.write.ReviewWriteActivity;
import com.malibin.boostcourseace.util.LikeState;
import com.malibin.boostcourseace.util.MovieRate;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private final int REQUEST_CODE_REVIEW_WRITE = 10000;
    private final int REQUEST_CODE_REVIEW_MORE = 10001;

    private String movieTitle = "군도";
    private float starRate = 8.2f;
    private int movieRate = 15;
    private LikeState currentLikeState = LikeState.NOTHING;
    private int likeCount = 15;
    private int dislikeCount = 1;

    private ImageView btnLike;
    private ImageView btnDislike;
    private TextView tvLikeCount;
    private TextView tvDislikeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initView();
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
        if (requestCode == REQUEST_CODE_REVIEW_MORE) {
            showReturnFromReviewMore();
        }
    }

    private void initView() {
        setStarRateScore();
        bindEvaluationView();
        initEvaluationButton();
        initReviewList();
        initReviewWriteBtn();
        initReviewMoreBtn();
    }

    private void setStarRateScore() {
        RatingBar ratingBar = findViewById(R.id.rating_movie_detail_act_star_rating);
        ratingBar.setRating(starRate / 2);
    }

    private void bindEvaluationView() {
        btnLike = findViewById(R.id.btn_movie_detail_act_like);
        btnDislike = findViewById(R.id.btn_movie_detail_act_dislike);
        tvLikeCount = findViewById(R.id.tv_movie_detail_act_like_count);
        tvDislikeCount = findViewById(R.id.tv_movie_detail_act_dislike_count);
    }

    private void initEvaluationButton() {
        tvLikeCount.setText(String.valueOf(likeCount));
        tvDislikeCount.setText(String.valueOf(dislikeCount));

        btnLike.setOnClickListener(view -> {
            if (currentLikeState == LikeState.LIKE) {
                rollbackCount(view);
                return;
            }
            modifyCount(view);
        });

        btnDislike.setOnClickListener(view -> {
            if (currentLikeState == LikeState.DISLIKE) {
                rollbackCount(view);
                return;
            }
            modifyCount(view);
        });
    }

    private void rollbackCount(View view) {
        view.setSelected(false);
        currentLikeState = LikeState.NOTHING;
        if (view.equals(btnLike)) {
            tvLikeCount.setText(String.valueOf(likeCount));
        }
        if (view.equals(btnDislike)) {
            tvDislikeCount.setText(String.valueOf(dislikeCount));
        }
    }

    private void modifyCount(View view) {
        if (view.equals(btnLike)) {
            likeClick();
        }
        if (view.equals(btnDislike)) {
            dislikeClick();
        }
    }

    private void likeClick() {
        currentLikeState = LikeState.LIKE;
        btnLike.setSelected(true);
        tvLikeCount.setText(String.valueOf(likeCount + 1));
        btnDislike.setSelected(false);
        tvDislikeCount.setText(String.valueOf(dislikeCount));
    }

    private void dislikeClick() {
        currentLikeState = LikeState.DISLIKE;
        btnLike.setSelected(false);
        tvLikeCount.setText(String.valueOf(likeCount));
        btnDislike.setSelected(true);
        tvDislikeCount.setText(String.valueOf(dislikeCount + 1));
    }

    private void initReviewList() {
        ListView reviewList = findViewById(R.id.rv_movie_detail_review_list);
        ReviewListAdapter adapter = new ReviewListAdapter(this, tempData());
        reviewList.setAdapter(adapter);
        setListViewHeightBasedOnChildren(reviewList);
    }

    private List<MovieReview> tempData() {
        ArrayList<MovieReview> result = new ArrayList<>();
        result.add(new MovieReview("", "모메", "10분전", 10f, "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", 0));
        result.add(new MovieReview("", "모메", "10분전", 3.7f, "리뷰 내용용요용ㅇ용", 2));
        result.add(new MovieReview("", "모메", "10분전", 4.2f, "리뷰 내용용요용ㅇ용", 2));
        result.add(new MovieReview("", "모메", "10분전", 2f, "리뷰 내용용요용ㅇ용", 2));
        return result;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;
        int listWidth = listView.getMeasuredWidth();

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View mView = listAdapter.getView(i, null, listView);
            mView.measure(
                    MeasureSpec.makeMeasureSpec(listWidth, MeasureSpec.UNSPECIFIED),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

            totalHeight += mView.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 2));
    }

    private void initReviewWriteBtn() {
        LinearLayout btn = findViewById(R.id.btn_movie_detail_act_review_write);
        btn.setOnClickListener(view -> startReviewWriteActivity());
    }

    private void initReviewMoreBtn() {
        ConstraintLayout btn = findViewById(R.id.btn_movie_detail_act_review_more);
        btn.setOnClickListener(view -> startReviewMoreActivity());
    }

    private void startReviewWriteActivity() {
        ReviewWriteDTO dto = getReviewWriteDTO();
        Intent intent = new Intent(this, ReviewWriteActivity.class);
        intent.putExtra("dto", dto);
        startActivityForResult(intent, REQUEST_CODE_REVIEW_WRITE);
    }

    private void startReviewMoreActivity() {
        ReviewMoreDTO dto = getReviewMoreDTO();
        Intent intent = new Intent(this, ReviewMoreActivity.class);
        intent.putExtra("dto", dto);
        startActivityForResult(intent, REQUEST_CODE_REVIEW_MORE);
    }

    private ReviewWriteDTO getReviewWriteDTO() {
        MovieRate rate = MovieRate.findByRate(movieRate);
        return new ReviewWriteDTO(movieTitle, rate);
    }

    private ReviewMoreDTO getReviewMoreDTO() {
        MovieRate rate = MovieRate.findByRate(movieRate);
        return new ReviewMoreDTO(movieTitle, rate, starRate);
    }

    private void appendReceivedReview(MovieReview review) {
        ListView listView = findViewById(R.id.rv_movie_detail_review_list);
        ReviewListAdapter adapter = (ReviewListAdapter) listView.getAdapter();
        adapter.addReview(review);
        setListViewHeightBasedOnChildren(listView);
    }

    private void showWriteReviewSaved() {
        Snackbar.make(btnLike, R.string.snack_bar_review_saved, Snackbar.LENGTH_SHORT).show();
    }

    private void showWriteReviewCanceled() {
        Snackbar.make(btnLike, R.string.snack_bar_review_canceled, Snackbar.LENGTH_SHORT).show();
    }

    private void showReturnFromReviewMore() {
        Toast.makeText(this, "모두보기 화면에서 돌아왔습니다.", Toast.LENGTH_SHORT).show();
    }
}
