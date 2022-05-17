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
public class ResgisServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
        //1，获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        //2,检查验证码是否正确  ===先写死 验证码为abcde
        //equalsIgnoreCase() 忽略大小写
        if ("abcde".equalsIgnoreCase(code)) {
            //正确
            //3，检查 用户名名是否可用
            if (userService.existsUsername(username)) {
                //把回显信息放到request域中
                request.setAttribute("msg","用户名已存在");
                request.setAttribute("name",username);
                request.setAttribute("email",email);
                //不可用 跳回注册页面

                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else {
                //可用,调用service保存到数据库
                userService.registUser(new User(null, username, password, email));
                //最后跳转到成功的页面
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }

        } else {
            //把回显信息放到request域中
            request.setAttribute("msg","验证码错误");
            request.setAttribute("name",username);
            request.setAttribute("email",email);
            //不正确 跳转到注册页面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }

    }


}
