package com.easymall.backend;

import com.easymall.domain.SaleInfo;
import com.easymall.factory.BasicFactory;
import com.easymall.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManageSaleListServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        OrderService orderService = BasicFactory.getFactory().getInstance(OrderService.class);
        // 1.查出销售榜单
        List<SaleInfo> saleList = orderService.saleList();
        // 2.存入request域
        request.setAttribute("list", saleList);
        // 3.带到页面显示
        request.getRequestDispatcher("/backend/manageSaleList.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
