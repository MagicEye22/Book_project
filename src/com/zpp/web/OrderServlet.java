package com.zpp.web;

import com.zpp.Service.OrderService;
import com.zpp.Service.impl.OrderServiceImpl;
import com.zpp.pojo.*;
import com.zpp.utils.JDBCUtils;
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
public class OrderServlet extends BaseServlet {
    private OrderService orderService=new OrderServiceImpl();
    /**
     * 生成订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取userid
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        Integer userId = loginUser.getId();
        //调用 orderService.createOrder(),生成订单
        String orderId = orderService.createOrder(cart, userId);

//        req.setAttribute("orderId",orderId);
       /* //请求转发  bug:请求多次提交
        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);*/

       req.getSession().setAttribute("orderId",orderId);//重定向不能在request域中存储数据
       //请求重定向
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    /**
     * 查看订单（用户）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数  userId
        int userId = WebUtils.parseInt(req.getParameter("userId"), 0);
        //调用orderService.showMyOrders()
        List<Order> orders =orderService.showMyOrders(userId);
        //将对应userId查到的数据库订单记录保存到session域中
        req.getSession().setAttribute("orders",orders);
//        //请求转发到 /pages/order/order.jsp
//        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
        //请求重定向
        resp.sendRedirect(req.getContextPath()+"/pages/order/order.jsp");
    }

    /**
     * 查询所有订单(管理员)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //调用orderService.showAllOrder()
       List<Order> orders =orderService.showAllOrders();
       //将orders存入session域中
          req.getSession().setAttribute("orders",orders);
        //重定向回 /pages/manager/order_manager.jsp
       resp.sendRedirect(req.getContextPath()+"/pages/manager/order_manager.jsp");
            //请求转发 存到request域
//        req.setAttribute("orders",orders);
//       req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

    /**
     * 发货功能（管理员） status改为 1  order_manager.jsp
     * bug : 修改后页面的status不会立即刷新为对应状态 已解决
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        String orderId = req.getParameter("orderId");
        //调用orderService.sendOrder(orderId);
        orderService.sendOrder(orderId);
        //重定向回 /pages/manager/order_manager.jsp
        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showAllOrders");
    }

    /**
     * 签收功能（用户） status改为 2  order.jsp
     * bug : 修改后页面的status不会立即刷新为对应状态 已解决
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void receiverOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        String orderId = req.getParameter("orderId");
        int userId = WebUtils.parseInt(req.getParameter("userId"), 0);
        //调用orderService.receiverOrder(orderId);
        orderService.receiverOrder(orderId);
        //重定向回 /pages/order/order.jsp
        resp.sendRedirect(req.getContextPath()+"/orderServlet?action=showMyOrders&userId="+userId);
    }

    /**
     * 查看订单详情（用户&管理员）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 orderId
        String orderId = req.getParameter("orderId");
        //调用orderService.showOrderDetail()
        List<OrderItem> orderItems =orderService.showOrderDetail(orderId);
        req.getSession().setAttribute("orderItem",orderItems);
        //转发到显示页面
        resp.sendRedirect(req.getContextPath()+"/pages/order/orderDatails.jsp");
    }

}
