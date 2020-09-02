package ybk.org.movieapp.ui.moviedetail;

import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import ybk.org.movieapp.MovieListActivity;
import ybk.org.movieapp.adapter.CommentAdapter;
import ybk.org.movieapp.adapter.CommentItem;
import ybk.org.movieapp.adapter.GalleryAdapter;
import ybk.org.movieapp.adapter.GalleryItem;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.ui.moviegallery.MovieGalleryActivity;
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.databinding.FragmentMovieDetailBinding;
import ybk.org.movieapp.ui.comment.CommentListActivity;
import ybk.org.movieapp.ui.comment.CommentWriteActivity;
import ybk.org.movieapp.R;
import ybk.org.movieapp.util.Network;
import ybk.org.movieapp.util.Utils;

public class MovieDetailFragment extends Fragment {

    private FragmentMovieDetailBinding binding;
    private MovieDetailViewModel viewModel;
    private List<DetailMovie> movieItem;
    private List<Comment> commentList;
    private GalleryAdapter galleryAdapter;
    private CommentAdapter commentAdapter;
    private int id;
    private String title;
    private int grade;
    private int numOfComment = 2;
    private boolean isPressedLike = false;
    private boolean isPressedDisLike = false;
    private int likeCount;
    private int dislikeCount;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setBasicMovieInfoFromBundle();

