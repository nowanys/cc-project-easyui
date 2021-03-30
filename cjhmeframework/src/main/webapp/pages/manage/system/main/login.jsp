<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>管理系统登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
    <script type="text/javascript" charset="utf-8" src="pages/manage/system/main/js/login.js"></script>
    <script type="text/javascript">
       $(function(){
         var msg='${requestScope.msg}'
         if(undefined!=msg && ""!=msg && "null"!=msg){
            parent.alertBox.showAlert(msg,'warning');
         }
       });
    </script>
    
  </head>
  
  <body class="login-body">
  
       <div class="login-box">
           <div style="height: 90%">
	          <div class="login-box-left">
	            <div class="login-box-left-inner">
	            </div>
	          </div>
	          <div class="login-box-rgith">
	            <p class="login-title">管理系统登录</p>
	            <div class="login-input-box">
	                <table>
			          <tr style="height: 46px;">
			          	<td class="login-td">用户名：</td>
			          	<td>
			              <input id="userName" type="text" value="admin" class="easyui-validatebox login-input"  required="required" validType="length[5,15]">
			            </td>
			          </tr>
			          <tr style="height: 46px;">
			            <td class="login-td">密码：</td>
			          	<td>
			              <input id="password" type="password" value="admin" class="easyui-validatebox login-input"  required="required">
			            </td>
			          </tr>
			          <tr style="height: 46px;">
			            <td>
			            </td>
			          	<td colspan="2">
			          	   <a href="javascript:void(0);"  onclick="validateLogin();"   class="easyui-linkbutton" style="color:#fbfbfb;float: left;color: #333" data-options="iconCls:'icon-login'" title="登录">登录</a>
			          	   <a href="javascript:void(0);"  onclick="resetLogin();"   class="easyui-linkbutton" style="color:#fbfbfb;float: right;margin-right: 2px;color: #333" data-options="iconCls:'icon-chear'" title="清空">清空</a>
			            </td>
			          </tr>
			        </table>
	          	</div>
	          </div>
	        </div>
	        <div class="login-copyright">
		           cjhmeframework © 版权所有 2014-2015
		  </div>
       </div> 
       
  </body>
</html>
