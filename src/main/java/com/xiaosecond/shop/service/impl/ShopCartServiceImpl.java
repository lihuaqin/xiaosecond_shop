package com.xiaosecond.shop.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosecond.shop.common.Constant;
import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.mapper.ShopOrderMapper;
import com.xiaosecond.shop.utils.HandleQueryParam;
import com.xiaosecond.shop.view.ShopOrderView;
import com.xiaosecond.shop.view.ShopOrderViewReq;
import com.xiaosecond.shop.vo.ShopCartVo;
import com.xiaosecond.shop.mapper.ShopCartMapper;
import com.xiaosecond.shop.service.ShopCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Service
public class ShopCartServiceImpl extends ServiceImpl<ShopCartMapper, ShopCartVo> implements ShopCartService {

    @Autowired
    private ShopCartMapper shopCartMapper;

    public PageResult<ShopCartVo> listPage(ShopCartVo shopCartVo , PageParam pageParam){
        Map<String,Object> param = new HashMap<>();
        Page<ShopCartVo> page = new Page<>();
        page.setCurrent(pageParam.getPage()==null ? Constant.DEFAULT_PAGE_INDEX : pageParam.getPage());
        page.setSize(pageParam.getLimit()==null ? Constant.DEFAULT_PAGE_SIZE : pageParam.getLimit());
        HandleQueryParam<ShopCartVo> handleQueryParam = new HandleQueryParam<ShopCartVo>(ShopCartVo.class);
        handleQueryParam.viewVoToMap(shopCartVo , param);
        return new PageResult<>(shopCartMapper.listPage(page , param ));
    }

}
