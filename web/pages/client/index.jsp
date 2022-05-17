<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%--静态包含抽取相同内容 base标签 css样式 jquery样式--%>
    <%@include file="/pages/common/haed.jsp" %>
    <SCRIPT type="text/javascript">
        $(function () {
            //跳到指定页码
            $("#pageBtu").click(function () {
                var page = $("#pn_input").val();
                // javaScript 语言中提供了一个 location 地址栏对象
                // 它有一个属性叫 href.它可以获取浏览器地址栏中的地址
                // href 属性可读，可写
                //判断输入的页码是否在数据范围内
                if (page<1||page>${requestScope.page.pageTotal}){
                    //范围不正确时
                    alert("请输入正确页码");
                }else {
                    //正确时
                    location.href="${pageScope.basePath}${requestScope.page.url}&pageNo="+page+"";
                }

            });



            //加入购物车按钮绑定单击事件
            $("button.addToCart").click(function () {
                //先判断用户是否登录
                if(${empty sessionScope.user}){
                    location.href="http://localhost:8080/book/pages/user/login.jsp";
                    return;
                }

                var bookId = $(this).attr("bookId");
                //basePath ==> http://localhost:8080/book/
                <%--location.href = "${basePath}cartServlet?action=addItem&id=" + bookId;--%>

                //发ajax请求添加商品到购物车
                $.getJSON("${basePath}cartServlet","action=ajaxAddItem&id="+bookId+"&user=${requestScope.user}",function (data) {

                      $("#cartTotalCount").text("您的购物车中有"+data.totalCount+"件商品");
                      $("#cartLastName").text(data.lastName);


                });
                //解决第一次加入时,使用的是购物车为空的标签显示的bug
                //如果session域的items为空时，就刷新一次页面
                if (${empty sessionScope.cart.items}){
//                     定义和用法
//                     replace() 方法可用一个新文档取代当前文档。
//                     语法
//                     location.replace(newURL)
                    location.replace("${basePath}");
                 }
            });
        });

    </SCRIPT>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <div>
        <%--如果用户还没有登录--%>
        <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <%--如果已经登录 则显示用户信息--%>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
            <%--将登录的用户id发给servlet程序 用户才能有购物车--%>
            <c:if test="${empty sessionScope.admin}" >
            <a href="orderServlet?action=showMyOrders&userId=${sessionScope.user.id}">我的订单</a>
            </c:if>
            <a href="userServlet?action=logOut">注销</a>&nbsp;&nbsp;
        </c:if>

            <%--用户才能有购物车--%>
         <c:if test="${empty sessionScope.admin}" >
        <a href="pages/cart/cart.jsp">购物车</a>
         </c:if>
            <%--管理员才有后台管理--%>
            <c:if test="${not empty sessionScope.admin}">
        <a href="pages/manager/manager.jsp">后台管理</a>
            </c:if>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice"/>
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>
        <div style="text-align: center">
            <c:if test="${empty sessionScope.cart.items}">
                <%--购物车为空--%>
                <span id="cartTotalCount"></span>
                <div id="cartLastName">当前购物车为空</div>
            </c:if>

            <c:if test="${not empty sessionScope.cart.items}">
                <%--购物车不为空--%>
                <span id="cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
                <div>
                    您刚刚将<span style="color: red" id="cartLastName">${sessionScope.lastName}</span>加 入到了购物车中
                </div>
            </c:if>

        </div>
        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button bookId="${book.id}" class="addToCart">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
    <%--静态包含分页条--%>
    <%@include file="/pages/common/page_nav.jsp" %>
</div>
<%--静态包含 页脚部分 footer--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>