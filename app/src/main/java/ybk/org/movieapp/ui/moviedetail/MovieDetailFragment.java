package ybk.org.movieapp.ui.moviedetail;

import androidx.databinding.DataBindingUtil;
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
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.databinding.FragmentMovieDetailBinding;
import ybk.org.movieapp.ui.comment.CommentAdapter;
import ybk.org.movieapp.ui.comment.CommentListActivity;
import ybk.org.movieapp.ui.comment.CommentParcelable;
import ybk.org.movieapp.ui.comment.CommentWriteActivity;
import ybk.org.movieapp.R;

public class MovieDetailFragment extends Fragment {

    private FragmentMovieDetailBinding binding;

    private boolean likeState = false;
    private boolean dislikeState = false;
    private int likeCount = 15;
    private int dislikeCount = 1;

    private ArrayList<CommentParcelable> comments = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.e(Constants.TAG_MOVIE_DETAIL_FRAGMENT, "call onCreateView()");

        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        setDataBinding(view);

        setSelectedMovieData();

        setInitLikeAndDisLikeCount();

        addInitDataToCommentList();

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
        if(getArguments() != null) {
            binding.ivMoviePoster
                    .setImageResource(getArguments().getInt(Constants.BUNDLE_KEY_SMALL_POSTER));
            binding.tvMovieTitle.setText(getArguments().getString(Constants.BUNDLE_KEY_TITLE));
        } else {
            Log.e(Constants.TAG_MOVIE_DETAIL_FRAGMENT, "getArguments() is null.");
        }
    }

    /** 좋아요, 싫어요 초기값 설정 */
    private void setInitLikeAndDisLikeCount() {
        binding.tvLikeCount.setText(String.valueOf(likeCount));
        binding.tvDislikeCount.setText(String.valueOf(dislikeCount));
    }

    /** 초기 댓글 데이터를 댓글 ArrayList에 추가 */
    private void addInitDataToCommentList() {
        comments.add(new CommentParcelable("kym71**", "10분전", 5,
                "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", 0));

        comments.add(new CommentParcelable("kym71**", "10분전", 5,
                "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", 0));

        setInitCommentAdapter();
    }

    /** Recyclerview Adapter를 설정 */
    private void setInitCommentAdapter() {
        CommentAdapter adapter = new CommentAdapter();

        for(int idx = 0; idx < 2; idx++) {
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
        intent.putExtra(getString(R.string.movie_name_text), binding.tvMovieTitle.getText().toString());
        startActivityForResult(intent, Constants.REQUEST_COMMENT_WRITE_CODE);
    }

    /**
     * 모두보기 버튼 클릭시 이벤트
     * - CommentListActivity로 이동 (Constants.REQUEST_COMMENT_LIST_CODE)
     */
    public void onClickLoadAllButton() {
        Log.e(Constants.TAG_MOVIE_DETAIL_FRAGMENT, "call onClickLoadAllButton()");

        Intent intent = new Intent(getActivity(), CommentListActivity.class);
        intent.putExtra(getString(R.string.movie_name_text),
                binding.tvMovieTitle.getText().toString());
        intent.putExtra(getString(R.string.movie_rating_text),
                binding.rating.getRating());
        intent.putExtra(getString(R.string.movie_grade_text),
                binding.tvGrade.getText().toString());
        intent.putParcelableArrayListExtra(getString(R.string.comment_list_text),
                comments);
        startActivityForResult(intent, Constants.REQUEST_COMMENT_LIST_CODE);
    }

}
