<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>常用功能配置</title>
    
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
            $('#menuTree').tree({
						data:${requestScope.menus},
						parentField:"parentMenuId",
						textFiled:"menuName",
						idFiled:"menuId",
						onClick: function(node){
							 //如果不是叶子节点展开
					           if (!$(this).tree('isLeaf', node.target)) {
					                $(this).tree('expand',node.target);
					           }else{
					        	   var rows=$('#menuInfos').datagrid('getRows');
								   var dataCount = rows.length;
								   for(var i=0;i<dataCount;i++){
								     if(rows[i].menuId==node.id){
								       parent.alertBox.showAlert("“"+node.text+"”菜单已分配！",'warning');
								       return ;
								     }
								   } 
								   
								   $('#menuInfos').datagrid('insertRow',{
										index:dataCount,
										row: {
											menuId: node.id,
											menuName: node.text
										}
								   });
					           }
						}
		   });
	    
		   $('#menuInfos').datagrid({
			            method:'POST', 
			            singleSelect:false, 
			            height:'300',
			            fit : false, 
			            border : true,
			            fitColumns: false, 
			            striped: true, 
			            collapsible:true, 
			            sortName: 'menuId', 
			            sortOrder: 'desc', 
			            remoteSort: true, 
			            idField:'menuId', 
			            queryParams:{}, 
			            data:${requestScope.comFuncs},
			            rownumbers:true,
					    frozenColumns:[[
			                {field:'ck',checkbox:true},
			                {title:'menuId',field:'menuId',width:10,sortable:true,hidden:true}
					    ]],
					    columns:[[ 
					         {field:'menuName',title:'菜单名称',width:160}
					    ]],
					    toolbar: [{
							id:'del',
							text:'删除',
							iconCls:'icon-del',
							handler:function(){
							 var selected =$('#menuInfos').datagrid('getSelected'); 
							 if(selected){
							     var selections  =$('#menuInfos').datagrid('getSelections');
								 var selectRows = [];
								 for ( var i= 0; i< selections.length; i++) {
						           selectRows.push(selections[i]);
						         }
	                             for(var j =0;j<selectRows.length;j++){
	                               var index = $('#menuInfos').datagrid('getRowIndex',selectRows[j]);
	                               $('#menuInfos').datagrid('deleteRow',index);
	                             }
							 }else{
							   parent.alertBox.showAlert('请选择一条记录操作！','warning');
							 }
						    }
						}]
				});
	    });
	    
	    function serializeForm(){
	        var rows=$('#menuInfos').datagrid('getRows');
	        
		    var param =[]; 
			for(var i=0;i<rows.length;i++){
				param.push(rows[i].menuId);
			}
			
			var jsonInfo ={
				"menuIds":param
		    };
		    
		    return jsonInfo;
	    }
	    
	 </script> 
  </head>
  
  <body class="body-scroll">
    <div class="data-outer">
      <div class="data-div-left" style="width:220px;">  
        <span class="span1">我的菜单：</span>
        <div class="tree-inner-div" style="height: 290px;overflow-y: scroll">
           <ul id="menuTree"></ul>
        </div>
      </div>
      <div class="data-div-right" style="width:230px;"> 
        <span class="span1">常用功能：</span>
        <table id="menuInfos"></table>
      </div>
    </div>
  </body>
</html>
