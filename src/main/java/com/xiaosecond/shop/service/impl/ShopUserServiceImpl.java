package com.xiaosecond.shop.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaosecond.shop.common.Constant;
import com.xiaosecond.shop.common.PageParam;
import com.xiaosecond.shop.common.PageResult;
import com.xiaosecond.shop.utils.HandleQueryParam;
import com.xiaosecond.shop.view.ShopUserView;
import com.xiaosecond.shop.mapper.ShopUserMapper;
import com.xiaosecond.shop.service.ShopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Service
public class ShopUserServiceImpl  implements ShopUserService {

    @Autowired
    private ShopUserMapper shopUserMapper;

    public PageResult<ShopUserView> listPage(ShopUserView shopUserView , PageParam pageParam){
        Map<String,Object> param = new HashMap<>();
        Page<ShopUserView> page = new Page<>();
        page.setCurrent(pageParam.getPage()==null ? Constant.DEFAULT_PAGE_INDEX : pageParam.getPage());
        page.setSize(pageParam.getLimit()==null ? Constant.DEFAULT_PAGE_SIZE : pageParam.getLimit());
        HandleQueryParam<ShopUserView> handleQueryParam = new HandleQueryParam<ShopUserView>(ShopUserView.class);
        handleQueryParam.viewVoToMap(shopUserView , param);
        return new PageResult<>(shopUserMapper.listPage(page , param ));
    }

}
