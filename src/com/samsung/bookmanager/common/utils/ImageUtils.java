package com.samsung.bookmanager.common.utils;

import android.content.Context;
import android.graphics.*;

import java.util.HashMap;
import java.util.Map;

/**
 * User: anhnt
 * Date: 1/23/14
 * Time: 1:21 PM
 */
public class ImageUtils
{
    public static Bitmap getResizeBitmap(Bitmap bm, int newWidth, int newHeight)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);
        // RECREATE THE NEW BITMAP
        return Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, true);
    }

    public static Map<String, Integer> getSizeImage(Context context, int ResourceId)
    {
        Map<String, Integer> sizeMap = new HashMap<String, Integer>();
        BitmapFactory.Options dimensions = new BitmapFactory.Options();
        dimensions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), ResourceId, dimensions);
        sizeMap.put("HEIGHT", dimensions.outHeight);
        sizeMap.put("WIDTH", dimensions.outWidth);
        return sizeMap;
    }
}
