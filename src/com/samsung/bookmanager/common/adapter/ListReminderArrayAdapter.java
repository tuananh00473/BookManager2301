package com.samsung.bookmanager.common.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.TextView;
import com.samsung.bookmanager.R;
import com.samsung.bookmanager.entities.Reminder;

/**
 * User: anhnt
 * Date: 10/10/13
 * Time: 11:41 AM
 */
public class ListReminderArrayAdapter
{
    public static SimpleCursorAdapter getSimpleCursorAdapter(Context context, Cursor homeCursor)
    {
        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(context, R.layout.reminder_item,
                homeCursor, new String[]{
                Reminder.BOOK_NAME,
                Reminder.TIME_REMIND},
                new int[]{
                        R.id.reminder_item_tvBookName,
                        R.id.reminder_item_tvTimeReminder});

        SimpleCursorAdapter.ViewBinder viewBinder = new SimpleCursorAdapter.ViewBinder()
        {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int i)
            {
                switch (i)
                {
                    case 1:
                        ((TextView) view.findViewById(R.id.reminder_item_tvBookName)).setText("BOOK:" + cursor.getString(cursor.getColumnIndex(Reminder.BOOK_NAME)));
                        break;
                    case 2:
                        ((TextView) view.findViewById(R.id.reminder_item_tvTimeReminder)).setText("REMINDER:" + cursor.getString(cursor.getColumnIndex(Reminder.TIME_REMIND)));
                        break;
                }
                return true;
            }
        };
        mAdapter.setViewBinder(viewBinder);
        return mAdapter;
    }
}
