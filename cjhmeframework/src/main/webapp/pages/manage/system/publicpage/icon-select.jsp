<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>图标选择</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<style type="text/css">
	  .view-min-icon{
	     cursor:pointer;
	     cursor: hand;
	     padding: 5px;
	     margin: 2px
	  }
	  
	  .view-min-icon:hover {
		  background: #c5e2fe;
		  filter: none;
		}
	
	</style>
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
    <script type="text/javascript">
      function clickIcon(iconCls,iconPath){
         if(null!=iconCls && ""!=iconCls && undefined!=iconCls){
           var iframes = window.parent.frames;
           var iframeObj;
           for(var i=0;i<iframes.length;i++){
              if(iframes[i].${requestScope.callBackFunc}){
                 iframeObj=iframes[i];
                 break;
              }
           }
           iframeObj.${requestScope.callBackFunc}(iconCls,iconPath);
         }else{
            parent.alertBox.showAlert('图标选择失败！','error');
         }
      }
      
      
     
    </script>
  </head>
  
  <body class="body-scroll">
      <c:forEach items="${requestScope.systemIcons}" var="iconObj">
       <img class="view-min-icon" title="${iconObj.iconCls}" src="${iconObj.iconPath}" onclick="clickIcon('${iconObj.iconCls}','${iconObj.iconPath}')"/>
      </c:forEach>
  </body>
</html>
