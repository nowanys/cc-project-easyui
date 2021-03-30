<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>单个菜单选择</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
   
    <script type="text/javascript">
       //是否展开叶子节点
       var isCheckLeaf='${requestScope.isCheckLeaf}'
       
	   $(function(){
	     queryMenuTree('','','');
	   });
	   
	   function queryMenuTree(menuCode,menuName){
		  var jsonInfo ={
		    "menuCode":menuCode,
		    "menuName":menuName
		  };
	   
		  jsutil.defaultReq(
			 mUrl.queryMenuByCondition,
			 JSON.stringify(jsonInfo),
			 function(data){
				 if(data.resultType=="success"){
				       $('#menuTree').tree({
							data:data.resultData,
							animate:true,
							onClick: function(node){
							   //如果不是叶子节点展开
					           if ('Y'==isCheckLeaf && !$(this).tree('isLeaf', node.target)) {
					                $(this).tree('expand',node.target);
					           }else{
					                var iframes = window.parent.frames;
						            var iframeObj;
						            for(var i=0;i<iframes.length;i++){
						              if(iframes[i].${requestScope.callBackFunc}){
						                 iframeObj=iframes[i];
						                 break;
						              }
						            }
								    iframeObj.${requestScope.callBackFunc}(node);
					           }
							}
						 });
				     
				  }else if(data.resultType=="failure"){
				        parent.alertBox.showAlert(data.resultMsg,'warning');
				  }else{
					    parent.alertBox.showAlert(data.resultMsg,'error');
				  }		
			 }
		  );
	   }
	   
	   function queryMenuTreeByCondition(){
	      var menuCode=$('#queryMenuCode').val();
	      var menuName=$('#queryMenuName').val();
	      queryMenuTree(menuCode,menuName);
	   }
	   
	   function clearQueryCondition(){
	      $('#queryMenuCode').val('');
	      $('#queryMenuName').val('');
	   }
	   
	   function resetQueryCondition(){
	      clearQueryCondition();
	      queryMenuTreeByCondition();
	   }
	</script> 
  </head>
  
  <body class="body-scroll">
      <div class="query-div2">
		<form id="queryForm" name ="queryForm">  
			<table>  
			    <tr>  
			      <td width="36px" height="25px">编号：</td>
			      <td width="170px" >
			          <input  id="queryMenuCode" type="text" class="input-border01" style="width:150px;"/>
			      </td>
			    </tr>
			    <tr>
			      <td width="50px" height="25px">名称：</td>
			      <td width="170px" >
			          <input  id="queryMenuName"  type="text" class="input-border01" style="width:150px;"/>
			      </td>
			      <td width="50px"></td>
			    </tr>
			    <tr>
			      <td width="100px" height="25px" colspan="4">
			          <a href="javascript:void(0)" title="查询" onclick="queryMenuTreeByCondition()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
			          <a href="javascript:void(0)" title="清空" onclick="clearQueryCondition();" class="easyui-linkbutton" iconCls="icon-no" style="margin-left: 20px">清空</a>
			          <a href="javascript:void(0)" title="重置" onclick="resetQueryCondition();" class="easyui-linkbutton" iconCls="icon-reload" style="margin-left: 20px">重置</a>
			      </td> 
			    </tr>
			 </table>  
		</form> 
      </div>
      
      <div class="data-div2">  
        <ul id="menuTree" style="margin: 10px"></ul>
      </div> 
</html>
