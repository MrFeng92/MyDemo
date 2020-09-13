package com.easymall.utils;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;

public class TransactionManager
{
    private static ThreadLocal<Connection> conn_tl = new ThreadLocal<Connection>()
    {

        protected Connection initialValue()
        {
            try
            {
                return DaoUtils.getConn();// 获取c3p0连接池里面的连接
            } catch (Exception e)
            {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        ;
    };

    private TransactionManager()
    {
    }

    /**
     * 开启事务
     */
    public static void startTransaction()
    {
        try
        {
            conn_tl.get().setAutoCommit(false);
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交事务
     */
    public static void submitTransaction()
    {
        try
        {
            conn_tl.get().commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 回滚事务
     */
    public static void rollbackTransaction()
    {
        try
        {
            conn_tl.get().rollback();
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConn()
    {
        return conn_tl.get();
    }

    /**
     * 释放资源
     */
    public static void release()
    {
        DbUtils.closeQuietly(conn_tl.get());// 此线程执行完了，释放对应线程上的Connection
        conn_tl.remove();
    }
}
