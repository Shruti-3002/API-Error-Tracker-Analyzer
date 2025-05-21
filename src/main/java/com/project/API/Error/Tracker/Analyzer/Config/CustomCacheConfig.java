package com.project.API.Error.Tracker.Analyzer.Config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableCaching
public class CustomCacheConfig {
    public static final String API_RESPONSE_CACHE = "apiResponseCache";

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Collections.singletonList(new FiloCache(API_RESPONSE_CACHE, 5)));
        return cacheManager;
    }

    static public class FiloCache extends ConcurrentMapCache {
        private final int maxSize;
        private final Deque<Object> keyOrder = new LinkedList<>();
        private final ConcurrentHashMap<Object, Object> store;

        public FiloCache(String name, int maxSize) {
            super(name, new ConcurrentHashMap<>(), false);
            this.maxSize = maxSize;
            this.store = (ConcurrentHashMap<Object, Object>) this.getNativeCache();
        }

        @Override
        public void put(Object key, Object value) {
            synchronized (keyOrder) {
                if (store.size() >= maxSize) {
                    Object lastKey = keyOrder.removeLast();
                    store.remove(lastKey);
                }
                keyOrder.addFirst(key);
                store.put(key, value);
            }
        }

        public Object removeLatest() {
            synchronized (keyOrder) {
                if (!keyOrder.isEmpty()) {
                    Object latestKey = keyOrder.removeFirst();
                    return store.remove(latestKey);
                }
                return null;
            }
        }
    }
}

