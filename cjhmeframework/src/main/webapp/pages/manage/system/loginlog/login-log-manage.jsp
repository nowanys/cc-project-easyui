<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>登录日志管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
    <script type="text/javascript">
	  $(function(){
	        //刷新数据表格
            $(window).resize(function(){
               $('#infos').datagrid('resize');
            });
            
            //数据表格
			$('#infos').datagrid({
				    title:'登录日志列表',
				    iconCls:'icon-log-file', 
		            method:'POST', 
		            singleSelect:false, 
		            //height:'463',
		            fit : true, 
		            border : true,
		            fitColumns: false, 
		            striped: true, 
		            collapsible:true, 
		            url:mUrl.queryLoginLogByConditionPaging,
		            sortName: 'loginId', 
		            sortOrder: 'desc', 
		            remoteSort: true, 
		            idField:'loginId', 
		            queryParams:{}, 
		            pagination:true, 
		            rownumbers:true,
		            fitColumns:true,
		            pageSize : 15,
					pageList : [15, 30, 45, 60, 75,90,100,150,200,300,500,1000],
				    frozenColumns:[[
		                {title:'loginId',field:'loginId',width:10,sortable:true,hidden:true}
				    ]],
				    columns:[[ 
				         {field:'loginIP',title:'IP地址',width:350},
				         {field:'status',title:'状态',width:50,
				         formatter:function(value){ 
				           if(value=='1'){
				             return "<span style='color:#219d1d'>正常</span>";
				           }else if(value=='2'){
				             return "<span style='color:#f5921e'>锁定</span>";
				           }else if(value=='3'){
				             return "<span style='color:#f51010'>删除</span>";
				           }else{
				             return "<span style='color:#6357fa'>未知</span>";
				           }
				         }},
				         {field:'createUser',title:'创建人',width:150},
			       		 {field:'createDate',title:'创建时间',width:150},
				    ]],
				    onLoadSuccess:function(){ 
                      $('#infos').datagrid('clearSelections'); 
                    }
			 });
	  
	    });
	    
	    //用户选择弹出框
		var userDialog;
		function userSelect(){
			 userDialog=parent.easyUI.modalDialog({
			    title:'用户选择',
			    iconCls:'icon-select',
			    width:578,
			    height:500,
			    url:mUrl.preSingleUserSelect+"userSelectCallBackFunc",
			     buttons:[{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){
					   userDialog.find('iframe').get(0).contentWindow.selectUsers();
					}
				}]
			  });
		 }
		  
		 //用户选择回调处理
		 function userSelectCallBackFunc(users){
		    if(undefined!=users &&  ''!=users && undefined!=users){
		       if(users.length>1){
		         parent.alertBox.showAlert('只能选择一个用户！','warning');
		       }else{
		         $('#queryUserId').val(users[0].userId);
		         $('#queryUserName').val(users[0].userName);
		         userDialog.dialog('destroy');
		       }
		    }else{
		      parent.alertBox.showAlert('选择用户失败！','error');
		    }
		 }
		 
		 function queryLoginLogByCondition(){
		   var opt = $('#infos').datagrid('options');
		   var parameter = opt.queryParams;
		   parameter['queryUserId'] = $('#queryUserId').val();
		   $('#infos').datagrid('load');
		 }
		 
		 function clearQueryCondition(){
		   $('#queryUserName').val('');
		   $('#queryUserId').val('');
		 }
		 
		 function resetQueryCondition(){
		   clearQueryCondition();
		   queryLoginLogByCondition();
		 }
	 </script>  
  </head>
  
  <body class="easyui-layout">
     <div data-options="region:'north',border:false" style="padding:5px">
		<form id="queryForm" name ="queryForm">  
			<table>  
			    <tr>  
			      <td width="60px" style="text-align:right">用户名：</td>
			      <td width="200px" >
			          <input  id="queryUserId" type="hidden"/>
			          <input  id="queryUserName" type="text" class="input-border07"  readonly="readonly"/>
			          <a href="javascript:void(0);"  class="easyui-linkbutton" icon="icon-select" title="选择图标" onclick="userSelect()"></a>
			      </td>
			      <td width="100px">
			          <a href="javascript:void(0)" title="查询" onclick="queryLoginLogByCondition()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			      </td>  
			      <td width="100px">
			          <a href="javascript:void(0)" title="清空" onclick="clearQueryCondition();" class="easyui-linkbutton" iconCls="icon-chear">清空</a>
			      </td>  
			      <td width="100px">
			          <a href="javascript:void(0)" title="重置" onclick="resetQueryCondition();" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
			      </td> 
			     </tr>
			 </table>  
		</form> 
      </div>
      
      <div data-options="region:'center',border:false" style="padding: 5px">
        <table id="infos"></table>
      </div> 
      
  </body>
</html>
