package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class list extends AppCompatActivity {
MaterialButton donorlist,disqualifiedlist,recrlist;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        donorlist=(MaterialButton) findViewById(R.id.showdonor);
        disqualifiedlist=(MaterialButton) findViewById(R.id.showrecr);
        recrlist=(MaterialButton) findViewById(R.id.showdisqualified);




    }
}