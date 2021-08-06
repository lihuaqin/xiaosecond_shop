package com.xiaosecond.shop.view;

import com.xiaosecond.shop.annotation.QueryParam;
import com.xiaosecond.shop.vo.*;
import lombok.Data;
import java.util.List;

@Data
public class ShopOrderView extends ShopOrderVo {

    private String date;

    @QueryParam(isDateStart = true)
    private String startDate;
    @QueryParam(isDateEnd = true)
    private String endDate;


    private List<ShopOrderItemVo> items;

    private ShopAddressVo address;

    private ShopUserVo user;

    private SysExpressVo express;

}
