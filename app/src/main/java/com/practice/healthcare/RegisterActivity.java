package com.practice.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText Rname,Remail,Rpass,Confirmpass;
    Button Rbtn;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Rname=findViewById(R.id.BName);
        Remail=findViewById(R.id.BAddress);
        Rpass=findViewById(R.id.BContact);
        Confirmpass=findViewById(R.id.BFees);
        Rbtn=findViewById(R.id.Registerbtn);
        txt=findViewById(R.id.textOldUser);

        Rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=Rname.getText().toString();
                String email=Remail.getText().toString();
                String pass=Rpass.getText().toString();
                String conPass=Confirmpass.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(name.length()==0 || email.length()==0 || pass.length()==0 || conPass.length()==0){
                    Toast.makeText(RegisterActivity.this, "All field is Required", Toast.LENGTH_SHORT).show();
                } else{
                    if(pass.compareTo(conPass)==0){
                       if(isValid(pass)){
                           db.register(name,email,pass);
                           Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                       }else{
                           Toast.makeText(RegisterActivity.this, "Password should have aleast 8 characters, having letter, digit and symbol", Toast.LENGTH_SHORT).show();
                       }
                    }else {
                        Toast.makeText(RegisterActivity.this, "Password should be matched", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        txt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public static boolean isValid(String passwordHere){
        int i=0,j=0,k=0;
        if(passwordHere.length()<8) {
            return false;
        }else{
            for (int p=0;p<passwordHere.length();p++){
                if(Character.isLetter(passwordHere.charAt(p))){
                    i=1;
                }
            }
            for (int r=0;r<passwordHere.length();r++){
                if(Character.isDigit(passwordHere.charAt(r))){
                    j=1;
                }
            }
            for(int s=0;s<passwordHere.length();s++){
                char c=passwordHere.charAt(s);
                if(c>=33 && c<=46 || c==64){
                    k=1;
                }
            }

            if(i==1 &&j==1 && k==1){
                return true;
            }
            return false;
        }
    }
}