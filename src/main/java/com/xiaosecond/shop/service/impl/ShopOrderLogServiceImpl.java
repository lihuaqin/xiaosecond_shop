package com.xiaosecond.shop.service.impl;

import com.xiaosecond.shop.mapper.ShopOrderMapper;
import com.xiaosecond.shop.vo.ShopOrderLogVo;
import com.xiaosecond.shop.mapper.ShopOrderLogMapper;
import com.xiaosecond.shop.service.ShopOrderLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单日志 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Service
public class ShopOrderLogServiceImpl extends ServiceImpl<ShopOrderLogMapper, ShopOrderLogVo> implements ShopOrderLogService {

    @Autowired
    private ShopOrderLogMapper shopOrderLogMapper;

    public List<ShopOrderLogVo> queryByIdOrder(Long idOrder){
        return shopOrderLogMapper.queryByIdOrder(idOrder);
    }

}
