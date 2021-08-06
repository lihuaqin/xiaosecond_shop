package com.xiaosecond.shop.security;


import com.xiaosecond.shop.vo.ShopUserVo;
import com.xiaosecond.shop.vo.SysUserVo;
import lombok.Data;

/**
 * 由于前后端使用一个基础模块：公用了一些权限处理代码，所以封装本类用来做前后端处理登录的用户信息实体类
 */
@Data
public class JwtUser {
    public static  int MANAGER= 0;
    public static  int FRONT_USER= 1;
    private String userName;
    private String password;
    private Long id;

    /**
     * 刷新token的时候使用的用户信息
     * @param user
     */
    public JwtUser(AuthorizationUser user){
        this.userName = user.getAccount();
        this.password = user.getPassword();
        this.id = user.getId();
    }

    /**
     * 前端登录用户信息
     * @param user
     */
    public JwtUser(ShopUserVo user){
        this.userName = user.getMobile();
        this.id = (long)user.getId();
        this.password = user.getPassword();
    }

    /**
     * 后端登录用户信息
     * @param user
     */
    public JwtUser(SysUserVo user){
        this.userName = user.getAccount();
        this.id = (long)user.getId();
        this.password = user.getPassword();
    }
}
