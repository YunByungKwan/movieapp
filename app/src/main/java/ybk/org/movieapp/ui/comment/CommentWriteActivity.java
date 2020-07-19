package ybk.org.movieapp.ui.comment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.ActivityCommentWriteBinding;
import ybk.org.movieapp.util.Constants;

public class CommentWriteActivity extends AppCompatActivity {

    private ActivityCommentWriteBinding binding;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding();
        setMovieInfo();
    }

    private void dataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comment_write);
        binding.setActivity(this);
    }

    private void setMovieInfo() {
        Intent intent = getIntent();
        if(intent != null) {
            id = intent.getIntExtra(Constants.MOV_ID, 0);
            setTitle(intent.getStringExtra(Constants.MOV_TITLE));
            setGrade(intent.getIntExtra(Constants.MOV_GRADE, 0));
        }
    }

    private void setTitle(String title) {
        binding.tvMovieName.setText(title);
    }

    private void setGrade(int grade) {
        int resId = Constants.convertGradeToResId(grade);
        binding.ivMovieRating.setImageResource(resId);
    }

    /** 저장 버튼 클릭시 이벤트 */
    public void onClickSaveButton() {
        if(isEmptyContents()) {
            Toast.makeText(this, Constants.MSG_EMPTY, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra(Constants.COMM_ID, id);
            intent.putExtra(Constants.COMM_WRITER, "ByungKwan");
            intent.putExtra(Constants.COMM_TIME, Constants.getCurrentTime());
            intent.putExtra(Constants.COMM_RATING, binding.rating.getRating());
            intent.putExtra(Constants.COMM_CONT, binding.etContents.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    /** 본문이 비어있는지 판별 */
    boolean isEmptyContents() {
        String contents = binding.etContents.getText().toString();
        return contents.equals("");
    }

    /** 취소 버튼 클릭시 이벤트 */
    public void onClickCancelButton() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
