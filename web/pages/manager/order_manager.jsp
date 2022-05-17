<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%--静态包含抽取相同内容 base标签 css样式 jquery样式--%>
    <%@include file="/pages/common/haed.jsp" %>

    <script type="text/javascript">
        $(function () {
            $("#sendOrder").click(function () {

            })
        })

    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">订单管理系统</span>
    <%@include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>日期</td>
            <td>金额</td>
            <td>详情</td>
            <td>发货</td>

        </tr>
        <%--管理员订单页面--%>
		<%--<c:if test="${empty sessionScope.orders}">
			<tr>
				<td colspan="5">未有用户购买</td>
			</tr>
		</c:if>--%>


		<c:if test="${not empty sessionScope.orders}">
        <c:forEach items="${sessionScope.orders}" var="orders">
            <tr>
                <td><fmt:formatDate value="${orders.createTime}" type="both"/></td>
                <td>${orders.price}元</td>
                <td><a href="orderServlet?action=showOrderDetail&orderId=${orders.orderId}">查看详情</a></td>
                    <%--校验订单状态--%>

                <c:choose>
                    <c:when test="${orders.status==0}">
                		<td><a id="sendOrder" href="orderServlet?action=sendOrder&orderId=${orders.orderId}">立即发货</a></td>
               		 </c:when>

                		<c:when test="${orders.status==1}">
                    		<td>已发货</td>
                		</c:when>

                		<c:when test="${orders.status==2}">
                    		<td>已签收</td>
                		</c:when>
                </c:choose>
            </tr>

        </c:forEach>
		</c:if>

    </table>
</div>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>
