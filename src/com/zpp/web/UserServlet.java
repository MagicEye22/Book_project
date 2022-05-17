package com.zpp.web;

import com.google.gson.Gson;
import com.zpp.Service.UserService;
import com.zpp.Service.impl.UserServiceImpl;
import com.zpp.pojo.User;
import com.zpp.utils.WebUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author : zpp
 * @version : 1.0
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();


    /**
     * 判断注册的用户名是否可用
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数 username
        String username = request.getParameter("username");
        //调用userService.existUsername();
        boolean existUsername=userService.existsUsername(username);
        //把返回的结果封装为一个map对象
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("existUsername",existUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        response.getWriter().write(json);
    }
    /**
     * 处理登录的方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String username = request.getParameter("username");
        //判断管理员和用户
        if ("root".equals(username)){
            request.getSession().setAttribute("admin","root");
        }
        String password = request.getParameter("password");
        //1,调用login()处理登录业务
        User user = userService.login(new User(null, username, password, null));
        if (user == null) {
            //把错误信息和回显的表单项信息，保存到request域中
            request.setAttribute("msg", "用户名或密码错误");
            request.setAttribute("username", username);
            //失败 跳回登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else {
            //保存用户名的信息到session域中 页面回显用
            //request.getSession().setAttribute("user",username);
            request.getSession().setAttribute("user", user);
            //成功 跳到登陆成功页面
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);

        }
    }

    /**
     * 处理注册的方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取生成的session中的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //1，获取请求的参数
        String code = request.getParameter("code");//用户输入的验证码

        //将所有的请求参数的值注入到user对象当中
        User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());
        //2,检查验证码是否正确
        //equalsIgnoreCase() 忽略大小写
        if (token.equalsIgnoreCase(code) && token != null) {
            //正确
            //3，检查 用户名名是否可用
            if (userService.existsUsername(user.getUsername())) {
                //把回显信息放到request域中
                request.setAttribute("msg", "用户名已存在");
                request.setAttribute("name", user.getUsername());
                request.setAttribute("email", user.getEmail());
                //不可用 跳回注册页面
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else {
                //可用,调用service保存到数据库
                userService.registUser(user);
                //最后跳转到成功的页面
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
            }

        } else {
            //把回显信息放到request域中
            request.setAttribute("msg", "验证码错误");
            request.setAttribute("name", user.getUsername());
            request.setAttribute("email", user.getEmail());
            //不正确 跳转到注册页面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取隐藏域的value
        String action = request.getParameter("action");
        //判断是登录还是注册

        try {
            //获取action业务鉴别字符串方法名，获取响应的业务  方法反射对象
            Method declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //调用方法
            declaredMethod.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 注销用户
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,销毁session域中的用户登录的信息（或者雄安会session）
        request.getSession().invalidate();
        //2，重定向到首页
        response.sendRedirect(request.getContextPath());

    }


}
