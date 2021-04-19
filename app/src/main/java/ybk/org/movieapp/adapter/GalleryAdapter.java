package ybk.org.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.GalleryItemBinding;
import ybk.org.movieapp.util.App;
import ybk.org.movieapp.util.Utils;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private ArrayList<GalleryItem> items = new ArrayList<>();
    private OnItemClickListener listener;

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
        GalleryItemBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.gallery_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GalleryItem item = items.get(position);
        holder.setItem(item);
        holder.setOnItemClickListener(listener);
    }

    public void addItem(GalleryItem item) {
        items.add(item);
    }

    public GalleryItem getItem(int position) {
        return items.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private OnItemClickListener listener;
        private GalleryItemBinding binding;

        public ViewHolder(@NonNull GalleryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            this.binding.getRoot().setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(listener != null) {
                     listener.OnItemClick(ViewHolder.this, view, position);
                }
            });
        }

        /** 갤러리의 이미지를 설정함 */
        public void setItem(GalleryItem item) {
            StringBuilder sb = new StringBuilder();

            if(Utils.isVideo(item.getUrl())) {
                String id = getVideoId(item.getUrl());
                sb.append("https://img.youtube.com/vi/");
                sb.append(id);
                sb.append("/0.jpg");

                binding.ivPlay.setVisibility(View.VISIBLE);
                binding.ivPlay.bringToFront();
            } else {
                sb.append(item.getUrl());
            }

            Glide.with(App.getInstance()).load(sb.toString()).into(binding.ivGalleryItem);

        }

        /** Url에서 id를 추출 */
        private String getVideoId(String src) {
            String[] array = src.split("/");
            return array[3];
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }

}
