<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
  </head>
  
  <body class="body-scroll">
     
     <table style="width:100%;margin: 10px" cellspacing="0" cellpadding="3">
            <tr>
	          <td class="table-style-0" style="width:20%;">
	               菜单编号：
	          </td>
	          <td style="width:80%">
	           <div  class="div-border01">${requestScope.menuBean.menuCode}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%;">
	               菜单名称：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.menuBean.menuName}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	              链接地址：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border02" style="height:50px">${requestScope.menuBean.accessUrl}</div>
	          </td>
	        </tr>
	       <tr>
	          <td class="table-style-0" style="width:20%">
	             图标：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01" style="float: left;width: 179px">${requestScope.menuBean.iconCls}</div>
	            <img id="viewIcon"  class="view-icon" style="margin-top: 2px;margin-left: 5px" >
	            <script type="text/javascript">
	              var iconImg = '${requestScope.menuBean.iconCls}';
	              iconImg=iconImg.substring(5,iconImg.length);
	              var iconUrl=easyUI.iconImgUrl+iconImg+'.png';
	              $('#viewIcon').attr('src',iconUrl);
	            </script>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             创建人：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.menuBean.createUser}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	                      修改人：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.menuBean.updateUser}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             创建时间：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.menuBean.createDate}</div>
	          </td>
	        </tr>
	        <tr>
	          <td class="table-style-0" style="width:20%">
	             修改时间：
	          </td>
	          <td style="width:80%">
	            <div  class="div-border01">${requestScope.menuBean.updateDate}</div>
	          </td>
	        </tr>
	   </table>
      
  </body>
</html>
