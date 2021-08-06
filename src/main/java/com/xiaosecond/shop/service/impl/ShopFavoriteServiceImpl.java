package com.xiaosecond.shop.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosecond.shop.common.Constant;
import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.mapper.ShopCartMapper;
import com.xiaosecond.shop.utils.HandleQueryParam;
import com.xiaosecond.shop.view.ShopCartView;
import com.xiaosecond.shop.view.ShopFavoriteView;
import com.xiaosecond.shop.vo.ShopCartVo;
import com.xiaosecond.shop.vo.ShopFavoriteVo;
import com.xiaosecond.shop.mapper.ShopFavoriteMapper;
import com.xiaosecond.shop.service.ShopFavoriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户收藏 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-06
 */
@Service
public class ShopFavoriteServiceImpl extends ServiceImpl<ShopFavoriteMapper, ShopFavoriteVo> implements ShopFavoriteService {

    @Autowired
    private ShopFavoriteMapper shopFavoriteMapper;

    public PageResult<ShopFavoriteView> listPage(ShopFavoriteView shopFavoriteView , PageParam pageParam){
        Map<String,Object> param = new HashMap<>();
        Page<ShopFavoriteView> page = new Page<>();
        page.setCurrent(pageParam.getPage()==null ? Constant.DEFAULT_PAGE_INDEX : pageParam.getPage());
        page.setSize(pageParam.getLimit()==null ? Constant.DEFAULT_PAGE_SIZE : pageParam.getLimit());
        HandleQueryParam<ShopFavoriteView> handleQueryParam = new HandleQueryParam<ShopFavoriteView>(ShopFavoriteView.class);
        handleQueryParam.viewVoToMap(shopFavoriteView , param);
        return new PageResult<>(shopFavoriteMapper.listPage(page , param ));
    }
}
