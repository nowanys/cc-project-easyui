var typeId=-1;
var typeCode="";
var itemId=-1;

/**
 * 初始化方法
 */
$(function(){
  
  //初始化查询数据字典分类
  queryDataDicType();
  
  
});



/**
 * 查询数据字典分类
 */
function queryDataDicType(){
        var queryDicCategory=$('#queryDicCategory').val();
		jsutil.notDataReq(
		 mUrl.queryDataDicTypeList+queryDicCategory,
		 function(data){
			$('#dataDicList').empty();
			if(data.resultType=="success"){
			   if(null!=data.resultData && ""!=data.resultData && undefined!=data.resultData && data.resultData.length>0){
					  var liHtml="";
					  for(var i=0;i<data.resultData.length;i++){
					    liHtml+="<li>";
					    liHtml+="<input type='checkbox' name='dataDicTypeCk' value='"+data.resultData[i].dicTypeId+"' style='height:20px;float:left'/>";
					    liHtml+="<div  onclick='queryDataDicItemById(\""+data.resultData[i].dicTypeId+"\",\""+data.resultData[i].dicTypeCode+"\",this);'>";
					    liHtml+="<span class='"+data.resultData[i].iconCls+" icon-span'></span>";
					    liHtml+="<span style='margin-left:3px;height:20px;'>"+data.resultData[i].dicTypeName+"</span>"; 
					    liHtml+="</div></li>";
					    
					  }
					       
					 $('#dataDicList').html(liHtml);
					       
					 //添加鼠标移入与移出事件
					 $('#dataDicList > li').hover(
				        function(){
				          $(this).addClass('tree-node-hover');
				        },function(){
				          $(this).removeClass('tree-node-hover');
				        }
				     );
				       
				}        
			 
			}else if(data.resultType=="failure"){
			    parent.alertBox.showAlert(data.resultMsg,'warning');
			}else{
			    parent.alertBox.showAlert(data.resultMsg,'error');
			}
	     }
	  );
}
		
/**
 * 查询数据字典明细
 */
function queryDataDicItemById(dataDicTypeId,dataDicTypeCode,node){
    typeId=dataDicTypeId;
    typeCode=dataDicTypeCode;
	$('#dataDicList > li').removeClass('tree-node-hover');
	$('#dataDicList > li').removeClass('tree-node-selected');
	$(node).parent().addClass('tree-node-selected');
	
	var opt = $('#infos').treegrid('options');
	opt.url= mUrl.queryDataDicItemByIdPaging;
	var parameter = opt.queryParams;
    parameter['dicTypeId'] = typeId;
    parameter['status'] = $('#queryStatus').val();
    $('#infos').treegrid('load');
}

/**
 * 查询数据字典明细
 */
function queryDataDicItemByStatus(){
    if(typeId==-1 || typeId=='-1' || typeId==null || typeId==undefined){
        parent.alertBox.showAlert('请选择数据字典分类！','warning');
    }else{
        var opt = $('#infos').treegrid('options');
		opt.url= mUrl.queryDataDicItemByIdPaging;
		var parameter = opt.queryParams;
	    parameter['dicTypeId'] = typeId;
	    parameter['status'] = $('#queryStatus').val();
	    $('#infos').treegrid('load');
    }
}


/**
 * 弹出数据字典分类添加窗口
 */
