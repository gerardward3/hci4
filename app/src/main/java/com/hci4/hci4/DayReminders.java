package com.hci4.hci4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ListView;

import android.widget.TextView;


import com.hci4.hci4.db.ReminderContract;
import com.hci4.hci4.db.ReminderDBHelper;

import java.util.ArrayList;

public class DayReminders extends AppCompatActivity {

    private ReminderDBHelper mHelper;
    private ListView mReminderListView;
    private ArrayAdapter<String> mAdapter;
    public static String currentDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_reminders);

        Intent intent = this.getIntent();
        currentDate = intent.getStringExtra(MainActivity.DATE);

        if (currentDate == "")
            currentDate = intent.getStringExtra(AddReminder.CURRENT_DATE);
        TextView pageDate = findViewById(R.id.date);
        pageDate.setText(currentDate);

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
            //String date = cursor.
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
