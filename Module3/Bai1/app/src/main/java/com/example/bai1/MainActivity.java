package com.example.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);

        Button writeButton = findViewById(R.id.write_button);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFile();
            }
        });

        Button readButton = findViewById(R.id.read_button);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile();
            }
        });
    }

    private void writeFile() {
        String fileName = "example.txt";

        if (isExternalStorageWritable()) {
            File file = new File(getExternalFilesDir(null), fileName);
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(editText.getText().toString().getBytes());
                outputStream.close();
                Toast.makeText(this, "File saved to " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "External storage not writable", Toast.LENGTH_SHORT).show();
        }
    }

    private void readFile() {
        String fileName = "example.txt";

        if (isExternalStorageReadable()) {
            File file = new File(getExternalFilesDir(null), fileName);
            try {
                FileInputStream inputStream = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
                editText.setText(stringBuilder.toString());
                Toast.makeText(this, "File loaded from " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "External storage not readable", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }
}
