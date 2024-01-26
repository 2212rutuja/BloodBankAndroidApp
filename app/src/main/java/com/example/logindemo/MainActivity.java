package com.example.logindemo;

import static android.widget.Toast.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    MaterialButton loginbtn;
    MaterialButton signupbtn;
    TextInputEditText username_var,password_var;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    //user current_user=new user();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username_var = (TextInputEditText) findViewById(R.id.username);
        password_var = (TextInputEditText) findViewById(R.id.password);

        loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        signupbtn = (MaterialButton) findViewById(R.id.signupbtn);

        /*loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {*/
    }
        public void loginbutnclick(View view) {

            String username = username_var.getText().toString();
            String password = password_var.getText().toString();

            if (!username.isEmpty()) {
                username_var.setError(null);

                if (!password.isEmpty()) {
                    password_var.setError(null);
                    final String username_data = username_var.getText().toString();
                    final String password_data = password_var.getText().toString();

                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("datauser");

                    Query check_username = databaseReference.orderByChild("username").equalTo(username_data);
                    check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                username_var.setError(null);
                                String passwordcheck = snapshot.child(username_data).child("password").getValue(String.class);
                                if (password_data.equals(passwordcheck)) {
                                    password_var.setError(null);


                                    user.username=snapshot.child(username_data).child("username").getValue(String.class);
                                    user.mobileno = snapshot.child(username_data).child("mobileno").getValue(String.class);
                                   user.gender = snapshot.child(username_data).child("gender").getValue(String.class);
                                    user.bloodgrp = snapshot.child(username_data).child("bloodgrp").getValue(String.class);

                                    /*
                                    String namedB = snapshot.child(username_data).child("username").getValue(String.class);
                                    String mobiledB = snapshot.child(username_data).child("mobileno").getValue(String.class);
                                    String genderdB = snapshot.child(username_data).child("gender").getValue(String.class);
                                    String bloodgrdB = snapshot.child(username_data).child("bloodgrp").getValue(String.class);

                                    Intent intent1 = new Intent(getApplicationContext(), profile.class);
                                    intent1.putExtra("username",namedB);
                                    intent1.putExtra("mobileno",mobiledB);
                                    intent1.putExtra("bloodgroup",bloodgrdB);
                                    intent1.putExtra("gender",genderdB);
                                    startActivity(intent1);*/

                                    Toast.makeText(getApplicationContext(), "login successful", LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), dashboard.class);
                                    startActivity(intent);




                                }
                            } else {
                                username_var.setError("user does not exist");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                } else {
                    password_var.setError("please enter the correct password");
                }
            } else {
                username_var.setError("please enter the username");
            }

        }


        public void signupbuttonclick(View view){
                Intent intent = new Intent(getApplicationContext(),register.class);
                startActivity(intent);
                finish();
            }

    public void adminbtnclick(View view){
        Intent intent = new Intent(getApplicationContext(),admin_login.class);
        startActivity(intent);
        finish();
    }

}