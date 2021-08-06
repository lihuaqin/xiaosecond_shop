package com.xiaosecond.shop.view;

import com.xiaosecond.shop.annotation.QueryParam;
import com.xiaosecond.shop.vo.ShopAddressVo;
import com.xiaosecond.shop.vo.ShopOrderItemVo;
import com.xiaosecond.shop.vo.ShopUserVo;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class ShopOrderViewReq {
    private String date;

    @QueryParam(isDateStart = true)
    private String startDate;
    @QueryParam(isDateEnd = true)
    private String endDate;


    private List<ShopOrderItemVo> items;

    private ShopAddressVo address;

    private ShopUserVo user;

    /**
     * 管理员备注
     */
    private String adminMessage;

    /**
     * 确认收货时间
     */
    private Date confirmReceivingTime;

    /**
     * 收件人
     */
    private String consignee;

    /**
     * 收件地址
     */
    private String consigneeAddress;

    /**
     * 优惠券抵扣金额
     */
    private String couponPrice;

    /**
     * 收货信息
     */
    private Long idAddress;

    /**
     * 快递公司
     */
    private Long idExpress;

    /**
     * 用户id
     */
    private Long idUser;

    /**
     * 订单备注
     */
    private String message;

    /**
     * 收件人电话
     */
    @QueryParam(isFuzzy = true)
    private String mobile;

    /**
     * 订单号
     */
    @QueryParam(isQuery = true)
    private String orderSn;

    /**
     * 支付流水号
     */
    private String payId;

    /**
     * 支付状态1:未付款;2:已付款,3:支付中
     */
    private Integer payStatus;

    /**
     * 支付成功时间
     */
    private String payTime;

    /**
     * 实付类型:alipay,wechat
     */
    private String payType;

    /**
     * 实付金额
     */
    private String realPrice;

    /**
     * 配送费用
     */
    private String shippingAmount;

    /**
     * 快递单号
     */
    private String shippingSn;

    /**
     * 出库时间
     */
    private LocalDateTime shippingTime;

    /**
     * 状态
     */
    @QueryParam(isOrderStatus = true)
    private String status;

    /**
     * 总金额
     */
    private String totalPrice;

    /**
     * 交易金额
     */
    private String tradeAmount;
}
