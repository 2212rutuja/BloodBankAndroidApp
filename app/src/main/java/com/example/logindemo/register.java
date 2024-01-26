package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class register extends AppCompatActivity {
    MaterialButton login;
    TextInputEditText username1_var;
    TextInputEditText password1_var;
    EditText emailid_var;
    TextInputEditText inputMobile_var;
    TextInputEditText gender_var;
    TextInputEditText bloodgroup_var;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseAuth auth;
    //Da regdate=new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username1_var = (TextInputEditText) findViewById(R.id.username1);
        password1_var = (TextInputEditText) findViewById(R.id.password1);
     //  emailid_var= (EditText) findViewById(R.id.emailAddress);
        inputMobile_var=(TextInputEditText) findViewById(R.id.inputMobile);
        gender_var= (TextInputEditText) findViewById(R.id.gender);
        bloodgroup_var=(TextInputEditText) findViewById(R.id.inputBloodGroup);
    }
/*
    public void loginbuttonclick(View view){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }*/

    public void registerbtnclick(View view) {
        String username1 = username1_var.getEditableText().toString();
        String password1 = password1_var.getEditableText().toString();
        String emailaddress1 = emailid_var.getEditableText().toString();
        String inputMobile = inputMobile_var.getEditableText().toString();
        String gender = gender_var.getEditableText().toString();
        String inputbloodgroup = bloodgroup_var.getEditableText().toString();




        if (!username1.isEmpty()) {
            username1_var.setError(null);
            if (!password1.isEmpty()) {
                password1_var.setError(null);
               /* if (!emailaddress1.isEmpty()) {
                    emailid_var.setError(null);*/
                    if (!gender.isEmpty()) {
                        gender_var.setError(null);
                        if (!inputbloodgroup.isEmpty()) {
                            bloodgroup_var.setError(null);
                            if ((!inputMobile.isEmpty())) {
                                inputMobile_var.setError(null);

                                firebaseDatabase = FirebaseDatabase.getInstance();
                                reference = firebaseDatabase.getReference("datauser");
                                auth = FirebaseAuth.getInstance();

                                String username1_s = username1_var.getEditableText().toString();
                                String password1_s = password1_var.getEditableText().toString();
                                String inputMobile_s = inputMobile_var.getEditableText().toString();
                                String gender_s = gender_var.getEditableText().toString();
                                String inputbloodgroup_s = bloodgroup_var.getEditableText().toString();
                                String emailaddress_s = emailid_var.getEditableText().toString();
                                String reg_date= String.valueOf(Calendar.getInstance().getTime());
                                storedata storedatass = new storedata(username1_s, password1_s, inputMobile_s, gender_s, inputbloodgroup_s,reg_date);
                                reference.child(username1_s).setValue(storedatass);

                                Toast.makeText(getApplicationContext(), "registeration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                            } else {
                                inputMobile_var.setError("please enter the mobile no.");
                            }
                        } else {
                            bloodgroup_var.setError("please enter the blood group");
                        }
                    } else {
                        gender_var.setError("please enter the gender");
                    }
              /*  }
                else{
                        emailid_var.setError("Please enter the email address");

                    }*/
                } else {
                    password1_var.setError("please enter the password");
                }
            } else {
                username1_var.setError("please enter the name");
            }

        }
    }
