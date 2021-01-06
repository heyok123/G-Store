package com.cupdata.ums.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;

/**
 * @Description: 自定义 Shiro 过滤器
 * @Author: wsz
 * @Date: 2020/12/21 20:05
 **/
public class ShiroLoginFilter extends FormAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroLoginFilter.class);

    /**
     * @Description: 如果isAccessAllowed返回false,则执行onAccessDenid方法
     * @Author: wsz
     * @Date: 2020/12/21 20:08
     * @param: [request, response, mappedValue]
     * @return: boolean
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (request instanceof HttpServletRequest) {
            if (HttpMethod.OPTIONS.matches(((HttpServletRequest) request).getMethod())) {
                return true;
            }
        }
        boolean accessAllowed = super.isAccessAllowed(request, response, mappedValue);
        if (accessAllowed) {
            // 登录认证之后持续用户的Session存活时间: 5min
            SecurityUtils.getSubject().getSession().setTimeout(1000 * 60 * 60 * 5);
        }
        return accessAllowed;
    }


    /**
     * @Description: 访问controller之前判断是否登录 返回json 不进行重定向
     * @Author: wsz
     * @Date: 2020/12/21 20:10
     * @param: [request, response, mappedValue]
     * @return: boolean
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();
        response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401

        HashMap<String, Object> map = new HashMap<>();
        map.put("code",HttpServletResponse.SC_UNAUTHORIZED);
        map.put("message","请先登录!");
        map.put("path",path);
        map.put("timestamp",String.valueOf(new Date().getTime()));

        try {
            String info = JSONObject.toJSONString(map);
            PrintWriter writer = response.getWriter();
            writer.write(info);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            LOGGER.info("异常错误信息:{}",e);
        }
        return false;
    }
}
