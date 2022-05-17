package com.zpp.discard;

import com.zpp.Service.UserService;
import com.zpp.Service.impl.UserServiceImpl;
import com.zpp.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : zpp
 * @version : 1.0
 */
public class LoginServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //1,调用login()处理登录业务
        if(userService.login(new User(null,username,password,null))==null){
            //把错误信息和回显的表单项信息，保存到request域中
            request.setAttribute("msg","用户名或密码错误");
            request.setAttribute("username",username);
            //失败 跳回登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            //成功 跳到登陆成功页面
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }

    }


}
