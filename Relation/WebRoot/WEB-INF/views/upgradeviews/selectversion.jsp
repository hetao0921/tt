<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

		<title>My JSP 'upserver.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="./js/fxdigital.js"></script>
	
		<script type="text/javascript">
		$(function() {
			jQuery("#jqGrid").jqGrid(
				{
					url : 'getUprecord',
					datatype : "json",
					mtype : "POST",
					height:"100%",
					autowidth  :true,
					colNames : [ '软件包名称', '升级前版本', '升级后版本','产品类型','升级状态','升级时间'],
					colModel : [ {
						name : 'softname',
						index : 'softname',
						align: 'center',
						width : '15%'
					}, {
						name : 'softversion',
						index : 'softversion',
						align: 'center',
						width : '15%'
					}, {
						name : 'softcurversion',
						index : 'softcurversion',
						align: 'center',
						width : '25%'
					},{
						name : 'softtype',
						index : 'softtype',
						align: 'center',
						width : '15%'
					},{
						name : 'operatetatus',
						index : 'operatetatus',
						align: 'center',
						width : '15%'
					},{
						name : 'upgradedate',
						index : 'upgradedate',
						align: 'center',
						width : '15%'
					}],
					rowNum : 40,
					rowList : [ 10, 20, 30 ],
					pager : 'plist47',
					//sortname : 'id',
					viewrecords : true,
					//sortorder : "desc",
					loadonce : true,
					//caption : "本机升级记录"
					loadComplete: function(xhr) {
						getselfver();
						 var rowNum = parseInt($(this).getGridParam("records"), 10);
						  if (rowNum <= 0) { appendMessage("没有记录!","notice");  }
							} 
				}).navGrid('#plist47', { add: false, edit: false, del: false,search: false,refresh: false });
		doResize();
		Event.onResizend(function() {
			doResize();
		});
		function doResize() {
			var mainContentHeight = $("#main-content").height();
			var mainContentWidth = $("#main-content").width();
			var buttonsHeight = $("#buttons").height();
			var tableHeadHeight = $("#jqgh_jqGrid_admin").height();
			var pagerHeight = $("#plist47").height();
			$("#jqGrid").setGridHeight(
					mainContentHeight - buttonsHeight - pagerHeight
							- tableHeadHeight - 8);
			$("#jqGrid").setGridWidth(mainContentWidth - 2);
		}

		function getselfver(){
			$.post("getseftVer",function(data){
				$("#selfver").val(data);
			});
		}
		});
		
		
</script>
	</head>
	
	<body bgcolor="#989CAC">
	<div id="buttons">
		<label>服务器当前版本：</label>
		<input id="selfver" type="text" readonly="readonly" value="0">
	</div>
		<table id="jqGrid"></table>
		<div id="plist47">
		</div>
	</body>
</html>
