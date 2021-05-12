package com.example.interview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

public class splash_act extends AppCompatActivity {
    ImageView AppIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        AppIcon = findViewById(R.id.logo);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(splash_act.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2500);


    }
}
