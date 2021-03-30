
/**
 * @author cjh
 * @date 2013-4-19 下午20:39:23
 * JQuery 遮罩层
 */
$(function () {
    //初始化在body中添加一个div层
    var htmlStr="<div id='maskDivBg' class='mask-div-bg'>";
    htmlStr+="<div id='maskWin' class='mask-win'>";
    htmlStr+="<table>";
      htmlStr+="<tr style='height:35px'>";
        htmlStr+="<td style='width:35px'>";
          htmlStr+="<div class='loading-div'></div>";
        htmlStr+="</td>";
        htmlStr+="<td style='width:100px'>";
          htmlStr+="<div>疯狂处理中...</div>";
        htmlStr+="</td>";
      htmlStr+="</tr>";
     htmlStr+="</table>";
    htmlStr+="</div>";
    htmlStr+="</div>";
	$("body").append(htmlStr);
});

//显示
function showMask() {
    $("#maskDivBg").css({ display: "block", height: $(document).height()});
	$("#maskWin").css("display", "block");
}

//关闭
function closeMask() {
    $("#maskDivBg").css('display','none');
	$("#maskWin").css("display", "none");
}

