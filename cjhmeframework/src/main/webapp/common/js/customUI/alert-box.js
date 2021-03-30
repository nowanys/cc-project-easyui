/**
 * @author cjh
 * @date 2014-9-16 下午20:39:23
 * JQuery alertBox
 */
 
var alertBox = alertBox || {};

var infoCss="font-size:13px;top: 3%;padding: 6px;position: fixed;z-index: 99999;display: none;color: #219d1d;text-align: left;background:#e8ffe8 url('common/js/customUI/images/info.png') no-repeat 5px center;border: 1px solid #219d1d;";
var warningCss="font-size:13px;top: 3%;padding: 6px;position: fixed;z-index: 99999;display: none;color: #f5921e;text-align: left;background:#fef7e7  url('common/js/customUI/images/warning.png') no-repeat 5px center;border: 1px solid #f5921e;";
var errorCss="font-size:13px;top: 3%;padding: 6px;position: fixed;z-index: 99999;display: none;color: #f51010;text-align: left;background:#feebeb  url('common/js/customUI/images/error.png') no-repeat 5px center;border: 1px solid #f51010;";


/*
 * 消息框（参数：消息、消息类型、回调函数、显示时间）
 */
alertBox.showAlert = function(msg,type,func,seed) {
    var alertBoxCss = infoCss;
    
    if(type=="error"){
      alertBoxCss=errorCss;
    }else if(type=="warning"){
      alertBoxCss=warningCss;
    }
    
    var alertObj = $('<div  style="'+alertBoxCss+'"><div style="margin-left:19px">'+msg+'</div></div>');
    $("body").append(alertObj);
    
    var t_w = ($(top.document.body).outerWidth(true)-alertObj.width())/2;
    var t_h = ($(top.document.body).outerHeight(true)-alertObj.height())/2;
    alertObj.css('left',t_w+'px');
    //alertObj.css('top',t_h+'px');
    
    //回调函数
    if(undefined==func || null== func){
      func=function(){}
    }
    
    //显示时间
    if(''==seed || null==seed || undefined==seed || 'null'==seed){
      seed=2000;
    }
    
    //IE
    if(window.ActiveXObject){
	    alertObj.show();
	    
	    setTimeout(function(){
		  alertObj.hide();
		}, seed);
    
    //非IE
	}else{
	    alertObj.animate({
           opacity: "show"
        });
        
        setTimeout(function(){
	       alertObj.animate({
		      opacity: "hide"
		   });
		   
		   func();
		   
		}, seed);
	}
	
	return true;
}
