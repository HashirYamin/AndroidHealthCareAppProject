        package com.example.signin;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.constraintlayout.widget.ConstraintLayout;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.HashMap;

        public class DoctorDetailsActivity extends AppCompatActivity {

            private String[][] doctor_details1=
                    {
                            {"Doctor name : Dr. Prakash Tiwari", "Hospital Address : 1717 Oak Avenue, Pashan", "Exp : 6 years", "Mobile No : 043-210-98765", "850"},
                            {"Doctor name : Dr. Reena Sharma", "Hospital Address : 1818 Maple Avenue, Chakan", "Exp : 8 years", "Mobile No : 065-432-10987", "950"},
                            {"Doctor name : Dr. Vikram Singh", "Hospital Address : 1919 Birch Road, Lonavala", "Exp : 4 years", "Mobile No : 065-432-10987", "800"},
                            {"Doctor name : Dr. Sarita Yadav", "Hospital Address : 2020 Pine Lane, Talegaon", "Exp : 5 years", "Mobile No : 043-210-98765", "850"},
                            {"Doctor name : Dr. Sunil Verma", "Hospital Address : 2121 Elm Road, Alandi", "Exp : 9 years", "Mobile No : 098-765-43210", "1000"},
                    };
            private String[][] doctor_details2=
                    {
                            {"Doctor name : Dr. Nisha Gupta", "Hospital Address : 222 Cedar Street, Baner", "Exp : 5 years", "Mobile No : 065-432-10987", "850"},
                            {"Doctor name : Dr. Sanjay Patel", "Hospital Address : 333 Maple Avenue, Chinchwad", "Exp : 7 years", "Mobile No : 031-987-65432", "950"},
                            {"Doctor name : Dr. Anjali Desai", "Hospital Address : 444 Birch Road, Kothrud", "Exp : 9 years", "Mobile No : 087-654-32109", "1100"},
                            {"Doctor name : Dr. Alok Verma", "Hospital Address : 555 Cedar Lane, Hadapsar", "Exp : 3 years", "Mobile No : 012-345-67890", "600"},
                            {"Doctor name : Dr. Shalini Singhania", "Hospital Address : 666 Oak Avenue, Pimple Saudagar", "Exp : 12 years", "Mobile No : 054-321-09876", "1200"}

                    };private String[][] doctor_details3=
                    {
                            {"Doctor name : Dr. Ramesh Reddy", "Hospital Address : 777 Elm Road, Hinjewadi", "Exp : 11 years", "Mobile No : 076-987-65432", "1150"},
                            {"Doctor name : Dr. Meena Das", "Hospital Address : 888 Maple Street, Kharadi", "Exp : 4 years", "Mobile No : 043-210-98765", "700"},
                            {"Doctor name : Dr. Arjun Mehta", "Hospital Address : 999 Pine Lane, Viman Nagar", "Exp : 8 years", "Mobile No : 065-432-10987", "850"},
                            {"Doctor name : Dr. Kavita Sharma", "Hospital Address : 1010 Birch Avenue, Magarpatta", "Exp : 6 years", "Mobile No : 032-109-87654", "800"},
                            {"Doctor name : Dr. Mohan Kumar", "Hospital Address : 1111 Cedar Road, Kalyani Nagar", "Exp : 7 years", "Mobile No : 098-765-43210", "900"}


                    };private String[][] doctor_details4=
                    {
                            {"Doctor name : Dr. Sneha Joshi", "Hospital Address : 1212 Oak Lane, Vishrantwadi", "Exp : 5 years", "Mobile No : 065-432-10987", "850"},
                            {"Doctor name : Dr. Suresh Choudhary", "Hospital Address : 1313 Maple Street, Yerwada", "Exp : 9 years", "Mobile No : 065-432-10987", "950"},
                            {"Doctor name : Dr. Poonam Gupta", "Hospital Address : 1414 Pine Avenue, Warje", "Exp : 3 years", "Mobile No : 043-210-98765", "700"},
                            {"Doctor name : Dr. Vivek Mishra", "Hospital Address : 1515 Elm Road, Kondhwa", "Exp : 10 years", "Mobile No : 065-432-10987", "1000"},
                            {"Doctor name : Dr. Ritu Saxena", "Hospital Address : 1616 Cedar Lane, Katraj", "Exp : 7 years", "Mobile No : 098-765-43210", "900"},

                    };private String[][] doctor_details5=
                    {
                            {"Doctor name : Dr. Preeti Sharma", "Hospital Address : 323 Silver Street, Chinchwad", "Exp : 6 years", "Mobile No : 041-123-45678", "850"},
                            {"Doctor name : Dr. Sameer Patel", "Hospital Address : 232 Gold Road, Baner", "Exp : 5 years", "Mobile No : 063-234-56789", "800"},
                            {"Doctor name : Dr. Rakesh Gupta", "Hospital Address : 452 Diamond Lane, Pimple Saudagar", "Exp : 7 years", "Mobile No : 087-987-65432", "900"},
                            {"Doctor name : Dr. Nandini Reddy", "Hospital Address : 555 Ruby Avenue, Hinjewadi", "Exp : 9 years", "Mobile No : 096-345-67890", "1100"},
                            {"Doctor name : Dr. Anisha Das", "Hospital Address : 666 Sapphire Road, Kharadi", "Exp : 8 years", "Mobile No : 075-678-90123", "1000"}


                    };
            TextView tv;
            Button btn;

            String[][] doctor_details = {};

            HashMap<String,String> item;
            ArrayList list;

            SimpleAdapter sa;
            ConstraintLayout bg;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_doctor_details);

                tv=findViewById(R.id.textViewCartPackageName);
                btn=findViewById(R.id.buttonDDBack);
                bg = findViewById(R.id.constraint_layout);

                Intent it = getIntent();
                String title = it.getStringExtra("title");
                tv.setText(title);
                if(title.compareTo("Family Physicians")==0)
                    doctor_details=doctor_details1;
                else
                if(title.compareTo("Dietitians")==0) {
                    bg.setBackgroundResource(R.drawable.nutritionist);
                    doctor_details = doctor_details2;
                }else
                if(title.compareTo("Dentists")==0) {
                    bg.setBackgroundResource(R.drawable.dentist);
                    doctor_details = doctor_details3;
                }else
                if(title.compareTo("Surgeons")==0) {
                    bg.setBackgroundResource(R.drawable.surgeon);
                    doctor_details = doctor_details4;
                }else {
                    bg.setBackgroundResource(R.drawable.cardiologist);
                    doctor_details = doctor_details5;
                }

                btn.setOnClickListener(view -> startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class)));

                list = new ArrayList();
                for (int i=0;i<doctor_details.length;i++){
                    item = new HashMap<String,String>();
                    item.put("line1",doctor_details[i][0]);
                    item.put("line2",doctor_details[i][1]);
                    item.put("line3",doctor_details[i][2]);
                    item.put("line4",doctor_details[i][3]);
                    item.put("line5","Consultant Fees"+doctor_details[i][4]+"/-");
                    list.add(item);
                }
                    sa = new SimpleAdapter(this,list,
                            R.layout.multi_lines,
                            new String[]{"line1","line2","line3","line4","line5"},
                            new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                            );
                    ListView lst = findViewById(R.id.listViewDD);
                    lst.setAdapter(sa);
                    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                            it.putExtra("text1",title);
                            it.putExtra("text2",doctor_details[i][0]);
                            it.putExtra("text3",doctor_details[i][1]);
                            it.putExtra("text4",doctor_details[i][2]);
                            it.putExtra("text5",doctor_details[i][3]);
                            it.putExtra("text6",doctor_details[i][4]);
                            startActivity(it);
                        }
                    });
            }
        }