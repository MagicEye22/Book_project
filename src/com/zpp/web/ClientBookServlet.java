package com.zpp.web;

import com.zpp.Service.BookService;
import com.zpp.Service.impl.BookServiceImpl;
import com.zpp.pojo.Book;
import com.zpp.pojo.Page;
import com.zpp.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author : zpp
 * @version : 1.0
 */
public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数 pageNo 和pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //调用BookService.page(pageNo,pageSize);Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);

        //设置前台的url
        page.setUrl("client/bookServlet?action=page");
        //保存Page对象到Request域中
        request.setAttribute("page", page);
        //请求转发到 /pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数 pageNo 和pageSize，minPrice,maxPrice
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"), 0);
        int max = WebUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);
        //调用BookService.pageByPrice(pageNo,pageSize);Page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        //设置前台的url 如果不将最大和最小值传回去，点击页码或者其它操作数据的时候，就会按默认值去查询 （bug）

//       解决方法1； 该方法有点小bug 当提交空值时，点下一页会提交默认值的min和max回显到页面中
//         page.setUrl("client/bookServlet?action=pageByPrice&min="+min+"&max="+max);


        //方法二：该方法就不会回显到页面中
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        //如果有最大值，追加到分页条地址参数中
        if (request.getParameter("min") != null) {
            sb.append("&min=").append(request.getParameter("min"));
        }


        if (request.getParameter("max") != null) {
            sb.append("&max=").append(request.getParameter("max"));
        }
        page.setUrl(sb.toString());
        //保存Page对象到Request域中
        request.setAttribute("page", page);
        //请求转发到 /pages/client/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }


}
