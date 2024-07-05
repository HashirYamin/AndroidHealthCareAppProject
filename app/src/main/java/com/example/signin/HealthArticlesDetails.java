package com.example.signin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HealthArticlesDetails extends AppCompatActivity {

    ImageView img;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles_details);

        back = findViewById(R.id.btn_back_ha_details);
        img = findViewById(R.id.display_img);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesDetails.this,HealthArticlesActivity.class));
            }
        });
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            int resId = bundle.getInt("text2");
            img.setImageResource(resId);
        }

    }
}