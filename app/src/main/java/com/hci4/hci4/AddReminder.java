package com.hci4.hci4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddReminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
    }

    public void addReminder(View view) {
        Intent intent = new Intent(this, CreateVibration.class);
        startActivity(intent);
    }
}
