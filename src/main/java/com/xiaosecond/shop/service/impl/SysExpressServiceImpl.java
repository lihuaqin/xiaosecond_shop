package com.xiaosecond.shop.service.impl;

import com.xiaosecond.shop.vo.SysExpressVo;
import com.xiaosecond.shop.mapper.SysExpressMapper;
import com.xiaosecond.shop.service.SysExpressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 物流公司 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Service
public class SysExpressServiceImpl extends ServiceImpl<SysExpressMapper, SysExpressVo> implements SysExpressService {

    @Autowired
    private SysExpressMapper sysExpressMapper;

    public List<SysExpressVo> queryAll(){
        return sysExpressMapper.queryAll();
    }
}
