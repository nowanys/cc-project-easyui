var id=-1;
/*
 * 菜单选择
 */
var menuSelectDialog;
 
function queryMenuByCondition(){
  menuSelectDialog=parent.easyUI.modalDialog({
    title:'选择',
    iconCls:'icon-select',
    width:340,
    height:425,
    url:mUrl.preSingleMenuSelect+"btnMenuSelectCallBackFunc&isCheckLeaf=Y"
  });

}

/**
 * 菜单回调处理
 */
function btnMenuSelectCallBackFunc(node){
  $('#queryMenuName').val(node.text);
  $('#queryMenuId').val(node.id);
  menuSelectDialog.dialog('destroy');
} 


/**
 * 根据条件查询按钮
 */
function queryButtonByCondition(){
   var opt = $('#infos').datagrid('options');
   var parameter = opt.queryParams;
   parameter['buttonName'] = $('#queryButtonName').val();
   parameter['busniessMark'] = $('#queryBusniessMark').val();
   parameter['menuId'] = $('#queryMenuId').val();
   parameter['status']=$('#queryStatus').val();
   $('#infos').datagrid('load');
}

/**
 * 清空查询条件
 */
function clearQueryCondition(){
   $('#queryButtonName').val('');
   $('#queryMenuId').val('-1');
   $('#queryMenuName').val('所有');
   
   var queryStatusVal=jsutil.getDefAttrVal('queryStatus');
   $('#queryStatus').val(queryStatusVal);
   $('#statusSelect').combobox('setValue',queryStatusVal);
	 
   var queryBusniessMarkVal=jsutil.getDefAttrVal('queryBusniessMark');
   $('#queryBusniessMark').val(queryBusniessMarkVal);
   $('#busniessMarkSelect').combobox('setValue',queryBusniessMarkVal);
}

/**
 * 重置查询条件
 */
function resetQueryCondition(){
    clearQueryCondition();
    queryButtonByCondition();
}


/**
 * 添加按钮
 */
function buttonAdd(){
  var buttonDialog=parent.easyUI.modalDialog({
    title:'添加',
    iconCls:'icon-add',
    width:470,
    height:430,
    url:mUrl.preButtonAdd,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = buttonDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.saveButton,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    buttonDialog.dialog('destroy');
					    queryButtonByCondition();
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
 * 查看按钮
 */
function buttonView(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.buttonId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var buttonDialog=parent.easyUI.modalDialog({
    title:'查看',
    iconCls:'icon-view',
    width:470,
    height:430,
    url:mUrl.preButtonView+id,
    buttons:[{
		text:'关闭',
		iconCls:'icon-close',
		handler:function(){
		     buttonDialog.dialog('destroy');
		}
	}]
  });
}

/**
 * 编辑按钮
 */
function buttonEdit(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.buttonId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var buttonDialog=parent.easyUI.modalDialog({
    title:'编辑',
    iconCls:'icon-edit',
    width:470,
    height:430,
    url:mUrl.preButtonEdit+id,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = buttonDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.updateButtonById,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    buttonDialog.dialog('destroy');
					    queryButtonByCondition();
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
 * 根据id删除按钮
 */
function delButtonById(){
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
         parameter.push(selecteds[i].buttonId)
      }

 	  parameter.join(",");
 	  
 	  jsutil.notDataReq(
 		 mUrl.deleteButtonByIds+parameter,
		 function(data){
			if(data.resultType=="success"){
			    parent.alertBox.showAlert(data.resultMsg,'info');
			    queryButtonByCondition();
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

