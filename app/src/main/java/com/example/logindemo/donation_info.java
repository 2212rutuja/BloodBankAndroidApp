package com.example.logindemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParsePosition;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

public class donation_info extends AppCompatActivity {
    TextView last_donated, next_donatn, total_donation;
    FirebaseDatabase fb;
    DatabaseReference ref;

    int diff =0,donated_count;

    String date, mobileno, msg;
    String name, d, status1, status2;

    private static final int CHANNEL_ID=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_info);
        last_donated = (TextView) findViewById(R.id.last_donation);
        next_donatn = findViewById(R.id.next_donation);
        total_donation = findViewById(R.id.donation_count);
        name = user.username;


            fb = FirebaseDatabase.getInstance();
            ref = fb.getReference("donor");
            //date= String.valueOf(ref.child("jkl").child("accept_dt").get());
            //last_donated.setText(date);fb = FirebaseDatabase.getInstance();

            //Query check_username = ref.child(name).child("status").equalTo("accepted");
            // check_username.addListenerForSingleValueEvent(new ValueEventListener() {
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    mobileno = snapshot.child(name).child("mobile_var").getValue(String.class);
                    status1=snapshot.child(name).child("status").getValue(String.class);
                    donated_count=  snapshot.child(name).child("donation_count").getValue(Integer.class);
                   // if(status1 == "accepted") {
                        date = snapshot.child(name).child("accept_dt").getValue(String.class);
                        last_donated.setText(date);

                         finddate();

                    //if (!date.isEmpty()) {
                    next_donatn.setText(d);
                    total_donation.setText(String.valueOf(donated_count));
                   // makenotificaton();
                    if (diff == 0) {
                        try {
                            msg = "XYZ Blood Bank \n 90 days completed,requesting you to come for next donation";
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(mobileno, null, msg, null, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "You are not registered as donor", Toast.LENGTH_SHORT).show();
                    }
                    /*}else {
                        disqualified();
                    }*/
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "failed to load data", Toast.LENGTH_SHORT).show();
                }
            });

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


    private void makenotificaton() {
        if(diff==0) {
           Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.blood_reception, null);
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap largeicon = bitmapDrawable.getBitmap();
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification notification = new Notification.Builder(this)
                    .setLargeIcon(largeicon)
                    .setSmallIcon(R.drawable.blood_reception)
                    .setContentText("90 days completed")
                    .setSubText("requesting you to come for next donation")
                    //        .setChannelId(CHANNEL_ID)
                    .build();

            nm.notify(1, notification);
            try {
                msg = "90 days completed,requesting you to come for next donation";
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(mobileno, null, msg, null, null);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void finddate() {

    try {

            SimpleDateFormat format1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy");
            format1.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));
            ParsePosition pos = new ParsePosition(0);

            Date dt1 = format1.parse((String) date, pos);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt1);
            //cal.setTime((Date) dt1);
            cal.add(Calendar.DAY_OF_MONTH, 90);
            String After = format1.format(cal.getTime());
            Date dt2 = format1.parse(After);
            dt1 = Calendar.getInstance().getTime();
            //long timediff=obj.parse(dateAfter).getTime()-obj.parse(date).getTime();
            long timediff = dt2.getTime() - dt1.getTime();
            diff = (int) ((timediff / (1000 * 60 * 60 * 24)) % 365);
            diff = 0;
            d = String.valueOf(diff);

        } catch(ParseException e){
            e.printStackTrace();
        }
    }

   /*

    Query check_name = ref.child("fullname").equalTo(name);
        check_name.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            status1=snapshot.child(name).child("accept_dt").getValue(String.class);
            while(status1.isEmpty()){}
            if(status1.equals("accepted")) {
                try {
                    fb = FirebaseDatabase.getInstance();
                    ref = fb.getReference("donor");
                    //date= String.valueOf(ref.child("jkl").child("accept_dt").get());
                    //last_donated.setText(date);fb = FirebaseDatabase.getInstance();
                    //        ref = fb.getReference("receiver");
                    Query check_username = ref.child("fullname").equalTo(name);
                    check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                        //  ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //   for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                            date = snapshot.child(name).child("accept_dt").getValue(String.class);
                            mobileno = snapshot.child(name).child("mobile_var").getValue(String.class);
                            last_donated.setText(date);
                            // }
                            finddate();
                            //if (!date.isEmpty()) {
                            next_donatn.setText(d);
                            if (diff == 0) {
                                try {
                                    msg = "XYZ Blood Bank \n 90 days completed,requesting you to come for next donation";
                                    SmsManager smsManager = SmsManager.getDefault();
                                    smsManager.sendTextMessage(mobileno, null, msg, null, null);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "failed to load data", Toast.LENGTH_SHORT).show();
                            }
                            // makenotificaton();
                            // }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), "You are not registered as donor", Toast.LENGTH_SHORT).show();
                        }
                    });

           int days=finddate(date);
           String d= String.valueOf(days);
           next_donatn.setText(d);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

      @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    */
   public void disqualified() {
      /* ref = fb.getReference("disqualified donor");
       Query checkname = ref.child("fullname").equalTo(name);
       checkname.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               status2 = snapshot.child(name).child("accept_dt").getValue(String.class);
               if (status2.equals("rejected")) {
                  */ try {
                       fb = FirebaseDatabase.getInstance();
                       ref = fb.getReference("disqualified donor").child(name);
                     //  Query check_user = ref.child("fullname").equalTo(name);
                     //  check_user.addListenerForSingleValueEvent(new ValueEventListener() {
                            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                               status2=snapshot.child("status").getValue(String.class);
                            //if(status2=="rejected") {
                                mobileno = snapshot.child("mobile_var").getValue(String.class);
                                try {

                                    msg = "XYZ Blood Bank \n Sorry to inform you that, you can't donate blood in future because your blood sample result get positive in blood test.";
                                    SmsManager smsManager = SmsManager.getDefault();
                                    smsManager.sendTextMessage(mobileno, null, msg, null, null);
                                    next_donatn.setText("Sorry,You can't donate.Check further deatils in sms");

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            /*}
                             else{
                                   next_donatn.setText("You are not registered as donor");
                               }*/
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {
                               Toast.makeText(getApplicationContext(), "You are not registered as donor", Toast.LENGTH_SHORT).show();
                           }
                       });
                       //sendsms();

                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
         /* }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

   }*/

}