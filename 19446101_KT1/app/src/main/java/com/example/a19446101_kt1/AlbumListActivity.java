package com.example.a19446101_kt1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AlbumListActivity extends AppCompatActivity {

    private ListView albumListView;
    private AlbumAdapter albumAdapter;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_list_layout);

        dbHelper = new DbHelper(this);


        // Ánh xạ ListView từ layout
        albumListView = findViewById(R.id.album_list_view);

        // Lấy danh sách Album từ cơ sở dữ liệu
        List<Album> albumList = getAlbumListFromDatabase();

        // Sử dụng Adapter để hiển thị danh sách Album trên ListView
        albumAdapter = new AlbumAdapter(this, albumList);
        albumListView.setAdapter(albumAdapter);

        // Xử lý sự kiện khi người dùng chọn một Album trong danh sách
        albumListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy Album tại vị trí được chọn
                Album selectedAlbum = albumAdapter.getItem(position);

                // Chuyển sang màn hình chỉnh sửa Album
                showDetailAlbum();
            }
        });
    }
    private void showDetailAlbum() {
        final Dialog dialog = new Dialog(AlbumListActivity.this);
        dialog.setContentView(R.layout.detail_album);

        final EditText edtAlbumId = dialog.findViewById(R.id.etMaAlbum);
        final EditText edtAlbumName = dialog.findViewById(R.id.etTenAlbum);
        final Button btnUpdate = dialog.findViewById(R.id.btnUpdate);
        final Button btnDel = dialog.findViewById(R.id.btnXoaTrang);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String albumId = getIntent().getStringExtra("albumId");
                String albumName = getIntent().getStringExtra("albumName");

                EditText albumIdEditText = findViewById(R.id.etMaAlbum);
                EditText albumNameEditText = findViewById(R.id.etTenAlbum);

                albumIdEditText.setText(albumId);
                albumNameEditText.setText(albumName);

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DbHelper.ALBUM_NAME, albumName);
                db.update(DbHelper.TABLE_ALBUM, values, DbHelper.ALBUM_ID + " = ?", new String[]{albumId});
                db.close();

                dialog.dismiss();
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtAlbumId.setText("");
                edtAlbumName.setText("");
                edtAlbumId.requestFocus();
            }
        });


        dialog.show();
    }
    private ArrayList<Album> getAlbumListFromDatabase() {
        ArrayList<Album> albumList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_ALBUM, null);

        if (cursor.moveToFirst()) {
            do {

                @SuppressLint("Range") String albumID = cursor.getString(cursor.getColumnIndex(DbHelper.ALBUM_ID));
                @SuppressLint("Range") String albumName = cursor.getString(cursor.getColumnIndex(DbHelper.ALBUM_NAME));
                Album album = new Album(albumID, albumName);
                albumList.add(album);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return albumList;
    }

    private static class AlbumAdapter extends ArrayAdapter<Album> {

        private Context context;

        public AlbumAdapter(Context context, List<Album> albumList) {
            super(context, R.layout.album_list_item, albumList);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder holder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.album_list_item, parent, false);
                holder = new ViewHolder();
                holder.sttTextView = view.findViewById(R.id.album_index);
                holder.maAlbumTextView = view.findViewById(R.id.album_id);
                holder.tenAlbumTextView = view.findViewById(R.id.album_name);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            // Lấy Album tại vị trí được chọn
            Album album = getItem(position);

            // Thiết lập giá trị cho các TextView trong CustomLayout
            holder.sttTextView.setText(String.valueOf(position + 1));
            holder.maAlbumTextView.setText(album.getAlbumId());
            holder.tenAlbumTextView.setText(album.getAlbumName());

            return view;
        }

        private static class ViewHolder {
            TextView sttTextView;
            TextView maAlbumTextView;
            TextView tenAlbumTextView;
        }
    }
}

