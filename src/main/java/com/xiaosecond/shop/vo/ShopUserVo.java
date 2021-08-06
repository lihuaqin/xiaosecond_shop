package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaosecond.shop.annotation.QueryParam;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author lhq
 * @since 2021-08-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_shop_user")
public class ShopUserVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别:male;female
     */
    private String gender;

    /**
     * 最后登陆时间
     */
    @JsonFormat(timezone = "GMT+8",locale = "zh",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    /**
     * 手机号
     */
    @QueryParam(isFuzzy = true)
    private String mobile;

    /**
     * 昵称
     */
    @QueryParam(isFuzzy = true)
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 微信头像
     */
    private String wechatHeadImgUrl;

    /**
     * 微信昵称
     */
    private String wechatNickName;

    /**
     * 微信OpenID
     */
    private String wechatOpenId;


}
