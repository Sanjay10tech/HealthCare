package com.practice.healthcare;

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

    private String[][] packages=
            {
                    {"Uprise-D3 1000IU Capsule","","","","50"},
                    {"HealthVit Chromium Picolinate 200mcg Capsule","","","","350"},
                    {"Vitamin B Complex Capsules","","","","448"},
                    {"Inlife Vitamin E Wheat Germ Oil Capsule","","","","580"},
                    {"Dolo 650 Tablet","","","","30"},
                    {"Crocin 650 Advance Tablet","","","","50"},
                    {"Strepsiles Medicated Lozenges for Sore Throat  ","","","","40"},
                    {"Tata 1mg Calcium + Vitamin D3","","","","50"},
                    {"Feronia -XT Tablet","","","","140"}
            };

    private  String[] package_details={
            "It helps strengthen the bones and muscles\n" +
                    "It can help improve mood and energy levels\n" +
                    "It may help improve heart function",
            "It help a chromium deficiency, control blood sugar levels, lower cholesterol, or lose weight",
            "It may relieve stress, boost cognitive performance, and reduce symptoms of depression and anxiety",
            "It promotes health as well as skin benefit.\n" +
                    "It may help reduce skin blemish and pigmentation.\n" +
                    "It act as safeguard the skin from the harsh UVA and UVB sun rays.",
            "It helps in relieve pain and reduce fever. It is used to treat many conditions such as headaches, body aches, toothaches, and the common cold",
                    "Image result for Crocin 650 Advance Tablet benefits\n" +
                    "It may relieves fever and pain, including toothache, headache, migraine, muscle ache, period pain",
            "It may help relief of mouth and throat infections, dry, irritating cough associated with the common cold and nasal congestion",
            "it may help prevent bone disorders, such as osteoporosis",
            "It is used for the treatment of anaemia or iron deficiency"

    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,goToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);
        btnBack=findViewById(R.id.BMbtnBack);
        goToCart=findViewById(R.id.BMGotoCart);
        lst=findViewById(R.id.ListViewBM);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });



        list=new ArrayList();
        for (int i=0;i<package_details.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/-");
            list.add(item);
        }

        sa=new SimpleAdapter(this,list,
                R.layout.multi_line,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);

                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);

                startActivity(it);
            }
        });


        goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,CardBuyMedicineActivity.class));
            }
        });
    }
}