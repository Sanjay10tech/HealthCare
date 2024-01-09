package com.practice.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {

    EditText editName,editContact,editAddress,editPin;
    Button bookBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);
        editName=findViewById(R.id.BMBFname);
        editAddress=findViewById(R.id.BMBAddress);
        editContact=findViewById(R.id.BMBContact);
        editPin=findViewById(R.id.BMBPin);
        bookBtn=findViewById(R.id.BMBbookBtn);


        Intent intent=getIntent();
        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");


        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username,editName.getText().toString(),editAddress.getText().toString(),editContact.getText().toString(),Integer.parseInt(editPin.getText().toString()),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                db.removeCart(username,"medicine");
                Toast.makeText(BuyMedicineBookActivity.this, "Your booking is done Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuyMedicineBookActivity.this,HomeActivity.class));
            }
        });




    }
}