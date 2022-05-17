package com.zpp.dao.impl;

import com.zpp.dao.UserDao;
import com.zpp.pojo.User;

/**
 * @author : zpp
 * @version : 1.0
 *
 * 用户登录和注册功能需要用到的数据库的增删改查操作的具体实现
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select id,username,password,email from t_user where username= ?";

        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernamePassword(String username, String password) {
        String sql = "select id,username,password,email from t_user where username= ? and password= ?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql ="insert into t_user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
