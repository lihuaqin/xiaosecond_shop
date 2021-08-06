package com.xiaosecond.shop.cache.impl;

import com.xiaosecond.shop.cache.Cache;
import com.xiaosecond.shop.utils.DateUtils;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class LocalCacheImpl implements Cache {

    /**  concurrentHashMap的线程安全性  static--避免被jvm垃圾回收**/
    private static Map<String, Object> cache = new ConcurrentHashMap<>();

    private static Map<String, Object> cacheTimeOut = new ConcurrentHashMap<>();

    @Override
    public Object get(String key) {
        refresh();
        return cache.get(key);
    }

    @Override
    public void set(String key, Object value) {
        refresh();
        set( key , value  , 60 * 60 * 24 , TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
        refresh();
        cache.put( key , value );
        long expireTime = DateUtils.getNowDate().getTime() + timeUnit.toMillis(timeout);
        cacheTimeOut.put(key , expireTime );
    }

    @Override
    public void del(String key) {
        refresh();
        cache.remove(key);
        cacheTimeOut.remove(key);
    }

    public Map<String, Object> getAll() {
        refresh();
        return  this.cache;
    }


    public void delAll() {
        refresh();
        cache = new ConcurrentHashMap<>();
        cacheTimeOut = new ConcurrentHashMap<>();
    }
    /**
     * 刷新缓存，清除已失效的缓存，释放内存
     * 每个操作都刷新一下缓存，使过期缓存能够及时释放
     */
    private void refresh(){
        long nowTime = DateUtils.getNowDate().getTime();
        Set<String> keys = cacheTimeOut.keySet();
        List<String> expireKeys = new ArrayList<>();
        for (String key : keys) {
            long expireTime = (Long) cacheTimeOut.get(key);
            if(nowTime > expireTime){
                expireKeys.add(key);
            }
        }
        for (String expireKey :expireKeys) {
            cache.remove(expireKey);
            cacheTimeOut.remove(expireKey);
        }
    }

}
