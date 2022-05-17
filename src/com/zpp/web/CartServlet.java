package com.zpp.web;

import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zpp.Service.BookService;
import com.zpp.Service.impl.BookServiceImpl;
import com.zpp.pojo.Book;
import com.zpp.pojo.Cart;
import com.zpp.pojo.CartItem;
import com.zpp.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zpp
 * @version : 1.0
 */
@SuppressWarnings({"all"})
public class CartServlet extends BaseServlet {
    BookService bookService=new BookServiceImpl();

    /**
     * 加入购物车 version：2.0 ajax   bug :没登录时也可以添加
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService.queryBookById(id)得到图书的信息
        Book book = bookService.queryBookById(id);
        //把图书信息转化为CartItem商品项
        CartItem item = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem()添加商品
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(item);
        //最后一个添加的商品名称 添加到session域中
        req.getSession().setAttribute("lastName",item.getName());
        //返回购物车总的商品数量，和最应该添加的商品名称
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",item.getName());
        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJsonString);
    }
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService.queryBookById(id)得到图书的信息
        Book book = bookService.queryBookById(id);
        //把图书信息转化为CartItem商品项
        CartItem item = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem()添加商品
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(item);
        //最后一个添加的商品名称 添加到session域中
        req.getSession().setAttribute("lastName",item.getName());
//       System.out.println(cart);
        //重定向返回当前添加的商品列表
        //getHeader("Referer")//获取请求头地址  Referer：可以把请求发起时，浏览器地址栏中的地址发送给服务器
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //从session域中获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart!=null){
            //删除对应id购物车商品项
            cart.deleteItem(id);
            //重定向回购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            //清空
            cart.clear();
            //重定向回原来的购物车页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的 商品编号，商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            //修改商品数量
            cart.updateCount(id,count);
            //重定向回购物车页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
