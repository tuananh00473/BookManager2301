package com.samsung.bookmanager.provider;

import android.content.*;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.bookmanager.common.AppSetting;
import com.samsung.bookmanager.common.DataBaseHelper;

import java.util.HashMap;

public class MyContentProvider extends ContentProvider
{
    // integer values used in content URI
    static final int BOOKS = 1;
    static final int BOOKS_ID = 2;
    static final int REMINDERS = 3;
    static final int REMINDERS_ID = 4;


    static final UriMatcher uriMatcher;

    static String TABLE_BOOK = AppSetting.TABLE_BOOK;
    static String TABLE_REMINDER = AppSetting.TABLE_REMINDER;

    // maps content URI "patterns" to the integer values that were set above
    static
    {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AppSetting.PROVIDER_NAME, TABLE_BOOK, BOOKS);
        uriMatcher.addURI(AppSetting.PROVIDER_NAME, TABLE_BOOK + "/#", BOOKS_ID);
        uriMatcher.addURI(AppSetting.PROVIDER_NAME, TABLE_REMINDER, REMINDERS);
        uriMatcher.addURI(AppSetting.PROVIDER_NAME, TABLE_REMINDER + "/#", REMINDERS_ID);
    }

    DataBaseHelper dataBaseHelper;

    // projection map for a query
    private static HashMap<String, String> BookMap;
    private static HashMap<String, String> ReminderMap;

    // database declarations
    private SQLiteDatabase database;

    @Override
    public boolean onCreate()
    {
        Context context = getContext();
        dataBaseHelper = new DataBaseHelper(context);
        // permissions to be writable
        database = dataBaseHelper.getWritableDatabase();

        return database != null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder)
    {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        // the TABLE_BOOK to query on

        switch (uriMatcher.match(uri))
        {
            // maps all database column names
            case BOOKS:
                queryBuilder.setTables(TABLE_BOOK);
                queryBuilder.setProjectionMap(BookMap);
                break;
            case BOOKS_ID:
                queryBuilder.setTables(TABLE_BOOK);
                queryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case REMINDERS:
                queryBuilder.setTables(TABLE_REMINDER);
                queryBuilder.setProjectionMap(ReminderMap);
                break;
            case REMINDERS_ID:
                queryBuilder.setTables(TABLE_REMINDER);
                queryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (sortOrder == null || sortOrder.equals(""))
        {
            // No sorting-> sort on names by default
            sortOrder = "_id";
        }
        Cursor cursor = queryBuilder.query(database, projection, selection,
                selectionArgs, null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        switch (uriMatcher.match(uri))
        {
            case BOOKS:
                long bookRow = database.insert(TABLE_BOOK, "", values);
                if (bookRow > 0)
                {
                    Uri newUri = ContentUris.withAppendedId(AppSetting.CONTENT_URI_BOOK, bookRow);
                    getContext().getContentResolver().notifyChange(newUri, null);
                    return newUri;
                }
                break;
            case REMINDERS:
                long reminderRow = database.insert(TABLE_REMINDER, "", values);
                if (reminderRow > 0)
                {
                    Uri newUri = ContentUris.withAppendedId(AppSetting.CONTENT_URI_REMINDER, reminderRow);
                    getContext().getContentResolver().notifyChange(newUri, null);
                    return newUri;
                }
                break;
        }
        throw new SQLException("Fail to add a new record into " + uri);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs)
    {
        int count;

        switch (uriMatcher.match(uri))
        {

            case BOOKS:
                count = database.update(TABLE_BOOK, values, selection, selectionArgs);
                break;
            case BOOKS_ID:
                count = database.update(TABLE_BOOK, values,
                        "_id=" + uri.getLastPathSegment() +
                                (!TextUtils.isEmpty(selection) ? " AND (" +
                                        selection + ')' : ""), selectionArgs);
                break;
            case REMINDERS:
                count = database.update(TABLE_REMINDER, values, selection, selectionArgs);
                break;
            case REMINDERS_ID:
                count = database.update(TABLE_REMINDER, values,
                        "_id=" + uri.getLastPathSegment() +
                                (!TextUtils.isEmpty(selection) ? " AND (" +
                                        selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        int count;

        switch (uriMatcher.match(uri))
        {
            case BOOKS:
                // delete all the records of the table
                count = database.delete(TABLE_BOOK, selection, selectionArgs);
                break;
            case BOOKS_ID:
                String bookId = uri.getLastPathSegment();    //gets the id
                count = database.delete(TABLE_BOOK, "_id=" + bookId +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            case REMINDERS:
                // delete all the records of the table
                count = database.delete(TABLE_REMINDER, selection, selectionArgs);
                break;
            case REMINDERS_ID:
                String reminderId = uri.getLastPathSegment();    //gets the id
                count = database.delete(TABLE_REMINDER, "_id=" + reminderId +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;


    }

    @Override
    public String getType(Uri uri)
    {
        switch (uriMatcher.match(uri))
        {
            case BOOKS:
                return "vnd.android.cursor.dir/vnd.example.books";
            case BOOKS_ID:
                return "vnd.android.cursor.item/vnd.example.book";
            case REMINDERS:
                return "vnd.android.cursor.dir/vnd.example.reminders";
            case REMINDERS_ID:
                return "vnd.android.cursor.item/vnd.example.reminder";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


}
