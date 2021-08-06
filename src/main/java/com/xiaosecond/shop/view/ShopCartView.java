package com.xiaosecond.shop.view;

import com.xiaosecond.shop.vo.ShopCartVo;
import com.xiaosecond.shop.vo.ShopGoodsSkuVo;
import com.xiaosecond.shop.vo.ShopGoodsVo;
import com.xiaosecond.shop.vo.ShopUserVo;
import lombok.Data;


@Data
public class ShopCartView extends ShopCartVo {

    private ShopUserVo user;

    private ShopGoodsVo goods;

    private ShopGoodsSkuVo sku;

    public String getPrice(){
        if(this.getIdSku()!=null){
            return sku.getPrice();
        }
        return goods.getPrice();
    }
    public String getTitle(){
        return this.getIdSku()!=null?goods.getName()+" "+sku.getCodeName():goods.getName();
    }

}
