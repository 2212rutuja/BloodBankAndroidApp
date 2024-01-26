package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class dashboard extends AppCompatActivity {
    MaterialButton donor_regbutton,bloodreq_btn,available_btn,compatible_btn,profile_btn,support_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        donor_regbutton=(MaterialButton) findViewById(R.id.donor_regbtn);
        bloodreq_btn=(MaterialButton) findViewById(R.id.blood_req_btn);
        available_btn=(MaterialButton) findViewById(R.id.available);
        compatible_btn=(MaterialButton) findViewById(R.id.compatiblebtn);
        profile_btn=(MaterialButton) findViewById(R.id.profilebtn);
        support_btn=(MaterialButton) findViewById(R.id.supportbtn);

        donor_regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),donor_main.class);
                startActivity(intent);

            }
        });

        bloodreq_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),request_main.class);
                startActivity(intent);

            }
        });
        available_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),blood_count.class);
                startActivity(intent);

            }
        });

        compatible_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), compatibility.class);
                startActivity(intent);

            }
        });
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),profile.class);
                startActivity(intent);

            }

        });
        support_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),support.class);
                startActivity(intent);

            }
        });

    }
}