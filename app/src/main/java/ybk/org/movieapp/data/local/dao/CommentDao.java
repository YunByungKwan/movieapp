package ybk.org.movieapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import ybk.org.movieapp.data.local.entity.Comment;

@Dao
public interface CommentDao {

    @Query("SELECT * FROM comment WHERE movieId = (:id)")
    List<Comment> getCommentList(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Comment comment);

}
