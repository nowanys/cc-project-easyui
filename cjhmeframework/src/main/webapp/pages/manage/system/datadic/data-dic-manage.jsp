<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>数据字典管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
	<script type="text/javascript" charset="utf-8" src="pages/manage/system/datadic/js/data-dic-manage.js"></script>
    <style type="text/css">
       .layout-split-west {
           border-right: 2px solid #fefefe;
       }
       
	   .data-dic-type-ul{
	       list-style-type: none;
		   padding: 0;
		   margin: 12px
	   }
	   
	   .data-dic-type-ul li{
	       height: 25px;
	       line-height: 25px;
	       padding-left: 5px;
	       margin-top: 2px;
	       cursor: hand;
	       cursor: pointer;
	   }
	   
	   .data-dic-type-ul li div{
	       float:left;
	       height:23px;
	       margin-left: 3px;
	       width: 146px;
	   }
	   
	   .data-dic-type-toolbar{
	      border-bottom:1px solid #ddd;
	      height:26px;
	      background: #f6f6f6;
	      padding: 2px
	   }
	   
	   
	 </style> 
	 
    <script type="text/javascript">
	  $(function(){
	        //刷新数据表格
            $(window).resize(function(){
               $('#main').layout('resize');
               $('#infos').datagrid('resize');
            });
    
            //数据表格
			$('#infos').treegrid({
				    title:'数据字典明细列表',
				    iconCls:'icon-view', 
		            method:'POST', 
		            singleSelect:false, 
		            //height:'512',
		            fit : true, 
		            border : true,
		            fitColumns: true, 
		            striped: true, 
		            collapsible:true, 
		            url:'',
		            sortName: 'dicItemId', 
		            sortOrder: 'desc', 
		            remoteSort: true, 
		            idField:'dicItemId',
		            treeField:'dicItemCode',
		            queryParams:{}, 
		            pagination:true, 
		            rownumbers:true,
		            pageSize : 10,
					pageList : [10,15,20],
				    frozenColumns:[[
		                {field:'ck',checkbox:true},
		                {title:'dicItemId',field:'dicItemId',width:10,sortable:true,hidden:true}
				    ]],
				    columns:[[ 
				         {field:'dicItemCode',title:'编号',width:150},
				         {field:'dicItemName',title:'名称',width:150},
				         {field:'dicItemValue',title:'值',width:150},
				         {field:'spareValue',title:'备用值',width:150},
				         {field:'defaultValue',title:'默认值',width:150},
				         {field:'status',title:'状态',width:50,
				         formatter:function(value){ 
				           <c:forEach var="dataDic" items="${requestScope.STATUS_TYPE}">  
				               if(value=='${dataDic.dicItemValue}'){
				                 return '${dataDic.dicItemName}';
		                       }
		                    </c:forEach>
				         }},
				         {field:'sortNum',title:'排序',width:50},
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
			     <td width="240px">类别：
			          <input type="hidden" id="queryDicCategory" value="">
			          <select class="easyui-combobox select-border01" id="dicCategorySelect" data-options="editable:false,
			          onSelect: function(record){
			            $('#queryDicCategory').val(record.value);
			            queryDataDicType();
			          },onLoadSuccess:function(){
					     $('#queryDicCategory').val($(this).combobox('getValue'));
					  }">
		                <c:forEach var="dataDic" items="${requestScope.DIC_CATEGORY_TYPE}">  
	                       <c:if test="${dataDic.defaultValue=='true'}">
	                        <option value="${dataDic.dicItemValue}" selected="selected">${dataDic.dicItemName}</option>
	                       </c:if>
	                       <c:if test="${dataDic.defaultValue!='true'}">
	                        <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	                       </c:if>
	                    </c:forEach>
		              </select>
			      </td>
			      <td width="240px">状态：
			          <input type="hidden" id="queryStatus" value="">
			          <select class="easyui-combobox select-border01" id="statusSelect" data-options="editable:false,
			          onSelect: function(record){
			            $('#queryStatus').val(record.value);
			          },onLoadSuccess:function(){
					     $('#queryStatus').val($(this).combobox('getValue'));
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
			      <td>
			         <span style="color:red">新增的数据字典明细内容不会立即生效，如需要立即生效请点击&gt;&gt;</span>
			         <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-reload" onclick="reloadDataDictionary();" title="重新载入数据字典信息">重新载入数据字典信息</a>
			      </td>
			     </tr>  
			 </table>  
		</form> 
      </div>
      
       <div data-options="region:'center',border:false" style="padding: 5px;overflow: hidden;">
	      <div id="main" class="easyui-layout"  style="width:100%;height: 100%;overflow: hidden;">
			    <div data-options="region:'west',split:true,title:'数据字典分类',iconCls:'icon-data-dic'" style="width:235px;overflow-y: scroll;">
			       <div class="data-dic-type-toolbar">
	                    <c:forEach items="${requestScope.buttonList}" var="btnList">
					     <c:if test="${btnList.layoutMark=='3'}">
					         <a href="javascript:void(0);" plain="true" class="easyui-linkbutton" icon="${btnList.iconCls}" title="${btnList.buttonName}" onclick="${btnList.functionName}">${btnList.buttonName}</a>
						 </c:if>
				        </c:forEach>
	               </div>
	               
	               <ul id="dataDicList" class="data-dic-type-ul"></ul>
			    </div>
			     <div data-options="region:'center'" style="border: 0px;overflow: hidden;">
			        <table id="infos"></table>
			    </div>
		  </div>
	  </div>
  </body>
</html>
