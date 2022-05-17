package com.zpp.Service.impl;

import com.zpp.Service.UserService;
import com.zpp.dao.UserDao;
import com.zpp.dao.impl.UserDaoImpl;
import com.zpp.pojo.User;

/**
 * @author : zpp
 * @version : 1.0
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    @Override
    public void registUser(User user) {
        //添加用户
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
    return  userDao.queryUserByUsernamePassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username)==null){
            //等于null，说明数据库中没有该值，说明可用
            return  false;
        }
        //返回true说明该用户名已存在
        return true;
    }


}
