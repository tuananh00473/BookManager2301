package com.samsung.bookmanager.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.samsung.bookmanager.entities.Book;

/**
 * User: Admin
 * Date: 1/18/14
 * Time: 8:58 PM
 */
public class DataBaseHelper extends SQLiteOpenHelper
{
    String TABLE_NAME = AppSetting.TABLE_NAME;
    String CREATE_TABLE =
            " CREATE TABLE " + TABLE_NAME +
                    " (" +
                    Book.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Book.BOOK_NAME + " TEXT NOT NULL, " +
                    Book.AUTHOR_NAME + " TEXT, " +
                    Book.PUBLISHER + " TEXT, " +
                    Book.TYPE + " TEXT, " +
                    Book.COST + " TEXT, " +
                    Book.AVATAR + " TEXT" +
                    " );";

    public DataBaseHelper(Context context)
    {
        super(context, AppSetting.DATABASE_NAME, null, AppSetting.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(DataBaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ". Old data will be destroyed");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
