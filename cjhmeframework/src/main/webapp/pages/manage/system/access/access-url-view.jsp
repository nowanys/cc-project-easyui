<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>访问地址查看</title>
    
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
	             地址名称：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.accessUrlBean.accessUrlName}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             访问地址：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border02" style="height:50px">${requestScope.accessUrlBean.accessUrl}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             状态：
	          </td>
	          <td style="width:80%">
	               <c:forEach var="dataDic" items="${requestScope.STATUS_TYPE}">  
	                 <c:if test="${requestScope.accessUrlBean.status==dataDic.dicItemValue}">
	                   <div  class="div-border01">${dataDic.dicItemName}</div>
	                 </c:if>
	               </c:forEach>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             创建人：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.accessUrlBean.createUser}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	                      修改人：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.accessUrlBean.updateUser}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             创建时间：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.accessUrlBean.createDate}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             修改时间：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.accessUrlBean.updateDate}</div>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
