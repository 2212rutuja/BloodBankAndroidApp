package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class profile extends AppCompatActivity {

    TextView name1;
    TextInputEditText name2;
    TextInputEditText bloodgr;
    TextInputEditText mobile;
    TextInputEditText gender;
    FirebaseDatabase fb;
    DatabaseReference ref;
    String name, bloodgrp,gender1,mobileno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name1 = (TextView) findViewById(R.id.name);
        name2 = (TextInputEditText) findViewById(R.id.name1);
        bloodgr= (TextInputEditText)findViewById(R.id.bloodgr);
        mobile=(TextInputEditText) findViewById(R.id.mobile);
        gender= (TextInputEditText) findViewById(R.id.gender1);
        name1.setText(user.username);
        name2.getEditableText().append(user.username);
        bloodgr.getEditableText().append(user.bloodgrp);
        mobile.getEditableText().append(user.mobileno);
        gender.getEditableText().append(user.gender);
        //showalluserdata();


    }
/*
    public void showalluserdata() {
        Intent intent1=getIntent();
        String user_username=intent1.getStringExtra("username");
        String user_blood=intent1.getStringExtra("bloodgroup");
        String user_mobile=intent1.getStringExtra("mobileno");
        String user_gender=intent1.getStringExtra("gender");

        name1.setText(user_username);
        name2.getEditableText().append(user_username);
        bloodgr.getEditableText().append(user_blood);
        mobile.getEditableText().append(user_mobile);
        gender.getEditableText().append(user_gender);

    }*/

    public void  updateclick(View view) {
        name = String.valueOf(name2.getEditableText());
        bloodgrp = String.valueOf(bloodgr.getEditableText());
        gender1= String.valueOf(gender.getEditableText());
        mobileno= String.valueOf(mobile.getEditableText());
       // user newuser=new user(name,mobileno,bloodgrp,gender1);

        user.username =name;
        user.bloodgrp = bloodgrp;
        user.gender = gender1;
        user.mobileno = mobileno;
        Toast.makeText(getApplicationContext(), "updating...", Toast.LENGTH_SHORT).show();
        fb = FirebaseDatabase.getInstance();
        ref = fb.getReference("datauser");
        ref.child(user.username).child("username").setValue(name);
        ref.child(user.username).child("gender").setValue(gender1);
        ref.child(user.username).child("bloodgrp").setValue(bloodgrp);
        ref.child(user.username).child("mobileno").setValue(mobileno);
        Toast.makeText(getApplicationContext(), "profile updated succesfully", Toast.LENGTH_SHORT).show();
    }
}
