package com.example.relativelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences("IS_LOGIN",MODE_PRIVATE);
                boolean check =  sp.getBoolean("login_flag",false);       //second parameter is default value
                Intent intent;
                if(check){
                    intent = new Intent(Splash.this,MainActivity.class);
                }else {
                    intent = new Intent(Splash.this,Login.class);
                }
                startActivity(intent);
                finish();
            }
        },4000);
    }
}