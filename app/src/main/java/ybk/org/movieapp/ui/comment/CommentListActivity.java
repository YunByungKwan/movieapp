package ybk.org.movieapp.ui.comment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import java.util.List;
import java.util.Objects;
import ybk.org.movieapp.data.Comment;
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.ActivityCommentListBinding;

public class CommentListActivity extends AppCompatActivity {

    private ActivityCommentListBinding binding;
    private CommentListViewModel viewModel;
    CommentAdapter adapter;
    private int id;
    private String title;
    private List<Comment> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding();
        initializeToolbar();
        initializeViewModelAndMovie();
        updateCommentList();
    }

    private void dataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comment_list);
        binding.setActivity(this);
    }

    private void initializeToolbar() {
        Toolbar mToolbar = findViewById(R.id.tb_back);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void initializeViewModelAndMovie() {
        Intent intent = getIntent();
        if(intent != null) {
            initializeViewModel(intent);
            initializeMovie(intent);
        }
    }

    private void initializeViewModel(Intent intent) {
        viewModel = ViewModelProviders.of(this).get(CommentListViewModel.class);
        id = intent.getIntExtra(Constants.KEY_ID, -1);
        viewModel._movieId.setValue(id);
        viewModel.init();
    }

    private void initializeMovie(Intent intent) {
        title = intent.getStringExtra(Constants.KEY_TITLE);
        binding.tvMovieName.setText(title);
        binding.rating.setRating(intent.getFloatExtra(Constants.KEY_RATING, 0));
        binding.tvRating.setText(intent.getStringExtra(Constants.KEY_GRADE));
        binding.ivMovieRating.setImageResource(Constants.convertGradeToResId(intent.getIntExtra(Constants.KEY_GRADE, 0)));
    }

    private void updateCommentList() {
        viewModel.getCommentAllList().observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                commentList = comments;
                setCommentListAdapter();
            }
        });
    }

    private void setCommentListAdapter() {
        adapter = new CommentAdapter();

        for(Comment comment : commentList) {
            adapter.addItem(comment);
        }

        binding.lvComment.setAdapter(adapter);
    }

    /**
     * 작성하기 버튼 클릭시 이벤트
     * - CommentWriteActivity로 이동 (REQUEST_COMMENT_WRITE_CODE)
     * startActivityForResult 사용
     */
    public void onClickWriteCommentButton() {
        Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
        intent.putExtra(Constants.KEY_ID, id);
        intent.putExtra(Constants.KEY_TITLE, title);
        startActivityForResult(intent, Constants.REQUEST_COMMENT_WRITE_CODE);
    }

    /**
     * 뒤로 가기 버튼 클릭시 이벤트
     * - MainActivity로 이동 (RESULT_CANCELED)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent();
//            intent.putParcelableArrayListExtra(
//                    getString(R.string.comment_list_text),
//                    comments
//            );
            setResult(RESULT_CANCELED, intent);
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * CommentWriteActivity에서 돌아왔을 경우
     * - RESULT_OK && REQUEST_COMMENT_WRITE_CODE: 한줄평을 목록에 추가
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Constants.REQUEST_COMMENT_WRITE_CODE && resultCode == RESULT_OK) {
            if(data != null) {
                float rating = data.getFloatExtra(getString(R.string.movie_rating_text), 0);
                String contents = data.getStringExtra(getString(R.string.movie_contents_text));

                CommentParcelable newComment = new CommentParcelable(
                        "kym71**",
                        "10분전",
                        rating,
                        contents,
                        0);

                //adapter.addItem(newComment);
                binding.lvComment.setAdapter(adapter);

                // comments.add(newComment);
            } else {
                Log.e(Constants.TAG_COMMENT_LIST_ACTIVITY, "Data is null.");
            }

        }else {
            Log.e(Constants.TAG_COMMENT_LIST_ACTIVITY, "ActivityResult is failed.");
        }
    }
}
