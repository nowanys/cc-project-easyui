<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>数据字典分类添加</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
	<script type="text/javascript">
	  
	  //图标选择弹出框
	  var iconDialog;
	  function iconSelect(){
		 iconDialog=parent.easyUI.modalDialog({
		    title:'图标选择',
		    iconCls:'icon-select',
		    width:350,
		    height:350,
		    url:mUrl.preIconSelect+"iconSelectCallBackFunc"
		  });
	  }
	  
	  //图标选择回调处理
	  function iconSelectCallBackFunc(iconCls,iconSrc){
	     $('#viewIcon').attr('src',iconSrc);
	     $('#iconCls').val(iconCls);
	     $('#iconCls').focus();
	     iconDialog.dialog('destroy');
	  }
	  
	  function validSubmit(){
	     var fields = ['dicTypeName','dicTypeCode','iconCls'];
	     return validForm(fields);
	  }
	  
	  function serializeForm(){
	    var fields = ['dicTypeName','dicTypeCode','iconCls','dicCategory'];
	    return jsutil.serializeFrom(fields);
	  }
	</script>
  </head>
  
  <body class="body-scroll">
     <input type="hidden" id="dicCategory"  name="dicCategory" value="${requestScope.dicCategory}">
     <table style="width:100%;margin: 10px" cellspacing="0" cellpadding="3">
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             编号：
	          </td>
	          <td style="width:80%">
	            <input type="text"  id="dicTypeCode"  class="easyui-validatebox input-border01"  required="required" validType="length[1,30]">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             名称：
	          </td>
	          <td style="width:80%">
	            <input type="text" id="dicTypeName"  class="easyui-validatebox input-border01"  required="required" validType="checkTrimLength[30,15]">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             图标：
	          </td>
	          <td style="width:80%">
	            <img id="viewIcon"  class="view-icon" style="margin-bottom: 2px;margin-right: 5px">
	            <input type="text" id="iconCls" class="easyui-validatebox input-border04" required="required" readonly="readonly">
	            <a href="javascript:void(0);"  class="easyui-linkbutton" icon="icon-select" title="选择图标" onclick="iconSelect()"></a>
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
