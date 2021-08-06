package com.xiaosecond.shop.view;

import com.xiaosecond.shop.vo.ShopFavoriteVo;
import com.xiaosecond.shop.vo.ShopGoodsVo;
import com.xiaosecond.shop.vo.ShopUserVo;
import lombok.Data;

@Data
public class ShopFavoriteView extends ShopFavoriteVo {

    private ShopUserVo user;

    private ShopGoodsVo goods;

}
