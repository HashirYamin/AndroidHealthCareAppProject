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
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartLabActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_cart_lab);

        dateButton = findViewById(R.id.buttonBADate);
        timeButton = findViewById(R.id.buttonBATime);
        btnCheckout = findViewById(R.id.buttonCLCartCheckout);
        btnBack = findViewById(R.id.buttonCLCartBack);
        tvTotal = findViewById(R.id.textViewCLTotalPrice);
        lst = findViewById(R.id.listViewCLCart);
        ListView lst = findViewById(R.id.listViewCLCart);

        SharedPreferences sharedPreferences =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();

        Database db = new Database(getApplicationContext(), "healthcare",null,1);

        float totalAmount= 0;
        ArrayList dbData = db.getCartData(username,"lab");


        packages = new String[dbData.size()][];
        for (int i = 0; i<packages.length;i++){
            packages[i]=new String[5];  //This line creates a new row (an array) and assigns it to the current position i in the packages array.
                                        // Each row can hold five pieces of information, represented by five empty strings.
                                        // It's like creating an empty slot in each row to fill with data later.
        }

        for(int i=0; i<packages.length; i++)
        {
            String arrData = dbData.get(i).toString();  // retrieves a row of data from the dbData ArrayList and converts it to a string representation, which can then be further processed or manipulated as needed.
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$")); // as product and price are separated by $ sign we do that work for this purpose
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
                startActivity(new Intent(CartLabActivity.this, LabTestActivity.class));
            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(CartLabActivity.this, LabTestBookActivity.class);
                it.putExtra("price", tvTotal.getText());
                it.putExtra("date", dateButton.getText());
                it.putExtra("time",timeButton.getText());
                startActivity(it);
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

        //TimePicker
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
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
    public void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i+":"+i1);
            }
        };

        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);

        int style = AlertDialog.BUTTON_NEGATIVE;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }
}