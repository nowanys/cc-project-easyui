<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>部门编辑</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
	<script type="text/javascript">
	  //用户选择弹出框
	  var userDialog;
	  function userSelect(){
		 userDialog=parent.easyUI.modalDialog({
		    title:'用户选择',
		    iconCls:'icon-select',
		    width:578,
		    height:500,
		    url:mUrl.preSingleUserSelect+"userSelectCallBackFunc",
		     buttons:[{
				text:'提交',
				iconCls:'icon-ok',
				handler:function(){
				   userDialog.find('iframe').get(0).contentWindow.selectUsers();
				}
			}]
		  });
	  }
	  
	  //用户选择回调处理
	  function userSelectCallBackFunc(users){
	    if(undefined!=users &&  ''!=users && undefined!=users){
	       if(users.length>1){
	         parent.alertBox.showAlert('只能选择一个用户！','warning');
	       }else{
	         $('#deptLeaderId').val(users[0].userId);
	         $('#deptLeader').val(users[0].userName);
	         $('#deptLeader').focus();
	         userDialog.dialog('destroy');
	       }
	    }else{
	      parent.alertBox.showAlert('选择用户失败！','error');
	    }
	  }
	  
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
	     var fields = ['deptCode','deptName','deptLevel','deptLeader','iconCls','deptTel'];
	     return validForm(fields);
	  }
	  
	  function serializeForm(){
	    var fields = ['deptId','deptCode','deptName','deptSortName','deptLevel','deptDescript','deptLeaderId','deptTel','deptAddr','iconCls','smsSwitch','emailSwitch'];
	    return jsutil.serializeFrom(fields);
	  }
	  
	</script>
  </head>
  
  <body class="body-scroll">
     <input type="hidden" id="deptId" value="${requestScope.deptBean.deptId}"/>
     
     <table style="width:100%;margin: 10px" cellspacing="0" cellpadding="3">
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             编号：
	          </td>
	          <td style="width:80%">
	            <input type="text"  id="deptCode" value="${requestScope.deptBean.deptCode}" class="easyui-validatebox input-border01"  required="required" validType="length[1,30]">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             部门名称：
	          </td>
	          <td style="width:80%">
	            <input type="text" id="deptName" value="${requestScope.deptBean.deptName}" class="easyui-validatebox input-border01"  required="required" validType="checkTrimLength[40,20]">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             部门简称：
	          </td>
	          <td style="width:80%">
	            <input type="text" id="deptSortName" value="${requestScope.deptBean.deptSortName}" class="easyui-validatebox input-border01"  validType="checkTrimLength[30,15]">
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             部门级别：
	          </td>
	          <td style="width:80%">
	            <input  type="text" id="deptLevel" value="${requestScope.deptBean.deptLevel}" class="easyui-numberspinner easyui-validatebox input-border01"  data-options="required:true,precision:0,min:1,max:10">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             部门描述：
	          </td>
	          <td style="width:80%">
	            <textarea id="deptDescript" class="easyui-validatebox textarea-border01" validType="length[0,500]">${requestScope.deptBean.deptDescript}</textarea>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             负责人：
	          </td>
	          <td style="width:80%">
	            <input type="text" id="deptLeader" value="${requestScope.deptBean.deptLeader}" class="easyui-validatebox input-border03"  required="required" readonly="readonly">
	            <input type="hidden" id="deptLeaderId" value="${requestScope.deptBean.deptLeaderId}"/>
	            <a href="javascript:void(0);"  class="easyui-linkbutton" icon="icon-select" title="选择图标" onclick="userSelect()"></a>
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             联系电话：
	          </td>
	          <td style="width:80%">
	            <input type="text" id="deptTel"  value="${requestScope.deptBean.deptTel}" class="easyui-validatebox input-border01"  validType="telephone">
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             部门地址：
	          </td>
	          <td style="width:80%">
	            <textarea id="deptAddr"  class="easyui-validatebox textarea-border01" style="height:50px" validType="length[0,200]">${requestScope.deptBean.deptAddr}</textarea>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             图标：
	          </td>
	          <td style="width:80%">
	            <img id="viewIcon"  class="view-icon" style="margin-bottom: 2px;margin-right: 5px" >
	            <input type="text" id="iconCls" class="easyui-validatebox input-border04" required="required" value="${requestScope.deptBean.iconCls}" readonly="readonly">
	            <a href="javascript:void(0);"  class="easyui-linkbutton" icon="icon-select" title="选择图标" onclick="iconSelect()"></a>
	            <span style="color:red">*</span>
	            <script type="text/javascript">
	              var iconImg = '${requestScope.deptBean.iconCls}';
	              iconImg=iconImg.substring(5,iconImg.length);
	              var iconUrl=easyUI.iconImgUrl+iconImg+'.png';
	              $('#viewIcon').attr('src',iconUrl);
	            </script>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             邮件开关：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="emailSwitch" value="${requestScope.deptBean.emailSwitch}">
			    <select class="easyui-combobox select-border02" id="emailSwitchSelect" data-options="editable:false,
			    onSelect: function(record){
			        $('#emailSwitch').val(record.value);
			    },onLoadSuccess:function(){
			        $('#emailSwitch').val($(this).combobox('getValue'));
			    }">
	               <c:forEach var="dataDic" items="${requestScope.EMAIL_SWITCH_TYPE}">  
	                 <c:if test="${requestScope.deptBean.emailSwitch==dataDic.dicItemValue}">
	                   <option value="${dataDic.dicItemValue}" selected="selected">${dataDic.dicItemName}</option>
	                 </c:if>
	                 <c:if test="${requestScope.deptBean.emailSwitch!=dataDic.dicItemValue}">
	                    <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	                 </c:if>
	               </c:forEach>
	            </select>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             短信开关：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="smsSwitch" value="${requestScope.deptBean.smsSwitch}">
			    <select class="easyui-combobox select-border02" id="smsSwitchSelect" data-options="editable:false,
			    onSelect: function(record){
			       $('#smsSwitch').val(record.value);
			    },onLoadSuccess:function(){
			       $('#smsSwitch').val($(this).combobox('getValue'));
			    }">
	               <c:forEach var="dataDic" items="${requestScope.SMS_SWITCH_TYPE}">  
	                 <c:if test="${requestScope.deptBean.smsSwitch==dataDic.dicItemValue}">
	                    <option value="${dataDic.dicItemValue}" selected="selected">${dataDic.dicItemName}</option>
	                 </c:if>
	                 <c:if test="${requestScope.deptBean.smsSwitch!=dataDic.dicItemValue}">
	                    <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	                 </c:if>
	               </c:forEach>
	            </select>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
