package com.sahilguptalive.completeimageview.views;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import java.net.URL;

/**
 * Created on 06-08-2018.
 */
public interface ImageLoader {

    ImageLoader init(@NonNull MemoryCachingPolicy memoryCachingPolicy);

    ImageLoader with(@NonNull Activity activity);

    ImageLoader with(@NonNull Fragment fragment);

    ImageLoader with(@NonNull Context context);

    ImageLoader src(@NonNull String src);

    ImageLoader src(@NonNull URL src);

    ImageLoader placeholder(@DrawableRes int placeholder);

    ImageLoader loader(@ColorRes int darkColor, @ColorRes int lightColor);

    ImageLoader loader(@ColorRes int color);

    ImageLoader shape(@DrawableRes int shape);

    void into(@NonNull BitmapView view);

    void into(@NonNull ImageView view);

    ImageLoader scaleType(@NonNull ImageScaleType imageScaleType);

}
