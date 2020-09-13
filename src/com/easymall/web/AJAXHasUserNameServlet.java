package com.easymall.web;

import com.easymall.factory.BasicFactory;
import com.easymall.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AJAXHasUserNameServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            UserService service = BasicFactory.getFactory().getInstance(UserService.class);
            request.setCharacterEncoding("utf-8");
            //1.获取要检查的用户名
            String username = request.getParameter("username");
            //2.调用Service根据用户名查找用户,返回结果
            response.getWriter().write(service.hasUser(username) + "");
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
