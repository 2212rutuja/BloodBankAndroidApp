package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.util.Calendar;
import java.text.DateFormat;
import java.util.Date;
public class blood_request extends AppCompatActivity {
    TextInputEditText name,bloodgr,mobile,address,bloodunit;
    FirebaseDatabase firebaseDatabase2;
    DatabaseReference reference2;
    String user_reg=user.username;
    //Date accept_date=Calendar.getInstance().getTime();
    //Timestamp accept_date = new Timestamp(System.currentTimeMillis());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request);
        name = (TextInputEditText) findViewById(R.id.patient_name1);
        address = (TextInputEditText) findViewById(R.id.address1);
        mobile = (TextInputEditText) findViewById(R.id.mobile_numberR);
        bloodgr = (TextInputEditText) findViewById(R.id.blood_groupR);
        bloodunit = (TextInputEditText) findViewById(R.id.blood_unit);
    }
    public void submitbuttonclick(View view){
            String nameR = name.getEditableText().toString();
            String addressR = address.getEditableText().toString();
            String mobileR = mobile.getEditableText().toString();
            String bloodgrR = bloodgr.getEditableText().toString();
            String bloodunitR = bloodunit.getEditableText().toString();
            if (!nameR.isEmpty()) {
                name.setError(null);
                if (!addressR.isEmpty()) {
                    address.setError(null);
                    if (!mobileR.isEmpty()) {
                        mobile.setError(null);
                        if (!bloodgrR.isEmpty()) {
                            bloodgr.setError(null);
                            if (!bloodunitR.isEmpty()) {
                                bloodunit.setError(null);
                                firebaseDatabase2 = FirebaseDatabase.getInstance();
                                reference2 = firebaseDatabase2.getReference("receiver");
                                String name_s = name.getEditableText().toString();
                                String address_s = address.getEditableText().toString();
                                String mobile_var_s = mobile.getEditableText().toString();
                                String bloodgr_s = bloodgr.getEditableText().toString();
                                String bloodunit_s = bloodunit.getEditableText().toString();
                                String accept_date=String.valueOf(Calendar.getInstance().getTime());
                                String status="pending";
                              /*  receiver.accept_dt=accept_dt;
                                receiver rec= new receiver(name_s,address_s,mobile_var_s,accept_dt ,bloodgr_s,bloodunit_s);
                                reference2.child(name_s).setValue(rec);
                                */
                            /*    receiver rec= new receiver(name_s,address_s,mobile_var_s,accept_dt ,bloodgr_s,bloodunit_s,user_reg);
                                reference2.child(name_s).setValue(rec);*/
                                store_receiverdata store_receiverdatass = new store_receiverdata(name_s, address_s, mobile_var_s, bloodgr_s, bloodunit_s,user_reg,accept_date, status);
                                reference2.child(name_s).setValue(store_receiverdatass);

                                Toast.makeText(getApplicationContext(), "request successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), request_main.class);
                                startActivity(intent);
                                finish();
                            } else {
                                bloodunit.setError("please enter the blood unit");
                            }
                        } else {
                            bloodgr.setError("please enter the blood group");
                        }
                    } else {
                        mobile.setError("please enter the mobile no.");
                    }
                } else {
                    address.setError("please enter the address");
                }
            } else {
                name.setError("please enter the name");
            }
        }

}