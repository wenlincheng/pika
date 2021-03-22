package com.wenlincheng.pika.auth.config;

import com.wenlincheng.pika.auth.filter.JWTAuthenticationFilter;
import com.wenlincheng.pika.auth.filter.PikaFilterSecurityInterceptor;
import com.wenlincheng.pika.auth.filter.PikaUsernamePasswordAuthenticationFilter;
import com.wenlincheng.pika.auth.filter.WebSecurityCorsFilter;
import com.wenlincheng.pika.auth.handler.EntryPointUnauthorizedHandler;
import com.wenlincheng.pika.auth.handler.RestAccessDeniedHandler;
import com.wenlincheng.pika.auth.service.impl.UserDetailsServiceImpl;
import com.wenlincheng.pika.common.core.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security 配置
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    protected AuthenticationManager authenticationManager;
    @Autowired
    private AuthIgnoredUrisProperties authIgnoredUrlsProperties;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failHandler;
    @Autowired
    private RestAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
    @Autowired
    private PikaFilterSecurityInterceptor filterSecurityInterceptor;
    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private RedisUtils redisUtils;

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * 修改AuthenticationManager的注入，
     * 解决http.getSharedObject(AuthenticationManager.class)
     * 无法获取AuthenticationManager实例的问题
     *
     * @return org.springframework.security.authentication.AuthenticationManager
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // @Override
    // public void configure(WebSecurity web) throws Exception {
    //     web.ignoring().antMatchers("/auth/code/**","/login/v1","/auth/token/**", "/auth/logout");
    // }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 登录认证过滤器
        PikaUsernamePasswordAuthenticationFilter authenticationFilter = new PikaUsernamePasswordAuthenticationFilter(authenticationManager, successHandler, failHandler, redisUtils);
        // 除配置文件忽略路径其它所有请求都需经过认证和授权
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        for(String url: authIgnoredUrlsProperties.getUris()) {
            registry.antMatchers(url).permitAll();
        }

        http
            // 添加jtw鉴权过滤器
            .addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            // 权限管理过滤器 网关通过调用服务鉴权 所以这里注释掉
            //.addFilterAt(filterSecurityInterceptor, FilterSecurityInterceptor.class)
            // 添加自定义权限过滤器
            .addFilterBefore(new WebSecurityCorsFilter(), ChannelProcessingFilter.class)
            // 添加自定义登陆过滤器
            .addFilterAfter(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .cors()
            .and()
            // 关闭csrf 所有请求可以访问
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(entryPointUnauthorizedHandler).accessDeniedHandler(accessDeniedHandler)
            .and()
            // 关闭session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .anyRequest().authenticated();
    }
}
