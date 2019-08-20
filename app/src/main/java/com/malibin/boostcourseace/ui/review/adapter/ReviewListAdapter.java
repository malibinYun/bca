package com.malibin.boostcourseace.ui.review.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.malibin.boostcourseace.ui.dto.ReviewListDTO;
import com.malibin.boostcourseace.ui.review.MovieReview;
import com.malibin.boostcourseace.ui.review.MovieReviewView;

import java.util.ArrayList;
import java.util.List;

public class ReviewListAdapter extends BaseAdapter {

    private Context context;
    private List<MovieReview> items = new ArrayList<>();
    private View.OnClickListener recommendBtnClickListener;

    public ReviewListAdapter(Context context) {
        this.context = context;
    }

    public ReviewListAdapter(Context context, ReviewListDTO reviewListDTO) {
        this.context = context;
        this.items = reviewListDTO.getReviews();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieReviewView view = new MovieReviewView(context);
        if (convertView != null) {
            view = (MovieReviewView) convertView;
        }
        MovieReview item = items.get(position);
        view.bindContentsWith(item);
        view.setRecommendBtnClickListener(recommendBtnClickListener);
        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public void setRecommendBtnClickListener(View.OnClickListener onClickListener) {
        recommendBtnClickListener = onClickListener;
    }

    public void disableBtnById(int reviewId) {
        MovieReview review = items.stream()
                .filter(item -> item.getReviewId() == reviewId)
                .findFirst()
                .get();
        int position = items.indexOf(review);
        isEnabled(position);
        MovieReviewView view = (MovieReviewView) getView(position, null, null);
        //view.setEnabled(false);
        view.setRecommendBtnDisabled();

        review.setRecommendCount(review.getRecommendCount() + 1);
        notifyDataSetChanged();
        // 메소드는 모두 정상 실행되지만.. 온클릭리스너를 null 로 다시 set 해도 동작하지않고,
        // 온클릭리스너 set 했던 뷰의 enable 값을 false 로도 바꾸어봤지만 버튼을 막지는 못했습니다..
        // 제 생각엔 getView 에서 리턴해주는 View 가 일급 컬렉션 패턴이여서 그런 것 같다는 생각이 듭니다.
        // 그래서 우선은 클릭리스너 자체에서 클릭을 막는 코드를 넣었지만, 만일 서버통신에 실패하여 반영이 안되었을 때는
        // 다시 누를 수 있게 만들어야하는데, 이것을 하지 못하는 패턴이라 임시방편이라고 생각됩니다.
        // Review 데이터 클래스 내부에 isClicked 라는 변수를 두고, itemView 내부에 이 변수에 따른 클릭 블락 코드를 심어놓은뒤
        // isClicked 변수를 바꾼뒤 notifyDataSetChanged(); 메소드를 호출하는 방법이 떠오릅니다.
        // 시간이 많이 남지않아 해보지는 못했습니다만..  이게 맞는 방법인지요 ??
    }

    public void addReview(MovieReview review) {
        items.add(0, review);
        notifyDataSetChanged();
    }

    public void addReviews(List<MovieReview> reviews) {
        items.addAll(reviews);
        notifyDataSetChanged();
    }

    public void deleteAllReviews() {
        items.clear();
        notifyDataSetChanged();
    }
}
