package com.example.bai2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CacheDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cache_detail);

        TextView titleView = findViewById(R.id.cache_title);
        TextView contentView = findViewById(R.id.cache_content);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        titleView.setText(title);
        contentView.setText(content);
    }
}


