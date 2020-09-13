package com.easymall.web;

import com.easymall.factory.BasicFactory;
import com.easymall.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DelOrderServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        OrderService orderService = BasicFactory.getFactory().getInstance(OrderService.class);
        // 1.获取要删除的订单id
        String orderId = request.getParameter("id");
        // 2.调用service删除订单
        orderService.delOrder(orderId);
        // 3.返回订单列表
        response.getWriter().write("删除订单成功！");
        response.setHeader("Refresh", "1;url=" + request.getContextPath() + "/servlet/OrderListServlet");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
