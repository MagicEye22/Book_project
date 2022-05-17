package com.zpp.web;

import com.zpp.Service.BookService;
import com.zpp.Service.impl.BookServiceImpl;
import com.zpp.pojo.Book;
import com.zpp.pojo.Page;
import com.zpp.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author : zpp
 * @version : 1.0
 */
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 0);

        //获取请求的参数==封装为Book对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        //调用BookService.add()保存图书
        bookService.addBook(book);
        //小bug 输入页码跳转的value回显页码与最大页码不符合 解决一半
        //当页码能被显示个数整除且不是新一页数据时 还是会出现
        int pageTotal = bookService.pageTotal();
        if (pageTotal>pageNo){
            pageNo=pageTotal;
        }
        //跳到图书列表页面 请求重定向
       /* //当用户提交完请求，浏览器会记录下最后一次请求的全部信息。当用户按下功能键 F5，就会发起浏览器记录的最后一次请求。
        //请求转发
        request.getRequestDispatcher("/pages/manager/bookServlet?action=list").forward(request,response); 有bug*/
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    /**
     *  删除图书
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取请求的参数 id，图书编号
        int i = WebUtils.parseInt(request.getParameter("id"), 0);
        //调用对应方法删除图书
        bookService.deleteBook(i);
        //有bug 删除最后一页最后一条数据时那页就不存在了，就会出现跳转不了
        //解决方法 获取删除后的记录总页码,将删除后的记录总页数返回到jsp页面
        int total = WebUtils.parseInt(request.getParameter("pageTotal"), 1);//删除前的总页码
        int deleteTotal = bookService.pageTotal();//删除后的总页码
        //使用StringBuilder的原因，效率快，减少内存使用
        StringBuilder set = new StringBuilder(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=");

        if (deleteTotal == total) {
            //不是最后一页的最后一条数据时跳转当前删除的页码
            set.append(request.getParameter("pageNo"));
        } else {
            //是最后一页的最后一条数据时跳转到删除后的最后一页
            set.append(deleteTotal);
        }
        //重定向回图书列表管理页面
        response.sendRedirect(set.toString());

    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、获取请求的参数==封装成为 Book 对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        // 2、调用 BookService.updateBook( book );修改图书
        bookService.updateBook(book);
        // 3、重定向回图书列表管理页面
        // 地址：/工程名/manager/bookServlet?action=list
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 获取请求的参数图书编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //2 调用 bookService.queryBookById 查询图书
        Book book = bookService.queryBookById(id);
        //3 保存到图书到 Request 域中
        request.setAttribute("book", book);
        //4 请求转发到。pages/manager/book_edit.jsp 页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }


    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2，把全部图书保存到request域中
        request.setAttribute("Books", books);
        //3,请求转发到/pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

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

        //设置后台的url
        page.setUrl("manager/bookServlet?action=page");
        //保存Page对象到Request域中
        request.setAttribute("page", page);
        //请求转发到 /pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

}
