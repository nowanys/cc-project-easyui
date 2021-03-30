<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>用户添加</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
	<script type="text/javascript" charset="utf-8" src="common/js/ajaxupload/ajaxupload.js"></script>
	<script type="text/javascript" charset="utf-8" src="pages/manage/js/ajaxupload-img.js"></script>
	
	<script type="text/javascript">
	 $(function(){
		  //图片上传
		  ajaxUpload.fullAjaxUploadImg($('#imgUploadButton'),{
			  onComplete : function(file, response) {
				   if(response.resultType=='success'){
					  $('#userHeadImg').attr('src',response.resultData.fileUrl);
					  $('#headImg').val(response.resultData.fileUrl);
					  $('#fileName').val(response.resultData.fileName);
					}else{
					  parent.alertBox.showAlert("头像上传失败！", "error");
					}  
			  }
		  });
	  })
	  
	  function validSubmit(){
	     var fields = ['userCode','userName','realName'];
	     return validForm(fields);
	  }
	  
	  function serializeForm(){
	    var fields = ['userCode','userName','realName','sex','telephone','email','status','userDescribe','headImg','fileName','idCard','smsSwitch','emailSwitch'];
	    return jsutil.serializeFrom(fields);
	  }
	 
	</script>
  </head>
  
  <body class="body-scroll">
     <table style="width:100%;margin: 10px" cellspacing="0" cellpadding="3">
            <tr>
              <td class="table-style-0" style="width:20%;">
	             头像：
	          </td>
	          <td style="width:80%">
	            <img  class="user-head" id="userHeadImg" src="pages/manage/images/user-default-head.png">
	            <input type="hidden" id="headImg">
	            <input type="hidden" id="fileName">
	          </td>
            </tr>
            <tr>
              <td class="table-style-0" style="width:20%;">
	          </td>
	          <td style="width:80%">
	           <a href="javascript:void(0)" title="上传图片" id="imgUploadButton" class="easyui-linkbutton" iconCls="icon-upload">上传图片</a>
	            <!--[if lt IE 9]>
	              &nbsp;&nbsp;
	            <![endif]-->
	          </td>
            </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             编号：
	          </td>
	          <td style="width:80%">
	            <input type="text" id="userCode"   class="easyui-validatebox input-border01"  required="required" validType="checkTrimLength[20,10]">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             用户名：
	          </td>
	          <td style="width:80%">
	            <input type="text"  id="userName"   class="easyui-validatebox input-border01"  required="required"  validType="checkAccount">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             密码：
	          </td>
	          <td style="width:80%">
	           <span style="color:red">默认111111，使用时请修改密码！</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	              姓名：
	          </td>
	          <td style="width:80%">
	            <input type="text"  id="realName"  class="easyui-validatebox input-border01" required="required"  validType="checkTrimLength[20,10]">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	            性别：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id=sex value="">
			    <select class="easyui-combobox select-border02" id="sexSelect" data-options="editable:false,
			    onSelect: function(record){
			      $('#sex').val(record.value);
			    },onLoadSuccess:function(){
			     $('#sex').val($(this).combobox('getValue'));
			    }">
	               <c:forEach var="dataDic" items="${requestScope.SEX_TYPE}">  
	                    <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	               </c:forEach>
	            </select>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             身份证：
	          </td>
	          <td style="width:80%">
	            <input type="text"  id="idCard"  class="easyui-validatebox input-border01"   validType="idCard">
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             电话：
	          </td>
	          <td style="width:80%">
	            <input type="text"  id="telephone"  class="easyui-validatebox input-border01"   validType="telephone">
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             邮箱：
	          </td>
	          <td style="width:80%">
	            <input type="text"  id="email"  class="easyui-validatebox input-border01"   validType="email">
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             邮件开关：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="emailSwitch" value="">
			    <select class="easyui-combobox select-border02" id="emailSwitchSelect" data-options="editable:false,
			    onSelect: function(record){
			       $('#emailSwitch').val(record.value);
			    },onLoadSuccess:function(){
			       $('#emailSwitch').val($(this).combobox('getValue'));
			    }">
	               <c:forEach var="dataDic" items="${requestScope.EMAIL_SWITCH_TYPE}">  
	                    <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	               </c:forEach>
	            </select>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             短信开关：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="smsSwitch" value="">
			    <select class="easyui-combobox select-border02" id="smsSwitchSelect" data-options="editable:false,
			    onSelect: function(record){
			        $('#smsSwitch').val(record.value);
			    },onLoadSuccess:function(){
			        $('#smsSwitch').val($(this).combobox('getValue'));
			    }">
	               <c:forEach var="dataDic" items="${requestScope.SMS_SWITCH_TYPE}">  
	                    <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	               </c:forEach>
	            </select>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             状态：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="status" value="">
			    <select class="easyui-combobox select-border02" id="statusSelect" data-options="editable:false,
			    onSelect: function(record){
			        $('#status').val(record.value);
			    },onLoadSuccess:function(){
			        $('#status').val($(this).combobox('getValue'));
			    }">
	               <c:forEach var="dataDic" items="${requestScope.STATUS_TYPE}">  
	                    <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	               </c:forEach>
	            </select>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             描述：
	          </td>
	          <td style="width:80%">
	            <textarea id="userDescribe" class="easyui-validatebox textarea-border01" validType="length[0,100]"></textarea>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
