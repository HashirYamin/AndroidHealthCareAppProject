package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edname = findViewById(R.id.editTextLTBFullName);
        edaddress = findViewById(R.id.editTextBAAddress);
        edcontact = findViewById(R.id.editTextBAContactNo);
        edpincode = findViewById(R.id.editTextBAFees);
        btnBooking = findViewById(R.id.buttonBABooking);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");

                // Validate inputs
                String name = edname.getText().toString();
                String address = edaddress.getText().toString();
                String contact = edcontact.getText().toString();
                String pincodeStr = edpincode.getText().toString();

                if (name.isEmpty() || address.isEmpty() || contact.isEmpty() || pincodeStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int pincode = Integer.parseInt(pincodeStr);
                    Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                    db.addOrder(username, name, address, contact, pincode, date, time, Float.parseFloat(price[1]), "lab");
                    db.removeCart(username, "lab");
                    Toast.makeText(getApplicationContext(), "YOUR BOOKING IS DONE SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
                } catch (NumberFormatException e) {
                    Toast.makeText(LabTestBookActivity.this, "Invalid input. Please check your entries.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
