package com.easymall.web;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 1.查看用户是否登录，用户登录会产生session
        // request.getSession(false)有就返回session没有返回null
        if (request.getSession(false) != null)
        {
            request.getSession().invalidate();
        }
        //清除cookie信息
        Cookie rnamec = new Cookie("autologin", "");
        rnamec.setPath(request.getContextPath() + "/");
        rnamec.setMaxAge(0);
        response.addCookie(rnamec);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
