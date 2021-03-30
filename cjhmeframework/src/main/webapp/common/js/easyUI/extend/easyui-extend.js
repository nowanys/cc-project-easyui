var easyUI = easyUI || {};

//图标地址
easyUI.iconImgUrl = 'common/js/easyUI/themes/icons/';

/**
 * 模式化的dialog
 */
easyUI.modalDialog = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		width : 500,
		height : 300,
		modal : true,
		maximizable : true,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;
	if (options.url) {
		opts.content = '<iframe  src="' + options.url + '" allowTransparency="true" frameborder="0" scrolling="no" width="100%" height="98%"></iframe>';
	}
	return $('<div style="overflow:hidden"/>').dialog(opts);
};



/**
 *  panel中iframe内存回收
 */
$.extend($.fn.panel.defaults, {
	onBeforeDestroy : function() {
		var frame = $('iframe', this);
		try {
			if (frame.length > 0) {
				for (var i = 0; i < frame.length; i++) {
					frame[i].src = '';
					frame[i].contentWindow.document.write('');
					frame[i].contentWindow.close();
				}
				frame.remove();
				if (navigator.userAgent.indexOf("MSIE") > 0) {
					try {
						CollectGarbage();
					} catch (e) {
					}
				}
			}
		} catch (e) {
		}
	}
});


/**
 *  防止panel、window、dialog组件超出浏览器边界
 */
easyUI.onMove = {
	onMove : function(left, top) {
		var l = left;
		var t = top;
		if (l < 1) {
			l = 1;
		}
		if (t < 1) {
			t = 1;
		}
		var width = parseInt($(this).parent().css('width')) + 14;
		var height = parseInt($(this).parent().css('height')) + 14;
		var right = l + width;
		var buttom = t + height;
		var browserWidth = $(window).width();
		var browserHeight = $(window).height();
		if (right > browserWidth) {
			l = browserWidth - width;
		}
		if (buttom > browserHeight) {
			t = browserHeight - height;
		}
		$(this).parent().css({
			left : l,
			top : t
		});
	}
};

$.extend($.fn.dialog.defaults, easyUI.onMove); //dialog扩展
$.extend($.fn.window.defaults, easyUI.onMove); //window扩展
$.extend($.fn.panel.defaults, easyUI.onMove);  //panel扩展


/**
 * 自定义tree的loadFilter的实现
 */
/*var  treeLoadFilter = function (data, parent) {
    if(""==data || undefined==data || null==data){
      return data;
    }
    
	var opt = $(this).data().tree.options;
	var idFiled,
	textFiled,
	parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;
		
		var i,
		l,
		treeData = [],
		tmpMap = [];
		
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children']){
				    //默认有子菜单的关闭
				    tmpMap[data[i][parentField]]['state']='closed'; 
					tmpMap[data[i][parentField]]['children'] = [];
			    }
			    data[i]['id'] = data[i][idFiled];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
			    data[i]['id'] = data[i][idFiled];
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		
		//树根项展开
		data[0]['state']='open';
		
		return treeData;
	}
	return data;
};*/

//$.fn.tree.defaults.loadFilter=treeLoadFilter; //dialog扩展

//$.fn.combotree.defaults.loadFilter=treeLoadFilter; //dialog扩展


/**
 *  扩展combobox
 */
$.extend(
   $.fn.combobox.defaults,{
     panelHeight:'64'
   }
);


/**
 *  验证器扩展
 */
$.extend($.fn.validatebox.defaults.rules, {
    comboboxRequired: {//combobox必须选择一项
        validator: function(value, param){
            var comboxValue  = $('#'+param).combobox('getValue');
            if(null==comboxValue || ''==comboxValue || '--请选择--'==comboxValue || '请选择'==comboxValue){
              return false;
            }
            return true;
        },
        message: '该选择框必须选择一项!'
    },
    phone:{//电话格式
        validator: function(value, param){
            var rePhone =new RegExp("^[0-9]{3,4}-[0-9]{7,8}$");
            var reMobile =new RegExp("^[1][3 | 5 | 8][0-9]{9}$");
            if(!rePhone.test(value) &&  !reMobile.test(value)){
                 return false;
            }
            return true;
        },
        message: '联系电话格式不正确，请使用固话或手机!'
    },
    telephone:{//可以为空的电话格式
        validator: function(value, param){
           if(value!='' && value!=null && value.length>0  && value!=undefined ){
              var rePhone =new RegExp("^[0-9]{3,4}-[0-9]{7,8}$");
              var reMobile =new RegExp("^[1][3 | 5 | 8][0-9]{9}$");
              if(!rePhone.test(value) &&  !reMobile.test(value)){
                 return false;
              }
           }
           return true;
        },
        message: '联系电话格式不正确，请使用固话或手机!'
    },
    confirmPassword:{//密码两次输入
        validator: function (value, param) {  
            return $.trim(value) == $.trim($(param[0]).val());  
        },  
        message: '新密码与确认密码不一致!'  
    },
    selectBox:{//选择框验证
        validator: function(value, param){
           if(value!='' && value!=null  && value!=undefined ){
                if(value=='-1'){
                  return false;
                }
           }
           return true;
        },
        message: '该选择框必须选择一项!'
    },
    checkLength:{//检查长度
        validator: function (value, param) {  
           var l = 0;
		   value=value.replace(/\s+/g,"");
		   var a = value.split("");
		   
		   for (var i=0;i<a.length;i++) {
			  if (a[i].charCodeAt(0)<299) {
			   l++;
			  } else {
			   l+=2;
			  }
		   }
		   return l<=param[0];
           
        },  
        message: '请输入1-{0}个字符!'  
    },
    checkTrimLength:{//检查包括空格长度
        validator: function (value, param) {  
           var l = 0;
		   for (var i=0;i<value.length;i++) {
			  if (value[i].charCodeAt(0)<299) {
			   l++;
			  } else {
			   l+=2;
			  }
		   }
		   return l<=param[0];
           
        },  
        message: '请输入1-{1}个字符!'  
    },
    checkAccount:{//检查用户名是否为5到20个字符,字母开头,可使用字母、下划线、数字
        validator: function (value, param) {
           value=jsutil.trim(value);
           var reStr = new RegExp("^[a-z | A-Z][a-z | A-Z | 0-9 | _]{4,19}$"); 
           if(!reStr.test(value)){
             return false;
           }
           
           return true;
        },  
        message: '用户名为5到20个字符,字母开头,可使用字母、下划线、数字!'  
    },
    idCard:{//身份证
        validator: function (value, param) {
           value=jsutil.trim(value);
           var reStr=new RegExp("^([0-9]{15}|[0-9]{18}|[0-9]{17}(X|x))$");
           if(!reStr.test(value)){
             return false;
           }
           
           return true;
        },  
        message: '身份证格式不正确!'  
    }
});


/**
 * 验证表单
 */
function validForm(fields) {
	for (var i = 0; i < fields.length; i++) {
		var validValue = $("#" + fields[i]).validatebox("isValid");
		if (!validValue) {
			return false;
		}
	}
	return true;
}