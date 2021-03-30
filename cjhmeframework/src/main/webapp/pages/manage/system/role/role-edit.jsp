<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>角色编辑</title>
    
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
	     var fields = ['roleCode','roleName','roleDescribe'];
	     return validForm(fields);
	  }
	  
	  function serializeForm(){
	    var fields = ['roleId','roleCode','roleName','isSuper','status','roleDescribe','smsSwitch','emailSwitch'];
	    return jsutil.serializeFrom(fields);
	  }
	  
	</script>
  </head>
  
  <body class="body-scroll">
  
     <input type="hidden" id="roleId" value="${requestScope.roleBean.roleId}">
     
     <table style="width:100%;margin: 10px" cellspacing="0" cellpadding="3">
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             编号：
	          </td>
	          <td style="width:80%">
	            <input type="text" id="roleCode" value="${requestScope.roleBean.roleCode}" class="easyui-validatebox input-border01"  required="required" validType="checkTrimLength[20,10]">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	              角色名称：
	          </td>
	          <td style="width:80%">
	            <input type="text"  id="roleName" value="${requestScope.roleBean.roleName}" class="easyui-validatebox input-border01"  required="required"  validType="checkTrimLength[20,10]">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             超级管理员：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="isSuper" value="${requestScope.roleBean.isSuper}">
			    <select class="easyui-combobox select-border02" id="isSuperSelect" data-options="editable:false,
			    onSelect: function(record){
			      $('#isSuper').val(record.value);
			    },onLoadSuccess:function(){
			      $('#isSuper').val($(this).combobox('getValue'));
			    }">
	                <c:forEach var="dataDic" items="${requestScope.IS_SUPER_TYPE}">  
	                 <c:if test="${requestScope.roleBean.isSuper==dataDic.dicItemValue}">
	                   <option value="${dataDic.dicItemValue}" selected="selected">${dataDic.dicItemName}</option>
	                 </c:if>
	                 <c:if test="${requestScope.roleBean.isSuper!=dataDic.dicItemValue}">
	                   <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	                 </c:if>
	                </c:forEach>
	            </select>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             邮件开关：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="emailSwitch" value="${requestScope.roleBean.emailSwitch}">
			    <select class="easyui-combobox select-border02" id="emailSwitchSelect" data-options="editable:false,
			    onSelect: function(record){
			       $('#emailSwitch').val(record.value);
			    },onLoadSuccess:function(){
			       $('#emailSwitch').val($(this).combobox('getValue'));
			    }">
	               <c:forEach var="dataDic" items="${requestScope.EMAIL_SWITCH_TYPE}">  
	                 <c:if test="${requestScope.roleBean.emailSwitch==dataDic.dicItemValue}">
	                    <option value="${dataDic.dicItemValue}" selected="selected">${dataDic.dicItemName}</option>
	                 </c:if>
	                 <c:if test="${requestScope.roleBean.emailSwitch!=dataDic.dicItemValue}">
	                    <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	                 </c:if>
	               </c:forEach>
	            </select>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             短信开关：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="smsSwitch" value="${requestScope.roleBean.smsSwitch}">
			    <select class="easyui-combobox select-border02" id="smsSwitchSelect" data-options="editable:false,
			    onSelect: function(record){
			        $('#smsSwitch').val(record.value);
			    },onLoadSuccess:function(){
			        $('#smsSwitch').val($(this).combobox('getValue'));
			    }">
	               <c:forEach var="dataDic" items="${requestScope.SMS_SWITCH_TYPE}">  
	                 <c:if test="${requestScope.roleBean.smsSwitch==dataDic.dicItemValue}">
	                    <option value="${dataDic.dicItemValue}" selected="selected">${dataDic.dicItemName}</option>
	                 </c:if>
	                 <c:if test="${requestScope.roleBean.smsSwitch!=dataDic.dicItemValue}">
	                    <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	                 </c:if>
	               </c:forEach>
	            </select>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             状态：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="status" value="${requestScope.roleBean.status}">
			    <select class="easyui-combobox select-border02" id="statusSelect" data-options="editable:false,
			    onSelect: function(record){
			        $('#status').val(record.value);
			    },onLoadSuccess:function(){
			        $('#status').val($(this).combobox('getValue'));
			    }">
	                <c:forEach var="dataDic" items="${requestScope.STATUS_TYPE}">  
	                 <c:if test="${requestScope.roleBean.status==dataDic.dicItemValue}">
	                    <option value="${dataDic.dicItemValue}" selected="selected">${dataDic.dicItemName}</option>
	                 </c:if>
	                 <c:if test="${requestScope.roleBean.status!=dataDic.dicItemValue}">
	                    <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	                 </c:if>
	               </c:forEach>
	            </select>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	                     描述：
	          </td>
	          <td style="width:80%">
	            <textarea id="roleDescribe"  class="easyui-validatebox textarea-border01" validType="length[0,100]">${requestScope.roleBean.roleDescribe}</textarea>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
