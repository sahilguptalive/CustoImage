package com.sahilguptalive.completeimageview.views;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

/**
 * Created on 06-08-2018.
 */
class BitmapViewWrapper implements BitmapView {

    @NonNull
    private final ImageView mView;

    BitmapViewWrapper(@NonNull final ImageView view) {
        mView = view;
    }

    @Override
    public void setImageBitmap(@Nullable final Bitmap bm) {
        mView.setImageBitmap(bm);
    }

    @Override
    public void setImageDrawable(@Nullable final Drawable drawable) {
        mView.setImageDrawable(drawable);
    }
}
