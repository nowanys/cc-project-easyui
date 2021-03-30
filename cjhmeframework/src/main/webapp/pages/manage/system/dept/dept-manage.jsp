<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>部门管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
	<style type="text/css">
	   .layout-split-west {
           border-right: 2px solid #fefefe;
       }
	</style>
	
	<script type="text/javascript" charset="utf-8" src="pages/manage/system/dept/js/dept-manage.js"></script>
	
    <script type="text/javascript">
	  $(function(){
	        //刷新数据表格
            $(window).resize(function(){
               $('#main').layout('resize');
               $('#infos').datagrid('resize');
            });
    
            //数据表格
			$('#infos').datagrid({
				    title:'部门明细列表',
				    iconCls:'icon-view', 
		            method:'POST', 
		            singleSelect:false, 
		            //height:'495',
		            fit : true, 
		            border : true,
		            fitColumns: true, 
		            striped: true, 
		            collapsible:true, 
		            url:mUrl.queryDeptByConditionPaging,
		            sortName: 'deptId', 
		            sortOrder: 'desc', 
		            remoteSort: true, 
		            idField:'deptId', 
		            queryParams:{'deptId':'1','queryStatus':'1'}, 
		            pagination:true, 
		            rownumbers:true,
		            pageSize : 15,
					pageList : [15, 30, 45, 60, 75,90,100,150,200,300,500,1000],
				    frozenColumns:[[
		                {field:'ck',checkbox:true},
		                {title:'deptId',field:'deptId',width:10,sortable:true,hidden:true}
				    ]],
				    columns:[[ 
				         {field:'deptCode',title:'编号',width:150},
				         {field:'deptName',title:'部门名称',width:170},
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
      <div data-options="region:'north',border:false" style="padding:5px;overflow: hidden;">
		<form id="queryForm" name ="queryForm">  
			<table>  
			    <tr>
			      <td width="60px">部门名称：</td>
			      <td width="170px" >
			          <input  id="queryDeptName"  type="text" class="input-border01" style="width:150px;"/>
			      </td>
			      <td width="240px">状态：
			          <input type="hidden" id="queryStatus" value="" defVal="">
			          <select class="easyui-combobox select-border01" id="statusSelect" data-options="editable:false,
			          onSelect: function(record){
			            $('#queryStatus').val(record.value);
			          },onLoadSuccess:function(){
					     $('#queryStatus').val($(this).combobox('getValue'));
					     jsutil.setDefAttrVal('queryStatus',$(this).combobox('getValue'));
					  }">
			            <option value="1" selected="selected">指定部门</option>
		                <option value="2">所有部门</option>
		              </select>
			      </td>
			      <td width="100px">
			          <a href="javascript:void(0)" title="查询" onclick="queryDeptByCondition()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
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
      
      <div data-options="region:'center',border:false" style="padding: 5px;overflow: hidden;">
	      <div id="main" class="easyui-layout"  style="width:100%;height: 100%;overflow: hidden;">
			    <div data-options="region:'west',split:true,title:'部门结构',iconCls:'icon-dept'" style="width:250px;overflow-y: scroll;">
	               <ul id="deptTree" style="margin: 10px"></ul>
			    </div>
			    <div data-options="region:'center'" style="border: 0px;overflow: hidden;">
			        <table id="infos"></table>
			    </div>
		  </div>
	  </div>
  </body>
</html>
