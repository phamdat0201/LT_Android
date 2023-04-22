package com.example.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView cacheListView;
    private CacheAdapter cacheAdapter;
    private List<Cache> cacheList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cacheListView = findViewById(R.id.cache_list);

        // Khởi tạo danh sách các Cache files
        cacheList = new ArrayList<>();
        cacheList.add(new Cache("Cache 1", "Nội dung Cache 1", "/path/to/cache1"));
        cacheList.add(new Cache("Cache 2", "Nội dung Cache 2", "/path/to/cache2"));
        cacheList.add(new Cache("Cache 3", "Nội dung Cache 3", "/path/to/cache3"));

        // Tạo CacheAdapter và đưa dữ liệu vào ListView
        cacheAdapter = new CacheAdapter(this, R.layout.activity_main, cacheList);
        cacheListView.setAdapter(cacheAdapter);

        // Xử lý sự kiện chọn một Cache trên ListView
        cacheListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cache selectedCache = cacheList.get(position);

                // Mở Activity để xem chi tiết nội dung của Cache
                Intent intent = new Intent(MainActivity.this, CacheDetailActivity.class);
                intent.putExtra("title", selectedCache.getTitle());
                intent.putExtra("content", selectedCache.getContent());
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click nút Xóa từng Cache
        Button deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị AlertDialog xác nhận xóa Cache
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Bạn có chắc muốn xóa Cache này?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Cache selectedCache = cacheList.get(cacheListView.getCheckedItemPosition());
                                cacheList.remove(selectedCache);
                                cacheAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Không", null)
                        .show();
            }
        });

        // Xử lý sự kiện click nút Xóa toàn bộ Cache
        Button deleteAllButton = findViewById(R.id.clear_button);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị AlertDialog xác nhận xóa toàn bộ Cache
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Bạn có chắc muốn xóa toàn bộ Cache?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cacheList.clear();
                                cacheAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Không", null)
                        .show();
            }
        });
    }
}