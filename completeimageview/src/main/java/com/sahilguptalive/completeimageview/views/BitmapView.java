package com.sahilguptalive.completeimageview.views;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

/**
 * Created on 06-08-2018.
 */
public interface BitmapView {

    void setImageBitmap(@Nullable Bitmap bm);

    void setImageDrawable(@Nullable Drawable drawable);
}
