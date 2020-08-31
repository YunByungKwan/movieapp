package ybk.org.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import ybk.org.movieapp.R;
import ybk.org.movieapp.util.App;
import ybk.org.movieapp.util.Utils;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<GalleryItem> items = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void OnItemClick(ViewHolder holder, View view, int position);
    }

    public GalleryAdapter(Context context) {
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
        View itemView = inflater.inflate(R.layout.gallery_item, parent, false);

        return new ViewHolder(itemView);
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

        private ImageView imageView;
        private ImageView playIcon;
        private OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.iv_gallery_item);
            playIcon = (ImageView) itemView.findViewById(R.id.iv_play);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.logd("Call onClick in GalleryAdapter");
                    int position = getAdapterPosition();
                    if(listener != null) {
                         listener.OnItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        /** 갤러리의 이미지를 설정함 */
        public void setItem(GalleryItem item) {
            Utils.logd("Call setItem in GalleryAdapter");
            StringBuilder sb = new StringBuilder();

            if(Utils.isVideo(item.getUrl())) {
                String id = getVideoId(item.getUrl());
                sb.append("https://img.youtube.com/vi/");
                sb.append(id);
                sb.append("/0.jpg");

                playIcon.setVisibility(View.VISIBLE);
                playIcon.bringToFront();
            } else {
                sb.append(item.getUrl());
            }

            Glide.with(App.getInstance()).load(sb.toString()).into(imageView);

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
