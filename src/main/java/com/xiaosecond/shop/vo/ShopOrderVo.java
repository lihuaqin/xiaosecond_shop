package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.Date;

import com.xiaosecond.shop.annotation.QueryParam;
import com.xiaosecond.shop.enums.OrderEnum;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_shop_order")
public class ShopOrderVo extends BaseVo {

    private static final long serialVersionUID = 1L;

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
    @QueryParam(isQuery = true)
    private Integer status;

    /**
     * 总金额
     */
    private String totalPrice;

    /**
     * 交易金额
     */
    private String tradeAmount;

    public String getStatusName(){
        if(status!=null) {
            return OrderEnum.get(status).getValue();
        }
        return null;
    }
    public String getPayStatusName(){
        if(payStatus!=null) {
            return OrderEnum.getPayStatus(payStatus).getValue();
        }
        return null;
    }
    public String getPayTypeName(){
        if(payType!=null) {
            return OrderEnum.get(payType).getValue();
        }
        return null;
    }

    public Boolean hasPayed(){
        return OrderEnum.PayStatusEnum.UN_SEND.getId().equals(payStatus);
    }
}