function dataDicTypeAdd(){
   var queryDicCategory=$('#queryDicCategory').val();
  var dataDicTypeDialog=parent.easyUI.modalDialog({
    title:'添加',
    iconCls:'icon-add',
    width:460,
    height:200,
    url:mUrl.preDataDicTypeAdd+queryDicCategory,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = dataDicTypeDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.saveDataDicType,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    dataDicTypeDialog.dialog('destroy');
					    queryDataDicType();
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
 * 弹出数据字典分类编辑窗口
 */
function dataDicTypeEdit(){
  var dataDicTypeCkSize = $("input[name='dataDicTypeCk']:checked").length;
  
  if(dataDicTypeCkSize==0){
     parent.alertBox.showAlert('请选择一条记录操作！','warning');
     return ;
  }
  
  if(dataDicTypeCkSize>1){
     parent.alertBox.showAlert('只能选择一条记录操作！','warning');
     return ;
  }
  
  var ckId = $("input[name='dataDicTypeCk']:checked").eq(0).val();
  
  var dataDicTypeDialog=parent.easyUI.modalDialog({
    title:'编辑',
    iconCls:'icon-edit',
    width:460,
    height:220,
    url:mUrl.preDataDicTypeEdit+ckId,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = dataDicTypeDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.updateDataDicTypeById,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    dataDicTypeDialog.dialog('destroy');
					    queryDataDicType();
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
 * 根据id删除数据字典分类
 */
function delDicTypeById(){
  if($("input[name='dataDicTypeCk']:checked").length==0){
     parent.alertBox.showAlert('请选择一条记录操作！','warning');
     return ;
  }
  
  parent.$.messager.confirm('提示', '确定要删除选中的记录吗?',function(r){
    if(r){
      var parameter =[];
	  $("input[name='dataDicTypeCk']:checked").each(function(){
	     parameter.push($(this).val())
	  })
	  parameter.join(",");
	  
	  jsutil.notDataReq(
		 mUrl.deleteDataDicTypeByIds+parameter,
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
 * 弹出数据字典明细添加窗口
 */
function dataDicItenAdd(){
 if(typeId==-1 || typeId=='-1' || typeId==null || typeId==undefined){
        parent.alertBox.showAlert('请选择数据字典分类！','warning');
 }else{
  var status=$('#queryStatus').val();
  var dataDicItemDialog=parent.easyUI.modalDialog({
    title:'添加',
    iconCls:'icon-add',
    width:460,
    height:392,
    url:mUrl.preDataDicItemAdd+typeId+'&dicTypeCode='+typeCode+'&status='+status,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = dataDicItemDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.saveDataDicItem,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    dataDicItemDialog.dialog('destroy');
					    queryDataDicItemByStatus();
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
}

/**
 * 弹出数据字典明细查看窗口
 */
function dataDicItenView(){
  var selected =$('#infos').treegrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').treegrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    itemId=selected.dicItemId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var dataDicItemDialog=parent.easyUI.modalDialog({
    title:'查看',
    iconCls:'icon-view',
    width:460,
    height:392,
    url:mUrl.preDataDicItemView+itemId+'&dicTypeCode='+typeCode,
    buttons:[{
		text:'关闭',
		iconCls:'icon-close',
		handler:function(){
		   dataDicItemDialog.dialog('destroy');
		}
	 }]
   });
}

/**
 * 弹出数据字典明细编辑窗口
 */
function dataDicItenEdit(){
  var selected =$('#infos').treegrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').treegrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    itemId=selected.dicItemId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var dataDicItemDialog=parent.easyUI.modalDialog({
    title:'编辑',
    iconCls:'icon-edit',
    width:460,
    height:392,
    url:mUrl.preDataDicItemEdit+itemId+'&dicTypeCode='+typeCode,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = dataDicItemDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.updateDataDicItemById,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    dataDicItemDialog.dialog('destroy');
					    queryDataDicItemByStatus();
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
 * 根据id删除数据字典明细
 */
function delDicItemById(){
  var selected =$('#infos').treegrid('getSelected'); 
  
  if(!selected){
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  parent.$.messager.confirm('提示', '确定要删除选中的记录吗?',function(r){
    if(r){
      var parameter =[];
      
      var selecteds =$('#infos').treegrid('getSelections');  
      for(var i=0;i<selecteds.length;i++){
         parameter.push(selecteds[i].dicItemId)
      }

 	  parameter.join(",");
 	  
 	  jsutil.notDataReq(
 		 mUrl.deleteDataDicItemByIds+parameter,
		 function(data){
			if(data.resultType=="success"){
			    parent.alertBox.showAlert(data.resultMsg,'info');
			    queryDataDicItemByStatus();
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

//上移
function dicItemMoveUp(){
  var selected =$('#infos').treegrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').treegrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    itemId=selected.dicItemId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var selecteds =$('#infos').treegrid('getSelections');  
  var dicItemId = selecteds[0].dicItemId;
  var sortNum = selecteds[0].sortNum;
  var status = $('#queryStatus').val();
  
  var jsonInfo ={
     "dicItemId":dicItemId,
     "sortNum":sortNum,
     "status":status,
     "dicTypeCode":typeCode,
     "moveSign":"up"
  };
  
  
  jsutil.defaultReq(
	  mUrl.updateDataDicItemSortNumById,
	  JSON.stringify(jsonInfo),
	  function(data){
		if(data.resultType=="success"){
			parent.alertBox.showAlert(data.resultMsg,'info');
			queryDataDicItemByStatus();	   
		}else if(data.resultType=="failure"){
			parent.alertBox.showAlert(data.resultMsg,'warning');
		}else{
			parent.alertBox.showAlert(data.resultMsg,'error');
		}
	 }
   );
 
}

//下移
function dicItemMoveDown(){
  var selected =$('#infos').treegrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').treegrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    itemId=selected.dicItemId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var selecteds =$('#infos').treegrid('getSelections');  
  var dicItemId = selecteds[0].dicItemId;
  var sortNum = selecteds[0].sortNum;
  var status = $('#queryStatus').val();
  
  var jsonInfo = {
     "dicItemId":dicItemId,
     "sortNum":sortNum,
     "status":status,
     "dicTypeCode":typeCode,
     "moveSign":"down"
  };
  
  jsutil.defaultReq(
	  mUrl.updateDataDicItemSortNumById,
	  JSON.stringify(jsonInfo),
	  function(data){
		if(data.resultType=="success"){
			parent.alertBox.showAlert(data.resultMsg,'info');
			queryDataDicItemByStatus();	   
		}else if(data.resultType=="failure"){
			parent.alertBox.showAlert(data.resultMsg,'warning');
		}else{
			parent.alertBox.showAlert(data.resultMsg,'error');
		}
	 }
   );
  
}

/**
 * 重新载入数据字典信息
 */
function reloadDataDictionary(){
  parent.$.messager.confirm('提示','确定要重新载入数据字典信息吗？',function(r){
    if(r){
      jsutil.notDataReq(
    	 mUrl.reloadDataDic,
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
 * 数据字典明细角色分配 
 */
function dicItemRoleDistribute(){
  var selected =$('#infos').treegrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').treegrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.dicItemId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var userDialog=parent.easyUI.modalDialog({
    title:'角色分配',
    iconCls:'icon-config',
    width:747,
    height:437,
    url:mUrl.preDataDicItemRoleDistribute+id,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = userDialog.find('iframe').get(0).contentWindow;
		      
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultSerializeReq(
            	  mUrl.saveDataDicItemRoleMapping,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    userDialog.dialog('destroy');
					    $('#infos').treegrid('clearSelections');
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

