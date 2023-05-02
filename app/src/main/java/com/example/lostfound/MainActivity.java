package com.example.lostfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.lostfound.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.reportBtn.setOnClickListener(view -> {
            Intent toReportIntent = new Intent(getApplicationContext(), PostActivity.class);
            startActivity(toReportIntent);
        });

        binding.showAllBtn.setOnClickListener(view -> {
            Intent toShowIntent = new Intent(getApplicationContext(), ShowActivity.class);
            startActivity(toShowIntent);
        });
    }
}