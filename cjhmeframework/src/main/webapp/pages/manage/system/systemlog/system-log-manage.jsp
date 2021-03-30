<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
  
    <title>系统日志</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	
	<jsp:include page="../../../manage/common-manage.jsp"></jsp:include>
	
    <script type="text/javascript">
	   $(function(){
	        //刷新数据表格
            $(window).resize(function(){
               $('#infos').datagrid('resize');
            });
	        
	    });
	 </script> 
  </head>
  
  <body class="easyui-layout">
     <div data-options="region:'north',border:false" style="padding:5px">
        <table>  
			<tr>  
		      <td>
			     <span style="color:red">系统日志总数：${requestScope.systemLogCount}</span>
			  </td>
			</tr>  
		 </table>
      </div>
      
     <div data-options="region:'center',border:false" style="padding: 5px">
        <table id="infos" class="easyui-datagrid" 
         data-options="fit : true,title:'系统日志',iconCls:'icon-log-file',fitColumns:true,rownumbers:true,collapsible:true,toolbar:toolbar">
	        <thead>
	            <tr>
	                <th data-options="field:'ck',checkbox:true"></th>
	                <th field="fileName" width="320">文件名</th>
	                <th field="filePath" width="520">文件路径</th>
	                <th field="fileSize" width="100">文件大小</th>
	                <th field="lastModifyDate" width="150">文件最后修改时间</th>
	            </tr>
	        </thead>
	        <tbody>
			  <c:forEach var="systemLogFile" items="${requestScope.systemLogFiles}" varStatus="stauts">  
				<tr onclick="taskView('${systemLogFile.filePath}')">
				    <td>
				    </td>
					<td>
					  ${systemLogFile.fileName}
					</td>
					<td>
					  ${systemLogFile.filePath}
					</td>
					<td>
					  ${systemLogFile.fileSize}
					</td>
					<td>
					  ${systemLogFile.lastModifyDate}
					</td>
				</tr>
			  </c:forEach>
			</tbody>
	    </table>
      </div> 
      
      <script type="text/javascript">
        var toolbar = [
            <c:forEach items="${requestScope.buttonList}" var="btnList">
				<c:if test="${btnList.layoutMark=='5'}">
				 {
					id:'${btnList.buttonId}',
					text:'${btnList.buttonName}',
					iconCls:'${btnList.iconCls}',
					handler:function(){
						${btnList.functionName}
					}
			     },
				</c:if>
			</c:forEach>
        ];
        
        /**
         * 下载
         */
        function downloadFile(){
              var selected =$('#infos').datagrid('getSelected'); 
			  if(selected){
			     var selecteds =$('#infos').datagrid('getSelections');    
				 if(selecteds.length>1){
					parent.alertBox.showAlert('只能选择一条记录操作！','warning');
			        return ;		 
				 }else{
				    window.location.href=mUrl.downloadSystemLog+window.encodeURI(window.encodeURI(selected.filePath));
				 }
			  }else{
			    parent.alertBox.showAlert('请选择一条记录操作！','warning');
			    return ;
			  }
        }
        
        /**
         * 删除
         */
        function delFile(){
              var selected =$('#infos').datagrid('getSelected'); 
			  if(selected){
			     var selecteds =$('#infos').datagrid('getSelections');    
				 if(selecteds.length>1){
					parent.alertBox.showAlert('只能选择一条记录操作！','warning');
			        return ;		 
				 }else{
				    jsutil.notDataReq(
				      mUrl.deleteSystemLog+window.encodeURI(window.encodeURI(selected.filePath)),
					  function(data){
						if(data.resultType=="success"){
						    parent.alertBox.showAlert(data.resultMsg,'info');
						    window.location.reload();
					    }else if(data.resultType=="failure"){
						    parent.alertBox.showAlert(data.resultMsg,'warning');
						}else{
						    parent.alertBox.showAlert(data.resultMsg,'error');
						}
					   }
				     );
				 }
			  }else{
			    parent.alertBox.showAlert('请选择一条记录操作！','warning');
			    return ;
			  }
        }
      </script>
  </body>
</html>
