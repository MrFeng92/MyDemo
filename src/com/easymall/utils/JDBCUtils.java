package com.easymall.utils;

import java.sql.*;

public class JDBCUtils
{
    public JDBCUtils()
    {
    }

    public static Connection getConn() throws Exception
    {
        //1.注册驱动
        Class.forName(PropUtils.getProp("driver"));
        //2.获取链接
        Connection conn = DriverManager.getConnection(PropUtils.getProp("url"),
                PropUtils.getProp("user"), PropUtils.getProp("password"));
        return conn;
    }

    /**
     * 关闭资源
     *
     * @param rs
     * @param stat
     * @param conn
     */
    public static void closeAll(ResultSet rs, Statement stat, Connection conn)
    {
        if (rs != null)
        {
            try
            {
                rs.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            } finally
            {
                rs = null;
            }
        }
        if (stat != null)
        {
            try
            {
                stat.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            } finally
            {
                stat = null;
            }
        }
        if (conn != null)
        {
            try
            {
                conn.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            } finally
            {
                conn = null;
            }
        }

    }
}
