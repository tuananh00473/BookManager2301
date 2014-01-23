package com.samsung.bookmanager.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.samsung.bookmanager.R;

public class MyActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void showBookManagerActivity(View v)
    {
        Intent intent = new Intent(this, MainManagerActivity.class);
        startActivity(intent);
    }
}
