package com.example.bai10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText editTextNumber;
    String input, answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNumber = (EditText) findViewById(R.id.EditTextNumber);
    }

    public void ButtonClick(View view) {
        Button button = (Button) view;
        String data = button.getText().toString();

        switch (data) {
            case "Delete":
                input = "";
                break;
            case "=":
                Solve();
                answer = input;
                break;
            case "x":
                Solve();
                input+="*";
                break;
            default:
                if (input == null) {
                    input="";
                }
                if (data.equals("+") || data.equals("-") || data.equals("÷")) {
                    Solve();
                }
                input+=data;
        }
        editTextNumber.setText(input);
    }
    public void Solve(){
        if (input.split("\\*").length==2) {
            String number[] = input.split("\\*");
            try {
                double mul = Double.parseDouble(number[0])*Double.parseDouble(number[1]);
                input = mul +"";
            }catch (Exception e) {

            }
        }

        if (input.split("\\+").length==2) {
            String number[] = input.split("\\+");
            try {
                double plus = Double.parseDouble(number[0])+Double.parseDouble(number[1]);
                input = plus +"";
            }catch (Exception e) {

            }
        }
        if (input.split("\\-").length==2) {
            String number[] = input.split("\\-");
            try {
                double minus = Double.parseDouble(number[0])-Double.parseDouble(number[1]);
                input = minus +"";
            }catch (Exception e) {

            }
        }
        if (input.split("\\÷").length==2) {
            String number[] = input.split("\\÷");
            try {
                double div = Double.parseDouble(number[0])/Double.parseDouble(number[1]);
                input = div +"";
            }catch (Exception e) {

            }
        }
    }
}

