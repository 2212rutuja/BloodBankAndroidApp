package com.example.logindemo;

import static com.example.logindemo.R.id.recr_name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class RequestInfoVerify extends AppCompatActivity {
    TextView fullname,bloodgr,address, mobile_var, bloodunit;
    Button accept_btn;
    String status;
    FirebaseDatabase fb;
    DatabaseReference ref;

    String name,blood,mobileno,addr,count,user_reg;
    String reg_date= String.valueOf(Calendar.getInstance().getTime());


    // Date date =Calendar.getInstance().getTime();
    //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_info_verify);

        accept_btn= (Button)findViewById(R.id.accept_reqbutton);
        fullname=findViewById(recr_name);
        bloodgr=findViewById(R.id.bloodgrup);
        address=findViewById(R.id.address);
        mobile_var=findViewById(R.id.mobile_var);
        bloodunit=findViewById(R.id.bloodunit);

        fullname.setText(getIntent().getStringExtra("fullname"));
        bloodgr.setText(getIntent().getStringExtra("bloodgr"));
        address.setText(getIntent().getStringExtra("address"));
        mobile_var.setText(getIntent().getStringExtra("mobile_var"));
        bloodunit.setText(getIntent().getStringExtra("bloodunit"));

        name=getIntent().getStringExtra("fullname");
        fb= FirebaseDatabase.getInstance();
        ref= fb.getReference("receiver");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                status =  snapshot.child(name).child("status").getValue(String.class);
                user_reg=snapshot.child(name).child("user_reg").getValue(String.class);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "failed to load data", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void acceptclick(View view){
        status="accepted";
        name=getIntent().getStringExtra("fullname");
        receiver recr=new receiver(reg_date,status);
        fb= FirebaseDatabase.getInstance();
        ref= fb.getReference("receiver");
        ref.child(name).child("accept_date").setValue(recr.accept_date);
        ref.child(name).child("status").setValue(recr.status);
        Toast.makeText(getApplicationContext(), "request accepted", Toast.LENGTH_SHORT).show();
    }

    public void rejectclick(View view){
    name=getIntent().getStringExtra("fullname");
  /*  fb= FirebaseDatabase.getInstance();
    ref= fb.getReference("receiver");
    ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                status =  snapshot.child(name).child("status").getValue(String.class);
                user_reg=snapshot.child(name).child("user_reg").getValue(String.class);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "failed to load data", Toast.LENGTH_SHORT).show();
            }
        });*/
        removerecr();
    }

    private void removerecr() {

        name=getIntent().getStringExtra("fullname");
        blood = getIntent().getStringExtra("bloodgr");
        addr=getIntent().getStringExtra("address");
        mobileno=getIntent().getStringExtra("mobile_var");
        count=getIntent().getStringExtra("bloodunit");
        ref.child(name).removeValue();
        status="rejected";
        receiver rec=new receiver(name,addr,mobileno,blood,count,user_reg,status);
        ref=fb.getReference("rejected requests");
        ref.child(name).setValue(rec);
        Toast.makeText(getApplicationContext(), "request rejected", Toast.LENGTH_SHORT).show();

    }
}