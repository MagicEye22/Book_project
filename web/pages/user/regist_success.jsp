<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--静态包含抽取相同内容 base标签 css样式 jquery样式--%>
	<%@include file="/pages/common/haed.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>

<%--静态包含 登录成功页面菜单模块--%>
<%@ include file="/pages/common/login_sucess_menu.jsp"%>
		
		<div id="main">
		
			<h1>注册成功! <a href="index.jsp">转到主页</a></h1>
	
		</div>

<%@include file="/pages/common/footer.jsp"%>
</body>
</html>