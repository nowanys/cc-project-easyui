<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>系统公告添加</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
	<script type="text/javascript" charset="utf-8" src="common/js/kindeditor/kindeditor-all-min.js"></script>
	
	
	<script type="text/javascript">
	//编辑器
    var editor;
    KindEditor.ready(function (K) {
        editor = KindEditor.create("#noticeContent", {
        	minWidth : 553,
        	minHeight : 290,
        	themeType : 'simple',
            items: [
                'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                'insertunorderedlist', '|', 'emoticons'],
        });
    });
    
	  function validSubmit(){
	     var fields = ['noticeTitle'];
	     var result= validForm(fields);
	     
	     if(result){
	    	if(editor.text()<=0 || editor.text()>=500){
	    		parent.alertBox.showAlert('公告内容请输入1到500个字符！','warning');
	    		return false;
	    	}else{
	    		if($('#cutoffDate').datebox('getValue')=='' || $('#cutoffDate').datebox('getValue').length<=0){
	    			parent.alertBox.showAlert('截止时间不能为空！','warning');
		    		return false;
	    		}else{
	    			return true;
	    		}
	    	}
	    	 
	     }else{
	    	 return false;
	     }
	  }
	  
	  function serializeForm(){
	    var data ={
	    	"noticeId":$('#noticeId').val(),
	    	"noticeTitle":$('#noticeTitle').val(),
	    	"noticeContent":editor.fullHtml(),
	    	"status":$('#status').val(),
	    	"cutoffDate":$('#cutoffDate').datebox('getValue')
	    	
	    }
	    return data;
	  }
	 
	</script>
  </head>
  
  <body class="body-scroll">
  
     <input type="hidden" id="noticeId" value="${requestScope.noticeBean.noticeId}">
   
     <table style="width:100%;margin: 10px" cellspacing="0" cellpadding="3">
	       <tr>
	          <td class="table-style-0" style="width:10%">
	             公告标题：
	          </td>
	          <td style="width:90%">
	             <input type="text"  id="noticeTitle"   class="easyui-validatebox input-border01" style="width:550px"  required="required"  validType="length[0,100]" value="${requestScope.noticeBean.noticeTitle}">
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:10%">
	               公告内容：
	          </td>
	          <td style="width:90%">
	            <textarea name="noticeContent" id="noticeContent">${requestScope.noticeBean.noticeContent}</textarea>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:10%">
	             截止时间：
	          </td>
	          <td style="width:90%">
	            <input id="cutoffDate" type="text" class="easyui-datebox  input-border01" data-options="editable:false" value="${requestScope.noticeBean.cutoffDate}"/>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:10%">
	             状态：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="status" value="${requestScope.noticeBean.status}">
			    <select class="easyui-combobox select-border02" id="statusSelect" data-options="editable:false,
			    onSelect: function(record){
			        $('#status').val(record.value);
			    },onLoadSuccess:function(){
			        $('#status').val($(this).combobox('getValue'));
			    }">
	                <c:forEach var="dataDic" items="${requestScope.S_TYPE}">  
	                 <c:if test="${requestScope.noticeBean.status==dataDic.dicItemValue}">
	                    <option value="${dataDic.dicItemValue}" selected="selected">${dataDic.dicItemName}</option>
	                 </c:if>
	                 <c:if test="${requestScope.noticeBean.status!=dataDic.dicItemValue}">
	                    <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	                 </c:if>
	               </c:forEach>
	            </select>
	          </td>
	        </tr>
	        
	        
	   </table>
  </body>
</html>
