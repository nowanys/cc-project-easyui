
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<base href="<%=basePath%>">

<script type="text/javascript">
   var webPath= '<%=basePath%>';
</script>

<link rel="stylesheet" type="text/css" charset="utf-8" href="common/js/easyUI/themes/icon.css">
<link rel="stylesheet" type="text/css" href="common/js/customUI/css/mask-box.css">

<script type="text/javascript" charset="utf-8" src="common/js/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="common/js/jquery.cookie.js"></script>
<script type="text/javascript" charset="utf-8" src="common/js/jsutil.js"></script>

<script type="text/javascript" charset="utf-8" src="common/js/easyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" charset="utf-8" src="common/js/easyUI/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="common/js/easyUI/extend/easyui-extend.js"></script>

<script type="text/javascript" charset="utf-8" src="common/js/customUI/alert-box.js"></script>
<script type="text/javascript" charset="utf-8" src="common/js/customUI/mask-box.js"></script>

<script type="text/javascript" charset="utf-8" src="pages/manage/js/common-mu.js"></script>




<script type="text/javascript">

//初始化个性化主题
initIndividuationTheme();

/**
 *  初始化个性化主题
 */
function initIndividuationTheme(){
   //根据个性化初始化主题
    var individuationThemeVal = $.cookie('INDIVIDUATION_THEME_TYPE');
    var themeVal="gray";
	if(individuationThemeVal){
		themeVal=individuationThemeVal;
	}

	window.document.write('<link id="easyuiThemeLink" rel="stylesheet" type="text/css" charset="utf-8" href="common/js/easyUI/themes/default/easyui-'+themeVal+'.css">');
	window.document.write('<link id="themeLink" rel="stylesheet" type="text/css" charset="utf-8" href="pages/manage/css/theme-'+themeVal+'.css">');
}

</script>




