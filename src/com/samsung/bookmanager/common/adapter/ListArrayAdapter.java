package com.samsung.bookmanager.common.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.bookmanager.R;
import com.samsung.bookmanager.common.utils.ImageUtils;
import com.samsung.bookmanager.entities.Book;

import java.util.Map;

/**
 * User: anhnt
 * Date: 10/10/13
 * Time: 11:41 AM
 */
public class ListArrayAdapter
{
    public static SimpleCursorAdapter getSimpleCursorAdapter(final Context context, Cursor homeCursor)
    {
        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(context, R.layout.book_item,
                homeCursor, new String[]{
                Book.BOOK_NAME,
                Book.AUTHOR_NAME,
                Book.AVATAR},
                new int[]{
                        R.id.book_item_tvBookName,
                        R.id.book_item_tvAuthorName,
                        R.id.book_item_ivAvatar});

        SimpleCursorAdapter.ViewBinder viewBinder = new SimpleCursorAdapter.ViewBinder()
        {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int i)
            {
                switch (i)
                {
                    case 1:
                        ((TextView) view.findViewById(R.id.book_item_tvBookName)).setText("BOOK:" + cursor.getString(cursor.getColumnIndex(Book.BOOK_NAME)));
                        break;
                    case 2:
                        ((TextView) view.findViewById(R.id.book_item_tvAuthorName)).setText("AUTHOR:" + cursor.getString(cursor.getColumnIndex(Book.AUTHOR_NAME)));
                        break;
                    case 6:
                        ((ImageView) view.findViewById(R.id.book_item_ivAvatar)).setImageBitmap(getBitmap(context, cursor.getString(cursor.getColumnIndex(Book.AVATAR))));
                }
                return true;
            }
        };
        mAdapter.setViewBinder(viewBinder);
        return mAdapter;
    }

    private static Bitmap getBitmap(Context context, String path)
    {
        Bitmap photo = ImageUtils.getBitmap(path);
        Map<String, Integer> targetSize = ImageUtils.getSizeImage(context, R.drawable.book_avatar_detail);
        int width = targetSize.get("WIDTH");
        int height = targetSize.get("HEIGHT");
        return ImageUtils.getResizeBitmap(photo, width, height);
    }
}
