/*
 * 页面初始化
 */
$(function () {
	
	//退出系统
	$('#exitSys').click(function() {
         exitSystem();
    });
    
    //个性化
    $('#individuation').click(function() {
         individualization();
    });
    
    //初始化个性化菜单
    initIndividuationMenu();
   
    
});

/**
 *  初始化个性化菜单
 */
function initIndividuationMenu(){
   //根据个性化初始化用户菜单
    var individuationMenuVal = $.cookie('INDIVIDUATION_MENU_TYPE');
	if(individuationMenuVal){
	  $('#westLayout').empty();
	  if(individuationMenuVal==1){
	    $('#westLayout').html('<div id="menus"></div>');
	    initAccordionUserMenu();
	  }else if(individuationMenuVal==2){
	    $('#westLayout').html('<ul id="menuTree" style="margin: 12px"></ul>');
	    initUserMenu();
	  }
	}else{
		 $('#westLayout').html('<div id="menus"></div>');
		 initAccordionUserMenu();
	}
}

/**
 * 退出系统
 */
function exitSystem(){
   $.messager.confirm('提示','您确定要退出吗?',function(r){
		if(r){
		  window.location.href=mUrl.exitLogin;
		}
	});
}


/*
 * 初始化用户菜单
 */
function initUserMenu(){
   jsutil.notDataReq(
		 mUrl.queryUserMenuByRoleId,
		 function(data){
		   if(data.resultType=="success"){
		      $('#menuTree').tree({
				data:data.resultData,
				animate:true,
				id:'menuId',
				text:'menuName',
				onClick: function(node){
					if(node.attributes!=undefined && node.attributes.attr1!=undefined && node.attributes.attr1!=null && node.attributes.attr1!=''){
		              addTab(node.text,node.attributes.attr1,node.iconCls);
			        }else{
			          if(node.state=='closed'){
			             $(this).tree('expand',node.target);
			          }else{
			             $(this).tree('collapse',node.target);
			          }
			        }		
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

/*
 * 初始化用户菜单
 */
function initAccordionUserMenu(){
   $('#menus').accordion({
      fit:true,
      border:false,
      animate:true,
      plain:true
   });
   
   jsutil.notMaskDataReq(
		 mUrl.queryUserAccordionMenuByRoleId,
		 function(data){
		   if(data.resultType=="success"){
		        $.each(data.resultData,function(i,n){
                    $('#menus').accordion('add',{
                        title: n.menuName,
                        id:n.menuId,
                        iconCls:n.iconCls,
                        selected: true,
                        content:'<ul id="accordion'+n.menuId+'" style="margin: 12px;overflow-x: hidden"></ul>',
                    });
                });
                
                $('#menus').accordion({
		            onSelect: function(title,index){
		               var menuPanel = $('#menus').accordion('getSelected');
		               var menuId = menuPanel.panel('options').id;
		               jsutil.notMaskDataReq(
		            		 mUrl.queryUserAccordionTreeMenuById+menuId,
							 function(data){
							   if(data.resultType=="success"){
							      $('#accordion'+menuId).tree({
									data:data.resultData,
									animate:true,
									onClick: function(node){
										if(node.attributes!=undefined && node.attributes.attr1!=undefined && node.attributes.attr1!=null && node.attributes.attr1!=''){
							              addTab(node.text,node.attributes.attr1,node.iconCls);
								        }else{
								          if(node.state=='closed'){
								             $(this).tree('expand',node.target);
								          }else{
								             $(this).tree('collapse',node.target);
								          }
								        }	
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
		        });
		        
		   }else if(data.resultType=="failure"){
		      parent.alertBox.showAlert(data.resultMsg,'warning');
		   }else{
			  parent.alertBox.showAlert(data.resultMsg,'error');
		   }
		 }
	);
}



/*
 * 添加页签
 */
function addTab(title, page,icon) {
    //先判断是否存在标题为title的选项卡
	var tab = $("#tab").tabs("exists", title);
	
	//若存在，则直接打开
	if (tab) {
		$("#tab").tabs("select", title);
	//否则创建
	} else {
	    //超过10个页签提示
	    if($('#tab').tabs('tabs').length>9){
	      parent.alertBox.showAlert("已打开10个页签，请关闭没用的页签！",'warning');
	      return ;
	    }
	    
		$("#tab").tabs("add", {
		   title:title, 
		   content:"<iframe width='100%' height='99.5%'  id='iframe' frameborder='0' scrolling='no'  src='" + page + "'></iframe>", 
		   closable:true,
		   icon:icon
		 });
	}
}

/*
 * 刷新当前tab
 */
function refreshCurrentTab(){
    var currentPanel = $('#tab').tabs('getSelected');
    var currentPanelUrl = currentPanel.find('iframe').attr('src');
    currentPanel.find('iframe').attr('src',currentPanelUrl);
}

/**
 * 关闭tab
 */
function closeTab(action){
    var allTabs = $('#tab').tabs('tabs');
    var currentTab =$('#tab').tabs('getSelected');
    var allTabTitle = [];
    
    
    $.each(allTabs,function(i,n){
        var title =$(n).panel('options').title;
        if(title!='主页'){
          allTabTitle.push(title);
        }
    })
    


    switch (action) {
        case "closeCurrent":
            var currtabTitle = currentTab.panel('options').title;
            if(currtabTitle!="主页"){
              $('#tab').tabs('close', currtabTitle);
            }
            break;
        case "closeAll":
            $.each(allTabTitle, function (i, n) {
                    $('#tab').tabs('close', n);
            });
            break;
        case "closeOther":
            var currtabTitle = currentTab.panel('options').title;
            $.each(allTabTitle, function (i, n) {
                if (n != currtabTitle){
                    $('#tab').tabs('close', n);
                }
            });
            
            $("#tab").tabs("select", currtabTitle);
            break;
    }
}

/**
 * 切换当前用户部门
 */
function changeCurrentDept(value){
  jsutil.notDataReq(
     mUrl.changeCurrentUserDept+value,
     function(data){
        if(data.resultType=="success"){
           parent.alertBox.showAlert(data.resultMsg,'info',function(){
              window.location.reload();
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
 * 切换当前用户角色
 */
function changeCurrentRole(value){
  jsutil.notDataReq(
	 mUrl.changeCurrentUserRole+value,
     function(data){
        if(data.resultType=="success"){
           parent.alertBox.showAlert(data.resultMsg,'info',function(){
              window.location.reload();
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
 * 个性化
 */
function individualization(){
      var individuationDialog=parent.easyUI.modalDialog({
		title:'个性化',
		iconCls:'icon-individuation',
		width:375,
		height:225,
		url:mUrl.preIndividuation,
		buttons:[{
			text:'提交',
			iconCls:'icon-ok',
			handler:function(){
			      var iframeObj = individuationDialog.find('iframe').get(0).contentWindow;
	              iframeObj.individuationConfig();
	              individuationDialog.dialog('destroy');
	              parent.alertBox.showAlert('个性化成功！','info',function(){
		               window.location.reload();
		          });
			}
		},{
			text:'关闭',
			iconCls:'icon-close',
			handler:function(){
				individuationDialog.dialog('destroy');
			}	
		 }]
	   });
}

/**
 * 获取导航布局宽度
 * @returns
 */
function westLayoutWidth(){
	return $('#westLayout').width();
}