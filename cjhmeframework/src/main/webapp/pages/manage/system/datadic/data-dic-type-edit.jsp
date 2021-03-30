<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>数据字典分类编辑</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
	<script type="text/javascript">
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
	     var fields = ['dicTypeId','dicTypeName','dicTypeCode','iconCls','dicCategory'];
	     return jsutil.serializeFrom(fields);
	  }
	</script>
  </head>
  
  <body class="body-scroll">
  
     <input type="hidden" id="dicTypeId" value="${requestScope.dataDicTypeBean.dicTypeId}">
     
     <table style="width:100%;margin: 10px" cellspacing="0" cellpadding="3">
            <tr>
	          <td class="table-style-0" style="width:20%;">
	             编号：
	          </td>
	          <td style="width:80%">
	            <input type="text"  id="dicTypeCode"  value="${requestScope.dataDicTypeBean.dicTypeCode}" class="easyui-validatebox input-border01"  required="required" validType="length[1,30]">
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             名称：
	          </td>
	          <td style="width:80%">
	            <input type="text" id="dicTypeName"  value="${requestScope.dataDicTypeBean.dicTypeName}" class="easyui-validatebox input-border01"  required="required" validType="checkTrimLength[30,15]">
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:25%">
	             类别：
	          </td>
	          <td style="width:75%">
	            <input type="hidden" id="dicCategory" value="${requestScope.dataDicTypeBean.dicCategory}">
			    <select class="easyui-combobox select-border02" id="dicCategorySelect" data-options="editable:false,
			    onSelect: function(record){
			        $('#dicCategory').val(record.value);
			    },onLoadSuccess:function(){
			        $('#dicCategory').val($(this).combobox('getValue'));
			    }">
	                <c:forEach var="dataDic" items="${requestScope.DIC_CATEGORY_TYPE}">  
	                 <c:if test="${requestScope.dataDicTypeBean.dicCategory==dataDic.dicItemValue}">
	                    <option value="${dataDic.dicItemValue}" selected="selected">${dataDic.dicItemName}</option>
	                 </c:if>
	                 <c:if test="${requestScope.dataDicTypeBean.dicCategory!=dataDic.dicItemValue}">
	                    <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	                 </c:if>
	               </c:forEach>
	            </select>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             图标：
	          </td>
	          <td style="width:80%">
	            <img id="viewIcon"  class="view-icon" style="margin-bottom: 2px;margin-right: 5px" >
	            <input type="text" id="iconCls" class="easyui-validatebox input-border04"  required="required" value="${requestScope.dataDicTypeBean.iconCls}" readonly="readonly">
	            <a href="javascript:void(0);"  class="easyui-linkbutton" icon="icon-select" title="选择图标" onclick="iconSelect()"></a>
	            <span style="color:red">*</span>
	            <script type="text/javascript">
	              var iconImg = '${requestScope.dataDicTypeBean.iconCls}';
	              iconImg=iconImg.substring(5,iconImg.length);
	              var iconUrl=easyUI.iconImgUrl+iconImg+'.png';
	              $('#viewIcon').attr('src',iconUrl);
	            </script>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
