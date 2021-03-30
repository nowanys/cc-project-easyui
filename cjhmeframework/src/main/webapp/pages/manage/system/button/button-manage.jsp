<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>按钮管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
	<script type="text/javascript" charset="utf-8" src="pages/manage/system/button/js/button-manage.js"></script>

    <script type="text/javascript">
	  $(function(){
	        //刷新数据表格
            $(window).resize(function(){
               $('#infos').datagrid('resize');
            });
            
    
            //数据表格
			$('#infos').datagrid({
				    title:'按钮列表',
				    iconCls:'icon-view', 
		            method:'POST', 
		            singleSelect:false, 
		            fit : true, 
		            border : true,
		            fitColumns: true, 
		            striped: true, 
		            collapsible:true, 
		            url:mUrl.queryButtonByConditionPaging,
		            sortName: 'buttonId', 
		            sortOrder: 'desc', 
		            remoteSort: true, 
		            idField:'buttonId', 
		            queryParams:{}, 
		            pagination:true, 
		            rownumbers:true,
		            pageSize : 15,
					pageList : [15, 30, 45, 60, 75,90,100,150,200,300,500,1000],
				    frozenColumns:[[
		                {field:'ck',checkbox:true},
		                {title:'buttonId',field:'buttonId',width:10,sortable:true,hidden:true}
				    ]],
				    columns:[[ 
				         {field:'buttonName',title:'按钮名称',width:120},
				         {field:'buttonType',title:'按钮分类',width:75,
				         formatter:function(value){ 
				           <c:forEach var="dataDic" items="${requestScope.BUTTON_CATEGORY_TYPE}">  
				               if(value=='${dataDic.dicItemValue}'){
				                 return '${dataDic.dicItemName}';
		                       }
		                    </c:forEach>
				         }},
				         {field:'functionName',title:'函数名',width:160},
				         {field:'iconCls',title:'图标',width:50,
				         formatter:function(value){ 
					        var oldValue = value;
					        value=value.replace(/\s+/g,"");
					        value = jsutil.subString(value,5,value.length);
					        return '<img style="vertical-align: bottom" title="'+oldValue+'" src="'+easyUI.iconImgUrl+value+'.png"/>';
				           
				         }},
				         {field:'busniessMark',title:'业务标识',width:160,
				          formatter:function(value){ 
				        	  <c:forEach var="busniessMark" items="${requestScope.busniessMarks}">  
				        	       if(value=='${busniessMark.busniessMarkVal}'){
					                 return '${busniessMark.busniessMarkDesc}';
			                       }
	                          </c:forEach>
				         }},
				         {field:'layoutMark',title:'布局',width:50,
				         formatter:function(value){ 
				           <c:forEach var="dataDic" items="${requestScope.BUTTON_LAYOUT_MARK_TYPE}">  
				               if(value=='${dataDic.dicItemValue}'){
				                 return '${dataDic.dicItemName}';
		                       }
		                    </c:forEach>
				         }},
				         {field:'menuName',title:'菜单名称',width:130},
				         {field:'sortNum',title:'排序',width:50},
				         {field:'status',title:'状态',width:50,
				         formatter:function(value){ 
				            <c:forEach var="dataDic" items="${requestScope.STATUS_TYPE}">  
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
			      <td width="60px" style="text-align:right">按钮名称：</td>
			      <td width="170px" >
			          <input  id="queryButtonName" type="text" class="input-border01" style="width:150px;"/>
			      </td>
			      <td width="60px" style="text-align:right">菜单：</td>
			      <td width="200px">
			          <input  id="queryMenuName" type="text" class="input-border07"  value="所有" readonly="readonly"/>
			          <input  id="queryMenuId" type="hidden" value="-1"/>
			          <a href="javascript:void(0)" title="选择" onclick="queryMenuByCondition()" class="easyui-linkbutton"  style="height: 23px" iconCls="icon-select"></a>
			      </td>
			    </tr>
			    <tr>
			    <td width="60px" style="text-align:right">业务标识：</td>
			      <td width="170px" >
			          <input type="hidden" id="queryBusniessMark" value="-1" defVal="-1">
			          <select class="easyui-combobox select-border01" id="busniessMarkSelect" data-options="editable:false,onSelect: function(record){
			          $('#queryBusniessMark').val(record.value);}">
		                <option value="-1" selected="selected">所有</option>
		                <c:forEach var="busniessMark" items="${requestScope.busniessMarks}">  
                          <option value="${busniessMark.busniessMarkVal}">${busniessMark.busniessMarkDesc}</option>
                        </c:forEach>
		              </select>
			      </td>
			      <td width="36px" style="text-align:right">状态：</td>
			      <td width="100px">
			          <input type="hidden"  id="queryStatus" value="" defVal="">
			          <select class="easyui-combobox select-border01" id="statusSelect" data-options="editable:false,
			          onSelect: function(record){
			            $('#queryStatus').val(record.value);
			          },onLoadSuccess:function(){
					     $('#queryStatus').val($(this).combobox('getValue'));
					     jsutil.setDefAttrVal('queryStatus',$(this).combobox('getValue'));
					  }">
		                <c:forEach var="dataDic" items="${requestScope.QUERY_STATUS_TYPE}">  
	                       <c:if test="${dataDic.defaultValue=='true'}">
	                        <option value="${dataDic.dicItemValue}" selected="selected">${dataDic.dicItemName}</option>
	                       </c:if>
	                       <c:if test="${dataDic.defaultValue!='true'}">
	                        <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	                       </c:if>
	                    </c:forEach>
		              </select>
			      </td>
			      <td width="100px">
			          <a href="javascript:void(0)" title="查询" onclick="queryButtonByCondition()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
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
