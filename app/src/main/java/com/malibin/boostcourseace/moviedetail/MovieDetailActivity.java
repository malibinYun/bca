package com.malibin.boostcourseace.moviedetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.util.LikeState;

public class MovieDetailActivity extends AppCompatActivity {

    private LikeState currentLikeState = LikeState.NOTHING;
    private int likeCount = 15;
    private int dislikeCount = 1;

    private ImageView btnLike;
    private ImageView btnDislike;
    private TextView tvLikeCount;
    private TextView tvDislikeCount;

    // 코드를 메소드로 최대한 분리하기위해 뷰들을 클래스 필드로 선언했는데, 이게 좋은방법인지 궁금합니다.
    // 만일 액티비티에서 관리하는 뷰들이 많아지면 필드가 너무 많아지는데요,
    // 코틀린에서는 anko 라이브러리가 뷰 바인딩을 해주어 쉽게 사용할수 있는데, 자바에서는 어떻게 하면 좋을지 잘 모르겠습니다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        bindingEvaluationView();
        evaluationButtonInit();
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

        // 클릭리스너 안의 메소드가 중복이 되는데 하나로 줄일 수 있는 방법이 있을까요?
        // 코틀린의 listOf(...).forEach{...} 문법이 존재하는지 궁금합니다.
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
            likeClicked();
        }
        if (view.equals(btnDislike)) {
            dislikeClick();
        }
    }

    private void likeClicked() {
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
}
