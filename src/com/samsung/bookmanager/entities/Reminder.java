package com.samsung.bookmanager.entities;

import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;

/**
 * User: Admin
 * Date: 1/22/14
 * Time: 11:53 PM
 */
public class Reminder implements Serializable
{
    public static final String ID = "_id";
    public static final String BOOK_ID = "bookId";
    public static final String BOOK_NAME = "bookName";
    public static final String TIME_REMIND = "timeRemind";
    public static final String MESSAGE_REMIND = "messageRemind";

    private int _id;
    private int bookId;
    private String bookName;
    private String timeRemind;
    private String messageRemind;

    public Reminder(int bookId, String bookName, String timeRemind, String messageRemind)
    {
        this.bookId = bookId;
        this.bookName = bookName;
        this.timeRemind = timeRemind;
        this.messageRemind = messageRemind;
    }

    public int get_id()
    {
        return _id;
    }

    public void set_id(int _id)
    {
        this._id = _id;
    }

    public int getBookId()
    {
        return bookId;
    }

    public void setBookId(int bookId)
    {
        this.bookId = bookId;
    }

    public String getBookName()
    {
        return bookName;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getTimeRemind()
    {
        return timeRemind;
    }

    public void setTimeRemind(String timeRemind)
    {
        this.timeRemind = timeRemind;
    }

    public String getMessageRemind()
    {
        return messageRemind;
    }

    public void setMessageRemind(String messageRemind)
    {
        this.messageRemind = messageRemind;
    }

    public ContentValues getContentValues()
    {
        ContentValues values = new ContentValues();
        values.put(BOOK_ID, bookId);
        values.put(BOOK_NAME, bookName);
        values.put(TIME_REMIND, timeRemind);
        values.put(MESSAGE_REMIND, messageRemind);
        return values;
    }

    public static Reminder getReminder(Cursor c)
    {
        Reminder reminder = new Reminder(c.getInt(c.getColumnIndex(BOOK_ID)),
                c.getString(c.getColumnIndex(BOOK_NAME)),
                c.getString(c.getColumnIndex(TIME_REMIND)),
                c.getString(c.getColumnIndex(MESSAGE_REMIND)));
        reminder.set_id(c.getInt(c.getColumnIndex(ID)));
        return reminder;
    }

    @Override
    public String toString()
    {
        return "BOOK: " + bookName + "; MESSAGE: " + messageRemind + "\n";
    }
}
