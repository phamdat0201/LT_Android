package com.example.a19446101_kt1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddAlbum, btnViewAlbum, btnManageSong;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddAlbum = (Button) findViewById(R.id.btnAddAlbum);
        btnViewAlbum = (Button) findViewById(R.id.btnViewAlbum);
        btnManageSong = (Button) findViewById(R.id.btnManageSong);

        btnAddAlbum.setOnClickListener(this);
        btnViewAlbum.setOnClickListener(this);
        btnViewAlbum.setOnClickListener(this);
        dbHelper = new DbHelper(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddAlbum:
                // Handle add album button click
                showAddAlbumDialog();
                break;
            case R.id.btnViewAlbum:
                // Handle view albums button click
                showAlbumList();
                break;
//            case R.id.btnManageSong:
//                // Handle manage songs button click
//                Intent manageSongsIntent = new Intent(MainActivity.this, ManageSongsActivity.class);
//                startActivity(manageSongsIntent);
//                break;
        }
    }
    private void showAlbumList() {
        Intent viewAlbumsIntent = new Intent(MainActivity.this, AlbumListActivity.class);
        startActivity(viewAlbumsIntent);

    }
    private void showAddAlbumDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_add_album);

        final EditText edtAlbumId = dialog.findViewById(R.id.etMaAlbum);
        final EditText edtAlbumName = dialog.findViewById(R.id.etTenAlbum);
        final Button btnSave = dialog.findViewById(R.id.btnSave);
        final Button btnDel = dialog.findViewById(R.id.btnXoaTrang);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String albumId = edtAlbumId.getText().toString().trim();
                String albumName = edtAlbumName.getText().toString().trim();

                // Lưu thông tin album vào database
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DbHelper.ALBUM_ID, albumId);
                values.put(DbHelper.ALBUM_NAME, albumName);
                db.insert(DbHelper.TABLE_ALBUM, null, values);
                db.close();

                // Đóng Dialog
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
}