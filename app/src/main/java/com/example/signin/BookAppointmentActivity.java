package com.example.signin;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText ed1, ed2,ed3, ed4;
    TextView tv;
    Button backBtn, bookDocAppointBack;

    Button btnBook;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private Button dateButton, timeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv=findViewById(R.id.textViewBMB);
        ed1=findViewById(R.id.editTextBAFullName);
        ed2=findViewById(R.id.editTextBAAddress);
        ed3=findViewById(R.id.editTextBAContactNo);
        ed4=findViewById(R.id.editTextBAFees);
        dateButton=findViewById(R.id.buttonBADate);
        timeButton=findViewById(R.id.buttonBATime);
        btnBook=findViewById(R.id.buttonBABooking);
        backBtn=findViewById(R.id.buttonBackAppointment);
        bookDocAppointBack =findViewById(R.id.doc_book_app_back);


        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text5");
        String fees = it.getStringExtra("text6");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Consultant Fees: "+ fees +"/-");


        Intent intent = new Intent(BookAppointmentActivity.this, DoctorDetailsActivity.class);
        backBtn.setOnClickListener(view -> {
            startActivity(intent);
        });


        bookDocAppointBack.setOnClickListener(view -> startActivity(intent));
        btnBook.setOnClickListener(view -> {
            Database db = new Database(getApplicationContext(),"healthcare", null,1);
            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username", "").toString();
            if(db.checkAppointmentExists(username,title+"=>"+fullname,address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1){
                Toast.makeText(getApplicationContext(), "Appointment already booked plz select another slot", Toast.LENGTH_SHORT).show();
            }else {
                db.addOrder(username,title+"=>"+fullname,address,contact,0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fees),"appointment");
                Toast.makeText(getApplicationContext(), "Your appointment is done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BookAppointmentActivity.this,HomeActivity.class) );
            }
        });
        initDatePicker();
        dateButton.setOnClickListener(view -> datePickerDialog.show());

        initTimePicker();
        timeButton.setOnClickListener(view -> timePickerDialog.show());




        }
        public void initDatePicker(){
            DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, i, i1, i2) -> {
                i1=i1+1;
                dateButton.setText(i2+"/"+i1+"/"+i);
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
            TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, i, i1) -> timeButton.setText(i+":"+i1);

            Calendar cal = Calendar.getInstance();
            int hrs = cal.get(Calendar.HOUR);
            int mins = cal.get(Calendar.MINUTE);

            int style = AlertDialog.BUTTON_NEGATIVE;
            timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
        }
}