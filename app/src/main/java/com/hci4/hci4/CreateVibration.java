package com.hci4.hci4;

import android.content.Context;
import android.content.Intent;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateVibration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vibration);
    }

    public void playPattern1(View view) {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(new long[]{0, 100, 100, 100, 300, 500}, -1);
    }

    public void playPattern2(View view) {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(new long[]{0, 750, 100, 200, 100, 200}, -1);
    }

    public void setPattern(View view) {
        Intent intent = new Intent(this, AddReminder.class);
        startActivity(intent);
    }
}
