package com.example.assigmnent2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAcount extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText confirmpassword;
    Button create;
    Button back;

    private static final String PREFS_NAME="mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acount);

        username=findViewById(R.id.creuser);
        password=findViewById(R.id.crepass);
        confirmpassword=findViewById(R.id.crepass2);
        create=findViewById(R.id.createbtn);
        back=findViewById(R.id.backbtn);

        final SharedPreferences sp = getSharedPreferences(PREFS_NAME, 0);//setup sharedpref

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();//to get text from edit text
                String pass=password.getText().toString();//to get text from edit text
                String pass2=confirmpassword.getText().toString();//to get text from edit text

                if (user.isEmpty() || pass.isEmpty() || pass2.isEmpty() ){//to check user text or pass text or pass2 text if empty or not
                    Toast.makeText(CreateAcount.this,"Field Empty",Toast.LENGTH_SHORT).show();//to show error message cuz its empty
                    return;

                }
                if (pass.equals(pass2)){//if the pass =pass2 save the user and password at sharedpref
                    SharedPreferences.Editor editor= sp.edit();
                    editor.putString("username",user);//to save user name
                    editor.putString("password",pass);//to save password
                    editor.apply();
                    Log.d("CreateAcount", "Account created - Username: " + user + ", Password: " + pass);
                    Intent intent=new Intent(CreateAcount.this,MainActivity.class);//to go to login activity
                    startActivity(intent);

                }
                else {
                    Toast.makeText(CreateAcount.this,"Password Doesnt Match",Toast.LENGTH_SHORT).show();//error message

                }

                Log.d("CreateAcount", "Username: " + user + ", Password: " + pass);


            }
        });

        back.setOnClickListener(new View.OnClickListener() {//back button
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });

    }
}