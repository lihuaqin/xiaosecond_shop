package com.xiaosecond.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaosecond.shop.enums.CheckRepeatEnum;
import com.xiaosecond.shop.excpetion.MyException;
import com.xiaosecond.shop.service.CommonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;



/**
 * <p>
 * 通用服务实现类
 * </p>
 *
 *
 * @since 2021-07-01
 */
@Service
public class CommonServiceImpl<T> implements CommonService<T> {



    /**
     * 校验 字段 是否在数据库中已存在
     * @param val       值
     * @param field     字段名
     * @param baseMapper
     */
    public void checkRepeat(String val ,String field , BaseMapper<T> baseMapper){
        checkRepeat(val, null  ,field , baseMapper);
    }

    /**
     * 校验 字段 是否在数据库中已存在
     * @param val 值
     * @param id  id   当修改操作时 ，校验字段重复，需传入id排除自身
     * @param field  字段名
     * @param baseMapper
     */
    public void checkRepeat(String val , Integer id  ,String field , BaseMapper<T> baseMapper){

        if(StringUtils.isEmpty(val) && id != null){
            throw  new MyException( CheckRepeatEnum.getInfoByFieldName(field) + "不能为空","checkRepeat");
        }
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        if(id != null){
            wrapper.eq(field,val).ne("id" , id);
        }else{
            wrapper.eq(field,val);
        }
        if(baseMapper.selectCount(wrapper) > 0){
            throw  new MyException( CheckRepeatEnum.getInfoByFieldName(field)  + "已存在","checkRepeat");
        }
    }








}
