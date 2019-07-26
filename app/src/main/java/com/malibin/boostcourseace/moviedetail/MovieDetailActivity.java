package com.malibin.boostcourseace.moviedetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.util.LikeState;

public class MovieDetailActivity extends AppCompatActivity {

    private LikeState currentLikeState = LikeState.NOTHING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        evaluationButtonInit();
    }

    private void evaluationButtonInit() {
        ImageView btnLike = findViewById(R.id.btn_movie_detail_act_like);
        ImageView btnDislike = findViewById(R.id.btn_movie_detail_act_dislike);
        TextView tvLikeCount = findViewById(R.id.tv_movie_detail_act_like_count);
        TextView tvDislikeCount = findViewById(R.id.tv_movie_detail_act_dislike_count);

        btnLike.setOnClickListener(view -> {
            int likeCount = getTextViewInt(tvLikeCount);
            if (currentLikeState == LikeState.LIKE) {
                currentLikeState = LikeState.NOTHING;
                tvLikeCount.setText(String.valueOf(likeCount - 1));
                view.setSelected(false);
                return;
            }
            currentLikeState = LikeState.LIKE;
            tvLikeCount.setText(String.valueOf(likeCount + 1));
            view.setSelected(true);
        });

        btnDislike.setOnClickListener(view -> {
            int disLikeCount = getTextViewInt(tvDislikeCount);
            if (currentLikeState == LikeState.DISLIKE) {
                currentLikeState = LikeState.NOTHING;
                tvDislikeCount.setText(String.valueOf(disLikeCount - 1));
                view.setSelected(false);
                return;
            }
            currentLikeState = LikeState.DISLIKE;
            tvDislikeCount.setText(String.valueOf(disLikeCount + 1));
            view.setSelected(true);
        });

        // 위 메소드들이 중복되는데, 코드를 줄일 방법을 잘 모르겠습니다.
        // 뷰들을 클래스 필드로 설정한 후 중복된 코드들을 메소드로 따로 빼는것이 나은지,
        // 아니면 위 방법이 나은지 모르겠습니다.
        // 평소에 코틀린으로 코드를 짜다보니 자바로는 익숙하지 않아 코드를 어떻게 줄이고 깔끔하게 할 수 있을지를 잘 모르겠습니다.
    }

    private int getTextViewInt(TextView tv) {
        return Integer.parseInt(tv.getText().toString());
    }

}
