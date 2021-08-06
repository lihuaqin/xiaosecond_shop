package com.xiaosecond.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosecond.shop.vo.SysCfgVo;
import com.xiaosecond.shop.mapper.SysCfgMapper;
import com.xiaosecond.shop.service.SysCfgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统参数 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Service
public class SysCfgServiceImpl extends ServiceImpl<SysCfgMapper, SysCfgVo> implements SysCfgService {

    @Autowired
    private  SysCfgMapper sysCfgMapper;

    public String getCfgValue(String cfgName){
        QueryWrapper<SysCfgVo> query = new QueryWrapper<>();
        query.eq("cfg_name" , cfgName);
        SysCfgVo sysCfgVo =  sysCfgMapper.selectOne(query);
        if(sysCfgVo != null && !StringUtils.isEmpty(sysCfgVo.getCfgValue()))
            return sysCfgVo.getCfgValue();
        return null;
    }



}
