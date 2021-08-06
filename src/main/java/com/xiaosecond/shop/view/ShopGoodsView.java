package com.xiaosecond.shop.view;

import com.xiaosecond.shop.vo.ShopCategoryVo;
import com.xiaosecond.shop.vo.ShopGoodsVo;
import lombok.Data;

@Data
public class ShopGoodsView extends ShopGoodsVo {

    private ShopCategoryVo category;



}
