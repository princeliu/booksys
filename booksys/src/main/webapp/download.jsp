<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
<style type="text/css">
table{
	border:0px solid #F00
	} 
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件下载</title>
</head>
<body>
	
	<table>
		<tbody>
			<c:if test="${!empty files }">
			<c:forEach items="${files }" var="file">
			<tr>
				<td>${file }</td>
				<td>
					<a href="/file/doDownload?file=${file }">下载</a>
				</td>
			</tr>
			</c:forEach>
			</c:if>
		</tbody>
	</table>
	<a href="/file/toUpload">返回</a>
</body>
</html>