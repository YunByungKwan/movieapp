package ybk.org.movieapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {

    private ArrayList<CommentParcelable> comments = new ArrayList<>();

    @Override
    public int getCount() {
        return comments.size();
    }

    public void addItem(CommentParcelable item) {
        comments.add(item);
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentItemView view = new CommentItemView(parent.getContext());

        CommentParcelable comment = comments.get(position);

        view.setUserId(comment.userId);
        view.setRegisterTime(comment.registerTime);
        view.setRatingBar(comment.ratingCount);
        view.setComment(comment.comment);
        view.setRecommendCount(comment.recommendCount);

        return view;
    }
}
