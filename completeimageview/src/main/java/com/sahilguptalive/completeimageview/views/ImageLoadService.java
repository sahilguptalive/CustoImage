package com.sahilguptalive.completeimageview.views;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 06-08-2018.
 */
public class ImageLoadService extends Service {

    private static final String KEY_IMAGE_URL = "KEY_IMAGE_URL";
    private static final String KEY_RESPONSE_RECEIVER = "KEY_RESPONSE_RECEIVER";
    private static final int MAX_NUM_THREADS = 5;
    private ExecutorService mExecutorService;
    private Handler mHandler;

    @Nullable
    @Override
    public IBinder onBind(final Intent intent) {
        return new ImageLoadServiceBinder();
    }

    public static Intent createIntent(@NonNull String src) {
        Intent intent = new Intent();
        intent.putExtra(KEY_IMAGE_URL, src);
        return intent;
    }

    @Override
    public void onCreate() {
        mExecutorService = Executors.newFixedThreadPool(MAX_NUM_THREADS);
        mHandler = new Handler(Looper.myLooper());
        super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        if (intent == null) {
            return START_NOT_STICKY;
        }
        final String imageUrl = intent.getStringExtra(KEY_IMAGE_URL);
        final ResultReceiver resultReceiver = intent.getParcelableExtra(KEY_RESPONSE_RECEIVER);
        mExecutorService
                .execute(new ImageDownloadRunnable(imageUrl, resultReceiver, mHandler));
        return START_NOT_STICKY;
    }

    private class ImageLoadServiceBinder extends Binder {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mExecutorService.shutdown();
    }
}
