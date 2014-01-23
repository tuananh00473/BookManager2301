package com.samsung.bookmanager.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.samsung.bookmanager.R;
import com.samsung.bookmanager.entities.Book;

/**
 * User: Admin
 * Date: 1/21/14
 * Time: 9:10 PM
 */
public class BookDetailInforActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_book_layout);
        Bundle bundle = getIntent().getExtras();
        Book book = (Book) bundle.get("BOOK");

        ((TextView) findViewById(R.id.tvName)).setText(book.getBookName());
    }

    public void cancelOrBack(View view)
    {
        finish();
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}