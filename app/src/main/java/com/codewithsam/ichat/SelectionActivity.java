package com.codewithsam.ichat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionActivity extends AppCompatActivity {
    Bundle strings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Button CallBtn,VideoCallBtn;

        CallBtn = findViewById(R.id.call_btn);
        VideoCallBtn =findViewById(R.id.videocall_btn);

        CallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionActivity.this,CallActivity.class);
                startActivity(intent);
            }
        });

        VideoCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent r = new Intent();
                strings = r.getExtras();
                String name = strings.getString("Name");

                Intent intent = new Intent(SelectionActivity.this, VideoCallActivity.class);
                intent.putExtra("userName", name);

                startActivity(intent);
            }
        });
    }
}
