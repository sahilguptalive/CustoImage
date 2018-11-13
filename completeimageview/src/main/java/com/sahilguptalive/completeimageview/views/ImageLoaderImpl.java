package com.sahilguptalive.completeimageview.views;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Works on following requirements:
 * <p>
 * <ul>
 * <li>Support for URL string to load image.</li>
 * <li>Support for placeholder images, will be shown when downloading the
 *                                          image from URL string.[Optional To Use]</li>
 * <li>Support for loading bar which would be shown as a colored border around the image view.</li>
 * <li>Support for Image Caching. Along with additional parameter maximum number of items
 *                                           and cache size.[Optional To Implement]</li>
 * <li>Support for 2-Color gradient for loading bar.[Optional To Implement]</li>
 * <li>Support for 2 image shapes: Circle and Rectangle.
 *    It will follow process of loading->cropping->resizing.[Optional To Implement]</li>
 * </ul>
 * <p>
 * <p>
 * Created on 06-08-2018.
 */
public class ImageLoaderImpl implements ImageLoader {

    MemoryCachingPolicy sMemoryCachePolicy;
    Context mContext;
    URL mSrc;
    @DrawableRes
    int mPlaceHolder;
    int[] mProgressColor;
    BitmapView mBitmapView;
    ImageScaleType mScaleType;

    public static ImageLoader newInstance() {
        return new ImageLoaderImpl();
    }

    private ImageLoaderImpl() {

    }

    @Override
    public ImageLoader init(@NonNull final MemoryCachingPolicy memoryCachingPolicy) {
        sMemoryCachePolicy = memoryCachingPolicy;
        return this;
    }

    @Override
    public ImageLoader with(@NonNull final Activity activity) {
        return with((Context) activity);
    }

    @Override
    public ImageLoader with(@NonNull final Fragment fragment) {
        return with((Context) fragment.getActivity());
    }

    @Override
    public ImageLoader with(@NonNull final Context context) {
        mContext = context;
        return this;
    }

    @Override
    public ImageLoader src(@NonNull final String src) {

        try {
            return src(new URL(src));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public ImageLoader src(@NonNull final URL src) {
        this.mSrc = src;
        return this;
    }

    @Override
    public ImageLoader placeholder(@DrawableRes final int placeholder) {
        mPlaceHolder = placeholder;
        return this;
    }

    @Override
    public ImageLoader loader(@ColorRes final int darkColor, @ColorRes final int lightColor) {
        mProgressColor = new int[]{darkColor, lightColor};
        return this;
    }

    @Override
    public ImageLoader loader(@ColorRes final int color) {
        return loader(color, 0);
    }

    @Override
    public ImageLoader shape(@DrawableRes final int shape) {
        return null;
    }

    @Override
    public void into(@NonNull final BitmapView view) {
        mBitmapView = view;
        validate();
        ImageLoaderHelper.newInstance(this).load();
    }

    @Override
    public void into(@NonNull final ImageView view) {
        into(new BitmapViewWrapper(view));
    }

    @Override
    public ImageLoader scaleType(@NonNull final ImageScaleType imageScaleType) {
        mScaleType = imageScaleType;
        return this;
    }

    private void validate() {

    }

}
