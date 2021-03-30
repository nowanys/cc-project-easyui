<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>错误页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

    <link rel="stylesheet" type="text/css" charset="utf-8" href="pages/errorPage/css/error.css">
	
  </head>
  
  <body class="error-body">
     <div class="error-div">
        <img src="pages/errorPage/images/error.png">
         <span class="error-tip">友情提示：</span>
        <div class="error-content">您未分配角色，请点击浏览器返回按钮返回到上一页面！</div>
     </div>
  </body>
</html>
