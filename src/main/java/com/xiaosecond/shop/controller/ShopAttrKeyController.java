package com.xiaosecond.shop.controller;


import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.service.ShopAttrKeyService;
import com.xiaosecond.shop.vo.ShopAttrKeyVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 商品属性名 前端控制器
 * </p>
 *
 * @author lhq
 * @since 2021-08-04
 */
@Controller
@RequestMapping("/shop/goods/attr/key")
public class ShopAttrKeyController {

    @Autowired
    private ShopAttrKeyService shopAttrKeyService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Response save(ShopAttrKeyVo shopAttrKeyVo){
        shopAttrKeyService.saveOrUpdate(shopAttrKeyVo);
        return ResponseUtils.success();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public Response remove(Long id){
        shopAttrKeyService.removeById(id.intValue());
        return ResponseUtils.success();
    }

    @RequestMapping(value="updateAttrName",method = RequestMethod.POST)
    @ResponseBody
    public Response updateAttrName(@RequestParam("id") Long id, @RequestParam("attrName") String attrName){
        ShopAttrKeyVo attrKey = shopAttrKeyService.getById(id);
        attrKey.setAttrName(attrName);
        shopAttrKeyService.updateById(attrKey);
        return ResponseUtils.success();
    }

}
