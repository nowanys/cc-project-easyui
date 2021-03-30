<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>
  
    <title>用户密码编辑</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
	<script type="text/javascript">
	  
	  function validSubmit(){
	     var fields = ['password','confPassword'];
	     return validForm(fields);
	  }
	  
	  function serializeForm(){
	    var fields = ['userId','password','confPassword'];
	    return jsutil.serializeFrom(fields);
	  }
	  
	</script>
  </head>
  
  <body class="body-scroll">
  
     <input type="hidden" id="userId" value="${requestScope.userId}">
     
     <table style="width:100%;margin: 10px" cellspacing="0" cellpadding="3">
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             新密码：
	          </td>
	          <td style="width:80%">
	            <input type="password" id="password"   class="easyui-validatebox input-border01"  required="required" validType="length[5,15]">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	              确认密码：
	          </td>
	          <td style="width:80%">
	            <input type="password"  id="confPassword"  class="easyui-validatebox input-border01"  required="required"  validType="confirmPassword['#password']">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	   </table>
	   
  </body>
</html>
