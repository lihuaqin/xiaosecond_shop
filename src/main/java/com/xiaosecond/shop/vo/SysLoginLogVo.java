package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登录日志
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_login_log")
public class SysLoginLogVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String ip;

    private String logname;

    private String message;

    private String succeed;

    private Integer userid;


}
