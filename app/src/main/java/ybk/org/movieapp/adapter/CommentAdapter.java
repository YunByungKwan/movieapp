package ybk.org.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import ybk.org.movieapp.R;
import ybk.org.movieapp.util.Utils;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    Context context;
    ArrayList<CommentItem> items = new ArrayList<>();
    OnItemClickListener listener;

    public interface OnItemClickListener {
        void OnItemClick(ViewHolder holder, View view, int position);
    }

    public CommentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.comment_item_view, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentItem item = items.get(position);
        holder.setItem(item);
        holder.setOnItemClickListener(listener);
    }

    public void addItem(CommentItem item) {
        items.add(item);
    }

    public CommentItem getItem(int position) {
        return items.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUserId;
        private TextView tvRegisterTime;
        private RatingBar ratingBar;
        private TextView tvComment;
        private TextView tvRecommendCount;
        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserId = itemView.findViewById(R.id.tv_user_id);
            tvRegisterTime = itemView.findViewById(R.id.tv_register_time);
            ratingBar = itemView.findViewById(R.id.rating);
            tvComment = itemView.findViewById(R.id.tv_comment);
            tvRecommendCount = itemView.findViewById(R.id.tv_recommend_count);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null) {
                        Utils.logd("Call onClick in ViewHolder");
                        listener.OnItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(CommentItem comment) {
            tvUserId.setText(comment.getWriter());
            tvRegisterTime.setText(comment.getRegisterTime());
            ratingBar.setRating(comment.getRating());
            tvComment.setText(comment.getComment());
            tvRecommendCount.setText(String.valueOf(comment.getRecommendCount()));
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }

}
