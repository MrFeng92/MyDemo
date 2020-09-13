package com.easymall.service;

import com.easymall.dao.UserDao;
import com.easymall.domain.User;
import com.easymall.exception.MsgException;
import com.easymall.factory.BasicFactory;

public class UserServiceImpl implements UserService
{
    // 获取UserDao得实现类
    UserDao userDao = BasicFactory.getFactory().getInstance(UserDao.class);// 向上造型，单例模式获取userDao

    /*
     * 用户注册
     */
    public void registUser(User user)
    {
        if (userDao.findUserByUserName(user.getUsername()) != null)// 如果根据用户名从数据库中查找到数据，则用户名已存在
        {
            throw new MsgException("用户名已存在！");
        }
        userDao.addUser(user);// 用户名不存在，将注册信息存到数据库
    }

    /**
     * 登陆用户
     * @param username 用户名
     * @param password 密码
     * @return 如果用户名密码正确，则返回该用户bean表示登陆 ，如果用户名密码不正确，则返回null表示不登陆
     */
    public User loginUser(String username, String password)
    {
        return userDao.findUserByUsernameAndPassword(username, password);
    }

    /**
     * 检查用户名已经存在
     * @param username
     * @return
     */
    public boolean hasUser(String username)
    {
        return userDao.findUserByUserName(username) != null;
    }
}
