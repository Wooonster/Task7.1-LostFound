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

//        add lost/found advertise
        binding.reportBtn.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), PostActivity.class));
        });

//        check all lost/found advertises
        binding.showAllBtn.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ShowActivity.class));
        });

//        show all advertises on Map
        binding.showOnMapBtn.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MapActivity.class));
        });
    }
}