package ybk.org.movieapp.ui.moviedetail;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import ybk.org.movieapp.ui.main.MovieListActivity;
import ybk.org.movieapp.adapter.CommentAdapter;
import ybk.org.movieapp.adapter.CommentItem;
import ybk.org.movieapp.adapter.GalleryAdapter;
import ybk.org.movieapp.adapter.GalleryItem;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.ui.moviegallery.MovieGalleryActivity;
import ybk.org.movieapp.util.App;
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.databinding.FragmentMovieDetailBinding;
import ybk.org.movieapp.ui.comment.CommentListActivity;
import ybk.org.movieapp.ui.commentwrite.CommentWriteActivity;
import ybk.org.movieapp.R;
import ybk.org.movieapp.util.Dlog;
import ybk.org.movieapp.util.Network;
import ybk.org.movieapp.util.Utils;

public class MovieDetailFragment extends Fragment {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    @Inject
    public MovieDetailViewModel viewModel;

    private FragmentMovieDetailBinding binding;
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
    public int likeCount;
    public int dislikeCount;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        App.getInstance().appComponent()
                .movieDetailComponent().create().inject(this);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        setBasicMovieInfoFromBundle();
//        viewModel = new ViewModelProvider(
//                this,viewModelFactory).get(MovieDetailViewModel.class);
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_movie_detail, container, false);
        binding.setFragment(this);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        viewModel.detailMovie.observe(getViewLifecycleOwner(), detailMovie -> {
            movieItem = detailMovie;
            Dlog.d("movie info size:" + detailMovie.size());
            if(movieItem.size() != 0) {
                setMovieInfo();
                Network.showToast(getActivity());
            } else {
                Toast.makeText(getActivity(), getString(R.string.msg_no_data),
                        Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.commentList.observe(getViewLifecycleOwner(), _commentList -> {
            commentList = _commentList;
            if(commentList.size() != 0) {
                updateCommentList();
                Network.showToast(getActivity());
            } else {
                Toast.makeText(getActivity(), getString(R.string.msg_no_data),
                        Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MovieListActivity)requireActivity()).showOptionMenu(false);
    }

    private void setBasicMovieInfoFromBundle() {
        if(getArguments() != null) {
            //id = getArguments().getInt(getString(R.string.bun_id));
            App.getInstance().movieId = getArguments().getInt(getString(R.string.bun_id));
            id = App.getInstance().movieId;
            title = getArguments().getString(getString(R.string.bun_title));
            grade = getArguments().getInt(getString(R.string.bun_grade));
        }
    }

    private void setMovieInfo() {
        if(getArguments() != null) {
            // Glide.with(getContext()).load(movieItem.get(0).getImage()).into(binding.ivMoviePoster);
            binding.ivMovieRating.setImageResource(Utils.convertGradeToResId(movieItem.get(0).getGrade()));
            likeCount = movieItem.get(0).getLike();
            dislikeCount = movieItem.get(0).getDislike();
            binding.tvLikeCount.setText(String.valueOf(likeCount));
            binding.tvDislikeCount.setText(String.valueOf(dislikeCount));
            binding.rating.setRating(movieItem.get(0).getReviewerRating() / 2);

            DecimalFormat df = new DecimalFormat("###,###");
            binding.tvCumulativeAudience.setText(String.valueOf(df.format(movieItem.get(0).getAudience())));

            setGalleryAdapter();
        }
    }

    private void setGalleryAdapter() {
        galleryAdapter = new GalleryAdapter();
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

        galleryAdapter.setOnItemClickListener((holder, view, position) -> {
            String url = galleryAdapter.getItem(position).getUrl();

            if(Utils.isVideo(url)) { // 동영상일 경우
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } else { // 이미지일 경우
                Intent intent = new Intent(getActivity(), MovieGalleryActivity.class);
                intent.putExtra(getString(R.string.gallery_url), url);
                startActivity(intent);
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
        commentAdapter = new CommentAdapter();
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
        commentAdapter.setOnItemClickListener((holder, view, position) -> {
            addRecommendCountToServer(position);
            addRecommendCountToUI(position);
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

    public void onClickWriteCommentButton() {
        if(getActivity() != null && Network.isConnected()) {
            Intent intent = new Intent(getActivity(), CommentWriteActivity.class);
            intent.putExtra(getString(R.string.mov_id), App.getInstance().movieId);
            intent.putExtra(getString(R.string.mov_title), title);
            intent.putExtra(getString(R.string.mov_grade), grade);
            getActivity().startActivityForResult(intent, Constants.REQUEST_COMMENT_WRITE_CODE);
        } else {
            Toast.makeText(getActivity(), getString(R.string.msg_req_net),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickLoadAllButton() {
        Intent intent = new Intent(getActivity(), CommentListActivity.class);
        intent.putExtra(getString(R.string.mov_id), App.getInstance().movieId);
        intent.putExtra(getString(R.string.mov_title), title);
        intent.putExtra(getString(R.string.mov_rating), movieItem.get(0).getReviewerRating());
        intent.putExtra(getString(R.string.mov_grade), grade);
        startActivityForResult(intent, Constants.REQUEST_COMMENT_LIST_CODE);
    }
}
