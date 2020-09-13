package com.easymall.backend;

import com.easymall.domain.Role;
import com.easymall.factory.BasicFactory;
import com.easymall.service.PrivilegeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManagePrivilegeServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrivilegeService service = BasicFactory.getFactory().getInstance(PrivilegeService.class);
        // 1.调用Service查询所有角色信息
        List<Role> list = service.getRoles();
        // 2.存入request域带到页面显示
        request.setAttribute("list", list);
        request.getRequestDispatcher("/backend/managePrivilege.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
