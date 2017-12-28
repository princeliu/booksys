<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
 table{ 
 	border:0px solid #F00 
 	}  
 td{
    text-align:center
 }
</style>
<script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有书本</title>
<script type="text/javascript">
	function del(id){
		$.get("/book/delBook?id=" + id,function(data){
			if("success" == data.result){
				alert("删除成功!");
				window.location.reload(); 
			}else{
				alert("删除失败!")
			}
		});
	}

</script>
</head>
<body>
	
	<table>
		<tbody>
			<tr>
				<th>书名</th>
				<th>价格</th>
				<th>作者</th>
				<th>编辑</th>
			</tr>
			<c:if test="${!empty book }">
			<c:forEach items="${book }" var="b">
			<tr>
				<td>${b.name }</td>
				<td>${b.price }</td>
				<td>${b.author }</td>
				<td>
					<a href="/book/getBook?id=${b.id }">编辑</a>
					<a href="javascript:del('${b.id }')">删除</a>
				</td>
			</tr>
			</c:forEach>
			</c:if>
		</tbody>
	</table>
	<a href="/book/toAddBook">添加书本</a>
</body>
</html>