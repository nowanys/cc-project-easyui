<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>访问地址添加</title>
    
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
	     var fields = ['accessUrl','accessUrlName'];
	     return validForm(fields);
	  }
	  
	  function serializeForm(){
	    var fields = ['accessUrlId','accessUrl','accessUrlName','status'];
	    return jsutil.serializeFrom(fields);
	  }
	  
	</script>
  </head>
  
  <body class="body-scroll">
     <input type="hidden" id="accessUrlId" value="${requestScope.accessUrlBean.accessUrlId}">
     <table style="width:100%;margin: 10px" cellspacing="0" cellpadding="3">
	        <tr>
	        <td class="table-style-0" style="width:20%;">
	             地址名称：
	          </td>
	          <td style="width:80%">
	            <input type="text" id="accessUrlName"  value="${requestScope.accessUrlBean.accessUrlName}"  class="easyui-validatebox input-border01"  required="required" validType="checkTrimLength[50,25]">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             访问地址：
	          </td>
	          <td style="width:80%">
	            <textarea id="accessUrl" class="easyui-validatebox textarea-border01" style="height:50px" required="required" validType="length[1,200]">${requestScope.accessUrlBean.accessUrl}</textarea>
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             状态：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="status" value="${requestScope.accessUrlBean.status}">
			    <select class="easyui-combobox select-border02" id="statusSelect" data-options="editable:false,
			    onSelect: function(record){
			        $('#status').val(record.value);
			    },onLoadSuccess:function(){
			        $('#status').val($(this).combobox('getValue'));
			    }">
	                <c:forEach var="dataDic" items="${requestScope.STATUS_TYPE}">
		                  <c:if test="${requestScope.accessUrlBean.status==dataDic.dicItemValue}">
		                     <option value="${dataDic.dicItemValue}" selected="selected">${dataDic.dicItemName}</option>
		                  </c:if>
		                  <c:if test="${requestScope.accessUrlBean.status!=dataDic.dicItemValue}">
		                     <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
		                  </c:if>
                    </c:forEach>
	            </select>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
