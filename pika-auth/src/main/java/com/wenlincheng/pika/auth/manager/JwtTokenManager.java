package com.wenlincheng.pika.auth.manager;

import com.alibaba.fastjson.JSON;
import com.wenlincheng.pika.auth.client.dto.Role;
import com.wenlincheng.pika.auth.config.JwtProperties;
import com.wenlincheng.pika.common.core.constant.SecurityConstants;
import com.wenlincheng.pika.auth.security.AuthUserDetails;
import com.wenlincheng.pika.auth.security.ScopesEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * 令牌工具类
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Component
public class JwtTokenManager {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 生成新的token方法，并将角色保存
     *
     * @param authUserDetails
     * @return java.lang.String
     * @throws
     */
    public String createAccessJwtToken(AuthUserDetails authUserDetails) throws JwtException {
        if (StringUtils.isBlank(authUserDetails.getUsername())) {
            throw new IllegalArgumentException("用户名为空无法创建token");
        }

        // 存入角色信息
        List<Long> list = new ArrayList<>();
        for (Role roleInfo : authUserDetails.getRoleList()) {
            list.add(roleInfo.getId());
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put(SecurityConstants.AUTHORITIES_KEY, JSON.toJSONString(list));
        claims.put("user_id", authUserDetails.getId());

        Date createDate = new Date();
        Date expirationDate = new Date(createDate.getTime() + jwtProperties.getTokenExpiration() * 1000);

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                // 用户信息
                .setClaims(claims)
                // 主题
                .setSubject(authUserDetails.getUsername())
                // 签发人
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                // 令牌类型
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                // 签发时间
                .setIssuedAt(createDate)
                // 过期时间
                .setExpiration(expirationDate)
                // 签名
                .signWith(Keys.hmacShaKeyFor(jwtProperties.getSigningKey().getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * 验证令牌
     *
     * @param token 令牌
     * @throws JwtException  异常
     * @return io.jsonwebtoken.Claims
     */
    public Claims parserToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSigningKey().getBytes())
                .build()
                .parseClaimsJws(token.replace(SecurityConstants.JWT_TOKEN_PREFIX, ""))
                .getBody();
    }

    /**
     * 根据令牌获取用户id
     *
     * @param token 令牌
     * @throws JwtException 异常
     * @return java.lang.Long 用户id
     */
    public Long getUserIdByToken(String token) throws JwtException {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSigningKey().getBytes())
                .build()
                .parseClaimsJws(token.replace(SecurityConstants.JWT_TOKEN_PREFIX, ""))
                .getBody();

        return Long.parseLong(claims.get("user_id").toString());
    }

    /**
     * 创建刷新token
     *
     * @param authUserDetails
     * @return java.lang.String
     * @throws
     */
    public String createRefreshToken(AuthUserDetails authUserDetails) {
        if (StringUtils.isBlank(authUserDetails.getUsername())) {
            throw new IllegalArgumentException("用户名为空无法创建token");
        }

        LocalDateTime currentTime = LocalDateTime.now();

        Claims claims = Jwts.claims().setSubject(authUserDetails.getUsername());
        claims.put(SecurityConstants.AUTHORITIES_KEY, Collections.singletonList(ScopesEnum.REFRESH_TOKEN.authority()));

        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(jwtProperties.getSigningKey().getBytes()), SignatureAlgorithm.HS512)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(SecurityConstants.refreshTokenExpTime)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .setClaims(claims)
                .compact();

        return token;
    }

    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(secretString);
    }

}
