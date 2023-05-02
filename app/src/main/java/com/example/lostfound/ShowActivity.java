package com.example.lostfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.lostfound.adapter.PostsAdapter;
import com.example.lostfound.dao.PostsDAO;
import com.example.lostfound.database.PostDatabase;
import com.example.lostfound.databinding.ActivityShowBinding;
import com.example.lostfound.entities.PostItem;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShowActivity extends AppCompatActivity {
    private ActivityShowBinding binding;
    PostsAdapter postsAdapter;
    PostDatabase postDatabase;
    PostsDAO postsDAO;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        postDatabase = Room.databaseBuilder(getApplicationContext(),
                PostDatabase.class, "lost-found.db").build();
        postsDAO = postDatabase.postsDAO();
        databaseWriteExecutor.execute(() -> {
            List<PostItem> postItemList = postsDAO.getAllPostItems();

            for (int i = 0; i < postItemList.size(); i++) {
                Log.v("post list", postItemList.get(i).getDescription() + postItemList.get(i).getDate());
            }

            postsAdapter = new PostsAdapter(postItemList);
            binding.postRecyclerLayout.setAdapter(postsAdapter);
            binding.postRecyclerLayout.setLayoutManager(new LinearLayoutManager(this));
        });

        binding.fab.setOnClickListener(view -> {
            Intent addNewPostIntent = new Intent(this, PostActivity.class);
            startActivity(addNewPostIntent);
        });
    }
}