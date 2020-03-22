package ybk.org.movieapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CommentItemView extends LinearLayout {

    TextView tvUserId;

    TextView tvRegisterTime;

    RatingBar ratingBar;

    TextView tvComment;

    TextView tvRecommendCount;

    public CommentItemView(Context context) {
        super(context);

        init(context);
    }

    public CommentItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_item_view, this, true);

        tvUserId = (TextView)findViewById(R.id.tv_user_id);
        tvRegisterTime = (TextView)findViewById(R.id.tv_register_time);
        ratingBar = (RatingBar)findViewById(R.id.rating);
        tvComment = (TextView)findViewById(R.id.tv_comment);
        tvRecommendCount = (TextView)findViewById(R.id.tv_recommend_count);
    }

    public void setUserId(String userId) {
        tvUserId.setText(userId);
    }

    public void setRegisterTime(String registerTime) {
        tvRegisterTime.setText(registerTime);
    }

    public void setRatingBar(float rating) {
        ratingBar.setRating(rating);
    }

    public void setComment(String comment) {
        tvComment.setText(comment);
    }

    public void setRecommendCount(int recommendCount) {
        tvRecommendCount.setText(String.valueOf(recommendCount));
    }
}
