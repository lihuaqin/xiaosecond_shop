package com.xiaosecond.shop.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


/**
 * <p>
 * 通用服务类
 * </p>
 *
 *
 * @since 2021-07-01
 */
public interface CommonService<T> {

    /**
     * 校验 字段 是否在数据库中已存在
     * @param val       值
     * @param fieldName     字段名
     * @param baseMapper
     */
    void checkRepeat(String val, String fieldName, BaseMapper<T> baseMapper);

    /**
     * 校验 字段 是否在数据库中已存在
     * @param val 值
     * @param id  id   当修改操作时 ，校验字段重复，需传入id排除自身
     * @param fieldName  字段名
     * @param baseMapper
     */
    void checkRepeat(String val, Integer id, String fieldName, BaseMapper<T> baseMapper);



}
