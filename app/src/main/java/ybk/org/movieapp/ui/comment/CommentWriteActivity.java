package ybk.org.movieapp.ui.comment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.ActivityCommentWriteBinding;
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.util.Utils;

public class CommentWriteActivity extends AppCompatActivity {

    private ActivityCommentWriteBinding binding;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding();
        initializeMovie();
    }

    private void dataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comment_write);
        binding.setActivity(this);
    }

    private void initializeMovie() {
        Intent intent = getIntent();
        if(intent != null) {
            id = intent.getIntExtra(getString(R.string.mov_id), 0);
            setTitle(intent.getStringExtra(getString(R.string.mov_title)));
            setGrade(intent.getIntExtra(getString(R.string.mov_grade), 0));
        }
    }

    private void setTitle(String title) {
        binding.tvMovieName.setText(title);
    }

    private void setGrade(int grade) {
        int resId = Utils.convertGradeToResId(grade);
        binding.ivMovieRating.setImageResource(resId);
    }

    /** 저장 버튼 클릭시 이벤트 */
    public void onClickSaveButton() {
        if(isEmptyContents()) {
            Toast.makeText(this, getString(R.string.msg_empty), Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra(getString(R.string.comm_id), id);
            intent.putExtra(getString(R.string.comm_writer), "ByungKwan");
            intent.putExtra(getString(R.string.comm_time), Utils.getCurrentTime());
            intent.putExtra(getString(R.string.comm_rating), binding.rating.getRating());
            intent.putExtra(getString(R.string.comm_cont), binding.etContents.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    /** 본문이 비어있는지 판별 */
    private boolean isEmptyContents() {
        String contents = binding.etContents.getText().toString();
        return "".equals(contents);
    }

    /** 취소 버튼 클릭시 이벤트 */
    public void onClickCancelButton() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
