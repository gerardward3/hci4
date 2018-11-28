package com.hci4.hci4;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hci4.hci4.db.ReminderContract;
import com.hci4.hci4.db.ReminderDBHelper;

import java.util.Calendar;

import static java.lang.Integer.valueOf;

public class AddReminder extends AppCompatActivity implements View.OnClickListener {

    TextView date, time;

    public static final String CURRENT_DATE = "";
    private int mYear, mMonth, mDay, mHour, mMinute;
    private ReminderDBHelper mHelper;
    public static final String Colour = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        mHelper = new ReminderDBHelper(this);

        Intent intent = this.getIntent();
        String title = intent.getStringExtra(CreateVibration.TITLE);
        final String colour = intent.getStringExtra(CreateVibration.COLOUR);
        Button vibration = findViewById(R.id.vibration);

        vibration.setText(title);
        if (colour != null)
            vibration.setBackgroundColor(Color.parseColor(colour));


        final EditText reminderTitle = findViewById(R.id.reminderName);
        date = findViewById(R.id.dateOutput);
        time = findViewById(R.id.timeOutput);

        date.setOnClickListener(this);
        time.setOnClickListener(this);

        Button saveReminder = findViewById(R.id.createEvent);

        saveReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reminder = String.valueOf(reminderTitle.getText());
                String reminderDate = String.valueOf(date.getText());
                String reminderTime = String.valueOf(time.getText());

                SQLiteDatabase db = mHelper.getWritableDatabase();

                ContentValues values = new ContentValues();

                values.put(ReminderContract.ReminderEntry.COL_REMINDER_TITLE,reminder);
                values.put(ReminderContract.ReminderEntry.COL_REMINDER_DATE, reminderDate);
                values.put(ReminderContract.ReminderEntry.COL_REMINDER_TIME, reminderTime);
                db.insertWithOnConflict(ReminderContract.ReminderEntry.TABLE,
                        null,
                        values,
                        SQLiteDatabase.CONFLICT_REPLACE);
                db.close();

                Intent intent = new Intent(AddReminder.this, DayReminders.class);
                intent.putExtra(Colour, colour);
                startActivity(intent);
            }
        });



    }

    public void createVibration(View view) {
        Intent intent = new Intent(this, CreateVibration.class);
        startActivity(intent);
    }

    public void addEvent(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View v){
        if (v == date) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == time) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            time.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

    }
}
