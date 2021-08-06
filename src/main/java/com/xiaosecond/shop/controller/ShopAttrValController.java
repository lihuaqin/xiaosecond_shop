package com.xiaosecond.shop.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.excpetion.MyException;
import com.xiaosecond.shop.service.ShopAttrValService;
import com.xiaosecond.shop.vo.ShopAttrValVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;


/**
 * <p>
 * 商品属性值 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Controller
@RequestMapping("/shop/attr/val")
public class ShopAttrValController {

    @Autowired
    private ShopAttrValService shopAttrValService;

    @RequestMapping(value = "/getAttrByCategoryAndGoods/{idCategory}",method = RequestMethod.GET)
    @ResponseBody
    public Response getAttrByCategoryAndGoods(@PathVariable("idCategory") Long idCategory) {
        return ResponseUtils.success(shopAttrValService.getAttrByCategoryAndGoods(idCategory));
    }

    @RequestMapping(value = "getAttrVals", method = RequestMethod.GET)
    @ResponseBody
    public Response getAttrVals(@RequestParam("idAttrKey")Long idAttrKey) {
        QueryWrapper<ShopAttrValVo> query = new QueryWrapper();
        query.eq("id_attr_key" , idAttrKey);
        return ResponseUtils.success(shopAttrValService.list(query));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response save(ShopAttrValVo shopAttrValVo) {
        shopAttrValService.saveShopAttrValVo(shopAttrValVo);
        return ResponseUtils.success();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public Response remove(Long id) {
        if (id == null) throw new MyException("未传必传参数","remove");
        shopAttrValService.removeById(id.intValue());
        return ResponseUtils.success();
    }

    @RequestMapping(value="updateAttrVal",method = RequestMethod.POST)
    @ResponseBody
    public Object updateAttrName(ShopAttrValVo shopAttrValVo){
        if (shopAttrValVo.getId() == null) throw new MyException("未传必传参数","remove");
        shopAttrValService.saveShopAttrValVo(shopAttrValVo);
        return ResponseUtils.success();
    }

}
