package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class DonorInfoVerify extends AppCompatActivity {
    TextView fullname,bloodgr,address, mobile_var, healthissue;
    FirebaseDatabase fb;
    DatabaseReference ref,ref1;
    String name,blood,addr,mobileno,issue;
    String status,msg;
    int donation_count=0;
    String reg_date= String.valueOf(Calendar.getInstance().getTime());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_info_verify);
        fullname=findViewById(R.id.fullname);
        bloodgr=findViewById(R.id.bloodgr);
        address=findViewById(R.id.address);
        mobile_var=findViewById(R.id.mobile_var);
        healthissue=findViewById(R.id.health_issue);

        fullname.setText(getIntent().getStringExtra("fullname"));
        bloodgr.setText(getIntent().getStringExtra("bloodgr"));
        address.setText(getIntent().getStringExtra("address"));

        mobile_var.setText(getIntent().getStringExtra("mobile_var"));
        healthissue.setText(getIntent().getStringExtra("healthissue"));
       // name = toString(fullname);
    }

   /* private String toString(TextView fullname) {

        return String.valueOf(fullname);
    }*/

    public void acceptbtnclick(View view){
        fb= FirebaseDatabase.getInstance();
        ref= fb.getReference("donor");
        name=getIntent().getStringExtra("fullname");
        status="accepted";

        donor dr= new donor(reg_date,status,donation_count);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               dr.donation_count =  snapshot.child(name).child("donation_count").getValue(int.class);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
        dr.donation_count++;
        ref.child(name).child("donation_count").setValue(dr.donation_count);
        ref.child(name).child("accept_dt").setValue(dr.accept_dt);
        ref.child(name).child("status").setValue(dr.status);

        Toast.makeText(getApplicationContext(), "donor accepted", Toast.LENGTH_SHORT).show();
       /* donor donor=new donor();
        name = donor.fullname;
        donor.accept_dt=reg_date;
           name=getIntent().getStringExtra("fullname");
        receiver recr=new receiver(reg_date);
        fb= FirebaseDatabase.getInstance();
        ref= fb.getReference("receiver");
        ref.child(name).child("accept_date").setValue(recr.accept_date);
        */
    }
    public void rejectbtnclick(View view){
        fb= FirebaseDatabase.getInstance();
        ref= fb.getReference("donor");
        name=getIntent().getStringExtra("fullname");

        ref.child(name).removeValue();
        disqualified_data();


       /* Query removedata=ref.child("fullname").equalTo(name);
        removedata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    snapshot1.getRef().removeValue();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private void  disqualified_data() {
        Toast.makeText(getApplicationContext(), "donor rejected", Toast.LENGTH_SHORT).show();

        name=getIntent().getStringExtra("fullname");
        blood=getIntent().getStringExtra("bloodgr");
        addr=getIntent().getStringExtra("address");
        mobileno=getIntent().getStringExtra("mobile_var");
        issue=getIntent().getStringExtra("healthissue");
        status="rejected";
        donor dr=new donor(name,blood,addr,mobileno,issue,status);
        Toast.makeText(getApplicationContext(), "donor rejected", Toast.LENGTH_SHORT).show();
        ref1=fb.getReference("disqualified donor");
        ref1.child(name).setValue(dr);
        sendsms();

    }
    private void sendsms() {
        try {
            msg = "XYZ Blood Bank \n Sorry to inform you that, you can't donate blood in future because your blood sample result get positive in blood test.";
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(mobileno, null, msg, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}