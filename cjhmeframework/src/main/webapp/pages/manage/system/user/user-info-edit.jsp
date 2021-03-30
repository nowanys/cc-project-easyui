<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>
  
    <title>用户资料</title>
    
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
	  
	  /**
	   * 验证表单
	   */
	  function validSubmit(){
	     var fields = ['realName','telephone','email','userDescribe'];
	     return validForm(fields);
	  }
	  
	  /**
	   * 序列化表单
	   */
	  function serializeForm(){
	    var fields = ['userId','realName','sex','telephone','email','userDescribe','headImg','fileName','fileId'];
	    return jsutil.serializeFrom(fields);
	  }
	  
	  /**
	   * 清空表单
	   */
	  function clearForm(){
	    $('#realName').val('');
	    $('#telephone').val('');
	    $('#email').val('');
	    $('#userDescribe').val('');
	    
	    $('#sex').val('1');
	    $('#sexSelect').combobox('setValue','1');
	  }
	  
	</script>
  </head>
  
  <body class="body-scroll">
  
     <input type="hidden" id="userId" value="${requestScope.userBean.userId}">
     
     <table style="width:100%;margin: 10px" cellspacing="0" cellpadding="3">
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             编号：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.userBean.userCode}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             用户名：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.userBean.userName}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	          </td>
	          <td style="width:80%;">
	           <span style="color:red">------以下资料可编辑------</span>
	          </td>
	        </tr>
	        <tr>
              <td class="table-style-0" style="width:20%;">
	             头像：
	          </td>
	          <td style="width:80%">
	           <c:if test="${!empty requestScope.fileDataBean}">
	             <img  class="user-head" id="userHeadImg" src="${requestScope.fileDataBean.fileUrl}">
	             <input type="hidden" id="headImg"  value="${requestScope.fileDataBean.fileUrl}">
	             <input type="hidden" id="fileName" value="${requestScope.fileDataBean.fileName}">
	             <input type="hidden" id="fileId" value="${requestScope.fileDataBean.fileId}">
	           </c:if>
	           <c:if test="${empty requestScope.fileDataBean}">
	             <img  class="user-head" id="userHeadImg" src="pages/manage/images/user-default-head.png">
	             <input type="hidden" id="headImg"  value="">
	             <input type="hidden" id="fileName" value="">
	             <input type="hidden" id="fileId" value="">
	           </c:if>
	            
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
	            <span style="height: 30px;color:red">格式：jpg、png、gif</span>
	          </td>
            </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	              姓名：
	          </td>
	          <td style="width:80%">
	            <input type="text"  id="realName" value="${requestScope.userBean.realName}" class="easyui-validatebox input-border01" required="required"  validType="checkTrimLength[20,10]">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	            性别：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id=sex value="${requestScope.userBean.sex}">
			    <select class="easyui-combobox select-border02" id="sexSelect" data-options="editable:false,
			    onSelect: function(record){
			      $('#sex').val(record.value);}
			    ">
	              <c:if test="${requestScope.userBean.sex=='1'}">
	                <option value="1" selected="selected">男</option>
	                <option value="2">女</option>
	                <option value="3">保密</option>
	              </c:if>
	              <c:if test="${requestScope.userBean.sex=='2'}">
	                <option value="1">男</option>
	                <option value="2" selected="selected">女</option>
	                <option value="3">保密</option>
	              </c:if>
	              <c:if test="${requestScope.userBean.sex=='3'}">
	                <option value="1">男</option>
	                <option value="2">女</option>
	                <option value="3" selected="selected">保密</option>
	              </c:if>
	               
	            </select>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             电话：
	          </td>
	          <td style="width:80%">
	            <input type="text"  id="telephone" value="${requestScope.userBean.telephone}" class="easyui-validatebox input-border01"   validType="telephone">
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             邮箱：
	          </td>
	          <td style="width:80%">
	            <input type="text"  id="email" value="${requestScope.userBean.email}"  class="easyui-validatebox input-border01"   validType="email">
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	              描述：
	          </td>
	          <td style="width:80%">
	            <textarea id="userDescribe"  class="easyui-validatebox textarea-border01" validType="length[0,100]">${requestScope.userBean.userDescribe}</textarea>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
