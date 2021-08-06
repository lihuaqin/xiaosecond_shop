package com.xiaosecond.shop.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.xiaosecond.shop.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账号
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_user")
public class SysUserVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String account;

    private String avatar;

    private LocalDateTime birthday;

    private Long deptid;

    private String email;

    private String name;

    private String password;

    private String phone;

    private String roleid;

    private String salt;

    private Integer sex;

    private Integer status;

    private Integer version;


}
