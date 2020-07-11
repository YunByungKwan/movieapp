package ybk.org.movieapp.ui.comment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.Objects;
import ybk.org.movieapp.Repository;
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.ActivityCommentListBinding;

public class CommentListActivity extends AppCompatActivity {

    ActivityCommentListBinding binding;

    private Repository repo;

    CommentAdapter adapter;
    ArrayList<CommentParcelable> comments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setDataBinding();

        setToolbar();

        setMovieInfoAndComments();

        initCommentAdapter();
    }

    /** 데이터바인딩 설정 */
    private void setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comment_list);
        binding.setActivity(this);
    }

    /** 툴바 연결 */
    private void setToolbar() {
        Toolbar mToolbar = findViewById(R.id.tb_back);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    /** 영화 정보(이름, 별점) 및 한줄평목록을 연결함 */
    private void setMovieInfoAndComments() {
        Intent intent = getIntent();
        if(intent != null) {
            int id = intent.getIntExtra("movie_id", -1);


            String movieName = intent.getStringExtra(getString(R.string.movie_name_text));
            float movieRating =
                    intent.getFloatExtra(getString(R.string.movie_rating_text), 0);
            String movieGrade = intent.getStringExtra(getString(R.string.movie_grade_text));

            comments = intent.getParcelableArrayListExtra(getString(R.string.comment_list_text));
            binding.tvMovieName.setText(movieName);
            binding.rating.setRating(movieRating);
            binding.tvRating.setText(movieGrade);
        } else {
            Log.e(Constants.TAG_COMMENT_LIST_ACTIVITY, "getIntent() is null.");
        }
    }

    /** 초기 리스트뷰 어뎁터를 설정함 */
    private void initCommentAdapter() {
        adapter = new CommentAdapter();

        for(CommentParcelable comment : comments) {
            //adapter.addItem(comment);
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
        intent.putExtra(getString(R.string.movie_name_text),
                binding.tvMovieName.getText().toString());
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
            intent.putParcelableArrayListExtra(
                    getString(R.string.comment_list_text),
                    comments
            );
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

                comments.add(newComment);
            } else {
                Log.e(Constants.TAG_COMMENT_LIST_ACTIVITY, "Data is null.");
            }

        }else {
            Log.e(Constants.TAG_COMMENT_LIST_ACTIVITY, "ActivityResult is failed.");
        }
    }
}
