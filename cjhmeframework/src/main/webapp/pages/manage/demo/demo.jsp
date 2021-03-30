<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'demo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<jsp:include page="../../manage/common-manage.jsp"></jsp:include>
<script type="text/javascript">
	function saveDemo() {
		jsutil.defaultSerializeReq(mUrl.saveDemo, {
			'content' : '22'
		}, function(data) {
			if (data.resultType == "success") {
				parent.alertBox.showAlert(data.resultMsg, 'info');
			} else if (data.resultType == "failure") {
				parent.alertBox.showAlert(data.resultMsg, 'warning');
			} else {
				parent.alertBox.showAlert(data.resultMsg, 'error');
			}
		});
	}

	function updateDemo() {
		jsutil.defaultSerializeReq(mUrl.updateDemo, {
			'id' : '14',
			'content' : 'qwertyuuuuuu'
		}, function(data) {
			if (data.resultType == "success") {
				parent.alertBox.showAlert(data.resultMsg, 'info');
			} else if (data.resultType == "failure") {
				parent.alertBox.showAlert(data.resultMsg, 'warning');
			} else {
				parent.alertBox.showAlert(data.resultMsg, 'error');
			}
		});
	}

	function deleteDemo() {
		jsutil.notDataReq(mUrl.deleteDemo+'15', function(data) {
			if (data.resultType == "success") {
				parent.alertBox.showAlert(data.resultMsg, 'info');
			} else if (data.resultType == "failure") {
				parent.alertBox.showAlert(data.resultMsg, 'warning');
			} else {
				parent.alertBox.showAlert(data.resultMsg, 'error');
			}
		});
	}

	function queryDemoPaging() {
		jsutil.defaultSerializeReq(mUrl.queryDemoPaging, {
			'page' : 1,
			'pageRows' : '15'
		}, function(data) {
			console.info(data.rows);
			console.info(data.total);
		});
	}

	function queryDemoById() {
		jsutil.notDataReq(mUrl.queryDemoById+'14', function(data) {
			console.info(data);
		});
	}
</script>
</head>

<body>
	<button onclick="saveDemo();">saveDemo</button>
	<button onclick="updateDemo();">updateDemo</button>
	<button onclick="deleteDemo();">deleteDemo</button>
	<button onclick="queryDemoPaging();">queryDemoPaging</button>
	<button onclick="queryDemoById();">queryDemoById</button>
</body>
</html>
