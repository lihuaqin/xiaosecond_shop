package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.annotation.BussinessLog;
import com.xiaosecond.shop.common.Permission;
import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.service.ShopGoodsSkuService;
import com.xiaosecond.shop.vo.ShopGoodsSkuVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 商品SKU 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Controller
@RequestMapping("/shop/goods/sku")
public class ShopGoodsSkuController {

    @Autowired
    private ShopGoodsSkuService shopGoodsSkuService;

    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑商品SKU", key = "name")
    @RequiresPermissions(value = {Permission.GOODS_EDIT})
    @ResponseBody
    public Response save(@RequestBody ShopGoodsSkuVo shopGoodsSkuVo){
        shopGoodsSkuService.saveShopGoodsSkuVo(shopGoodsSkuVo);
        return ResponseUtils.success(shopGoodsSkuVo);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除商品SKU", key = "id")
    @RequiresPermissions(value = {Permission.GOODS_EDIT})
    @ResponseBody
    public Response remove(Long id){
        shopGoodsSkuService.removeById(id.intValue());
        return ResponseUtils.success();
    }

}
