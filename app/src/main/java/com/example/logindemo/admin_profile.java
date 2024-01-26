package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class admin_profile extends AppCompatActivity {
    TextInputEditText name,oldpass,newpass;
    EditText editPhone;
    String admin_name,oldpasswd,newpasswd,password,phone;
    FirebaseDatabase fb;
    DatabaseReference ref;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        name = (TextInputEditText) findViewById(R.id.admin_name);
        oldpass=(TextInputEditText) findViewById(R.id.oldpassword);
        newpass=(TextInputEditText) findViewById(R.id.newpassword);
        editPhone=(EditText)findViewById(R.id.editTextPhone);

        Intent intent1=getIntent();
        admin_name=intent1.getStringExtra("username");
        name.getEditableText().append(admin_name);
    }

    public void updateinfoclick(View view){
        //admin_name= String.valueOf(name.getEditableText());
        oldpasswd= (String) oldpass.getEditableText().toString();
        newpasswd= String.valueOf(newpass.getEditableText());
        phone=String.valueOf(editPhone.getEditableText());
        fb = FirebaseDatabase.getInstance();
        ref = fb.getReference("datauser");
        ref.child(admin_name).child("mobileno").setValue(phone);
        ref.addListenerForSingleValueEvent(new ValueEventListener()  {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                password= snapshot.child(admin_name).child("password").getValue(String.class);
                Toast.makeText(getApplicationContext(), "wait", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "failed to load data", Toast.LENGTH_SHORT).show();
            }
        });


       // Toast.makeText(getApplicationContext(), "updating", Toast.LENGTH_SHORT).show();
        if (oldpasswd.equals(password)) {
            ref.child(admin_name).child("password").setValue(newpasswd);
            Toast.makeText(getApplicationContext(), "Please enter correct password", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "updating", Toast.LENGTH_SHORT).show();
        }
    }
}