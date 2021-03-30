<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>待办管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
	<script type="text/javascript" charset="utf-8" src="pages/manage/system/task/js/task-manage.js"></script>

    <script type="text/javascript">
	  $(function(){
	        //刷新数据表格
            $(window).resize(function(){
               $('#infos').datagrid('resize');
            });
    
            //数据表格
			$('#infos').datagrid({
				    title:'待办列表',
				    iconCls:'icon-view', 
		            method:'POST', 
		            singleSelect:false, 
		            //height:'495',
		            fit : true, 
		            border : true,
		            fitColumns:true,
		            striped: true, 
		            collapsible:true, 
		            url:mUrl.queryTaskByConditionPaging,
		            sortName: 'taskId', 
		            sortOrder: 'desc', 
		            remoteSort: true, 
		            idField:'taskId', 
		            queryParams:{}, 
		            pagination:true, 
		            rownumbers:true,
		            pageSize : 15,
					pageList : [15, 30, 45, 60, 75,90,100,150,200,300,500,1000],
				    frozenColumns:[[
		                {field:'ck',checkbox:true},
		                {title:'taskId',field:'taskId',width:10,sortable:true,hidden:true}
				    ]],
				    columns:[[ 
				         {field:'title',title:'标题',width:250},
				         {field:'taskUrl',title:'URL',width:300},
				         {field:'taskParameter',title:'taskParameter',width:1,hidden:true},
				         {field:'status',title:'状态',width:50,
				         formatter:function(value){ 
				           <c:forEach var="dataDic" items="${requestScope.TASK_STATUS_TYPE}">  
				               if(value=='${dataDic.dicItemValue}'){
				                 return '${dataDic.dicItemName}';
		                       }
		                    </c:forEach>
				         }},
				         {field:'createUser',title:'创建人',width:150},
			       		 {field:'createDate',title:'创建时间',width:150}
			       		 
					 
				    ]],
				    toolbar:[
					  <c:forEach items="${requestScope.buttonList}" var="btnList">
					    <c:if test="${btnList.layoutMark=='5'}">
					      {
					        id:'${btnList.buttonId}',
							text:'${btnList.buttonName}',
							iconCls:'${btnList.iconCls}',
							handler:function(){
							   ${btnList.functionName}
						    }
						   },
						 </c:if>
				      </c:forEach>
				    '-'],
				    onLoadSuccess:function(){ 
                      $('#infos').datagrid('clearSelections'); 
                    }
			 });
	  
	    });
	    
	 </script> 
  </head>
  
  <body class="easyui-layout">
     <div data-options="region:'north',border:false" style="padding:5px">
		<form id="queryForm" name ="queryForm">  
			<table>  
			    <tr>  
			      <td width="50px" style="text-align:right">标题：</td>
			      <td width="170px" >
			          <input  id="queryTitle" type="text" class="input-border01" style="width:150px;"/>
			      </td>
			      <td width="50px" style="text-align:right">状态：</td>
			      <td width="100px">
			          <input type="hidden" id="queryStatus" value="">
			          <select class="easyui-combobox select-border01" id="statusSelect" data-options="editable:false,
			          onSelect: function(record){
			            $('#queryStatus').val(record.value);
			          },onLoadSuccess:function(){
					     $('#queryStatus').val($(this).combobox('getValue'));
					  }">
		                <c:forEach var="dataDic" items="${requestScope.TASK_STATUS_TYPE}">  
		                   <c:if test="${dataDic.defaultValue=='true'}">
	                        <option value="${dataDic.dicItemValue}" selected="selected">${dataDic.dicItemName}</option>
	                       </c:if>
	                       <c:if test="${dataDic.defaultValue!='true'}">
	                        <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	                       </c:if>
	                    </c:forEach>
		              </select>
			      </td>
			      <td width="300px">
			          <a href="javascript:void(0)" title="查询" onclick="queryTaskByCondition()" class="easyui-linkbutton" style="margin-left:20px" iconCls="icon-search">查询</a>
			          <a href="javascript:void(0)" title="清空" onclick="clearQueryCondition();" class="easyui-linkbutton" style="margin-left:20px"iconCls="icon-chear">清空</a>
			          <a href="javascript:void(0)" title="重置" onclick="resetQueryCondition();" class="easyui-linkbutton" style="margin-left:20px" iconCls="icon-reload">重置</a>
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
