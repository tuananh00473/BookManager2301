package com.samsung.bookmanager.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.samsung.bookmanager.entities.Book;
import com.samsung.bookmanager.entities.Reminder;

/**
 * User: Admin
 * Date: 1/18/14
 * Time: 8:58 PM
 */
public class DataBaseHelper extends SQLiteOpenHelper
{
    String TABLE_BOOK = AppSetting.TABLE_BOOK;
    String CREATE_TABLE_BOOK =
            " CREATE TABLE " + TABLE_BOOK +
                    " (" +
                    Book.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Book.BOOK_NAME + " TEXT NOT NULL, " +
                    Book.AUTHOR_NAME + " TEXT, " +
                    Book.PUBLISHER + " TEXT, " +
                    Book.TYPE + " TEXT, " +
                    Book.COST + " TEXT, " +
                    Book.AVATAR + " TEXT" +
                    " );";

    String TABLE_REMINDER = AppSetting.TABLE_REMINDER;
    String CREATE_TABLE_REMINDER =
            " CREATE TABLE " + TABLE_REMINDER +
                    " (" +
                    Reminder.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Reminder.BOOK_ID + " INTEGER, " +
                    Reminder.BOOK_NAME + " TEXT, " +
                    Reminder.TIME_REMIND + " TEXT, " +
                    Reminder.MESSAGE_REMIND + " TEXT " +
                    " );";

    public DataBaseHelper(Context context)
    {
        super(context, AppSetting.DATABASE_NAME, null, AppSetting.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_BOOK);
        db.execSQL(CREATE_TABLE_REMINDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(DataBaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ". Old data will be destroyed");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        onCreate(db);
    }
}
