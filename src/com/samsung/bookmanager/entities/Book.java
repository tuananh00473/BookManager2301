package com.samsung.bookmanager.entities;

import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;

/**
 * User: Admin
 * Date: 1/18/14
 * Time: 9:27 PM
 */
public class Book implements Serializable
{
    public static final String ID = "_id";
    public static final String BOOK_NAME = "bookName";
    public static final String AUTHOR_NAME = "authorName";
    public static final String PUBLISHER = "publisher";
    public static final String TYPE = "type";
    public static final String COST = "cost";
    public static final String AVATAR = "avatar";

    private int _id;
    private String bookName;
    private String authorName;
    private String publisher;
    private String type;
    private String cost;
    private String avatar;

    public Book(String bookName, String authorName, String publisher, String type, String cost, String avatar)
    {
        this.bookName = bookName;
        this.authorName = authorName;
        this.publisher = publisher;
        this.type = type;
        this.cost = cost;
        this.avatar = avatar;
    }

    public int get_id()
    {
        return _id;
    }

    public void set_id(int _id)
    {
        this._id = _id;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getCost()
    {
        return cost;
    }

    public void setCost(String cost)
    {
        this.cost = cost;
    }

    public String getBookName()
    {
        return bookName;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getAuthorName()
    {
        return authorName;
    }

    public void setAuthorName(String authorName)
    {
        this.authorName = authorName;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public ContentValues getContentValues()
    {
        ContentValues values = new ContentValues();
        values.put(BOOK_NAME, bookName);
        values.put(AUTHOR_NAME, authorName);
        values.put(PUBLISHER, publisher);
        values.put(TYPE, type);
        values.put(COST, cost);
        values.put(AVATAR, avatar);
        return values;
    }

    public static Book getBook(Cursor c)
    {
        Book book = new Book(c.getString(c.getColumnIndex(BOOK_NAME)),
                c.getString(c.getColumnIndex(AUTHOR_NAME)),
                c.getString(c.getColumnIndex(PUBLISHER)),
                c.getString(c.getColumnIndex(TYPE)),
                c.getString(c.getColumnIndex(COST)),
                c.getString(c.getColumnIndex(AVATAR)));
        book.set_id(c.getInt(c.getColumnIndex(ID)));
        return book;
    }

    @Override
    public String toString()
    {
        return "BOOK: " + bookName + "; AUTHOR: " + authorName + "\n";
    }
}
