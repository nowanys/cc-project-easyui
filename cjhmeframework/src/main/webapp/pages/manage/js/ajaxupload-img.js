var ajaxUpload = ajaxUpload || {};

/**
 * 全参数图片上传
 * @param obj 按钮对象
 * 
 * @param action 后台请求地址
 * @param name 后台表单名
 * @param data 数据
 * @param autoSubmit 是否自动提交
 * @param responseType 后台值类型
 * @param hoverClass 鼠标经过样式
 * @param disabledClass 不可用状态样式
 * @param onChange 改变事件
 * @param onSubmit 提交事件
 * @param onComplete 处理完成事件
 */
ajaxUpload.fullAjaxUploadImg=function(obj,opt){
	var _action=(opt.action==null || opt.action=="" || typeof(opt.action)=="undefined")? mUrl.uploadImgCompression+"?isCompress=Y&width=128&height=128&isNewFileName=Y": opt.action;
	var _name=(opt.name==null || opt.name=="" || typeof(opt.name)=="undefined")? "imgUpload" : opt.name;
	var _data=(opt.data==null || opt.data=="" || typeof(opt.data)=="undefined")? {} : opt.data;
	var _autoSubmit=(opt.autoSubmit==null || opt.autoSubmit=="" || typeof(opt.autoSubmit)=="undefined")? true : opt.autoSubmit;
	var _responseType=(opt.responseType==null || opt.responseType=="" || typeof(opt.responseType)=="undefined")? "json" : opt.responseType;
	var _hoverClass=(opt.hoverClass==null || opt.hoverClass=="" || typeof(opt.hoverClass)=="undefined")? "l-btn-hover" : opt.hoverClass;
	var _disabledClass=(opt.disabledClass==null || opt.disabledClass=="" || typeof(opt.disabledClass)=="undefined")? "l-btn-disabled" : opt.disabledClass;
	var _onChange=(opt.onChange==null || opt.onChange=="" || typeof(opt.onChange)=="undefined")? function (file, extension) {} : opt.onChange;
	var _onSubmit=(opt.onSubmit==null || opt.onSubmit=="" || typeof(opt.onSubmit)=="undefined")? function (file, extension) {
		if (!(extension && /^(jpg|JPG|jpeg|JPEG|png|PNG|gif|GIF)$/.test(extension))) {  
            parent.alertBox.showAlert("您上传的图片格式不对，请重新选择！", "warning");
            return false;  
        } 
	} : opt.onSubmit;
    var _onComplete=(opt.onComplete==null || opt.onComplete=="" || typeof(opt.onComplete)=="undefined")? function (file, response) {} : opt.onComplete;
	
    new AjaxUpload(obj, {
		action : _action,
		name : _name,
		data : _data,
		autoSubmit:_autoSubmit,
		responseType:_responseType,
		hoverClass:_hoverClass,
		disabledClass:_disabledClass,
		onChange:function (file, extension) {
			return _onChange(file,extension);
		},
		onSubmit : function(file, extension) {
			return _onSubmit(file,extension);
		},
		onComplete : function(file, response) {
			_onComplete(file, response);
		}
	});
    
}
