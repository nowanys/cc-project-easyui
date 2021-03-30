<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>数据字典明细查看</title>
    
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
     <input type="hidden" id="dicItemId" value="${requestScope.dtaDicItemBean.dicItemId}" />
     <input type="hidden" id="dicTypeCode" value="${requestScope.dicTypeCode}"/>
            
     <table style="width:100%;margin: 10px" cellspacing="0" cellpadding="3">
	        <tr>
	          <td class="table-style-0" style="width:25%;">
	             编号：
	          </td>
	          <td style="width:75%">
	            <div  class="div-border01">${requestScope.dtaDicItemBean.dicItemCode}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:25%;">
	                      名称：
	          </td>
	          <td style="width:75%">
	            <div  class="div-border01">${requestScope.dtaDicItemBean.dicItemName}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:25%;">
	             值：
	          </td>
	          <td style="width:75%">
	            <div  class="div-border01">${requestScope.dtaDicItemBean.dicItemValue}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:25%;">
	             备用值：
	          </td>
	          <td style="width:75%">
	            <div  class="div-border01">${requestScope.dtaDicItemBean.spareValue}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:25%;">
	             默认值：
	          </td>
	          <td style="width:75%">
	            <div  class="div-border01">${requestScope.dtaDicItemBean.defaultValue}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:25%">
	             图标：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01" style="float: left;width: 179px">${requestScope.dtaDicItemBean.iconCls}</div>
	            <img id="viewIcon"  class="view-icon" style="margin-top: 2px;margin-left: 5px" >
	            <script type="text/javascript">
	              var iconImg = '${requestScope.dtaDicItemBean.iconCls}';
	              if(null!=iconImg && ""!=iconImg && undefined!=iconImg){
	                iconImg=iconImg.substring(5,iconImg.length);
	                var iconUrl=easyUI.iconImgUrl+iconImg+'.png';
	                $('#viewIcon').attr('src',iconUrl);
	              }
	            </script>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:25%">
	             上级：
	          </td>
	          <td style="width:75%">
	            <c:if test="${requestScope.dtaDicItemBean.dicItemParentId!=-1}">
	               <div  class="div-border01">${requestScope.dtaDicItemBean.dicItemParentName}</div>
	            </c:if>
	            <c:if test="${requestScope.dtaDicItemBean.dicItemParentId==-1}">
		           <div  class="div-border01">无</div>
		        </c:if>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:25%">
	             状态：
	          </td>
	          <td style="width:75%">
	                <c:forEach var="dataDic" items="${requestScope.STATUS_TYPE}">  
	                 <c:if test="${requestScope.dtaDicItemBean.status==dataDic.dicItemValue}">
	                   <div  class="div-border01">${dataDic.dicItemName}</div>
	                 </c:if>
	               </c:forEach>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:25%">
	             描述：
	          </td>
	          <td style="width:75%">
	            <div  class="div-border02">${requestScope.dtaDicItemBean.dicItemDesc}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             创建人：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.dtaDicItemBean.createUser}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	                      修改人：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.dtaDicItemBean.updateUser}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             创建时间：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.dtaDicItemBean.createDate}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             修改时间：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.dtaDicItemBean.updateDate}</div>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
