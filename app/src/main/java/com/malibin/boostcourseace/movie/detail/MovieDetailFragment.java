package com.malibin.boostcourseace.movie.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.malibin.boostcourseace.R;
import com.malibin.boostcourseace.dto.ReviewListDTO;
import com.malibin.boostcourseace.dto.ReviewMoreDTO;
import com.malibin.boostcourseace.dto.ReviewWriteDTO;
import com.malibin.boostcourseace.movie.Movie;
import com.malibin.boostcourseace.movie.MovieHomeActivity;
import com.malibin.boostcourseace.network.MovieRepository;
import com.malibin.boostcourseace.review.MovieReview;
import com.malibin.boostcourseace.review.adapter.ReviewListAdapter;
import com.malibin.boostcourseace.review.more.ReviewMoreActivity;
import com.malibin.boostcourseace.review.write.ReviewWriteActivity;
import com.malibin.boostcourseace.util.LikeState;
import com.malibin.boostcourseace.util.MovieRate;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created By Yun Hyeok
 * on 8월 13, 2019
 */

public class MovieDetailFragment extends Fragment implements MovieDetailContract.View {

    private final int REQUEST_CODE_REVIEW_WRITE = 10000;
    private final int REQUEST_CODE_REVIEW_MORE = 10001;

    private MovieDetailContract.Presenter presenter;

    private Movie movie;
    private int movieId;
    private int likeCount;
    private int dislikeCount;

    private View inflatedView;
    private LikeState currentLikeState = LikeState.NOTHING;
    private ImageView btnLike;
    private ImageView btnDislike;
    private TextView tvLikeCount;
    private TextView tvDislikeCount;

    private ReviewListDTO reviewListDTO;

