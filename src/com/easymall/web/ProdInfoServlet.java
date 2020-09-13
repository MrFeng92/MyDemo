package com.easymall.web;

import com.easymall.domain.Product;
import com.easymall.factory.BasicFactory;
import com.easymall.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProdInfoServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ProdService service = BasicFactory.getFactory().getInstance(ProdService.class);
        //1.获取要查询的商品id
        String id = request.getParameter("id");
        //2.调用Servicew根据id查询商品
        Product prod = service.findProdById(id);
        request.setAttribute("prod", prod);
        //3.将商品信息存入request域 带到页面显示
        request.getRequestDispatcher("/prodInfo.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
