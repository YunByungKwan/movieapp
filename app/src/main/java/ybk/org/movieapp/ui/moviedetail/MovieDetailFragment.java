package ybk.org.movieapp.ui.moviedetail;

import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.databinding.FragmentMovieDetailBinding;
import ybk.org.movieapp.ui.comment.CommentAdapter;
import ybk.org.movieapp.ui.comment.CommentListActivity;
import ybk.org.movieapp.ui.comment.CommentWriteActivity;
import ybk.org.movieapp.R;
import ybk.org.movieapp.util.Network;

public class MovieDetailFragment extends Fragment {

    private FragmentMovieDetailBinding binding;
    private MovieDetailViewModel viewModel;
    private List<DetailMovie> movieItem;
    private List<Comment> commentList;
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

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        viewModel.movieId.setValue(id);
        viewModel.limit.setValue(numOfComment);
        viewModel.init();
        viewModel.getDetailMovie().observe(getViewLifecycleOwner(), new Observer<List<DetailMovie>>() {
            @Override
            public void onChanged(List<DetailMovie> _movieInfo) {
                movieItem = _movieInfo;
                if(movieItem.size() != 0) {
                    setMovieInfo();
                    Network.showToast(getActivity());
                } else {
                    Toast.makeText(getActivity(), Constants.MSG_NO_DATA, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getActivity(), Constants.MSG_NO_DATA, Toast.LENGTH_SHORT).show();
                }
            }
        });

        dataBinding(view);

        // 댓글 리스트 아이템 클릭 이벤트
        binding.lvComment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, Object> param = new HashMap<>();
                param.put("review_id", commentList.get(position).getId());
                param.put("writer", commentList.get(position).getWriter());
                viewModel.recommendComment(param);

                // UI 변경
                int currentRecommendCnt = commentList.get(position).getRecommend();
                commentList.get(position).setRecommend(++currentRecommendCnt);
                updateCommentList();
            }
        });

        return view;
    }

    private void setBasicMovieInfoFromBundle() {
        if(getArguments() != null) {
            Constants.loge("getArguments() is not null!!");

            id = getArguments().getInt(Constants.BUN_ID);
            title = getArguments().getString(Constants.BUN_TITLE);
            grade = getArguments().getInt(Constants.BUN_GRADE);

            Constants.loge("id:" + id + " title: " + title + "grade: " + grade);
        }
    }

    private void setMovieInfo() {
        if(getArguments() != null) {
            Glide.with(getContext())
                    .load(movieItem.get(0).getImage())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(binding.ivMoviePoster);
            binding.tvMovieTitle.setText(movieItem.get(0).getTitle());
            binding.ivMovieRating.setImageResource(Constants.convertGradeToResId(movieItem.get(0).getGrade()));
            binding.tvReleaseDate.setText( movieItem.get(0).getDate());
            binding.tvGenre.setText(movieItem.get(0).getGenre());
            binding.tvRunningTime.setText(String.valueOf(movieItem.get(0).getDuration()));
            likeCount = movieItem.get(0).getLike();
            dislikeCount = movieItem.get(0).getDislike();
            binding.tvLikeCount.setText(String.valueOf(likeCount));
            binding.tvDislikeCount.setText(String.valueOf(dislikeCount));
            binding.tvReservationRateRanking.setText(
                    String.valueOf(movieItem.get(0).getReservationGrade()));
            binding.tvReservationRate.setText(String.valueOf(movieItem.get(0).getAudienceRating()));
            binding.rating.setRating(movieItem.get(0).getReviewerRating() / 2);
            binding.tvGrade.setText(String.valueOf(movieItem.get(0).getReviewerRating()));
            DecimalFormat df = new DecimalFormat("###,###");
            binding.tvCumulativeAudience.setText(String.valueOf(df.format(movieItem.get(0).getAudience())));
            binding.tvStory.setText(movieItem.get(0).getSynopsis());
            binding.tvDirectorName.setText(movieItem.get(0).getDirector());
            binding.tvCasting.setText(movieItem.get(0).getActor());
        }
    }

    /** MovieListActivity에서 호출됨 */
    public void addComment(HashMap<String, Object> comment) {
        viewModel.addComment(comment);
        addCommentToList(comment);
    }

    private void addCommentToList(HashMap<String, Object> commentMap) {
        Comment comment = new Comment();
        comment.setId((Integer) commentMap.get(Constants.COMM_ID));
        comment.setWriter((String) commentMap.get(Constants.COMM_WRITER));
        comment.setTime((String) commentMap.get(Constants.COMM_TIME));
        comment.setRating((Float) commentMap.get(Constants.COMM_RATING));
        comment.setContents((String) commentMap.get(Constants.COMM_CONT));
        comment.setRecommend(0);
        commentList.add(0, comment);

        viewModel.commentList.setValue(commentList);
    }

    private void updateCommentList() {
        CommentAdapter adapter = new CommentAdapter();
        for(int i = 0; i < numOfComment; i++) {
            adapter.addItem(commentList.get(i));
        }
        binding.lvComment.setAdapter(adapter);
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
        param.put(Constants.COMM_ID, id);
        param.put(Constants.POST_LIKE, "Y");
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
        param.put(Constants.COMM_ID, id);
        param.put(Constants.POST_LIKE, "N");
        viewModel.addLikeDisLike(param);

        binding.tvLikeCount.setText(String.valueOf(--likeCount));
        isPressedLike = false;
        binding.btnLike.setBackgroundResource(R.drawable.thumbs_up_selector);
    }

    /** 싫어요 숫자 증가. 좋아요 아이콘이 선택되었을 경우, 좋아요 숫자 감소 */
    public void incrDisLike() {
        HashMap<String, Object> param = new HashMap<>();
        param.put(Constants.COMM_ID, id);
        param.put(Constants.POST_DISLIKE, "Y");
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
        param.put(Constants.COMM_ID, id);
        param.put(Constants.POST_DISLIKE, "N");
        viewModel.addLikeDisLike(param);

        binding.tvDislikeCount.setText(String.valueOf(--dislikeCount));
        isPressedDisLike = false;
        binding.btnDislike.setBackgroundResource(R.drawable.thumbs_down_selector);
    }

    /** 작성하기 버튼 클릭시 이벤트 */
    public void onClickWriteCommentButton() {
        if(getActivity() != null && Network.isConnected()) {
            Intent intent = new Intent(getActivity(), CommentWriteActivity.class);
            intent.putExtra(Constants.MOV_ID, id);
            intent.putExtra(Constants.MOV_TITLE, title);
            intent.putExtra(Constants.MOV_GRADE, grade);
            getActivity().startActivityForResult(intent, Constants.REQUEST_COMMENT_WRITE_CODE);
        } else {
            Toast.makeText(getActivity(), Constants.MSG_REQ_NET, Toast.LENGTH_SHORT).show();
        }
    }

    /** 모두보기 버튼 클릭시 이벤트 */
    public void onClickLoadAllButton() {
        Intent intent = new Intent(getActivity(), CommentListActivity.class);
        intent.putExtra(Constants.MOV_ID, id);
        intent.putExtra(Constants.MOV_TITLE, title);
        intent.putExtra(Constants.MOV_RATING, movieItem.get(0).getReviewerRating());
        intent.putExtra(Constants.MOV_GRADE, grade);
        startActivityForResult(intent, Constants.REQUEST_COMMENT_LIST_CODE);
    }
}
