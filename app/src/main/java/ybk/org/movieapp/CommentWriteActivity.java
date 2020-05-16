package ybk.org.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import ybk.org.movieapp.databinding.ActivityCommentWriteBinding;

public class CommentWriteActivity extends AppCompatActivity {

    private static final String TAG = "CommentWriteActivity";

    ActivityCommentWriteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setDataBinding();

        setMovieName();
    }

    /** 데이터바인딩 설정 */
    private void setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comment_write);
        binding.setActivity(this);
    }

    /** 영화 제목 설정 */
    private void setMovieName() {
        Intent intent = getIntent();
        if(intent != null) {
            binding.tvMovieName.setText(intent.getStringExtra(getString(R.string.movie_name_text)));
        } else {
            Log.e(TAG, "getIntent() is null.");
        }
    }
    /**
     * 저장 버튼 클릭시 이벤트
     * - MainActivity or CommentListActivity 로 이동 (RESULT_OK)
     */
    public void onClickSaveButton() {
        float rating = binding.rating.getRating();
        String contents = binding.etContents.getText().toString();

        if(contents.equals("")) { // 본문을 입력하지 않았을 경우
            Toast.makeText(
                    this,
                    getString(R.string.comment_empty_text),
                    Toast.LENGTH_SHORT).show();
        }else { // 본문을 입력했을 경우
            Intent intent = new Intent();
            intent.putExtra(getString(R.string.movie_rating_text), rating);
            intent.putExtra(getString(R.string.movie_contents_text), contents);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    /**
     * 취소 버튼 클릭시 이벤트
     * - MainActivity or CommentListActivity 로 이동 (RESULT_CANCELED)
     */
    public void onClickCancelButton() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
