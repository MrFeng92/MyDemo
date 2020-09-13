package com.easymall.filter;

import com.easymall.domain.User;
import com.easymall.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

public class AutoLoginFilter implements Filter
{
    public void destroy()
    {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getSession(false) == null || req.getSession().getAttribute("user") == null)
        {
            //如果没有进来了，表示session中有登陆信息，用户已登陆
            //检查是否点击了自动登陆按钮
            Cookie[] cookies = req.getCookies();
            Cookie autoCookie = null;
            if (cookies != null)
            {
                for (Cookie cookie : cookies)
                {
                    if ("autologin".equals(cookie.getName()))
                    {
                        autoCookie = cookie;
                    }
                }
            }
            if (autoCookie != null)
            {
                //URLDecoder对URLEncoder编码进行解码
                String username = URLDecoder.decode(autoCookie.getValue(), "utf-8").split(":")[0];
                String password = URLDecoder.decode(autoCookie.getValue(), "utf-8").split(":")[1];
                UserServiceImpl userService = new UserServiceImpl();
                User user = userService.loginUser(username, password);
                //如果登陆成功将消息存到session
                if (user != null)
                {
                    req.getSession().setAttribute("user", user);
                }
            }

        }


        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
