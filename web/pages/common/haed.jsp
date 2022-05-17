<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Z
  Date: 2022/4/19
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String scheme = request.getScheme()
            +"://"
            +request.getServerName()
            +":"
            +request.getServerPort()
            +request.getContextPath()
            +"/";

   pageContext.setAttribute("basePath",scheme);
%>

<!--base路径,永远固定相对路径的跳转-->
<base href="<%=scheme%>">

<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="jquery-3.6.0/jquery-3.6.0.js"></script>
