package com.easymall.listener;

import com.easymall.domain.Product;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.LinkedHashMap;

@WebListener()
public class MyHttpSessionListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener
{

    // Public constructor is required by servlet spec
    public MyHttpSessionListener()
    {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce)
    {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
    }

    public void contextDestroyed(ServletContextEvent sce)
    {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se)
    {
        /* Session is created. */
        se.getSession().setAttribute("cartmap", new LinkedHashMap<Product, Integer>());
    }

    public void sessionDestroyed(HttpSessionEvent se)
    {
        /* Session is destroyed. */
        se.getSession().removeAttribute("cartmap");
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe)
    {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe)
    {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe)
    {
      /* This method is invoked when an attribute
         is replaced in a session.
      */
    }
}
