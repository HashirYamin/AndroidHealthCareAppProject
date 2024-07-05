package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword, edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.username_xml_ct);
        edEmail = findViewById(R.id.email);
        edPassword = findViewById(R.id.password_ct);
        edConfirm = findViewById(R.id.Re_type_pass);
        btn = findViewById(R.id.signup_btn);
        tv = findViewById(R.id.txt);

        Intent intent = new Intent(Register.this, LoginActivity.class);
        tv.setOnClickListener(view -> startActivity(intent));
        Intent intent1 = new Intent(Register.this,LoginActivity.class);


        btn.setOnClickListener(view -> {
            String username = edUsername.getText().toString();
            String edEmailReg = edEmail.getText().toString();
            String edPasswordReg = edPassword.getText().toString();

            Database db = new Database(getApplicationContext(),"healthcare",null,1);

            String edConfirmReg = edConfirm.getText().toString();
            if (username.length() == 0 || edEmailReg.length() == 0 || edPasswordReg.length() == 0 || edConfirmReg.length() == 0) {
                Toast.makeText(Register.this, "Please fill all the Details", Toast.LENGTH_SHORT).show();
            } else {
                if (edPasswordReg.compareTo(edConfirmReg) == 0) {
                    if(isValid(edPasswordReg)){
                        db.register(username,edEmailReg,edPasswordReg);
                        Toast.makeText(Register.this, "Account Created plz Login to Continue", Toast.LENGTH_SHORT).show();
                        startActivity(intent1);
                    }
                    else {
                        Toast.makeText(Register.this, "Password must contain at least 8 characters, having letter, digit and special symbol", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Register.this, "Password and Re-type Password didn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static boolean isValid(String passwordhere) {
        int f1=0,f2=0,f3=0;
        if(passwordhere.length() < 8){
            return false;
        }
        else {
            for (int p = 0; p < passwordhere.length(); p++) {
                if(Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++) {
                if(Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for(int s = 0; s < passwordhere.length(); s++) {
               char c = passwordhere.charAt(s);
               if(c>=33&&c<=46 || c==64){
                   f3=1;
                }
            }
            if (f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }
}
