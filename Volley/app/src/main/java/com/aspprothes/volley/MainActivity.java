package com.aspprothes.volley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private AdView adView;
    private LottieAnimationView animationView;
    private AppCompatButton btn;
    private String url = "https://prothesbarai.tiiny.io/";
    public static boolean GET_INSTRACTION = false;
    /* This Line Write For Another Activity Controls.. Because We are Call Data Parcing Method In One Activity ,
    that Save the User Internet, Call One Activity And Control Many Activity */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        adView = findViewById(R.id.adView);
        animationView = findViewById(R.id.animationView);
        btn = findViewById(R.id.btn);

        // ===================== Apps Ads Implementation Start Here Basic / Common Code =========================
        MobileAds.initialize(MainActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });



        // Data Parcing From Online
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                animationView.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                if (response.contains("ON")){
                    textView.setText("Show Ads");
                    AdRequest adRequest = new AdRequest.Builder().build();
                    adView.loadAd(adRequest);
                    GET_INSTRACTION = true;
                }else{
                    textView.setText("OFF Ads");
                    GET_INSTRACTION = false;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setVisibility(View.GONE);
                animationView.setVisibility(View.VISIBLE);
                textView.setText("Error");
            }
        });

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(stringRequest);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });



    }
}