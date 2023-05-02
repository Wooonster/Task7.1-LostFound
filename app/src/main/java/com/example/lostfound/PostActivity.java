package com.example.lostfound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.lostfound.dao.PostsDAO;
import com.example.lostfound.databinding.ActivityPostBinding;
import com.example.lostfound.database.PostDatabase;
import com.example.lostfound.entities.PostItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PostActivity extends AppCompatActivity {
    private ActivityPostBinding binding;
    // false->lost, true->found
    private boolean postType;
    PostDatabase postDatabase;
    PostsDAO postsDAO;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkboxUnique();
        // initialize db setting
        postDatabase = Room.databaseBuilder(getApplicationContext(),
                PostDatabase.class, "lost-found.db").build();
        postsDAO = postDatabase.postsDAO();

        binding.postBtn.setOnClickListener(view -> {
            PostItem newPost = new PostItem(postType, binding.nameEdit.getText().toString(),
                    binding.phoneEdit.getText().toString(), binding.descriptionEdit.getText().toString(),
                    binding.dateEdit.getText().toString(), binding.locationEdit.getText().toString());

            try {
                databaseWriteExecutor.execute(() -> {
                    postsDAO.insertNewPost(newPost);
                });
            } catch (Exception e) {
                Log.v("add exception", String.valueOf(e));
            }

            Intent toShowIntent = new Intent(getApplicationContext(), ShowActivity.class);
            startActivity(toShowIntent);
        });

    }

    private void checkboxUnique() {
        binding.lostCheck.setOnCheckedChangeListener((compoundButton, b) -> {
            binding.foundCheck.setEnabled(!b);
            if (b) postType = false;
        });

        binding.foundCheck.setOnCheckedChangeListener(((compoundButton, b) -> {
            binding.lostCheck.setEnabled(!b);
            if (b) postType = true;
        }));
    }
}