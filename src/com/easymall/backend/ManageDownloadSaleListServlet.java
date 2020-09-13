package com.easymall.backend;

import com.easymall.domain.SaleInfo;
import com.easymall.factory.BasicFactory;
import com.easymall.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

public class ManageDownloadSaleListServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
        // 1.查询出销售榜单
        List<SaleInfo> list = service.saleList();
        // 2.组织称Excel格式的数据（.csv）
        StringBuffer buffer = new StringBuffer();
        buffer.append("商品id,商品名称,销售总量\r\n");
        for (SaleInfo si : list)
        {
            buffer.append(si.getProd_id() + "," + si.getProd_name() + "," + si.getSale_num() + "\r\n");
        }
        String data = buffer.toString();
        // 3.提供下载
        response.setContentType("text/html;charset=gbk");
        response.setHeader("Content-Disposition", "attachment;filename="
                + URLEncoder.encode("EasyMall销售榜单" + new Date().toLocaleString() + ".csv", "utf-8"));
        response.getWriter().write(data);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
