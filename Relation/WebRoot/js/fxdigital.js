$("input[type=button], input[type=submit], button").button();
$(".datepicker").datetimepicker({
	controlType: 'select',
	timeFormat: 'HH:mm:ss'
	//changeMonth : true,
	//changeYear : true,
	//autoSize : true,
});
$(".datepicker").attr("readonly","readonly");

function jump(name, data) {
	$("#main-content").empty();
	$("#main-content").html(data);
}

var mDialog = null;
function createMessageDialog(message,type){
	$("#dialog-message").remove();
	var vMessageDialog = "<div id=\"dialog-message\"></div>";
	$(vMessageDialog).appendTo($("body"));
	if(type == "alert"){
		$("#dialog-message").attr("title","警告");
		$("#dialog-message").css("color","red");
	}else if(type == "notice"){
		$("#dialog-message").attr("title","通知");
	}else if(type == "check"){
		$("#dialog-message").attr("title","检查");
	}else if(type == "close"){
		$("#dialog-message").attr("title","失败");
		$("#dialog-message").css("color","red");
	}else{
		$("#dialog-message").attr("title","信息");
	}
	if(type == "" || type == null){
		$("<p></span>" + message + "</p>").appendTo($("#dialog-message"));
	}else{
		$("<p><span class=\"ui-icon ui-icon-"+type+"\" style=\"float:left; margin:0 7px 20px 0;\"></span>" + message + "</p>").appendTo($("#dialog-message"));
	}
	mDialog = $("#dialog-message").dialog({
		modal : true,
		autoOpen : false,
		buttons : {
			"确定" : function() {
				mDialog.dialog("close");
			}
		},
		close : function() {
			mDialog.empty();
			mDialog.dialog("destroy");
			mDialog = null;
		}
	});
}
function messageDialog(message,type) {
	if (mDialog == null) {
		if(type == "fail"){
			type = "close";
		}else if(type == "success"){
			type = "check";
		}
		createMessageDialog(message,type);
	}else{
		mDialog.dialog("close");
		mDialog.dialog("destroy");
	}
	mDialog.dialog("open");
}
function appendMessage(message,type){
	if(mDialog == null){
		if(type == "fail"){
			type = "close";
		}else if(type == "success"){
			type = "check";
		}
		messageDialog(message,type);
	}else{
		$("<p><span class=\"ui-icon ui-icon-"+type+"\" style=\"float:left; margin:0 7px 20px 0;\"></span>" + message + "</p>").appendTo($("#dialog-message"));
	}
}

var vDialogCallback;
var cDialog = null;
function createConfirmDialog(message){
	$("#dialog-confirm").remove();
	var vConfirmDialog = "<div id=\"dialog-confirm\" title=\"确认\"></div>";
	$(vConfirmDialog).appendTo($("body"));
	$("<p><span class=\"ui-icon ui-icon-alert\" style=\"float:left; margin:0 7px 20px 0;\"></span>" + message + "</p>").appendTo($("#dialog-confirm"));
	cDialog = $("#dialog-confirm").dialog({
		modal : true,
		autoOpen : false,
		buttons : {
			"是" : function() {
				$(this).dialog("close");
				vDialogCallback(true);
			},
			"否" : function() {
				$(this).dialog("close");
				vDialogCallback(false);
			}
		},
		close : function() {
			cDialog.empty();
			cDialog.dialog("destroy");
			cDialog = null;
		}
	});
}
function confirmDialog(message,callback) {
	vDialogCallback = callback;
	if (cDialog == null) {
		createConfirmDialog(message);
	}
	cDialog.dialog("open");
}