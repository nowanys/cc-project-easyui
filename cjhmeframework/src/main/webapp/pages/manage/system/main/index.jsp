<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	<script type="text/javascript" charset="utf-8" src="pages/manage/system/main/js/index.js"></script>
	
    
  </head>
  
  <body class="easyui-layout">
  
     <!-- 北面板 start... -->     
     <div data-options="region:'north',border:false,split:false" class="header">
		 <div class="header-left">
		    <div>
		    </div>
		 </div>
		 <div class="header-right">
		   <div class="header-right-div1">
		      <span class="icon-person-center other-span" style="padding-top:10px"></span>
		      <span class="color-style other-margin-left" id="sysCurrentUser">欢迎[${requestScope.sysCurrentUser}]</span>
		      <span class="icon-time other-span" style="padding-top:10px"></span>
	          <span class="color-style other-margin-left" id="sysCurrentTime">${requestScope.sysCurrentTime}</span> 
              <a href="javascript:void(0);"  id="individuation" plain="true"  class="easyui-linkbutton" style="color:#fbfbfb;" data-options="iconCls:'icon-individuation'" title="个性化">个性化</a>
		      <a href="javascript:void(0);"  id="exitSys" plain="true"  class="easyui-linkbutton" style="color:#fbfbfb;" data-options="iconCls:'icon-exit'" title="退出系统">退出系统</a>
		   </div>
		   <div class="header-right-div2">
		    <span style="vertical-align: middle;">角色：</span>
		     <select class="easyui-combobox select-border02" style="width:135px;" data-options="editable:false,onSelect: function(record){
			   changeCurrentRole(record.value); }">
				<c:forEach var="role" items="${requestScope.currentUserRoles}">  
				   <c:choose>
	                   <c:when test="${requestScope.currentUserRole.roleId==role.roleId}">
	                      <option value="${role.roleId}" selected="selected">${role.roleName}</option>
	                   </c:when>
	                   <c:otherwise>
	                      <option value="${role.roleId}">${role.roleName}</option>
	                   </c:otherwise>
	               </c:choose>
	            </c:forEach>
			 </select>
		     <span style="vertical-align: middle;margin-left:8px">部门：</span>
		     <select class="easyui-combobox select-border02" style="width:135px" data-options="editable:false,onSelect: function(record){
			   changeCurrentDept(record.value); }">
				<c:forEach var="dept" items="${requestScope.currentUserDepts}">  
				   <c:choose>
	                   <c:when test="${requestScope.currentUserDept.deptId==dept.deptId}">
	                      <option value="${dept.deptId}" selected="selected">${dept.deptName}</option>
	                   </c:when>
	                   <c:otherwise>
	                      <option value="${dept.deptId}">${dept.deptName}</option>
	                   </c:otherwise>
	               </c:choose>
	            </c:forEach>
			 </select>
		   </div>
		 </div>
     </div>
     <!-- 北面板 end...-->
		
	 <!-- 南面板 start... -->	
	 <div data-options="region:'south',border:false,split:false" class="south-panel">
		<div>
			 <span style="color:#333"> cjhmeframework © 版权所有 2014-2015</span>
		</div>
	 </div>
	 <!-- 南面板 end... -->
	
	
	
	 <!-- 西面板 start... -->
	 <div id="westLayout" data-options="region:'west',split:false,iconCls:'icon-system'" title="系统导航" class="west-panel">
	 </div>
	 <!-- 西面板 end...-->
	
	
	
	<!-- 东面板 start... -->
	<div data-options="region:'center'">
	
	    <!-- 页签 -->
		<div class="easyui-tabs" id="tab" data-options="fit:true,border:false,tools:'#tabTools'">
		    <div title="主页" data-options="iconCls:'icon-home',closable:false">
				<iframe width='100%' height='99.3%'  id='iframe' frameborder='0' scrolling='no'  src='system/preHome.jhtml'></iframe>
			</div>
		</div>
		
	</div>
	<!-- 东面板 end...-->
	
	
	<!-- tabtools -->
	<div id="tabTools">
		<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-reload"  onclick="refreshCurrentTab();"> </a>
		<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#closeMenus'"  iconCls="icon-close" ></a>
	</div>
	
	<!-- menus -->
	<div id="closeMenus" class="easyui-menu" style="width:170px;display: none">
	      <div  iconCls="icon-close-current" onclick="closeTab('closeCurrent');">关闭当前页签</div>
          <div  iconCls="icon-close-all" onclick="closeTab('closeAll');">关闭所有页签</div>
          <div  iconCls="icon-close-other" onclick="closeTab('closeOther');">关闭除此之外所有页签</div>
    </div>
    
    
    
</body>
</html>
