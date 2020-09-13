package com.easymall.web;

import com.easymall.domain.OrderInfo;
import com.easymall.domain.User;
import com.easymall.factory.BasicFactory;
import com.easymall.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderListServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        OrderService orderService = BasicFactory.getFactory().getInstance(OrderService.class);
        // 1.获取当前用户信息
        User user = (User) request.getSession().getAttribute("user");
        // 2.调用service根据用户id查询所有订单信息
        List<OrderInfo> list = orderService.findOrderInfoByUserId(user.getId());
        // 3.存入request域中
        request.setAttribute("list", list);
        request.getRequestDispatcher("/orderList.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
