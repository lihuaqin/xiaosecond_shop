package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaosecond.shop.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 订单日志
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_shop_order_log")
public class ShopOrderLogVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 日志详情
     */
    private String descript;

    /**
     * 所属订单id
     */
    private Long idOrder;

    @JsonFormat(timezone = "GMT+8",locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;  //创建时间

}
