package com.malibin.boostcourseace.moviedetail;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.moviedetail.adapter.ReviewListAdapter;
import com.malibin.boostcourseace.util.LikeState;

import java.util.ArrayList;
import java.util.List;

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

        viewInit();
    }

    private void viewInit() {
        bindingEvaluationView();
        evaluationButtonInit();
        reviewListInit();
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
        result.add(new MovieReview("", "모메", "10분전", 5f, "리뷰 내용용요용ㅇ용", 2));
        result.add(new MovieReview("", "모메", "10분전", 8f, "리뷰 내용용요용ㅇ용", 2));
        result.add(new MovieReview("", "모메", "10분전", 2f, "리뷰 내용용요용ㅇ용", 2));
        return result;
    }

    // 스크롤뷰 내 리스트뷰의 높이조절 이슈때문에 추가하였습니다만..
    // 이것을 사용해도 리스트뷰의 자식뷰 높이를 제대로 불러오지 못하는 것 같습니다.
    // 저로서는 해결이 도저히 되지 않아 이슈가 남은 상태로 코드 리뷰를 신청합니다..
    // 항상 리사이클러뷰를 사용하여 이러한 뷰를 구현해왔는데, 평소에 써본적 없는 리스트뷰를
    // 사용해서 구현하려니 시간내에 이슈를 극복하기에는 역부족인것 같습니다..
    // 리사이클러뷰로 구현한다면 문제없이 구현할 수 있습니다. 혹시 리사이클러뷰로 구현해도 괜찮은지요..
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        int UNBOUNDED = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        Log.v("Malibin Debug","listAdapter.getCount() : " + listAdapter.getCount());
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(UNBOUNDED, UNBOUNDED);
            Log.v("Malibin Debug","listItem.getMeasuredHeight() : " + listItem.getMeasuredHeight());
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
