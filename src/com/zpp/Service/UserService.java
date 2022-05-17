package com.zpp.Service;

import com.zpp.pojo.User;

/**
 * @author : zpp
 * @version : 1.0
 * 业务层
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return 如果返回null说明登录失败，返回有值说明登录成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username 用户的账号名
     * @return 返回true 表示用户名已存在  false表示用户名可用
     */
    public boolean existsUsername(String username);
}
