package com.easymall.dao;

import com.easymall.domain.User;

public interface UserDao extends Dao
{
    /**
     * 根据用户名查找用户
     * @param username 要查找的用户名
     * @return 有返回找到的用户，没有返回null
     */
    public User findUserByUserName(String username);

    /**
     * 增加用户
     * @param user 用户信息
     */
    public void addUser(User user);

    /**
     * 根据用户名密码查找用户
     * @param username 用户名
     * @param password 密码
     * @return 找到返回用户，否则返回null
     */
    public User findUserByUsernameAndPassword(String username, String password);

    User findUserByUserId(int user_id);
}
