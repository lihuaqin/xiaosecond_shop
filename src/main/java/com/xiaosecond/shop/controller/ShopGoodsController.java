package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.annotation.BussinessLog;
import com.xiaosecond.shop.common.*;
import com.xiaosecond.shop.service.ShopGoodsService;
import com.xiaosecond.shop.view.ShopGoodsView;
import com.xiaosecond.shop.view.ShopUserView;
import com.xiaosecond.shop.vo.ShopCategoryVo;
import com.xiaosecond.shop.vo.ShopGoodsVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 商品 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Controller
@RequestMapping("/shop/goods")
public class ShopGoodsController {

    @Autowired
    private ShopGoodsService shopGoodsService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Response<PageResult<ShopGoodsView>> list(ShopGoodsView shopGoodsView, PageParam pageParam) {
        return ResponseUtils.success(shopGoodsService.listPage(shopGoodsView ,pageParam));
    }

    @RequestMapping(value = "/saveBaseInfo",method = RequestMethod.POST)
    @RequiresPermissions(value = {Permission.GOODS_EDIT})
    @ResponseBody
    public Response saveBaseInfo(@RequestBody ShopGoodsVo shopGoodsVo){
        shopGoodsService.save(shopGoodsVo);
        return ResponseUtils.success(shopGoodsVo.getId());
    }

    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑商品", key = "name")
    @RequiresPermissions(value = {Permission.GOODS_EDIT})
    @ResponseBody
    public Response save(@RequestBody ShopGoodsVo shopGoodsVo){
        shopGoodsService.saveShopGoodsVo(shopGoodsVo);
        return ResponseUtils.success();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Response get(Long id){
        return ResponseUtils.success(shopGoodsService.getDetail(id));
    }

    @RequestMapping(value="/changeIsOnSale",method = RequestMethod.POST)
    @RequiresPermissions(value = {Permission.GOODS_EDIT})
    @BussinessLog(value = "上架/下架商品", key = "id")
    @ResponseBody
    public Response changeIsOnSale(ShopGoodsVo shopGoodsVo){
        shopGoodsService.saveShopGoodsVo(shopGoodsVo);
        return ResponseUtils.success(shopGoodsService.getDetail((long)shopGoodsVo.getId()));
    }

}
