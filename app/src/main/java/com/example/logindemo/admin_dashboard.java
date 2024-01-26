package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class admin_dashboard extends AppCompatActivity {
    MaterialButton donor_verifybutton,bloodreq_btn,availablity_btn,list_btn,profile_btn,support_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        donor_verifybutton=(MaterialButton) findViewById(R.id.donor_verifybtn);
        bloodreq_btn=(MaterialButton) findViewById(R.id.req_btn);
        availablity_btn=(MaterialButton) findViewById(R.id.availablity);
        list_btn=(MaterialButton) findViewById(R.id.listbtn);
        profile_btn=(MaterialButton) findViewById(R.id.profilebutn);
        support_btn=(MaterialButton) findViewById(R.id.supportbutn);

        donor_verifybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),donar_verification.class);
                startActivity(intent);
            }
        });
        bloodreq_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),request_verification.class);
                startActivity(intent);
            }
        });

        availablity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),blood_count.class);
                startActivity(intent);
            }
        });

        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),list.class);
                startActivity(intent);
            }
        });
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),admin_profile.class);
                startActivity(intent);

            }
        });
        support_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),support_editable.class);
                startActivity(intent);

            }
        });
    }

}