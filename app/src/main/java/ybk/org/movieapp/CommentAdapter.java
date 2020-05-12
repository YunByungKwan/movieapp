package ybk.org.movieapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {

    ArrayList<CommentParcelable> items = new ArrayList<CommentParcelable>();

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(CommentParcelable item) {
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
        CommentItemView view = new CommentItemView(parent.getContext());

        CommentParcelable item = items.get(position);

        view.setUserId(item.userId);
        view.setRegisterTime(item.registerTime);
        view.setRatingBar(item.ratingCount);
        view.setComment(item.comment);
        view.setRecommendCount(item.recommendCount);

        return view;
    }
}
