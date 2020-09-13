package com.easymall.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

public class EncodingFilter implements Filter
{
    private String encode = null;

    public void destroy()
    {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        // 1.响应乱码
        response.setContentType("text/html;charset=" + encode);
        // 2.请求乱码，需要重写getParameterMap实现对GET方法的乱码处理
        chain.doFilter(new MyHttpServletRequest((HttpServletRequest) request), response);
    }

    public void init(FilterConfig config) throws ServletException
    {
        encode = config.getServletContext().getInitParameter("encode");
    }

    // 装饰类，改造HttpServletRequest中的getParameterMap,getParameterValues,getParameter方法
    // 继承HttpServletRequestWrapper类，改造需要重写的方法，其他方法wrapper类会自动调父类方法
    private class MyHttpServletRequest extends HttpServletRequestWrapper
    {
        private HttpServletRequest request = null;
        private boolean hasNotEncode = true;

        public MyHttpServletRequest(HttpServletRequest request)
        {
            super(request);
            this.request = request;
        }

        @Override
        public Map<String, String[]> getParameterMap()
        {
            try
            {
                if ("POST".equals(request.getMethod()))
                {
                    request.setCharacterEncoding(encode);
                    return request.getParameterMap();
                } else if ("GET".equals(request.getMethod()))
                {
                    Map<String, String[]> map = request.getParameterMap();
                    if (hasNotEncode)
                    {
                        for (Map.Entry<String, String[]> entry : map.entrySet())
                        {
                            String[] vs = entry.getValue();
                            for (int i = 0; i < vs.length; i++)
                            {
                                vs[i] = new String(vs[i].getBytes("iso8859-1"), encode);
                            }
                        }
                        hasNotEncode = false;
                    }
                    return map;
                } else
                {
                    return request.getParameterMap();
                }
            } catch (Exception e)
            {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        @Override
        public String[] getParameterValues(String name)
        {
            return getParameterMap().get(name);
        }

        @Override
        public String getParameter(String name)
        {
            String vs[] = getParameterValues(name);
            return vs == null ? null : vs[0];
        }
    }
}

