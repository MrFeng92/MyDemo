package com.easymall.web;

import com.easymall.factory.BasicFactory;
import com.easymall.service.OrderService;
import com.easymall.utils.PaymentUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

public class CallBackServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
        // 获得回调所有数据
        String p1_MerId = request.getParameter("p1_MerId");
        String r0_Cmd = request.getParameter("r0_Cmd");
        String r1_Code = request.getParameter("r1_Code");
        String r2_TrxId = request.getParameter("r2_TrxId");
        String r3_Amt = request.getParameter("r3_Amt");
        String r4_Cur = request.getParameter("r4_Cur");
        String r5_Pid = request.getParameter("r5_Pid");
        String r6_Order = request.getParameter("r6_Order");
        String r7_Uid = request.getParameter("r7_Uid");
        String r8_MP = request.getParameter("r8_MP");
        String r9_BType = request.getParameter("r9_BType");
        String rb_BankId = request.getParameter("rb_BankId");
        String ro_BankOrderId = request.getParameter("ro_BankOrderId");
        String rp_PayDate = request.getParameter("rp_PayDate");
        String rq_CardNo = request.getParameter("rq_CardNo");
        String ru_Trxtime = request.getParameter("ru_Trxtime");
        // 身份校验 --- 判断是不是支付公司通知你
        String hmac = request.getParameter("hmac");
        if (PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, ResourceBundle.getBundle("merchantInfo").getString("keyValue")))
        {
            if ("1".equals(r9_BType))
            {
                // 注意在正式的开发中，下面代码应该在r9_BType=2的位置出写，但是现在测试阶段没办法写在这里了
                service.updatePayState(r6_Order, 1);
                response.getWriter().write("SUCCESS");

                // 浏览器访问，不能相信，只做提示
                response.getWriter().write("支付成功！");
                response.setHeader("Refresh", "1;url=" + request.getContextPath()
                        + "/servlet/OrderListServlet");

                return;
            } else if ("2".equals(r9_BType))
            {
                // 易宝服务访问，可以相信，修改订单的支付状态为已支付
                service.updatePayState(r6_Order, 1);
                response.getWriter().write("SUCCESS");
                return;
            } else
            {
                throw new RuntimeException("支付结果码未知！");
            }
        } else
        {
            throw new RuntimeException("支付回调数据被篡改过！");
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}
