package com.xiaosecond.shop.service;

import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.security.AuthorizationUser;
import com.xiaosecond.shop.security.JwtUser;
import com.xiaosecond.shop.vo.SysUserVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 账号 服务类
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
public interface SysUserService extends IService<SysUserVo> {

    SysUserVo findByAccount(String userName);

    String loginForToken(JwtUser user);

    Response getUserInfo();

    AuthorizationUser getAuthorizationInfo();

    void logout();

    void updatePwd( String oldPassword,String password, String rePassword);
}
