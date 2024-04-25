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

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages =
    {
        {"Package 1 : Full Body Checkup", "","","", "999"},
        {"Package 2 : Blood Glucose Fasting", "","","", "999"},
        {"Package 3 : Covid-19 Antibody - IgG", "","","", "999"},
        {"Package 4 : Thyroid Check", "","","", "999"},
        {"Package 5 : Immunity Check", "","","", "999"},
        {"Package 6 : Comprehensive Laboratory Panel", "","","", "3999"}
    };

    private String[] package_datails={
            "Blood Glucose Fasting\n"+
                    "Complete Hemogram\n"+
                    "HbA1c\n"+
                    " Iron Studies\n"+
                    "Kidney Function Test\n"+
                    "LDH Lactate Dehydrogenase, Serum\n"+
                    "Lipid Profile\n"+
                    "Liver Function Test\n",
            "Blood Glucose Fasting",
            "Covid-19 AntiBody - IgG",
            "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensative)",
            "Complete Hemogram\n"+
                    "CRP (C Reactive Protien)", "Quatitative Serum\n"+
                    "Iron Studies\n"+
                    "Kidney Function Test\n"+
                    "Vitamin D Total-25 Hydroxy\n"+
                    "Liver Function Test\n"+
                    "Lipid Profile"
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGotoCart, btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGotoCart=findViewById(R.id.buttonBMGoToCart);
        btnBack=findViewById(R.id.buttonBMBack);
        listView=findViewById(R.id.listViewBM);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0; i<packages.length;i++){
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost:"+packages[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(LabTestActivity.this, LaBTestDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2", package_datails[i]);
                it.putExtra("text3", packages[i][4]);
                startActivity(it);

            }
        });


        btnGotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,CartLabActivity.class));
            }
        });


    }
}