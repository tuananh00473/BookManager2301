package com.samsung.bookmanager.service;

import android.content.Context;
import android.database.Cursor;
import com.samsung.bookmanager.common.AppSetting;
import com.samsung.bookmanager.entities.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * User: anhnt
 * Date: 1/19/14
 * Time: 10:55 AM
 */
public class BookService
{
    private Context context;

    public BookService(Context context)
    {
        this.context = context;
    }

    public Book findById(int bookId)
    {
        String where = "_id=?";
        String[] selectionArgs = new String[]{String.valueOf(bookId)};
        Cursor c = context.getContentResolver().query(AppSetting.CONTENT_URI_BOOK, null, where, selectionArgs, Book.BOOK_NAME);
        if (c.moveToFirst())
        {
            return Book.getBook(c);
        }
        return null;
    }

    public List<Book> getAll()
    {
        List<Book> books = new ArrayList<Book>();
        Cursor c = context.getContentResolver().query(AppSetting.CONTENT_URI_BOOK, null, null, null, Book.BOOK_NAME);
        if (null != c && c.moveToFirst())
        {
            do
            {
                books.add(Book.getBook(c));
            } while (c.moveToNext());
        }
        return books;
    }

    public void insert(Book book)
    {
        context.getContentResolver().insert(AppSetting.CONTENT_URI_BOOK, book.getContentValues());
    }

    public void update(Book book)
    {
        String where = "_id=? AND bookName=? AND authorName=? AND publisher=? AND type=? AND cost=? AND avatar=?";
        String[] selectionArgs = new String[]{String.valueOf(book.get_id()), book.getBookName(), book.getAuthorName(), book.getPublisher(), book.getType(), book.getCost(), book.getAvatar()};
        context.getContentResolver().update(AppSetting.CONTENT_URI_BOOK, book.getContentValues(), where, selectionArgs);
    }

    public void delete(Book book)
    {
        String where = "_id=?";
        String[] selectionArgs = new String[]{String.valueOf(book.get_id())};
        context.getContentResolver().delete(AppSetting.CONTENT_URI_BOOK, where, selectionArgs);
    }

    public void deleteAll()
    {
        context.getContentResolver().delete(AppSetting.CONTENT_URI_BOOK, null, null);
    }

    public Cursor getCursor()
    {
        return context.getContentResolver().query(AppSetting.CONTENT_URI_BOOK, null, null, null, Book.ID);
    }
}
