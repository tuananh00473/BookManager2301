package com.samsung.bookmanager.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.samsung.bookmanager.R;
import com.samsung.bookmanager.common.AppSetting;
import com.samsung.bookmanager.common.utils.ImageUtils;
import com.samsung.bookmanager.entities.Book;
import com.samsung.bookmanager.fragments.dialog.EditBookAvatarDialogFragment;
import com.samsung.bookmanager.service.BookService;

import java.io.File;
import java.util.Map;

/**
 * User: Admin
 * Date: 1/20/14
 * Time: 11:52 PM
 */
public class AddBookActivity extends Activity
{
    private BookService bookService;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_book_layout);
        bookService = new BookService(getBaseContext());
    }

    public void saveBook(View view)
    {
        bookService.insert(getBookInformation());
        startActivity(new Intent(this, MainManagerActivity.class));
        finish();
    }

    private Book getBookInformation()
    {
        return new Book(((EditText) findViewById(R.id.new_book_etBookName)).getText().toString(),
                ((EditText) findViewById(R.id.new_book_etAuthorName)).getText().toString(),
                ((EditText) findViewById(R.id.new_book_etPublisher)).getText().toString(),
                ((EditText) findViewById(R.id.new_book_etType)).getText().toString(),
                ((EditText) findViewById(R.id.new_book_etCost)).getText().toString(),
                "avatar_link");
    }

    public void editBookAvatar(View view)
    {
        EditBookAvatarDialogFragment menuDialogFragment = new EditBookAvatarDialogFragment();
        menuDialogFragment.setRetainInstance(true);
        menuDialogFragment.show(getFragmentManager(), "EditBookAvatarDialogFragment");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK)
        {
            Bitmap photo = null;
            if (requestCode == AppSetting.PICK_FROM_CAMERA)
            {
                photo = (Bitmap) data.getExtras().get("data");
            }
            if (requestCode == AppSetting.PICK_FROM_FILE)
            {
                Uri selectedImageUri = data.getData();
                String selectedImagePath = getPath(selectedImageUri);
                photo = getPreview(selectedImagePath);
            }
            Map<String, Integer> targetSize = ImageUtils.getSizeImage(this, R.drawable.book_avatar_detail);
            int width = targetSize.get("WIDTH");
            int height = targetSize.get("HEIGHT");
            Bitmap bitmapResize = ImageUtils.getResizeBitmap(photo, width, height);
            ((ImageView) findViewById(R.id.new_book_ivAvatar)).setImageBitmap(bitmapResize);
        }
    }

    public String getPath(Uri uri)
    {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public Bitmap getPreview(String fileName)
    {
        File image = new File(fileName);

        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(image.getPath(), bounds);
        if ((bounds.outWidth == -1) || (bounds.outHeight == -1))
        {
            return null;
        }
        int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight : bounds.outWidth;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = originalSize / 64;
        return BitmapFactory.decodeFile(image.getPath(), opts);
    }
}