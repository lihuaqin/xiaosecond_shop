package com.xiaosecond.shop.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaosecond.shop.annotation.QueryParam;
import com.xiaosecond.shop.vo.ShopUserVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@Data
public class ShopUserView extends ShopUserVo {

    @QueryParam(isDateStart = true)
    private String startRegDate; //注册时间查询范围
    @QueryParam(isDateEnd = true)
    private String endRegDate;//注册时间查询范围
    @QueryParam(isDateStart = true)
    private String startLastLoginTime; //登录时间查询范围
    @QueryParam(isDateEnd = true)
    private String endLastLoginTime;//登录时间查询范围


    @JsonFormat(timezone = "GMT+8",locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("注册时间")
    private Date createTime;  //注册时间
}
