package com.zpp.test;

import com.zpp.dao.UserDao;
import com.zpp.dao.impl.UserDaoImpl;
import com.zpp.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : zpp
 * @version : 1.0
 */
public class UserDaoTest {
    //多态：向下转型：编译看左边，运行看右边
    UserDao userDao=new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        if (userDao.queryUserByUsername("root")!=null){
            System.out.println("用户名已存在");
        }else{
            System.out.println("用户名可用");
        }

    }

    @Test
    public void queryUserByUsernamePassword() {
        if (userDao.queryUserByUsernamePassword("root","root")==null){
            System.out.println("用户名或密码错误");
        }else {
            System.out.println("查询成功");
        }

    }

    //保存 insert操作
    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User
                (null,"root2","root2","root2@qq.com"))!=0?"添加成功":"添加失败");
    }
}