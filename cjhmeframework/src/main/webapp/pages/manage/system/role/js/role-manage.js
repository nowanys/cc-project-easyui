var id=-1;

/**
 * 根据条件查询用户
 */
function queryRoleByCondition(){
     var opt = $('#infos').datagrid('options');
	 var parameter = opt.queryParams;
	 parameter['roleCode'] = $('#queryRoleCode').val();
	 parameter['roleName'] = $('#queryRoleName').val();
	 parameter['isSuper'] = $('#queryIsSuper').val();
	 parameter['status'] = $('#queryStatus').val();
	 $('#infos').datagrid('load');
}

/**
 * 清空查询条件
 */
function clearQueryCondition(){
     $('#queryRoleCode').val('');
	 $('#queryRoleName').val('');
	 
	 var queryStatusVal=jsutil.getDefAttrVal('queryStatus');
	 $('#queryStatus').val(queryStatusVal);
	 $('#statusSelect').combobox('setValue',queryStatusVal);
	 
	 var queryIsSuperVal=jsutil.getDefAttrVal('queryIsSuper');
	 $('#queryIsSuper').val(queryIsSuperVal);
	 $('#isSuperSelect').combobox('setValue',queryIsSuperVal);
	 
}

/**
 * 重置查询条件
 */
function resetQueryCondition(){
     clearQueryCondition();
     queryRoleByCondition();
}

/**
 * 添加角色
 */
function roleAdd(){
  var roleDialog=parent.easyUI.modalDialog({
    title:'添加',
    iconCls:'icon-add',
    width:470,
    height:355,
    url:mUrl.preRoleAdd,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = roleDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.saveRole,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    roleDialog.dialog('destroy');
					    queryRoleByCondition();
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
 * 编辑查看
 */
function roleView(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.roleId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var roleDialog=parent.easyUI.modalDialog({
    title:'查看',
    iconCls:'icon-view',
    width:470,
    height:355,
    url:mUrl.preRoleView+id,
    buttons:[{
		text:'关闭',
		iconCls:'icon-close',
		handler:function(){
		 	roleDialog.dialog('destroy');
		}
	}]
  });
}

/**
 * 编辑角色
 */
function roleEdit(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.roleId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var roleDialog=parent.easyUI.modalDialog({
    title:'编辑',
    iconCls:'icon-edit',
    width:470,
    height:355,
    url:mUrl.preRoleEdit+id,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = roleDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.updateRoleById,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    roleDialog.dialog('destroy');
					    queryRoleByCondition();
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
 * 根据id删除角色
 */
function delRoleById(){
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
         parameter.push(selecteds[i].roleId)
      }

 	  parameter.join(",");
 	  
 	  jsutil.notDataReq(
 		 mUrl.deleteRoleByIds+parameter,
		 function(data){
			if(data.resultType=="success"){
			    parent.alertBox.showAlert(data.resultMsg,'info');
			    queryRoleByCondition();
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
 * 角色用户查询
 */
function roleUsersQuery(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.roleId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  parent.easyUI.modalDialog({
    title:'角色用户',
    iconCls:'icon-admin',
    width:613,
    height:350,
    url:mUrl.preRoleUsersQuery+id
  });
}

/**
 * 角色分配菜单
 */
function roleMenuDistribute(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.roleId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var roleDialog=parent.easyUI.modalDialog({
    title:'菜单分配',
    iconCls:'icon-config',
    width:525,
    height:440,
    url:mUrl.preRoleMenuDistribute+id,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = roleDialog.find('iframe').get(0).contentWindow;
		      
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultSerializeReq(
            	  mUrl.saveRoleMenuMapping,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    roleDialog.dialog('destroy');
					    $('#infos').datagrid('clearSelections');
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

//角色按钮分配
function roleButtonDistribute(){
var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.roleId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var roleDialog=parent.easyUI.modalDialog({
    title:'按钮分配',
    iconCls:'icon-config',
    width:382,
    height:400,
    url:mUrl.preRoleButtonDistribute+id,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = roleDialog.find('iframe').get(0).contentWindow;
		      
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultSerializeReq(
            	  mUrl.saveRoleButtonMapping,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    roleDialog.dialog('destroy');
					    $('#infos').datagrid('clearSelections');
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

//角色访问地址分配
function roleAccessUrlDistribute(){
var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.roleId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var roleDialog=parent.easyUI.modalDialog({
    title:'访问地址分配',
    iconCls:'icon-config',
    width:675,
    height:475,
    url:mUrl.preRoleAccessUrlDistribute+id,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = roleDialog.find('iframe').get(0).contentWindow;
		      
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultSerializeReq(
            	  mUrl.saveRoleAccessUrlMapping,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    roleDialog.dialog('destroy');
					    $('#infos').datagrid('clearSelections');
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