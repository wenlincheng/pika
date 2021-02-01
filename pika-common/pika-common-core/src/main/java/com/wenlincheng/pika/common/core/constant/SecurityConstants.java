package com.wenlincheng.pika.common.core.constant;

/**
 *
 * Jwt 常量
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class SecurityConstants {

    /**
     * token 头部
     */
    public static String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 请求头参数 KEY
     */
    public static String JWT_TOKEN_HEADER = "Authorization";

    /**
     * 登录链接
     */
    public static String LOGIN_URL = "/login/v1";

    /**
     * 用户令牌 缓存前缀
     */
    public static String JWT_TOKEN_REDIS_PREFIX = "pika-auth:token:";

    /**
     * 用户登录次数限制 缓存前缀
     */
    public static String LOGIN_TIME_LIMIT_REDIS_PREFIX = "pika-auth:login_time_limit:";
    
    /**
     * 用户登录失败 缓存前缀
     */
    public static String LOGIN_FAIL_FLAG_REDIS_PREFIX = "pika-auth:login_fail_flag:";

    /**
     * 所有权限 缓存前缀
     */
    public static String PERMISSIONS_REDIS_KEY = "pika-auth:permissions";

    /**
     * 用户的权限缓存前缀
     */
    public static String USER_PERMISSIONS_REDIS_KEY = "pika-auth:user_permissions:";

    /**
     * token中自定义权限标识
     */
    public static String AUTHORITIES_KEY = "roles";

    /**
     * Token 发行人
     */
    public static String TOKEN_ISSUER = "pika";

    /**
     * 令牌类型
     */
    public static final String TOKEN_TYPE = "JWT";

    /**
     * 刷新Token时间
     */
    public static Integer refreshTokenExpTime = 720;

    /**
     * 用户信息
     */
    public static String X_CLIENT_TOKEN_USER = "x-client-token-user";
    /**
     * 服务间调用的认证token
     */
    public static String X_CLIENT_TOKEN = "x-client-token";

    /**
     * 结果返回
     */
    public static final String CODE = "code";
    public static final String MSG = "msg";
    public static final String DATA = "data";
    public static final String TIME = "time";

}
