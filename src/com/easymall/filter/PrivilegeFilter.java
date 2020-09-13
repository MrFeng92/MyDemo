package com.easymall.filter;

import com.easymall.domain.User;
import com.easymall.factory.BasicFactory;
import com.easymall.service.PrivilegeService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PrivilegeFilter implements Filter
{
    public void destroy()
    {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        PrivilegeService service = BasicFactory.getFactory().getInstance(PrivilegeService.class);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //1.拦截当前请求
        String uri = req.getRequestURI();
        uri = uri.substring(req.getContextPath().length());
        //2.检查当前访问的资源是否需要权限
        boolean needRole = service.needPriv(uri);
        if (needRole)
        {
            if (req.getSession(false) == null || req.getSession().getAttribute("user") == null)
            {
                //需要权限,而未登陆，先去登陆
                response.getWriter().write("当前资源需要权限，请先登陆后再操作！");
                resp.setHeader("Refresh", "1;url=" + req.getContextPath() + "/login.jsp");
                return;
            } else
            {
                //需要权限，登陆过，检查用户具有的角色是否符合
                User user = (User) req.getSession().getAttribute("user");
                if (service.hasPriv(user.getRole_id(), uri))
                {
                    chain.doFilter(request, response);
                    return;
                } else
                {
                    throw new RuntimeException("权限不足！无法访问！");
                }
            }

        } else
        {
            //不需要权限，直接放行
            chain.doFilter(request, response);
            return;
        }
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
