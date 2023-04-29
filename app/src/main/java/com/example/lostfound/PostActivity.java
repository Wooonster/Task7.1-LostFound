package com.example.lostfound;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;

import com.example.lostfound.databinding.ActivityPostBinding;
import com.example.lostfound.dbSettings.DBHelper;
import com.example.lostfound.entities.PostItem;

public class PostActivity extends AppCompatActivity {
    private ActivityPostBinding binding;
    // false->lost, true->found
    private boolean postType;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkboxUnique();
        // initialize db setting
        dbHelper = new DBHelper(getApplicationContext());

        binding.postBtn.setOnClickListener(view -> {
            PostItem newPost = new PostItem(1, postType, binding.nameEdit.getText().toString(),
                    binding.phoneEdit.getText().toString(), binding.descriptionEdit.getText().toString(),
                    binding.dateEdit.getText().toString(), binding.locationEdit.getText().toString());
            // TODO: insert newPost to DB
            long rowId = dbHelper.insertPost(newPost);
            Log.v("insert ID", String.valueOf(rowId));
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