    public static MovieDetailFragment getInstance(int movieId) {
        Bundle bundle = new Bundle();
        bundle.putInt("movieId", movieId);
        MovieDetailFragment instance = new MovieDetailFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        initPresenter();
        initMovieId();
        sendMovieDetailRequest();
        sendRecentReviewsRequest();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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

    @Override
    public void setLoadingIndicator(boolean active) {
        int visibility = active ? View.VISIBLE : View.INVISIBLE;
        ProgressBar progressBar = inflatedView.findViewById(R.id.progressBar_movie_detail_frag);
        progressBar.setVisibility(visibility);
        notifyDataLoaded(!active);
    }

    @Override
    public void initMovieDetailInfo(Movie movie) {
        this.movie = movie;

        likeCount = movie.getLikeCount();
        dislikeCount = movie.getDislikeCount();
        initMovieDetailView();
    }

    @Override
    public void initRecentReviews(ReviewListDTO dto) {
        reviewListDTO = dto;
        initReviewZone();
    }

    private void initPresenter() {
        MovieRepository repository = MovieRepository.getInstance(getActivity());
        presenter = new MovieDetailPresenter(this, repository);
    }

    private void initMovieId() {
        if (getArguments() == null) {
            throw new NullPointerException();
        }
        movieId = getArguments().getInt("movieId");
    }

    private void sendMovieDetailRequest() {
        presenter.sendMovieDetailRequest(movieId);
    }

    private void sendRecentReviewsRequest() {
        presenter.sendRecentReviewRequest(movieId);
    }

    private void notifyDataLoaded(boolean active) {
        int visibility = active ? View.VISIBLE : View.INVISIBLE;
        NestedScrollView wholeView = inflatedView.findViewById(R.id.zone_movie_detail_frag_whole);
        wholeView.setVisibility(visibility);
    }

    private void initView() {
        inflatedView = getView();
        setActivityAppbarTitle();
    }

    private void initMovieDetailView() {

        initPosterZone();

        initGradeRecordZone();

        initDetailZone();

    }

    private void setActivityAppbarTitle() {
        ((MovieHomeActivity) getActivity()).setAppbarTitle("영화 상세");
    }

    private void initPosterZone() {
        initMovieImage();
        initMovieTitle();
        initMovieRateImage();
        initOpeningDay();
        initGenre();
        initShowTime();

        bindEvaluationView();
        initEvaluationButton();
    }

    private void initMovieImage() {
        ImageView movieImage = inflatedView.findViewById(R.id.iv_movie_detail_frag_main_image);
        Glide.with(this).load(movie.getImageUrl()).into(movieImage);
    }

    private void initMovieTitle() {
        TextView tvTitle = inflatedView.findViewById(R.id.tv_movie_detail_frag_title);
        tvTitle.setText(movie.getTitle());
    }

    public void initMovieRateImage() {
        ImageView ivMovieRate = inflatedView.findViewById(R.id.iv_movie_detail_frag_movie_rating);
        int imageResource = movie.getMovieRate().getImageResource();
        ivMovieRate.setImageResource(imageResource);
    }

    private void initOpeningDay() {
        TextView tvOpenDay = inflatedView.findViewById(R.id.tv_movie_detail_frag_opening_day);
        tvOpenDay.setText(movie.getOpeningDay());
    }

    private void initGenre() {
        TextView tvGenre = inflatedView.findViewById(R.id.tv_movie_detail_frag_genre);
        tvGenre.setText(movie.getGenre());
    }

    private void initShowTime() {
        TextView tvShowTime = inflatedView.findViewById(R.id.tv_movie_detail_frag_show_time);
        tvShowTime.setText(String.valueOf(movie.getShowTime()));
    }

    private void bindEvaluationView() {
        btnLike = inflatedView.findViewById(R.id.btn_movie_detail_frag_like);
        btnDislike = inflatedView.findViewById(R.id.btn_movie_detail_frag_dislike);
        tvLikeCount = inflatedView.findViewById(R.id.tv_movie_detail_frag_like_count);
        tvDislikeCount = inflatedView.findViewById(R.id.tv_movie_detail_frag_dislike_count);
    }

    private void initEvaluationButton() {
        tvLikeCount.setText(String.valueOf(likeCount));
        tvDislikeCount.setText(String.valueOf(dislikeCount));

        btnLike.setOnClickListener(view -> {
            if (currentLikeState == LikeState.LIKE) {
                presenter.sendLikeCancelRequest(movieId);
                rollbackCount(view);
                return;
            }
            modifyCount(view);
        });

        btnDislike.setOnClickListener(view -> {
            if (currentLikeState == LikeState.DISLIKE) {
                presenter.sendDisLikeCancelRequest(movieId);
                rollbackCount(view);
                return;
            }
            modifyCount(view);
        });
    }

    private void initGradeRecordZone() {
        initReservationRecord();
        initStarRateScore();
        initAccumulation();
    }

    private void initReservationRecord() {
        TextView tvRank = inflatedView.findViewById(R.id.tv_movie_detail_frag_ranking);
        tvRank.setText((movie.getReservationRank() + "위"));

        TextView tvRate = inflatedView.findViewById(R.id.tv_movie_detail_frag_reservation);
        tvRate.setText((movie.getReservationRate() + "%"));
    }

    private void initStarRateScore() {
        float starRate = movie.getStarRate();
        RatingBar ratingBar = inflatedView.findViewById(R.id.rating_movie_detail_frag_star_rating);
        ratingBar.setRating(starRate / 2);

        TextView tvStarRate = inflatedView.findViewById(R.id.tv_movie_detail_frag_star_rate);
        tvStarRate.setText(String.valueOf(starRate));
    }

    private void initAccumulation() {
        TextView tvAccumulation = inflatedView.findViewById(R.id.tv_movie_detail_frag_accumulation);
        String numberFormat = NumberFormat.getNumberInstance(Locale.US).format(movie.getAccumulatedAttendance());
        tvAccumulation.setText((numberFormat + "명"));
    }

    private void initDetailZone() {
        initPlot();
        initDirector();
        initActor();
    }

    private void initPlot() {
        TextView tvPlot = inflatedView.findViewById(R.id.tv_movie_detail_frag_plot);
        tvPlot.setText(movie.getPlot());
    }

    private void initDirector() {
        TextView tvDirector = inflatedView.findViewById(R.id.tv_movie_detail_frag_director);
        tvDirector.setText(movie.getDirector());
    }

    private void initActor() {
        TextView tvActor = inflatedView.findViewById(R.id.tv_movie_detail_frag_actor);
        tvActor.setText(movie.getActress());
    }

    private void initReviewZone() {
        initReviewList();
        initReviewWriteBtn();
        initReviewMoreBtn();
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
        if (currentLikeState != LikeState.NOTHING) {
            presenter.sendDisLikeCancelRequest(movieId);
        }
        presenter.sendLikeRequest(movieId);

        currentLikeState = LikeState.LIKE;
        btnLike.setSelected(true);
        tvLikeCount.setText(String.valueOf(likeCount + 1));
        btnDislike.setSelected(false);
        tvDislikeCount.setText(String.valueOf(dislikeCount));
    }

    private void dislikeClick() {
        if (currentLikeState != LikeState.NOTHING) {
            presenter.sendLikeCancelRequest(movieId);
        }
        presenter.sendDisLikeRequest(movieId);

        currentLikeState = LikeState.DISLIKE;
        btnLike.setSelected(false);
        tvLikeCount.setText(String.valueOf(likeCount));
        btnDislike.setSelected(true);
        tvDislikeCount.setText(String.valueOf(dislikeCount + 1));
    }

    private void initReviewList() {
        ListView reviewList = inflatedView.findViewById(R.id.rv_movie_detail_review_list);
        ReviewListAdapter adapter = new ReviewListAdapter(getActivity(), reviewListDTO);
        reviewList.setAdapter(adapter);
        setListViewHeightBasedOnChildren(reviewList);
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;
        int listWidth = listView.getMeasuredWidth();

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View mView = listAdapter.getView(i, null, listView);
            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(listWidth, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            totalHeight += mView.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 2));
    }

