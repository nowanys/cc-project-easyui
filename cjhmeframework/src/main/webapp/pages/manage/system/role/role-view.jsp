<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>角色查看</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
  </head>
  
  <body class="body-scroll">
  
     
     <table style="width:100%;margin: 10px" cellspacing="0" cellpadding="3">
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             编号：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.roleBean.roleCode}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	              角色名称：
	          </td>
	          <td style="width:80%">
	           <div  class="div-border01">${requestScope.roleBean.roleName}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             超级管理员：
	          </td>
	          <td style="width:80%">
	                <c:forEach var="dataDic" items="${requestScope.IS_SUPER_TYPE}">  
	                 <c:if test="${requestScope.roleBean.isSuper==dataDic.dicItemValue}">
	                   <div  class="div-border01">${dataDic.dicItemName}</div>
	                 </c:if>
	                </c:forEach>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             邮件开关：
	          </td>
	          <td style="width:80%">
	               <c:forEach var="dataDic" items="${requestScope.EMAIL_SWITCH_TYPE}">  
	                 <c:if test="${requestScope.roleBean.emailSwitch==dataDic.dicItemValue}">
	                   <div  class="div-border01">${dataDic.dicItemName}</div>
	                 </c:if>
	               </c:forEach>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             短信开关：
	          </td>
	          <td style="width:80%">
	               <c:forEach var="dataDic" items="${requestScope.SMS_SWITCH_TYPE}">  
	                 <c:if test="${requestScope.roleBean.smsSwitch==dataDic.dicItemValue}">
	                   <div  class="div-border01">${dataDic.dicItemName}</div>
	                 </c:if>
	               </c:forEach>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             状态：
	          </td>
	          <td style="width:80%">
	               <c:forEach var="dataDic" items="${requestScope.STATUS_TYPE}">  
	                 <c:if test="${requestScope.roleBean.status==dataDic.dicItemValue}">
	                   <div  class="div-border01">${dataDic.dicItemName}</div>
	                 </c:if>
	               </c:forEach>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	                     描述：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border02">${requestScope.roleBean.roleDescribe}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             创建人：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.roleBean.createUser}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	                      修改人：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.roleBean.updateUser}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             创建时间：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.roleBean.createDate}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             修改时间：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.roleBean.updateDate}</div>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
