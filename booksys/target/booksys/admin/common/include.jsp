<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%--
	Object obj = session.getAttribute("sysUser");
	SysUser sysUser = null;
	if (obj != null)
	{
		sysUser = (SysUser) obj;
	}else{
		%>

<script type="text/javascript">
	function goLoginPage(){
		var login = document;
		for(;;){
			login = login.parentWindow;
			if(login==null){
				login = document;
				break;
			}
		}
		login.location.href = "<%=basePath%>";
	}
	goLoginPage();
</script>
		
		<%
	}
--%>
