package com.xiaosecond.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosecond.shop.view.ShopGoodsView;
import com.xiaosecond.shop.view.ShopOrderView;
import com.xiaosecond.shop.vo.ShopOrderVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Repository
public interface ShopOrderMapper extends BaseMapper<ShopOrderVo> {

    Integer count();

    Object orderSumPrice(Integer status);

    IPage<ShopOrderView> listPage(Page<ShopOrderView> page, @Param("param") Map<String,Object> param );


    List<Object> countStatus();

    ShopOrderView getByOrderSn(String orderSn);
}
