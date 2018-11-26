package com.hci4.hci4.db;

import android.provider.BaseColumns;

public class ReminderContract {

    public static final String DB_NAME = "com.hci4.hci4";
    public static final int DB_VERSION = 1;

    public class ReminderEntry implements BaseColumns {

        public static final String TABLE = "reminders";
        public static final String COL_REMINDER_TITLE = "title";
        public static final String COL_REMINDER_DATE = "date";
        public static final String COL_REMINDER_TIME = "time";
        public static final String COL_REMINDER_PATTERN = "pattern";

    }
}
