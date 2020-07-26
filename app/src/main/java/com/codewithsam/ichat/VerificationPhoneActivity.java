package com.codewithsam.ichat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerificationPhoneActivity extends AppCompatActivity {

    Button btn_send_otp, btn_signIn;
    EditText et_phone, et_code;
    FirebaseAuth mAuth;
    String codeSent;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            codeSent = s;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_phone);


        btn_send_otp = findViewById(R.id.buttonGetVerificationCode);
        btn_signIn = findViewById(R.id.buttonSignIn);
        et_phone = findViewById(R.id.editTextPhone);
        et_code = findViewById(R.id.editTextCode);
        mAuth = FirebaseAuth.getInstance();

        btn_send_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();
            }
        });


        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifySigninCode();
            }
        });

    }

    private void verifySigninCode() {
        String code = et_code.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void sendVerificationCode() {

        String phoneNumber = et_phone.getText().toString();

        if (phoneNumber.isEmpty()) {
            et_phone.setError("Phone Number is Required");
            et_phone.requestFocus();
            return;
        }

        if (phoneNumber.length() < 10) {
            et_phone.setError("Please enter a valid Phone Number");
            et_phone.requestFocus();
            return;
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, this, mCallbacks);


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(VerificationPhoneActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(VerificationPhoneActivity.this, ProfileEntry.class);
                            startActivity(intent);

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(VerificationPhoneActivity.this, "Incorrect verification code", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}


