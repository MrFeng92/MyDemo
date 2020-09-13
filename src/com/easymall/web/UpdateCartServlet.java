package com.easymall.web;

import com.easymall.domain.Product;
import com.easymall.factory.BasicFactory;
import com.easymall.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UpdateCartServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ProdService service = BasicFactory.getFactory().getInstance(ProdService.class);
        //1.获取要修改的商品的id 和 新的数量
        String id = request.getParameter("id");
        int buynum = Integer.parseInt(request.getParameter("buynum"));
        Product prod = service.findProdById(id);
        //2.获取购物车map
        Map<Product, Integer> cartmap = (Map<Product, Integer>) request.getSession().getAttribute("cartmap");
        //3.修改
        cartmap.put(prod, buynum);
        //4.回到购物车页面
        response.sendRedirect(request.getContextPath() + "/cart.jsp");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
