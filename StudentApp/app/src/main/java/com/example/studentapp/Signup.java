package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText username, name, gmail, password;
    Button signupbtn;
    checkdata db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.regno1);
        name = (EditText) findViewById(R.id.name1);
        gmail = (EditText) findViewById(R.id.gmail1);
        password = (EditText) findViewById(R.id.passwd1);

        signupbtn = (Button) findViewById(R.id.signup1);

        db = new checkdata(this);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String n1 = name.getText().toString();
                String g1 = gmail.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || n1.equals("") || g1.equals("") || pass.equals(""))
                {
                    Toast.makeText(Signup.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkuser = db.checkusername(user);
                    if (checkuser == false)
                    {
                        Boolean insert = db.insertData(user, n1, g1, pass);
                        if (insert == true)
                        {
                            Toast.makeText(Signup.this, "Registered Successfully   :)", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(Signup.this, "Registration Failed   :(", Toast.LENGTH_SHORT).show();
                        }
                    }

                    else
                    {
                        Toast.makeText(Signup.this, "User already exists !! please try again", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}