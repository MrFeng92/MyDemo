package com.easymall.web;

import com.easymall.domain.PageInfo;
import com.easymall.domain.ProdFindCond;
import com.easymall.factory.BasicFactory;
import com.easymall.service.ProdService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FindProdByCondServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            ProdService prodService = BasicFactory.getFactory().getInstance(ProdService.class);
            int thispage = Integer.parseInt(request.getParameter("thispage"));
            int rownum = 10;
            ProdFindCond prodFindCond = new ProdFindCond();
            BeanUtils.populate(prodFindCond, request.getParameterMap());

            prodService.findProdByCond(prodFindCond, thispage, rownum);
            PageInfo pi = prodService.findProdByCond(prodFindCond, thispage, rownum);
            pi.setBaseurl(request.getContextPath() + "/servlet/FindProdByCondServlet" + "?" + prodFindCond.toString());
            //3.将查询到的商品信息存入request域
            request.setAttribute("pi", pi);
            //4.带回到商品列表页面显示
            request.getRequestDispatcher("/prodList.jsp").forward(request, response);
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
