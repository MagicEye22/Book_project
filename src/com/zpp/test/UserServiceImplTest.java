package com.zpp.test;

import com.zpp.Service.UserService;
import com.zpp.Service.impl.UserServiceImpl;
import com.zpp.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : zpp
 * @version : 1.0
 */
public class UserServiceImplTest {

    UserService userService=new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"zpp12","112211","zpp222@qq.com"));
        userService.registUser(new User(null,"zpp131t3","11211","zpp223@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"root","root",null)));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("root")){
            System.out.println("用户已存在");
        }else{
            System.out.println("用户名可用");
        }
    }
}