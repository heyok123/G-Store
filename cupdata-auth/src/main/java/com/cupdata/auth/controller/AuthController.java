package com.cupdata.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cupdata.auth.util.ConstantUtils;
import com.cupdata.auth.util.VerCodeUtil;
import com.cupdata.common.bean.ResponseVo;
import com.cupdata.common.exception.MyException;
import com.cupdata.ums.entity.UserEntity;
import com.cupdata.ums.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description: 认证中心接口
 * Created by Wsork on 2021/1/22 17:29.
 **/
@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * @Description: verCode
     * @Author: Wsork
     * @Date: 2021/1/22 17:50
     * @param: [request, response]
     * @return: com.cupdata.helloworld.core.ResponseVo
     */
    @GetMapping("/verCode")
    public ResponseVo verCode(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Content-Type","image/png");
        Map<String, Object> map = VerCodeUtil.getCheckCodePic();
        String code = (String) map.get("code");
        HttpSession session = request.getSession();
        session.setAttribute(ConstantUtils.VER_CODE,code);
        System.out.println(code);
        return ResponseVo.ok(code);
    }

    /**
     * @Description: login
     * @Author: Wsork
     * @Date: 2021/1/22 17:32
     * @param: [user, session]
     * @return: com.cupdata.helloworld.core.ResponseVo
     */
    @PostMapping("/login")
    public ResponseVo<UserEntity> login(@RequestBody UserEntity user, HttpSession session){
        if (user == null){
            throw new MyException("参数校验失败！");
        }
        // 验证码校验
        String code = (String) session.getAttribute(ConstantUtils.VER_CODE);
        if (StringUtils.isEmpty(code) || !code.equalsIgnoreCase(user.getVerCode())) {
            session.removeAttribute(ConstantUtils.VER_CODE);
            throw new MyException("验证码填写错误！");
        }
        session.removeAttribute(ConstantUtils.VER_CODE);
        // 账号校验
        UserEntity userReal = this.userService.getOne(new QueryWrapper<UserEntity>().eq("username",user.getUsername()));
        if (userReal == null) {
            throw new MyException("用户名或密码错误！");
        }
        // 用户状态校验
        if ("0".equals(userReal.getStatus())) {
            throw new MyException("账号已被禁用!");
        }
        // 用户登录错误次数校验
//        if (UserVo.USER_LOGIN_ERROR_NUM_MAX.equals(user.getErrorNum())) {
//            throw new MyException("账号登录错误次数超过3次,已被禁用!");
//        }
        // 密码校验
        if (StringUtils.isEmpty(user.getPassword()) || !userReal.getPassword().equals(user.getPassword())) {
            throw new MyException("用户名或密码错误！");
        }
        // shiro登录
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        // 用户信息
        UserEntity userInfo = (UserEntity) subject.getPrincipal();
        String shiroSessionId = (String) subject.getSession().getId();
        // 存储token
        userInfo.setToken(shiroSessionId);
        SimplePrincipalCollection collection = new SimplePrincipalCollection(userInfo, userInfo.getUsername());
        subject.runAs(collection);
        UserEntity userData = new UserEntity();
        BeanUtils.copyProperties(userInfo,userData);

        return ResponseVo.ok(userData);
    }

    /**
     * @Description: 登出
     * @Author: Wsork
     * @Date: 2021/1/22 18:19
     * @param: []
     * @return: com.cupdata.helloworld.core.ResponseVo
     */
    @GetMapping("/logout")
    public ResponseVo logout(){
        SecurityUtils.getSubject().logout();
        return ResponseVo.ok();
    }

    /**
     * @Description: 返回登录用户信息
     * @Author: Wsork
     * @Date: 2021/1/22 18:14
     * @param: []
     * @return: com.cupdata.helloworld.core.ResponseVo
     */
    @GetMapping("/getLoginUser")
    public ResponseVo getUserInfo(){
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        UserEntity userRole = new UserEntity();
        // list<UserRole>
        // list<Role>
//        user.setRoleName()
        return ResponseVo.ok(user);
    }

    /**
     * @Description: 返回登录用户权限信息
     * @Author: Wsork
     * @Date: 2021/1/22 18:18
     * @param: []
     * @return: com.cupdata.helloworld.core.ResponseVo
     */
    @GetMapping("/getUserPerms")
    public ResponseVo getUserPerms(){
        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
        // list<userRole>
        // list<role>
        return ResponseVo.ok();
    }


}
