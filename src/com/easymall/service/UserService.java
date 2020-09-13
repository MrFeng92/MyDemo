package com.easymall.service;

import com.easymall.domain.User;

public interface UserService extends Service
{
    /**
     * 注册用户的方法
     * @param user
     */
    public void registUser(User user);

    /**
     * 登陆用户
     * @param username 用户名
     * @param password 密码
     * @return 如果用户名密码正确，则返回该用户bean表示登陆 ，如果用户名密码不正确，则返回null表示不登陆
     */
    public User loginUser(String username, String password);

    /**
     * 检查用户名已经存在
     * @param username
     * @return
     */
    public boolean hasUser(String username);
}
