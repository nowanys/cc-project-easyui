/**
 * 根据条件查询公告
 */
function queryNoticeByCondition(){
     var opt = $('#infos').datagrid('options');
	 var parameter = opt.queryParams;
	 parameter['noticeTitle'] = $('#queryNoticeTitle').val();
	 parameter['cutoffDate'] =$('#queryCutoffDate').datebox('getValue');
	 parameter['status'] = $('#queryStatus').val();
	 $('#infos').datagrid('load');
}

/**
 * 清空查询条件
 */
function clearQueryCondition(){
     $('#queryNoticeTitle').val('');
	 
	 var queryStatusVal=jsutil.getDefAttrVal('queryStatus');
	 $('#queryStatus').val(queryStatusVal);
	 $('#statusSelect').combobox('setValue',queryStatusVal);
	 
	 $('#queryCutoffDate').datebox('setValue','');
}

/**
 * 重置查询条件
 */
function resetQueryCondition(){
     clearQueryCondition();
     queryNoticeByCondition();
}

/**
 * 添加系统公告
 */
function sysNoticeAdd(){
  var sysNoticeDialog=parent.easyUI.modalDialog({
    title:'添加',
    iconCls:'icon-add',
    width:735,
    height:525,
    url:mUrl.preSysNoticeAdd,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = sysNoticeDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultSerializeReq(
            	  mUrl.saveSysNotice,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
			            sysNoticeDialog.dialog('destroy');
			            queryNoticeByCondition();
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
function sysNoticeView(){
  var id;
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.noticeId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var sysNoticeDialog=parent.easyUI.modalDialog({
    title:'查看',
    iconCls:'icon-view',
    width:735,
    height:525,
    url:mUrl.preSysNoticeView+id,
    buttons:[{
		text:'关闭',
		iconCls:'icon-close',
		handler:function(){
			sysNoticeDialog.dialog('destroy');
		}
	}]
  });
}

/**
 * 编辑系统公告
 */
function sysNoticeEdit(){
  var id;
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.noticeId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var sysNoticeDialog=parent.easyUI.modalDialog({
    title:'编辑',
    iconCls:'icon-edit',
    width:735,
    height:525,
    url:mUrl.preSysNoticeEdit+id,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = sysNoticeDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultSerializeReq(
                	  mUrl.updateSysNoticeById,
    			      jsonInfo,
    			      function(data){
    			        if(data.resultType=="success"){
    			            parent.alertBox.showAlert(data.resultMsg,'info');
    			            sysNoticeDialog.dialog('destroy');
    			            queryNoticeByCondition();
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
 * 根据id删除系统公告
 */
function delSysNoticeById(){
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
         parameter.push(selecteds[i].noticeId)
      }

 	  parameter.join(",");
 	  
 	  jsutil.notDataReq(
 		 mUrl.deleteSysNoticeByIds+parameter,
		 function(data){
			if(data.resultType=="success"){
			    parent.alertBox.showAlert(data.resultMsg,'info');
			    queryNoticeByCondition();
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