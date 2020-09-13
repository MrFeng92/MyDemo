package com.easymall.backend;

import com.easymall.factory.BasicFactory;
import com.easymall.service.PrivilegeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AJAXUpdatePrivilegeServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            PrivilegeService service = BasicFactory.getFactory().getInstance(PrivilegeService.class);
            // 1.获取角色id和要修改的路径
            int role_id = Integer.parseInt(request.getParameter("role_id"));
            String path = request.getParameter("path");
            // 2.调用Service修改角色对应的路径
            String[] splitpath = path.split("\n");
            service.updatePrivilege(role_id, splitpath);
            // 3.返回消息
            response.getWriter().write("true");
        } catch (NumberFormatException e)
        {
            e.printStackTrace();
            response.getWriter().write("false");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
