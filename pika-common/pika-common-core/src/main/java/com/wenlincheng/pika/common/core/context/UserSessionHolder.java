package com.wenlincheng.pika.common.core.context;

import com.wenlincheng.pika.common.core.exception.PikaException;

import java.util.Objects;

import static com.wenlincheng.pika.common.core.exception.SystemErrorCodeEnum.USER_NOT_LOGIN;

/**
 * 用户Session
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class UserSessionHolder {

   private final ThreadLocal<UserSession> USER_SESSION_THREAD_LOCAL = new ThreadLocal<>();

   private static final UserSessionHolder INSTANCE = new UserSessionHolder();

   private UserSessionHolder() {
   }

   /**
    * 获取单例实例
    *
    * @return UserContextHolder
    */
   public static UserSessionHolder getInstance() {
       return INSTANCE;
   }

   /**
    * 设置用户Session
    *
    * @param userSessionData 用户信息
    */
   public void setSession(UserSession userSessionData) {
       USER_SESSION_THREAD_LOCAL.set(userSessionData);
   }

   /**
    * 获取用户Session
    *
    * @return Map<String, String>
    */
   public UserSession getSession() {
       return USER_SESSION_THREAD_LOCAL.get();
   }

    /**
     * 获取Session中的用户信息
     *
     * @return PikaUser
     */
    public PikaUser getUser() {
        return USER_SESSION_THREAD_LOCAL.get() == null ? null : USER_SESSION_THREAD_LOCAL.get().getUser();
    }

   /**
    * 获取Session中的用户名
    *
    * @return String
    */
   public String getUsername() {
       return getUser().getUsername();
   }

   /**
    * 获取Session中的用户id
    *
    * @return Long
    */
    public Long getUserId() {
        return getUser().getId();
    }

   /**
    * 清空Session
    */
   public void clear() {
       USER_SESSION_THREAD_LOCAL.remove();
   }

}
