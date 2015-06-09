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
	
	var json="${key}";
	$.post("isrest",{"centerid":json},function(data){
		if(data=="0"){
			getdata();
		}else{
			messageDialog("远程同步服务器异常！","notice");
		}
		},"text");
		});
		
	
	function getdata(){
			$.post("downserverdata",{"json" : "json"},function(data){
			//$('#accordion')).accordion("refresh");
			var str=new Array();
				str=data.split("_");
			if(str[0]=="0"){
				showdata(str[1]);
			}else if(str[0]=="1"){
				showdata(str[1]);
			}else if(str[0]=="2"){
				backupfail();
			}else{
			getdata();
		}
		},"text");
		}
		
	function backupfail(){
		 $.get("onloadfail",function(data){
			jump("链接错误",data);
		});
	}
	
	function showdata(data){
	var mydata=eval(data);
	//var str=[{"enddate":"2014-10-29 15:32:55","flag":"6","id":"379","resetState":"正在发送","startdate":"2014-10-29 15:32:55","uuid":"lch@001","uuname":"lch","version":"437"},{"enddate":"2014-10-29 15:32:55","flag":"7","id":"380","resetState":"发送成功","startdate":"2014-10-29 15:32:55","uuid":"lch@001","uuname":"lch","version":"437"},{"enddate":"2014-10-29 15:32:55","flag":"10","id":"381","resetState":"正在解包","startdate":"2014-10-29 15:32:55","uuid":"lch@001","uuname":"lch","version":"437"},{"enddate":"2014-10-29 15:32:55","flag":"12","id":"382","resetState":"开始解析xml","startdate":"2014-10-29 15:32:55","uuid":"lch@001","uuname":"lch","version":"437"},{"enddate":"2014-10-29 15:32:55","flag":"14","id":"383","resetState":"正在插入数据","startdate":"2014-10-29 15:32:55","uuid":"lch@001","uuname":"lch","version":"437"},{"enddate":"2014-10-29 15:32:55","flag":"15","id":"384","resetState":"插入成功","startdate":"2014-10-29 15:32:55","uuid":"lch@001","uuname":"lch","version":"437"},{"enddate":"2014-10-29 15:32:55","flag":"17","id":"385","resetState":"还原成功","startdate":"2014-10-29 15:32:55","uuid":"lch@001","uuname":"lch","version":"437"}];
			jQuery("#jqGrid").jqGrid(
				{
					data : mydata,
					datatype : "local",
					//mtype : "POST",
					height:"100%",
					autowidth  :true,
					colNames : [ '中心Id', '中心名称', '开始时间','还原版本','结束时间','还原状态'],
					colModel : [ {
						name : 'uuid',
						index : 'uuid',
						width : '15%',
						sorttype : "int"
					}, {
						name : 'uuname',
						index : 'uuname',
						width : '20%',
						sorttype : "date"
					}, {
						name : 'startdate',
						index : 'startdate',
						width : '20%'
					}, {
						name : 'version',
						index : 'version',
						width : '10%'
					}, {
						name : 'enddate',
						index : 'enddate',
						width : '25%'
					}, {
						name : 'resetState',
						index : 'resetState',
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
				}).navGrid('#plist47', { add: true, edit: true, del: true,search:true,refresh:true });
		doResize();
		Event.onResizend(function() {
			doResize();
		});
		function doResize() {
			var mainContentHeight = $("#main-content").height();
			var mainContentWidth = $("#main-content").width();
			var buttonsHeight = $("#buttons").height();
			var tableHeadHeight = $("#jqgh_jqGrid_uuid").height();
			var pagerHeight = $("#plist47").height();
			$("#jqGrid").setGridHeight(mainContentHeight - buttonsHeight-pagerHeight-tableHeadHeight-8);
			$("#jqGrid").setGridWidth(mainContentWidth-2);
		}
		}
</script>
	</head>

	<body bgcolor="#989CAC">
		<table id="jqGrid"></table>
		<div id="plist47">
		</div>
	</body>
</html>
