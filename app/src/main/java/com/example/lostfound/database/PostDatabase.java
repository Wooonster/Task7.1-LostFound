package com.example.lostfound.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lostfound.dao.PostsDAO;
import com.example.lostfound.entities.PostItem;

@Database(entities = {PostItem.class}, version = 1)
public abstract class PostDatabase extends RoomDatabase {
    public abstract PostsDAO postsDAO();
}
