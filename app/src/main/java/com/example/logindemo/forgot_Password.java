package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.concurrent.TimeUnit;

public class forgot_Password extends AppCompatActivity {
    String codeBySystem;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String phoneNo, username;
    TextInputEditText pinFromUser,name;
    TextInputEditText password_var,otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        name = (TextInputEditText) findViewById(R.id.user_name);
        password_var = (TextInputEditText) findViewById(R.id.newpwd);
        username=name.getEditableText().toString();
        otp=(TextInputEditText) findViewById(R.id.otp);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("datauser").child(username);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                phoneNo = snapshot.child("mobile_var").getValue(String.class);
                sendVerificationCodeToUser(phoneNo);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
        private void sendVerificationCodeToUser (String phoneNo){
            // [START start_phone_auth]

            PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth) //mAuth is defined on top
                    .setPhoneNumber(phoneNo)       // Phone number to verify
                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                    .setActivity(this)                 // Activity (for callback binding)
                    .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                    .build();
            PhoneAuthProvider.verifyPhoneNumber(options);
            // [END start_phone_auth]

        }

        private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        codeBySystem = s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        String code = phoneAuthCredential.getSmsCode();
                        if (code != null) {
                            pinFromUser.setText(code);
                            verifyCode(code);
                        }
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(forgot_Password.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                };

        private void verifyCode (String code){
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
            signInWithPhoneAuthCredential(credential);
        }

        private void signInWithPhoneAuthCredential (PhoneAuthCredential credential){
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //Verification completed successfully here Either
                                firebaseDatabase = FirebaseDatabase.getInstance();
                                databaseReference = firebaseDatabase.getReference("datauser").child(username);
                                String password_data = password_var.getText().toString();
                                databaseReference.child("password").setValue(password_data);
                                Toast.makeText(forgot_Password.this, "Password updated successfully", Toast.LENGTH_SHORT).show();

                            } else {
                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                    Toast.makeText(forgot_Password.this, "Verification Not Completed! Try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }
        public void passwordverifyclick (View view){
            String code = pinFromUser.toString();
            if (!code.isEmpty()) {
                verifyCode(code);
            }
        }
}