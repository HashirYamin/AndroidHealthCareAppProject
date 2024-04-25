package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class LoginAppSplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_app_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("login_check",MODE_PRIVATE);
                Boolean check = pref.getBoolean("flag",false);

                Intent intent;
                if(check){
                    intent = new Intent(LoginAppSplashScreen.this,HomeActivity.class);
                }else {
                    intent = new Intent(LoginAppSplashScreen.this,LoginActivity.class);
                }
                startActivity(intent);
            }
        },1000);

    }
}