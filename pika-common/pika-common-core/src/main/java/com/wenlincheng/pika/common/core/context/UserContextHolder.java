package com.wenlincheng.pika.common.core.context;

import com.wenlincheng.pika.common.core.exception.BaseException;

import java.util.Objects;

import static com.wenlincheng.pika.common.core.exception.SystemErrorCodeEnum.USER_NOT_LOGIN;

/**
 * 用户上下文
 *
 * @author  wenlincheng
 * @date    2020/11/24 19:39 下午
 * @version 1.0
 */
public class UserContextHolder {

   private final ThreadLocal<UserSessionData> USER_CONTEXT;

   private UserContextHolder() {
       this.USER_CONTEXT = new ThreadLocal<>();
   }

   /**
    * 创建实例
    *
    * @return UserContextHolder
    */
   public static UserContextHolder getInstance() {
       return SingletonHolder.INSTANCE;
   }

   /**
    * 单例初使化
    */
   private static class SingletonHolder {
       private static final UserContextHolder INSTANCE = new UserContextHolder();
   }

   /**
    * 设置用户上下文
    *
    * @param userSessionData 用户信息
    */
   public void setContext(UserSessionData userSessionData) {
       USER_CONTEXT.set(userSessionData);
   }

   /**
    * 获取用户上下文
    *
    * @return Map<String, String>
    */
   public UserSessionData getContext() {
       return USER_CONTEXT.get();
   }

    /**
     * 获取上下文中的用户信息
     *
     * @return PikaUser
     */
    public PikaUser getUser() {
        UserSessionData userSessionData = USER_CONTEXT.get();
        if (Objects.isNull(userSessionData)) {
            throw BaseException.construct(USER_NOT_LOGIN).build();
        }
        return userSessionData.getUser();
    }

   /**
    * 获取上下文中的用户名
    *
    * @return String
    */
   public String getUsername() {
       return getUser().getUsername();
   }

   /**
    * 获取上下文中的用户id
    *
    * @return Long
    */
    public Long getUserId() {
        return getUser().getUserId();
    }

   /**
    * 清空上下文
    */
   public void clear() {
       USER_CONTEXT.remove();
   }

}
