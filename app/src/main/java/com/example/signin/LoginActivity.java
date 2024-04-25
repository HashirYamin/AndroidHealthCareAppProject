package com.example.signin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {
    EditText Username_login, Password;
    Button Login_btn;
    Button BTN;

    TextView textBtnAlready;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username_login=findViewById(R.id.login_name);
        Password = findViewById(R.id.password_log);
        Login_btn=findViewById(R.id.login_btn);


        Login_btn.setOnClickListener(view -> {
            String username= Username_login.getText().toString();
            String password = Password.getText().toString();
            Database db = new Database(getApplicationContext(),"healthcare",null,1);
            if(username.length() == 0 || password.length() == 0) {
                Toast.makeText(LoginActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
            else if(username.length()<3){
                Toast.makeText(LoginActivity.this, "Please Enter correct Username", Toast.LENGTH_SHORT).show();
            } else if (password.length()<5) {
                Toast.makeText(LoginActivity.this, "Password length must be 6 or more", Toast.LENGTH_SHORT).show();
            } else {
                if(db.login(username, password)==1){
                    Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                    SharedPreferences pref = getSharedPreferences("login_check",MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = pref.edit();
                    editor1.putBoolean("flag",true);
                    editor1.apply();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                    SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = sharedPreferences.edit();
                    editor2.putString("username",username);
                    editor2.apply();

                }else {
                    Toast.makeText(LoginActivity.this, "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent intent1 = new Intent(LoginActivity.this,Register.class);
        BTN= findViewById(R.id.myButton);
        BTN.setOnClickListener(view -> startActivity(intent1));
        textBtnAlready=findViewById(R.id.already_text);
        textBtnAlready.setOnClickListener(v -> startActivity(intent1));
    }
}