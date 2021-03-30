<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>角色分配</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
    <script type="text/javascript">
      var id='${requestScope.dicItemId}';
	  $(function(){
	       //数据表格
		   $('#roleInfos').datagrid({
		            method:'POST', 
		            singleSelect:true, 
		            height:'300',
		            fit : false, 
		            border : true,
		            fitColumns: false, 
		            striped: true, 
		            collapsible:true, 
		            sortName: 'roleId', 
		            sortOrder: 'desc', 
		            remoteSort: true, 
		            idField:'roleId', 
		            queryParams:{}, 
		            data:${requestScope.roleData},
		            rownumbers:true,
				    frozenColumns:[[
		                {title:'roleId',field:'roleId',width:10,sortable:true,hidden:true}
				    ]],
				    columns:[[ 
				         {field:'roleCode',title:'编号',width:120},
				         {field:'roleName',title:'角色名称',width:160}
				    ]],
				    onClickRow: function(rowIndex, rowData){
						   var rows=$('#dataDicItemRoleInfos').datagrid('getRows');
						   var dataCount = rows.length;
						   for(var i=0;i<dataCount;i++){
						     if(rows[i].roleId==rowData.roleId){
						       parent.alertBox.showAlert("“"+rowData.roleName+"”角色已分配！",'warning');
						       return ;
						     }
						   }
						   
						   $('#dataDicItemRoleInfos').datagrid('insertRow',{
								index:dataCount,
								row: {
									roleId: rowData.roleId,
									roleCode: rowData.roleCode,
									roleName: rowData.roleName
								}
						  });
					}
			});
			
			 
            //数据表格
			$('#dataDicItemRoleInfos').datagrid({
		            method:'POST', 
		            singleSelect:false, 
		            height:'300',
		            fit : false, 
		            border : true,
		            fitColumns: false, 
		            striped: true, 
		            collapsible:true, 
		            sortName: 'roleId', 
		            sortOrder: 'desc', 
		            remoteSort: true, 
		            idField:'roleId', 
		            queryParams:{}, 
		            data:${requestScope.dataDicItemRoleData},
		            rownumbers:true,
				    frozenColumns:[[
		                {field:'ck',checkbox:true},
		                {title:'roleId',field:'roleId',width:10,sortable:true,hidden:true}
				    ]],
				    columns:[[ 
				         {field:'roleCode',title:'编号',width:120},
				         {field:'roleName',title:'角色名称',width:160}
				    ]],
				    toolbar: [{
							id:'del',
							text:'删除',
							iconCls:'icon-del',
							handler:function(){
							 var selected =$('#dataDicItemRoleInfos').datagrid('getSelected'); 
							 if(selected){
							     var selections  =$('#dataDicItemRoleInfos').datagrid('getSelections');
								 var selectRows = [];
								 for ( var i= 0; i< selections.length; i++) {
						           selectRows.push(selections[i]);
						         }
	                             for(var j =0;j<selectRows.length;j++){
	                               var index = $('#dataDicItemRoleInfos').datagrid('getRowIndex',selectRows[j]);
	                               $('#dataDicItemRoleInfos').datagrid('deleteRow',index);
	                             }
							 }else{
							   parent.alertBox.showAlert('请选择一条记录操作！','warning');
							 }
						    }
					}]
			 });
	    });
	    
	    function serializeForm(){
	        var rows=$('#dataDicItemRoleInfos').datagrid('getRows');
		    var param =[]; 
			for(var i=0;i<rows.length;i++){
				param.push(rows[i].roleId);
			}
			
			var jsonInfo ={
				"dicItemId":id,
				"roleIds":param
		    };
		    
		    return jsonInfo;
	    }
	    
	 </script> 
  </head>
  
  <body class="body-scroll">
    <div class="data-outer">
      <div class="data-div-left" style="width:323px;">  
        <span class="span1">所有角色：</span>
        <table id="roleInfos"></table>
      </div>
      <div class="data-div-right" style="width:350px;"> 
        <span class="span1">分配角色：</span>
        <table id="dataDicItemRoleInfos"></table>
      </div>
     </div>
  </body>
</html>
