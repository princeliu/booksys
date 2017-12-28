<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加书本</title>
</head>
<body>
	<h1>添加书本</h1>
	<form name="userForm" action="/book/doAddBook" method="post">
		书名：<input type="text" name="name">
		价格：<input type="text" name="price">
		作者：<input type="text" name="author">
		<input type="submit" value="添加" >
	</form>
</body>
</html>