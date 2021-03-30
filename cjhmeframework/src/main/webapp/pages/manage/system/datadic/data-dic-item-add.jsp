<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>数据字典明细添加</title>
    
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
	     iconDialog.dialog('destroy');
	  }
	  
	  var dicItemDialog;
	  function dataDicItemSelect(){
		  var dicTypeCode=$('#dicTypeCode').val();
		  var status=$('#status').val();
		  dicItemDialog=parent.easyUI.modalDialog({
			  title:'字典明细选择',
			  iconCls:'icon-select',
			  width:350,
			  height:350,
			  url:mUrl.preSingleDataDicItemSelect+"dataDicItemSelectCallBackFunc&dicTypeCode="+dicTypeCode+"&status="+status+"&isCheckLeaf=N"
		  });  
	  }
	  
	  function dataDicItemSelectCallBackFunc(node){
		    $('#dicItemParentId').val(node.id);
		    $('#dicItemParentName').val(node.text);
		    dicItemDialog.dialog('destroy');
	  }
	
	  function resetDataDicItemPatent(){
		  $('#dicItemParentId').val(-1);
		  $('#dicItemParentName').val("无");
	  }
	
	
	  function validSubmit(){
	     var fields = ['dicItemCode','dicItemName','dicItemValue','dicItemDesc','defaultValue','spareValue'];
	     return validForm(fields);
	  }
	  
	  function serializeForm(){
	    var fields = ['dicTypeId','dicTypeCode','dicItemCode','dicItemName','dicItemValue','iconCls','dicItemParentId','status','dicItemDesc','defaultValue','spareValue'];
	    return jsutil.serializeFrom(fields);
	  }
	  
	</script>
  </head>
  
  <body class="body-scroll">
     <input type="hidden" id="dicTypeId" value="${requestScope.dicTypeId}" />
     <input type="hidden" id="dicTypeCode" value="${requestScope.dicTypeCode}"/>
     <input type="hidden" id="status" value="${requestScope.status}"/>
            
     <table style="width:100%;margin: 10px" cellspacing="0" cellpadding="3">
	        <tr>
	          <td class="table-style-0" style="width:25%;">
	             编号：
	          </td>
	          <td style="width:75%">
	            <input type="text" id="dicItemCode"  class="easyui-validatebox input-border01"   required="required" required="required" validType="length[1,30]">
	             <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:25%;">
	              名称：
	          </td>
	          <td style="width:75%">
	            <input type="text" id="dicItemName"  class="easyui-validatebox input-border01"  required="required" validType="checkTrimLength[20,10]">
	             <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:25%;">
	             值：
	          </td>
	          <td style="width:75%">
	            <input type="text"  id="dicItemValue"  class="easyui-validatebox input-border01"  required="required" validType="length[1,50]">
	             <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:25%;">
	             备用值：
	          </td>
	          <td style="width:75%">
	            <input type="text"  id="spareValue"  class="easyui-validatebox input-border01"   validType="length[0,50]">
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:25%;">
	             默认值：
	          </td>
	          <td style="width:75%">
	            <input type="text"  id="defaultValue"  class="easyui-validatebox input-border01"  validType="length[0,50]">
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:25%">
	             图标：
	          </td>
	          <td style="width:75%">
	            <img id="viewIcon"  class="view-icon" style="margin-bottom: 2px;margin-right: 5px">
	            <input type="text" id="iconCls" class="easyui-validatebox input-border05" readonly="readonly">
	            <a href="javascript:void(0);"  class="easyui-linkbutton" style="margin-left:12px" icon="icon-select" title="选择图标" onclick="iconSelect()"></a>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:25%">
	             上级：
	          </td>
	          <td style="width:75%">
	            <input type="hidden" id="dicItemParentId" value="-1">
	            <input type="text" id="dicItemParentName" class="input-border06"  value="无"  readonly="readonly">
	            <a href="javascript:void(0);"  class="easyui-linkbutton" style="margin-left:12px" icon="icon-select" title="选择字典明细" onclick="dataDicItemSelect()"></a>
	            <a href="javascript:void(0);"  class="easyui-linkbutton" style="margin-left:12px" icon="icon-reload" title="重置" onclick="resetDataDicItemPatent()"></a>
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
	          <td class="table-style-0" style="width:25%">
	             描述：
	          </td>
	          <td style="width:75%">
	            <textarea id="dicItemDesc" class="easyui-validatebox textarea-border01" validType="length[0,100]"></textarea>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
