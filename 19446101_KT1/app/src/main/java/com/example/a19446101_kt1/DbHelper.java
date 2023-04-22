package com.example.a19446101_kt1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    // Tên database và phiên bản
    public static final String DATABASE_NAME = "music.db";
    public static final int DATABASE_VERSION = 1;

    // Tên bảng và các cột trong bảng Album
    public static final String TABLE_ALBUM = "Album";
    public static final String ALBUM_ID = "albumId";
    public static final String ALBUM_NAME = "albumName";

    // Câu lệnh tạo bảng Album
    private static final String CREATE_TABLE_ALBUM =
            "CREATE TABLE " + TABLE_ALBUM +
                    " (" + ALBUM_ID + " TEXT, " +
                    ALBUM_NAME + " TEXT)";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Album
        db.execSQL(CREATE_TABLE_ALBUM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại và tạo lại bảng mới
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALBUM);
        onCreate(db);
    }
}

