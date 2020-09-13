package com.easymall.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoUtils
{
    private static ComboPooledDataSource source = new ComboPooledDataSource();

    public DaoUtils()
    {
    }

    public static DataSource getSource()
    {
        return source;
    }

    public static Connection getConn() throws SQLException
    {
        return source.getConnection();
    }
}
