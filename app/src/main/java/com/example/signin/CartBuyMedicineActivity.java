package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuyMedicineActivity extends AppCompatActivity {
    HashMap<String,String> item;
    TextView tvTotal;

    SimpleAdapter sa;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private Button dateButton, timeButton, btnCheckout, btnBack;

    ListView lst;
    ArrayList list;
    private String[][] packages = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);

        dateButton = findViewById(R.id.BMselectDatebtn);
        btnCheckout = findViewById(R.id.BMcheckoutbtn);
        btnBack = findViewById(R.id.BMbackbtn);
        tvTotal = findViewById(R.id.BMtotalpricetxt);
        lst = findViewById(R.id.BMlistView);

        SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();

        Database db = new Database(getApplicationContext(), "healthcare",null,1);

        float totalAmount= 0;
        ArrayList dbData = db.getCartData(username,"medicine");


        packages = new String[dbData.size()][];
        for (int i = 0; i<packages.length;i++){
            packages[i]=new String[5];  //This line creates a new row (an array) and assigns it to the current position i in the packages array.
            // Each row can hold five pieces of information, represented by five empty strings.
            // It's like creating an empty slot in each row to fill with data later.
        }

        for(int i=0; i<packages.length; i++)
        {
            String arrData = dbData.get(i).toString();  //it taks the data of ith index of dbData like 1st i=0 row o
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$")); // as product and price are seperated by $ sign we do that work for this purpose
            packages[i][0] = strData[0];
            packages[i][4] = "Cost : "+strData[1]+"/-";
            totalAmount = totalAmount + Float.parseFloat(strData[1]);
        }

        tvTotal.setText("Total Cost : "+totalAmount);

        list = new ArrayList();
        for (int i=0; i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[] {"line1", "line2", "line3", "line4","line5"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d,R.id.line_e});

        lst.setAdapter(sa);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartBuyMedicineActivity.this, BuyMedicineActivity.class));
            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent it = new Intent(CartBuyMedicineActivity.this, BuyMedicineBookActivity.class);
                //it.putExtra("price", tvTotal.getText());
                //it.putExtra("date", dateButton.getText());
                //it.putExtra("time",timeButton.getText());
                //startActivity(it);
            }
        });
        //datePicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
    }


    public void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                dateButton.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.BUTTON_NEGATIVE;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }
}