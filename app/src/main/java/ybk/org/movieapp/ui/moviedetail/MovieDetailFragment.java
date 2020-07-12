package ybk.org.movieapp.ui.moviedetail;

import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.List;
import ybk.org.movieapp.data.Comment;
import ybk.org.movieapp.data.DetailMovie;
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.databinding.FragmentMovieDetailBinding;
import ybk.org.movieapp.ui.comment.CommentAdapter;
import ybk.org.movieapp.ui.comment.CommentListActivity;
import ybk.org.movieapp.ui.comment.CommentWriteActivity;
import ybk.org.movieapp.R;

public class MovieDetailFragment extends Fragment {

    private FragmentMovieDetailBinding binding;
    private MovieDetailViewModel viewModel;
    private List<DetailMovie> movieItem;
    private List<Comment> comments;
    private int id;
    private int grade;
    private boolean likeState = false;
    private boolean dislikeState = false;
    private int likeCount = 15;
    private int dislikeCount = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        id = getArguments().getInt(Constants.BUNDLE_KEY_ID);
        grade = getArguments().getInt(Constants.BUNDLE_KEY_WATCHING_AGE);

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);

        Constants.loge("setValue전");
        viewModel._movieId.setValue(id);
        viewModel._limit.setValue(2);
        Constants.loge("setValue후");

        viewModel.init();

        viewModel.getDetailMovie().observe(getViewLifecycleOwner(), new Observer<List<DetailMovie>>() {
            @Override
            public void onChanged(List<DetailMovie> items) {
                movieItem = items;
                setSelectedMovieData();
            }
        });
        viewModel.getCommentList().observe(getViewLifecycleOwner(), new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> items) {
                comments = items;
                setInitCommentAdapter();
            }
        });

        setDataBinding(view);
        setInitLikeAndDisLikeCount();

        return view;
    }

    /** 데이터바인딩 설정 */
    private void setDataBinding(View view) {
        binding = DataBindingUtil.bind(view);

        if(binding != null) {
            binding.setFragment(this);
        } else {
            Log.e(Constants.TAG_MOVIE_DETAIL_FRAGMENT, "DataBinding is null.");
        }
    }

    /** 영화목록에서 선택한 영화에 대한 데이터를 받음 */
    private void setSelectedMovieData() {
        Constants.loge("call setSelectedMovieData");

        if(getArguments() != null) {
            Constants.logd("Arguments is not null.");

            Constants.loge("Image path: " + movieItem.get(0).getImage());
            Glide.with(getContext())
                    .load(movieItem.get(0).getThumb())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(binding.ivMoviePoster);
            binding.tvMovieTitle.setText(movieItem.get(0).getTitle());
//            binding.ivMoviePoster.setImageResource(
//                    getArguments().getInt(Constants.BUNDLE_KEY_SMALL_POSTER));
            binding.ivMovieRating.setImageResource(Constants.convertGradeToResId(movieItem.get(0).getGrade()));
            binding.tvReleaseDate.setText( movieItem.get(0).getDate());
            binding.tvGenre.setText(movieItem.get(0).getGenre());
            binding.tvRunningTime.setText(String.valueOf(movieItem.get(0).getDuration()));
            binding.tvLikeCount.setText(String.valueOf(movieItem.get(0).getLike()));
            binding.tvDislikeCount.setText(String.valueOf(movieItem.get(0).getDislike()));
            binding.tvReservationRateRanking.setText(
                    String.valueOf(movieItem.get(0).getReservationGrade()));
            binding.tvReservationRate.setText(String.valueOf(movieItem.get(0).getAudienceRating()));
            binding.tvGrade.setText(String.valueOf(movieItem.get(0).getReviewerRating()));
            binding.tvCumulativeAudience.setText(String.valueOf(movieItem.get(0).getAudience()));
            binding.tvStory.setText(movieItem.get(0).getSynopsis());
            binding.tvDirectorName.setText(movieItem.get(0).getDirector());
            binding.tvCasting.setText(movieItem.get(0).getActor());
        } else {
            Log.e(Constants.TAG_MOVIE_DETAIL_FRAGMENT, "getArguments() is null.");
        }
    }

    /** 좋아요, 싫어요 초기값 설정 */
    private void setInitLikeAndDisLikeCount() {
        binding.tvLikeCount.setText(String.valueOf(likeCount));
        binding.tvDislikeCount.setText(String.valueOf(dislikeCount));
    }

    /** Recyclerview Adapter를 설정 */
    private void setInitCommentAdapter() {
        CommentAdapter adapter = new CommentAdapter();

        for(int idx = 0; idx < comments.size(); idx++) {
            adapter.addItem(comments.get(idx));
        }

        binding.lvComment.setAdapter(adapter);
    }

    /** 좋아요 아이콘 클릭시 이벤트 */
    public void onClickLikeButton() {
        Log.e(Constants.TAG_MOVIE_DETAIL_FRAGMENT, "call onClickLikeButton()");

        if(!likeState) {
            increaseLikeCount();
        }else {
            decreaseLikeCount();
        }
    }

    /** 싫어요 아이콘 클릭시 이벤트 */
    public void onClickDisLikeButton() {
        Log.e(Constants.TAG_MOVIE_DETAIL_FRAGMENT, "call onClickDisLikeButton()");

        if(!dislikeState) {
            increaseDisLikeCount();
        }else {
            decreaseDisLikeCount();
        }
    }

    /** 좋아요 숫자 증가. 싫어요 아이콘이 선택되었을 경우, 싫어요 숫자 감소 */
    public void increaseLikeCount() {
        Log.e(Constants.TAG_MOVIE_DETAIL_FRAGMENT, "call increaseLikeCount()");

        likeCount += 1;
        binding.tvLikeCount.setText(String.valueOf(likeCount));
        likeState = true;
        binding.btnLike.setBackgroundResource(R.drawable.ic_thumb_up_selected);
        if(dislikeState) {
            decreaseDisLikeCount();
        }
    }

    /** 좋아요 숫자 감소 */
    public void decreaseLikeCount() {
        Log.e(Constants.TAG_MOVIE_DETAIL_FRAGMENT, "call decreaseLikeCount()");

        likeCount -= 1;
        binding.tvLikeCount.setText(String.valueOf(likeCount));
        likeState = false;
        binding.btnLike.setBackgroundResource(R.drawable.thumbs_up_selector);
    }

    /** 싫어요 숫자 증가. 좋아요 아이콘이 선택되었을 경우, 좋아요 숫자 감소 */
    public void increaseDisLikeCount() {
        Log.e(Constants.TAG_MOVIE_DETAIL_FRAGMENT, "call increaseDisLikeCount()");

        dislikeCount += 1;
        binding.tvDislikeCount.setText(String.valueOf(dislikeCount));
        dislikeState = true;
        binding.btnDislike.setBackgroundResource(R.drawable.ic_thumb_down_selected);
        if(likeState) {
            decreaseLikeCount();
        }
    }

    /** 싫어요 숫자 감소 */
    public void decreaseDisLikeCount() {
        Log.e(Constants.TAG_MOVIE_DETAIL_FRAGMENT, "call decreaseDisLikeCount()");

        dislikeCount -= 1;
        binding.tvDislikeCount.setText(String.valueOf(dislikeCount));
        dislikeState = false;
        binding.btnDislike.setBackgroundResource(R.drawable.thumbs_down_selector);
    }

    /**
     * 작성하기 버튼 클릭시 이벤트
     * - CommentWriteActivity로 이동 (Constants.REQUEST_COMMENT_WRITE_CODE)
     */
    public void onClickWriteCommentButton() {
        Log.e(Constants.TAG_MOVIE_DETAIL_FRAGMENT, "call onClickWriteCommentButton()");

        Intent intent = new Intent(getActivity(), CommentWriteActivity.class);
        intent.putExtra(Constants.KEY_TITLE, binding.tvMovieTitle.getText().toString());
        intent.putExtra(Constants.KEY_GRADE, grade);
        startActivityForResult(intent, Constants.REQUEST_COMMENT_WRITE_CODE);
    }

    /**
     * 모두보기 버튼 클릭시 이벤트
     * - CommentListActivity로 이동 (Constants.REQUEST_COMMENT_LIST_CODE)
     */
    public void onClickLoadAllButton() {
        Log.e(Constants.TAG_MOVIE_DETAIL_FRAGMENT, "call onClickLoadAllButton()");

        Intent intent = new Intent(getActivity(), CommentListActivity.class);
        intent.putExtra(Constants.KEY_ID, id);
        intent.putExtra(Constants.KEY_TITLE, binding.tvMovieTitle.getText().toString());
        intent.putExtra(Constants.KEY_RATING, binding.rating.getRating());
        intent.putExtra(Constants.KEY_GRADE, binding.tvGrade.getText().toString());
        startActivityForResult(intent, Constants.REQUEST_COMMENT_LIST_CODE);
    }

}
