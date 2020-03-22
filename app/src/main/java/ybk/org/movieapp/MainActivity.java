package ybk.org.movieapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;
import ybk.org.movieapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_COMMENT_WRITE_CODE = 101;

    static final int REQUEST_COMMENT_LIST_CODE = 102;

    ActivityMainBinding binding;

    boolean likeState = false;
    boolean dislikeState = false;

    int likeCount = 15;
    int dislikeCount = 1;

    CommentAdapter adapter;
    ArrayList<CommentParcelable> commentList = new ArrayList<CommentParcelable>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();

        initCommentAdapter();
    }

    /**
     * 레이아웃을 연결함(activity_main.xml)
     */
    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
    }

    /**
     * 초기 리스트뷰 어뎁터를 설정함
     */
    private void initCommentAdapter() {
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

    /**
     * 리스트뷰 어뎁터를 설정함
     */
    private void setCommentAdapter() {
        adapter = new CommentAdapter();

        for(int idx = 0; idx < 2; idx++) {
            adapter.addItem(commentList.get(idx));
        }

        binding.lvComment.setAdapter(adapter);
    }

    /**
     * 좋아요 아이콘 클릭시 이벤트
     */
    public void onClickLikeButton() {
        if(!likeState) {
            increaseLikeCount();
        }else {
            decreaseLikeCount();
        }
    }

    /**
     * 싫어요 아이콘 클릭시 이벤트
     */
    public void onClickDisLikeButton() {
        if(!dislikeState) {
            increaseDisLikeCount();
        }else {
            decreaseDisLikeCount();
        }
    }

    /**
     * 좋아요 숫자 증가. 싫어요 아이콘이 선택되었을 경우, 싫어요 숫자 감소
     */
    public void increaseLikeCount() {
        likeCount += 1;
        binding.tvLikeCount.setText(String.valueOf(likeCount));
        likeState = true;
        binding.btnLike.setBackgroundResource(R.drawable.ic_thumb_up_selected);
        if(dislikeState) {
            decreaseDisLikeCount();
        }
    }

    /**
     * 좋아요 숫자 감소
     */
    public void decreaseLikeCount() {
        likeCount -= 1;
        binding.tvLikeCount.setText(String.valueOf(likeCount));
        likeState = false;
        binding.btnLike.setBackgroundResource(R.drawable.thumbs_up_selector);
    }

    /**
     * 싫어요 숫자 증가. 좋아요 아이콘이 선택되었을 경우, 좋아요 숫자 감소
     */
    public void increaseDisLikeCount() {
        dislikeCount += 1;
        binding.tvDislikeCount.setText(String.valueOf(dislikeCount));
        dislikeState = true;
        binding.btnDislike.setBackgroundResource(R.drawable.ic_thumb_down_selected);
        if(likeState) {
            decreaseLikeCount();
        }
    }

    /**
     * 싫어요 숫자 감소
     */
    public void decreaseDisLikeCount() {
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
        Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
        intent.putExtra(getString(R.string.movie_name_text), binding.tvMovieName.getText().toString());
        startActivityForResult(intent, REQUEST_COMMENT_WRITE_CODE);
    }

    /**
     * 모두보기 버튼 클릭시 이벤트
     * - CommentListActivity로 이동 (REQUEST_COMMENT_LIST_CODE)
     */
    public void onClickLoadAllButton() {
        Intent intent = new Intent(getApplicationContext(), CommentListActivity.class);
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

    /**
     * 1. CommentWriteActivity에서 돌아왔을 경우
     * - RESULT_OK && REQUEST_COMMENT_WRITE_CODE: 한줄평을 목록에 추가
     * - RESULT_CANCELED                        : Toast 메시지 출력
     * 2. CommentListActivity에서 돌아왔을 경우
     * - RESULT_CANCELED                        : Toast 메시지 출력, 한줄평 리스트 업데이트
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_COMMENT_WRITE_CODE) {
                if(data != null) {
                    float rating =
                            data.getFloatExtra(getString(R.string.movie_rating_text), 0);
                    String contents = data.getStringExtra(getString(R.string.movie_contents_text));

                    CommentParcelable newItem = new CommentParcelable(
                            "kym71**",
                            "10분전",
                            rating,
                            contents,
                            0);

                    commentList.add(newItem);
                    adapter.addItem(newItem);
                    binding.lvComment.setAdapter(adapter);
                }
            }
        }else if(resultCode == RESULT_CANCELED) {
            if(requestCode == REQUEST_COMMENT_WRITE_CODE) {
                Toast.makeText(
                        this,
                        getString(R.string.comment_write_cancel_text),
                        Toast.LENGTH_SHORT
                ).show();
            }else if(requestCode == REQUEST_COMMENT_LIST_CODE) {
                Toast.makeText(
                        this,
                        getString(R.string.comment_list_cancel_text),
                        Toast.LENGTH_SHORT
                ).show();

                commentList =
                        data.getParcelableArrayListExtra(getString(R.string.comment_list_text));

                setCommentAdapter();
            }
        }
    }
}
