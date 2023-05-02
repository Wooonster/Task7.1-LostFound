package com.example.lostfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.example.lostfound.dao.PostsDAO;
import com.example.lostfound.database.PostDatabase;
import com.example.lostfound.entities.PostItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailActivity extends AppCompatActivity {
    private com.example.lostfound.databinding.ActivityDetailBinding binding;
    PostDatabase postDatabase;
    PostsDAO postsDAO;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.lostfound.databinding.ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PostItem post = (PostItem) getIntent().getExtras().getSerializable("post_info");
        if (post.isPostType()) {
//            found
            binding.postType.setText("Found Report");
        } else {
            binding.postType.setText("Lost Report");
        }
        binding.postDescription.setText(post.getDescription());
        binding.postDate.setText(post.getDate());
        binding.postLocation.setText(post.getLocation());
        binding.postName.setText(post.getPhone());
        binding.postPhone.setText(post.getPhone());

        postDatabase = Room.databaseBuilder(getApplicationContext(),
                PostDatabase.class, "lost-found.db").build();
        postsDAO = postDatabase.postsDAO();
        binding.removeBtn.setOnClickListener(view -> {
            databaseWriteExecutor.execute(() -> {
                postsDAO.deletePost(post);
            });

            Intent backIntent = new Intent(this, ShowActivity.class);
            startActivity(backIntent);
        });

        binding.returnBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, ShowActivity.class));
        });
    }
}