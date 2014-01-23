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
import com.samsung.bookmanager.entities.Book;

import java.util.HashMap;

public class MyContentProvider extends ContentProvider
{
    // integer values used in content URI
    static final int BOOKS = 1;
    static final int BOOKS_ID = 2;
    static final UriMatcher uriMatcher;

    static String TABLE_NAME = AppSetting.TABLE_NAME;

    // maps content URI "patterns" to the integer values that were set above
    static
    {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AppSetting.PROVIDER_NAME, TABLE_NAME, BOOKS);
        uriMatcher.addURI(AppSetting.PROVIDER_NAME, TABLE_NAME + "/#", BOOKS_ID);
    }

    DataBaseHelper dataBaseHelper;

    // projection map for a query
    private static HashMap<String, String> BirthMap;

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
        // the TABLE_NAME to query on
        queryBuilder.setTables(TABLE_NAME);

        switch (uriMatcher.match(uri))
        {
            // maps all database column names
            case BOOKS:
                queryBuilder.setProjectionMap(BirthMap);
                break;
            case BOOKS_ID:
                queryBuilder.appendWhere("id=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (sortOrder == null || sortOrder.equals(""))
        {
            // No sorting-> sort on names by default
            sortOrder = Book.ID;
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
        long row = database.insert(TABLE_NAME, "", values);

        // If record is added successfully
        if (row > 0)
        {
            Uri newUri = ContentUris.withAppendedId(AppSetting.CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
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
                count = database.update(TABLE_NAME, values, selection, selectionArgs);
                break;
            case BOOKS_ID:
                count = database.update(TABLE_NAME, values,
                        "id=" + uri.getLastPathSegment() +
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
                count = database.delete(TABLE_NAME, selection, selectionArgs);
                break;
            case BOOKS_ID:
                String id = uri.getLastPathSegment();    //gets the id
                count = database.delete(TABLE_NAME, "id=" + id +
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
            // Get all friend-birthday records
            case BOOKS:
                return "vnd.android.cursor.dir/vnd.example.books";
            // Get a particular friend
            case BOOKS_ID:
                return "vnd.android.cursor.item/vnd.example.book";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


}
