package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LaBTestDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    TextView edDetails;
    Button btnAddToCart, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvPackageName=findViewById(R.id.textViewBMCartTittle);
        tvTotalCost=findViewById(R.id.textViewCLTotalPrice);
        edDetails=findViewById(R.id.listViewCLCart);
        btnAddToCart=findViewById(R.id.buttonBMDAddToCart);
        btnBack=findViewById(R.id.buttonCLCartBack);

        edDetails.setKeyListener(null);

        Intent intent =getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost : "+intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaBTestDetailsActivity.this, LabTestActivity.class));
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare", null, 1);
                if(db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "PRODUCT ALREADY ADDED", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    db.addToCart(username, product, price, "lab");
                    Toast.makeText(getApplicationContext(), "RECORD INSERTED TO " , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LaBTestDetailsActivity.this,LabTestActivity.class));
                }
            }
        });
    }

}