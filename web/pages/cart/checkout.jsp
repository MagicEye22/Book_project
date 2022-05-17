<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
	<%--静态包含抽取相同内容 base标签 css样式 jquery样式--%>
	<%@include file="/pages/common/haed.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>

<%@ include file="/pages/common/login_sucess_menu.jsp"%>
	
	<div id="main">
		<%--当出现错误时--%>
<%--		<c:if test="${empty sessionScope.orderId}">--%>
<%--			<h1>结算错误,返回重试！</h1>--%>
<%--		</c:if>--%>
			<c:if test="${not empty sessionScope.orderId}">
			<h1>你的订单已结算，订单号为${sessionScope.orderId}</h1>
			</c:if>



	</div>

<%@include file="/pages/common/footer.jsp"%>
</body>
</html>