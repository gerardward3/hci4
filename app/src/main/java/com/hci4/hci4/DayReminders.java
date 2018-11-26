package com.hci4.hci4;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hci4.hci4.db.ReminderContract;
import com.hci4.hci4.db.ReminderDBHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class DayReminders extends AppCompatActivity {

    private ReminderDBHelper mHelper;
    private ListView mReminderListView;
    private ArrayAdapter<String> mAdapter;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_reminders);

        Intent intent = getIntent();
        String date = intent.getStringExtra(MainActivity.DATE);

        TextView pageDate = findViewById(R.id.date);
        pageDate.setText(date);

        mHelper = new ReminderDBHelper(this);
        mReminderListView = (ListView) findViewById(R.id.reminders) ;

        updateUI();
        Button addReminder = findViewById(R.id.addReminder);
        addReminder.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
               Intent addReminderIntent = new Intent(DayReminders.this, AddReminder.class);
               startActivity(addReminderIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_add_reminder:

                final Context context = getBaseContext();

                final EditText reminderEditText = new EditText(this);
                final TextView date = new TextView(this);
                final TextView time = new TextView(this);

                date.setOnClickListener(new TextView.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog (context, new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }
                });
                time.setOnClickListener(new TextView.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {

                                        time.setText(hourOfDay + ":" + minute);
                                    }
                                }, mHour, mMinute, false);
                        timePickerDialog.show();
                    }
                });

                ScrollView layout = new ScrollView(context);

                layout.addView(reminderEditText);
                layout.addView(date);
                layout.addView(time);

                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add Reminder")
                        .setMessage("Create a new Reminder")
                        .setView(layout)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String reminder = String.valueOf(reminderEditText.getText());
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
                                updateUI();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    public void createVibration(View view) {
        Intent intent = new Intent(this, CreateVibration.class);
        startActivity(intent);
    }

    public void deleteReminder(View view) {
        View parent = (View) view.getParent();
        TextView reminderTextView = (TextView) parent.findViewById(R.id.reminder_title);
        String reminder = String.valueOf(reminderTextView.getText());
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(ReminderContract.ReminderEntry.TABLE,ReminderContract.ReminderEntry.COL_REMINDER_TITLE + " = ?", new String[]{reminder});
        db.close();
        updateUI();
    }
    public void updateUI() {
        ArrayList<String> reminderList = new ArrayList();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(ReminderContract.ReminderEntry.TABLE,
                new String[]{ReminderContract.ReminderEntry._ID, ReminderContract.ReminderEntry.COL_REMINDER_TITLE,
                ReminderContract.ReminderEntry.COL_REMINDER_DATE, ReminderContract.ReminderEntry.COL_REMINDER_TIME},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(ReminderContract.ReminderEntry.COL_REMINDER_TITLE);
            reminderList.add(cursor.getString(idx));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<String>(this, R.layout.reminder_item,R.id.reminder_title,reminderList);
            mReminderListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(reminderList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }
}
