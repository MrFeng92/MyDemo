package com.easymall.backend;

import com.easymall.domain.Resource;
import com.easymall.factory.BasicFactory;
import com.easymall.service.PrivilegeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AJAXGetResourceByRoleServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrivilegeService service = BasicFactory.getFactory().getInstance(PrivilegeService.class);
        // 1.获取角色id
        int role_id = Integer.parseInt(request.getParameter("role_id"));
        // 2.调用Service根据角色id查询该角色的所有资源
        List<Resource> list = service.findResourceByRoleId(role_id);
        // 3.响应发回数据
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        for (Resource r : list)
        {
            buffer.append("'" + r.getPath() + "',");
        }
        if (buffer.toString().endsWith(","))
        {
            buffer.deleteCharAt(buffer.length() - 1);
        }
        buffer.append("]");
        response.getWriter().write(buffer.toString());

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
