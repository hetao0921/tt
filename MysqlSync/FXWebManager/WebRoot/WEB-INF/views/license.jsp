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

<title>授权</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="./uploadify/uploadify.css" type="text/css">
<script src="./js/fxdigital.js"></script>
<script src="./uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
		$("#file_upload").uploadify({
			'buttonText' : '选择授权文件',
			'swf' : './uploadify/uploadify.swf',
			'uploader' : 'importFileLicense',
			//'fileTypeExts' : '*.xls; *.xlsx',
			'auto' : false,
			'successTimeout' : 30,
			'onUploadSuccess' : function(file, data, response) {
				if (data == null || data == "") {
					messageDialog("处理超时！","alert");
				} else if (data == "true") {
					messageDialog("导入成功！","success");
				} else if (data == "false"){
					messageDialog("导入失败！","fail");
				} else {
					messageDialog(data,"fail");
				}
			}
		});
		$('#txtImport').click(function() {
			var txtLicense = $('#txtLicense').val();
			if(txtLicense == ""){
				messageDialog("授权码不能为空！","alert");
				return;
			}
			$.post("importTxtLicense",
					{"license":txtLicense},
					function(data){
				if(data == "true"){
					messageDialog("导入成功！","success");
				} else if (data == "false"){
					messageDialog("导入失败！","fail");
				} else {
					messageDialog(data,"fail");
				}
			});
		});
		$('#fileImport').click(function() {
			$('#file_upload').uploadify('upload');
		});
</script>

</head>

<body>
	<div align="center">
		<br/><br/>
		<span>Licese授权码：</span>
		<input type="text" id="txtLicense" size="30" />
		<input id="txtImport" type="button" value="导入" />
		<br/><br/>
	</div>
	<hr>
	<div align="center">
		<br/><br/>
		<span>从文件导入 ：</span>
		<br/><br/>
		<input type="file" id="file_upload" name="file_upload">
		<input id="fileImport" type="button" value="导入" />
	</div>
</body>
</html>
