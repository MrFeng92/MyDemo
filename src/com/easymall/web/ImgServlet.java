package com.easymall.web;

import com.easymall.domain.Product;
import com.easymall.factory.BasicFactory;
import com.easymall.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImgServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ProdService prodService = BasicFactory.getFactory().getInstance(ProdService.class);
        String id = request.getParameter("id");
        Product prod = prodService.findProdById(id);
        if (prod != null)
        {
            String imgurl = prod.getImgurl();
            request.getRequestDispatcher(imgurl).forward(request, response);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
