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
					url : 'getupinfo',
					datatype : "json",
					mtype : "POST",
					height : "100%",
					autowidth : true,
					colNames : [ '升级包类型', '版本号', '升级包名称', '包大小', '软件等级', '包类型',
							'发布时间', '包Id','包字节' ],
					colModel : [ {
						name : 'softtype',
						index : 'softtype',
						align: 'center',
						width : '15%'
					}, {
						name : 'softversion',
						index : 'softversion',
						align: 'center',
						width : '15%'
					}, {
						name : 'filename',
						index : 'filename',
						align: 'center',
						width : '25%'
					}, {
						name : 'filesize',
						index : 'filesize',
						align: 'center',
						width : '10%'
					}, {
						name : 'softlevel',
						index : 'softlevel',
						align: 'center',
						width : '10%'
					}, {
						name : 'softuptype',
						index : 'softuptype',
						align: 'center',
						width : '15%'
					}, {
						name : 'publishdate',
						index : 'publishdate',
						align: 'center',
						width : '15%'
					}, {
						name : 'fileID',
						index : 'fileID',
						width : '0%',
						hidden : true
					}, {
						name : 'filelength',
						index : 'filelength',
						width : '0%',
						hidden : true
					} ],
					rowNum : 40,
					rowList : [ 10, 20, 30 ],
					pager : 'plist47',
					//sortname : 'id',
					viewrecords : true,
					//sortorder : "desc",
					loadonce : true,
					//caption : "升级服务"
					loadComplete: function(xhr) {
					 var rowNum = parseInt($(this).getGridParam("records"), 10);
					  if (rowNum <= 0) { appendMessage("没有记录!","notice"); }
						} 
				}).navGrid('#plist47', {
			add : false,
			edit : false,
			del : false,
			search : false,
			refresh : false
		});
		//var vHeight = $(window).height();
		//var vWidth = $(window).width();
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
		
		var add_dialog = null;
		function createAddDialog() {
			add_dialog = $("#modal-dialog").dialog({
				autoOpen : false,
				height : 250,
				width : 450,
				modal : true,
				buttons : {
					"完成" : function() {
						add_dialog.dialog("destroy");
						refresh();
					},
					"取消" : function() {
						add_dialog.dialog("close");
						refresh();
					}
				},
				close : function() {
					add_dialog.find("form")[0].reset();
					add_dialog.dialog("destroy");
				}
			});
		}
		
		var add_showplan = null;
		function createshowplan() {
			add_showplan = $("#modal-showplan").dialog({
				autoOpen : false,
				height : 250,
				width : 450,
				modal : true,
				buttons : {
					"完成" : function() {
						add_showplan.dialog("destroy");
						refresh();
					},
					"取消" : function() {
						add_showplan.dialog("close");
						refresh();
					}
				},
				close : function() {
					add_showplan.find("form")[0].reset();
					add_showplan.dialog("destroy");
				}
			});
		}
		
		$('#add').click(function() {
			var selectedId = $("#jqGrid").jqGrid("getGridParam", "selrow");
			var rowData = $("#jqGrid").jqGrid("getRowData", selectedId);
			if (selectedId == null) {
				messageDialog("请选择要升级版本！", "notice");
				return;
			}
			$("#modal-dialog").attr("title", "升级过程监控");
			$("#progressbar").progressbar({
				value : 0
			});
			isdownpack(rowData.fileID, rowData.filelength);
			createAddDialog();
			add_dialog.dialog("open");
		});
	
		//是否可以下载
		function isdownpack(fileid, filesize) {
			var offset = 0;
			$.post("isdownpack", {
				"fileid" : fileid
			}, function(data) {
				offset = parseFloat(data);
				//alert("data:"+offset);
				if (offset == filesize) {
					//alert("已经下载：" + offset);
					$("#progressbar").progressbar("option", "value", 30);
					$("#address").val("已经下载！");
						nextun(fileid);
				} else if (offset == 0) {
					//alert("开始下载：" + offset + ",fileid:" + fileid);
					begindown(offset, fileid);
				} else {
					//alert("继续下载：" + offset);
					begindown(offset, fileid);
				}
			});
		}

	
		//开始下载
		function begindown(offset, fileid) {
			var downpram = fileid + "," + offset;
		//	alert("downpram:" + downpram);
			$.ajax({
				url : "downPacket",
				type : "post",
				data : {
					"downpram" : downpram
				},
				beforeSend : function() {
					showdata(0);
				},
				complete : function() {
				showdata(20);
				},
				success : function(data) {
					showdata(30);
					$("#address").val(data);
					nextun(fileid);
				},
				error : function() {
					showdata(30);
					$("#address").val("下载失败！");
				}
			});
		}

		function showdata(data) {
			var count = parseInt(data);
			$("#progressbar").progressbar("option", "value", count);
		}


		//开始解压
		function beginunzip(fileid) {
			//alert("fileid:" + fileid);
			$.ajax({
				url : "unZip",
				type : "post",
				data : {
					"fileid" : fileid
				},
				beforeSend : function() {
					$("#txtAddress").val("解压过程！");
				},
				complete : function() {
				    showdata(60);
				},
				success : function(data) {
					$("#address").val("解压完成！");
					$("#progressbar").progressbar("option", "value", 60);
					nextup(data);
				},
				error : function(data) {
					showdata(60);
					$("#address").val(data);
				}
			});
		}


			//开始升级
		function beginupgrade() {
			//alert("filepath:" + filepath);
			$.ajax({
				url : "upGrade",
				type : "post",
				data : {
					"filepath" : "filepath"
				},
				beforeSend : function() {
					$("#txtAddress").val("升级过程！需要等待30s...");
					$("#address").val("升级中！");
				},
				complete : function() {
				showdata(100);
				},
				success : function(data) {
					//alert(data);
					if("0"==data){
					$("#txtAddress").val("升级过程！");
						$("#address").val("升级成功！");
						appendMessage("重启系统生效！", "notice");
					}else{
						$("#address").val(data);
					}
					//$("#progressbar").progressbar("option", "value", 100);
				},
				error : function() {
					showdata(90);
					$("#address").val(data);
				}
			});
		}
	

		function nextun(fileid) {
			confirmDialog("下一步解压，确认解压？", function(result) {
				if (result) {
					beginunzip(fileid);
				} else {
					add_dialog.dialog("destroy");
				}
			});
		}

		function nextup() {
			confirmDialog("下一步升级！确认升级？", function(result) {
				if (result) {
					beginupgrade();
				} else {
					add_dialog.dialog("destroy");
				}
			});
		}
		function refresh() {
		$.post("definedupserver", function(data) {
			jump("自定义升级", data);
		});
		}
		
		$('#rollback').click(function() {
				$.post("isrollback", function(data) {
				if(data==0){
				appendMessage("没有还原版本", "notice");
				}else{
					var str= new Array();   
		        	str=data.split(",");  
		        	var notice="确认从"+str[0]+"回滚到"+str[1];
					confirmDialog(notice, function(result) {
				if (result) {
					//setTimeout(function sleep(){rollback();}, "300");
					rollback();
				} else {
					
				}
			});
		}
		});
		});
		
		function rollback(){
			$("#modal-showplan").attr("title", "回滚监控过程");
			createshowplan();
			add_showplan.dialog("open");
			$("#loadstate").show();
			$.post("rollback", function(data) {
			$("#plantext").val(data);
			$("#loadstate").hide();
		});
		}
		
	});
	
		
</script>
</head>

<body bgcolor="#989CAC">
	<div id="buttons">
		<input id="add" type="button" value="升级">
		<input id="rollback" type="button" value="还原旧版本">
	</div>
	<table id="jqGrid"></table>
	<div id="plist47"></div>
	<div id="modal-dialog" style="display: none;">
		<form>
			<input type="text" id="txtAddress" value="下载过程" readonly="readonly" style="border-left:0px;border-top:0px;border-right:0px;border-bottom:1px"
				class="text ui-widget-content ui-corner-all" /> 
				<br><br>
				<div id="progressbar"></div>
			<br> <input id="address" type="text" name="txtAddress" style="border-left:0px;border-top:0px;border-right:0px;border-bottom:1px"
				value="开始下载" class="text ui-widget-content ui-corner-all" /> <br>
	   </form>
	</div>
	<div id="modal-showplan" style="display: none;">
	<br> <input id="plantext" type="text" name="plantext" style="border-left:0px;border-top:0px;border-right:0px;border-bottom:1px"
				value="回滚中" class="text ui-widget-content ui-corner-all" />
		<div id="loadstate" align="center" style="display: none">
			<p>
				<img src="media/img/uploading.gif">
			</p>
		</div>
	</div>
</body>
</html>
