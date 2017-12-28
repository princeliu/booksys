<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@include file="/admin/common/include.jsp"%>
    <base href="<%=basePath%>">
    
    <title>admin登陆</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=basePath%>/assets/css/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
	</script>
	<script src="<%=basePath%>/assets/script/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="<%=basePath%>/assets/script/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>/admin/assets/script/sysuer.js" type="text/javascript"></script>
	
<style>
*{
	padding:0px;
	margin:0px;
	}

body{
	font-family:Arial, Helvetica, sans-serif;
	background:url(<%=basePath%>/admin/assets/img/sys_images/grass.jpg) repeat;
	font-size:13px;
  	background-size:cover; 
	}
img{
	border:0;
	}
.lg{
	width:468px;
	height:468px; 
	margin:100px auto; 
	background:url(<%=basePath%>/admin/assets/img/sys_images/login_bg.png) no-repeat;
	 }
.lg_top{ 
	height:200px; 
	width:468px;
	}
.lg_main{
	width:400px; 
	height:180px; 
	margin:0 25px;
	}
.lg_m_1{
	width:290px;
	height:100px;
	padding:60px 55px 20px 55px;
}
.ur{
	height:37px;
	border:0;
	color:#666;
 	width:242px; 
	margin:4px 28px;
	background:url(<%=basePath%>/admin/assets/img/sys_images/user.png) no-repeat;
	padding-left:10px;
	font-size:16pt;
	font-family:Arial, Helvetica, sans-serif;
}
.pw{
	height:37px;
	border:0;
	color:#666;
	width:242px;
	margin:4px 28px;
	background:url(<%=basePath%>/admin/assets/img/sys_images/password.png) no-repeat;
	padding-left:10px;
	font-size:16pt;
	font-family:Arial, Helvetica, sans-serif;
}
.bn{
	width:330px; 
	height:72px; 
	background:url(<%=basePath%>/admin/assets/img/sys_images/enter.png) no-repeat; 
	border:none; 
	outline: 0;
	display:block; 
	font-size:18px; 
	color:#FFF; 
	font-family:Arial, Helvetica, sans-serif; 
	font-weight:bolder;
	}
.lg_foot{
	height:80px;
	width:330px;
	padding: 6px 68px 0 68px;
}
</style>
</head>

<body class="b">
<table align="center">
<div class="lg">
<!-- onsubmit="return submits(this)" -->
<form name="login" id="login" action="/user/info/login" method="POST">
    <div class="lg_top"></div>
    <div class="lg_main">
        <div class="lg_m_1">
        
        <input name="account" value="account" class="ur" />
        <input name="password" type="password" value="password" class="pw" />
        
        </div>
    </div>
    <div class="lg_foot">
    <input type="submit" value="Login In" class="bn" /></div>
</form>
</div>
</table>
</body>
</html>
