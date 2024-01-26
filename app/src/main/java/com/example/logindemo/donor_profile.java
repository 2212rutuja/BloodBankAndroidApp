package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class donor_profile extends AppCompatActivity {

    TextInputEditText fullname_var,address_var,mobile_var,bloodgr_var,healthissue_var;
    FirebaseDatabase firebaseDatabase1;
    DatabaseReference reference1;
    String accept_dt=String.valueOf(Calendar.getInstance().getTime());
    String status="pending";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile);

        fullname_var = (TextInputEditText) findViewById(R.id.donor_name1);
        address_var = (TextInputEditText) findViewById(R.id.address);
        mobile_var=(TextInputEditText) findViewById(R.id.mobile_number);
        bloodgr_var=(TextInputEditText) findViewById(R.id.blood_group);
        healthissue_var= (TextInputEditText) findViewById(R.id.health_issue);
        fullname_var.getEditableText().append(user.username);
        mobile_var.getEditableText().append(user.mobileno);
        bloodgr_var.getEditableText().append(user.bloodgrp);

    }

    public void submitbtnclick(View view) {
        String fullname = fullname_var.getEditableText().toString();
        String address = address_var.getEditableText().toString();
        String mobile = mobile_var.getEditableText().toString();
        String bloodgr = bloodgr_var.getEditableText().toString();
        String healthissue = healthissue_var.getEditableText().toString();


        if (!fullname.isEmpty()) {
            fullname_var.setError(null);
            if (!address.isEmpty()) {
                address_var.setError(null);
                if (!mobile.isEmpty()) {
                    mobile_var.setError(null);
                    if (!bloodgr.isEmpty()) {
                        bloodgr_var.setError(null);
                        if (!healthissue.isEmpty()) {
                            healthissue_var.setError(null);

                            firebaseDatabase1 = FirebaseDatabase.getInstance();
                            reference1 = firebaseDatabase1.getReference("donor");


                            String fullname_s = user.username;
                            String address_s = address_var.getEditableText().toString();
                            String mobile_var_s = user.mobileno;
                            String bloodgr_s =user.bloodgrp;
                            String healthissue_s = healthissue_var.getEditableText().toString();
                            int donation_count=0;
                         /*   String fullname_s = fullname_var.getEditableText().toString();
                            String address_s = address_var.getEditableText().toString();
                        String mobile_var_s = mobile_var.getEditableText().toString();
                            String bloodgr_s = bloodgr_var.getEditableText().toString();
                            String healthissue_s = healthissue_var.getEditableText().toString();*/

                            store_donordata store_donordatass = new store_donordata(fullname_s, address_s, mobile_var_s, bloodgr_s, healthissue_s,accept_dt,status,donation_count);
                            reference1.child(fullname_s).setValue(store_donordatass);

                            Toast.makeText(getApplicationContext(), "Donor registeration successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), donor_main.class);
                            startActivity(intent);
                            finish();
                        } else {
                            healthissue_var.setError("please enter the health issue");
                        }
                    } else {
                        bloodgr_var.setError("please enter the blood group");
                    }
                } else {
                    mobile_var.setError("please enter the mobile no.");
                }
            } else {
                address_var.setError("please enter the address");
            }
        } else {
            fullname_var.setError("Please enter name of user  ");
        }
    }
}