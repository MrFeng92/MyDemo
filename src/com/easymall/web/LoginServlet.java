package com.easymall.web;

import com.easymall.domain.User;
import com.easymall.factory.BasicFactory;
import com.easymall.service.UserService;
import com.easymall.utils.MD5Utils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class LoginServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        UserService service = BasicFactory.getFactory().getInstance(UserService.class);
        try
        {
            // request.setCharacterEncoding("utf-8");
            //0.处理记住用户名
            if ("true".equals(request.getParameter("remname")))
            {
                //使用指定的编码机制将字符串转换为 application/x-www-form-urlencoded 格式
                Cookie c = new Cookie("remname", URLEncoder.encode(request.getParameter("username"), "utf-8"));
                c.setPath(request.getContextPath() + "/");
                c.setMaxAge(3600 * 24 * 30);
                response.addCookie(c);
            } else
            {
                Cookie c = new Cookie("remname", "");
                c.setPath(request.getContextPath() + "/");
                c.setMaxAge(0);
                response.addCookie(c);
            }

            //1.获取用户名密码
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            password = MD5Utils.md5(password);
            //2.调用Service检查用户名密码
            User user = service.loginUser(username, password);
            //3.如果不正确提示
            if (user == null)
            {
                request.setAttribute("msg", "用户名密码不正确！");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            //4.如果正确则登陆用户名
            request.getSession().setAttribute("user", user);
            // 处理自动登陆
            if ("true".equals(request.getParameter("autologin")))
            {
                Cookie autologinC = new Cookie("autologin", URLEncoder.encode(user.getUsername() + ":"
                        + user.getPassword(), "utf-8"));
                autologinC.setPath(request.getContextPath() + "/");
                autologinC.setMaxAge(3600 * 24 * 30);
                response.addCookie(autologinC);
            }
            //5.回到主页
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }
}
