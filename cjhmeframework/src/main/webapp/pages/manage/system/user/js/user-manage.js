var id=-1;

/**
 * 根据条件查询用户
 */
function queryUserByCondition(){
     var opt = $('#infos').datagrid('options');
	 opt.url= mUrl.queryUserByConditionPaging;
	 var parameter = opt.queryParams;
	 parameter['userCode'] = $('#queryUserCode').val();
	 parameter['userName'] = $('#queryUserName').val();
	 parameter['realName'] = $('#queryRealName').val();
	 parameter['status'] = $('#queryStatus').val();
	 $('#infos').datagrid('load');
}

/**
 * 清空查询条件
 */
function clearQueryCondition(){
     $('#queryUserCode').val('');
	 $('#queryUserName').val('');
	 $('#queryRealName').val('');
	 
	 var queryStatusVal=jsutil.getDefAttrVal('queryStatus');
	 $('#queryStatus').val(queryStatusVal);
	 $('#statusSelect').combobox('setValue',queryStatusVal);
}

/**
 * 重置查询条件
 */
function resetQueryCondition(){
     clearQueryCondition();
     queryUserByCondition();
}

/**
 * 添加用户
 */
function userAdd(){
  var userDialog=parent.easyUI.modalDialog({
    title:'添加',
    iconCls:'icon-add',
    width:470,
    height:550,
    url:mUrl.preUserAdd,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = userDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.saveUser,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    userDialog.dialog('destroy');
					    queryUserByCondition();
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
 * 查看用户
 */
function userView(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.userId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var userDialog=parent.easyUI.modalDialog({
    title:'查看',
    iconCls:'icon-view',
    width:470,
    height:550,
    url:mUrl.preUserView+id,
    buttons:[{
		text:'关闭',
		iconCls:'icon-close',
		handler:function(){
		 	userDialog.dialog('destroy');
		}
	}]
  });
}

/**
 * 编辑用户
 */
function userEdit(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.userId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var userDialog=parent.easyUI.modalDialog({
    title:'编辑',
    iconCls:'icon-edit',
    width:470,
    height:550,
    url:mUrl.preUserEdit+id,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = userDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.updateUserById,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    userDialog.dialog('destroy');
					    queryUserByCondition();
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
 * 编辑用户密码
 */
function userPasswordEdit(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.userId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var userDialog=parent.easyUI.modalDialog({
    title:'密码修改',
    iconCls:'icon-pwd-change',
    width:470,
    height:179,
    url:mUrl.preUserPasswordEdit+id,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = userDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.updateUserPasswordById,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    userDialog.dialog('destroy');
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

/**
 * 重置用户密码
 */
function userPasswordReset(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(!selected){
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  parent.$.messager.confirm('提示', '确定要重置选中的用户密码吗?',function(r){
    if(r){
      var parameter =[];
      
      var selecteds =$('#infos').datagrid('getSelections');  
      for(var i=0;i<selecteds.length;i++){
         parameter.push(selecteds[i].userId)
      }

 	  parameter.join(",");
 	  
 	  jsutil.notDataReq(
 		 mUrl.resetUserPwdByIds+parameter,
		 function(data){
			if(data.resultType=="success"){
			    parent.alertBox.showAlert(data.resultMsg,'info');
			    $('#infos').datagrid('clearSelections'); 
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
 * 根据id删除用户
 */
function delUserById(){
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
         parameter.push(selecteds[i].userId)
      }

 	  parameter.join(",");
 	  
 	  jsutil.notDataReq(
 		 mUrl.deleteUserByIds+parameter,
		 function(data){
			if(data.resultType=="success"){
			    parent.alertBox.showAlert(data.resultMsg,'info');
			    queryUserByCondition();
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
 * 用户分配角色
 */
function userRoleDistribute(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.userId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var userDialog=parent.easyUI.modalDialog({
    title:'角色分配',
    iconCls:'icon-config',
    width:675,
    height:475,
    url:mUrl.preUserRoleDistribute+id,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = userDialog.find('iframe').get(0).contentWindow;
		      
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultSerializeReq(
            	  mUrl.saveUserRoleMapping,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    userDialog.dialog('destroy');
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

/**
 * 用户分配部门
 */
function userDeptDistribute(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.userId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var userDialog=parent.easyUI.modalDialog({
    title:'部门分配',
    iconCls:'icon-config',
    width:565,
    height:445,
    url:mUrl.preUserDeptDistribute+id,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = userDialog.find('iframe').get(0).contentWindow;
		      
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultSerializeReq(
            	  mUrl.saveUserDeptMapping,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    userDialog.dialog('destroy');
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


