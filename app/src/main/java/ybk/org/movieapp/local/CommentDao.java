package ybk.org.movieapp.local;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CommentDao {

    @Query("SELECT * FROM comment WHERE id = (:id)")
    CommentEntity getCommentList(int id);

}
