<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>访问地址分配</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
    <script type="text/javascript">
        var id='${requestScope.roleId}';
      
        /**
		 * 序列化表单
		 */
	    function serializeForm(){
	        var rows=$('#roleAccessUrlInfos').find('option');
		    var param =[]; 
			for(var i=0;i<rows.length;i++){
				param.push(rows[i].value);
			}
			
			var jsonInfo ={
				"roleId":id,
				"accessUrlIds":param
		    };
		    
		    return jsonInfo;
	    }
	    
	    /**
		 * 清空所有
		 */
	    function removeAll(){
	       $('#roleAccessUrlInfos').empty();
	    }
	    
	    /**
		 * 选中移除
		 */
	    function selectRemove(){
	       $('#roleAccessUrlInfos').find("option:selected").remove();;
	    }
	    
	    /**
		 * 添加所有
		 */
	    function addAll(){
	      var accessUrlOptions = $('#accessUrlInfos').find('option');
	      $('#roleAccessUrlInfos').empty();
	      for(var i=0;i<accessUrlOptions.length;i++){
	        $('#roleAccessUrlInfos').append("<option value='"+accessUrlOptions[i].value+"'>"+accessUrlOptions[i].text+"</option>");
	      }
	      
	    }
	    
	    /**
		 * 选中添加
		 */
	    function addSelect(){
	       var selects=$('#accessUrlInfos').find("option:selected");
	       var options=$('#roleAccessUrlInfos').find('option');
	       
	       for(var i=0;i<selects.length;i++){
	          var isCheck=true;
	          for(var j=0;j<options.length;j++){
	             if(selects[i].value==options[j].value){
	                isCheck=false;
	                break;
	             }
	          }
	          
	          if(isCheck){
	            $('#roleAccessUrlInfos').append("<option value='"+selects[i].value+"'>"+selects[i].text+"</option>");
	          }
	       }
	    }
	    
	 </script> 
  </head>
  
  <body class="body-scroll">
     <div style="margin-top:10px">
        <a href="javascript:void(0)" title="添加全部" onclick="addAll();" class="easyui-linkbutton" iconCls="icon-add" style="margin-left:20px">添加全部</a>
        <a href="javascript:void(0)" title="添加选中" onclick="addSelect();" class="easyui-linkbutton" iconCls="icon-add" style="margin-left:20px">添加选中</a>
        <a href="javascript:void(0)" title="移除全部" onclick="removeAll();" class="easyui-linkbutton" iconCls="icon-del" style="margin-left:20px">移除全部</a>
        <a href="javascript:void(0)" title="选中移除" onclick="selectRemove();" class="easyui-linkbutton" iconCls="icon-del" style="margin-left:20px">选中移除</a>
     </div>
     <div class="data-outer">
        <div class="data-div-left" style="width:300px;">
           <div class="span1">
               所有访问地址：
           </div>  
           <select id="accessUrlInfos" multiple="multiple" class="select-border01" style="width:300px;height:300px" ondblclick="addSelect();">
             <c:forEach items="${requestScope.accessUrlData}" var="item"  varStatus="status">
                  <option value="${item.accessUrlId}">${item.accessUrlName}</option>
             </c:forEach>
           </select>
        </div>
        <div class="data-div-right" style="width:300px;"> 
           <div class="span1">
               分配访问地址：
           </div>
           <select id="roleAccessUrlInfos" multiple="multiple" class="select-border01" style="width:300px;height:300px" ondblclick="selectRemove();">
              <c:forEach items="${requestScope.roleAccessUrlData}" var="item"  varStatus="status">
                  <option value="${item.accessUrlId}">${item.accessUrlName}</option>
              </c:forEach>
           </select>
        </div>
     </div>
     
  </body>
</html>
