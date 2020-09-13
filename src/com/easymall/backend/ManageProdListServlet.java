package com.easymall.backend;

import com.easymall.domain.Product;
import com.easymall.factory.BasicFactory;
import com.easymall.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManageProdListServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ProdService service = BasicFactory.getFactory().getInstance(ProdService.class);
        //1.查询出所有商品,存入request域
        List<Product> list = service.findAllProds();
        request.setAttribute("list", list);
        //2.转发到商品管理页面显示
        request.getRequestDispatcher("/backend/manageProdList.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
