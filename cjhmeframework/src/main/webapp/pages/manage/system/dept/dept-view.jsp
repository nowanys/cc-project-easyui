<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>部门查看</title>
    
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
	            <div  class="div-border01">${requestScope.deptBean.deptCode}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             部门名称：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.deptBean.deptName}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             部门简称：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.deptBean.deptSortName}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             部门级别：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.deptBean.deptLevel}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             部门描述：
	          </td>
	          <td style="width:80%">
	             <div  class="div-border02">${requestScope.deptBean.deptDescript}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             负责人：
	          </td>
	          <td style="width:80%">
	             <div  class="div-border01">${requestScope.deptBean.deptLeader}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             联系电话：
	          </td>
	          <td style="width:80%">
	             <div  class="div-border01">${requestScope.deptBean.deptTel}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             部门地址：
	          </td>
	          <td style="width:80%">
	             <div  class="div-border02">${requestScope.deptBean.deptAddr}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             图标：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01" style="float: left;width: 179px">${requestScope.deptBean.iconCls}</div>
	            <img id="viewIcon"  class="view-icon" style="margin-top: 2px;margin-left: 5px" >
	            <script type="text/javascript">
	              var iconImg = '${requestScope.deptBean.iconCls}';
	              iconImg=iconImg.substring(5,iconImg.length);
	              var iconUrl=easyUI.iconImgUrl+iconImg+'.png';
	              $('#viewIcon').attr('src',iconUrl);
	            </script>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             邮件开关：
	          </td>
	          <td style="width:80%">
	               <c:forEach var="dataDic" items="${requestScope.EMAIL_SWITCH_TYPE}">  
	                 <c:if test="${requestScope.deptBean.emailSwitch==dataDic.dicItemValue}">
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
	                 <c:if test="${requestScope.deptBean.smsSwitch==dataDic.dicItemValue}">
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
	            <div  class="div-border01">${requestScope.deptBean.createUser}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	                      修改人：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.deptBean.updateUser}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             创建时间：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.deptBean.createDate}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             修改时间：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.deptBean.updateDate}</div>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
