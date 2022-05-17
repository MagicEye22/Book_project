package com.zpp.dao;

import com.zpp.pojo.User;

/**
 * @author : zpp
 * @version : 1.0
 * 该接口制定实现类的方法规范
 */
public interface UserDao {


    /**
     * 根据用户名查询用户信息
     * @param username  用户名
     * @return  如果返回null  说明没有这个用户
     */
    public User queryUserByUsername(String username);

    /**
     * 根据 用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null  说明用户名或密码错误
     */
    public User queryUserByUsernamePassword(String username,String password);

    /**
     * 注册保存用户信息 添加操作
     * @param user
     * @return
     */
    public int saveUser(User user);


}
