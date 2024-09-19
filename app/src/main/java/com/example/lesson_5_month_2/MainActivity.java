package com.example.lesson_5_month_2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Double first,second,result;
    private boolean clicked_simvil;
    private String operation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.text_view);
    }

    public void onNumberclick(View view) {
        String text = ((MaterialButton)view).getText().toString();
        if (text.equals("AC")){
            first=0.0;
            second=0.0;
            textView.setText("0");
        }else if(text.equals(".")){
            if (!textView.getText().toString().contains(".")){
                textView.append(text);
            }
        }
        else if (textView.getText().toString().equals("0")||clicked_simvil) {
            textView.setText(text);
        } else {
            textView.append(text);
        }
        clicked_simvil = false;
    }

    public void onOperationClick(View view) {
        if (view.getId()== R.id.plus_btn|| view.getId()== R.id.minus_btn||view.getId()== R.id.devided_btn||view.getId()== R.id.multiply_btn||view.getId()==R.id.percent_btn) {
            first = Double.parseDouble(textView.getText().toString());
            if (view.getId() == R.id.plus_btn) {
                operation = "+";
            }
            if (view.getId() == R.id.minus_btn) {
                operation = "-";
            }
            if (view.getId() == R.id.devided_btn) {
                operation = "/";
            }
            if (view.getId() == R.id.multiply_btn) {
                operation = "*";
            }
            if (view.getId() == R.id.percent_btn) {
                operation = "%";
            }
        }
        else if (view.getId()==R.id.equal_btn){
            second= Double.parseDouble(textView.getText().toString());
            switch (operation) {
                case "+":
                    result = first + second;
                    break;
                case "-":
                    result =first - second;
                    break;
                case"/":
                    result=first / second;
                    break;
                case"*":
                    result=first * second;
                    break;
                case "%":
                    result = second * (first/100);
            }
            DecimalFormat df = new DecimalFormat("#.##");
            String DF_result = df.format(result);
            if (DF_result.endsWith(".0")){
                DF_result=DF_result.substring(0,DF_result.length()-2);
            }
            textView.setText(DF_result);
        }
        clicked_simvil=true;
    }

}