    private void initReviewWriteBtn() {
        LinearLayout btn = inflatedView.findViewById(R.id.btn_movie_detail_frag_review_write);
        btn.setOnClickListener(view -> startReviewWriteActivity());
    }

    private void initReviewMoreBtn() {
        ConstraintLayout btn = inflatedView.findViewById(R.id.btn_movie_detail_frag_review_more);
        btn.setOnClickListener(view -> startReviewMoreActivity());
    }

    private void startReviewWriteActivity() {
        ReviewWriteDTO dto = getReviewWriteDTO();
        Intent intent = new Intent(getActivity(), ReviewWriteActivity.class);
        intent.putExtra("dto", dto);
        startActivityForResult(intent, REQUEST_CODE_REVIEW_WRITE);
    }

    private void startReviewMoreActivity() {
        ReviewMoreDTO dto = getReviewMoreDTO();
        Intent intent = new Intent(getActivity(), ReviewMoreActivity.class);
        intent.putExtra("dto", dto);
        startActivityForResult(intent, REQUEST_CODE_REVIEW_MORE);
    }

    private ReviewWriteDTO getReviewWriteDTO() {
        String movieTitle = movie.getTitle();
        MovieRate rate = movie.getMovieRate();
        return new ReviewWriteDTO(movieTitle, rate);
    }

    private ReviewMoreDTO getReviewMoreDTO() {
        String movieTitle = movie.getTitle();
        MovieRate rate = movie.getMovieRate();
        float starRate = movie.getStarRate();
        int participants = reviewListDTO.getTotalCount();
        return new ReviewMoreDTO(movieTitle, rate, starRate, participants);
    }

    private void appendReceivedReview(MovieReview review) {
        ListView listView = inflatedView.findViewById(R.id.rv_movie_detail_review_list);
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
        Toast.makeText(getActivity(), "모두보기 화면에서 돌아왔습니다.", Toast.LENGTH_SHORT).show();
    }
}
