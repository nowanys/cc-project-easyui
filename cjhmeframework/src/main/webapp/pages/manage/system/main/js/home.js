$(function(){
   var westLayoutWidth=parent.westLayoutWidth()+20;
   
   $(window).resize(function(){
	  $('#desktop').portal({width:$(parent.window).width()-westLayoutWidth});
   });
   
   
	     
   $('#desktop').portal({
	   border:false,
	   fit:false,
	   width:$(parent.window).width()-westLayoutWidth
   });
		 
		 
   $('.data-grid tbody tr').hover(
       function(){
           $(this).addClass('data-grid-tr-over'); 
       },function(){
           $(this).removeClass('data-grid-tr-over'); 
       }
   );
         
   initEvent();
   
   initComFuncDisplay();
		 
});
  

      
/**
 * 初始化事件
 */  
function initEvent(){
	
	//窗口改变
	$(window).resize(function(){
		initComFuncDisplay();
	}); 
	
	//修改资料
	$('#userEdit').click(function() {
	  var userDialog=parent.easyUI.modalDialog({
		title:'修改资料',
		iconCls:'icon-person',
		width:470,
		height:565,
		url:mUrl.preCurrentUserInfoEdit,
		buttons:[{
			text:'清空',
			iconCls:'icon-chear',
			handler:function(){
				var iframeObj = userDialog.find('iframe').get(0).contentWindow;
			    iframeObj.clearForm();
			}
		},{
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
		    
	 });
		    
	 //密码修改
	 $('#pwdEdit').click(function() {
		  var userPwdDialog=parent.easyUI.modalDialog({
			 title:'密码修改',
			 iconCls:'icon-pwd-change',
			 width:475,
			 height:179,
			 url:mUrl.preCurrentUserPwdEdit,
			 buttons:[{
					text:'清空',
					iconCls:'icon-chear',
					handler:function(){
					    var iframeObj = userPwdDialog.find('iframe').get(0).contentWindow;
					 	iframeObj.clearForm();
					}
			 },{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){
					      var iframeObj = userPwdDialog.find('iframe').get(0).contentWindow;
					      
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
								    userPwdDialog.dialog('destroy');
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
	});
		    
	//退出系统
	$('#exitSys').click(function() {
		 parent.exitSystem();
	});
}

/**
 * 初始化常用功能显示
 */  
function initComFuncDisplay(){
	var comFuncLiObj=$('.com-func').find('li');
	var divisor=$('.com-func').width()%110;
	var dividend=parseInt($('.com-func').width()/110);
	
	comFuncLiObj.css('padding-left',(divisor/dividend/2)+'px');
	comFuncLiObj.css('padding-right',(divisor/dividend/2)+'px');
}
       
 /**
 * 查看待办
 */    
function taskView(taskUrl,taskId,taskParameter){
  window.location.href=(taskUrl).replace(/\s+/g,"")+"?id="+(taskId).replace(/\s+/g,"")+"&taskParameter="+(taskParameter).replace(/\s+/g,"");
}

/**
 * 查看更多
 */
function taskMoreView(){
  window.location.href=mUrl.preTaskManage;
}

/**
 * 查看公告
 */  
function noticeView(noticeId){
	alert(noticeId);
}


/**
 * 常用功能配置
 */
function comFuncConfig(){
	var comFuncDialog=parent.easyUI.modalDialog({
	    title:'常用功能配置',
	    iconCls:'icon-config',
	    width:520,
	    height:440,
	    url:mUrl.preComFuncConfig,
	    buttons:[{
			text:'提交',
			iconCls:'icon-ok',
			handler:function(){
			      var iframeObj = comFuncDialog.find('iframe').get(0).contentWindow;
			      
	              var jsonInfo = iframeObj.serializeForm();
	              
	              jsutil.defaultSerializeReq(
	            	  mUrl.saveComfuncConfig,
				      jsonInfo,
				      function(data){
				        if(data.resultType=="success"){
				            comFuncDialog.dialog('destroy');
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
		}]
	  });
}

