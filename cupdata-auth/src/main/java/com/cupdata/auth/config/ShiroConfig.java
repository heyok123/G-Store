package com.cupdata.auth.config;

import com.cupdata.auth.filter.ShiroLoginFilter;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: shiro配置类
 * Created by Wsork on 2021/1/22 15:51.
 **/
@Configuration
public class ShiroConfig {

    /**
     * > 认证过滤器:
     * anon：无需认证。
     * authc：必须认证。
     * authcBasic：需要通过 HTTPBasic 认证。
     * user：不一定通过认证，只要曾经被 Shiro 记录即可，比如：记住我。
     *
     * > 授权过滤器:
     * perms：必须拥有某个权限才能访问。
     * role：必须拥有某个角色才能访问。
     * port：请求的端口必须是指定值才可以。
     * rest：请求必须基于 RESTful，POST、PUT、GET、DELETE。
     * ssl：必须是安全的 URL 请求，协议 HTTPS。
     */

    /**
     * @Description: 过滤规则 创建ShiroFilterFactoryBean
     * @Author: Wsork
     * @Date: 2021/1/22 16:09
     * @param: [securityManager]
     * @return: org.apache.shiro.spring.web.ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 将shiroLoginFilter注入到ShiroFilter
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authc",new ShiroLoginFilter());
        // 登录
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/login","anon");
//        map.put("/user/list","authc");
        // 获取验证码
        map.put("/verCode","anon");
        // 登出
        map.put("/logout","anon");
        // 忘记密码 系统管理员验证
        map.put("/forgetPwd","anon");
        // 显示密保问题
        map.put("/showPwdQuestion","anon");
        // 密保问题验证
        map.put("/security","anon");
        // 通过密保后 修改密码
        map.put("/updatePwd","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * @Description: 安全管理器
     * @Author: Wsork
     * @Date: 2021/1/22 16:09
     * @param: [realm]
     * @return: org.apache.shiro.web.mgt.DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(customRealm());
        manager.setSessionManager(sessionManager());
        return manager;
    }

    /**
     * @Description: 注入自定义realm
     * @Author: Wsork
     * @Date: 2021/1/22 16:12
     * @param: []
     * @return: com.cupdata.helloworld.config.CustomRealm
     */
    @Bean
    public CustomRealm customRealm(){
        return new CustomRealm();
    }

    /**
     * @Description: 开启aop注解支持(@RequiresRoles @RequiresPermissions...)
     *               配置两个bean(AuthorizationAttributeSourceAdvisor + DefaultAdvisorAutoProxyCreator)
     * @Author: Wsork
     * @Date: 2021/1/22 16:20
     * @param: []
     * @return: org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    /**
     * @Description: 开启Shiro的注解支持
     * Created by Wsork on 2021/2/4 11:11
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * @Description: session会话管理
     * @Author: Wsork
     * @Date: 2021/1/22 16:25
     * @param: []
     * @return: org.apache.shiro.session.mgt.SessionManager
     */
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setSessionIdCookie(simpleCookie());
        return sessionManager;
    }

    /**
     * @Description: 使用默认MemorySessionDAO操作session
     * @Author: Wsork
     * @Date: 2021/1/22 16:27
     * @param: []
     * @return: org.apache.shiro.session.mgt.eis.SessionDAO
     */
    @Bean
    public SessionDAO sessionDAO(){
        return new MemorySessionDAO();
    }

    @Bean
    public Cookie simpleCookie(){
        SimpleCookie cookie = new SimpleCookie();
        cookie.setName("SHIROJSESSIONID");
        return cookie;
    }

}
