<%--
  Created by IntelliJ IDEA.
  User: Z
  Date: 2022/5/3
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单详情</title>
    <%--静态包含抽取相同内容 base标签 css样式 jquery样式--%>
    <%@include file="/pages/common/haed.jsp" %>
</head>
<body>
<div id="main">

    <c:if test="${empty sessionScope.admin}">
        <h1><a href="http://localhost:8080/book/pages/order/order.jsp">返回</a></h1>
    </c:if>

    <c:if test="${not empty sessionScope.admin}">
        <h1><a href="http://localhost:8080/book/pages/manager/order_manager.jsp">返回</a></h1>
    </c:if>
    <table>
        <tr>
            <td>书名</td>
            <td>数量</td>
            <td>价格</td>
            <td>总价</td>
        </tr>
            <c:forEach items="${sessionScope.orderItem}" var="orderItems">
                <tr>
                    <td>${orderItems.name}</td>
                    <td>${orderItems.count}</td>
                    <td>${orderItems.price}</td>
                    <td>${orderItems.totalPrice}</td>
                </tr>
            </c:forEach>


    </table>
</div>

</body>
</html>
