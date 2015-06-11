<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>服务器日志</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script src="./js/fxdigital.js"></script>
<script src="./layer/layer.min.js"></script>

<script type="text/javascript">
	$(function() {
		jQuery("#serverLog").jqGrid(
				{
					url : 'getServerLogs',
					datatype : "json",
					mtype : "POST",
					autowidth : true,
					colNames : [ '文件名称', '文件大小', '最后修改日期' ],
					colModel : [ {
						name : 'name',
						index : 'name',
						width : '30%',
						sorttype : "text",
						resizable : true,
					}, {
						name : 'size',
						index : 'size',
						width : '30%',
						sorttype : "text",
						resizable : true,
					}, {
						name : 'date',
						index : 'date',
						width : '40%',
						sorttype : "text",
						resizable : true,
					} ],
					rowNum : 20,
					rowList : [ 10, 20, 30 ],
					pager : '#pglist',
					pagerpos : 'right',
					recordpos : 'left',
					viewrecords : true,
					loadonce : true,
				}).navGrid('#pglist', {
					add : false,
					edit : false,
					del : false,
					search : false,
					refresh : false,
				});
		$("#serverLog").closest(".ui-jqgrid-bdiv").css({
			'overflow-x' : 'hidden'
		});
		
		doResize();
		Event.onResizend(function() {
			doResize();
		});
		function doResize() {
			var mainContentHeight = $("#main-content").height();
			var mainContentWidth = $("#main-content").width();
			var buttonsHeight = $("#buttons").height();
			var tableHeadHeight = $("#jqgh_serverLog_name").height();
			var pagerHeight = $("#pglist").height();
			$("#serverLog").setGridHeight(mainContentHeight - buttonsHeight-pagerHeight-tableHeadHeight-8);
			$("#serverLog").setGridWidth(mainContentWidth-2);
		}
		
		$("#download").click(function(){
			var selectedId = $("#serverLog").jqGrid("getGridParam", "selrow");
			var rowData = $("#serverLog").jqGrid("getRowData", selectedId);
			if(selectedId == null){
				messageDialog("请选择一行数据！","notice");
				return;
			}
			//window.location.target = "_blank";
			//window.location.href = "download?filename="+rowData.name;
			window.open("download?filename="+rowData.name, "_blank");
		});
	});
</script>

</head>

<body>
	<div id="buttons">
		<input id="download" type="button" value="下载">
	</div>
	<table id="serverLog"></table>
	<div id="pglist"></div>
</body>
</html>
