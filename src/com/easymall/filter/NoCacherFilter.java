package com.easymall.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCacherFilter implements Filter
{
    public void destroy()
    {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.setDateHeader("Expires", -1);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");

        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
