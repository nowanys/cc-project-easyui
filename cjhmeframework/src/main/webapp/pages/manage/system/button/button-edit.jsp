<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>按钮编辑</title>
    
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
	  
	  /*
	   * 菜单选择
	   */
	  var menuDialog;
	  function menuSelect(){
	   menuDialog=parent.easyUI.modalDialog({
	     title:'选择',
	     iconCls:'icon-select',
	     width:340,
	     height:425,
	     url:mUrl.preSingleMenuSelect+"addMenuSelectCallBackFunc&isCheckLeaf=Y"
	   });
	  }
	
	  /**
	   * 菜单回调处理
	   */
	  function addMenuSelectCallBackFunc(node){
	    $('#menuName').val(node.text);
	    $('#menuId').val(node.id);
	    $('#menuName').focus();
	    menuDialog.dialog('destroy');
	  } 
	  
	  function validSubmit(){
	     var fields = ['buttonName','functionName','menuName','iconCls','sortNum'];
	     return validForm(fields);
	  }
	  
	  function serializeForm(){
	    var fields = ['buttonId','buttonType','buttonName','functionName','menuId','busniessMark','layoutMark','buttonDescribe','iconCls','status','sortNum'];
	    return jsutil.serializeFrom(fields);
	  }
	  
	</script>
  </head>
  
  <body class="body-scroll">
     <input type="hidden" id="buttonId" value="${requestScope.buttonBean.buttonId}">
     <table style="width:100%;margin: 10px" cellspacing="0" cellpadding="3">
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             按钮名称：
	          </td>
	          <td style="width:80%">
	            <input type="text" id="buttonName"  value="${requestScope.buttonBean.buttonName}"  class="easyui-validatebox input-border01"  required="required" validType="checkTrimLength[20,10]" >
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	              按钮分类：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="buttonType" value="${requestScope.buttonBean.buttonType}">
			    <select class="easyui-combobox select-border02" id="buttonTypeSelect" data-options="editable:false,
			    onSelect: function(record){
			        $('#buttonType').val(record.value);
			    },onLoadSuccess:function(){
			        $('#buttonType').val($(this).combobox('getValue'));
			    }">
		            <c:forEach var="dataDic" items="${requestScope.BUTTON_CATEGORY_TYPE}">
		                  <c:if test="${requestScope.buttonBean.buttonType==dataDic.dicItemValue}">
		                     <option value="${dataDic.dicItemValue}" selected="selected">${dataDic.dicItemName}</option>
		                  </c:if>
		                  <c:if test="${requestScope.buttonBean.buttonType!=dataDic.dicItemValue}">
		                     <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
		                  </c:if>
                    </c:forEach>
		        </select>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	                      函数名：
	          </td>
	          <td style="width:80%">
	            <input type="text"  id="functionName"  value="${requestScope.buttonBean.functionName}"  class="easyui-validatebox input-border01"  required="required"  validType="checkTrimLength[30,15]">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             菜单名称：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="menuId" value="${requestScope.buttonBean.menuId}">
	            <input type="text" id="menuName"  value="${requestScope.buttonBean.menuName}" class="easyui-validatebox input-border03" required="required" readonly="readonly">
	            <a href="javascript:void(0);"  class="easyui-linkbutton" icon="icon-select" title="选择菜单" onclick="menuSelect()"></a>
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             业务标识：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="busniessMark" value="${requestScope.buttonBean.busniessMark}">
			    <select class="easyui-combobox select-border02" id="busniessMarkSelect" data-options="editable:false,
			    onSelect: function(record){
			        $('#busniessMark').val(record.value);
			    },onLoadSuccess:function(){
			        $('#busniessMark').val($(this).combobox('getValue'));
			    }">
                    <c:forEach var="busniessMark" items="${requestScope.busniessMarks}">
                        <c:if test="${requestScope.buttonBean.busniessMark==busniessMark.busniessMarkVal}"> 
                           <option value="${busniessMark.busniessMarkVal}" selected="selected">${busniessMark.busniessMarkDesc}</option>
                        </c:if>
                        <c:if test="${requestScope.buttonBean.busniessMark!=busniessMark.busniessMarkVal}"> 
                           <option value="${busniessMark.busniessMarkVal}" >${busniessMark.busniessMarkDesc}</option>
                        </c:if>
                    </c:forEach>
		        </select>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	             布局：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="layoutMark" value="${requestScope.buttonBean.layoutMark}">
			    <select class="easyui-combobox select-border02" id="layoutMarkSelect" data-options="editable:false,
			    onSelect: function(record){
			        $('#layoutMark').val(record.value);
			    },onLoadSuccess:function(){
			        $('#layoutMark').val($(this).combobox('getValue'));
			    }">
		            <c:forEach var="dataDic" items="${requestScope.BUTTON_LAYOUT_MARK_TYPE}">
		                  <c:if test="${requestScope.buttonBean.layoutMark==dataDic.dicItemValue}">
		                     <option value="${dataDic.dicItemValue}" selected="selected">${dataDic.dicItemName}</option>
		                  </c:if>
		                  <c:if test="${requestScope.buttonBean.layoutMark!=dataDic.dicItemValue}">
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
	            <input type="text" id="iconCls" class="easyui-validatebox input-border04"  required="required" value="${requestScope.buttonBean.iconCls}" readonly="readonly">
	            <a href="javascript:void(0);"  class="easyui-linkbutton" icon="icon-select" title="选择图标" onclick="iconSelect()"></a>
	            <span style="color:red">*</span>
	            <script type="text/javascript">
	              var iconImg = '${requestScope.buttonBean.iconCls}';
	              iconImg=iconImg.substring(5,iconImg.length);
	              var iconUrl=easyUI.iconImgUrl+iconImg+'.png';
	              $('#viewIcon').attr('src',iconUrl);
	            </script>
	          </td>
	        </tr>
	        
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             排序：
	          </td>
	          <td style="width:80%">
	            <input type="text" id="sortNum"  class="easyui-numberspinner easyui-validatebox input-border01" data-options="min:1,max:15"  required="required" value="${requestScope.buttonBean.sortNum}">
	            <span style="color:red">*</span>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             状态：
	          </td>
	          <td style="width:80%">
	            <input type="hidden" id="status" value="${requestScope.buttonBean.status}">
			    <select class="easyui-combobox select-border02" id="statusSelect" data-options="editable:false,
			    onSelect: function(record){
			        $('#status').val(record.value);
			    },onLoadSuccess:function(){
			        $('#status').val($(this).combobox('getValue'));
			    }">
	                <c:forEach var="dataDic" items="${requestScope.STATUS_TYPE}">
		                  <c:if test="${requestScope.buttonBean.status==dataDic.dicItemValue}">
		                     <option value="${dataDic.dicItemValue}" selected="selected">${dataDic.dicItemName}</option>
		                  </c:if>
		                  <c:if test="${requestScope.buttonBean.status!=dataDic.dicItemValue}">
		                     <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
		                  </c:if>
                    </c:forEach>
	            </select>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	                       描述：
	          </td>
	          <td style="width:80%">
	            <textarea id="buttonDescribe" class="easyui-validatebox textarea-border01" validType="length[0,100]">${requestScope.buttonBean.buttonDescribe}</textarea>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
