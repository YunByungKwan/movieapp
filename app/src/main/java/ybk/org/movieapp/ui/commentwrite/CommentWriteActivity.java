package ybk.org.movieapp.ui.commentwrite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.ActivityCommentWriteBinding;
import ybk.org.movieapp.util.App;
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.util.Utils;

public class CommentWriteActivity extends AppCompatActivity {

    private ActivityCommentWriteBinding binding;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_comment_write);
        binding.setActivity(this);

        Intent intent = getIntent();
        if(intent != null) {
            id = intent.getIntExtra(getString(R.string.mov_id), 0);

            // Set grade
            int grade = intent.getIntExtra(getString(
                    R.string.mov_grade), 0);
            int resId = Utils.convertGradeToResId(grade);
            binding.ivMovieRating.setImageResource(resId);
        }
    }

    public void onClickSaveButton() {
        String contents = binding.etContents.getText().toString();
        if("".equals(contents)) {
            Toast.makeText(
                    this,
                    getString(R.string.msg_empty),
                    Toast.LENGTH_SHORT).show();
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

    public void onClickCancelButton() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
