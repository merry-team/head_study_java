$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/xinzhizhu/adPosition/update",
		data : $('#signupForm').serialize(),// 你的formid
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
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			width : {
				required : true ,
				number : true ,
				minlength : 1 ,
				maxlength : 5
			},
			height : {
				required : true ,
				number : true ,
				minlength : 1 ,
				maxlength : 5
			},
			content : {
				required : true 
			}
		},
		messages : {
			name : {
				required : icon + "请输入位置名称"
			},
			width : {
				required : icon + "请输入位置宽度",
				number : icon + "请输入数字",
				minlength : icon + "宽度应该大于1位",
				maxlength : icon + "宽度应该小于5位"
			},
			height : {
				required : icon + "请输入位置高度",
				number : icon + "请输入数字",
				minlength : icon + "高度应该大于1位",
				maxlength : icon + "高度应该小于5位"
			},
			content : {
				required : icon + "请输入位置描述"
			}
		}
	})
}