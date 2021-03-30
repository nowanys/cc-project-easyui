<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>所有用户选择</title>
    
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
				    //title:'用户列表',
				    //iconCls:'icon-view', 
		            method:'POST', 
		            singleSelect:false, 
		            height:'308',
		            fit : false, 
		            border : true,
		            fitColumns: false, 
		            striped: true, 
		            collapsible:true, 
		            url:mUrl.queryAllUserByConditionPaging,
		            sortName: 'userId', 
		            sortOrder: 'desc', 
		            remoteSort: true, 
		            idField:'userId', 
		            queryParams:{}, 
		            pagination:true, 
		            rownumbers:true,
		            pageSize : 10,
					pageList : [10, 30, 45, 60, 75,60,70,90,120,150,200,300,500,1000],
				    frozenColumns:[[
		                {field:'ck',checkbox:true},
		                {title:'userId',field:'userId',width:10,sortable:true,hidden:true}
				    ]],
				    columns:[[ 
				         {field:'userCode',title:'编号',width:150},
				         {field:'userName',title:'用户名',width:150},
				         {field:'realName',title:'姓名',width:150}
				    ]],
				    onLoadSuccess:function(){ 
                      $('#infos').datagrid('clearSelections'); 
                    }
			 });
	  
	    });
	    
		//根据条件查询用户
		function queryUserByCondition(){
		     var opt = $('#infos').datagrid('options');
			 opt.url= mUrl.queryUserByConditionPaging;
			 var parameter = opt.queryParams;
			 parameter['userCode'] = $('#queryUserCode').val();
			 parameter['userName'] = $('#queryUserName').val();
			 parameter['realName'] = $('#queryRealName').val();
			 $('#infos').datagrid('load');
		}
		
		//清空
		function clearQueryCondition(){
		     $('#queryUserCode').val('');
			 $('#queryUserName').val('');
			 $('#queryRealName').val('');
		}
		
		//重置查询条件
		function resetQueryCondition(){
		     clearQueryCondition();
		     queryUserByCondition();
		}
		
		//选择用户
		function selectUsers(){
           var selected =$('#infos').datagrid('getSelected'); 
		   if(!selected){
		    parent.alertBox.showAlert('请选择一条记录操作！','warning');
		    return ;
		   }
		   
		   var parameter =[];
      
	       var selecteds =$('#infos').datagrid('getSelections');  
	       for(var i=0;i<selecteds.length;i++){
	         parameter.push(selecteds[i])
	       }
	
	 	   parameter.join(",");
	 	   
	 	   var iframes = window.parent.frames;
           var iframeObj;
           for(var i=0;i<iframes.length;i++){
              if(iframes[i].${requestScope.callBackFunc}){
                 iframeObj=iframes[i];
                 break;
              }
           }
           
	 	   iframeObj.${requestScope.callBackFunc}(parameter);
        }
	    
	 </script> 
  </head>
  
  <body class="body-scroll">
   <div class="data-outer">
      <div class="query-div">
		<form id="queryForm" name ="queryForm">  
			<table>  
			    <tr>  
			      <td width="36px">编号：</td>
			      <td width="170px" >
			          <input  id="queryUserCode" type="text" class="input-border01" style="width:150px;"/>
			      </td>
			      <td width="50px">用户名：</td>
			      <td width="170px" >
			          <input  id="queryUserName"  type="text" class="input-border01" style="width:150px;"/>
			      </td>
			      <td width="50px"></td>
			    </tr>
			    <tr>
			      <td width="36px">姓名：</td>
			      <td width="170px" >
			          <input  id="queryRealName"  type="text" class="input-border01" style="width:150px;"/>
			      </td>
			      <td width="100px" colspan="4">
			          <a href="javascript:void(0)" title="查询" onclick="queryUserByCondition()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			          <a href="javascript:void(0)" title="清空" onclick="clearQueryCondition();" class="easyui-linkbutton" iconCls="icon-no" style="margin-left: 20px">清空</a>
			          <a href="javascript:void(0)" title="重置" onclick="resetQueryCondition();" class="easyui-linkbutton" iconCls="icon-reload" style="margin-left: 20px">重置</a>
			      </td> 
			    </tr>
			 </table>  
		</form> 
      </div>
      
      <div class="data-div">  
        <table id="infos"></table>
      </div> 
    </div>
</html>
