package com.piotr.localweather.api.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * @author piotr on 16.05.16.
 */
public class BitmapFactory implements com.piotr.localweather.api.model.Bitmap {

    Drawable drawable;
    Bitmap bitmap;

    BitmapFactory(Drawable mDrawable) {
        this.drawable = mDrawable;
    }

    public static Bitmap convert(Drawable drawable) {
        return new BitmapFactory(drawable)
                .getBitmapFromDrawable()
                .createBitmap()
                .drawCanvas();
    }

    public BitmapFactory getBitmapFromDrawable() {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                bitmap = bitmapDrawable.getBitmap();
                return this;
            }
        }
        bitmap = null;
        return this;
    }

    public BitmapFactory createBitmap() {
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color
            // bitmap will be created of 1x1 pixel
        } else {
            bitmap = (Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable
                    .getIntrinsicHeight(), Bitmap.Config.ARGB_8888));
        }
        return this;
    }

    @Override
    public Bitmap drawCanvas() {
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    public Bitmap create(Drawable drawable) {
        return convert(drawable);
    }
}