package com.hci4.hci4.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReminderDBHelper extends SQLiteOpenHelper {

    public ReminderDBHelper(Context context)
    {
        super(context, ReminderContract.DB_NAME, null, ReminderContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTable = "CREATE TABLE " + ReminderContract.ReminderEntry.TABLE + " ( " +
                ReminderContract.ReminderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ReminderContract.ReminderEntry.COL_REMINDER_TITLE + " TEXT NOT NULL, " +
                ReminderContract.ReminderEntry.COL_REMINDER_DATE + "," + ReminderContract.ReminderEntry.COL_REMINDER_TIME +
                "," + ReminderContract.ReminderEntry.COL_REMINDER_PATTERN + ");";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ReminderContract.ReminderEntry.TABLE);
        onCreate(db);
    }
}
