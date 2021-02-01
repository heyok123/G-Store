package com.cupdata.auth.filter;

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
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @Description: 自定义ShiroLoginFilter
 * Created by Wsork on 2021/1/22 16:33.
 **/
public class ShiroLoginFilter extends FormAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroLoginFilter.class);

    /**
     * @Description: 如果isAccessAllowed()返回false,执行onAccessDenied()
     * @Author: Wsork
     * @Date: 2021/1/22 16:49
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
            // 登录认证成功后 用户session持续存活时间
            SecurityUtils.getSubject().getSession().setTimeout(1000 * 60 * 30 );
        }
        return accessAllowed;
    }

    /**
     * @Description: 访问Controller之前判断是否登录 返回json 不进行重定向
     * @Author: Wsork
     * @Date: 2021/1/22 16:35
     * @param: [request, response]
     * @return: boolean
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setHeader("Access-Control-Allow-Origin",httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        HashMap<String, Object> map = new HashMap<>();
        map.put("code",HttpServletResponse.SC_UNAUTHORIZED);
        map.put("message","请先登录!");
        map.put("path",httpServletRequest.getServletPath());
        map.put("timestamp",String.valueOf(System.currentTimeMillis()));

        try {
            String info = JSONObject.toJSONString(map);
            PrintWriter writer = response.getWriter();
            writer.write(info);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            LOGGER.error("异常错误信息：{}",e);
        }
        return false;
    }
}
