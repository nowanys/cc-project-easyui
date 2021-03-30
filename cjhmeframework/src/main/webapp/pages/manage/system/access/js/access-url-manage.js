var id=-1;

/**
 * 根据条件查询访问地址
 */
function queryAccessUrlByCondition(){
   var opt = $('#infos').datagrid('options');
   var parameter = opt.queryParams;
   parameter['accessUrl'] = $('#queryAccessUrl').val();
   parameter['accessUrlName'] = $('#queryAccessUrlName').val();
   parameter['status']=$('#queryStatus').val();
   $('#infos').datagrid('load');
}

/**
 * 清空查询条件
 */
function clearQueryCondition(){
   $('#queryAccessUrl').val('');
   $('#queryAccessUrlName').val('');
   
   var queryStatusVal=jsutil.getDefAttrVal('queryStatus');
   $('#queryStatus').val(queryStatusVal);
   $('#statusSelect').combobox('setValue',queryStatusVal);
}

/**
 * 重置查询条件
 */
function resetQueryCondition(){
    clearQueryCondition();
    queryAccessUrlByCondition();
}


/**
 * 添加访问地址
 */
function accessUrlAdd(){
  var accessUrldialog=parent.easyUI.modalDialog({
    title:'添加',
    iconCls:'icon-add',
    width:470,
    height:225,
    url:mUrl.preAccessUrlAdd,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = accessUrldialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.saveAccessUrl,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    accessUrldialog.dialog('destroy');
					    queryAccessUrlByCondition();
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
 * 查看访问地址
 */
function accessUrlView(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.accessUrlId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var accessUrldialog=parent.easyUI.modalDialog({
    title:'查看',
    iconCls:'icon-view',
    width:470,
    height:225,
    url:mUrl.preAccessUrlView+id,
    buttons:[{
		text:'关闭',
		iconCls:'icon-close',
		handler:function(){
		     accessUrldialog.dialog('destroy');
		}
	}]
  });
}

/**
 * 编辑访问地址
 */
function accessUrlEdit(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.accessUrlId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var accessUrldialog=parent.easyUI.modalDialog({
    title:'编辑',
    iconCls:'icon-edit',
    width:470,
    height:225,
    url:mUrl.preAccessUrlEdit+id,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = accessUrldialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.updateAccessUrlById,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    accessUrldialog.dialog('destroy');
					    queryAccessUrlByCondition();
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
 * 根据id删除访问地址
 */
function delAccessUrlById(){
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
         parameter.push(selecteds[i].accessUrlId)
      }

 	  parameter.join(",");
 	  
 	  jsutil.notDataReq(
 		 mUrl.deleteAccessUrlByIds+parameter,
		 function(data){
			if(data.resultType=="success"){
			    parent.alertBox.showAlert(data.resultMsg,'info');
			    queryAccessUrlByCondition();
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

