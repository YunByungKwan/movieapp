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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import ybk.org.movieapp.data.Comment;
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.ActivityCommentListBinding;

public class CommentListActivity extends AppCompatActivity {

    private ActivityCommentListBinding binding;
    private CommentListViewModel viewModel;
    private int id;
    private String title;
    private int grade;
    private List<Comment> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding();
        setToolbar();
        initializeViewModelAndMovie();
        updateCommentList();

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
    }

    private void dataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comment_list);
        binding.setActivity(this);
    }

    private void setToolbar() {
        Toolbar mToolbar = findViewById(R.id.tb_back);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void initializeViewModelAndMovie() {
        Intent intent = getIntent();
        if(intent != null) {
            initializeViewModel(intent);
            setMovieInfo(intent);
        }
    }

    private void initializeViewModel(Intent intent) {
        viewModel = ViewModelProviders.of(this).get(CommentListViewModel.class);
        id = intent.getIntExtra(Constants.MOV_ID, -1);
        viewModel.movieId.setValue(id);
        viewModel.init();
    }

    private void setMovieInfo(Intent intent) {
        title = intent.getStringExtra(Constants.MOV_TITLE);
        grade = intent.getIntExtra(Constants.MOV_GRADE, 0);
        float rating = intent.getFloatExtra(Constants.MOV_RATING, 0);

        binding.tvMovieName.setText(title);
        binding.rating.setRating(rating / 2);
        binding.tvRating.setText(String.valueOf(rating));
        binding.ivMovieRating.setImageResource(Constants.convertGradeToResId(grade));
    }

    private void updateCommentList() {
        viewModel.getCommentAllList().observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> _commentList) {
                commentList = _commentList;
                setCommentListAdapter();
            }
        });
    }

    private void setCommentListAdapter() {
        CommentAdapter adapter = new CommentAdapter();
        for(Comment comment : commentList) {
            adapter.addItem(comment);
        }
        binding.lvComment.setAdapter(adapter);
    }

    /** 작성하기 버튼 클릭시 이벤트 */
    public void onClickWriteCommentButton() {
        Intent intent = new Intent(this, CommentWriteActivity.class);
        intent.putExtra(Constants.MOV_ID, id);
        intent.putExtra(Constants.MOV_TITLE, title);
        intent.putExtra(Constants.MOV_GRADE, grade);
        startActivityForResult(intent, Constants.REQUEST_COMMENT_WRITE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /** CommentWriteActivity에서 돌아왔을 경우 */
        if(requestCode == Constants.REQUEST_COMMENT_WRITE_CODE && resultCode == RESULT_OK) {
            if(data != null) {
                HashMap<String, Object> commentMap = new HashMap<>();
                commentMap.put(Constants.COMM_ID, data.getIntExtra(Constants.COMM_ID, 0));
                commentMap.put(Constants.COMM_WRITER, data.getStringExtra(Constants.COMM_WRITER));
                commentMap.put(Constants.COMM_TIME, data.getStringExtra(Constants.COMM_TIME));
                commentMap.put(Constants.COMM_RATING, data.getFloatExtra(Constants.COMM_RATING, 0));
                commentMap.put(Constants.COMM_CONT, data.getStringExtra(Constants.COMM_CONT));
                addComment(commentMap);
            }
        }
    }

    private void addComment(HashMap<String, Object> comment) {
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

    /** 뒤로 가기 버튼 클릭시 이벤트 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
