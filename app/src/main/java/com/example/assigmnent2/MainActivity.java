package com.example.assigmnent2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    Button signup;
    Button Login;
    CheckBox check;

    private Toolbar toolbar;
    private static final String PREFS_NAME = "mypref";
    private static final String PREF_REMEMBER = "rem";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);//define toolbar

        setSupportActionBar(toolbar);


         username=findViewById(R.id.username);
         password=findViewById(R.id.TextPassword);
         signup=findViewById(R.id.Reg);
         Login=findViewById(R.id.login);
         check=findViewById(R.id.Remember);

        SharedPreferences sp = getSharedPreferences(PREFS_NAME, 0);
        boolean rem=sp.getBoolean(PREF_REMEMBER,false);
        check.setChecked(rem);
        if (rem){
            String namesaved=sp.getString("username","username");
            String passsaved= sp.getString("password","password");
            username.setText(namesaved);
            password.setText(passsaved);
        }



         signup.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(MainActivity.this,CreateAcount.class);
                    startActivity(intent);
             }
         });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sp.edit();
                editor.putBoolean(PREF_REMEMBER,check.isChecked());
                editor.apply();

                String usernamelogin=username.getText().toString();
                String passlogin=password.getText().toString();

                String namesaved=sp.getString("username","username");
                String passsaved=sp.getString("password","password");

                if(usernamelogin.equals(namesaved) && passlogin.equals(passsaved)){
                    Intent intent=new Intent(MainActivity.this,firstPage.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }



}