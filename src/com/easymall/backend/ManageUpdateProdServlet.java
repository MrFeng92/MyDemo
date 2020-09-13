package com.easymall.backend;

import com.easymall.factory.BasicFactory;
import com.easymall.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManageUpdateProdServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ProdService service = BasicFactory.getFactory().getInstance(ProdService.class);
        //1.获取要修改的商品的id和数量
        String id = request.getParameter("id");
        int newNum = Integer.parseInt(request.getParameter("newNum"));
        //2.调用Service中修改商品库存数量的方法
        service.updatePnum(id, newNum);
        //3.回到后台管理商品列表页面
        response.sendRedirect(request.getContextPath() + "/servlet/ManageProdListServlet");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
