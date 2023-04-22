package com.example.bai7;

import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.Browser;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

    public static final Uri BOOKMARKS_URI = Uri.parse("content://browser/bookmarks");
    Button btnshowallcontact;
    Button btnaccesscalllog;
    Button btnaccessmediastore;
    Button btnaccessbookmarks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnshowallcontact=(Button) findViewById(R.id.btnshowallcontact);
        btnshowallcontact.setOnClickListener(this);
        btnaccesscalllog=(Button) findViewById(R.id.btnaccesscalllog);
        btnaccesscalllog.setOnClickListener(this);
        btnaccessmediastore=(Button) findViewById(R.id.btnmediastore);
        btnaccessmediastore.setOnClickListener(this);
        btnaccessbookmarks=(Button) findViewById(R.id.btnaccessbookmarks);
        btnaccessbookmarks.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent=null;
        if(v==btnshowallcontact)
        {
            intent=new Intent(this, ShowAllContactActivity.class);
            startActivity(intent);
        }
        else if(v==btnaccesscalllog)
        {
            accessTheCallLog();
        }
        else if(v==btnaccessmediastore)
        {
            accessMediaStore();
        }
        else if(v==btnaccessbookmarks)
        {
            accessBookmarks();
        }
    }
    /**
     * hàm lấy danh sách lịch sử cuộc gọi
     * với thời gian nhỏ hơn 30 giây và sắp xếp theo ngày gọi
     */
    public void accessTheCallLog()
    {
        String [] projection=new String[]{
                Calls.DATE,
                Calls.NUMBER,
                Calls.DURATION
        };
        Cursor c=getContentResolver().query(
                CallLog.Calls.CONTENT_URI,
                projection,
                Calls.DURATION+"<?",new String[]{"30"},
                Calls.DATE +" Asc");
        c.moveToFirst();
        String s="";
        while(c.isAfterLast()==false){
            for(int i=0;i<c.getColumnCount();i++){
                s+=c.getString(i)+" - ";
            }
            s+="\n";
            c.moveToNext();
        }
        c.close();
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
    /**
     * hàm đọc danh sách các Media trong SD CARD
     */
    public void accessMediaStore()
    {
        String []projection={
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns.DATE_ADDED,
                MediaStore.MediaColumns.MIME_TYPE
        };
        CursorLoader loader=new CursorLoader
                (this, Media.EXTERNAL_CONTENT_URI,
                        projection, null, null, null);
        Cursor c=loader.loadInBackground();
        c.moveToFirst();
        String s="";
        while(!c.isAfterLast()){
            for(int i=0;i<c.getColumnCount();i++){
                s+=c.getString(i)+" - ";
            }
            s+="\n";
            c.moveToNext();
        }
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        c.close();
    }
    /**
     * hàm đọc danh sách Bookmark trong trình duyệt
     */
    public void accessBookmarks()
    {
        String []projection={
                BookmarkColumns.TITLE,
                BookmarkColumns.URL,
        };
        Cursor c=getContentResolver()
                .query(BOOKMARKS_URI, projection,
                        null, null, null);
        c.moveToFirst();
        String s="";
        int titleIndex=c.getColumnIndex
                (BookmarkColumns.TITLE);
        int urlIndex=c.getColumnIndex
                (BookmarkColumns.URL);
        while(!c.isAfterLast())
        {
            s+=c.getString(titleIndex)+" - "+
                    c.getString(urlIndex);
            c.moveToNext();
        }
        c.close();
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
    public static class BookmarkColumns implements BaseColumns {

        public static final String URL = "url";
        public static final String TITLE = "title";
    }
}
