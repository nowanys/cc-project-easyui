var jsutil = jsutil || {};


/*
 *获得参数
 */
jsutil.getRequest = function() {
	var url = location.search;//获取url中"?"符后的字串
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}


/**
 * 截取字符
 */
jsutil.subString = function(value,strLen,endLen){
    if (null != value && "" != value && undefined != value) {
		value = value.substring(strLen, endLen);
	}
	return value;
}


/**
 * 序列化表单
 */
 jsutil.serializeFrom=function(fields){
    var data = "{";
	for (var i = 0; i < fields.length; i++) {
	    var validValue = $("#" + fields[i]).val();
	    
	    if(null!=validValue && undefined!=validValue){
		    data+="'"+fields[i]+"':'"+validValue+"'";
		}else{
		    data+="'"+fields[i]+"':''";
		}
		
		if(i<(fields.length-1)){
		    data+=",";
		}
	}
	data+="}";
  return data;
}

/*
 *去空格
 */
jsutil.trim = function(str) {
	return str.replace(/\s+/g,'');
}

/*
 *去左空格
 */
jsutil.ltrim = function(str) {
	return str.replace(/(^\s*)/g, '');
}


/**
 * 全参数请求
 * url 发送请求的地址
 * data 发送到服务器的数据，数组存储，如：{"date": new Date().getTime(), "state": 1}
 * async 默认值: true。默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false。
 *       注意，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
 * type 请求方式("POST" 或 "GET")， 默认为 "GET"
 * dataType 预期服务器返回的数据类型，常用的如：xml、html、json、text
 * successfn 成功回调函数
 * errorfn 失败回调函数
 */
jsutil.fullReq=function(url, data, async, type, dataType, successfn, errorfn) {
        async = (async==null || async=="" || typeof(async)=="undefined")? "true" : async;
        type = (type==null || type=="" || typeof(type)=="undefined")? "post" : type;
        dataType = (dataType==null || dataType=="" || typeof(dataType)=="undefined")? "json" : dataType;
        data = (data==null || data=="" || typeof(data)=="undefined")? {"date": new Date().getTime()} : data;
        $.ajax({
            type: type,
            async: async,
            data: data,
            url: url,
            dataType: dataType,
            beforeSend:function(xhr){
              parent.showMask()
            },
            success: function(d){
                parent.closeMask();
                successfn(d);
            },
            error: function(e){
                errorfn(e);
            }
        });
};
    
/**
 * 无data请求
 */
jsutil.notDataReq=function(url, successfn) {
        $.ajax({
            type: "post",
            url: url,
            dataType: "json",
            beforeSend:function(xhr){
              parent.showMask()
            },
            success: function(d){
                parent.closeMask();
                successfn(d);
            },
			error:function(e){
			  parent.alertBox.showAlert('系统错误！','error');
		      return;
			} 
        });
};
    
/**
 * 默认请求
 */
jsutil.defaultReq=function(url, data, successfn) {
        data = (data==null || data=="" || typeof(data)=="undefined")? {"date": new Date().getTime()} : data;
        $.ajax({
            type: "post",
            data: data,
            url: url,
            dataType: "json",
            beforeSend:function(xhr){
              parent.showMask()
            },
            success: function(d){
                parent.closeMask();
                successfn(d);
            },
            error: function(e){
              parent.alertBox.showAlert('系统错误！','error');
		      return;  
            }
        });
};

/**
 * 默认请求
 */
jsutil.defaultSerializeReq=function(url, data, successfn) {
        data = (data==null || data=="" || typeof(data)=="undefined")? {"date": new Date().getTime()} : data;
        $.ajax({
            type: "post",
            data: JSON.stringify(data),
            url: url,
            dataType: "json",
            beforeSend:function(xhr){
              parent.showMask()
            },
            success: function(d){
                parent.closeMask();
                successfn(d);
            },
            error: function(e){
              parent.alertBox.showAlert('系统错误！','error');
		      return;  
            }
        });
};
 

/**
 * 无加载框无data请求
 */
jsutil.notMaskDataReq=function(url, successfn) {
        $.ajax({
            type: "post",
            url: url,
            dataType: "json",
            success: function(d){
                successfn(d);
            },
			error:function(e){
			  parent.alertBox.showAlert('系统错误！','error');
		      return;
			} 
        });
};


/**
 * 设置自定义属性值
 */
jsutil.setCustomAttrVal=function(id,attName,val){
  $("#" + id).attr(attName,val);
}
 
/**
 * 获得自定义属性值
 */
jsutil.getCustomAttrVal=function(id,attName){
  return $("#" + id).attr(attName);
}

/**
 * 设置默认值
 */
jsutil.setDefAttrVal=function(id,val){
  $("#" + id).attr('defVal',val);
}
 
/**
 * 获得默认值
 */
jsutil.getDefAttrVal=function(id){
  return $("#" + id).attr('defVal');
}

 

 
 
 
 