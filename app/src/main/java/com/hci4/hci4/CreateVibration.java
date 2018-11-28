package com.hci4.hci4;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class CreateVibration extends AppCompatActivity {

    public static final String TITLE = "CHOOSE PATTERN";
    public static final String COLOUR = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vibration);
    }

    public void playPattern(View view) {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        Button setPattern = findViewById(R.id.setPatternButton);
        switch (view.getId()) {
            case (R.id.shoppingPatternButton):
                vibe.vibrate(new long[]{0, 750, 100, 200, 100, 200}, -1);
                final Button shopping = findViewById(R.id.shoppingPatternButton);
                final RadioButton shoppingRadio = findViewById((R.id.shoppingRadio));
                setPattern.setOnClickListener( new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CreateVibration.this, AddReminder.class);
                        intent.putExtra(TITLE,String.valueOf(shoppingRadio.getText()));
                        ColorDrawable buttonColor = (ColorDrawable) shopping.getBackground();
                        int intColor = buttonColor.getColor();
                        String hexColor = String.format("#%06X", (0xFFFFFF & intColor));
                        Log.d("colour",hexColor);
                        intent.putExtra(COLOUR, hexColor);
                        startActivity(intent);
                    }
                                             }
                );
            break;
            case (R.id.cleaningPatternButton):
                vibe.vibrate(new long[]{0, 200, 100, 100, 50, 100, 50, 100, 200, 200}, -1);
                final Button cleaning = findViewById(R.id.cleaningPatternButton);
                final RadioButton cleaningRadio = findViewById((R.id.cleaningRadio));
                setPattern.setOnClickListener( new Button.OnClickListener(){
                                                 @Override
                                                 public void onClick(View v) {
                                                     Intent intent = new Intent(CreateVibration.this, AddReminder.class);
                                                     intent.putExtra(TITLE,String.valueOf(cleaningRadio.getText()));
                                                     ColorDrawable buttonColor = (ColorDrawable) cleaning.getBackground();
                                                     int intColor = buttonColor.getColor();
                                                     String hexColor = String.format("#%06X", (0xFFFFFF & intColor));
                                                     Log.d("colour",hexColor);
                                                     intent.putExtra(COLOUR, hexColor);
                                                     startActivity(intent);
                                                 }
                                             }
                );
                break;
            case (R.id.friendsPatternButton):
                vibe.vibrate(new long[]{0, 100, 50, 100, 50, 100, 50, 100, 50, 500, 100, 500}, -1);
                final Button friends = findViewById(R.id.friendsPatternButton);
                final RadioButton friendsRadio = findViewById((R.id.friendsRadio));
                setPattern.setOnClickListener( new Button.OnClickListener(){
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(CreateVibration.this, AddReminder.class);
                                                    intent.putExtra(TITLE,String.valueOf(friendsRadio.getText()));
                                                    ColorDrawable buttonColor = (ColorDrawable) friends.getBackground();
                                                    int intColor = buttonColor.getColor();
                                                    String hexColor = String.format("#%06X", (0xFFFFFF & intColor));
                                                    Log.d("colour",hexColor);
                                                    intent.putExtra(COLOUR, hexColor);
                                                    startActivity(intent);
                                                }
                                            }
                );
                break;
            case (R.id.petPatternButton):
                vibe.vibrate(new long[]{0, 250, 250, 250, 200, 100, 50, 100}, -1);
                final Button pet = findViewById(R.id.petPatternButton);
                final RadioButton petRadio = findViewById((R.id.petRadio));
                setPattern.setOnClickListener( new Button.OnClickListener(){
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(CreateVibration.this, AddReminder.class);
                                                intent.putExtra(TITLE,String.valueOf(petRadio.getText()));
                                                ColorDrawable buttonColor = (ColorDrawable) pet.getBackground();
                                                int intColor = buttonColor.getColor();
                                                String hexColor = String.format("#%06X", (0xFFFFFF & intColor));
                                                Log.d("colour",hexColor);
                                                intent.putExtra(COLOUR, hexColor);
                                                startActivity(intent);
                                            }
                                        }
                );
            break;

        }
    }


    public void setPattern(View view, int colour, String title) {
        Intent intent = new Intent(CreateVibration.this, AddReminder.class);
        intent.putExtra(TITLE,title);
        intent.putExtra(String.valueOf(COLOUR), colour);
        startActivity(intent);
    }
}
