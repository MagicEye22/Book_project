<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%--静态包含抽取相同内容 base标签 css样式 jquery样式--%>
    <%@include file="/pages/common/haed.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<%@ include file="/pages/common/login_sucess_menu.jsp" %>

<div id="main">

    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
        </tr>
        <%--用户订单页面--%>
        <%--该用户没有订单时--%>
            <c:if test="${empty sessionScope.orders}">
                <tr>
                <td colspan="5"><a href="index.jsp" style="color:red ">未购买任何物品,点击此处去加入购物车</a></td>
                </tr>
            </c:if>

            <%--该用户有订单时--%>

            <c:if test="${not empty sessionScope.orders}">
                <c:forEach items="${sessionScope.orders}" var="order">
                    <tr>
                    <%--格式化日期 更好看--%>
                    <td><fmt:formatDate value="${order.createTime}" type="both"/></td>
                    <td>${order.price}元</td>
                    <%--校验订单状态 以中文显示订单状态--%>
                    <c:choose>
                        <c:when test="${order.status==0}">
                            <td>未发货</td>
                        </c:when>
                        <c:when test="${order.status==1}">
                            <td><a href="orderServlet?action=receiverOrder&orderId=${order.orderId}&userId=${order.userId}">签收</a></td>
                        </c:when>
                        <c:when test="${order.status==2}">
                            <td>已签收</td>
                        </c:when>
                    </c:choose>

                    <td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
                    </tr>
                </c:forEach>
            </c:if>


    </table>


</div>

<%@include file="/pages/common/footer.jsp" %>
</body>
</html>