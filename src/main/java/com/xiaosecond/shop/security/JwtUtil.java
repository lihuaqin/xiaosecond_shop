package com.xiaosecond.shop.security;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xiaosecond.shop.utils.CookieUtil;
import com.xiaosecond.shop.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;


@Component
public class JwtUtil {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CookieUtil cookieUtil;

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param password 用户密码
     * @return 是否正确
     */
    public  boolean verify(String token,  String password) {
        JWTVerifier verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            verifier = JWT.require(algorithm)
                    .build();
        } catch (Exception e) {
            return false;
        }
        DecodedJWT jwt = verifier.verify(token);
        return true;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public  String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    public  String getUsername() {
        return getUsername(HttpUtil.getToken());
    }

    public  Integer getUserIdInt() {
        return getUserId().intValue();
    }
    public  Long getUserId() {
       return getUserId(cookieUtil.getToken());
    }
    public  Long getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asLong();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     * @param user 用户
     * @return 加密的token
     */
    public  String sign(JwtUser user, long expireTime) {
        try {
            Date date = new Date(System.currentTimeMillis() + expireTime);
            Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
            // 附带username信息
            return JWT.create()
                    .withClaim("username", user.getUserName())
                    .withClaim("userId",user.getId())
                    .withClaim("uuid", UUID.randomUUID().toString())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
