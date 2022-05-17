package com.zpp.Filter;

import com.zpp.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;
import java.rmi.RemoteException;

/**
 * @author : zpp
 * @version : 1.0
 */
public class TransactionFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request,response);
            JDBCUtils.commitAndClose();//提交事务

        } catch (Exception e) {
            JDBCUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);//将异常抛给Tomcat管理层展示友好界面
        }


    }

    @Override
    public void destroy() {

    }
}
