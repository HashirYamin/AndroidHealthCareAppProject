package com.example.signin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticlesActivity extends AppCompatActivity {

    private String[][] health_details =
            {
                    {"Walking Daily", "", "", "", "Click more details"},
                    {"Home Care of Covid-19", "", "", "", "Click more details"},
                    {"Stop Smoking", "", "", "", "Click more details"},
                    {"Healthy Diet", "", "", "", "Click more details"},
                    {"Exercise", "", "", "", "Click more details"}
            };

    private int[] images = {
            R.drawable.walk,
            R.drawable.homecare,
            R.drawable.stopsmoking,
            R.drawable.diet,
            R.drawable.exercise
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    Button back;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);


        lst = findViewById(R.id.health_article_list);
        back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<health_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",health_details[i][0]);
            item.put("line2",health_details[i][1]);
            item.put("line3",health_details[i][2]);
            item.put("line4",health_details[i][3]);
            item.put("line5",health_details[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.health_article_items,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);
        lst.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent it1 = new Intent(HealthArticlesActivity.this, HealthArticlesDetails.class);
            it1.putExtra("text2",images[i]);
            startActivity(it1);
        });
    }
}