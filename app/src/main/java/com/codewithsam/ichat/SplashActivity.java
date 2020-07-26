package com.codewithsam.ichat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    String myData = "";
    String profileData = "";

    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        btnNext = findViewById(R.id.next_btn);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myData = readFromUserFile(SplashActivity.this);
                profileData = readFromProfileFile(SplashActivity.this);


                if (myData.equals("") || myData.equals(" ")) {


                    Intent intent = new Intent(SplashActivity.this, VerificationPhoneActivity.class);
                    startActivity(intent);
                }
                 if ((myData != "" || myData != " ") && (profileData.equals("")|| profileData.equals(" "))) {


                    Intent intent = new Intent(SplashActivity.this, ProfileEntry.class);
                    startActivity(intent);
                }
                 if ((myData != "" || myData != " ") && (!profileData.equals("")|| !profileData.equals(" "))) {


                    Intent intent = new Intent(SplashActivity.this, CallActivity.class);
                    startActivity(intent);
                }


            }
        });


    }


    private String readFromUserFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("userDat.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    private String readFromProfileFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("profileDat.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
