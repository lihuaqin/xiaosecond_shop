package com.xiaosecond.shop.view;


import com.xiaosecond.shop.vo.ShopGoodsSkuVo;
import com.xiaosecond.shop.vo.ShopGoodsVo;
import lombok.Data;

import java.util.List;


@Data
public class ShopGoodsDetails   {

    private ShopGoodsVo goods;
    private List<ShopGoodsSkuVo> skuList;

    public ShopGoodsDetails(ShopGoodsVo goods , List<ShopGoodsSkuVo> skuList){
        this.goods = goods;
        this.skuList =skuList;
    }


}
