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

    public void playPattern(View view) {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        switch (view.getId()) {
            case (R.id.shoppingPatternButton):
                vibe.vibrate(new long[]{0, 750, 100, 200, 100, 200}, -1);
            break;
            case (R.id.cleaningPatternButton):
                vibe.vibrate(new long[]{0, 200, 100, 100, 50, 100, 50, 100, 200, 200}, -1);
            break;
            case (R.id.friendsPatternButton):
                vibe.vibrate(new long[]{0, 100, 50, 100, 50, 100, 50, 100, 50, 500, 100, 500}, -1);
            break;
            case (R.id.petPatternButton):
                vibe.vibrate(new long[]{0, 250, 250, 250, 200, 100, 50, 100}, -1);
            break;

        }
    }


    public void setPattern(View view) {
        Intent intent = new Intent(this, AddReminder.class);
        startActivity(intent);
    }
}
