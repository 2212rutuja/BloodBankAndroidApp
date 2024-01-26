package com.example.logindemo;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class request_status extends AppCompatActivity {
  //  TextView currentstate;
    RecyclerView recyclerView;
    request_adapter request_adapter;
    FirebaseDatabase fb;
    DatabaseReference ref;
    ArrayList<receiver> list;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_status);
        recyclerView=findViewById(R.id.requestlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        request_adapter=new request_adapter(this,list);
        recyclerView.setAdapter(request_adapter);
        fb = FirebaseDatabase.getInstance();
        ref = fb.getReference("receiver");
        Query check_username = ref.orderByChild("user_reg").equalTo(user.username);
        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    receiver receiver=dataSnapshot.getValue(receiver.class);
                    list.add(receiver);
                }
                request_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}