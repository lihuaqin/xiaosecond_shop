package com.xiaosecond.shop.mapper;

import com.xiaosecond.shop.view.ShopExpressInfoView;
import com.xiaosecond.shop.vo.ShopExpressInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 快递信息 Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Repository
public interface ShopExpressInfoMapper extends BaseMapper<ShopExpressInfoVo> {

    ShopExpressInfoView getByOrderId(Integer orderId);

}