        MovieListActivity activity = (MovieListActivity) getActivity();
        activity.hideMenu();

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        viewModel.movieId.setValue(id);
        viewModel.limit.setValue(numOfComment);
        viewModel.init();
        viewModel.getDetailMovie().observe(getViewLifecycleOwner(), new Observer<List<DetailMovie>>() {
            @Override
            public void onChanged(List<DetailMovie> movieInfo) {
                movieItem = movieInfo;
                if(movieItem.size() != 0) {
                    setMovieInfo();
                    Network.showToast(getActivity());
                } else {
                    Toast.makeText(getActivity(), getString(R.string.msg_no_data),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.getCommentList().observe(getViewLifecycleOwner(), new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> _commentList) {
                commentList = _commentList;
                if(commentList.size() != 0) {
                    updateCommentList();
                    Network.showToast(getActivity());
                } else {
                    Toast.makeText(getActivity(), getString(R.string.msg_no_data),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        dataBinding(view);

        return view;
    }

    private void setBasicMovieInfoFromBundle() {
        if(getArguments() != null) {
            id = getArguments().getInt(getString(R.string.bun_id));
            title = getArguments().getString(getString(R.string.bun_title));
            grade = getArguments().getInt(getString(R.string.bun_grade));
        }
    }

    private void setMovieInfo() {
        if(getArguments() != null) {
            Glide.with(getContext()).load(movieItem.get(0).getImage()).into(binding.ivMoviePoster);
            binding.tvMovieTitle.setText(movieItem.get(0).getTitle());
            binding.ivMovieRating.setImageResource(Utils.convertGradeToResId(movieItem.get(0).getGrade()));
            binding.tvReleaseDate.setText( movieItem.get(0).getDate());
            binding.tvGenre.setText(movieItem.get(0).getGenre());
            binding.tvRunningTime.setText(String.valueOf(movieItem.get(0).getDuration()));
            likeCount = movieItem.get(0).getLike();
            dislikeCount = movieItem.get(0).getDislike();
            binding.tvLikeCount.setText(String.valueOf(likeCount));
            binding.tvDislikeCount.setText(String.valueOf(dislikeCount));
            binding.tvReservationRateRanking.setText(String.valueOf(movieItem.get(0).getReservationGrade()));
            binding.tvReservationRate.setText(String.valueOf(movieItem.get(0).getAudienceRating()));
            binding.rating.setRating(movieItem.get(0).getReviewerRating() / 2);
            binding.tvGrade.setText(String.valueOf(movieItem.get(0).getReviewerRating()));

            DecimalFormat df = new DecimalFormat("###,###");
            binding.tvCumulativeAudience.setText(String.valueOf(df.format(movieItem.get(0).getAudience())));
            binding.tvStory.setText(movieItem.get(0).getSynopsis());
            binding.tvDirectorName.setText(movieItem.get(0).getDirector());
            binding.tvCasting.setText(movieItem.get(0).getActor());

            setGalleryAdapter();
        }
    }

    private void setGalleryAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        binding.rvGallery.setLayoutManager(layoutManager);
        galleryAdapter = new GalleryAdapter(getActivity());

        String moviePhotos = movieItem.get(0).getPhotos();
        if(moviePhotos != null) {
            String[] photos = Utils.parseStringInComma(moviePhotos);

            for(String photoUrl : photos) {
                galleryAdapter.addItem(new GalleryItem(photoUrl));
            }
        }

        String movieVideos = movieItem.get(0).getVideos();
        if(movieVideos != null) {
            String[] videos = Utils.parseStringInComma(movieVideos);

            for(String videoUrl: videos) {
                galleryAdapter.addItem(new GalleryItem(videoUrl));
            }
        }

        binding.rvGallery.setAdapter(galleryAdapter);
        galleryAdapter.setOnItemClickListener(new GalleryAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(GalleryAdapter.ViewHolder holder, View view, int position) {
                String url = galleryAdapter.getItem(position).getUrl();

                if(Utils.isVideo(url)) { // 동영상일 경우
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } else { // 이미지일 경우
                    Intent intent = new Intent(getActivity(), MovieGalleryActivity.class);
                    intent.putExtra(getString(R.string.gallery_url), url);
                    startActivity(intent);
                }
            }
        });
    }

    /** MovieListActivity에서 호출됨 */
    public void addComment(HashMap<String, Object> comment) {
        viewModel.addComment(comment);
        addCommentToList(comment);
    }

    private void addCommentToList(HashMap<String, Object> commentMap) {
        Comment comment = new Comment();
        comment.setId((Integer) commentMap.get(getString(R.string.comm_id)));
        comment.setWriter((String) commentMap.get(getString(R.string.comm_writer)));
        comment.setTime((String) commentMap.get(getString(R.string.comm_time)));
        comment.setRating((Float) commentMap.get(getString(R.string.comm_rating)));
        comment.setContents((String) commentMap.get(getString(R.string.comm_cont)));
        comment.setRecommend(0);
        commentList.add(0, comment);

        viewModel.commentList.setValue(commentList);
    }

    private void updateCommentList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        binding.rvComment.setLayoutManager(layoutManager);
        commentAdapter = new CommentAdapter(getActivity());
        for(int i = 0; i < numOfComment; i++) {
            commentAdapter.addItem(new CommentItem(
                            commentList.get(i).getId(),
                            commentList.get(i).getWriter(),
                            commentList.get(i).getTime(),
                            commentList.get(i).getRating(),
                            commentList.get(i).getContents(),
                            commentList.get(i).getRecommend()
                    ));
        }
        binding.rvComment.setAdapter(commentAdapter);
        // 댓글 리스트 아이템 클릭 이벤트
        commentAdapter.setOnItemClickListener(new CommentAdapter.OnItemClickListener() {

            @Override
            public void OnItemClick(CommentAdapter.ViewHolder holder, View view, int position) {
                addRecommendCountToServer(position);
                addRecommendCountToUI(position);
            }
        });
    }

    /** Recommend count를 증가시키고 서버로 보냄 */
    private void addRecommendCountToServer(int position) {
        HashMap<String, Object> param = new HashMap<>();
        param.put(getString(R.string.comm_review_id), commentList.get(position).getId());
        param.put(getString(R.string.comm_writer), commentList.get(position).getWriter());
        viewModel.recommendComment(param);
    }

    /** 화면에 recommend count를 증가시킴 */
    private void addRecommendCountToUI(int position) {
        int currentRecommendCnt = commentList.get(position).getRecommend();
        commentList.get(position).setRecommend(++currentRecommendCnt);
        updateCommentList();
    }

    private void dataBinding(View view) {
        binding = DataBindingUtil.bind(view);
        if(binding != null) {
            binding.setFragment(this);
        }
    }

    /** 좋아요 아이콘 클릭시 이벤트 */
    public void onClickLikeButton() {
        if(!isPressedLike) {
            incrLike();
        } else {
            decrLike();
        }
    }

    /** 싫어요 아이콘 클릭시 이벤트 */
    public void onClickDisLikeButton() {
        if(!isPressedDisLike) {
            incrDisLike();
        } else {
            decrDisLike();
        }
    }

    /** 좋아요 숫자 증가. 싫어요 아이콘이 선택되었을 경우, 싫어요 숫자 감소 */
    public void incrLike() {
        HashMap<String, Object> param = new HashMap<>();
        param.put(getString(R.string.comm_id), id);
        param.put(getString(R.string.post_like), "Y");
        viewModel.addLikeDisLike(param);

        binding.tvLikeCount.setText(String.valueOf(++likeCount));
        isPressedLike = true;
        binding.btnLike.setBackgroundResource(R.drawable.ic_thumb_up_selected);

        if(isPressedDisLike) {
            decrDisLike();
        }
    }

    /** 좋아요 숫자 감소 */
    public void decrLike() {
        HashMap<String, Object> param = new HashMap<>();
        param.put(getString(R.string.comm_id), id);
        param.put(getString(R.string.post_like), "N");
        viewModel.addLikeDisLike(param);

        binding.tvLikeCount.setText(String.valueOf(--likeCount));
        isPressedLike = false;
        binding.btnLike.setBackgroundResource(R.drawable.thumbs_up_selector);
    }

    /** 싫어요 숫자 증가. 좋아요 아이콘이 선택되었을 경우, 좋아요 숫자 감소 */
    public void incrDisLike() {
        HashMap<String, Object> param = new HashMap<>();
        param.put(getString(R.string.comm_id), id);
        param.put(getString(R.string.post_dislike), "Y");
        viewModel.addLikeDisLike(param);

        binding.tvDislikeCount.setText(String.valueOf(++dislikeCount));
        isPressedDisLike = true;
        binding.btnDislike.setBackgroundResource(R.drawable.ic_thumb_down_selected);

        if(isPressedLike) {
            decrLike();
        }
    }

    /** 싫어요 숫자 감소 */
    public void decrDisLike() {
        HashMap<String, Object> param = new HashMap<>();
        param.put(getString(R.string.comm_id), id);
        param.put(getString(R.string.post_dislike), "N");
        viewModel.addLikeDisLike(param);

        binding.tvDislikeCount.setText(String.valueOf(--dislikeCount));
        isPressedDisLike = false;
        binding.btnDislike.setBackgroundResource(R.drawable.thumbs_down_selector);
    }

    /** 작성하기 버튼 클릭시 이벤트 */
    public void onClickWriteCommentButton() {
        if(getActivity() != null && Network.isConnected()) {
            Intent intent = new Intent(getActivity(), CommentWriteActivity.class);
            intent.putExtra(getString(R.string.mov_id), id);
            intent.putExtra(getString(R.string.mov_title), title);
            intent.putExtra(getString(R.string.mov_grade), grade);
            getActivity().startActivityForResult(intent, Constants.REQUEST_COMMENT_WRITE_CODE);
        } else {
            Toast.makeText(getActivity(), getString(R.string.msg_req_net),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /** 모두보기 버튼 클릭시 이벤트 */
    public void onClickLoadAllButton() {
        Intent intent = new Intent(getActivity(), CommentListActivity.class);
        intent.putExtra(getString(R.string.mov_id), id);
        intent.putExtra(getString(R.string.mov_title), title);
        intent.putExtra(getString(R.string.mov_rating), movieItem.get(0).getReviewerRating());
        intent.putExtra(getString(R.string.mov_grade), grade);
        startActivityForResult(intent, Constants.REQUEST_COMMENT_LIST_CODE);
    }
}
