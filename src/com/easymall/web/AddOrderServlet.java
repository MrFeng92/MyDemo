package com.easymall.web;

import com.easymall.domain.Order;
import com.easymall.domain.OrderItem;
import com.easymall.domain.Product;
import com.easymall.domain.User;
import com.easymall.factory.BasicFactory;
import com.easymall.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AddOrderServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
        User user = (User) request.getSession().getAttribute("user");
        // 1.将订单信息封装的bean中
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setPaystate(0);
        order.setReceiverinfo(request.getParameter("receiverinfo"));
        order.setUser_id(user.getId());

        // --永远不要轻信客户端提交的数据，特别是像钱这样的敏感数据，金额不应该由前台传入，而是应该在后台计算出来
        Map<Product, Integer> cartmap = (Map<Product, Integer>) request.getSession().getAttribute("cartmap");
        List<OrderItem> list = new ArrayList<OrderItem>();
        double money = 0;
        for (Map.Entry<Product, Integer> entry : cartmap.entrySet())
        {
            money += entry.getKey().getPrice() * entry.getValue();

            OrderItem item = new OrderItem();
            item.setOrder_id(order.getId());
            item.setProduct_id(entry.getKey().getId());
            item.setBuynum(entry.getValue());
            list.add(item);
        }
        order.setMoney(money);
        // 2.调用Service增加订单
        service.addOrder(order, list);
        // 3.清空购物车
        cartmap.clear();
        // 4.回到订单列表页面
        // TODO 回到订单列表页面
        response.getWriter().write("订单增加成功，3秒后返回订单列表页面。。。");
        response.setHeader("Refresh", "1;url=" + request.getContextPath() + "/servlet/OrderListServlet");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
