<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑书本</title>
<script type="text/javascript">

</script>
</head>
<body>
	<h1>编辑书本</h1>
	<form name="userForm" action="/book/updateBook" method="post">
	    <input type="hidden" name="id" value="${book.id }">
		书名：<input type="text" name="name" value="${book.name }">
		价格：<input type="text" name="price" value="${book.price }">
		作者：<input type="text" name="author" value="${book.author }">
		<input type="submit" value="编辑" >
	</form>
</body>
</html>