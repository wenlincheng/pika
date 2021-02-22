package com.wenlincheng.pika.common.core.session;

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
    * @param userSession 用户信息
    */
   public void setSession(UserSession userSession) {
       USER_SESSION_THREAD_LOCAL.set(userSession);
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
    * 获取Session中的卖家信息
    *
    * @return PikaSeller
    */
   public PikaSeller getSeller() {
       return USER_SESSION_THREAD_LOCAL.get() == null ? null : USER_SESSION_THREAD_LOCAL.get().getSeller();
   }

   /**
    * 清空Session
    */
   public void clear() {
       USER_SESSION_THREAD_LOCAL.remove();
   }

}
