package com.example.signin;

import static java.lang.Integer.parseInt;

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

        edname=findViewById(R.id.editTextLTBFullName);
        edaddress=findViewById(R.id.editTextBAAddress);
        edcontact=findViewById(R.id.editTextBAContactNo);
        edpincode=findViewById(R.id.editTextBAFees);
        btnBooking=findViewById(R.id.buttonBABooking);

        Intent intent =getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

    btnBooking.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username", "").toString();

            Database db = new Database(getApplicationContext(), "healthcare",null,1);
            db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(), parseInt(edpincode.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
            db.removeCart(username,"lab");
            Toast.makeText(getApplicationContext(), "YOUR BOOKING IS DONE SUCCESSFULLY ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LabTestBookActivity.this,HomeActivity.class));
        }
    });
    }
}