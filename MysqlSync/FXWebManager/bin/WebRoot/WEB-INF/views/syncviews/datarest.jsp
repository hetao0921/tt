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
		<style type="text/css">

</style>
<script type="text/javascript">	
		$(function() {
		$("#resetbn").attr('disabled',true); 
			jQuery("#jqGrid").jqGrid(
				{
					url : 'getserverinfo',
					datatype : "json",
					mtype : "POST",
					height:"100%",
					autowidth  :true,
					colNames : [ '中心ID', '中心名称', '单位IP','本地版本','服务器版本','下载日期'],
					colModel : [ {
						name : 'centerid',
						index : 'centerid',
						width : '15%',
						sorttype : "int"
					}, {
						name : 'centername',
						index : 'centername',
						width : '25%',
						sorttype : "date"
					}, {
						name : 'centerip',
						index : 'centerip',
						width : '20%'
					}, {
						name : 'clientversion',
						index : 'clientversion',
						width : '10%'
					}, {
						name : 'version',
						index : 'version',
						width : '10%'
					}, {
						name : 'update',
						index : 'update',
						width : '20%'
					}],
					gridComplete : function(){
						$("#resetbn").removeAttr('disabled'); 
					},
					rowNum : 40,
					rowList : [ 10, 20, 30 ],
					pager : 'plist47',
					//sortname : 'id',
					viewrecords : true,
					//sortorder : "desc",
					loadonce : true,
				//	caption : "测试实例"
				}).navGrid('#plist47', { add: true, edit: true, del: true,search:true,refresh:true });
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
		
		function isreset(){
		var selectedId = $("#jqGrid").jqGrid("getGridParam", "selrow");
		var rowData = $("#jqGrid").jqGrid("getRowData", selectedId);
		var parm=rowData.centerid+","+rowData.centername+","+rowData.version;
		databackup(parm);
		}
		
		 function databackup(parm){
    	$.post("databackup",{"parm":parm},function(data) {
				jump("数据还原页面", data);
			});
    }
</script>
	</head>
		<body bgcolor="#989CAC">
		<div id="buttons">
			<input type="button" id="resetbn" value="还原" onclick="isreset()" >
	</div>
				<table id="jqGrid"></table>
			<div id="plist47">
			</div>
	</body>
</html>
