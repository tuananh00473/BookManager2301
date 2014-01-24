package com.samsung.bookmanager.service;

import android.content.Context;
import android.database.Cursor;
import com.samsung.bookmanager.common.AppSetting;
import com.samsung.bookmanager.entities.Book;
import com.samsung.bookmanager.entities.Reminder;

import java.util.ArrayList;
import java.util.List;

/**
 * User: anhnt
 * Date: 1/19/14
 * Time: 10:55 AM
 */
public class ReminderService
{
    private Context context;

    public ReminderService(Context context)
    {
        this.context = context;
    }

    public Reminder findById(int reminderId)
    {
        String where = "_id=?";
        String[] selectionArgs = new String[]{String.valueOf(reminderId)};
        Cursor c = context.getContentResolver().query(AppSetting.CONTENT_URI_BOOK, null, where, selectionArgs, Book.BOOK_NAME);
        if (c.moveToFirst())
        {
            return Reminder.getReminder(c);
        }
        return null;
    }

    public List<Reminder> getAll()
    {
        List<Reminder> reminders = new ArrayList<Reminder>();
        Cursor c = context.getContentResolver().query(AppSetting.CONTENT_URI_REMINDER, null, null, null, Reminder.TIME_REMIND);
        if (null != c && c.moveToFirst())
        {
            do
            {
                reminders.add(Reminder.getReminder(c));
            } while (c.moveToNext());
        }
        return reminders;
    }

    public void insert(Reminder reminder)
    {
        context.getContentResolver().insert(AppSetting.CONTENT_URI_REMINDER, reminder.getContentValues());
    }

    public void update(Reminder reminder)
    {
        String where = "_id=? AND bookId=? AND bookName=? AND timeRemind=? AND messageRemind=?";
        String[] selectionArgs = new String[]{String.valueOf(reminder.get_id()), String.valueOf(reminder.getBookId()), reminder.getBookName(), reminder.getTimeRemind(), reminder.getMessageRemind()};
        context.getContentResolver().update(AppSetting.CONTENT_URI_REMINDER, reminder.getContentValues(), where, selectionArgs);
    }

    public void delete(Reminder reminder)
    {
        String where = "_id=?";
        String[] selectionArgs = new String[]{String.valueOf(reminder.get_id())};
        context.getContentResolver().delete(AppSetting.CONTENT_URI_REMINDER, where, selectionArgs);
    }

    public void deleteAll()
    {
        context.getContentResolver().delete(AppSetting.CONTENT_URI_REMINDER, null, null);
    }

    public Cursor getCursor()
    {
        return context.getContentResolver().query(AppSetting.CONTENT_URI_REMINDER, null, null, null, Reminder.ID);
    }
}
