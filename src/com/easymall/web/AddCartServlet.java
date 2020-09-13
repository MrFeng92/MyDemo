package com.easymall.web;

import com.easymall.domain.Product;
import com.easymall.factory.BasicFactory;
import com.easymall.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class AddCartServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ProdService service = BasicFactory.getFactory().getInstance(ProdService.class);
        //1.获取要加入购物车的商品id 和数量
        String id = request.getParameter("id");
        int buynum = Integer.parseInt(request.getParameter("buynum"));
        //2.根据id查询商品
        Product prod = service.findProdById(id);
        //3.获取购物车
        HttpSession session = request.getSession();
        Map<Product, Integer> cartmap = (Map<Product, Integer>) session.getAttribute("cartmap");
        //4.加入购物车：如果之前没有就加入 如果之前有就修改数量
        cartmap.put(prod, cartmap.containsKey(prod) ? cartmap.get(prod) + buynum : buynum);
        //5.回到购物车页面
        response.sendRedirect(request.getContextPath() + "/cart.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
