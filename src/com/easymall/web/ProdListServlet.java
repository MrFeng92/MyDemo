package com.easymall.web;

import com.easymall.domain.PageInfo;
import com.easymall.factory.BasicFactory;
import com.easymall.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProdListServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ProdService prodService = BasicFactory.getFactory().getInstance(ProdService.class);
        int thispage = Integer.parseInt(request.getParameter("thispage"));
        int rownum = 10;
        PageInfo pi = prodService.findProdByPage(thispage, rownum);
        pi.setBaseurl(request.getContextPath() + "/servlet/ProdListServlet?");
        request.setAttribute("pi", pi);
        request.getRequestDispatcher("/prodList.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
