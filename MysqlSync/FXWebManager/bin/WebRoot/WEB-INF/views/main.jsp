<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- 严格型XHTML（jqGrid支持的HTML类型） -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<base href="<%=basePath%>"/>

<title>后台网页配置管理</title>

<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
<meta http-equiv="description" content="main"/>
<link href="./jquery-ui/default/jquery-ui.css" rel="stylesheet"/>
<link href="./ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"/>
<link href="./css/jquery-ui-timepicker-addon.css" rel="stylesheet"/>
<link href="./css/ui.jqgrid.css" rel="stylesheet"/>

<script src="./js/jquery-1.11.1.min.js"></script>
<script src="./js/jquery-ui.js"></script>
<script src="./js/jquery-ui-timepicker-addon.js"></script>
<script src="./js/jquery-ui-timepicker-zh-CN.js"></script>
<script src="./js/jquery.layout-latest.js"></script>
<script src="./ztree/js/jquery.ztree.core-3.5.js"></script>
<script src="./js/fxdigital.js"></script>
<script src="./js/jquery.jqGrid.min.js"></script>
<script src="./js/grid.addons.js"></script>
<script src="./js/grid.locale-cn.js"></script>

<script src="./js/load.js"></script>
<script src="./js/Event.js"></script>

<script>
	var setting = {
		view : {
			showLine : false,
			//showIcon: showIconForTree,
			fontCss : {
				size : "16"
			},
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pid",
			}
		},
		callback : {
			onClick : onClick,
		}
	};

	function showIconForTree(treeId, treeNode) {
		return !treeNode.isParent;
	}

	function onClick(event, treeId, treeNode, clickFlag) {
		var url = treeNode.diyUrl;
		var name = treeNode.name;
		if(url != null && url != ""){
			$.post(url, function(data) {
				jump(name, data);
			});
		}
		event.preventDefault();
	}

	$(document).ready(function() {
		$('body').layout({
			applyDefaultStyles : true,
			north__closable : false,
			north__resizable : false,
			west__resizable : false,
			west__closable : false,
			south__resizable : false,
			south__closable : false,
		});
		$.get("getMenu", function(data) {
			$.fn.zTree.init($("#tree"), setting, eval(data));
			//var treeObj = $.fn.zTree.getZTreeObj("tree");
			//treeObj.expandAll(true);
		});

		$("#main-content").css("overflow", "hidden");
		
		$("#theme li div").bind('click',function(){
			$("#theme li div[class='selected']").removeAttr('class');
			$(this).attr('class','selected');
			
			var theme = $(this).parent().attr('class');
			$("<link>").attr({ rel: "stylesheet",
				href: "./jquery-ui/"+theme+"/jquery-ui.css"}).appendTo("head");
			$(".headerNav").css('background','url(media/img/header_bg_'+theme+'.png) no-repeat fixed 100% -50px');
			$("#header").css('background','url(media/img/header_bg_'+theme+'.png) repeat-x fixed 0 0');
			if(theme == "azure"){
				$("#header").css('color','black');
			}else{
				$("#header").css('color','white');
			}
			//$.getScript("jquery-ui/"+theme+"/jquery-ui.js");
		});
		$( document ).tooltip();
	});
</script>

<style type="text/css">
/*
.ui-jqdialog {font-size:16px;}
.ui-widget {
	font-size: 12px;
}
.ui-jqgrid .ui-jqgrid-htable th div {
	height: 20px;
}
.ui-jqgrid .ui-pg-input {
	height: 18px;
}
*/
.ui-jqgrid .ui-jqgrid-view {
	font-size: 14px;
}
.ui-jqgrid .ui-jqgrid-pager {
	font-size: 14px;
}
.ui-jqgrid .ui-jqgrid-view button {
	font-size: 14px;
}

#theme li div { background:url(media/img/themeButton.png) no-repeat;}
#theme { position:absolute; right:5px; bottom:5px;}
#theme li { list-style-type:none; float:left; padding:0 3px;}
#theme li div { display:block; overflow:hidden; width:13px; height:11px; text-indent:-100px; cursor:pointer;}
#theme li.default div { background-position:0 0;}
#theme li.default .selected { background-position:0 -20px;}
#theme li.green div { background-position:-20px 0;}
#theme li.green .selected { background-position:-20px -20px;}
#theme li.purple div { background-position:-60px 0;}
#theme li.purple .selected { background-position:-60px -20px;}
#theme li.silver div { background-position:-80px 0;}
#theme li.silver .selected { background-position:-80px -20px;}
#theme li.azure div { background-position:-100px 0;}
#theme li.azure .selected { background-position:-100px -20px;}

.headerNav {
	height: 50px;
	background: url(media/img/header_bg_default.png) no-repeat fixed 100% -50px;
}
#header {
	text-align: center;
	color: white;
	font-size: 30px;
	background: url(media/img/header_bg_default.png) repeat-x fixed 0px 0px;
}

</style>
</head>

<body>
	<div class="ui-layout-center">
		<!-- 标题 -->
		<div id="main-header" class="ui-widget-header" style="font-size: 17px">
			<b>您当前的位置：</b>[<span id="menu-level1"></span>]
		</div>
		<!-- 内容 -->
		<div id="main-content" class="ui-layout-content"></div>
	</div>
	<div class="ui-layout-north">
		<div id="header">
			<div class="headerNav">
				<label style="height: 50px;line-height:50px;">后台网页配置管理</label>
				<div id="theme">
					<ul>
						<li class="default"><div class='selected' title="蓝色">蓝色</div></li>
						<li class="green"><div title="绿色">绿色</div></li>
						<li class="purple"><div title="紫色">紫色</div></li>
						<li class="silver"><div title="银色">银色</div></li>
						<li class="azure"><div title="天蓝">天蓝</div></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="ui-layout-south" style="font-size: 12px;">
		<div
			style="background-color: #DDDDDD;text-align: center;white-space: nowrap;">
			<label>版本信息：V 1.0.0.0</label>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label>版权所有：北京飞讯数码科技有限公司</label>
		</div>
	</div>
	<div class="ui-layout-west">
		<div class="ui-widget-header" style="font-size: 17px">
			<b>目录：</b>
		</div>
		<ul id="tree" class="ztree"></ul>
	</div>
</body>
</html>
