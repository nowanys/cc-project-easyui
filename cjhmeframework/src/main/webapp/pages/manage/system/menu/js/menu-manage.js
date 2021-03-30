var mId='1';
var id='-1';
var isQueryAll=false;

initMenuTree();
/**
 * 初始化菜单
 */
function initMenuTree(){
     jsutil.notDataReq(
    	 mUrl.queryMenuList,
		 function(data){
		    if(data.resultType=="success"){
			    $('#menuTree').tree({
					data:data.resultData,
					animate:true,
					onClick: function(node){
					  mId = node.id;
					  queryMenuByCondition();
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
 * 初始化树明细
 */
function initMenuItem(){
         var opt = $('#infos').datagrid('options');
		 opt.url= mUrl.queryMenuByConditionPaging;
		 var parameter = opt.queryParams;
		 parameter['queryStatus'] = '1';
		 parameter['menuId'] = mId;
		 parameter['menuName'] = $('#queryMenuName').val();
		 $('#infos').datagrid('reload');
}


/*
 * 根据条件查询树
 */
function queryMenuByCondition(){
   var queryStatus=$('#queryStatus').val();
   if(queryStatus==1 && (mId==-1 || mId=='-1' || mId==undefined)){
       parent.alertBox.showAlert('请选择菜单结构！','warning');
       return ;
   }
   
   
   var opt = $('#infos').datagrid('options');
   opt.url= mUrl.queryMenuByConditionPaging;
   var parameter = opt.queryParams;
   parameter['queryStatus'] = queryStatus;
   parameter['menuId'] = mId;
   parameter['menuName'] = $('#queryMenuName').val();
   $('#infos').datagrid('load');
   
   if(queryStatus!=2){
     isQueryAll=false;
   }else{
     isQueryAll=true;
   }

}
 
/**
 * 清空查询条件
 */
function clearQueryCondition(){
     mId='1';
     $('#queryMenuName').val('');
     
	 var queryStatusVal=jsutil.getDefAttrVal('queryStatus');
	 $('#queryStatus').val(queryStatusVal);
	 $('#statusSelect').combobox('setValue',queryStatusVal);
	 
}

/**
 * 重置查询条件
 */
function resetQueryCondition(){
     clearQueryCondition();
     initMenuItem();
     
}


/**
 * 弹出菜单添加窗口
 */
function menuAdd(){
  var menuAddDialog=parent.easyUI.modalDialog({
    title:'添加',
    iconCls:'icon-add',
    width:435,
    height:270,
    url:mUrl.preMenuAdd+mId,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = menuAddDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.saveMenu,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    menuAddDialog.dialog('destroy');
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
 * 弹出菜单查看窗口
 */
function menuView(){
   var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.menuId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var menuEditDialog=parent.easyUI.modalDialog({
    title:'查看',
    iconCls:'icon-view',
    width:435,
    height:270,
    url:mUrl.preMenuView+id,
    buttons:[{
		text:'关闭',
		iconCls:'icon-close',
		handler:function(){
		     menuEditDialog.dialog('destroy'); 
		}
	}]
  });
}


/**
 * 弹出菜单编辑窗口
 */
function menuEdit(){
   var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.menuId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var menuEditDialog=parent.easyUI.modalDialog({
    title:'编辑',
    iconCls:'icon-edit',
    width:435,
    height:270,
    url:mUrl.preMenuEdit+id,
    buttons:[{
		text:'提交',
		iconCls:'icon-ok',
		handler:function(){
		      var iframeObj = menuEditDialog.find('iframe').get(0).contentWindow;
		      
		      if(!iframeObj.validSubmit()){
		        return ;
		      }
			  
              var jsonInfo = iframeObj.serializeForm();
              
              jsutil.defaultReq(
            	  mUrl.updateMenuByMenuId,
			      jsonInfo,
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    menuEditDialog.dialog('destroy');
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
 * 根据id删除菜单
 */
function delMenuById(){
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
         parameter.push(selecteds[i].menuId)
      }

 	  parameter.join(",");
 	  
 	  jsutil.notDataReq(
 		 mUrl.deleteMenuByIds+parameter,
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
 * 菜单移动
 */
var menuMoveDialog;
 
function menuMove(){
 var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }else{
	    id=selected.menuId;
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  menuMoveDialog=parent.easyUI.modalDialog({
    title:'移动',
    iconCls:'icon-move',
    width:340,
    height:425,
    url:mUrl.preSingleMenuSelect+"menuSelectCallBackFunc"
  });

}

/**
 * 菜单移动回调处理
 */
function menuSelectCallBackFunc(node){
   if(id==node.id){
     parent.alertBox.showAlert('不能将自己移动到自己下！','warning');
     return ;
   }
    
   if(id==node.parentId){
     parent.alertBox.showAlert('不能将父菜单移动到子菜单下！','warning');
     return ;
   }
   
   var selected =$('#infos').datagrid('getSelected');
   
   parent.$.messager.confirm('提示', '确定要将“'+selected.menuName+'”移动到“'+node.text+'”下吗?',function(r){
    if(r){
       var jsonInfo ={
          "menuId":id,
          "parentMenuId":node.id
       };
       
       jsutil.defaultReq(
    		      mUrl.updateMenuParentMenuIdById,
			      JSON.stringify(jsonInfo),
			      function(data){
			        if(data.resultType=="success"){
			            parent.alertBox.showAlert(data.resultMsg,'info');
					    menuMoveDialog.dialog('destroy');
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

//上移
function menuMoveUp(){
  if(isQueryAll){
      parent.alertBox.showAlert('查询状态为“所有菜单”时不允许上移菜单！','warning');
      return ;	
  }  
  
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var selecteds =$('#infos').datagrid('getSelections');  
  var menuId = selecteds[0].menuId;
  var sortNum = selecteds[0].sortNum;
  
  var jsonInfo ={
     "menuId":menuId,
     "sortNum":sortNum,
     "parentMenuId":mId,
     "moveSign":"up"
  };
  
  jsutil.defaultReq(
	  mUrl.updateMenuSortNumById,
	  JSON.stringify(jsonInfo),
	  function(data){
		if(data.resultType=="success"){
			parent.alertBox.showAlert(data.resultMsg,'info');
			queryMenuByCondition();   
		}else if(data.resultType=="failure"){
			parent.alertBox.showAlert(data.resultMsg,'warning');
		}else{
			parent.alertBox.showAlert(data.resultMsg,'error');
		}
	 }
   );
  
}

//下移
function menuMoveDown(){
  if(isQueryAll){
      parent.alertBox.showAlert('查询状态为“所有菜单”时不允许下移菜单！','warning');
      return ;	
  } 
  
  var selected =$('#infos').datagrid('getSelected'); 
  
  if(selected){
     var selecteds =$('#infos').datagrid('getSelections');    
	 if(selecteds.length>1){
		parent.alertBox.showAlert('只能选择一条记录操作！','warning');
        return ;		 
	 }
  }else{
    parent.alertBox.showAlert('请选择一条记录操作！','warning');
    return ;
  }
  
  var selecteds =$('#infos').datagrid('getSelections');  
  var menuId = selecteds[0].menuId;
  var sortNum = selecteds[0].sortNum;
  
  var jsonInfo ={
     "menuId":menuId,
     "sortNum":sortNum,
     "parentMenuId":mId,
     "moveSign":"down"
  };
  
  jsutil.defaultReq(
	  mUrl.updateMenuSortNumById,
	  JSON.stringify(jsonInfo),
	  function(data){
		if(data.resultType=="success"){
			parent.alertBox.showAlert(data.resultMsg,'info');
			queryMenuByCondition();   
		}else if(data.resultType=="failure"){
			parent.alertBox.showAlert(data.resultMsg,'warning');
		}else{
			parent.alertBox.showAlert(data.resultMsg,'error');
		}
	 }
   );
}
