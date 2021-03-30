<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>按钮编辑</title>
    
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
	             按钮名称：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.buttonBean.buttonName}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	              按钮分类：
	          </td>
	          <td style="width:80%">
                    <c:forEach var="dataDic" items="${requestScope.BUTTON_CATEGORY_TYPE}">  
	                 <c:if test="${requestScope.buttonBean.buttonType==dataDic.dicItemValue}">
	                   <div  class="div-border01">${dataDic.dicItemName}</div>
	                 </c:if>
	                </c:forEach>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	                      函数名：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.buttonBean.functionName}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             菜单名称：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.buttonBean.menuName}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             业务标识：
	          </td>
	          <td style="width:80%">
		            <c:forEach var="busniessMark" items="${requestScope.busniessMarks}">  
		               <c:if test="${requestScope.buttonBean.busniessMark==busniessMark.busniessMarkVal}">
		                   <div  class="div-border01">${busniessMark.busniessMarkDesc}</div>
		               </c:if>
                    </c:forEach>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             布局：
	          </td>
	          <td style="width:80%">
                    <c:forEach var="dataDic" items="${requestScope.BUTTON_LAYOUT_MARK_TYPE}">  
	                 <c:if test="${requestScope.buttonBean.layoutMark==dataDic.dicItemValue}">
	                   <div  class="div-border01">${dataDic.dicItemName}</div>
	                 </c:if>
	                </c:forEach>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             图标：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01" style="float: left;width: 179px">${requestScope.buttonBean.iconCls}</div>
	            <img id="viewIcon"  class="view-icon" style="margin-top: 2px;margin-left: 5px" >
	            <script type="text/javascript">
	              var iconImg = '${requestScope.buttonBean.iconCls}';
	              iconImg=iconImg.substring(5,iconImg.length);
	              var iconUrl=easyUI.iconImgUrl+iconImg+'.png';
	              $('#viewIcon').attr('src',iconUrl);
	            </script>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             排序：
	          </td>
	          <td style="width:80%">
	               <div  class="div-border01">${requestScope.buttonBean.sortNum}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             状态：
	          </td>
	          <td style="width:80%">
	                <c:if test="${requestScope.buttonBean.status=='1'}">
	                  <div  class="div-border01">正常</div>
	                </c:if>
	              
	                <c:if test="${requestScope.buttonBean.status=='2'}">
	                   <div  class="div-border01">锁定</div>
	                </c:if>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	                       描述：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border02">${requestScope.buttonBean.buttonDescribe}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             创建人：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.buttonBean.createUser}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	                      修改人：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.buttonBean.updateUser}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             创建时间：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.buttonBean.createDate}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             修改时间：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.buttonBean.updateDate}</div>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
