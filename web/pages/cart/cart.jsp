<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含抽取相同内容 base标签 css样式 jquery样式--%>
	<%@include file="/pages/common/haed.jsp"%>
	<script type="text/javascript">
		$(function () {
			//给删除 绑定的单击事件
			$(".deleteItems").click(function () {
				return confirm("确定要删除【"+$(this).parent().parent().find("td:first").text()+"】吗？")
			});
			//给清空购物车绑定单击事件
			$("#clearItems").click(function () {
				return confirm("确定要清空购物车吗？");
			});

			//给 数量输入框 绑定内容发生改变事件
			$(".updateItems").change(function () {
				//商品名称
				var name = $(this).parent().parent().find("td:first").text();
				//商品数量
				var count = this.value;
				//商品对应的id
				var id = $(this).attr("bookId");
				if (confirm("确定要将【"+name+"】商品数量修改为:"+count+"吗？")){
					//发起请求给服务器保存修改
					location.href="${basePath}cartServlet?action=updateCount&count="+count+"&id="+id;
				}else{
					//defaultValue 属性是表单项Dom对象的属性，它表示默认的value属性值
					$(this).value=this.defaultValue;
				}

			});
		});

	</script>
</head>
<body>
		<%@ include file="/pages/common/login_sucess_menu.jsp"%>

	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
			<%-- 如果购物车为空的情况--%>
					<tr>
						<td colspan="5"><a href="index.jsp">当前购物车为空！点击去购物</a></td>

					</tr>

			</c:if>

			<c:if test="${not empty sessionScope.cart.items}">
				<%-- 如果购物车为空的情况--%>
				<c:forEach items="${sessionScope.cart.items}" var="cart">
					<tr>
						<td>${cart.value.name}</td>
						<td>
							<input bookId="${cart.value.id}" class="updateItems" type="text" value="${cart.value.count} " style="width:60px ;text-align: center"/>
						</td>
						<td>${cart.value.price}</td>
						<td>${cart.value.totalPrice}</td>
						<td><a href="cartServlet?action=deleteItem&id=${cart.value.id}" class="deleteItems">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>

		</table>
		<c:if test="${not empty sessionScope.cart.items}">
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a href="cartServlet?action=clear" id="clearItems">清空购物车</a></span>
			<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
		</div>
		</c:if>
	</div>

		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>