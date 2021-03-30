<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>系统公告查看</title>
    
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
	          <td class="table-style-0" style="width:10%;">
	             公告标题：
	          </td>
	          <td style="width:90%">
	            <div  class="div-border01"  style="width:550px">${requestScope.noticeBean.noticeTitle}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:10%;">
	              公告内容：
	          </td>
	          <td style="width:90%">
	           <div  class="div-border01"  style="width:550px;height:290px;overflow-y:scroll ">${requestScope.noticeBean.noticeContent}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:10%">
	             截止时间：
	          </td>
	          <td style="width:90%">
	                <div  class="div-border01">${requestScope.noticeBean.cutoffDate}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:10%">
	             状态：
	          </td>
	          <td style="width:90%">
	               <c:forEach var="dataDic" items="${requestScope.S_TYPE}">  
	                 <c:if test="${requestScope.noticeBean.status==dataDic.dicItemValue}">
	                   <div  class="div-border01">${dataDic.dicItemName}</div>
	                 </c:if>
	               </c:forEach>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:10%">
	             创建人：
	          </td>
	          <td style="width:90%">
	            <div  class="div-border01">${requestScope.noticeBean.createUser}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:10%">
	                      修改人：
	          </td>
	          <td style="width:90%">
	            <div  class="div-border01">${requestScope.noticeBean.updateUser}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:10%">
	             创建时间：
	          </td>
	          <td style="width:90%">
	            <div  class="div-border01">${requestScope.noticeBean.createDate}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:10%">
	             修改时间：
	          </td>
	          <td style="width:90%">
	            <div  class="div-border01">${requestScope.noticeBean.updateDate}</div>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
