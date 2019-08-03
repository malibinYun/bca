package com.malibin.boostcourseace.moviedetail;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.moviedetail.adapter.ReviewListAdapter;
import com.malibin.boostcourseace.util.LikeState;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private LikeState currentLikeState = LikeState.NOTHING;
    private int likeCount = 15;
    private int dislikeCount = 1;
    private float starRating = 8.2f;

    private ImageView btnLike;
    private ImageView btnDislike;
    private TextView tvLikeCount;
    private TextView tvDislikeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        viewInit();
    }

    private void viewInit() {
        setStarRateScore();
        bindingEvaluationView();
        evaluationButtonInit();
        reviewListInit();
        reviewWriteBtnInit();
        reviewMoreBtnInit();
    }

    private void setStarRateScore() {
        RatingBar ratingBar = findViewById(R.id.rating_movie_detail_act_star_rating);
        ratingBar.setRating(starRating / 2);
    }

    private void bindingEvaluationView() {
        btnLike = findViewById(R.id.btn_movie_detail_act_like);
        btnDislike = findViewById(R.id.btn_movie_detail_act_dislike);
        tvLikeCount = findViewById(R.id.tv_movie_detail_act_like_count);
        tvDislikeCount = findViewById(R.id.tv_movie_detail_act_dislike_count);
    }

    private void evaluationButtonInit() {
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

    private void reviewListInit() {
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
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        int UNBOUNDED = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        Log.v("Malibin Debug", "listAdapter.getCount() : " + listAdapter.getCount());
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(UNBOUNDED, UNBOUNDED);
            Log.v("Malibin Debug", "listItem.getMeasuredHeight() : " + listItem.getMeasuredHeight());
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    private void reviewWriteBtnInit() {
        LinearLayout btn = findViewById(R.id.btn_movie_detail_act_review_write);
        btn.setOnClickListener(view -> {
            Toast.makeText(this, "리뷰 쓰기 버튼 눌림", Toast.LENGTH_SHORT).show();
        });
    }

    private void reviewMoreBtnInit() {
        ConstraintLayout btn = findViewById(R.id.btn_movie_detail_act_review_more);
        btn.setOnClickListener(view -> {
            Toast.makeText(this, "리뷰 더보기 버튼 눌림", Toast.LENGTH_SHORT).show();
        });
    }
}
