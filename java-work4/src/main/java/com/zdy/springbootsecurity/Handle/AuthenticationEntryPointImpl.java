package com.zdy.springbootsecurity.Handle;



import com.alibaba.fastjson.JSON;

import com.zdy.springbootsecurity.domain.ResponseResult;
import com.zdy.springbootsecurity.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.management.BadBinaryOpValueExpException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : k
 * @Date : 2022/3/24
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
