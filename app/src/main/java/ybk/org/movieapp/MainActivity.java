package ybk.org.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

import ybk.org.movieapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    boolean likeState = false;
    boolean dislikeState = false;

    int likeCount = 15;
    int dislikeCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    public void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);

        CommentAdapter adapter = new CommentAdapter();

        adapter.addItem(new CommentItem(
                "kym71**",
                "10분전",
                5,
                "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.",
                0));
        adapter.addItem(new CommentItem(
                "kym71**",
                "10분전",
                5,
                "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.",
                0));

        binding.lvComment.setAdapter(adapter);
    }

    public void onClickLikeButton() {
        if(!likeState) {
            increaseLikeCount();
        }else {
            decreaseLikeCount();
        }
    }

    public void onClickDisLikeButton() {
        if(!dislikeState) {
            increaseDisLikeCount();
        }else {
            decreaseDisLikeCount();
        }
    }

    public void increaseLikeCount() {
        likeCount += 1;
        binding.tvLikeCount.setText(String.valueOf(likeCount));
        likeState = true;
        binding.btnLike.setBackgroundResource(R.drawable.ic_thumb_up_selected);
        if(dislikeState) {
            decreaseDisLikeCount();
        }
    }

    public void decreaseLikeCount() {
        likeCount -= 1;
        binding.tvLikeCount.setText(String.valueOf(likeCount));
        likeState = false;
        binding.btnLike.setBackgroundResource(R.drawable.thumbs_up_selector);
    }

    public void increaseDisLikeCount() {
        dislikeCount += 1;
        binding.tvDislikeCount.setText(String.valueOf(dislikeCount));
        dislikeState = true;
        binding.btnDislike.setBackgroundResource(R.drawable.ic_thumb_down_selected);
        if(likeState) {
            decreaseLikeCount();
        }
    }

    public void decreaseDisLikeCount() {
        dislikeCount -= 1;
        binding.tvDislikeCount.setText(String.valueOf(dislikeCount));
        dislikeState = false;
        binding.btnDislike.setBackgroundResource(R.drawable.thumbs_down_selector);
    }

    class CommentAdapter extends BaseAdapter {

        ArrayList<CommentItem> items = new ArrayList<CommentItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(CommentItem item) {
            items.add(item);
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
            CommentItemView view = new CommentItemView(getApplicationContext());

            CommentItem item = items.get(position);

            view.setUserId(item.getUserId());
            view.setRegisterTime(item.getRegisterTime());
            view.setRatingBar(item.getRatingCount());
            view.setComment(item.getComment());
            view.setRecommendCount(item.getRecommendCount());

            return view;
        }
    }
}
