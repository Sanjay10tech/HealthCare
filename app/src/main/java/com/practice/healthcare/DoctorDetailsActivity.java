package com.practice.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
private String[][] doctor_details1={
        {"Doctor Name : Ajit Saste","Hospital Address: Indore","Exp: 5yrs","Mobile No. : 8889954720","600"},
        {"Doctor Name : Prasant Pawar","Hospital Address: pune","Exp: 15yrs","Mobile No. : 8989904740","900"},
        {"Doctor Name : Swapnil Kale","Hospital Address: Bangalore","Exp: 8yrs","Mobile No. : 790956320","300"},
        {"Doctor Name : Deepak Seshmukh","Hospital Address: Delhi","Exp: 6yrs","Mobile No. : 8469954753","500"},
        {"Doctor Name : Ashok Panda","Hospital Address: Goa","Exp: 7yrs","Mobile No. : 9989954420","800"}
};

    private String[][] doctor_details2={
            {"Doctor Name : Arjit Saste","Hospital Address: Indore","Exp: 5yrs","Mobile No. : 8889954720","600"},
            {"Doctor Name : Pradesh Pawar","Hospital Address: pune","Exp: 15yrs","Mobile No. : 8989904740","900"},
            {"Doctor Name : Sapna Kale","Hospital Address: Bangalore","Exp: 8yrs","Mobile No. : 790956320","300"},
            {"Doctor Name : Deepika Seshmukh","Hospital Address: Delhi","Exp: 6yrs","Mobile No. : 8469954753","500"},
            {"Doctor Name : Ashoka Panda","Hospital Address: Goa","Exp: 7yrs","Mobile No. : 9989954420","800"}
    };
    private String[][] doctor_details3={
            {"Doctor Name : Amit Saste","Hospital Address: Indore","Exp: 5yrs","Mobile No. : 8889954720","600"},
            {"Doctor Name : Raja Pawar","Hospital Address: pune","Exp: 15yrs","Mobile No. : 8989904740","900"},
            {"Doctor Name : Ram Kale","Hospital Address: Bangalore","Exp: 8yrs","Mobile No. : 790956320","300"},
            {"Doctor Name : Sona Seshmukh","Hospital Address: Delhi","Exp: 6yrs","Mobile No. : 8469954753","500"},
            {"Doctor Name : Kahna Panda","Hospital Address: Goa","Exp: 7yrs","Mobile No. : 9989954420","800"}
    };
    private String[][] doctor_details4={
            {"Doctor Name : Shiva Saste","Hospital Address: Indore","Exp: 5yrs","Mobile No. : 8889954720","600"},
            {"Doctor Name : Amvika Pawar","Hospital Address: pune","Exp: 15yrs","Mobile No. : 8989904740","900"},
            {"Doctor Name : Rakhi Kale","Hospital Address: Bangalore","Exp: 8yrs","Mobile No. : 790956320","300"},
            {"Doctor Name : Tanuj Seshmukh","Hospital Address: Delhi","Exp: 6yrs","Mobile No. : 8469954753","500"},
            {"Doctor Name : Tanu Panda","Hospital Address: Goa","Exp: 7yrs","Mobile No. : 9989954420","800"}
    };
    private String[][] doctor_details5={
            {"Doctor Name : Krishna Saste","Hospital Address: Indore","Exp: 5yrs","Mobile No. : 8889954720","600"},
            {"Doctor Name : Prant Pawar","Hospital Address: pune","Exp: 15yrs","Mobile No. : 8989904740","900"},
            {"Doctor Name : Jezz Kale","Hospital Address: Bangalore","Exp: 8yrs","Mobile No. : 790956320","300"},
            {"Doctor Name : Lakshya Seshmukh","Hospital Address: Delhi","Exp: 6yrs","Mobile No. : 8469954753","500"},
            {"Doctor Name : Naman Panda","Hospital Address: Goa","Exp: 7yrs","Mobile No. : 9989954420","800"}
    };

TextView tv;
Button btn;
ArrayList list;
SimpleAdapter sa;
HashMap<String,String> item;
String[][] doctor_details={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv=findViewById(R.id.DDtitle);
        btn=findViewById(R.id.DDbtn);

        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0){
            doctor_details=doctor_details1;
        }else if(title.compareTo("Dietician")==0){
            doctor_details=doctor_details2;
        }else if(title.compareTo("Dentist")==0){
            doctor_details=doctor_details3;
        }else if(title.compareTo("Surgeon")==0){
            doctor_details=doctor_details4;
        }else if(title.compareTo("Cardiologist")==0){
            doctor_details=doctor_details5;
        }

            btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

        list=new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees: "+doctor_details[i][4]+" /-");
            list.add(item);
        }

        sa=new SimpleAdapter(this,
                list,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        ListView lst=findViewById(R.id.ListViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}