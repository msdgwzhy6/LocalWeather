package com.piotr.localweather.model;

import android.graphics.drawable.Drawable;

/**
 * @author piotr on 16.05.16.
 */
public interface Bitmap {
    android.graphics.Bitmap drawCanvas();

    android.graphics.Bitmap create(Drawable drawable);

}

