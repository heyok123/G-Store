package com.cupdata.auth.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cupdata.common.exception.MyException;
import com.cupdata.ums.entity.UserEntity;
import com.cupdata.ums.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @Description: 自定义 realm
 * Created by Wsork on 2021/1/22 10:51.
 **/
public class CustomRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRealm.class);

    @Autowired
    private UserService userService;

    /**
     * @Description: 授权
     * @Author: Wsork
     * @Date: 2021/1/22 10:53
     * @param: [principals]
     * @return: org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LOGGER.info("执行授权操作！");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//         获取权限
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
//        authorizationInfo.addStringPermission(user.getPerm);
        return authorizationInfo;

        //获取登录用户名
//        String name = (String) principals.getPrimaryPrincipal();
//        //查询用户名称
//        UserEntity user = loginService.getUserByName(name);
//        //添加角色和权限
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        for (Role role : user.getRoles()) {
//            //添加角色
//            simpleAuthorizationInfo.addRole(role.getRoleName());
//            //添加权限
//            for (Permissions permissions : role.getPermissions()) {
//                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
//            }
//        }
//        return simpleAuthorizationInfo;
    }

    /**
     * @Description: 认证
     * @Author: Wsork
     * @Date: 2021/1/22 10:54
     * @param: [token]
     * @return: org.apache.shiro.authc.AuthenticationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LOGGER.info("执行认证操作！");
        // 获取token
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        if (StringUtils.isEmpty(userToken.getUsername())){
            throw new MyException("参数有误!");
        }
        // 用户名认证
        String username = userToken.getUsername();
        UserEntity user = userService.getOne(new QueryWrapper<UserEntity>().eq("username", username));
        if (user == null) {
            throw new MyException("用户不存在!");
        }
        // 密码认证
        String password = String.valueOf(userToken.getPassword());
        return new SimpleAuthenticationInfo(user,password,getName());
    }
}
