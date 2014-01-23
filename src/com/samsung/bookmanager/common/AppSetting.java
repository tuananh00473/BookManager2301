package com.samsung.bookmanager.common;

import android.net.Uri;

/**
 * User: Admin
 * Date: 1/18/14
 * Time: 9:00 PM
 */
public class AppSetting
{
    public static final String PROVIDER_NAME = "com.samsung.bookmanager";
    public static final String DATABASE_NAME = "BookManager";
    public static final int DATABASE_VERSION = 1;


    public static final String TABLE_NAME = "books";
    public static final String URL = "content://" + AppSetting.PROVIDER_NAME + "/" + TABLE_NAME;
    public static final Uri CONTENT_URI = Uri.parse(URL);


    public static final int PICK_FROM_CAMERA = 999;
    public static final int PICK_FROM_FILE = 888;
}
