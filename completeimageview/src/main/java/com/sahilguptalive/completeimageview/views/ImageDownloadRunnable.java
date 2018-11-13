package com.sahilguptalive.completeimageview.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.ResultReceiver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created on 06-08-2018.
 */
class ImageDownloadRunnable implements Runnable {

    private final String mSrc;
    private final ResultReceiver mResultReciever;
    private final Handler mHandler;

    ImageDownloadRunnable(final String src,
                          final ResultReceiver resultReceiver,
                          final Handler handler) {
        mSrc = src;
        mResultReciever = resultReceiver;
        mHandler = handler;
    }

    @Override
    public void run() {
        URL url = null;
        try {
            url = new URL(mSrc);
        } catch (MalformedURLException e) {
            return;
        }
        mResultReciever.send();
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mImageLoader.mBitmapView.setImageBitmap(bmp);
    }
}
