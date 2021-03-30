<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>部门用户</title>
    
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
               $('#userInfos').datagrid('resize');
            });
    
            //数据表格
			$('#userInfos').datagrid({
		            method:'POST', 
		            singleSelect:false, 
		            height:'278',
		            fit : false, 
		            border : true,
		            fitColumns: false, 
		            striped: true, 
		            collapsible:true, 
		            sortName: 'userId', 
		            sortOrder: 'desc', 
		            remoteSort: true, 
		            idField:'userId', 
		            queryParams:{}, 
		            data:${requestScope.data},
		            rownumbers:true,
				    frozenColumns:[[
		                {title:'userId',field:'userId',width:10,sortable:true,hidden:true}
				    ]],
				    columns:[[ 
				         {field:'userCode',title:'编号',width:150},
				         {field:'userName',title:'用户名',width:150},
				         {field:'realName',title:'姓名',width:160},
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
				         }}
				    ]]
			 });
	    });
	    
	 </script> 
  </head>
  
  <body class="body-scroll">
      <div class="data-outer">  
        <table id="userInfos"></table>
      </div> 
  </body>
</html>
