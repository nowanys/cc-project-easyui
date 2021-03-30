var id=-1;
/**
 * 根据条件查询待办
 */
function queryTaskByCondition(){
     var opt = $('#infos').datagrid('options');
	 var parameter = opt.queryParams;
	 parameter['title'] = $('#queryTitle').val();
	 parameter['status'] = $('#queryStatus').val();
	 $('#infos').datagrid('load');
}

/**
 * 清空查询条件
 */
function clearQueryCondition(){
     $('#queryTitle').val('');
	 $('#queryStatus').val('1');
	 $('#statusSelect').combobox('setValue','1');
}

/**
 * 重置查询条件
 */
function resetQueryCondition(){
     clearQueryCondition();
     queryTaskByCondition();
}

/**
 * 修改待办状态
 */
function updateTaskStatus(){
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.taskId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  parent.$.messager.confirm('提示', '确定要取消待办?',function(r){
    if(r){
 	  jsutil.notDataReq(
 		 mUrl.updateTaskById+id,
		 function(data){
			if(data.resultType=="success"){
			    parent.alertBox.showAlert(data.resultMsg,'info');
			    queryTaskByCondition();
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
 * 查看待办
 */    
function taskView(){
  var taskId;
  var taskUrl;
  var taskParameter;
  
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    taskId=selected.taskId;
	    taskUrl=selected.taskUrl;
	    taskParameter=selected.taskParameter;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  window.location.href=(taskUrl).replace(/\s+/g,"")+"?id="+(taskId).replace(/\s+/g,"")+"&taskParameter="+(taskParameter).replace(/\s+/g,"");
}