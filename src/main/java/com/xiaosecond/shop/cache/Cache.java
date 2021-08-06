package com.xiaosecond.shop.cache;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 缓存接口
 * 实现方式为本地缓存，如需改用redis，只需新增redis实现类，无需修改业务逻辑代码
 */
public interface Cache {

    /**
     * 缓存获取
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 缓存获取 - 所有
     * @return
     */
    Map<String, Object> getAll();

    /**
     * 缓存放入  ** 因开辟的是虚拟机内存，为避免内存溢出（默认失效时间为1天）
     * @param key
     * @param value
     */
    void set(String key, Object value);

    /**
     * 缓存放入 -- 设置缓存失效时间
     * @param key     键
     * @param value    值
     * @param timeout  生效时间
     * @param timeUnit  失效时间单位
     */
    void set(String key, Object value, long timeout, TimeUnit timeUnit);

    /**
     * 删除缓存
     * @param key
     */
    void del(String key);

    /**
     * 删除缓存 - 所有
     */
    void delAll();




}
