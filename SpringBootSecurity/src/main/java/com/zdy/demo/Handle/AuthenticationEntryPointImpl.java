package com.zdy.demo.Handle;



import com.alibaba.fastjson.JSON;

import com.zdy.demo.pojo.ResponseResult;
import com.zdy.demo.util.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Desc : 认证的异常处理类
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(-1,"身份认证失败");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }


}
