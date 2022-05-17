<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%--静态包含抽取相同内容 base标签 css样式 jquery样式--%>
	<%@include file="/pages/common/haed.jsp"%>
	
	<script type="text/javascript">
		$(function () {
			//给删除的a标签绑定单击事件
			$("a.delete").click(function () {
				/*
				* confirm是确认提示框
				* 参数是他提示内容
				* 返回true表示点击了确认(提交表单发送请求)，返回false表示提交取消
				* */
				return confirm("你确定要删除【"+$(this).parent().parent().find("td:first").text()+"】吗？");
			});

			//跳到指定页码
			$("#pageBtu").click(function () {
				var page = $("#pn_input").val();
				// javaScript 语言中提供了一个 location 地址栏对象
				// 它有一个属性叫 href.它可以获取浏览器地址栏中的地址
				// href 属性可读，可写
				//判断输入的页码是否在数据范围内
				if (page<1||page>${requestScope.page.pageTotal}){
					//范围不正确时
					alert("请输入正确页码")
				}else {
					//正确时
					location.href="${pageScope.basePath}${requestScope.page.url}&pageNo="+page+"";
				}

			});
		})
		
		
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>

			<%--静态包含管理模块的菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>

			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<input type="hidden" name="book" value="${book}">
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<%--有bug 删除最后一页最后一条数据时那页就不存在了，就会出现跳转不了 已解决--%>
					<td><a class="delete" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}&pageTotal=${requestScope.page.pageTotal}">删除</a></td>
				</tr>


			</c:forEach>


			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>
		<%--			静态包含分页条--%>
		<%@include file="/pages/common/page_nav.jsp"%>
	</div>



	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>