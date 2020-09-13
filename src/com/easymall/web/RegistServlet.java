package com.easymall.web;

import com.easymall.domain.User;
import com.easymall.factory.BasicFactory;
import com.easymall.service.UserService;
import com.easymall.utils.MD5Utils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理用户注册
 */
public class RegistServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            UserService userService = BasicFactory.getFactory().getInstance(UserService.class);
            //设置表单编码格式，注意：1.此设置只用于Post提交,因为Get提交是URL在提交时已经按照编码分析提交内容
            //2.此设置要在request.getParameter()方法之前执行，应为getParameter()
            // 方法执行时会按编码分析提交内容，后面不再分析
            // request.setCharacterEncoding("utf-8");
            //设置服务器用什么编码发送也会通知浏览器用什么编码打开
            //response.setContentType("text/html;charset=utf-8");

            //防止表单重复提交
            String resubNum1 = request.getParameter("resubNum");
            String resubNum2 = (String) request.getSession()
                    .getAttribute("resubNum");

            if (resubNum1 == null || resubNum2 == null || !resubNum1
                    .equals(resubNum2))
            {
                throw new RuntimeException("请不要重复提交!");
            } else
            {
                request.getSession().removeAttribute("resubNum");
            }
            // 验证验证码
            String valistr = request.getParameter("valistr");
            String valistr2 = (String) request.getSession()
                    .getAttribute("valistr");
            if (valistr == null || valistr2 == null || !valistr
                    .equals(valistr2))
            {
                request.setAttribute("msg", "验证码不正确");
                request.getRequestDispatcher("/regist.jsp")
                        .forward(request, response);
            }

            //获取表单数据并封装
            User user = new User();
            BeanUtils.populate(user, request.getParameterMap());
            //校验user中数据是否为空
            user.checkData();
            //注册用户将数据存到数据库
            user.setPassword(MD5Utils.md5(user.getPassword()));
            userService.registUser(user);
            // 返回主页
            response.getWriter().write("恭喜你注册成功，3秒后跳转到主页面。。。。。");
            response.setHeader("Refresh",
                    "3;url=" + request.getContextPath() + "/index.jsp");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }
}
