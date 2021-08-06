package com.xiaosecond.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosecond.shop.cache.Cache;
import com.xiaosecond.shop.common.Response;
import com.xiaosecond.shop.common.ResponseUtils;
import com.xiaosecond.shop.excpetion.MyException;
import com.xiaosecond.shop.log.LogManager;
import com.xiaosecond.shop.log.LogTaskFactory;
import com.xiaosecond.shop.mapper.*;
import com.xiaosecond.shop.security.AuthorizationUser;
import com.xiaosecond.shop.security.JwtUtil;
import com.xiaosecond.shop.security.JwtUser;
import com.xiaosecond.shop.utils.*;
import com.xiaosecond.shop.vo.SysRoleVo;
import com.xiaosecond.shop.vo.SysUserVo;
import com.xiaosecond.shop.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.nutz.mapl.Mapl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 账号 服务实现类
 * </p>
 *
 * @author lhq
 * @since 2021-08-03
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserVo> implements SysUserService {



    @Value("${jwt.token.expire.time}")
    private Long tokenExpireTime ;


    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private Cache cache;

    @Override
    public SysUserVo findByAccount(String userName) {
        QueryWrapper<SysUserVo> query= new QueryWrapper<>();
        query.eq("account" , userName);
        return sysUserMapper.selectOne(query);
    }

    public String loginForToken(JwtUser user){
        //获取用户token值
        String token = jwtUtil.sign(user,tokenExpireTime*60000);
        //将token作为RefreshToken Key 存到缓存中，缓存时间为token有效期的两倍
//        String   refreshTokenCacheKey = token;
//        Date expireDate = new Date(System.currentTimeMillis()+tokenExpireTime*120000);
//        cache.set(refreshTokenCacheKey , String.valueOf(expireDate.getTime()));
        return token;
    }

    public Response getUserInfo(){
        Long idUser = jwtUtil.getUserId( cookieUtil.getToken() );
        if(idUser == null){
            throw new MyException("用户不存在" , "getUserInfo") ;
        }
        SysUserVo user = sysUserMapper.selectById(idUser);
        if(StringUtils.isEmpty(user.getRoleid())){
            throw new MyException("该用户未配置权限" , "getUserInfo") ;
        }
        AuthorizationUser shiroUser = getAuthorizationInfo(user.getAccount());
        Map map = Maps.newHashMap("name",user.getName(),"role","admin","roles", shiroUser.getRoleCodes());
        map.put("permissions",shiroUser.getUrls());
        Map profile = (Map) Mapl.toMaplist(user);
        profile.put("dept",shiroUser.getDeptName());
        profile.put("roles",shiroUser.getRoleNames());
        map.put("profile",profile);
        return ResponseUtils.success(map);
    }

    public AuthorizationUser getAuthorizationInfo( ){
        Long idUser = jwtUtil.getUserId( cookieUtil.getToken() );
        SysUserVo user = sysUserMapper.selectById(idUser);
        return getAuthorizationInfo(user.getAccount());
    }

    public AuthorizationUser getAuthorizationInfo(String account) {
        Object cacheUser =  cache.get(cookieUtil.getToken());
        if(cacheUser!=null){
            return (AuthorizationUser)cacheUser;
        }
        AuthorizationUser userBean = new AuthorizationUser();
        //构建后台用户认证信息
        SysUserVo user = findByAccount(account);
        userBean.setId((long)user.getId());            // 账号id
        userBean.setAccount(user.getAccount());// 账号
        userBean.setDeptId(user.getDeptid());    // 部门id
        userBean.setDeptName(sysDeptMapper.selectById(user.getDeptid()).getFullname());// 部门名称
        userBean.setName(user.getName());        // 用户名称
        userBean.setPassword(user.getPassword());
        Long[] roleArray = Convert.toLongArray(",", user.getRoleid());
        List<Long> roleList = new ArrayList<Long>();
        List<String> roleNameList = new ArrayList<String>();
        List<String> roleCodeList = new ArrayList<String>();
        Set<String> permissions = new HashSet<String>();
        Set<String> resUrls = new HashSet<>();
        for (Long roleId : roleArray) {
            roleList.add(roleId);
            SysRoleVo role = sysRoleMapper.selectById(roleId);
            roleNameList.add(role.getName());
            roleCodeList.add(role.getTips());
            permissions.addAll(sysMenuMapper.getResCodesByRoleId(roleId));
            resUrls.addAll(sysMenuMapper.getResUrlsByRoleId(roleId));
        }
        userBean.setRoleList(roleList);
        userBean.setRoleNames(roleNameList);
        userBean.setRoleCodes(roleCodeList);
        userBean.setPermissions(permissions);
        userBean.setUrls(resUrls);
        cache.set(cookieUtil.getToken(),userBean);
        return userBean;
    }

    public void logout(){
        cache.del(cookieUtil.getToken());
        LogManager.me().executeLog(LogTaskFactory.exitLog(jwtUtil.getUserId(), HttpUtil.getIp() , sysLoginLogMapper));
    }

    public void updatePwd( String oldPassword,String password, String rePassword){
        SysUserVo user = sysUserMapper.selectById(jwtUtil.getUserId());
        log.info("oldPassword:{},password:{},rePassword:{}", MD5.md5(oldPassword, user.getSalt()),password,rePassword);
        if(!MD5.md5(oldPassword, user.getSalt()).equals(user.getPassword())){
            throw  new MyException("旧密码输入错误" ,"updatePwd");
        }
        if(!password.equals(rePassword)){
            throw  new MyException("新密码前后不一致" , "updatePwd");
        }
        user.setPassword(MD5.md5(password, user.getSalt()));
        sysUserMapper.updateById(user);
        cache.set(cookieUtil.getToken() , user);
    }

}
