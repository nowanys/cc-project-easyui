var deptId='1';
var id='-1';

initDeptTree();


/*
 * 根据条件查询组织
 */
function queryDeptByCondition(){
   var queryStatus=$('#queryStatus').val();
   if(queryStatus==1 && (deptId==-1 || deptId=='-1' || deptId==undefined)){
       parent.alertBox.showAlert('请选择组织结构！','warning');
       return ;
   }
   
   var opt = $('#infos').datagrid('options');
   opt.url= mUrl.queryDeptByConditionPaging;
   var parameter = opt.queryParams;
   parameter['queryStatus'] = queryStatus;
   parameter['deptId'] = deptId;
   parameter['deptName'] = $('#queryDeptName').val();
   $('#infos').datagrid('load');

}

/**
 * 清空查询条件
 */
function clearQueryCondition(){
     $('#queryDeptName').val('');
     
     var queryStatusVal=jsutil.getDefAttrVal('queryStatus');
	 $('#queryStatus').val(queryStatusVal);
	 $('#statusSelect').combobox('setValue',queryStatusVal);
}

/**
 * 重置查询条件
 */
function resetQueryCondition(){
     clearQueryCondition();
     initDeptItem();
}

/**
 * 初始化组织明细
 */
function initDeptItem(){
         var opt = $('#infos').datagrid('options');
		 opt.url= mUrl.queryDeptByConditionPaging;
		 var parameter = opt.queryParams;
		 parameter['queryStatus'] = '1';
		 parameter['deptId'] = deptId;
		 parameter['deptName'] = $('#deptName').val();
		 $('#infos').datagrid('load');
}

/*
 * 初始化组织结构
 */
function initDeptTree(){
  jsutil.notDataReq(
	 mUrl.queryDeptList,
     function(data){
		    if(data.resultType=="success"){
			    $('#deptTree').tree({
					data:data.resultData,
					animate:true,
					onClick: function(node){
					  deptId=node.id;
					  $('#queryStatus').val('1');
					  queryDeptByCondition();
					}
				 });         
			}else if(data.resultType=="failure"){
			     parent.alertBox.showAlert(data.resultMsg,'warning');
			}else{
			     parent.alertBox.showAlert(data.resultMsg,'error');
			}
			
		 }
   );
}


/**
 * 弹出组织添加窗口
 */
function deptAdd(){
  var deptAddDialog=parent.easyUI.modalDialog({
    title:'添加',
    iconCls:'icon-add',
    width:460,
    height:495,
    url:mUrl.preDeptAdd+deptId,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = deptAddDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.saveDept,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    deptAddDialog.dialog('destroy');
					    window.location.reload();
			        }else if(data.resultType=="failure"){
			            parent.alertBox.showAlert(data.resultMsg,'warning');
			        }else{
			            parent.alertBox.showAlert(data.resultMsg,'error');
			        }
			      }
			  );
		}
	}]
  });
}

/**
 * 查看部门
 */
function deptView(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.deptId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var deptDialog=parent.easyUI.modalDialog({
    title:'查看',
    iconCls:'icon-view',
    width:470,
    height:495,
    url:mUrl.preDeptView+id,
    buttons:[{
		text:'关闭',
		iconCls:'icon-close',
		handler:function(){
		 	deptDialog.dialog('destroy');
		}
	}]
  });
}

/**
 * 弹出组织编辑窗口
 */
function deptEdit(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.deptId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var deptEditDialog=parent.easyUI.modalDialog({
    title:'编辑',
    iconCls:'icon-edit',
    width:460,
    height:495,
    url:mUrl.preDeptEdit+id,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = deptEditDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.updateDeptByDeptId,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    deptEditDialog.dialog('destroy');
					    window.location.reload();
			        }else if(data.resultType=="failure"){
			            parent.alertBox.showAlert(data.resultMsg,'warning');
			        }else{
			            parent.alertBox.showAlert(data.resultMsg,'error');
			        }
			      }
			  );
		}
	}]
  });
}

/**
 * 根据id删除组织
 */
function delDeptById(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(!selected){
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  parent.$.messager.confirm('提示', '确定要删除选中的记录吗?',function(r){
    if(r){
      var parameter =[];
      
      var selecteds =$('#infos').datagrid('getSelections');  
      for(var i=0;i<selecteds.length;i++){
         parameter.push(selecteds[i].deptId)
      }

 	  parameter.join(",");
 	  
 	  jsutil.notDataReq(
 		 mUrl.deleteDeptByIds+parameter,
		 function(data){
			if(data.resultType=="success"){
			    parent.alertBox.showAlert(data.resultMsg,'info');
			    window.location.reload();
		    }else if(data.resultType=="failure"){
			    parent.alertBox.showAlert(data.resultMsg,'warning');
			}else{
			    parent.alertBox.showAlert(data.resultMsg,'error');
			}
		 }
	  );
    }
  });
  
}



/**
 * 弹出组织移动窗口
 */
var deptMoveDialog;
 
function deptMove(){
   var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.deptId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  deptMoveDialog=parent.easyUI.modalDialog({
    title:'移动',
    iconCls:'icon-move',
    width:340,
    height:430,
    url:mUrl.preSingleDeptSelect+"deptSelectCallBackFunc"
  });
}

/**
 * 组织移动回调处理
 */
function  deptSelectCallBackFunc(node){
  
   if(id==node.id){
     parent.alertBox.showAlert('不能将自己移动到自己下！','warning');
     return ;
   }
    
   if(id==node.parentId){
     parent.alertBox.showAlert('不能将父组织移动到子组织下！','warning');
     return ;
   }
   
   var selected =$('#infos').datagrid('getSelected');
   
   parent.$.messager.confirm('提示', '确定要将“'+selected.deptName+'”移动到“'+node.text+'”下吗?',function(r){
    if(r){
       var jsonInfo ={
          "deptId":id,
          "parentDeptId":node.id
       };
       
       jsutil.defaultSerializeReq(
    	  mUrl.updateDeptParentDeptIdById,
		  jsonInfo,
		  function(data){
		    if(data.resultType=="success"){
			    parent.alertBox.showAlert(data.resultMsg,'info');
				deptMoveDialog.dialog('destroy');
			    window.location.reload();
			}else if(data.resultType=="failure"){
			    parent.alertBox.showAlert(data.resultMsg,'warning');
			}else{
			    parent.alertBox.showAlert(data.resultMsg,'error');
			}
		  }
		);
     }
   });
}

/**
 * 组织用户查询
 */
function deptUsersQuery(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.deptId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  parent.easyUI.modalDialog({
    title:'组织用户',
    iconCls:'icon-admin',
    width:615,
    height:350,
    url:mUrl.preDeptUsersQuery+id
  });
}

