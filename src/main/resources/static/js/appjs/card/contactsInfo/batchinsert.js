$().ready(function() {
	validateRule();
});
$.validator.setDefaults({
	submitHandler : function() {
		checkupload();
	}
});
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			contactsName : {
				required : true
			},
			isSharing : {
				required : true
			},
			myfile : {
				required : true
			},
		},
		messages : {
			contactsName : {
				required : icon + "请输入名称"
			},
			isSharing : {
				required : icon + "是否共享"
			},
			myfile : {
				required : icon + "请选择导入的文件"
			}
		}
	})
}
/*上传文件控件*/
$("#myfile").change(function() {
	var filename = $(this).val();
	var fileTypes = new Array("xls", "xlsx"); // 定义可支持的文件类型数组
	var fileTypeFlag = "0";
	var newFileName = filename.split('.');
	newFileName = newFileName[newFileName.length - 1];
	for (var i = 0; i < fileTypes.length; i++) {
		if (fileTypes[i] == newFileName) {
			fileTypeFlag = "1";
		}
	}
	if (fileTypeFlag == "0") {
		parent.layer.alert("文件必须是  xls, xlsx 文件!")
		$(this).val("");
		return false;
	}
});

function checkupload() {
	var formData = new FormData($("#signupForm")[0]);
	$.ajax({
		url : '/card/contactsInfo/importExcel?jsoncallback=?',
		type : 'POST',
		data : formData,
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		//        dataType: "jsonp",//问题就在这里，如果用了jsonp，那么后台就接收不到文件流，无法获得文件流，就没办法把文件写入服务器。如果不指定，就是注释掉，虽然ajax提交之后，还是跑到error那里去，但是文件已经是成功写入服务器的了。
		jsonp : "jsoncallback",
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.message)
			}

		}
	});
}