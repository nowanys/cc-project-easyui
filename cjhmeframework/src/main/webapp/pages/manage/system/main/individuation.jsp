<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>
  
    <title>修改化</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
	<script type="text/javascript">
	
	   function individuationConfig(){
		   var individuationThemeVal=$('#individuationTheme').combobox('getValue');
		   var individuationMenuVal=$('#individuationMenu').combobox('getValue');
		   
		   $.cookie('INDIVIDUATION_THEME_TYPE', individuationThemeVal,{ path: '/', expires: 30});
		   $.cookie('INDIVIDUATION_MENU_TYPE', individuationMenuVal,{ path: '/', expires: 30});
	   }
	   
	</script>
	
  </head>
  
  <body>
       <table style="width:100%;margin: 10px" cellspacing="0" cellpadding="3">
            <tr style="height: 39px">
               <td colspan="2">
                  <span style="color:red;margin-left:5px">设置个性化后页面会自动刷新，请保存操作！</span>
               </td>
            </tr>
	        <tr style="height: 39px">
	          <td class="table-style-0" style="width:20%;">
	              主题风格：
	          </td>
	          <td style="width:80%">
	            <select class="easyui-combobox select-border02" id="individuationTheme" data-options="editable:false,
	            panelHeight:'80',
			    onLoadSuccess:function(){
			       var individuationThemeVal = $.cookie('INDIVIDUATION_THEME_TYPE');
			       if(individuationThemeVal){
			         $(this).combobox('setValue',individuationThemeVal);
			       }
			    }">
			       <c:forEach var="dataDic" items="${requestScope.INDIVIDUATION_THEME_TYPE}">  
	                  <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	               </c:forEach>
	            </select>
	          </td>
	        </tr>
	        <tr style="height: 39px">
	          <td class="table-style-0" style="width:20%;">
	              菜单风格：
	          </td>
	          <td style="width:80%">
	            <select class="easyui-combobox select-border02" id="individuationMenu" data-options="editable:false,
			    onLoadSuccess:function(){
			       var individuationMenuVal = $.cookie('INDIVIDUATION_MENU_TYPE');
			       if(individuationMenuVal){
			         $(this).combobox('setValue',individuationMenuVal);
			       }
			    }">
			       <c:forEach var="dataDic" items="${requestScope.INDIVIDUATION_MENU_TYPE}">  
	                  <option value="${dataDic.dicItemValue}">${dataDic.dicItemName}</option>
	               </c:forEach>
	            </select>
	          </td>
	        </tr>
	   </table>
  </body>
</html>
