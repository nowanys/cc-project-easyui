<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>部门分配</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
    <script type="text/javascript">
      var id='${requestScope.userId}';
	  $(function(){
	       $('#deptTree').tree({
						data:${requestScope.deptTreeData},
						parentField:"parentDeptId",
						textFiled:"deptName",
						idFiled:"deptId",
						onClick: function(node){
						   var rows=$('#deptInfos').datagrid('getRows');
						   var dataCount = rows.length;
						   for(var i=0;i<dataCount;i++){
						     if(rows[i].deptId==node.id){
						       parent.alertBox.showAlert("“"+node.text+"”部门已分配！",'warning');
						       return ;
						     }
						   }
						   
						   $('#deptInfos').datagrid('insertRow',{
								index:dataCount,
								row: {
									deptId: node.id,
									deptName: node.text
								}
						  });
						}
		    });
		   
            //数据表格
			$('#deptInfos').datagrid({
		            method:'POST', 
		            singleSelect:false, 
		            height:'300',
		            fit : false, 
		            border : true,
		            fitColumns: false, 
		            striped: true, 
		            collapsible:true, 
		            sortName: 'deptId', 
		            sortOrder: 'desc', 
		            remoteSort: true, 
		            idField:'deptId', 
		            queryParams:{}, 
		            data:${requestScope.userDeptData},
		            rownumbers:true,
				    frozenColumns:[[
		                {field:'ck',checkbox:true},
		                {title:'deptId',field:'deptId',width:10,sortable:true,hidden:true}
				    ]],
				    columns:[[ 
				         {field:'deptName',title:'部门名称',width:200},
				    ]],
				    toolbar: [{
							id:'del',
							text:'删除',
							iconCls:'icon-del',
							handler:function(){
							 var selected =$('#deptInfos').datagrid('getSelected'); 
							 if(selected){
							     var selections  =$('#deptInfos').datagrid('getSelections');
								 var selectRows = [];
								 for ( var i= 0; i< selections.length; i++) {
						           selectRows.push(selections[i]);
						         }
	                             for(var j =0;j<selectRows.length;j++){
	                               var index = $('#deptInfos').datagrid('getRowIndex',selectRows[j]);
	                               $('#deptInfos').datagrid('deleteRow',index);
	                             }
							 }else{
							   parent.alertBox.showAlert('请选择一条记录操作！','warning');
							 }
						    }
					}]
			 });
	    });
	    
	    function serializeForm(){
	        var rows=$('#deptInfos').datagrid('getRows');
		    var param =[]; 
			for(var i=0;i<rows.length;i++){
				param.push(rows[i].deptId);
			}
			
			var jsonInfo ={
				"userId":id,
				"deptIds":param
		    };
		    
		    return jsonInfo;
	    }
	    
	 </script> 
  </head>
  
  <body class="body-scroll">
    <div class="data-outer">
      <div class="data-div-left" style="width:220px;">  
        <span class="span1">所有部门：</span>
        <div class="tree-inner-div" style="height: 290px;overflow-y: scroll">
           <ul id="deptTree"></ul>
        </div>
      </div>
      <div class="data-div-right" style="width:270px;"> 
        <span class="span1">分配部门：</span>
        <table id="deptInfos"></table>
      </div>
    </div>
  </body>
</html>
