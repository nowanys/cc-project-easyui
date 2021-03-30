<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<link rel="stylesheet" type="text/css" charset="utf-8" href="common/js/easyUI/other/css/portal.css">
	<script type="text/javascript" charset="utf-8" src="common/js/easyUI/other/jquery.portal.js"></script>
	
    <script type="text/javascript" charset="utf-8" src="pages/manage/system/main/js/home.js"></script>
    
  </head>
  
  <body class="body-scroll">
		<div id="desktop" style="margin-top: 30px;overflow-x:hidden " >
			<div style="width:50%;">
			
			    <!-- 我的资料 -->
				<div title="我的资料" data-options="iconCls:'icon-person-center',collapsible:true" style="height:210px;">
				   <div class="data-left">
				     <c:if test="${!empty requestScope.fileDataBean}">
		                <img class="user-head-img" src="${requestScope.fileDataBean.fileUrl}">
		             </c:if>
		             <c:if test="${empty requestScope.fileDataBean}">
		                <img class="user-head-img" src="pages/manage/images/user-default-head.png">
		             </c:if>
				   </div>
				   
				   <div class="data-right">
				     <table class="data-grid2" style="height:150px;margin-top:12px;margin-left: 23px" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="data-grid-td5" style="width: 200px">
					      姓名：${requestScope.user.realName}
					    </td>
					    <td class="data-grid-td5">
					      电话：${requestScope.user.telephone}
					    </td>
					  </tr>
					  <tr>
					    <td class="data-grid-td5">
					      邮箱：${requestScope.user.email}
					    </td>
					    <td class="data-grid-td5">
					      登录次数：${requestScope.userLoginLog.loginCount}
					    </td>
					  </tr>
					  <tr>
					    <td class="data-grid-td5">
					      上次登录：${requestScope.userLoginLog.lastLoginDate}
					    </td>
					    <td class="data-grid-td5">
					      本次登录：${requestScope.userLoginLog.currentLoginDate}
					    </td>
					  </tr>
					  <tr>
					    <td colspan="3">
					      <a href="javascript:void(0);" data-options="iconCls:'icon-person'"  class="easyui-linkbutton" style="margin-right: 20px;margin-left: 1px" title="修改资料" id="userEdit">修改资料</a>
					      <a href="javascript:void(0);" data-options="iconCls:'icon-pwd-change'" class="easyui-linkbutton" style="margin-right: 20px" title="修改密码" id="pwdEdit">修改密码</a>
					      <a href="javascript:void(0);" data-options="iconCls:'icon-exit'"  class="easyui-linkbutton" title="退出系统" id="exitSys">退出系统</a>
					    </td>
					  </tr>
					</table>
				   </div>
			    </div>
			    <!-- 我的资料 -->
			    
			    <!-- 我的待办 -->
			    <div title="我的待办" data-options="iconCls:'icon-tip',collapsible:true" style="height:210px;">
					<div class="data-left">
					   <div class="data-card">
					          <div style="margin: 5px;margin-bottom:1px">
					               您有：
					          </div>
					          <div style="text-align: center;">
					             <span class="data-card-num">${requestScope.task.count}</span>
					             <span>个</span>
					          </div>
					          <div style="margin: 10px;text-align: center;">
					               未处理的待办
					          </div>
					          <div style="text-align: center;">
					             <a href="javascript:void(0);"   class="easyui-linkbutton" title="查看更多" onclick="taskMoreView()">查看更多&gt;&gt;</a>
					          </div>
					   </div>
					</div>
					<div class="data-right">
					   <table class="data-grid" border="0" cellpadding="0" cellspacing="0">
						   <thead>
						    <tr>
						        <th class="data-grid-td1" style="width:23px"></th>
								<th class="data-grid-td1" >标题</th>
								<th class="data-grid-td1" style="width:85px">处理人</th>
								<th class="data-grid-td1" style="width:130px">创建时间</th>
						    </tr>
						   </thead>
						   <tbody>
						     <c:forEach var="itemData" items="${requestScope.task.tasks}" varStatus="stauts">  
					         <tr onclick="taskView('${itemData.taskUrl}','${itemData.taskId}','${itemData.taskParameter}')">
					              <td class="data-grid-td2">
					                ${stauts.count} 
					              </td>
					              <td class="data-grid-td3">
					                ${itemData.title}
					              </td>
					              <td class="data-grid-td3">
					                ${itemData.createUser}
					              </td>
					              <td class="data-grid-td4">
					                ${itemData.createDate}
					              </td>
					        </tr>
					        </c:forEach>
						   </tbody>
						</table>
					</div>
				</div>
				<!-- 我的待办 -->
				
			</div>
			
			<div style="width:50%;">
			
			    <!-- 我的公告 -->
			    <div title="我的公告" data-options="iconCls:'icon-notice',collapsible:true" style="height:210px;padding: 9px">
					  <table class="data-grid"  border="0" cellpadding="0" cellspacing="0">
						   <thead>
						    <tr>
						        <th class="data-grid-td1" style="width:23px"></th>
								<th class="data-grid-td1">标题</th>
								<th class="data-grid-td1" style="width:85px">创建人</th>
								<th class="data-grid-td1" style="width:130px">创建时间</th>
								<th class="data-grid-td1" style="width:69px"><a href="javasrcipt:void(0);">查看更多&gt;&gt;</a></th>
						    </tr>
						   </thead>
						   <tbody>
						     <c:forEach var="itemData" items="${requestScope.notices}" varStatus="stauts">  
					          <tr>
					              <td class="data-grid-td2" onclick="noticeView('${itemData.noticeId}')">
					                ${stauts.count} 
					              </td>
					              <td class="data-grid-td3" onclick="noticeView('${itemData.noticeId}')">
					                ${itemData.noticeTitle}
					              </td>
					              <td class="data-grid-td3" onclick="noticeView('${itemData.noticeId}')">
					                ${itemData.createUser}
					              </td>
					              <td class="data-grid-td4" onclick="noticeView('${itemData.noticeId}')">
					                ${itemData.createDate}
					              </td>
					              <td class="data-grid-td4">
					                <c:choose>
					                    <c:when test="${itemData.noticeType==1}">系统公告</c:when>
					                    <c:when test="${itemData.noticeType==2}">部门公告</c:when>
					                    <c:when test="${itemData.noticeType==3}">角色公告</c:when>
					                    <c:otherwise>未知</c:otherwise>
					                </c:choose>
					              </td>
					          </tr>
					        </c:forEach>
						   </tbody>
						</table>
				</div>
				<!-- 我的公告 -->
				
				<!-- 常用功能 -->
				<div title="常用功能" style="height:210px;" data-options="iconCls:'icon-task',collapsible:true,
				 tools:[{
			        iconCls:'icon-config',
			        handler:function(){
			          comFuncConfig();
			        }
			     }]" >
					<ul class="com-func" style="width: 95%">
					   <c:forEach var="itemData" items="${requestScope.comFuncs}" varStatus="stauts">  
					   <li>
					       <a href="javascript:void(0);" data-options="iconCls:'${itemData.iconCls}'" style="width:110px;white-space:nowrap;" class="easyui-linkbutton" title="${itemData.menuName}" onclick="parent.addTab('${itemData.menuName}','${itemData.accessUrl}','${itemData.iconCls}');">
						       <c:choose>
						           <c:when test="${fn:length(itemData.menuName)>'6'}">
						              ${fn:substring(itemData.menuName,0,6)}...
						           </c:when>
						           <c:otherwise>
						              ${itemData.menuName}
						           </c:otherwise>
						       </c:choose>
					       </a>
					   </li>
					   </c:forEach>
					</ul>
				</div>
				<!-- 常用功能 -->
				
			</div>
		</div>
  </body>
</html>
