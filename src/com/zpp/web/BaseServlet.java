package com.zpp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author : zpp
 * @version : 1.0
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
//        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
        //获取隐藏域的value
        String action = req.getParameter("action");
        //判断是登录还是注册
        try {
            //获取action业务鉴别字符串方法名，获取响应的业务  方法反射对象
            Method declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //调用方法
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new  RuntimeException(e);//将异常抛出，交给Filter过滤器处理事务问题
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
