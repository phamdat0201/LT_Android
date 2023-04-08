package com.example.bai13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editYear;
    TextView yearAm;
    Button btnDoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editYear = (EditText) findViewById(R.id.editYear);
        yearAm = (TextView) findViewById(R.id.editYearAm);
        btnDoi = (Button) findViewById(R.id.button);
        btnDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String can[] = {"Canh", "Tân", "Nhâm", "Quý", "Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ"};
               String chi[] = {"Thân", "Dậu", "Tuất", "Hợi", "Tý", "Sửu", "Dần", "Mẹo", "Thìn", "Tỵ", "Ngọ", "Mùi"};
               String dl = editYear.getText().toString();
               int n = Integer.parseInt(dl);
               String am = can[n%10] + " " +chi[n%12];
               yearAm.setText(am);
            }
        });
    }
}