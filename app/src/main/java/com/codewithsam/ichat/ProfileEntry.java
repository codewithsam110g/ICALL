package com.codewithsam.ichat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStreamWriter;
import java.io.InputStreamReader;

public class ProfileEntry extends AppCompatActivity {

    String Name, Description;
    EditText et_name, et_description;
    private String UserFilename = "UserData.txt";
    private String UserFilepath = "UserDataStorage";
    File userExist;
    String myData = "";
    private String profileFilename = "UserProfile.txt";
    private String profileFilepath = "UserProfileStorage";
    File profileExists;
    String profileData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_entry);

        Button mButton;

        mButton = findViewById(R.id.set_profile_btn);
        et_name = findViewById(R.id.Full_Name_et);
        et_description = findViewById(R.id.description_et);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(TextUtils.equals(et_name.getText().toString(), "")) && (!(TextUtils.equals(et_description.getText().toString(), "")))) {
                    Name = et_name.getText().toString();
                    Description = et_description.getText().toString();

                    writeToUserDataFile(Name, ProfileEntry.this);
                    writeToProfileFile(Name, ProfileEntry.this);


                    Intent intent = new Intent(ProfileEntry.this, SelectionActivity.class);
                    intent.putExtra("Name", Name);
                    startActivity(intent);


                } else {
                    et_name.setError("Please Enter Your Name and Description");

                }


            }
        });

    }

    private void writeToUserDataFile(@NonNull String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("userDat.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void writeToProfileFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("profileDat.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

}
