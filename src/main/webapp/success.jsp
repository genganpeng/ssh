<%@page import="cn.gap.beans.UserLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
<%@include file="./common/base.jsp" %>
<title>登陆成功</title>
</head>
<body>
登陆成功！<%=((UserLogin)request.getSession().getAttribute("user.session")).getUserName()%>
<br>
<a href="login/logout.action">退出</a>
</body>
</html>