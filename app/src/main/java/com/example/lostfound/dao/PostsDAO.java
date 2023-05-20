package com.example.lostfound.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.lostfound.entities.PostItem;

import java.util.List;

@Dao
public interface PostsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNewPost(PostItem postItem);

    @Query("SELECT * FROM post_items")
    List<PostItem> getAllPostItems();

    @Delete
    void deletePost(PostItem postItem);
}
