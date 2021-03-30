<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>测试</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../manage/common-manage.jsp"></jsp:include>
	<script type="text/javascript" charset="utf-8" src="common/js/easyUI/extend/datagrid-cellediting.js"></script>
	
	<script>
	    $(function(){
	    
	        //刷新数据表格
            $(window).resize(function(){
               $('#main').layout('resize');
               $('#infos').datagrid('resize');
            });
    
            var dataList = {total:6,rows:[
	            {"itemid":1,"productid":"FI-SW-01","productname":"Koi","unitcost":10.00,"status":"P","listprice":36.50,"itemid":"EST-1"},
	            {"itemid":2,"productid":"K9-DL-01","productname":"Dalmation","unitcost":12.00,"status":"P","listprice":18.50,"itemid":"EST-10"},
	            {"itemid":3,"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12.00,"status":"P","listprice":38.50,"itemid":"EST-11"},
	            {"itemid":4,"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12.00,"status":"N","listprice":26.50,"itemid":"EST-12"},
	            {"itemid":5,"productid":"RP-LI-02","productname":"Iguana","unitcost":12.00,"status":"N","listprice":35.50,"itemid":"EST-13"},
	            {"itemid":6,"productid":"FL-DSH-01","productname":"Manx","unitcost":12.00,"status":"P","listprice":158.50,"itemid":"EST-14"},
	            {"itemid":7,"productid":"FL-DSH-01","productname":"Manx","unitcost":12.00,"status":"P","listprice":83.50,"itemid":"EST-15"},
	            {"itemid":8,"productid":"FL-DLH-02","productname":"Persian","unitcost":12.00,"status":"N","listprice":23.50,"itemid":"EST-16"},
	            {"itemid":9,"productid":"FL-DLH-02","productname":"Persian","unitcost":12.00,"status":"P","listprice":89.50,"itemid":"EST-17"},
	            {"itemid":10,"productid":"AV-CB-01","productname":"Amazon Parrot","unitcost":92.00,"status":"N","listprice":63.50,"itemid":"EST-18"}
	        ]};
            //数据表格
			$('#infos').datagrid({
				    title:'datagrid列表',
				    iconCls:'icon-view', 
		            method:'POST', 
		            singleSelect:false, 
		            //height:'495',
		            fit : true, 
		            border : true,
		            fitColumns: true, 
		            striped: true, 
		            collapsible:true, 
		            url:'',
		            data:dataList,
		            sortName: 'itemid', 
		            sortOrder: 'desc', 
		            remoteSort: true, 
		            idField:'itemid', 
		            queryParams:{'deptId':'1','queryStatus':'1'}, 
		            pagination:true, 
		            rownumbers:true,
		            pageSize : 15,
					pageList : [15, 30, 45, 60, 75,90,100,150,200,300,500,1000],
				    frozenColumns:[[
		                {field:'ck',checkbox:true},
		                {title:'itemid',field:'itemid',width:10,sortable:true,hidden:true}
				    ]],
				    columns:[[ 
				         {field:'productid',title:'下拉',width:150,editor:'combobox'},
				         {field:'listprice',title:'文本',width:170,
				            editor:{
				              type:'textbox',
				              options:{
				                editable:false,
				                buttonText:'添加', 
				                onClickButton:function(){
				                  $('#dlg').dialog({
						                title: '对话框',
						                iconCls: "icon-edit",
						                collapsible: true,
						                minimizable: true,
						                maximizable: true,
						                resizable: true,
						                width: 300,
						                height: 120,
						                modal: true,
						                buttons:[{
											text:'提交',
											handler:function(){
											  var unitcostTxt=$('#unitcostTxt').val();
											  var currentEditRowIndex=$('#infos').datagrid('getCurrentEditRowIndex');
											  $('#infos').datagrid('updateRow',{
														index: currentEditRowIndex,
														row: {
															unitcost: unitcostTxt
														}
										      });
										      
										      $('#dlg').dialog('close');
											}
										}]
						          });
				                }
				              }
				            }
				         },
				         {field:'unitcost',title:'数字',width:150,
				             editor:{
				                type:'numberbox',
				                options:{
				                   precision:6
				                 }
				             }
				         },
			       		 {field:'status',title:'单选',width:150,editor:{type:'checkbox',options:{on:'Y',off:'N'}}},
			       		 {field:'createTime',title:'时间',width:150,editor:{type:'datetimebox'}}
				    ]],
				    toolbar: [{
						iconCls: 'icon-view',
						text:'查看',
						handler: function(){
						var rows = $('#infos').datagrid('getChangeRows');
						for(var i=0;i<rows.length;i++){
						  alert(rows[i].operateFlag);
						}
						}
					},'-',{
						iconCls: 'icon-add',
						text:'添加',
						handler: function(){
						    var len=$('#infos').datagrid('getRows').length;
						    $('#infos').datagrid('insertRow',{
								index: len,	
								row: {
								}
							});
						}
					},'-',{
						iconCls: 'icon-del',
						text:'删除',
						handler: function(){
						  var checkRows=$('#infos').datagrid('getChecked');
						  var delIndex=new Array();
						  for(var i=0;i<checkRows.length;i++){
						    delIndex.push(checkRows[i]);
						  }
						 
						  for(var i=0;i<delIndex.length;i++){
						    var index=$('#infos').datagrid('getRowIndex',delIndex[i]);
						    $('#infos').datagrid('deleteRow',index);
						  }
						}
					}],
				    onLoadSuccess:function(){ 
                      $('#infos').datagrid('clearSelections'); 
                    },
                    onEndEdit:function(index,data,changer){
                       var currentField=$('#infos').datagrid('getCurrentEditField');
                       var val=changer[currentField];
                       
                       if(val!=undefined && val!=''){
                           if(currentField=='unitcost'){
						     $('#infos').datagrid('updateRow',{
										index: index,
										row: {
											listprice: '我是来测试的'
										}
						     })
						   }
                       }
                    }
			 });
			 $('#infos').datagrid('enableCellEditing');
	      });   
	      
	     
	</script>
	
  </head>
  
  <body class="easyui-layout">
      <div data-options="region:'center',border:false" style="padding: 5px;overflow: hidden;">
	     <table id="infos"></table>
	     <div id="dlg">
	          <input type="text" id="unitcostTxt">
		 </div>
	  </div>
  </body>
</html>
