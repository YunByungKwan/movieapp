package ybk.org.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.CommentItemViewBinding;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    ArrayList<CommentItem> items = new ArrayList<>();
    OnItemClickListener listener;

    public interface OnItemClickListener {
        void OnItemClick(ViewHolder holder, View view, int position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        CommentItemViewBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.comment_item_view, parent, false);
        return new ViewHolder(binding);
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

        OnItemClickListener listener;
        private CommentItemViewBinding binding;

        public ViewHolder(@NonNull CommentItemViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            itemView.setOnClickListener( view -> {
                int position = getAdapterPosition();
                if(listener != null) {
                    listener.OnItemClick(ViewHolder.this, view, position);
                }
            });
        }

        public void setItem(CommentItem comment) {
            binding.tvUserId.setText(comment.getWriter());
            binding.tvRegisterTime.setText(comment.getRegisterTime());
            binding.rating.setRating(comment.getRating());
            binding.tvComment.setText(comment.getComment());
            binding.tvRecommendCount.setText(String.valueOf(comment.getRecommendCount()));
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }

}
