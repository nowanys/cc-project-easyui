<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>角色按钮分配</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	<script type="text/javascript" charset="utf-8" src="common/js/easyUI/extend/datagrid-groupview.js"></script>
	
	
    <script type="text/javascript">
      var id='${requestScope.roleId}';
	  $(function(){
	        //刷新数据表格
            $(window).resize(function(){
               $('#buttonInfos').datagrid('resize');
            });
    
            //数据表格
			$('#buttonInfos').datagrid({
		            method:'POST', 
		            singleSelect:false, 
		            height:'278',
		            fit : false, 
		            border : true,
		            fitColumns: false, 
		            striped: true, 
		            collapsible:true, 
		            sortName: 'buttonId', 
		            sortOrder: 'desc', 
		            remoteSort: true, 
		            idField:'buttonId', 
		            queryParams:{}, 
		            data:${requestScope.data},
		            rownumbers:true,
		            view:groupview,
		            groupField:'menuId',
		            groupFormatter:function(value,rows){
		              if(rows.length>0){
		                return rows[0].menuName;
		              }else{
		                return "";
		              }
                    },
		            rowStyler: function(index,row){
					  if((index%2)==0){
			             return 'background-color:#ffffff;color:#333';
			          }else{
			             return 'background-color:#f5f5f5;color:#333';
			          }
		            },
				    frozenColumns:[[
		                {field:'ck',checkbox:true},
		                {title:'buttonId',field:'buttonId',width:10,sortable:true,hidden:true}
				    ]],
				    columns:[[
				         {field:'buttonName',title:'按钮名称',width:150},
				         {field:'buttonType',title:'按钮类别',width:100,
				         formatter:function(value){ 
				           <c:forEach var="dataDic" items="${requestScope.BUTTON_CATEGORY_TYPE}">  
				               if(value=='${dataDic.dicItemValue}'){
				                 return '${dataDic.dicItemName}';
		                       }
		                    </c:forEach>
				         }}
				    ]],
				    onLoadSuccess:function(data){ 
                      $('#buttonInfos').datagrid('clearSelections'); 
                      
                           var rows = data.rows;
                           
                           jsutil.notDataReq(
                        	  mUrl.queryRoleButtonMappingById+id,
						      function(data){
						        if(data.resultType=="success"){
						            if(null!=rows && null!=data.resultData && data.resultData.length>0){
								          for(var i=0;i<rows.length;i++){
									         for(var j=0;j<data.resultData.length;j++){
									           if(rows[i].buttonId==data.resultData[j].buttonId){
									             $('#buttonInfos').datagrid('checkRow',i);
									           }
									         }
									      }
						             }
						        }else if(data.resultType=="failure"){
						            parent.alertBox.showAlert(data.resultMsg,'warning');
						        }else{
						            parent.alertBox.showAlert(data.resultMsg,'error');
						        }
						      }
						   );
                    }
			 });
	    });
	    
	    function serializeForm(){
	        var selected = $('#buttonInfos').datagrid('getSelections');  
	        
		    var param =[]; 
			for(var i=0;i<selected.length;i++){
				param.push(selected[i].buttonId);
			}
			
			var jsonInfo ={
				"roleId":id,
				"buttonIds":param
		    };
		    
		    return jsonInfo;
	    }
	    
	 </script> 
  </head>
  
  <body class="body-scroll">
      <div class="data-outer">  
        <table id="buttonInfos"></table>
      </div> 
  </body>
</html>
