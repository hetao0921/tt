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

		<title>My JSP 'left.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="./js/fxdigital.js"></script>
	
		<script type="text/javascript">
		$(function() {
			jQuery("#jqGrid").jqGrid(
				{
					url : 'getloginfo',
					datatype : "json",
					mtype : "POST",
					height:"100%",
					autowidth  :true,
					colNames : [ '中心Id', '中心名称', '操作日期','操作类型','详细信息','错误类型'],
					colModel : [ {
						name : 'centerid',
						index : 'centerid',
						width : '15%',
						sorttype : "int"
					}, {
						name : 'centername',
						index : 'centername',
						width : '20%',
						sorttype : "date"
					}, {
						name : 'operatetime',
						index : 'operatetime',
						width : '20%'
					}, {
						name : 'operate',
						index : 'operate',
						width : '10%'
					}, {
						name : 'operateinfo',
						index : 'operateinfo',
						width : '25%'
					}, {
						name : 'errorinfo',
						index : 'errorinfo',
						width : '10%'
					}],
					rowNum : 40,
					rowList : [ 10, 20, 30 ],
					pager : 'plist47',
					//sortname : 'id',
					viewrecords : true,
					//sortorder : "desc",
					loadonce : true,
					//caption : "日志记录"
				}).navGrid('#plist47', { add: false, edit: false, del: false,search:false,refresh:false });
		doResize();
		Event.onResizend(function() {
			doResize();
		});
		function doResize() {
			var mainContentHeight = $("#main-content").height();
			var mainContentWidth = $("#main-content").width();
			var buttonsHeight = $("#buttons").height();
			var tableHeadHeight = $("#jqgh_jqGrid_centerid").height();
			var pagerHeight = $("#plist47").height();
			$("#jqGrid").setGridHeight(mainContentHeight - buttonsHeight-pagerHeight-tableHeadHeight-8);
			$("#jqGrid").setGridWidth(mainContentWidth-2);
		}
		});
</script>
	</head>

	<body bgcolor="#989CAC">
				<table id="jqGrid"></table>
			<div id="plist47">
			</div>
	</body>
</html>
