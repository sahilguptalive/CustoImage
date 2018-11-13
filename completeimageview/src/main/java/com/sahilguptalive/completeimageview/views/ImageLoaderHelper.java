package com.sahilguptalive.completeimageview.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.net.URL;

/**
 * Created on 06-08-2018.
 */
class ImageLoaderHelper {

    @NonNull
    private final ImageLoaderImpl mImageLoader;

    private ImageLoaderHelper(@NonNull final ImageLoaderImpl imageLoader) {
        mImageLoader = imageLoader;
    }

    static ImageLoaderHelper newInstance(@NonNull ImageLoaderImpl imageLoader) {
        return new ImageLoaderHelper(imageLoader);
    }

    void load() {
        if (mImageLoader.mPlaceHolder != 0) {
            mImageLoader.mBitmapView.setImageDrawable(mImageLoader.mContext.
                    getResources().getDrawable(mImageLoader.mPlaceHolder));
        }
        URL url = mImageLoader.mSrc;
        if (url == null) {
            return;
        }
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mImageLoader.mBitmapView.setImageBitmap(bmp);
    }

    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return output;
    }
}
