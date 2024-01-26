package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class blood_count extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference, ref;
    TextView count;
    int count_status;
    String msg, mobileno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_count);
        count = (TextView) findViewById(R.id.A);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                count_status = (int) dataSnapshot.child("stock/count").getValue(int.class);
                count.setText(String.valueOf(count_status));
                sendmsg();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), "failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void sendmsg() {
        ref=firebaseDatabase.getReference("datauser");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot1) {
                mobileno = snapshot1.child("admin").child("mobileno").getValue(String.class);
                //Toast.makeText(getApplicationContext(), " data loaded", Toast.LENGTH_SHORT).show();
                if (count_status < 2) {
                    try {
                        Toast.makeText(getApplicationContext(), "failed to load data", Toast.LENGTH_SHORT).show();
                        msg = "XYZ Blood Bank \n Blood Count is less than 1 ";
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(mobileno, null, msg, null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "failed to load data", Toast.LENGTH_SHORT).show();

            }
        });
       /* if (count_status < 2) {
            try {
                Toast.makeText(getApplicationContext(), "failed to load data", Toast.LENGTH_SHORT).show();
                msg = "XYZ Blood Bank \n Blood Count is less than 1 ";
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(mobileno, null, msg, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}