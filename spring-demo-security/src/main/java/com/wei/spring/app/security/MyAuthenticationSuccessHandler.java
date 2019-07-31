package com.wei.spring.app.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by viruser on 2019/7/29.
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("login success");
        httpServletRequest.getSession().setAttribute("user_id","aaaa");

        httpServletResponse.sendRedirect("/security/");

//        // 允许跨域
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//        // 允许自定义请求头token(允许head跨域)
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
//        httpServletResponse.getWriter().write(200);
    }
}
