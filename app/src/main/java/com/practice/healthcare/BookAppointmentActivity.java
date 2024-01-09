package com.practice.healthcare;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText Bname,Baddress,Bcontact,Bfees;
    TextView tv;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    Button dateButton,timeButton,btnAppointment,btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv = findViewById(R.id.textAppointment);
        Bname = findViewById(R.id.BName);
        Baddress = findViewById(R.id.BAddress);
        Bcontact = findViewById(R.id.BContact);
        Bfees = findViewById(R.id.BFees);
        dateButton=findViewById(R.id.btnAppDate);
        timeButton=findViewById(R.id.btnApptime);
        btnAppointment=findViewById(R.id.Appointmentbtn);
        btnBack=findViewById(R.id.Cancelbtn);

        Bname.setKeyListener(null);
        Baddress.setKeyListener(null);
        Bcontact.setKeyListener(null);
        Bfees.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullName = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        Bname.setText(fullName);
        Baddress.setText(address);
        Bcontact.setText(contact);
        Bfees.setText("Cons Fees: " + fees + "/-");

        //datePicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this,FindDoctorActivity.class));
            }
        });

        btnAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(db.checkAppointmentExists(username,title+"=>"+fullName,address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1){
                    Toast.makeText(BookAppointmentActivity.this, "Appointment Already Booked", Toast.LENGTH_SHORT).show();
                }else{
                    db.addOrder(username,title+"=>"+fullName,address,contact,0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fees),"appointment");
                    Toast.makeText(BookAppointmentActivity.this, "Your appointment is done Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent( BookAppointmentActivity.this,HomeActivity.class));
                }
            }
        });


    }
        private void initDatePicker(){
            DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                      i1=i1+1;
                      dateButton.setText(i2+"/"+i1+"/"+i);
                }
            };

            Calendar cal=Calendar.getInstance();
            int year=cal.get(Calendar.YEAR);
            int month=cal.get(Calendar.MONTH);
            int day=cal.get(Calendar.DAY_OF_MONTH);

            int style= AlertDialog.THEME_HOLO_DARK;
            datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
            datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);

    }

    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i+":"+i1);
            }
        };

        Calendar cal=Calendar.getInstance();
        int hour=cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);

        int style=AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,timeSetListener,hour,min,true);
    }
}