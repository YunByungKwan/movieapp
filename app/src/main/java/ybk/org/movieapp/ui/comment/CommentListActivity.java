package ybk.org.movieapp.ui.comment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import ybk.org.movieapp.R;
import ybk.org.movieapp.adapter.CommentAdapter;
import ybk.org.movieapp.adapter.CommentItem;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.databinding.ActivityCommentListBinding;
import ybk.org.movieapp.ui.commentwrite.CommentWriteActivity;
import ybk.org.movieapp.util.App;
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.util.Dlog;
import ybk.org.movieapp.util.Network;
import ybk.org.movieapp.util.Utils;

public class CommentListActivity extends AppCompatActivity {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    @Inject
    public CommentListViewModel viewModel;

    private ActivityCommentListBinding binding;
    private CommentAdapter adapter;
    private List<Comment> commentList;
    private int id;
    private String title;
    private int grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getInstance().appComponent()
                .commentListComponent().create().inject(this);
        super.onCreate(savedInstanceState);
        // viewModel = new ViewModelProvider(this, viewModelFactory).get(CommentListViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comment_list);
        binding.setActivity(this);

        Toolbar mToolbar = findViewById(R.id.tb_back);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if(intent != null) {
            // id = intent.getIntExtra(getString(R.string.mov_id), -1);
            id = App.getInstance().movieId;
            grade = intent.getIntExtra(getString(R.string.mov_grade), 0);
            title = intent.getStringExtra(getString(R.string.mov_title));
            binding.ivMovieRating.setImageResource(Utils.convertGradeToResId(grade));
        }

        viewModel.commentList.observe(this, _commentList -> {
            commentList = _commentList;
            Dlog.d("commentList size=" + commentList.size());
            updateCommentList();
        });
    }

    private void updateCommentList() {
        Dlog.d("updateCommentList()");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        binding.rvComment.setLayoutManager(layoutManager);
        adapter = new CommentAdapter();
        for(Comment comment : commentList) {
            adapter.addItem(new CommentItem(
                    comment.getId(),
                    comment.getWriter(),
                    comment.getTime(),
                    comment.getRating(),
                    comment.getContents(),
                    comment.getRecommend()));
        }
        binding.rvComment.setAdapter(adapter);

        // 댓글 리스트 아이템 클릭 이벤트
        adapter.setOnItemClickListener((holder, view, position) -> {
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

    public void onClickWriteCommentButton() {
        if(Network.isConnected()) {
            Intent intent = new Intent(this, CommentWriteActivity.class);
            intent.putExtra(getString(R.string.mov_id), id);
            intent.putExtra(getString(R.string.mov_title), title);
            intent.putExtra(getString(R.string.mov_grade), grade);
            startActivityForResult(intent, Constants.REQUEST_COMMENT_WRITE_CODE);
        } else {
            Toast.makeText(this, getString(R.string.msg_req_net),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /** CommentWriteActivity에서 돌아왔을 경우 */
        if(requestCode == Constants.REQUEST_COMMENT_WRITE_CODE && resultCode == RESULT_OK) {
            if(data != null) {
                HashMap<String, Object> commentMap = new HashMap<>();
                commentMap.put(getString(R.string.comm_id), data.getIntExtra(getString(R.string.comm_id), 0));
                commentMap.put(getString(R.string.comm_writer), data.getStringExtra(getString(R.string.comm_writer)));
                commentMap.put(getString(R.string.comm_time), data.getStringExtra(getString(R.string.comm_time)));
                commentMap.put(getString(R.string.comm_rating), data.getFloatExtra(getString(R.string.comm_rating), 0));
                commentMap.put(getString(R.string.comm_cont), data.getStringExtra(getString(R.string.comm_cont)));
                addComment(commentMap);
            }
        }
    }

    /** 댓글을 서버와 화면에 보여줄 list에 추가함 */
    private void addComment(HashMap<String, Object> comment) {
        viewModel.addComment(comment);
        addCommentToList(comment);
    }

    /** 추가한 댓글을 list에 추가함 */
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
