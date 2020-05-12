package ybk.org.movieapp.ui.moviedetail;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import ybk.org.movieapp.CommentAdapter;
import ybk.org.movieapp.CommentListActivity;
import ybk.org.movieapp.CommentParcelable;
import ybk.org.movieapp.CommentWriteActivity;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.FragmentMovieDetailBinding;

public class MovieDetailFragment extends Fragment {

    private static final int REQUEST_COMMENT_WRITE_CODE = 101;
    private static final int REQUEST_COMMENT_LIST_CODE = 102;
    private static final String TAG = "MovieDetailFragment";

    private MovieDetailViewModel mViewModel;
    private FragmentMovieDetailBinding binding;

    private boolean likeState = false;
    private boolean dislikeState = false;
    private int likeCount = 15;
    private int dislikeCount = 1;

    private CommentAdapter adapter;
    private ArrayList<CommentParcelable> commentList = new ArrayList<CommentParcelable>();

    public static MovieDetailFragment newInstance() {
        return new MovieDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        binding = DataBindingUtil.bind(view);
        binding.setFragment(this);

        addInitDataToCommentList();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
    }

    /** 리스트뷰 초기 데이터를 설정함 */
    private void addInitDataToCommentList() {
        commentList.add(new CommentParcelable(
                "kym71**",
                "10분전",
                5,
                "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.",
                0));

        commentList.add(new CommentParcelable(
                "kym71**",
                "10분전",
                5,
                "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.",
                0));

        setCommentAdapter();
    }

    /** 리스트뷰 어뎁터를 설정함 */
    private void setCommentAdapter() {
        adapter = new CommentAdapter();

        for(int idx = 0; idx < 2; idx++) {
            adapter.addItem(commentList.get(idx));
        }

        binding.lvComment.setAdapter(adapter);
    }

    /** 좋아요 아이콘 클릭시 이벤트 */
    public void onClickLikeButton() {
        Log.e(TAG, "onClickLikeButton");

        if(!likeState) {
            increaseLikeCount();
        }else {
            decreaseLikeCount();
        }
    }

    /** 싫어요 아이콘 클릭시 이벤트 */
    public void onClickDisLikeButton() {
        Log.e(TAG, "onClickDisLikeButton");

        if(!dislikeState) {
            increaseDisLikeCount();
        }else {
            decreaseDisLikeCount();
        }
    }

    /** 좋아요 숫자 증가. 싫어요 아이콘이 선택되었을 경우, 싫어요 숫자 감소 */
    public void increaseLikeCount() {
        Log.e(TAG, "increaseLikeCount");

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
        Log.e(TAG, "decreaseLikeCount");

        likeCount -= 1;
        binding.tvLikeCount.setText(String.valueOf(likeCount));
        likeState = false;
        binding.btnLike.setBackgroundResource(R.drawable.thumbs_up_selector);
    }

    /** 싫어요 숫자 증가. 좋아요 아이콘이 선택되었을 경우, 좋아요 숫자 감소 */
    public void increaseDisLikeCount() {
        Log.e(TAG, "increaseDisLikeCount");

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
        Log.e(TAG, "decreaseDisLikeCount");

        dislikeCount -= 1;
        binding.tvDislikeCount.setText(String.valueOf(dislikeCount));
        dislikeState = false;
        binding.btnDislike.setBackgroundResource(R.drawable.thumbs_down_selector);
    }

    /**
     * 작성하기 버튼 클릭시 이벤트
     * - CommentWriteActivity로 이동 (REQUEST_COMMENT_WRITE_CODE)
     */
    public void onClickWriteCommentButton() {
        Log.e(TAG, "onClickWriteCommentButton");

        Intent intent = new Intent(getActivity(), CommentWriteActivity.class);
        intent.putExtra(getString(R.string.movie_name_text), binding.tvMovieName.getText().toString());
        startActivityForResult(intent, REQUEST_COMMENT_WRITE_CODE);
    }

    /**
     * 모두보기 버튼 클릭시 이벤트
     * - CommentListActivity로 이동 (REQUEST_COMMENT_LIST_CODE)
     */
    public void onClickLoadAllButton() {
        Log.e(TAG, "onClickLoadAllButton");

        Intent intent = new Intent(getActivity(), CommentListActivity.class);
        intent.putExtra(getString(R.string.movie_name_text),
                binding.tvMovieName.getText().toString());
        intent.putExtra(getString(R.string.movie_rating_text),
                binding.rating.getRating());
        intent.putExtra(getString(R.string.movie_grade_text),
                binding.tvGrade.getText().toString());
        intent.putParcelableArrayListExtra(getString(R.string.comment_list_text),
                commentList);
        startActivityForResult(intent, REQUEST_COMMENT_LIST_CODE);
    }

}
