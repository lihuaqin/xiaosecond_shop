package com.xiaosecond.shop.mapper;

import com.xiaosecond.shop.vo.ShopOrderLogVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 订单日志 Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Repository
public interface ShopOrderLogMapper extends BaseMapper<ShopOrderLogVo> {
    List<ShopOrderLogVo> queryByIdOrder(Long idOrder);
}
