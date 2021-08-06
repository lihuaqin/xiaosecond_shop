package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.common.*;
import com.xiaosecond.shop.service.CmsBannerService;
import com.xiaosecond.shop.service.ShopAttrKeyService;
import com.xiaosecond.shop.service.ShopCategoryService;
import com.xiaosecond.shop.vo.ShopCategoryVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 商品类别 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Controller
@RequestMapping("/shop/category")
public class ShopCategoryController {

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private CmsBannerService cmsBannerService;

    @Autowired
    private ShopAttrKeyService shopAttrKeyService;

//    @RequestMapping(value = "/list",method = RequestMethod.GET)
//    @ResponseBody
//    public Response<PageResult<ShopCategoryVo>> listPage(ShopCategoryVo shopCategoryVo , PageParam pageParam){
//        return ResponseUtils.success(shopCategoryService.listPage(shopCategoryVo , pageParam));
//    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Response listPage(){
        return ResponseUtils.success(shopCategoryService.getCategories());
    }

    @RequestMapping(method = RequestMethod.POST)
    @RequiresPermissions(value = {Permission.CATEGORY_EDIT})
    @ResponseBody
    public Response save( ShopCategoryVo shopCategoryVo){
        shopCategoryService.save(shopCategoryVo);
        return ResponseUtils.success();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @RequiresPermissions(value = {Permission.CATEGORY_EDIT})
    @ResponseBody
    public Response remove(Long id){
        shopCategoryService.deleteById(id.intValue());
        return ResponseUtils.success();
    }
    @RequestMapping(value="/getBanners/{idCategory}",method = RequestMethod.GET)
    @ResponseBody
    public Response getBanners(@PathVariable("idCategory") Long idCategory){
        return ResponseUtils.success(cmsBannerService.getBannerByCategoryId(idCategory.intValue()));
    }

    @PostMapping(value="/changeShowIndex/{idCategory}/{showIndex}")
    @RequiresPermissions(value = {Permission.CATEGORY_EDIT})
    @ResponseBody
    public Response changeShowIndex(@PathVariable("idCategory") Long idCategory,
                                  @PathVariable("showIndex") Boolean showIndex){
        shopCategoryService.changeShowIndex(idCategory,showIndex);
        return ResponseUtils.success();
    }

    @RequestMapping(value ="getAttrKeys/{idCategory}",method = RequestMethod.GET)
    @ResponseBody
    public Response getAttrKeys(@PathVariable("idCategory") Long idCategory){
        return ResponseUtils.success(shopAttrKeyService.getByCategoryId(idCategory));

    }
}
