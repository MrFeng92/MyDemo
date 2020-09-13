package com.easymall.dao;

import com.easymall.domain.User;
import com.easymall.utils.TransactionManager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao
{
    @Override
    public User findUserByUserName(String username)
    {
        String sql = "select * from user where username = ?";
        try
        {
            QueryRunner runner = new QueryRunner();
            User user = runner.query(TransactionManager.getConn(), sql, new BeanHandler<User>(User.class), username);
            return user;
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user)
    {
        String sql = "insert into user values(null,?,?,?,?,?)";
        try
        {
            QueryRunner runner = new QueryRunner();
            runner.update(TransactionManager.getConn(), sql, user.getUsername(), user.getPassword(), user.getNickname(), user.getEmail(), user.getRole_id());
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password)
    {
        String sql = "select * from user where username = ? and password = ?";
        try
        {
            QueryRunner runner = new QueryRunner();
            User user = runner.query(TransactionManager.getConn(), sql, new BeanHandler<User>(User.class), username, password);
            return user;
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public User findUserByUserId(int user_id)
    {
        String sql = "select * from user where id=?";
        try
        {
            QueryRunner runner = new QueryRunner();
            User user = runner.query(TransactionManager.getConn(), sql, new BeanHandler<User>(User.class), user_id);
            return user;
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
