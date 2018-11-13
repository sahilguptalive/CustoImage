package com.sahilguptalive.completeimageview.views;

/**
 * Created on 06-08-2018.
 */
public interface MemoryCachingPolicy {

    /**
     * @return the maximum number of items to store in cache.
     */
    int getMaxCount();

    /**
     * @return the maximum file size for storing cache files
     */
    int getMaxCacheSize();
}
