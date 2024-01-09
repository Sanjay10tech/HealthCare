package com.practice.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity {
 EditText Uname,Upass;
 Button btn;
 TextView newUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Uname=findViewById(R.id.edittxtUsername);
        Upass=findViewById(R.id.edittxtPassword);
        btn=findViewById(R.id.loginbtn);
        newUser=findViewById(R.id.textNewUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName=Uname.getText().toString();
                String password=Upass.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(UserName.length()==0 || password.length()==0){
                    Toast.makeText(LoginActivity.this, "Fill all the field", Toast.LENGTH_SHORT).show();
                }else{
                    if(db.login(UserName,password )==1) {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        editor.putString("username",UserName);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this,"Invalid Username and Password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}