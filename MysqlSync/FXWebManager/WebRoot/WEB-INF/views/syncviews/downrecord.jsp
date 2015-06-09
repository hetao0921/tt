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
		$("#downbutton").attr('disabled',true); 
		$("#resetbutton").attr('disabled',true); 
			jQuery("#jqGrid").jqGrid(
				{
					url : 'getdowninfo',
					datatype : "json",
					mtype : "POST",
					height:"100%",
					autowidth  :true,
					colNames : [ '中心ID', '单位名称', '单位IP','本地版本','服务器版本','下载日期','下载状态'],
					colModel : [ {
						name : 'centerid',
						index : 'centerid',
						width : '20%',
						sorttype : "int"
					}, {
						name : 'centername',
						index : 'centername',
						width : '25%',
						sorttype : "date"
					}, {
						name : 'centerip',
						index : 'centerip',
						width : '10%'
					}, {
						name : 'clientversion',
						index : 'clientversion',
						width : '10%'
					}, {
						name : 'serverversion',
						index : 'serverversion',
						width : '10%'
					}, {
						name : 'update',
						index : 'update',
						width : '15%'
					},{
						name : 'isload',
						index : 'isload',
						width : '10%'
					}],
					gridComplete : function(){
						$("#downbutton").removeAttr('disabled'); 
						$("#resetbutton").removeAttr('disabled');
					},
					rowNum : 40,
					rowList : [ 10, 20, 30 ],
					pager : 'plist47',
					viewrecords : true,
					loadonce : true,
					//caption : "下载记录"
				}).navGrid('#plist47', { add: true, edit: true, del: true,search:true,refresh:true});

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
	
	function getdownlock(){
		$.post("getdownlock",{},
				function(data) {
					if (data == "0") {
						$("#downbutton").attr('disabled',false); 
						$("#resetbutton").attr('disabled',true); 
						} else {
						$("#downbutton").attr('disabled',true); 
						$("#resetbutton").attr('disabled',false); 
						}
					}, "text");
		}

	function getTdValue(){
        var tab=document.getElementById('jqGrid');
        var json="[";
        for(var i=1 ;i<tab.rows.length;i++)
        {
        if(i==tab.rows.length-1){
         json=json+"{";
            json=json+"\"centerid\":"+"\""+tab.rows[i].cells[0].innerHTML+"\",";
            json=json+"\"centername\":"+"\""+tab.rows[i].cells[1].innerHTML+"\",";
            json=json+"\"centerip\":"+"\""+tab.rows[i].cells[2].innerHTML+"\",";
            json=json+"\"clientversion\":"+"\""+tab.rows[i].cells[3].innerHTML+"\",";
            json=json+"\"serverversion\":"+"\""+tab.rows[i].cells[4].innerHTML+"\",";
            json=json+"\"update\":"+"\""+tab.rows[i].cells[5].innerHTML+"\",";
            json=json+"\"isload\":"+"\""+tab.rows[i].cells[6].innerHTML+"\"";
            json=json+"}";
        }else{
            json=json+"{";
            json=json+"\"centerid\":"+"\""+tab.rows[i].cells[0].innerHTML+"\",";
            json=json+"\"centername\":"+"\""+tab.rows[i].cells[1].innerHTML+"\",";
            json=json+"\"centerip\":"+"\""+tab.rows[i].cells[2].innerHTML+"\",";
            json=json+"\"clientversion\":"+"\""+tab.rows[i].cells[3].innerHTML+"\",";
            json=json+"\"serverversion\":"+"\""+tab.rows[i].cells[4].innerHTML+"\",";
            json=json+"\"update\":"+"\""+tab.rows[i].cells[5].innerHTML+"\",";
            json=json+"\"isload\":"+"\""+tab.rows[i].cells[6].innerHTML+"\"";
            json=json+"},";
            }
        }
        json=json+"]";
     return json;
    }
    
    function isdownload(){
    var json=getTdValue();
    	 $.post("isdownload",{"json":json},function(data){
        if(data=="0"){
        	alert("下载中，请复位再次下载！");
        	getdownlock();
        }else if(data=="2"){
        	 downloading();
        }else{
        alert("是最新数据！无需下载！");
        getdownlock();
        }
        },"text");
    }
    function downloading(){
    	$.get("downloading",function(data) {
				jump("下载页面", data);
			});
    }
		
		function resetd() {
		if (confirm("确认复位？可能其他管理员正在进行下载操作！")) {
			$.post("undownlock", {
				"unlock" : "0"
			}, function(data) {
				alert(data);
				getdownlock();
			});
		}
	}
</script>
	</head>

	<body bgcolor="#989CAC">
	<div id="buttons">
			<input type="button" id="downbutton" value="下载" onclick="isdownload()" >
			<input type="button" id="resetbutton" value="复位" onclick="resetd()" >
	</div>
			<table id="jqGrid"></table>
			<div align="center" id="plist47">
			</div>
	</body>
</html>
