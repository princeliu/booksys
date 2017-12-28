<%@ page language="java"  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
${account } Welcome! <shiro:principal/>
	<br><br>
	
	<shiro:hasRole name="user">
		<a href="${path}/jsp/user.jsp">User Page</a>
	</shiro:hasRole>
	
	<br><br>
	
	<shiro:hasAnyRoles name="admin">
		<a href="${path}/jsp/admin.jsp">Admin Page</a>
	</shiro:hasAnyRoles>
	
	<br><br>
	<a href="${path}/user/info/logout">Logout</a>
</body>
</html>