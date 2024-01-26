package com.example.logindemo;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class admin_login extends AppCompatActivity {
    MaterialButton loginbtn;
    TextInputEditText username_var,password_var;
    FirebaseDatabase firebaseDatabase1;
    DatabaseReference databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        username_var = (TextInputEditText) findViewById(R.id.username2);
        password_var = (TextInputEditText) findViewById(R.id.password2);
        loginbtn = (MaterialButton) findViewById(R.id.loginbtn2);

    }

    public void loginbuttnclick(View view) {

        String name = username_var.getText().toString();
        String password = password_var.getText().toString();

        if (!name.isEmpty()) {
            username_var.setError(null);

            if (!password.isEmpty()) {
                password_var.setError(null);
                final String username_data = username_var.getText().toString();
                final String password_data = password_var.getText().toString();

                firebaseDatabase1 = FirebaseDatabase.getInstance();
                databaseReference1 = firebaseDatabase1.getReference("datauser");

                Query check_username = databaseReference1.orderByChild("username").equalTo(username_data);
                check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                           username_var.setError(null);
                            String passwordcheck =snapshot.child(username_data).child("password").getValue(String.class);
                            if (password_data.equals(passwordcheck)) {
                                password_var.setError(null);
                                user.username=snapshot.child(username_data).child("username").getValue(String.class);
                                user.mobileno = snapshot.child(username_data).child("mobileno").getValue(String.class);
                                String namedB = snapshot.child(username_data).child("username").getValue(String.class);
                                Intent intent1 = new Intent(getApplicationContext(), admin_profile.class);
                                intent1.putExtra("username",namedB);
                                startActivity(intent1);
                                finish();

                                Toast.makeText(getApplicationContext(), "Admin login successful", LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), admin_dashboard.class);
                                startActivity(intent);
                            }
                        } else {
                            username_var.setError("user does not exist");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            } else {
                password_var.setError("please enter the correct password");
            }
        } else {
            username_var.setError("please enter the username");
        }

    }


}