package com.aspprothes.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView2 = findViewById(R.id.textView2);

        if (MainActivity.GET_INSTRACTION == true){
            textView2.setText("SHOW ADS");
        }else{
            textView2.setText("OFF ADS");
        }

    }
}