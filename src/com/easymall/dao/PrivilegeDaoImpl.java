package com.easymall.dao;

import com.easymall.domain.Resource;
import com.easymall.domain.Role;
import com.easymall.utils.TransactionManager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class PrivilegeDaoImpl implements PrivilegeDao
{
    public List<Resource> findResourceByPath(String path)
    {
        String sql = "select * from resource where path = ?";
        try
        {
            QueryRunner runner = new QueryRunner();
            return runner.query(TransactionManager.getConn(), sql, new BeanListHandler<Resource>(Resource.class), path);
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Role> getRoles()
    {
        String sql = "select * from role";
        try
        {
            QueryRunner runner = new QueryRunner();
            return runner.query(TransactionManager.getConn(), sql, new BeanListHandler<Role>(Role.class));
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Resource> findResourceByRoleId(int role_id)
    {
        String sql = "select * from resource where role_id = ?";
        try
        {
            QueryRunner runner = new QueryRunner();
            return runner.query(TransactionManager.getConn(), sql, new BeanListHandler<Resource>(Resource.class), role_id);
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delResourceByRoleId(int role_id)
    {
        String sql = "delete from resource where role_id = ?";
        try
        {
            QueryRunner runner = new QueryRunner();
            runner.update(TransactionManager.getConn(), sql, role_id);
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void addresourceByRoleId(int role_id, String p)
    {
        String sql = "insert into resource values (null,?,?)";
        try
        {
            QueryRunner runner = new QueryRunner();
            runner.update(TransactionManager.getConn(), sql, p, role_id);
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
