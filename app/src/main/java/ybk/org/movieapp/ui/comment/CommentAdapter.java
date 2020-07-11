package ybk.org.movieapp.ui.comment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

import ybk.org.movieapp.data.CommentItem;

public class CommentAdapter extends BaseAdapter {

    private ArrayList<CommentItem> comments = new ArrayList<>();

    @Override
    public int getCount() {
        return comments.size();
    }

    public void addItem(CommentItem item) {
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

        CommentItem comment = comments.get(position);

        view.setUserId(comment.getWriter());
        view.setRegisterTime(comment.getTime());
        view.setRatingBar(comment.getRating());
        view.setComment(comment.getContents());
        view.setRecommendCount(comment.getRecommend());

        return view;
    }
}
