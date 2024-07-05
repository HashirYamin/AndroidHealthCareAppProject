package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages = {
            {"Uprise-D3 1000IU Capsule", "", "", "", "50"},
            {"Uprise-D3 1000IU Capsule", "", "", "", "50"},
            {"Aspirin 81mg Tablet", "", "", "", "100"},
            {"Amoxicillin 500mg Capsule", "", "", "", "30"},
            {"Ibuprofen 200mg Tablet", "", "", "", "60"},
            {"Lisinopril 10mg Tablet", "", "", "", "90"},
            {"Metformin 500mg Extended-Release Tablet", "", "", "", "60"},
            {"Atorvastatin 20mg Tablet", "", "", "", "30"},
            {"Ciprofloxacin 500mg Tablet", "", "", "", "40"},
            {"Omeprazole 20mg Capsule", "", "", "", "28"},
            {"Loratadine 10mg Tablet", "", "", "", "24"}
    };
    private String[] package_details ={
            "Building and keeping bones and teeth strong\n",
            "Relief of mild pain and fever",
            "Treatment of bacterial infections",
            "Relief of pain and inflammation",
            "Lowering high blood pressure",
            "Managing type 2 diabetes",
            "Lowering cholesterol levels",
            "Treatment of bacterial infections",
            "Reducing stomach acid",
            "Relief of allergy symptoms"
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack, btnGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

    lst = findViewById(R.id.listViewBM);
    btnBack = findViewById(R.id.buttonBMBack);
    btnGoToCart = findViewById(R.id.buttonBMGoToCart);


    btnGoToCart.setOnClickListener(v -> {
        startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
    });

    btnBack.setOnClickListener(v -> startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class)));

    list = new ArrayList();
    for(int i =0; i< packages.length;i++){
        item = new HashMap<String,String>();
        item.put("line1",packages[i][0]);
        item.put("line2",packages[i][1]);
        item.put("line3",packages[i][2]);
        item.put("line4",packages[i][3]);
        item.put("line5","Total Cost: "+packages[i][4]+"/-");
        list.add(item);
    }

    sa = new SimpleAdapter(this,list,
            R.layout.multi_lines,
            new String[] {"line1", "line2", "line3", "line4", "line5"},
            new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

    lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });
    }
}