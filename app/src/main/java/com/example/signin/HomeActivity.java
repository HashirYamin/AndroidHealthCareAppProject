package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        Toast.makeText(this, "Welcome "+username, Toast.LENGTH_SHORT).show();

        CardView logout = findViewById(R.id.cardExit);
        logout.setOnClickListener(view -> {
            SharedPreferences pref = getSharedPreferences("login_check",MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("flag",false);
            editor.apply();
            startActivity(new Intent(HomeActivity.this,LoginActivity.class));
        });
        CardView findDoctor = findViewById(R.id.cardFindDoctor);
        findDoctor.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this,FindDoctorActivity.class)));

        CardView labTest = findViewById(R.id.cardLabTest);
        labTest.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, LabTestActivity.class)));

        CardView orderDetails = findViewById(R.id.cardOrderDetails);
        orderDetails.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, OrderDetailsActivity.class)));

        CardView buyMedicine = findViewById(R.id.cardBuyMedical);
        buyMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,BuyMedicineActivity.class));
            }
        });

    }

}